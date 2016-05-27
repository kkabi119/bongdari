package com.bong.cal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("bong.calController")
public class calController {
	
	// 대화상자에 출력 할 일정 추가 폼
	@RequestMapping(value="/cal/inputForm")
	public String inputForm() throws Exception {
		return "admin/calendar/inputForm";
	}

	// 대화상자에 출력 할 상세 일정 폼
	@RequestMapping(value="/cal/articleForm")
	public String articleForm() throws Exception {
		return "admin/calendar/articleForm";
	}
	
	// 대화상자에 출력 할 상세 일정 폼
		@RequestMapping(value="/cal/eachDay")
		public String eachDay() throws Exception {
			return "admin/calendar/eachDay";
		}
	
	
}
