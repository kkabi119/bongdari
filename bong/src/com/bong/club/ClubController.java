package com.bong.club;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.clubController")
public class ClubController {
	
	@RequestMapping(value="/main/club")
	public ModelAndView clubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.club.clubMain.���ٸ� ����");
		return mav;
	}
	
	
	
	
	@RequestMapping(value="/main/createClub")
	public ModelAndView clubCreate() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.club.open.���Ƹ� �����ϱ�");
		return mav;
	}
	
	@RequestMapping(value="/main/myClub")
	public ModelAndView Myclub() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.�� ���Ƹ� ����");
		return mav;
	}
	
	
	@RequestMapping(value="/main/searchClub")
	public ModelAndView clubSearch() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.club.search.���Ƹ� �˻��ϱ�");
		return mav;
	}
	
	@RequestMapping(value="/club/index/main")
	public ModelAndView myClubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.�� ���Ƹ� ����");
		return mav;
	}
	
	@RequestMapping(value="/club/index/vapply")
	public ModelAndView clubVapply() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.article.���ٸ� ����������");
		return mav;
	}
	
	@RequestMapping(value="/club/index/vapplyList")
	public ModelAndView clubVapplyList() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.list.���ٸ� ����������");
		return mav;
	}
	
	@RequestMapping(value="/club/index/japply")
	public ModelAndView clubJapply() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.japply.���Խ�û�ϱ�");
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
	
	@RequestMapping(value="/club/index/notice/write")
	public ModelAndView insertClubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.create.�����۾���");
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
	
	@RequestMapping(value="/club/index/review")
	public ModelAndView clubReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.review.����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/calendar")
	public ModelAndView clubCalendar() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.calendar.�޷�");
		return mav;
	}
	
	@RequestMapping(value="/club/index/admin")
	public ModelAndView clubAdmin() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.admin.������������");
		return mav;
	}
}
