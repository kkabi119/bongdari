package com.bong.cal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.club.ClubService;
import com.bong.club.ClubTheme;


@Controller("bong.calController")
public class calController {
	
	@Autowired
	private CalService calService;
	
	@Autowired
	private ClubService cService; // 테마 불러오려고 잠깐 쓰는 것 
	
	// 대화상자에 출력 할 일정 추가 폼
	@RequestMapping(value="/cal/inputForm")
	public ModelAndView inputForm() throws Exception {
		
		
		List<ClubTheme> listGroup=cService.listClubThemeGroup();
		ModelAndView mav = new ModelAndView("admin/calendar/inputForm");
		mav.addObject("listGroup", listGroup); // 테마 불러오려고 쓴거다
		return mav;
	}
	
	// 일정 입력 (봉사일정 올리기)
	@RequestMapping(value="/cal/insertSchedule", method=RequestMethod.POST)
	@ResponseBody
	public String insertSchedule(Schedule dto) throws Exception {
		dto.setServiceIdx(7); // 수요처 번호 임시로 7 넣어줌.
		calService.insertVolunbbs(dto);
		
		String[] eachDayArray = dto.getEachDayArray().split(",");
		String[] eachDayValueArray = dto.getEachDayValueArray().split(",");
		int a = eachDayValueArray.length;
		for(int i=0;i<(eachDayValueArray.length-2);i++){
			dto.setEachDay(eachDayArray[i]);
			dto.setEachDayValue(eachDayValueArray[i]);
			calService.insertVolunbbsEach(dto);
		}
		
		return "";
	}
	
	@RequestMapping(value="/cal/eachDayInsert")
	@ResponseBody
	public List<String> insertEachDay(
			@RequestParam(value="startDay") String startDay,
			@RequestParam(value="endDay") String endDay,
			@RequestParam(value="checkDay") String checkDay
			) throws Exception {
		List<String> list = eachDayCount(startDay, endDay, checkDay);
		
		return list;
	}

	// 대화상자에 출력 할 상세 일정 폼
	@RequestMapping(value="/cal/articleForm")
	public String articleForm() throws Exception {
		return "admin/calendar/articleForm";
	}
	
	// 날짜별로 선택하기 눌렀을 때
	@RequestMapping(value="/cal/eachDay")
	@ResponseBody
	public ModelAndView eachDay(
			@RequestParam(value="startDay") String startDay,
			@RequestParam(value="endDay") String endDay,
			@RequestParam(value="checkDay") String checkDay
			) throws Exception {
		
		ModelAndView mav=new ModelAndView("admin/calendar/eachDay");
		List<String> list = new ArrayList<String>();
		list = eachDayCount(startDay, endDay, checkDay);
		list.remove(list.size()-1);
        mav.addObject("list", list);
		if(list.size()==0)
			mav.addObject("flag", "error");
		else
			mav.addObject("flag", "OK");
		return mav;
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
		while(it.hasNext()){
			Schedule sch = it.next();
			Schedule dto = new Schedule();
			dto.setStart(sch.getStartDay());			
			dto.setEnd(sch.getEndDay());
			dto.setId(sch.getId());
			if(sch.getStartTime()!=null && sch.getStartTime().length()!=0)
		    	dto.setStart(sch.getStartDay());
			// "T" + sch.getStartTime()); // 시간은 일단 표시하지 않기로 함 나중에 추가할 경우 여기를 수정할 것
	    	else
	    		dto.setStart(sch.getStartDay());
	    	
	    	if(sch.getEndTime()!=null && sch.getEndTime().length()!=0)
	    		dto.setEnd(sch.getEndDay());
				// "T" + sch.getEndTime()); // 시간은 일단 표시하지 않기로 함 나중에 추가할 경우 여기를 수정할 것
	    	else
	    		dto.setEnd(sch.getEndDay());
				dto.setTitle(sch.getTitle());
			
			listJson.add(dto);
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("list", listJson);
		return model;	
	}
	
	// 날짜별 선택하기에서 적용하기를 눌렀을 때
	@RequestMapping(value="/cal/eachDayOk")
	@ResponseBody
	public List<String> eachDayOk(
			@RequestParam(value="start") String startDay,
			@RequestParam(value="end") String endDay,
			@RequestParam(value="checkDay") String checkDay
			) throws Exception {
		
		List<String> list = new ArrayList<String>();
		
		list = eachDayCount(startDay, endDay, checkDay);	
        
		return list;	
	}
	
	// 사이 날짜 구하는 함수를 따로 뺐다
		@RequestMapping(value="/cal/eachDayCount")
		@ResponseBody
		public List<String> eachDayCount(String startDay, String endDay, String checkDay){
			
			String[] weekDayArray = checkDay.split(", ");
			
			DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			// 사이 날짜 구하기 참고 : http://bugnote.tistory.com/26
			int startYear = Integer.parseInt(startDay.substring(0,4));
	        int startMonth= Integer.parseInt(startDay.substring(5,7));
	        int startDate = Integer.parseInt(startDay.substring(8,10));
	        int endYear = Integer.parseInt(endDay.substring(0,4));
	        int endMonth= Integer.parseInt(endDay.substring(5,7));
	        int endDate = Integer.parseInt(endDay.substring(8,10));
	        Calendar calStart = Calendar.getInstance();
	        Calendar calEnd = Calendar.getInstance();
	        // Calendar의 Month는 0부터 시작하므로 -1 해준다.
	        // Calendar의 기본 날짜를 startDt로 셋팅해준다.
	        calStart.set(startYear, startMonth -1, startDate, 0, 0, 0);
	        calEnd.set(endYear, endMonth -1, endDate, 0, 0, 1);
	        
	        // list에 각각의 날짜를 넣어준다.
	        List<String> list = new ArrayList<String>();
	        if(calStart.getTime().before(calEnd.getTime())) // 시작 날짜가 끝 날짜보다 빠를 경우 실행한다.
	        {
		        while(true) {
	        			// 요일 검색해서 있으면 추가해주고 빠져나온다.
	        			for(int i=0;i<weekDayArray.length;i++){
	        				if(calStart.get(Calendar.DAY_OF_WEEK)==Integer.parseInt(weekDayArray[i])){
	        				list.add(sdFormat.format(calStart.getTime()));
	        				break;
	        				}
	        			}
			             
			            // Calendar의 날짜를 하루씩 증가한다.
			            calStart.add(Calendar.DATE, 1);
		             
			            // 현재 날짜가 종료일자보다 크면 종료  
			            if(calStart.getTime().after(calEnd.getTime())){
			            	break;
			            }
		        	}
	        }
	        list.add(checkDay);
	        
	        return list;
		}
	
}
