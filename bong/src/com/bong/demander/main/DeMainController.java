package com.bong.demander.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("demander.demainController")
public class DeMainController {

	@Autowired
	private DeMainService service;
	
	
	@RequestMapping(value="/demander/{demander_seq}/main")
	public ModelAndView demanderMain(
			@PathVariable int demander_seq
			) throws Exception {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("demander_seq", demander_seq);
		
		DeMain dto=service.deMainProfile(map);
		
		System.out.println("***********"+dto.getServiceIntro());
		ModelAndView mav = new ModelAndView(".four.demander.dari.main.각 수요처 메인");
		mav.addObject("dto", dto);
		return mav;
	}
}
