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
	
	/*개인동아리 공지게시판*/
	@RequestMapping(value="/club/{clubSeq}/notice/list")
	public ModelAndView clubNoticeList(
			HttpServletRequest req,
			@PathVariable int clubSeq,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		String cp=req.getContextPath();
		
		int numPerPage   = 10;  // 한 화면에 보여주는 게시물 수
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		 // 전체 페이지 수
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchKey", searchKey);
        map.put("searchValue", searchValue);
        map.put("clubSeq", clubSeq);
        
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
        
        // 리스트의 번호
        int listNum, n = 0;
        Iterator<Notice> it=list.iterator();
        while(it.hasNext()) {
            Notice data = it.next();
            listNum = dataCount - (start + n - 1);
            data.setListNum(listNum);
            n++;
        }
        
        String params = "";
        String urlList = cp+"/club/"+clubSeq+"/notice/list";
        String urlArticle = cp+"/club/"+clubSeq+"/notice/article?page=" + current_page;
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlList = cp+"/club/"+clubSeq+"/notice/list?" + params;
            urlArticle = cp+"/club/"+clubSeq+"/notice/article?page=" + current_page + "&"+ params;
        }
        
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.list.공지게시판");
		
        mav.addObject("list", list);
        mav.addObject("subMenu","3");
        mav.addObject("clubSeq",clubSeq);
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/notice/download")
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
		String path=root+File.separator+"uploads"+File.separator+"notice";
		Notice dto=service.readNotice(map);
		boolean flag=false;
		
		if(dto!=null) {
			flag=fileManager.doFileDownload(
					     dto.getSaveFilename(), 
					     dto.getOriginalFilename(), path, resp);
		}
		
		if(! flag) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			out.print("<script>alert('파일 다운로드가 실패했습니다.');history.back();</script>");
		}
	}
	
	@RequestMapping(value="/club/{clubSeq}/notice/article")
	public ModelAndView readClubNotice(HttpSession session,
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
		
		// 조회수 증가
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		service.updateHitCount(map);

		// 해당 레코드 가져 오기
		Notice dto = service.readNotice(map);

		if(dto==null)
			return new ModelAndView("redirect:.club.{clubSeq}.notice.list?page="+page);
		
		// 전체 라인수
        // int linesu = dto.getContent().split("\n").length;
		
		// 스마트에디터를 사용하므로 엔터를 <br>로 변경하지 않음
        // dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        
		// 이전 글, 다음 글
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);

		Notice preReadDto = service.preReadNotice(map);
		Notice nextReadDto = service.nextReadNotice(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		int replyCount=service.replyDataCount(map);
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.article.공지글보기");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);

		mav.addObject("page", page);
		mav.addObject("subMenu","3");
		mav.addObject("params", params);
		mav.addObject("replyCount",replyCount);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/notice/update", 
			method=RequestMethod.GET)
	public ModelAndView updateClubNotice(
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
		Notice dto = (Notice) service.readNotice(map);
		
		if(dto==null) {
			return new ModelAndView("redirect:/notice/list?page="+page);
		}
		
		ModelAndView mav=new ModelAndView(".four.club.dari.notice.created.공지글수정");
		mav.addObject("dto", dto);
		mav.addObject("clubSeq",clubSeq);
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/notice/update", 
			method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session,	
			Notice dto, 
			@PathVariable int clubSeq,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
	
		// 수정 하기
		dto.setClubIdx(clubSeq);
		service.updateNotice(dto, path);
		
		return new ModelAndView("redirect:/club/"+clubSeq+"/notice/list?page="+page);
	}
	
	@RequestMapping(value="/club/{clubSeq}/notice/created",method=RequestMethod.GET)
	public ModelAndView insertNoticeForm(
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.created.공지글쓰기");
		mav.addObject("mode", "created");
		mav.addObject("subMenu","3");
		mav.addObject("clubSeq", clubSeq);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/notice/created",method=RequestMethod.POST)
	public ModelAndView insertClubNotice(
			HttpSession session,
			@PathVariable int clubSeq,
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
		dto.setClubIdx(clubSeq);
		service.insertNotice(dto, pathname);
		return new ModelAndView("redirect:/club/{clubSeq}/notice/list");
	}
	
	@RequestMapping(value="/club/{clubSeq}/notice/deleteFile", 
			method=RequestMethod.GET)
	public ModelAndView deleteFile(
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
		Notice dto = service.readNotice(map);
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
	
	@RequestMapping(value="/club/{clubSeq}/notice/delete")
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
		
		// 해당 레코드 가져 오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		Notice dto = service.readNotice(map);
		if(dto==null) {
			return new ModelAndView("redirect:/club/{clubSeq}/notice/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/club/{clubSeq}/notice/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
 	
		service.deleteNotice(map, dto.getSaveFilename(), path);
		
		return new ModelAndView("redirect:/club/{clubSeq}/notice/list?page="+page);
	}
	
	// 댓글 리스트
		@RequestMapping(value="/club/{clubSeq}/notice/listReply")
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
			
			// 리스트에 출력할 데이터
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			map.put("start", start);
			map.put("end", end);
			List<Reply> listReply=service.listReply(map);
			
			// 엔터를 <br>
			Iterator<Reply> it=listReply.iterator();
			int listNum, n=0;
			while(it.hasNext()) {
				Reply dto=it.next();
				listNum=dataCount-(start+n-1);
				dto.setListNum(listNum);
				dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				n++;
			}
			// 페이징처리(인수2개 짜리 js로 처리)
			String paging=myUtil.paging(current_page, total_page);
			
			ModelAndView mav=new ModelAndView("/club/dari/notice/listReply");
			
			// jsp로 넘길 데이터
			mav.addObject("listReply", listReply);
			mav.addObject("pageNo", current_page);
			mav.addObject("replyCount", dataCount);
			mav.addObject("total_page", total_page);
			mav.addObject("paging", paging);
			
			return mav;
		}

		// 댓글별 답글 리스트
		@RequestMapping(value="/club/{clubSeq}/notice/listReplyAnswer")
		public ModelAndView listReplyAnswer(
				@PathVariable int clubSeq,
				@RequestParam(value="answer") int answer
				) throws Exception {
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("clubSeq", clubSeq);
			map.put("answer", answer);
			
			List<Reply> listReplyAnswer=service.listReplyAnswer(map);
			
			// 엔터를 <br>
			Iterator<Reply> it=listReplyAnswer.iterator();
			while(it.hasNext()) {
				Reply dto=it.next();
				dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			}
			
			ModelAndView mav=new ModelAndView("/club/dari/notice/listReplyAnswer");

			// jsp로 넘길 데이터
			mav.addObject("listReplyAnswer", listReplyAnswer);
			
			return mav;
		}
		
		@RequestMapping(value="/club/{clubSeq}/notice/replyCount",
				method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> replyCount(
				@PathVariable int clubSeq,
				@RequestParam(value="num") int num
				) throws Exception {
			// AJAX(JSON) - 댓글별 개수

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
		
		// 댓글별 답글 개수
		@RequestMapping(value="/club/{clubSeq}/notice/replyCountAnswer",
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
			
	   	    // 작업 결과를 json으로 전송
						
			map.put("count", count);
			return map;
		}
		
		// 댓글 및 리플별 답글 추가
		@RequestMapping(value="/club/{clubSeq}/notice/createdReply",
				method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object>  createdReply(
				HttpSession session,
				@PathVariable int clubSeq,
				Reply dto
				) throws Exception {
			SessionInfo info=(SessionInfo) session.getAttribute("member");
			String state="true";
			if(info==null) { // 로그인이 되지 않는 경우
				state="loginFail";
			} else {
				dto.setUserIdx(info.getUserIdx());
				
				int result=service.insertReply(dto);
				if(result==0)
					state="false";
			}
			
	   	    // 작업 결과를 json으로 전송
			Map<String, Object> model = new HashMap<>(); 
			model.put("state", state);
			model.put("clubSeq", clubSeq);
			return model;
		}
		
		// 댓글 및 댓글별답글 삭제
		@RequestMapping(value="/club/{clubSeq}/notice/deleteReply",
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
			if(info==null) { // 로그인이 되지 않는 경우
				state="loginFail";
			} else {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("clubSeq", clubSeq);
				map.put("mode", mode);
				map.put("replyNum", replyNum);

				// 좋아요/싫어요 는 ON DELETE CASCADE 로 자동 삭제

	            // 댓글삭제
				int result=service.deleteReply(map);

				if(result==0)
					state="false";
			}
			
	   	    // 작업 결과를 json으로 전송
			Map<String, Object> model = new HashMap<>(); 
			model.put("state", state);
			return model;
		}
	/*개인동아리 공지게시판 끝*/
	
}
