package com.bong.demander.review;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
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



@Controller("bong.dereviewController")
public class DereviewController {
	@Autowired
	private DeReviewService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/demander/index/review/list")
	public ModelAndView deReviewList(
			HttpServletRequest req,
			@RequestParam(value="page",defaultValue="1")int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			
			) throws Exception {
		String cp = req.getContextPath();
	   	    
			int numPerPage = 5;  // 한 화면에 보여주는 게시물 수
			int total_page = 0;
			int dataCount = 0;
	   	    
			if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
				searchValue = URLDecoder.decode(searchValue, "utf-8");
			}
			
	        // 전체 페이지 수
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
	        List<DeReview> list = service.listDeReview(map);

	        // 리스트의 번호
	        int listNum, n = 0;
	        Iterator<DeReview> it=list.iterator();
	        while(it.hasNext()) {
	        	DeReview data = it.next();
	            listNum = dataCount - (start + n - 1);
	            data.setListNum(listNum);
	            n++;
	        }
	        
	        String params = "";
	        String urlList = cp+"/demander/index/review/list";
	        String urlArticle = cp+"/demander/index/review/article?page=" + current_page;
	        if(searchValue.length()!=0) {
	        	params = "searchKey=" +searchKey + 
	        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
	        }
	        
	        if(params.length()!=0) {
	            urlList = cp+"/demander/index/review/list?" + params;
	            urlArticle = cp+"/demander/index/review/article?page=" + current_page + "&"+ params;
	        }

	        ModelAndView mav=new ModelAndView(".four.demander.dari.review.list.후기게시판");
	        
