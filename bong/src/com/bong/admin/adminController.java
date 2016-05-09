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
		
		ModelAndView mav = new ModelAndView(".four.admin.member.멤버관리페이지");
		return mav;
	}
	
	@RequestMapping(value="/admin/club")
	public ModelAndView adminClub() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.admin.club.동아리관리페이지");
		return mav;
	}
	
	@RequestMapping(value="/admin/demander")
	public ModelAndView adminDemander() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.admin.demander.수요처 관리페이지");
		return mav;
	}
	
	@RequestMapping(value="/admin/approval")
	public ModelAndView adminApproval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.admin.approval.수요처_동아리 가입승인페이지");
		return mav;
	}
}
