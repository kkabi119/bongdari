package com.bong.demander;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;


@Controller("bong.dereviewController")
public class dereviewController {
	@Autowired
	private DeReviewService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/demander/index/review/list")
	public ModelAndView demanderReview(
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
	        String urlList = cp+"/demander/dari/review/list";
	        String urlArticle = cp+"/demander/dari/review/article?page=" + current_page;
	        if(searchValue.length()!=0) {
	        	params = "searchKey=" +searchKey + 
	        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
	        }
	        
	        if(params.length()!=0) {
	            urlList = cp+"/demander/dari/review/list?" + params;
	            urlArticle = cp+"/demander/dari/review/article?page=" + current_page + "&"+ params;
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
	public ModelAndView deCreateRevForm(
			HttpSession session
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/main");
		}
		
		ModelAndView mav=new ModelAndView(".four.demander.dari.review.create.후기게시판");
		mav.addObject("mode", "created");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review/create",method=RequestMethod.POST)
	public ModelAndView deCreateRevSubmit(
			HttpSession session,
			DeReview dto
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"bbs";
		
		//dto.setUserId(info.getUserId());
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		
		service.insertDeReview(dto, path);
		
		
		
		return new ModelAndView("redirect:/demander/index/review/list");
		
	}
	@RequestMapping(value="/demander/index/review/article")
	public ModelAndView demanderArticleReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.review.article.후기게시판");
		return mav;
	}
	
	
	
	
}
