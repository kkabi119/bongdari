package com.bong.club.cal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("clubsch.scheduleService")
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	bongDAO dao;
	
	@Override
	public int insertSchedule(Schedule sch) {
		int result=0;
		
		try {
			result=dao.insertInformation("clubsch.insertSchedule", sch);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public List<Schedule> listMonthSchedule(Map<String, Object> map) {
		List<Schedule> list=null;
			
		try {
			list=dao.getListInformation("clubsch.listMonthSchedule", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public Schedule readSchedule(int num) {
		Schedule sch=null;
		
		try{
			// 게시물 가져오기
			sch=dao.getReadInformation("clubsch.readSchedule", num);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return sch;
	}

	@Override
	public int updateSchedule(Schedule sch) {
		int result=0;

		try{
			result=dao.updateInformation("clubsch.updateSchedule", sch);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteSchedule(int num) {
		int result=0;

		try{
			result=dao.deleteInformation("clubsch.deleteSchedule", num);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
}
