package com.bong.cal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("bong.calController")
public class calController {
	
	// ��ȭ���ڿ� ��� �� ���� �߰� ��
	@RequestMapping(value="/cal/inputForm")
	public String inputForm() throws Exception {
		return "admin/calendar/inputForm";
	}

	// ��ȭ���ڿ� ��� �� �� ���� ��
	@RequestMapping(value="/cal/articleForm")
	public String articleForm() throws Exception {
		return "admin/calendar/articleForm";
	}
	
	// ��ȭ���ڿ� ��� �� �� ���� ��
		@RequestMapping(value="/cal/eachDay")
		public String eachDay() throws Exception {
			return "admin/calendar/eachDay";
		}
	
	
}
