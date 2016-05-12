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
	
	@RequestMapping(value="/main/club/article")
	public ModelAndView clubArticle() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.article.���ٸ� ����������");
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
		
		ModelAndView mav = new ModelAndView(".four.club.dari.vapply.�����û �Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/japply")
	public ModelAndView clubJapply() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.japply.���Խ�û�ϱ�");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/list")
	public ModelAndView clubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.list.�����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/article")
	public ModelAndView clubNoticeArticle() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.article.�����ۺ���");
		return mav;
	}
	
	
	@RequestMapping(value="/club/index/free")
	public ModelAndView clubFreeBBS() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.�����Խ���");
		return mav;
	}
	
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
