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
		
		ModelAndView mav = new ModelAndView(".four.admin.member.�������������");
		return mav;
	}
	
	@RequestMapping(value="/admin/club")
	public ModelAndView adminClub() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.admin.club.���Ƹ�����������");
		return mav;
	}
	
	@RequestMapping(value="/admin/demander")
	public ModelAndView adminDemander() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.admin.demander.����ó ����������");
		return mav;
	}
	
	@RequestMapping(value="/admin/approval")
	public ModelAndView adminApproval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.admin.approval.����ó_���Ƹ� ���Խ���������");
		return mav;
	}
}
