package com.bong.demander;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.demanderjoin.Demanderjoin;
import com.bong.demanderjoin.DemanderjoinService;
import com.bong.member.SessionInfo;


@Controller("bong.demanderController")
public class DemanderController {
	
	@Autowired
	private DemanderjoinService service;
	@Autowired
	private MyUtil myUtil;
	@RequestMapping(value="/main/demander")
	public ModelAndView demanderMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.demander.main.수요처 전체 메인페이지");
		return mav;
	}

	@RequestMapping(value="/main/articleDemander")
	public ModelAndView articleDemander() throws Exception {
		ModelAndView mav = new ModelAndView(".layout.demander.article.수요처 간단프로필");

		return mav;
	}
	 
	@RequestMapping(value="/main/searchDemander")
	public ModelAndView searchDemander() throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.search.수요처 검색하기");

		return mav;
	}

	
	@RequestMapping(value="/demander/index/main")
	public ModelAndView demanderIndexMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.main.각 수요처 메인");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/calendar")
	public ModelAndView demanderCal() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.calendar.수요처달력");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/notice/list")
	public ModelAndView deNoticeList() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.notice.list.공지사항");
		return mav;
	}
	@RequestMapping(value="/demander/index/notice/create")
	public ModelAndView deNoticeCreate() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.notice.create.공지사항");
		return mav;
	}
	@RequestMapping(value="/demander/index/notice/article")
	public ModelAndView deNoticeArticle() throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.notice.article.공지사항");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/photo")
	public ModelAndView demanderPhoto() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.photo.포토게시판");
		return mav;
	}
	/*
	@RequestMapping(value="/demander/index/review/list")
	public ModelAndView demanderReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.review.list.후기게시판");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review/create")
	public ModelAndView demanderCreateReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.review.create.후기게시판");
		return mav;
	}
	@RequestMapping(value="/demander/index/review/article")
	public ModelAndView demanderArticleReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.review.article.후기게시판");
		return mav;
	}
	*/
	
	
	@RequestMapping(value="/demander/index/schedule")
	public ModelAndView demanderSchedule() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.schedule.일정등록게시판");
		return mav;
	}
	
	
	@RequestMapping(value="/demander/index/guest")
	public ModelAndView demanderGuest() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.guest.방명록");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/admin")
	public ModelAndView demanderadmin() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.admin.관리자페이지");
		
		return mav;
	}
	
	@RequestMapping(value="/demander/index/bookmark")
	public ModelAndView deadminbmark() throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.bookmark.관심등록");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/volList")
	public ModelAndView volList() throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.volList.나눔복지관");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/tab1")
	public ModelAndView tab1() throws Exception {
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/volList");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/tab2")
	public ModelAndView tab2() throws Exception {
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/tab1/showList")
	public ModelAndView showList() throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/appliedMembers");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/tab1/eval")
	public ModelAndView eval() throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/tab3", method=RequestMethod.GET)
	public ModelAndView tab3(
			HttpSession session
			) throws Exception {
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
		//세션에 있는 수요처 정보 가져오기
		SessionInfo info=(SessionInfo)session.getAttribute("member");

		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		Demanderjoin dto=service.readDemanderjoinInfo(Integer.toString(info.getUserIdx()));
		if(dto==null){
			session.invalidate();
			return new ModelAndView("redirect:/");
		}
	
		//수정폼
	ModelAndView mav = new ModelAndView("/demander/dari/admin/demanderUpdate");
	    mav.addObject("mode", "update");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin/tab3", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView demanderUpdateSubmit(
			HttpSession session
		   ,Demanderjoin dto){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
		
		SessionInfo info=(SessionInfo)session.getAttribute("demanderjoin");
		dto.setUserIdx(info.getUserIdx());
		service.updateDemander2(dto, pathname);
		
		ModelAndView mav=new ModelAndView("redirect:/demander/dari/admin/demanderUpdate");
	    
		return mav;
	}
}
