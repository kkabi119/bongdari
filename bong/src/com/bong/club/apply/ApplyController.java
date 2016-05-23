package com.bong.club.apply;

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


@Controller("club.applyController")
public class ApplyController {
	
	@Autowired
	private ApplyService service;
	
	@Autowired
	private MyUtil myUtil;
	
	
	/*개인동아리 봉사 신청게시판*/
	
	///////////////////////////////////////////// 리스트 ///////////////////////////////////////////////////
	@RequestMapping(value="club/index/apply/list")
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
        
        System.out.println(dataCount);
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
					
		if(dto==null)
			return new ModelAndView("redirect:.club.index.apply?page="+page);
		
		// 조회수 증가
		service.updateHitCount(dto.getVolunIdx());
				
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
			dto.setUserIdx(info.getUserIdx());
			int result=service.insertReply(dto);
			if(result==0)
				state="false";
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		return model;
	}
	
	@RequestMapping(value="/club/index/apply/listReply")
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
		System.out.println("replycount느느는="+dataCount);
		// jsp로 넘길 데이터
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	@RequestMapping(value="/club/index/apply/replyCount",  method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyCount(	@RequestParam(value="num") int num) throws Exception {
		// AJAX(JSON) - 댓글별 개수

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
	
	
}
	
	