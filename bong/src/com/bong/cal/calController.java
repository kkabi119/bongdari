package com.bong.cal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.calController")
public class calController {
	// �޷� �׽�Ʈ�� �ӽ� ����
	@RequestMapping(value="/cal")
	public ModelAndView adminMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.calendar.calendar");
		return mav;
	}
	
	// ��ȭ���ڿ� ��� �� ���� �߰� ��
	@RequestMapping(value="/cal/inputForm")
	public String inputForm() throws Exception {
		return "admin/calendar/inputForm.jsp";
	}

	// ��ȭ���ڿ� ��� �� �� ���� ��
	@RequestMapping(value="/cal/articleForm")
	public String articleForm() throws Exception {
		return "admin/calendar/articleForm.jsp";
	}
	
	
	
}
