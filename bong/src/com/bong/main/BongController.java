package com.bong.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.mainController")
public class BongController {
	
	@RequestMapping(value="/main")
	public ModelAndView maintest() throws Exception {
		
		ModelAndView mav = new ModelAndView(".mainLayout");
		return mav;
	}
}
