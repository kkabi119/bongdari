package com.bong.club;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.clubController")
public class ClubController {
	
	@RequestMapping(value="/main/club")
	public ModelAndView clubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.club.clubMain.봉다리 메인");
		return mav;
	}
	
	@RequestMapping(value="/main/club/article")
	public ModelAndView clubArticle() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.article.봉다리 개인페이지");
		return mav;
	}
	
	
	@RequestMapping(value="/main/createClub")
	public ModelAndView clubCreate() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.club.open.동아리 개설하기");
		return mav;
	}
	
	@RequestMapping(value="/main/myClub")
	public ModelAndView Myclub() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.각 동아리 메인");
		return mav;
	}
	
	
	@RequestMapping(value="/main/searchClub")
	public ModelAndView clubSearch() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.club.search.동아리 검색하기");
		return mav;
	}
	
	@RequestMapping(value="/club/index/main")
	public ModelAndView myClubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.내 동아리 메인");
		return mav;
	}
	
	@RequestMapping(value="/club/index/vapply")
	public ModelAndView clubVapply() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.vapply.봉사신청 게시판");
		return mav;
	}
	
	@RequestMapping(value="/club/index/japply")
	public ModelAndView clubJapply() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.japply.가입신청하기");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/list")
	public ModelAndView clubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.list.공지게시판");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/article")
	public ModelAndView clubNoticeArticle() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.article.공지글보기");
		return mav;
	}
	
	
	@RequestMapping(value="/club/index/free")
	public ModelAndView clubFreeBBS() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.자유게시판");
		return mav;
	}
	
	@RequestMapping(value="/club/index/review")
	public ModelAndView clubReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.review.리뷰게시판");
		return mav;
	}
	
	@RequestMapping(value="/club/index/calendar")
	public ModelAndView clubCalendar() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.calendar.달력");
		return mav;
	}
	
	@RequestMapping(value="/club/index/admin")
	public ModelAndView clubAdmin() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.admin.관리자페이지");
		return mav;
	}
}
