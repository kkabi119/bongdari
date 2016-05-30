package com.bong.demander.qna;

import java.io.File;
import java.net.URLDecoder;
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
import com.bong.demander.review.DeReview;
import com.bong.member.SessionInfo;

@Controller("bong.deQnaController")
public class QnaController {
	@Autowired
	private QnaService service;
	@Autowired
	private MyUtil myUtil;
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/demander/index/qna/list")
	public ModelAndView deQnaList(
			HttpServletRequest req,
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		
		int numPerPage = 10;  // 한 화면에 보여주는 게시물 수
		int total_page = 0;
		int dataCount = 0;
   	    
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}

        // 전체 페이지 수
        Map<String, Object> map = new HashMap<String, Object>();
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

        List<Qna> list = service.listQna(map);
        Iterator<Qna> it=list.iterator();
        while(it.hasNext()) {
        	Qna dto=it.next();
        	dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        }
        
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.list.QnA 게시판");
        mav.addObject("list", list);
        mav.addObject("pageNo", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("paging", myUtil.paging(current_page, total_page));
		return mav;
	}
	
	@RequestMapping(value="/demander/index/qna/create",method=RequestMethod.GET)
	public ModelAndView deRevCreateForm(
			) throws Exception {
		ModelAndView mav=new ModelAndView(".four.demander.dari.qna.create.qna게시판");
		mav.addObject("mode", "created");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/qna/create",method=RequestMethod.POST)
	public ModelAndView deRevCreateSubmit(
			HttpSession session,
			Qna dto
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		
		service.insertQna(dto);
		return new ModelAndView("redirect:/demander/index/qna/list");
		
	}
	@RequestMapping(value="/demander/index/qna/article")
	public ModelAndView deQnaArticle() throws Exception {
		
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.QnA 게시판");
		return mav;
	}

}
