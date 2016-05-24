package com.bong.club.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bong.club.ClubService;
import com.bong.common.FileManager;
import com.bong.common.MyUtil;

@Controller("post.myclubController")
public class MyclubController {
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private MyUtil util;
	
	@Autowired
	private FileManager fileManager;
	
}
