package com.bong.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("mypage.mypageController")
public class MypageController {
	
	@RequestMapping(value="/member/index/myPage")
	public ModelAndView mypageMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.mypage.main.마이페이지 메인");
		return mav;
	}
	
}
