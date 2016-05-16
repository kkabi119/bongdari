package com.bong.club.notice;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bong.member.SessionInfo;

@Controller("club.noticeController")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/club/index/main")
	public ModelAndView myClubMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.main.�� ���Ƹ� ����");
		return mav;
	}
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/index/notice/list")
	public ModelAndView clubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.list.�����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/article")
	public ModelAndView ReadClubNotice() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.article.�����ۺ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/created",method=RequestMethod.GET)
	public ModelAndView createNoticeForm() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.notice.created.�����۾���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/notice/created",method=RequestMethod.POST)
	public String insertClubNotice(
			HttpSession session,
			Notice dto
			) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			return "redirect:/member/login";
		}
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"notice";
		
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		service.insertNotice(dto, pathname);
		return "redirect:/club/dari/notice/list";
	}
	/*���ε��Ƹ� �����Խ��� ��*/
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/index/free/list")
	public ModelAndView ListClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.list.�����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/create")
	public ModelAndView insertClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.create.�����۾���");
		return mav;
	}
	
	@RequestMapping(value="/club/index/free/article")
	public ModelAndView readClubFree() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.�����ۺ���");
		return mav;
	}
	/*���ε��Ƹ� �����Խ��� ��*/
	
}
