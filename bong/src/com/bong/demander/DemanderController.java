package com.bong.demander;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.demanderjoin.Demanderjoin;
import com.bong.demanderjoin.DemanderjoinService;
import com.bong.member.SessionInfo;


@Controller("bong.demanderController")
public class DemanderController {
	
	@Autowired
	private DemanderjoinService service;
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="/main/demander")
	public ModelAndView demanderMain() throws Exception {
	ModelAndView mav = new ModelAndView(".layout.demander.main.����ó ��ü ����������");
		return mav;
	}

	/*@RequestMapping(value="/main/articleDemander")
	public ModelAndView articleDemander() throws Exception {
		ModelAndView mav = new ModelAndView(".layout.demander.article.����ó ����������");

		return mav;
	}*/
	 
	@RequestMapping(value="/main/searchDemander")
	public ModelAndView searchDemander() throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.search.����ó �˻��ϱ�");

		return mav;
	}


	
	@RequestMapping(value="/demander/{demander_seq}/calendar")
	public ModelAndView demanderCal(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.calendar.����ó�޷�");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/photo")
	public ModelAndView demanderPhoto(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.photo.����Խ���");
		return mav;
	}
	
	
	
	@RequestMapping(value="/demander/{demander_seq}/schedule")
	public ModelAndView demanderSchedule(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.schedule.������ϰԽ���");
		return mav;
	}
	
	
	@RequestMapping(value="/demander/{demander_seq}/guest")
	public ModelAndView demanderGuest(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.guest.����");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/admin")
	public ModelAndView demanderadmin(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.admin.������������");
		
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/bookmark")
	public ModelAndView deadminbmark(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.bookmark.���ɵ��");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/volList")
	public ModelAndView volList(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.volList.����������");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1")
	public ModelAndView tab1(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/volList");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab2")
	public ModelAndView tab2(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1/showList")
	public ModelAndView showList(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/appliedMembers");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1/eval")
	public ModelAndView eval(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab3", method=RequestMethod.GET)
	public ModelAndView tab3(
			HttpSession session,
			@PathVariable int demander_seq
			) throws Exception {
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
		//���ǿ� �ִ� ����ó ���� ��������
		SessionInfo info=(SessionInfo)session.getAttribute("member");

		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		Demanderjoin dto=service.readDemanderjoinInfo(Integer.toString(info.getUserIdx()));
		if(dto==null){
			session.invalidate();
			return new ModelAndView("redirect:/");
		}

		//������
	ModelAndView mav = new ModelAndView("/demander/dari/admin/demanderUpdate");
	    mav.addObject("mode", "update");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab3", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView demanderUpdateSubmit(
			HttpSession session
		   ,Demanderjoin dto,
		   @PathVariable int demander_seq
		   ){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		service.updateDemander2(dto, pathname);
		
		ModelAndView mav=new ModelAndView("redirect:/demander/"+demander_seq+"/admin/admin");
	    
		return mav;
	}
}
