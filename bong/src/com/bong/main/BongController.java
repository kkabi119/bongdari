package com.bong.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BongController {
	
	@RequestMapping(value="/bong/main")
	public ModelAndView maintest() throws Exception {
		
		ModelAndView mav = new ModelAndView("bong/index");
		return mav;
	}
}
