package com.bong.demanderjoin;

import java.io.File;
import java.util.HashMap;
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

import com.bong.member.Member;

@Controller(value="demanderjoin.demanderjoinController")
public class DemanderjoinController {
   
	@Autowired
	private DemanderjoinService service;
    
	//로그인 및 로그아웃
	/*@RequestMapping(value="/demander/login", method=RequestMethod.GET)
	public ModelAndView demanderLoginForm() throws Exception{
		return new ModelAndView(".layout.member.login.로그인");
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
			mav.addObject("message", "아이디 혹은 비밀번호가 일치 하지 않습니다.");
			return mav;
		}
		
		
		//로그인 정보를 세션에 저장
		SessionInfo info = new SessionInfo();
		info.setServiceIdx(dto.getServiceIdx());
		info.setServiceId(dto.getServiceId());
		info.setServiceName(dto.getServiceName());
		session.setAttribute("demanderjoin", info);
		
		return new ModelAndView("redirect:/");
	}
	@RequestMapping(value="/demanderjoin/logout")
	public String logout(HttpSession session) throws Exception{
		//로그인 정보 삭제
		session.removeAttribute("demanderjoin");
		session.invalidate();
		
		return "redirect:/";
	}*/
	//수요처 회원가입
	@RequestMapping(value="/demanderjoin/demanderRegister", method=RequestMethod.GET)
    public ModelAndView demanderjoinForm(){
		ModelAndView mav=new ModelAndView(".layout.demanderjoin.demanderRegister.회원가입");
		mav.addObject("mode", "created");
		return mav;
	}
	@RequestMapping(value="/demanderjoin/demanderRegister", method=RequestMethod.POST)
	public ModelAndView demanderjoinSubmit(
			HttpSession session,
			Demanderjoin dto){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
		
		service.insertDemanderjoin(dto,pathname);
		
		ModelAndView mav = new ModelAndView(".layout.member.login.로그인");
		
		return mav;
	}
	
    //아이디 중복 검사
	@RequestMapping(value="/demanderjoin/serviceIdCheck")
	@ResponseBody
	public Map<String, Object> serviceIdCheck(
			@RequestParam String userId
			) throws Exception{
		String passed="false";
		
		 Demanderjoin dto=service.readDemanderCheck(userId);
		if(dto==null){
			passed="true";			
		}
		
		Map<String, Object> model=new HashMap<String, Object>();
		model.put("passed", passed);
		return model;
	}
	@RequestMapping(value="/demanderjoin/zip", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView searchDemanderAddr(
			HttpServletRequest req
		   ,@RequestParam(value="zip", defaultValue="") String zip
			) throws Exception{

	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("streetname", zip);
		
		List<Demanderjoin> list=service.listDemanderAddr(map);

		ModelAndView mav= new ModelAndView("/demanderjoin/zip");
		mav.addObject("list", list);
		return mav;
	}
}
