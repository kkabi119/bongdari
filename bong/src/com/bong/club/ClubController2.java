package com.bong.club;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.clubController2")
public class ClubController2 {
		
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
