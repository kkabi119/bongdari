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

	//����ó ��ü�˻�����Ʈ�� ������ī��Ʈ
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

	//����ó�� ��ü�˻� ����Ʈ 
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
