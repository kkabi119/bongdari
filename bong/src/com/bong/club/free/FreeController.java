package com.bong.club.free;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

@Controller("club.freeController")
public class FreeController {
	
	@Autowired
	private FreeService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/{clubSeq}/free/list")
	public ModelAndView ListClubFree(
			HttpServletRequest req,
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@PathVariable int clubSeq
			) throws Exception {
		
		int numPerPage   = 8;  // �� ȭ�鿡 �����ִ� �Խù� ��
		int total_page = 0;
		int dataCount = 0;
		
		// ��ü ������ ��
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clubSeq", clubSeq);
        
        dataCount = service.dataCount(map);
        if(dataCount != 0){
        	if(dataCount > 0) {
    			if(dataCount % numPerPage == 0)
    				total_page=dataCount/numPerPage;
    			else
    				total_page=dataCount/numPerPage+1;
    		}
        }
        
        // �ٸ� ����� �ڷḦ �����Ͽ� ��ü ���������� ��ȭ �� ���
        if(total_page < current_page) 
            current_page = total_page;
        
        ModelAndView mav = new ModelAndView(".four.club.dari.free.list.�����Խ���");
        mav.addObject("clubSeq",clubSeq);
        mav.addObject("total_page", total_page);
        mav.addObject("subMenu","5");
             
		return mav;
	}
	
	/* list Ajax �� */
	@RequestMapping(value="/club/{clubSeq}/free/list2")
	public ModelAndView ListClubFree2(
			HttpServletRequest req,
			@PathVariable int clubSeq,
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		current_page++;
		String cp=req.getContextPath();
		
		int numPerPage   = 8;  // �� ȭ�鿡 �����ִ� �Խù� ��
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET ����� ���
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		 // ��ü ������ ��
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchKey", searchKey);
        map.put("searchValue", searchValue);
        map.put("clubSeq", clubSeq);
        
        dataCount = service.dataCount(map);
        if(dataCount != 0){
        	if(dataCount > 0) {
    			if(dataCount % numPerPage == 0)
    				total_page=dataCount/numPerPage;
    			else
    				total_page=dataCount/numPerPage+1;
    		}
        }
        // �ٸ� ����� �ڷḦ �����Ͽ� ��ü ���������� ��ȭ �� ���
        if(total_page < current_page) 
            current_page = total_page;

        // ����Ʈ�� ����� �����͸� ��������
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);
        
        // �� ����Ʈ
        List<Free> list = service.listFree(map);
        
