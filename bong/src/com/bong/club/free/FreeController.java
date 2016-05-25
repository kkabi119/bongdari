package com.bong.club.free;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*개인동아리 자유게시판*/
	@RequestMapping(value="/club/{clubSeq}/free/list")
	public ModelAndView ListClubFree(
			HttpServletRequest req,
			@PathVariable int clubSeq,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		/*String cp=req.getContextPath();
		
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
        List<Free> list = service.listFree(map);
        
     // 리스트의 번호
        int listNum, n = 0;
        Iterator<Free> it=list.iterator();
        while(it.hasNext()) {
            Free data = it.next();
            listNum = dataCount - (start + n - 1);
            data.setListNum(listNum);
            n++;
        } String params = "";
        String urlList = cp+"/club/"+clubSeq+"/free/list";
        String urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page;
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlList = cp+"/club/"+clubSeq+"/free/list?" + params;
            urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page + "&"+ params;
        }
        
        ModelAndView mav = new ModelAndView(".four.club.dari.free.list.자유게시판");
		
        mav.addObject("list", list);
        mav.addObject("clubSeq",clubSeq);
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));*/
		ModelAndView mav = new ModelAndView(".four.club.dari.free.list.자유게시판");
		return mav;
	}
	
	/* 자유게시판 글쓰기 폼 */
	@RequestMapping(value="/club/{clubSeq}/free/created",method=RequestMethod.GET)
	public ModelAndView insertFreeForm(
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.created.자유글쓰기");
		mav.addObject("mode", "created");
		mav.addObject("clubSeq", clubSeq);
		return mav;
	}
	
	
	/* 자유게시판 글쓰기 폼 */
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
	
	@RequestMapping(value="/club/{clubSeq}/free/article")
	public ModelAndView readClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.자유글보기");
		return mav;
	}
	/*개인동아리 자유게시판 끝*/
}
