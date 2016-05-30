package com.bong.club.apply;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;



@Controller("club.applyController")
public class ApplyController {
	
	@Autowired
	private ApplyService service;
	
	@Autowired
	private MyUtil myUtil;
	
	
	/*개인동아리 봉사 신청게시판*/
	
	///////////////////////////////////////////// 리스트 ///////////////////////////////////////////////////
	@RequestMapping(value="/club/index/apply/list")
	public ModelAndView clubApplyList(
			HttpServletRequest req,
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
                
        dataCount = service.dataCount(map);
        System.out.println("dataCount="+dataCount);
        
        if(dataCount != 0)
            total_page = myUtil.pageCount(numPerPage,  dataCount) ;
		
        System.out.println("total_page="+total_page);
    
		// 다른 사람이 자료를 삭제하여 전체 페이지수가 변화 된 경우
        if(total_page < current_page) 
            current_page = total_page;

        // 리스트에 출력할 데이터를 가져오기
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);

        System.out.println("start: "+start);
        System.out.println("end: "+end);
        System.out.println("current_page: "+current_page);
        System.out.println("numPerPage: "+numPerPage);
        // 글 리스트
        List<Apply> list = service.listApply(map);
                     
     // 리스트의 번호
        int listNum, n = 0;
        Iterator<Apply> it=list.iterator();
        
        while(it.hasNext()) {
            Apply data = it.next();
            listNum = dataCount - (start + n - 1);
            
            data.setListNum(listNum);
              
            n++;
        }
        
        String params = "";
        String urlList = cp+"/club/index/apply/list";
        String urlArticle = cp+"/club/index/apply/article?page=" + current_page;
      