	        mav.addObject("list", list);
	        mav.addObject("urlArticle", urlArticle);
	        mav.addObject("page", current_page);
	        mav.addObject("dataCount", dataCount);
	        mav.addObject("total_page", total_page);
	        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));	
	        
		
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review/create",method=RequestMethod.GET)
	public ModelAndView deRevCreateForm(
			HttpSession session
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/");
		}*/
		
		ModelAndView mav=new ModelAndView(".four.demander.dari.review.create.후기게시판");
		mav.addObject("mode", "created");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review/create",method=RequestMethod.POST)
	public ModelAndView deRevCreateSubmit(
			HttpSession session,
			DeReview dto
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		
		//dto.setUserId(info.getUserId());
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		
		service.insertDeReview(dto, path);
		
		
		
		return new ModelAndView("redirect:/demander/index/review/list");
		
	}
	
	
	@RequestMapping(value="/demander/index/review/article")
	public ModelAndView deReArticle(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") int page,
			@RequestParam(value="searchKey",defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue",defaultValue="") String searchValue
			) throws Exception {
		
	
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		//검색값 decode
		searchValue= URLDecoder.decode(searchValue,"utf-8");
		
		//조회수증가
		service.updateHitCount(num);
		
		//해당아티클가져오기
		DeReview dto=service.readDeReview(num);
		if(dto==null)
			return new ModelAndView("redirect:/");
			//return new ModelAndView("redirect:.demander.index.review.list?page="+page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("num", num);
		DeReview preReadDto = service.preReadDeReview(map);
		DeReview nextReadDto = service.nextReadDeReview(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		ModelAndView mav=new ModelAndView(".four.demander.dari.review.article.후기게시판");
		
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		mav.addObject("page", page);
		mav.addObject("params", params);
        return mav;
	}
	
	//다운로드
	@RequestMapping(value="/demander/index/review/download")
	public void deReviewDownload(
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session,
			@RequestParam(value="num") int num
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			resp.sendRedirect(cp+"/member/login");
			return;
		}*/
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		DeReview dto=service.readDeReview(num);
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
	
	@RequestMapping(value = "demander/index/review/update", method=RequestMethod.GET)
	public ModelAndView deRevUpdateForm(HttpSession session,
			@RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page
			) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");
		

		DeReview dto = service.readDeReview(num);
		/*if (dto == null) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}*/
	
		if (info.getUserIdx()!=dto.getUserIdx())
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);

		ModelAndView mav=new ModelAndView(".four.demander.dari.review.create.후기게시판");
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		mav.addObject("dto", dto);
		return mav;
	}

	@RequestMapping(value = "demander/index/review/update", method=RequestMethod.POST)
	public String deRevUpdateSubmit(HttpSession session, 
			DeReview dto,
			@RequestParam(value = "page") String page) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		/*
		if (info == null) {
			return "redirect:/member/login";
		}*/
		
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
				
		// 수정 하기
		service.updateDeReview(dto, path);
		
		return "redirect:/demander/index/review/list?page="+page;
	}

	@RequestMapping(value="/demander/index/review/deleteFile", 
			method=RequestMethod.GET)
	public ModelAndView deleteFile(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		DeReview dto = service.readDeReview(num);
		if(dto==null) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}
			
		if(! info.getUserId().equals(dto.getUserId())) {
			return new ModelAndView("redirect:.four.demander.dari.review.list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
		
		if(dto.getSaveFilename() != null && dto.getSaveFilename().length()!=0) {
			  fileManager.doFileDelete(dto.getSaveFilename(), path);
			  
			  dto.setSaveFilename("");
			  dto.setOriginalFilename("");
			  service.updateDeReview(dto, path);
       }
		
		return new ModelAndView("redirect:/demander/index/review/update?num="+num+"&page="+page);
	}
	

	@RequestMapping(value="/demander/index/review/delete")
	public ModelAndView delete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		// 해당 레코드 가져 오기
		DeReview dto = service.readDeReview(num);
		if(dto==null) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
 	
		service.deleteDeReview(num, dto.getSaveFilename(), path);
		
		return new ModelAndView("redirect:/demander/index/review/list?page="+page);
	}
	
	//좋아요
	@RequestMapping(value="/demander/index/review/sendLike",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deReviewLike(
			HttpSession session,
			DeReview dto) throws Exception {
	
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		
		int state=service.stateDeRevLike(dto);
		
		if(state==0){
			dto.setUserIdx(info.getUserIdx());
			service.insertDeReviewLike(dto);
		}else if(state==1){
			dto.setUserIdx(info.getUserIdx());
			service.deleteDeReviewLike(dto);
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		return model;
	}
	
	// 좋아요/싫어요 개수
	@RequestMapping(value="/demander/index/review/countLike",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  countLike(
			@RequestParam(value="serviceReviewIdx") int num) throws Exception {
		System.out.println("asd");
		int likeCount=0;
		Map<String, Object> map=service.deRevCountLike(num);
		if(map!=null) {
			// resultType이 map인 경우 int는 BigDecimal로 넘어옴
			likeCount=((BigDecimal)map.get("LIKECOUNT")).intValue();
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("likeCount", likeCount);
		
		System.out.println("controller_likeCount:"+likeCount);
		return model;
	}
	
	//댓글리스트
	@RequestMapping(value="/demander/index/review/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			) throws Exception {
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("serviceReviewIdx", num);
		
		
		dataCount=service.DeReviewReplyDataCount(map);
		total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		// 리스트에 출력할 데이터
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		map.put("start", start);
		map.put("end", end);
		map.put("serviceReviewIdx", num);
		List<DeReviewReply> listReply=service.listDeReviewReply(map);
		
		// 엔터를 <br>
		Iterator<DeReviewReply> it=listReply.iterator();
		int listNum, n=0;
		while(it.hasNext()) {
			DeReviewReply dto=it.next();
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			n++;
		}
		// 페이징처리(인수2개 짜리 js로 처리)
		String paging=myUtil.paging(current_page, total_page);
		
		ModelAndView mav=new ModelAndView("/demander/dari/review/listReply");
		
		// jsp로 넘길 데이터
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	// 댓글별 답글 리스트
			@RequestMapping(value="/demander/index/review/listReplyAnswer")
			public ModelAndView listReplyAnswer(
					@RequestParam(value="answer") int answer
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				List<DeReviewReply> listReplyAnswer=service.listDeReviewReplyAnswer(answer);
				
				// 엔터를 <br>
				Iterator<DeReviewReply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {
					DeReviewReply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/demander/dari/review/listReplyAnswer");

				// jsp로 넘길 데이터
				mav.addObject("listReplyAnswer", listReplyAnswer);
				
				return mav;
			}
	
			@RequestMapping(value="/demander/index/review/replyCount",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> replyCount(
					@RequestParam(value="num") int num
					) throws Exception {
				// AJAX(JSON) - 댓글별 개수

				String state="true";
				int count=0;

				//String tableName="b_"+blogSeq;
		        Map<String, Object> map=new HashMap<String, Object>();
		 		//map.put("tableName", tableName);
		   		map.put("serviceReviewIdx", num);
		  	    
		   	    count=service.DeReviewReplyDataCount(map);
		   	    
		   	    Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				model.put("count", count);
				
				return model;
			}
			
			@RequestMapping(value="/demander/index/review/replyCountAnswer",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer(
					@RequestParam(value="answer") int answer) throws Exception {
				int count=0;
				count=service.DeReviewReplyCountAnswer(answer);
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("count", count);
				return model;
			}
			
			// 댓글 및 리플별 답글 추가
			@RequestMapping(value="/demander/index/review/createdReply",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  createdReply(
					HttpSession session,
					DeReviewReply dto) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				String state="true";
				if(info==null) { // 로그인이 되지 않는 경우
					state="loginFail";
				} else {
					dto.setUserIdx(info.getUserIdx());
					int result=service.insertDeReviewReply(dto);
					if(result==0)
						state="false";
				}
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			// 댓글 및 댓글별답글 삭제
			@RequestMapping(value="/demander/index/review/deleteReply",
					method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,
					@RequestParam(value="replyNum") int replyNum,
					@RequestParam(value="mode") String mode
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				String state="true";
				if(info==null) { // 로그인이 되지 않는 경우
					state="loginFail";
				} else {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("mode", mode);
					map.put("replyNum", replyNum);

					// 좋아요/싫어요 는 ON DELETE CASCADE 로 자동 삭제

		            // 댓글삭제
					int result=service.deleteDeReviewReply(map);

					if(result==0)
						state="false";
				}
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
	
	
}
