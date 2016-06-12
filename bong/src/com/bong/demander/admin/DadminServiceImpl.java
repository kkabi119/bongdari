package com.bong.demander.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("demander.adminService")
public class DadminServiceImpl implements DadminService {

	@Autowired
	private bongDAO dao;
	
	
	@Override
	public Dadmin deMainProfile(Map<String, Object> map) {
		Dadmin dto=null;
		try {
			dto=dao.getReadInformation("dAdmin.deMainProfile", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	//봉사리스트의 데이터카운트
	@Override
	public int dataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("dAdmin.volunCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	//봉사 리스트 
	@Override
	public List<Dadmin> AdminVolunList(Map<String, Object> map) {
		List<Dadmin> list=null;
		try {
			list=dao.getListInformation("dAdmin.volunList", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

}