        //검색인경우
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }        
        if(params.length()!=0) {
            urlList = cp+"/club/index/apply/list?" + params;
            urlArticle = cp+"/club/index/apply/article?page=" + current_page + "&"+ params;
        }
        
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.list.봉사신청");
		
        mav.addObject("list", list);
       
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
        
		return mav;
	}
	
	///////////////////////////////////////////// 글보기 ///////////////////////////////////////////////////
	@RequestMapping(value="/club/index/apply/article")
	public ModelAndView readClubApply(HttpSession session,
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
		
		// 해당 레코드 가져 오기
		Apply dto = service.readApply(num);
		System.out.println(dto+" dto를 가져왓다");
		if(dto==null)
			return new ModelAndView("redirect:.club.index.apply?page="+page);
		
		// 조회수 증가
		service.updateHitCount(dto.getVolunIdx());
		service.updateHitCount_club(dto.getClubApplyIdx());
		
		// 전체 라인수
        // int linesu = dto.getContent().split("\n").length;
		
		// 스마트에디터를 사용하므로 엔터를 <br>로 변경하지 않음
        // dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        
		/////////////////// 이전 글, 다음 글 05/23 3시시작
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("num", num);

		Apply preReadDto = service.preReadApply(map);
		Apply nextReadDto = service.nextReadApply(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.article.봉다리 개인페이지");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);

		mav.addObject("page", page);
		mav.addObject("params", params);
		return mav;
	}
	
	////////////////////////////////////////////////////////// 		댓글관련 	///////////////////////////////////////////////////////
	@RequestMapping(value="/club/index/apply/createdReply",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  createdReply(	HttpSession session, Reply dto) throws Exception {
		
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		String state="true";
		if(info==null) { // 로그인이 되지 않는 경우
			state="loginFail";
		} else {
			
			System.out.println("유저 idx="+info.getUserIdx());
			System.out.println("유저 id="+info.getUserId());
			
			dto.setUserIdx(info.getUserIdx());
			dto.setUserId(info.getUserId());
			System.out.println("dto유저 id"+dto.getUserIdx());
			int result=service.insertReply(dto);
			if(result==0)
				state="false";
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		return model;
	}
	/////////////////////////////////////////////////////////////////////////////////////////		댓글 리스트 
	@RequestMapping(value="/club/index/apply/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			) throws Exception {
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		System.out.println("listReply로 넘어옴 ");
		Map<String, Object> map=new HashMap<String, Object>();
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
		
		ModelAndView mav=new ModelAndView("/club/dari/apply/listReply");
		
		// jsp로 넘길 데이터
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount); //
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	//////////////////////////////////// AJAX(JSON) - 댓글별 개수
	@RequestMapping(value="/club/index/apply/replyCount",  method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyCount(	@RequestParam(value="num") int num) throws Exception {

		String state="true";
		int count=0;
		System.out.println("replyCount로 넘어옴 ");
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
	
	//////////////////////////////////////////////////// 댓글 및 대댓글 삭제
			@RequestMapping(value="/club/index/apply/deleteReply", method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,	@RequestParam(value="replyNum") int replyNum,	@RequestParam(value="mode") String mode
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
					int result=service.deleteReply(map);

					if(result==0)
						state="false";
				}
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			//////////////////////////////////////////////////////////////////// 댓글별 답글 리스트
			@RequestMapping(value="/club/index/apply/listReplyAnswer")
			
			public ModelAndView listReplyAnswer(@RequestParam(value="answer") int answer
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				
				List<Reply> listReplyAnswer=service.listReplyAnswer(map);
				
				// 엔터를 <br>
				Iterator<Reply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {					
					Reply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/club/dari/apply/listReplyAnswer");

				// jsp로 넘길 데이터
				mav.addObject("listReplyAnswer", listReplyAnswer);
				
				return mav;
			}
			
			//////////////////////////////////////////////////////////////////// 댓글별 답글 개수
			@RequestMapping(value="/club/index/apply/replyCountAnswer",method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer( @RequestParam(value="answer") int answer) 
					throws Exception {
				
				int count=0;
				
				count=service.replyCountAnswer(answer);
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("count", count);
				return model;
			}
			/////////////////////////////////////////////////////////////////////////////////////// 	좋 아 요 
			//////////////////////////////////////////////////////////////// 댓글 좋아요 추가
			@RequestMapping(value="/apply/replyLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyLike(
					HttpSession session, Reply dto) throws Exception {
			
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				dto.setUserIdx(info.getUserIdx());
				
				int state=service.stateReplyLike(dto);
				System.out.println(state+"= state");
				
				if(state==0){	//좋아요가 처음이라면
				
					service.insertReplyLike(dto);
					
				}else if(state==1){ // 또 누른거라면 취소 
					dto.setUserIdx(info.getUserIdx());
					service.deleteReplyLike(dto);
				}
				
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			//////////////////////////////////////////////////////////////// 댓글 좋아요 개수
			@RequestMapping(value="/apply/countLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  countLike(
					@RequestParam(value="replyNum") int replyNum) throws Exception {
				
				int likeCount=0, disLikeCount=0;
				Map<String, Object> map=service.replyCountLike(replyNum);
				if(map!=null) {
					// resultType이 map인 경우 int는 BigDecimal로 넘어옴
					likeCount=((BigDecimal)map.get("LIKECOUNT")).intValue();
				}
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("likeCount", likeCount);
				return model;
			}
			
			
			@RequestMapping(value="/club/index/apply/delete")
			public ModelAndView delete(
					HttpSession session, @RequestParam(value="num") int num,	@RequestParam(value="page") String page	)
			throws Exception {
				
				SessionInfo info=(SessionInfo)session.getAttribute("member");
				if(info==null) {
					return new ModelAndView("redirect:/member/login");
				}
				
				// 해당 레코드 가져 오기
				Apply dto = service.readApply(num);
				
				if(dto==null) {
					return new ModelAndView("redirect:/club/index/apply/list?page="+page);
				}				
								
				String root = session.getServletContext().getRealPath("/");
				String path = root + File.separator + "uploads" + File.separator + "notice";		
		 	
				service.deleteApply(num, dto.getSaveFileName(), path);
				
				return new ModelAndView("redirect:/club/index/apply/list?page="+page);
			}
			
			
			@RequestMapping(value="/club/index/apply/applyList")
			public ModelAndView applyList( HttpSession session) {
				//   /club/index/apply/applyList
				ModelAndView mav = new ModelAndView(".four.club.dari.apply.applyList.봉다리 개인페이지");
				
				return mav;
			}
}
	
	