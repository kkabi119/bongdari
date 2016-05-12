package com.bong.cal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.calController")
public class calController {
	// 달력 테스트용 임시 매핑
	@RequestMapping(value="/cal")
	public ModelAndView adminMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.calendar.calendar");
		return mav;
	}
	
	// 대화상자에 출력 할 일정 추가 폼
	@RequestMapping(value="/cal/inputForm")
	public String inputForm() throws Exception {
		return "admin/calendar/inputForm.jsp";
	}

	// 대화상자에 출력 할 상세 일정 폼
	@RequestMapping(value="/cal/articleForm")
	public String articleForm() throws Exception {
		return "admin/calendar/articleForm.jsp";
	}
	
	
	
}
