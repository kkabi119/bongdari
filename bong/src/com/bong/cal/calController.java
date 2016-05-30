package com.bong.cal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("bong.calController")
public class calController {
	
	@Autowired
	private CalService calService;
	
	// 대화상자에 출력 할 일정 추가 폼
	@RequestMapping(value="/cal/inputForm")
	public String inputForm() throws Exception {
		return "admin/calendar/inputForm";
	}

	// 대화상자에 출력 할 상세 일정 폼
	@RequestMapping(value="/cal/articleForm")
	public String articleForm() throws Exception {
		return "admin/calendar/articleForm";
	}
	
	// 대화상자에 출력 할 상세 일정 폼
	@RequestMapping(value="/cal/eachDay")
	public String eachDay() throws Exception {
		return "admin/calendar/eachDay";
	}
	
	@RequestMapping(value="/cal/list")
	@ResponseBody
	public Map<String, Object> calList(
			@RequestParam(value="start") String startDay,
			@RequestParam(value="end") String endDay
			) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", startDay);
		map.put("end", endDay);
		List<Schedule> listJson = new ArrayList<>();
		List<Schedule> list=calService.listVolun(map);
		Iterator<Schedule> it=list.iterator();
		/*List<Schedule> list=calService.listVolun(map);*/
		while(it.hasNext()){
			Schedule sch = it.next();
			Schedule dto = new Schedule();
			dto.setStart(sch.getStartDay());			
			dto.setEnd(sch.getEndDay());
			if(sch.getStartTime()!=null && sch.getStartTime().length()!=0)
		    	dto.setStart(sch.getStartDay()+" " + sch.getStartTime());
	    	else
	    		dto.setStart(sch.getStartDay());
	    	
	    	if(sch.getEndTime()!=null && sch.getEndTime().length()!=0)
	    		dto.setEnd(sch.getEndDay()+" " + sch.getEndTime());
	    	else
	    		dto.setEnd(sch.getEndDay());
			dto.setTitle(sch.getTitle());
			listJson.add(dto);
		}
		
		
		Map<String, Object> model = new HashMap<>();
		model.put("list", listJson);
		return model;	
	}
	
	
}
