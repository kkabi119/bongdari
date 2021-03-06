package com.bong.notice;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;

@Controller("notice.noticeController")
public class NoticeController {
   
	@Autowired 
	private NoticeService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/notice/created", method=RequestMethod.GET)
	public ModelAndView insertNoticeForm(
			HttpSession session
			) throws Exception{
		SessionInfo info =(SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		ModelAndView mav = new ModelAndView(".layout.customer.notice.created.글쓰기");
		mav.addObject("mode", "created");
		return mav;
	}
	@RequestMapping(value="/notice/created", method=RequestMethod.POST)
	public ModelAndView insertNotice(
			HttpSession session
		   ,Notice dto
			) throws Exception{
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		String root = session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"notice";
		
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		
		service.insertNotice(dto, pathname);
		
		return new ModelAndView("redirect:/notice/list");
		
	}
	//공지 게시판
	@RequestMapping(value="/notice/list")
	public ModelAndView noticeList(
			HttpServletRequest req
		   ,@RequestParam(value="page", defaultValue="1") int current_page
		   ,@RequestParam(value="searchKey", defaultValue="subject") String searchKey
		   ,@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception{
		String cp=req.getContextPath();
		
	
		//페이징 
		int numPerPage = 10; //한 화면에 보여줄 게시물 개수
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		//전체 페이지 수 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		
		dataCount = service.dataCount(map);
		
	    if(dataCount != 0)
            total_page = myUtil.pageCount(numPerPage,  dataCount) ;
		
        // 다른 사람이 자료를 삭제하여 전체 페이지수가 변화 된 경우
        if(total_page < current_page) 
            current_page = total_page;
        
        // 리스트에 출력할 데이터를 가져오기
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);
        
        // 글 리스트
        List<Notice> list = service.listNotice(map);
        
        // 리스트 번호 
        int listNum, n = 0;
        Iterator<Notice> it = list.iterator();
        while(it.hasNext()){
        	Notice data = it.next();
        	listNum = dataCount-(start+n-1);
        	data.setListNum(listNum);
        	n++;
        }
        
        String params = "";
        String urlList = cp+"/notice/list";
        String urlArticle = cp+"/notice/article?page="+current_page;
        
        if(searchValue.length()!=0){
        	params = "searchKey="+searchKey+
        			"&searchValue="+URLEncoder.encode(searchValue, "utf-8");
        }
        
        if(params.length()!=0){
        	urlList = cp+"/notice/list?"+params;
        	urlArticle = cp+"/notice/article?page="+current_page+"&"+params;
        }
        ModelAndView mav = new ModelAndView(".layout.customer.notice.list.공지사항");
		
		mav.addObject("list", list);
		mav.addObject("urlArticle", urlArticle);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
		
		
		return mav;
	}
	//다운로드
	@RequestMapping(value="/notice/download")
	public void download(
			HttpServletRequest req
		   ,HttpServletResponse resp
		   ,HttpSession session
		   ,@RequestParam(value="num") int num
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			resp.sendRedirect(cp+"/member/login");
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"notice";
		Notice dto = service.readNotice(map);
		boolean flag = false;
		
		if(dto!=null){
			flag=fileManager.doFileDownload(dto.getSaveFilename()
					,dto.getOriginalFilename(), path, resp);
		}
		
		if(! flag){
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print("<script>alert('파일 다운로드가 실패했습니다.');history.back();</script>");
		}
	}
	//글보기
	@RequestMapping(value="/notice/article")
	public ModelAndView readNotice(
			HttpSession session
		   ,@RequestParam(value="num") int num
		   ,@RequestParam(value="page") String page
		   ,@RequestParam(value="searchKey", defaultValue="subject") String searchKey
		   ,@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception{
		
		SessionInfo info= (SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		searchValue = URLDecoder.decode(searchValue,"utf-8");
		
		//조회수 증가
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("num", num);
		service.updateHitCount(map);
		
		//해당 레코드 가져오기
		Notice dto = service.readNotice(map);
		
		if(dto==null)
			return new ModelAndView("redirect:.notice.list?page="+page);
		
		//이전글, 다음글
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		
		Notice preReadDto = service.preReadNotice(map);
		Notice nextReadDto = service.nextReadNotice(map);
		
		String params = "page="+page;
		if(searchValue.length()!=0){
			params += "&searchKey=" +searchKey+
					"&searchValue="+URLEncoder.encode(searchValue,"utf-8");
		}
		
		int replyCount=service.replyDataCount(map);
		
		ModelAndView mav = new ModelAndView(".layout.customer.notice.article.공지글 보기");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		
		mav.addObject("page", page);
		mav.addObject("params", params);
		mav.addObject("replyCount", replyCount);
		return mav;
	}
	@RequestMapping(value="/notice/update", method=RequestMethod.GET)
	public ModelAndView updateNotice(
			HttpSession session
		   ,@RequestParam(value="num") int num
		   ,@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		Notice dto = (Notice)service.readNotice(map);
		
		
		if(dto==null){
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
	    if(! info.getUserName().equals(dto.getUserName()) || ! info.getUserName().equals("관리자")){
	    	return new ModelAndView("redirect:/notice/list?page="+page);
	    }
	    
		ModelAndView mav = new ModelAndView(".layout.customer.notice.created.공지글수정");
		mav.addObject("dto", dto);
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		return mav;
	}
	@RequestMapping(value="/notice/update", method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session
		   ,Notice dto
		   ,@RequestParam(value="page") String page
			) throws Exception{
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"notice";
		
		//수정하기
		service.updateNotice(dto, path);
		
		return new ModelAndView("redirect:/notice/list?page="+page);
	   
	}
	@RequestMapping(value="/notice/delete")
	public ModelAndView delete(
			HttpSession session
		   ,@RequestParam(value="num") int num
		   ,@RequestParam(value="page") String page
			) throws Exception{
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		//해당 레코드 가져오기
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("num", num);
		
		Notice dto = service.readNotice(map);
		if(dto==null){
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")){
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator +"uploads"+File.separator+"notice";
		
		service.deleteNotice(map, dto.getSaveFilename(), path);
		 return new ModelAndView("redirect:/notice/list");
	  
	}
	@RequestMapping(value="/notice/deleteFile", method=RequestMethod.GET)
	public ModelAndView deleteFile(
			HttpSession session
		   ,@RequestParam(value="num") int num
		   ,@RequestParam(value="page") String page
			) throws Exception{
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("num", num);
		Notice dto = service.readNotice(map);
		
		if(dto==null){
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
		
		if(! info.getUserName().equals(dto.getUserName()) || ! info.getUserName().equals("관리자")){
			return new ModelAndView("redirect:.layout.customer.notice.list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator+"uploads"+File.separator+"notice";
		
		if(dto.getSaveFilename()!=null && dto.getSaveFilename().length()!=0){
			fileManager.doFileDelete(dto.getSaveFilename(), path);
			
			dto.setSaveFilename("");
			dto.setOriginalFilename("");
			service.updateNotice(dto, path);
		}
		
		return new ModelAndView("redirect:.layout.customer.notice.update?num="+num+"&page"+page);
		 
	}
	
	// 댓글 리스트
	@RequestMapping(value="/notice/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num
		   ,@RequestParam(value="pageNo", defaultValue="1") int current_page
			) throws Exception{
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		
		dataCount=service.replyDataCount(map);
		total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		// 리스트에 출력할 데이터
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		map.put("start", start);
		map.put("end", end);
		List<Reply> listReply = service.listReply(map);
		
		//엔터를 <br>
		Iterator<Reply> it=listReply.iterator();
		int listNum, n=0;
		while(it.hasNext()){
			Reply dto=it.next();
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			n++;
		}
		// 페이징 처리(인수2개짜리 js로 처리)
		String paging=myUtil.paging(current_page, total_page);
		
		ModelAndView mav=new ModelAndView("/customer/notice/listReply");
		
		//jsp로 넘길 데이터
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	// 댓글 및 리플별 답글 추가
	@RequestMapping(value="/notice/createdReply", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createdReply(
			HttpSession session
		   ,Reply dto
			) throws Exception{
       SessionInfo info=(SessionInfo) session.getAttribute("member");
       String state="true";
       if(info==null){
    	   state="loginFail";
       } else {
    	   dto.setUserIdx(info.getUserIdx());
    	   
    	   int result=service.insertReply(dto);
    	   if(result==0)
    		   state="false";
       }
       
       // 작업결과를 json으로 전송
       Map<String, Object> model = new HashMap<>();
       model.put("state", state);
       return model;
	}
	// 답글 리스트
	@RequestMapping(value="/notice/listReplyAnswer")
	@ResponseBody
	public ModelAndView listReplyAnswer(
			@RequestParam(value="answer") int answer
			) throws Exception{
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("answer", answer);
		
		List<Reply> listReplyAnswer=service.listReplyAnswer(map);
		
		// 엔터를 <br>
		Iterator<Reply> it=listReplyAnswer.iterator();
		while(it.hasNext()){
			Reply dto = it.next();
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		}
		
		ModelAndView mav=new ModelAndView("/customer/notice/listReplyAnswer");
	
		//jsp로 넘길 데이터
		mav.addObject("listReplyAnswer", listReplyAnswer);
		return mav;
	}
	@RequestMapping(value="/notice/replyCount", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyCount(
			@RequestParam(value="num") int num
			) throws Exception{
		//AJAX(JSON) - 댓글 별 개수
	 
		String state="true";
		int count=0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		
		count=service.replyDataCount(map);
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);
		model.put("count", count);
	   
		return model;
	}
	  // 댓글 및 리플별 답글 개수
	@RequestMapping(value="/notice/replyCountAnswer", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyCountAnswer(
			@RequestParam(value="answer") int answer
			) throws Exception{
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("answer", answer);
		count=service.replyCountAnswer(map);
		
		// 작업결과를 json으로 전송
		map.put("count", count);
		return map;
	}
	@RequestMapping(value="/notice/deleteReply", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteReply(
			HttpSession session
		   ,@RequestParam(value="replyNum") int replyNum
		   ,@RequestParam(value="mode") String mode
			) throws Exception{
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		
		String state="true";
		if(info==null){
			state="loginFail";
		} else {
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("mode", mode);
			map.put("replyNum", replyNum);
			
			//좋아요 ,싫어요는 ON DELETE CASCADE 로 자동삭제
			
			// 댓글 삭제
			int result=service.deleteReply(map);
			
			if(result==0)
				state="false";
		}
		
		// 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		return model;
	}
}
