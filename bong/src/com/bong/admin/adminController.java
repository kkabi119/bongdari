package com.bong.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.adminController")
public class adminController {
	@RequestMapping(value="/admin")
	public ModelAndView adminMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4Layout");
		return mav;
	}
	
	@RequestMapping(value="/admin/member")
	public ModelAndView adminMember() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.main.member");
		return mav;
	}
	
	@RequestMapping(value="/admin/club")
	public ModelAndView adminClub() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.main.club");
		return mav;
	}
	
	@RequestMapping(value="/admin/demander")
	public ModelAndView adminDemander() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.main.demander");
		return mav;
	}
	
	@RequestMapping(value="/admin/approval")
	public ModelAndView adminApproval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.main.approval");
		return mav;
	}
	
	@RequestMapping(value="/admin/cal")
	public ModelAndView adminCal() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.calendar.calendar");
		return mav;
	}
	
	
}
