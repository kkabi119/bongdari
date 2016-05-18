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
	
	//내정보
	@RequestMapping(value="/member/index/myPage", method=RequestMethod.GET)
	public ModelAndView mypageMain(
			 HttpSession session
			) throws Exception{
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
        
		Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
		
		
		ModelAndView mav= new ModelAndView(".layout.mypage.myInfo.내정보 보기");
		mav.addObject("dto", dto);
		return mav;
	}
	//정보 수정
/*@RequestMapping(value="/member/index/myPage", method=RequestMethod.GET)
	public ModelAndView mypageMain(
			HttpSession session
			
			) throws Exception {
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		//세션에 있는 멤버 정보 가져오기
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		//db에 있는 데이터 가져오기(userIdx를 통해서) mapper에 있는 쿼리 실행
		Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
		if(dto==null) {
			session.invalidate();
			return new ModelAndView("redirect:/");
		}

		service.updateMember2(dto,pathname);
		
		// 회원정보수정폼
	    ModelAndView mav=new ModelAndView(".layout.mypage.updateInfo.마이페이지 메인");
	    mav.addObject("dto", dto);
	    return mav;
	}
	@RequestMapping(value="/member/index/myPage", method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session
           ,Member dto){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		service.updateMember2(dto, pathname);
		
		ModelAndView mav=new ModelAndView("redirect:/");
		
		StringBuffer sb=new StringBuffer();
		sb.append(dto.getUserName()+"님의 회원정보가 변경되었습니다.<br>");
		
		mav.addObject("title", "수정");
		mav.addObject("message", sb.toString());
		return mav;
	}*/
}
