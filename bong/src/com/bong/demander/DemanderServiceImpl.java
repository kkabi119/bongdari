package com.bong.demander;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("demander.mainService")
public class DemanderServiceImpl implements DemanderService {

	@Autowired
	private bongDAO dao;
	
	@Override
	public Demander deMainProfile(Map<String, Object> map) {
		Demander dto=null;
		try {
			dto=dao.getReadInformation("deMain.deMainProfile", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

}
