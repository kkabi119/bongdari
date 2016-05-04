package com.bong.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("member.memberController")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//�α��� �� �α׾ƿ�
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public ModelAndView loginForm() throws Exception {
		return new ModelAndView("member/login");
	}
	
	// ȸ������ �� ȸ������ ���� -----------------------
	@RequestMapping(value="/member/register", method=RequestMethod.GET)
	public ModelAndView memberForm() {
		ModelAndView mav=new ModelAndView("member/register");
		mav.addObject("mode", "created");
		return mav;
	}
		
	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public ModelAndView memberSubmit(Member dto) {
			
		int result=service.insertMember(dto);
			
		ModelAndView mav=new ModelAndView("member/login");
			
		if(result==1) {
			StringBuffer sb=new StringBuffer();
			sb.append(dto.getUserName()+ "���� ȸ�� ������ ���������� ó���Ǿ����ϴ�.<br>");
			sb.append("����ȭ������ �̵��Ͽ� �α��� �Ͻñ� �ٶ��ϴ�.<br>");
				
			mav.setViewName("/member/login");
			mav.addObject("message", sb.toString());
			mav.addObject("title", "ȸ�� ����");
		} else {
			mav.setViewName("member/register");
			mav.addObject("mode", "created");
			mav.addObject("message", "���̵� �ߺ����� ȸ�������� �����߽��ϴ�.");
		}
		return mav;
	}	

}
