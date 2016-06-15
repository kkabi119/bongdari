package com.bong.volunList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("main.volunList")
public class VolunListServiceImpl implements VolunListService {

	@Autowired
	private bongDAO dao;
	

	@Override
	public int dataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("volunTot.dataCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<VolunList> volunSearchList(Map<String, Object> map) {
		List<VolunList> list=null;
		try {
			list=dao.getListInformation("volunTot.volunList", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

}
