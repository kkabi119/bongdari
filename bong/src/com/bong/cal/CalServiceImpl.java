package com.bong.cal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("cal.CalService")
public class CalServiceImpl implements CalService{
	@Autowired
	private bongDAO  dao;
	
	@Override
	public List<Schedule> listVolun(Map<String, Object> map) {
		List<Schedule> list=null;
		try{
			list=dao.getListInformation("cal.volunList", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}

	@Override
	public int VolunCount(Map<String, Object> map) {
		int result=0;
		
		try{
			result=dao.getIntValue("admin.volunCount", map);			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}


	
}
