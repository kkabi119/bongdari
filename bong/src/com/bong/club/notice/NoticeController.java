package com.bong.club.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("club.noticeController")
public class NoticeController {
	
	@RequestMapping(value="/club/index/main")
	public ModelAndView myClubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.�� ���Ƹ� ����");
		return mav;
	}
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/index/notice/list")
	public ModelAndView clubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.list.�����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/article")
	public ModelAndView ReadClubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.article.�����ۺ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/created")
	public ModelAndView insertClubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.created.�����۾���");
		return mav;
	}
	/*���ε��Ƹ� �����Խ��� ��*/
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/index/free/list")
	public ModelAndView ListClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.list.�����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/create")
	public ModelAndView insertClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.create.�����۾���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/article")
	public ModelAndView readClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.�����ۺ���");
		return mav;
	}
	/*���ε��Ƹ� �����Խ��� ��*/
	
}
