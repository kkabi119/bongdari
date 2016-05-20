package com.bong.club;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.clubController1")
public class ClubController1 {
	
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
	
	@RequestMapping(value="/main/searchClub")
	public ModelAndView clubSearch() throws Exception {
		
		ModelAndView mav = new ModelAndView(".layout.club.search.���Ƹ� �˻��ϱ�");
		return mav;
	}
	
	@RequestMapping(value="/club/index/vapply")
	public ModelAndView clubVapply() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.article.���ٸ� ����������");
		return mav;
	}
	
	/*@RequestMapping(value="/club/index/vapplyList")
	public ModelAndView clubVapplyList() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.list.���ٸ� ����������");
		return mav;
	}*/
	
	@RequestMapping(value="/club/index/japply")
	public ModelAndView clubJapply() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.japply.���Խ�û�ϱ�");
		return mav;
	}
	
	@RequestMapping(value="/club/index/review")
	public ModelAndView clubReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.review.����Խ���");
		return mav;
	}
	

}
