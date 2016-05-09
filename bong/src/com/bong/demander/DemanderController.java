package com.bong.demander;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bong.demanderController")
public class DemanderController {
	
	@RequestMapping(value="/main/demander")
	public ModelAndView demanderMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.main.����ó ��ü ����������");
		return mav;
	}
	
	@RequestMapping(value="/main/searchDemander")
	public ModelAndView searchDemander() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.search.����ó �˻��ϱ�");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/main")
	public ModelAndView demanderIndexMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.main.�� ����ó ����");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/calendar")
	public ModelAndView demanderCal() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.calendar.����ó�޷�");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/qna")
	public ModelAndView demanderQna() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.QnA �Խ���");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/photo")
	public ModelAndView demanderPhoto() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.photo.����Խ���");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review")
	public ModelAndView demanderReview() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.review.�ı�Խ���");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/schedule")
	public ModelAndView demanderSchedule() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.schedule.������ϰԽ���");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/approval")
	public ModelAndView demanderApproval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.approval.��û����������");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/eval")
	public ModelAndView demanderEval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.eval.��");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/guest")
	public ModelAndView demanderGuest() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.guest.�����");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/admin")
	public ModelAndView demanderadmin() throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.������������");
		return mav;
	}
}