package com.bong.club;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.clubController2")
public class ClubController2 {
	
	@RequestMapping(value="/club/index/main")
	public ModelAndView myClubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.내 동아리 메인");
		return mav;
	}
	
	/*개인동아리 공지게시판*/
	@RequestMapping(value="/club/index/notice/list")
	public ModelAndView clubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.list.공지게시판");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/article")
	public ModelAndView ReadClubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.article.공지글보기");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/write")
	public ModelAndView insertClubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.create.공지글쓰기");
		return mav;
	}
	/*개인동아리 공지게시판 끝*/
	
	/*개인동아리 자유게시판*/
	@RequestMapping(value="/club/index/free/list")
	public ModelAndView ListClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.list.자유게시판");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/create")
	public ModelAndView insertClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.create.자유글쓰기");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/article")
	public ModelAndView readClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.자유글보기");
		return mav;
	}
	/*개인동아리 자유게시판 끝*/
	
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
