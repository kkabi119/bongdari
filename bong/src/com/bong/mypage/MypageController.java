package com.bong.mypage;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bong.member.Member;
import com.bong.member.MemberService;
import com.bong.member.SessionInfo;

@Controller("mypage.mypageController")
public class MypageController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/member/index/myPage", method=RequestMethod.GET)
	public ModelAndView mypageMain(
			HttpSession session
			
			) throws Exception {
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		Member dto=service.readMemberLogin(info.getUserId());
		if(dto==null) {
			session.invalidate();
			return new ModelAndView("redirect:/");
		}
		int result=service.updateMember2(dto,pathname);
		
		// ȸ������������
				ModelAndView mav=new ModelAndView(".layout.mypage.updateInfo.���������� ����");
				mav.addObject("dto", dto);
				return mav;
	}
	@RequestMapping(value="/member/update", method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session
           ,Member dto){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/main");
		}
		
		service.updateMember2(dto, pathname);
		
		ModelAndView mav=new ModelAndView(".member.complete");
		
		StringBuffer sb=new StringBuffer();
		sb.append(dto.getUserName()+"���� ȸ�������� ����Ǿ����ϴ�.<br>");
		
		mav.addObject("title", "����");
		mav.addObject("message", sb.toString());
		return mav;
	}
}
