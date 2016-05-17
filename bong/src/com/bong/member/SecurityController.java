package com.bong.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("member.securityController")
public class SecurityController {
	@RequestMapping(value="/member/login")
	public ModelAndView login(HttpServletRequest req, HttpSession session) {
		boolean loginError = req.getParameter("login_error") != null;
		
		String errorMsg = "none";
		if (loginError) {
			AuthenticationException ex = 
					(AuthenticationException) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			errorMsg = ex != null ? ex.getMessage() : "none";
		}
		
		String msg="";
		if(loginError) {
			if("Bad credentials".equals(errorMsg)) {
				msg="���̵� �Ǵ� �н����带 �߸� �Է� �ϼ̽��ϴ�.";
			} else {
				msg="�α��� ���� : "+errorMsg; 
			}
		}
		
		req.setAttribute("message", msg);
		
		return new ModelAndView(".layout.member.login.�α���");
	}
	
	// ���� �����������̼�(Authorization:����)�� ���� ���
	@RequestMapping(value="/member/noAuthorized")
	public ModelAndView noAuthorized() {
		
		return new ModelAndView(".layout.member.noAuthorized.huhu");
	}
}
