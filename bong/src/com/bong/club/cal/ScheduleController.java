package com.bong.club.cal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.member.SessionInfo;

@Controller("clubsch.scheduleController")
public class ScheduleController {
	@Autowired
	private ScheduleService service;
	
	@RequestMapping(value="/club/{clubSeq}/calendar/main")
	public ModelAndView sch(
			HttpSession session
			,@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		ModelAndView mav=new ModelAndView(".four.club.dari.cal.sch.우리동아리달력");
		mav.addObject("subMenu","8");
		return mav;
	}

	// 대화상자에 출력 할 일정 추가 폼
	@RequestMapping(value="/club/{clubSeq}/calendar/inputForm")
	public String inputForm(
			@PathVariable int clubSeq
			) throws Exception {
		return "club/dari/cal/inputForm";
	}

	// 대화상자에 출력 할 상세 일정 폼
	@RequestMapping(value="/club/{clubSeq}/calendar/articleForm")
	public String articleForm(
			@PathVariable int clubSeq
			) throws Exception {
		return "club/dari/cal/articleForm";
	}
	
	@RequestMapping(value="/club/{clubSeq}/calendar/created", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> created(
			HttpSession session,
			Schedule sch,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			Map<String, Object> model = new HashMap<>(); 
			model.put("isLogin", "false");
			return model;
		}
		
		sch.setUserIdx(info.getUserIdx());
		
		service.insertSchedule(sch);
	
		Map<String, Object> model = new HashMap<>(); 
		model.put("isLogin", "true");
		model.put("state", "true");
		model.put("clubSeq", clubSeq);
		return model;		
	}
	
	@RequestMapping(value="/club/{clubSeq}/calendar/month")
	@ResponseBody
	public Map<String, Object> month(
			HttpSession session,
			@RequestParam(value="start") String start,
			@RequestParam(value="end") String end,
			@RequestParam(value="group", defaultValue="all") String group,
			@PathVariable int clubSeq
			) throws Exception {

		if(group.equals("red"))
			group="#00aeef";
		else if(group.equals("black"))
			group="#f7b10d";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			Map<String, Object> model = new HashMap<>(); 
			model.put("isLogin", "false");
			return model;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("group", group);
		map.put("start", start);
		map.put("end", end);
		map.put("userIdx", info.getUserIdx());
		map.put("clubSeq", clubSeq);
		
		List<Schedule> list=service.listMonthSchedule(map);
		
	 	List<ScheduleJSON> listJSON=new ArrayList<>();
	    Iterator<Schedule> it=list.iterator();
		while(it.hasNext()) {
			Schedule sch=it.next();
			// if(sch.getContent()!=null)
			//   sch.setContent(sch.getContent().replaceAll("\n", "<br>"));
			
			ScheduleJSON dto=new ScheduleJSON();
	    	dto.setId(sch.getNum());
	    	dto.setTitle(sch.getTitle());
	    	dto.setUserName(sch.getUserName());
	    	dto.setColor(sch.getColor());
	    	if(sch.getAllDay().equals("true"))
	    	    dto.setAllDay(true);
	    	else
	    		dto.setAllDay(false);
	    	
	    	if(sch.getStartTime()!=null && sch.getStartTime().length()!=0)
		    	dto.setStart(sch.getStartDay()+" " + sch.getStartTime());
	    	else
	    		dto.setStart(sch.getStartDay());
	    	
	    	if(sch.getEndTime()!=null && sch.getEndTime().length()!=0)
	    		dto.setEnd(sch.getEndDay()+" " + sch.getEndTime());
	    	else
	    		dto.setEnd(sch.getEndDay());
	    	dto.setContent(sch.getContent());
	    	dto.setCreated(sch.getCreated());
	    	listJSON.add(dto);
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("isLogin", "true");
		model.put("list", listJSON);
		return model;
	}
	
	@RequestMapping(value="/club/{clubSeq}/calendar/delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		Map<String, Object> model = new HashMap<>(); 
		if(info==null) {
			model.put("isLogin", "false");
			return model;
		}
		
		String state="false";
		
		model.put("clubSeq", clubSeq);
		model.put("num", num);
		int result=service.deleteSchedule(model);
		if(result==1)
			state="true";

		model.put("isLogin", "true");
		model.put("state", state);
		return model;
	}
	
	@RequestMapping(value="/club/{clubSeq}/calendar/update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  update(
			HttpSession session,
			Schedule sch,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			Map<String, Object> model = new HashMap<>(); 
			model.put("isLogin", "false");
			return model;
		}
		
		String state="false";
		int result=service.updateSchedule(sch);
		if(result==1)
			state="true";
		
		Map<String, Object> model = new HashMap<>(); 
		model.put("isLogin", "true");
		model.put("state", state);
		return model;
	}
}
