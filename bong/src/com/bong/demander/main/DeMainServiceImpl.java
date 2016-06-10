package com.bong.demander.main;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("demander.demainService")
public class DeMainServiceImpl implements DeMainService{

	@Autowired
	private bongDAO dao;
	
	@Override
	public DeMain deMainProfile(Map<String, Object> map) {
		DeMain dto=null;
		try {
			dto=dao.getReadInformation("deMain.deMainProfile",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

}
