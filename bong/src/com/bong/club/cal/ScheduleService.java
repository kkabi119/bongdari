package com.bong.club.cal;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
	public int insertSchedule(Schedule sch);
	public List<Schedule> listMonthSchedule(Map<String, Object> map);
	
	public Schedule readSchedule(int num);
	public int updateSchedule(Schedule sch);
	public int deleteSchedule(Map<String, Object> map);
}