        // ����Ʈ�� ��ȣ, ����Ʈ���� ���������� �̹��� ���ֱ�, ù��°���� �泻�Ϸ� �ø���
        Pattern pattern=Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
        Matcher match;
        String content=null;
        int listNum, n = 0;
        Iterator<Free> it=list.iterator();
        while(it.hasNext()) {
            Free data = it.next();
            listNum = dataCount - (start + n - 1);
            
            data.setListNum(listNum);
            match=pattern.matcher(data.getContent());
            
            if(match.find())
            	data.setListImageName(match.group(0));
            
            content=data.getContent().replaceAll("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>","");
           
            if(content.length()<50){
            	 data.setContent(content);
                 n++;
            }
            else {
            content=content.substring(0, 50);
            data.setContent(content);
            n++;
            }
        } 
        String params = "";
        String urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page;
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page + "&"+ params;
        }
        
        ModelAndView mav = new ModelAndView("club/dari/free/list2");
		
        mav.addObject("list", list);
        mav.addObject("clubSeq",clubSeq);
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        
		return mav;
	}
	
	/* �����Խ��� �۾��� �� */
	@RequestMapping(value="/club/{clubSeq}/free/created",method=RequestMethod.GET)
	public ModelAndView insertFreeForm(
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.created.�����۾���");
		mav.addObject("mode", "created");
		mav.addObject("clubSeq", clubSeq);
		mav.addObject("subMenu","5");
		return mav;
	}
	
	
	/* �����Խ��� �۾��� �� */
	@RequestMapping(value="/club/{clubSeq}/free/created",method=RequestMethod.POST)
	public ModelAndView insertClubFree(
			HttpSession session,
			@PathVariable int clubSeq,
			Free dto
			) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"free";
		
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		dto.setClubIdx(clubSeq);
		service.insertFree(dto, pathname);
		return new ModelAndView("redirect:/club/{clubSeq}/free/list");
	}
	
	@RequestMapping(value="/club/{clubSeq}/free/download")
	public void download(
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session,
			@PathVariable int clubSeq,
			@RequestParam(value="num") int num
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			resp.sendRedirect(cp+"/member/login");
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"Free";
		Free dto=service.readFree(map);
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
	
	/*���ε��Ƹ� �����Խ��� ��*/
	@RequestMapping(value="/club/{clubSeq}/free/article")
	public ModelAndView readClubFree(HttpSession session,
			@PathVariable int clubSeq,
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		service.updateHitCount(map);

		// �ش� ���ڵ� ���� ����
		Free dto = service.readFree(map);

		if(dto==null)
			return new ModelAndView("redirect:.club.{clubSeq}.free.list?page="+page);
        
		// ���� ��, ���� ��
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);

		Free preReadDto = service.preReadFree(map);
		Free nextReadDto = service.nextReadFree(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		int replyCount=service.replyDataCount(map);
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.�����Խ��� �ۺ���");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);

		mav.addObject("page", page);
		mav.addObject("subMenu","5");
		mav.addObject("params", params);
		mav.addObject("replyCount",replyCount);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/free/update", 
			method=RequestMethod.GET)
	public ModelAndView updateClubFree(
			HttpSession session,
			@PathVariable int clubSeq,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		Free dto = (Free) service.readFree(map);
		
		if(dto==null) {
			return new ModelAndView("redirect:/free/list?page="+page);
		}
			
		if(! info.getUserId().equals(dto.getUserId())) {
			return new ModelAndView("redirect:/free/list?page="+page);
		}
		
		ModelAndView mav=new ModelAndView(".four.club.dari.free.created.�����ۼ���");
		mav.addObject("dto", dto);
		mav.addObject("clubSeq",clubSeq);
		mav.addObject("mode", "update");
		mav.addObject("subMenu","5");
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/free/update", 
			method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session,	
			Free dto, 
			@PathVariable int clubSeq,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "free";		
	
		// ���� �ϱ�
		dto.setClubIdx(clubSeq);
		service.updateFree(dto, path);
		
		return new ModelAndView("redirect:/club/"+clubSeq+"/free/list?page="+page);
	}
	
	@RequestMapping(value="/club/{clubSeq}/free/delete")
	public ModelAndView delete(
			HttpSession session,
			@PathVariable int clubSeq,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		// �ش� ���ڵ� ���� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		Free dto = service.readFree(map);
		if(dto==null) {
			return new ModelAndView("redirect:/club/{clubSeq}/free/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/club/{clubSeq}/free/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "free";		
 	
		service.deleteFree(map, dto.getSaveFilename(), path);
		
		return new ModelAndView("redirect:/club/{clubSeq}/free/list?page="+page);
	}
	
	// ��� �� ���ú� ��� �߰�
			@RequestMapping(value="/club/{clubSeq}/free/createdReply",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  createdReply(
					HttpSession session,
					@RequestParam int num,
					@PathVariable int clubSeq,
					Reply dto
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				String state="true";
				if(info==null) { // �α����� ���� �ʴ� ���
					state="loginFail";
				} else {
					dto.setUserIdx(info.getUserIdx());
					dto.setClubFreeIdx(num);
					
					int result=service.insertReply(dto);
					if(result==0)
						state="false";
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				model.put("clubSeq", clubSeq);
				return model;
			}
			
			// ��� ����Ʈ
			@RequestMapping(value="/club/{clubSeq}/free/listReply")
			public ModelAndView listReply(
					@RequestParam(value="num") int num,
					@PathVariable int clubSeq,
					@RequestParam(value="pageNo", defaultValue="1") int current_page
					) throws Exception {
				int numPerPage=5;
				int total_page=0;
				int dataCount=0;
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("clubSeq", clubSeq);
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
				
				ModelAndView mav=new ModelAndView("/club/dari/free/listReply");
				
				// jsp�� �ѱ� ������
				mav.addObject("listReply", listReply);
				mav.addObject("pageNo", current_page);
				mav.addObject("replyCount", dataCount);
				mav.addObject("total_page", total_page);
				mav.addObject("paging", paging);
				
				return mav;
			}
	
	// ��� �� ��ۺ���� ����
			@RequestMapping(value="/club/{clubSeq}/free/deleteReply",
					method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,
					@PathVariable int clubSeq,
					@RequestParam(value="replyNum") int replyNum,
					@RequestParam(value="mode") String mode
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				String state="true";
				if(info==null) { // �α����� ���� �ʴ� ���
					state="loginFail";
				} else {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("clubSeq", clubSeq);
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
			
			// ��ۺ� ��� ����
			@RequestMapping(value="/club/{clubSeq}/free/replyCountAnswer",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer(
					@PathVariable int clubSeq,
					@RequestParam(value="answer") int answer
					) throws Exception {
				
				int count=0;
				Map<String , Object> map = new HashMap<String, Object>();
				map.put("clubSeq", clubSeq);
				map.put("answer", answer);
				count=service.replyCountAnswer(map);
				
		   	    // �۾� ����� json���� ����
							
				map.put("count", count);
				return map;
			}
			
			@RequestMapping(value="/club/{clubSeq}/free/replyCount",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> replyCount(
					@PathVariable int clubSeq,
					@RequestParam(value="num") int num
					) throws Exception {
				// AJAX(JSON) - ��ۺ� ����

				String state="true";
				int count=0;
		        Map<String, Object> map=new HashMap<String, Object>();
		        map.put("clubSeq", clubSeq);
		   		map.put("num", num);
		  	    
		   	    count=service.replyDataCount(map);
		   	    
		   	    Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				model.put("count", count);
				
				return model;
			}
			
			// ��ۺ� ��� ����Ʈ
			@RequestMapping(value="/club/{clubSeq}/free/listReplyAnswer")
			public ModelAndView listReplyAnswer(
					@PathVariable int clubSeq,
					@RequestParam(value="answer") int answer
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("clubSeq", clubSeq);
				map.put("answer", answer);
				
				List<Reply> listReplyAnswer=service.listReplyAnswer(map);
				
				// ���͸� <br>
				Iterator<Reply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {
					Reply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/club/dari/free/listReplyAnswer");

				// jsp�� �ѱ� ������
				mav.addObject("listReplyAnswer", listReplyAnswer);
				
				return mav;
			}	
}
