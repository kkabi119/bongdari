package com.bong.club.notice;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;


@Controller("club.noticeController")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/club/index/main")
	public ModelAndView myClubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.�� ���Ƹ� ����");
		return mav;
	}
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/index/notice/list")
	public ModelAndView clubNoticeList(
			HttpServletRequest req,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		String cp=req.getContextPath();
		
		int numPerPage   = 10;  // �� ȭ�鿡 �����ִ� �Խù� ��
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET ����� ���
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		 // ��ü ������ ��
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchKey", searchKey);
        map.put("searchValue", searchValue);
        
        dataCount = service.dataCount(map);
        if(dataCount != 0)
            total_page = myUtil.pageCount(numPerPage,  dataCount) ;
		
        // �ٸ� ����� �ڷḦ �����Ͽ� ��ü ���������� ��ȭ �� ���
        if(total_page < current_page) 
            current_page = total_page;

        // ����Ʈ�� ����� �����͸� ��������
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);
        
        // �� ����Ʈ
        List<Notice> list = service.listNotice(map);
        
     // ����Ʈ�� ��ȣ
        int listNum, n = 0;
        Iterator<Notice> it=list.iterator();
        while(it.hasNext()) {
            Notice data = it.next();
            listNum = dataCount - (start + n - 1);
            data.setListNum(listNum);
            n++;
        }
        
        String params = "";
        String urlList = cp+"/club/index/notice/list";
        String urlArticle = cp+"/club/index/notice/article?page=" + current_page;
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlList = cp+"/club/index/notice/list?" + params;
            urlArticle = cp+"/club/index/notice/article?page=" + current_page + "&"+ params;
        }
        
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.list.�����Խ���");
		
        mav.addObject("list", list);
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/download")
	public void download(
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session,
			@RequestParam(value="num") int num
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			resp.sendRedirect(cp+"/member/login");
			return;
		}
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"notice";
		Notice dto=service.readNotice(num);
		boolean flag=false;
		
		if(dto!=null) {
			flag=fileManager.doFileDownload(
					     dto.getSaveFilename(), 
					     dto.getOriginalFilename(), path, resp);
		}
		
		if(! flag) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			out.print("<script>alert('���� �ٿ�ε尡 �����߽��ϴ�.');history.back();</script>");
		}
	}
	
	
	
	@RequestMapping(value="/club/index/notice/article")
	public ModelAndView readClubNotice(HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}

		searchValue = URLDecoder.decode(searchValue, "utf-8");
		
		// ��ȸ�� ����
		service.updateHitCount(num);

		// �ش� ���ڵ� ���� ����
		Notice dto = service.readNotice(num);

		if(dto==null)
			return new ModelAndView("redirect:.club.index.notice.list?page="+page);
		
		// ��ü ���μ�
        // int linesu = dto.getContent().split("\n").length;
		
		// ����Ʈ�����͸� ����ϹǷ� ���͸� <br>�� �������� ����
        // dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        
		// ���� ��, ���� ��
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("num", num);

		Notice preReadDto = service.preReadNotice(map);
		Notice nextReadDto = service.nextReadNotice(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		int replyCount=service.replyDataCount(map);
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.article.�����ۺ���");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);

		mav.addObject("page", page);
		mav.addObject("params", params);
		mav.addObject("replyCount",replyCount);
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/update", 
			method=RequestMethod.GET)
	public ModelAndView updateForm(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		Notice dto = (Notice) service.readNotice(num);
		
		if(dto==null) {
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
			
		if(! info.getUserId().equals(dto.getUserId())) {
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
		
		ModelAndView mav=new ModelAndView(".four.club.dari.notice.created.�����ۼ���");
		mav.addObject("dto", dto);
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/update", 
			method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session,	
			Notice dto, 
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
	
		// ���� �ϱ�
		service.updateNotice(dto, path);		
		
		return new ModelAndView("redirect:/club/index/notice/list?page="+page);
	}
	
	@RequestMapping(value="/club/index/notice/created",method=RequestMethod.GET)
	public ModelAndView createNoticeForm(
			HttpSession session
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.created.�����۾���");
		mav.addObject("mode", "created");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/deleteFile", 
			method=RequestMethod.GET)
	public ModelAndView deleteFile(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		Notice dto = service.readNotice(num);
		if(dto==null) {
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
			
		if(! info.getUserId().equals(dto.getUserId())) {
			return new ModelAndView("redirect:.four.club.dari.notice.list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
		if(dto.getSaveFilename() != null && dto.getSaveFilename().length()!=0) {
			  fileManager.doFileDelete(dto.getSaveFilename(), path);
			  
			  dto.setSaveFilename("");
			  dto.setOriginalFilename("");
			  service.updateNotice(dto, path);
       }
		
		return new ModelAndView("redirect:.four.club.dari.notice.update?num="+num+"&page="+page);
	}
	
	@RequestMapping(value="/club/index/notice/delete")
	public ModelAndView delete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		// �ش� ���ڵ� ���� ����
		Notice dto = service.readNotice(num);
		if(dto==null) {
			return new ModelAndView("redirect:/club/index/notice/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/club/index/notice/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
 	
		service.deleteNotice(num, dto.getSaveFilename(), path);
		
		return new ModelAndView("redirect:/club/index/notice/list?page="+page);
	}
	
	@RequestMapping(value="/club/index/notice/created",method=RequestMethod.POST)
	public ModelAndView insertClubNotice(
			HttpSession session,
			Notice dto
			) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"notice";
		
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		service.insertNotice(dto, pathname);
		return new ModelAndView("redirect:/club/index/notice/list");
	}
	
	// ��� ����Ʈ
		@RequestMapping(value="/club/index/notice/listReply")
		public ModelAndView listReply(
				@RequestParam(value="num") int num,
				@RequestParam(value="pageNo", defaultValue="1") int current_page
				) throws Exception {
			int numPerPage=5;
			int total_page=0;
			int dataCount=0;
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("num", num);
			
			dataCount=service.replyDataCount(map);
			total_page=myUtil.pageCount(numPerPage, dataCount);
			if(current_page>total_page)
				current_page=total_page;
			
			// ����Ʈ�� ����� ������
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			map.put("start", start);
			map.put("end", end);
			List<Reply> listReply=service.listReply(map);
			
			// ���͸� <br>
			Iterator<Reply> it=listReply.iterator();
			int listNum, n=0;
			while(it.hasNext()) {
				Reply dto=it.next();
				listNum=dataCount-(start+n-1);
				dto.setListNum(listNum);
				dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				n++;
			}
			// ����¡ó��(�μ�2�� ¥�� js�� ó��)
			String paging=myUtil.paging(current_page, total_page);
			
			ModelAndView mav=new ModelAndView("/club/dari/notice/listReply");
			
			// jsp�� �ѱ� ������
			mav.addObject("listReply", listReply);
			mav.addObject("pageNo", current_page);
			mav.addObject("replyCount", dataCount);
			mav.addObject("total_page", total_page);
			mav.addObject("paging", paging);
			
			return mav;
		}

		// ��ۺ� ��� ����Ʈ
		@RequestMapping(value="/club/index/notice/listReplyAnswer")
		public ModelAndView listReplyAnswer(
				@RequestParam(value="answer") int answer
				) throws Exception {
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("answer", answer);
			
			List<Reply> listReplyAnswer=service.listReplyAnswer(map);
			
			// ���͸� <br>
			Iterator<Reply> it=listReplyAnswer.iterator();
			while(it.hasNext()) {
				Reply dto=it.next();
				dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			}
			
			ModelAndView mav=new ModelAndView("/club/dari/notice/listReplyAnswer");

			// jsp�� �ѱ� ������
			mav.addObject("listReplyAnswer", listReplyAnswer);
			
			return mav;
		}
		
		@RequestMapping(value="/club/index/notice/replyCount",
				method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> replyCount(
				@RequestParam(value="num") int num
				) throws Exception {
			// AJAX(JSON) - ��ۺ� ����

			String state="true";
			int count=0;

			//String tableName="b_"+blogSeq;
	        Map<String, Object> map=new HashMap<String, Object>();
	 		//map.put("tableName", tableName);
	   		map.put("num", num);
	  	    
	   	    count=service.replyDataCount(map);
	   	    
	   	    Map<String, Object> model = new HashMap<>(); 
			model.put("state", state);
			model.put("count", count);
			
			return model;
		}
		
		// ��ۺ� ��� ����
		@RequestMapping(value="/club/index/notice/replyCountAnswer",
				method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object>  replyCountAnswer(
				@RequestParam(value="answer") int answer) throws Exception {
			
			int count=0;
			
			count=service.replyCountAnswer(answer);
			
	   	    // �۾� ����� json���� ����
			Map<String, Object> model = new HashMap<>(); 
			model.put("count", count);
			return model;
		}
		
		// ��� �� ���ú� ��� �߰�
		@RequestMapping(value="/club/index/notice/createdReply",
				method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object>  createdReply(
				HttpSession session,
				Reply dto) throws Exception {
			SessionInfo info=(SessionInfo) session.getAttribute("member");
			String state="true";
			if(info==null) { // �α����� ���� �ʴ� ���
				state="loginFail";
			} else {
				dto.setUserIdx(info.getUserIdx());
				int result=service.insertReply(dto);
				if(result==0)
					state="false";
			}
			
	   	    // �۾� ����� json���� ����
			Map<String, Object> model = new HashMap<>(); 
			model.put("state", state);
			return model;
		}
		
		// ��� �� ��ۺ���� ����
		@RequestMapping(value="/club/index/notice/deleteReply",
				method=RequestMethod.POST)
		@ResponseBody	
		public Map<String, Object>  deleteReply(
				HttpSession session,
				@RequestParam(value="replyNum") int replyNum,
				@RequestParam(value="mode") String mode
				) throws Exception {
			SessionInfo info=(SessionInfo) session.getAttribute("member");
			
			String state="true";
			if(info==null) { // �α����� ���� �ʴ� ���
				state="loginFail";
			} else {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("mode", mode);
				map.put("replyNum", replyNum);

				// ���ƿ�/�Ⱦ�� �� ON DELETE CASCADE �� �ڵ� ����

	            // ��ۻ���
				int result=service.deleteReply(map);

				if(result==0)
					state="false";
			}
			
	   	    // �۾� ����� json���� ����
			Map<String, Object> model = new HashMap<>(); 
			model.put("state", state);
			return model;
		}
	/*���ε��Ƹ� �����Խ��� ��*/
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/index/free/list")
	public ModelAndView ListClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.list.�����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/create")
	public ModelAndView insertClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.create.�����۾���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/article")
	public ModelAndView readClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.�����ۺ���");
		return mav;
	}
	/*���ε��Ƹ� �����Խ��� ��*/
	
}
