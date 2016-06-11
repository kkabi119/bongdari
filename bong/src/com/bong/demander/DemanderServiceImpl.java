package com.bong.demander;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;
import com.bong.demander.review.DeReview;

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

	//수요처 전체검색리스트의 데이터카운트
	@Override
	public int dataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("deMain.dataCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	//수요처의 전체검색 리스트 
	@Override
	public List<Demander> deSearchList(Map<String, Object> map) {
		List<Demander> list=null;
		try {
			list=dao.getListInformation("deMain.deSearchList", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

}
