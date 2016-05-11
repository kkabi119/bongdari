package com.bong.demanderjoin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="demanderjoin.demanderjoinController")
public class DemanderjoinController {
   
	@Autowired
	private DemanderjoinService service;
    
	//�α��� �� �α׾ƿ�
	@RequestMapping(value="/demander/login", method=RequestMethod.GET)
	public ModelAndView demanderLoginForm() throws Exception{
		return new ModelAndView(".layout.member.login.�α���");
	}
	@RequestMapping(value="/demander/login", method=RequestMethod.POST)
	public ModelAndView demanderLoginOk(
			HttpSession session
		   ,@RequestParam("serviceId") String serviceId
		   ,@RequestParam("servicePwd") String servicePwd
			) throws Exception{
		
		Demanderjoin dto = service.readDemanderjoinLogin(serviceId);
		
		if(dto==null || (!dto.getServicePwd().equals(servicePwd))){
			ModelAndView mav = new ModelAndView("member/login");
			mav.addObject("message", "���̵� Ȥ�� ��й�ȣ�� ��ġ ���� �ʽ��ϴ�.");
			return mav;
		}
		
		
		//�α��� ������ ���ǿ� ����
		SessionInfo info = new SessionInfo();
		info.setServiceIdx(dto.getServiceIdx());
		info.setServiceId(dto.getServiceId());
		info.setServiceName(dto.getServiceName());
		session.setAttribute("demanderjoin", info);
		
		return new ModelAndView("redirect:/main");
	}
	
	@RequestMapping(value="/demanderjoin/demanderRegister", method=RequestMethod.GET)
    public ModelAndView demanderjoinForm(){
		ModelAndView mav=new ModelAndView(".layout.demanderjoin.demanderRegister.ȸ������");
		mav.addObject("mode", "created");
		return mav;
	}
	@RequestMapping(value="/demanderjoin/demanderRegister", method=RequestMethod.POST)
	public ModelAndView demanderjoinSubmit(Demanderjoin dto){
		
		int result=service.insertDemanderjoin(dto);
		
		ModelAndView mav = new ModelAndView(".layout.member.login.�α���");
		
		return mav;
	}
}
