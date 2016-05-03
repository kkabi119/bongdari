package com.bong.demander;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.demanderController")
public class DemanderController {
	
	@RequestMapping(value="/main/demander")
	public ModelAndView demanderMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.demanderMain.수요처 전체 메인페이지");
		return mav;
	}
	
	@RequestMapping(value="/main/searchDemander")
	public ModelAndView searchDemander() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.search.수요처 검색하기");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/main")
	public ModelAndView demanderIndexMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.indexMain.각 수요처 메인");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/calendar")
	public ModelAndView demanderCal() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.calendar.수요처달력");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/qna")
	public ModelAndView demanderQna() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.QnA 게시판");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/photo")
	public ModelAndView demanderPhoto() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.photo.포토게시판");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review")
	public ModelAndView demanderReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.review.후기게시판");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/schedule")
	public ModelAndView demanderSchedule() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.schedule.일정등록게시판");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/approval")
	public ModelAndView demanderApproval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.approval.요청승인페이지");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/eval")
	public ModelAndView demanderEval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.eval.달력");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/guest")
	public ModelAndView demanderGuest() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.guest.방명록");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin")
	public ModelAndView demanderadmin() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.관리자페이지");
		return mav;
	}
}
