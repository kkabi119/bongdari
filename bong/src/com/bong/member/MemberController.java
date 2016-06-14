package com.bong.member;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;

@Controller("member.memberController")
public class MemberController {
	
	@Autowired
	private MemberService service;
	@Autowired
	private MyUtil myUtil;
	/*//로그인 및 로그아웃
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public ModelAndView loginForm() throws Exception {
		return new ModelAndView(".layout.member.login.로그인");
	}
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public ModelAndView loginOk(
			HttpSession session
		   ,@RequestParam("userId") String userId
		   ,@RequestParam("userPwd") String userPwd
			) throws Exception{
		
		Member dto = service.readMemberLogin(userId);
		
		if(dto==null || (!dto.getUserPwd().equals(userPwd))){
			ModelAndView mav= new ModelAndView("member/login");
			
			mav.addObject("message", "아이디 또는 비밀번호가 일치 하지 않습니다");
			return mav;		
		}
		
		//로그인 날짜 변경
		service.updateLastLogin(dto.getUserId());
		
		//로그인 정보를 세션에 저장
		SessionInfo info = new SessionInfo();
		info.setUserIdx(dto.getUserIdx());
		info.setUserId(dto.getUserId());
		info.setUserName(dto.getUserName());
		session.setAttribute("member", info);
		
		return new ModelAndView("redirect:/main");
	}
	@RequestMapping(value="/member/logout")
	public String logout(HttpSession session) throws Exception{
		//로그인 정보 삭제	
		session.removeAttribute("member");
		session.invalidate();
		
		return "redirect:/main";
	}*/
	// 회원가입 및 회원정보 수정 -----------------------
	@RequestMapping(value="/member/register", method=RequestMethod.GET)
	public ModelAndView memberForm() {
		ModelAndView mav=new ModelAndView(".layout.member.register.회원가입");
		mav.addObject("mode", "created");
		return mav;
	}
		
	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public ModelAndView memberSubmit(
			HttpSession session,
			Member dto) throws Exception {
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		
		int result=service.insertMember(dto,pathname);
		
		ModelAndView mav=new ModelAndView();
		if(result==1) {
			StringBuffer sb=new StringBuffer();
			sb.append(dto.getUserName()+ "님의 회원 가입이 정상적으로 처리되었습니다.<br>");
			sb.append("메인화면으로 이동하여 로그인 하시기 바랍니다.<br>");
			
			mav.setViewName(".layout.member.complete.회원가입성공");
			mav.addObject("message", sb.toString());
		} else {
            mav.setViewName("/member/register");
		}
			
		return mav;
	}	
     //아이디 중복 검사
	@RequestMapping(value="/member/userIdCheck")
	@ResponseBody
	public Map<String, Object> userIdCheck(
			@RequestParam String userId
			) throws Exception{
		
		String passed="false";
	     Member dto=service.readMemberCheck(userId);
	     
		if(dto==null){
			passed="true";
		}
		
		Map<String, Object> model=new HashMap<String, Object>();
		model.put("passed", passed);
		
		return model;
	}
	@RequestMapping(value="/member/zip", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView searchAddr(
			HttpServletRequest req
		   ,@RequestParam(value="zip", defaultValue="") String zip
			) throws Exception{

	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("streetname", zip);
		
		List<Member> list =service.listAddr(map);

		ModelAndView mav= new ModelAndView("/member/zip");
		mav.addObject("list", list);
		return mav;
	}
}
