package com.bong.club.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("club.noticeController")
public class NoticeController {
	
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
	
	@RequestMapping(value="/club/index/notice/created")
	public ModelAndView insertClubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.created.공지글쓰기");
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
	
}
