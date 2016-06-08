package com.bong.demander.guest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("demander.guestService")
public class GuestServiceImpl implements GuestService {
	@Autowired
	private bongDAO dao;
	
	@Override
	public int insertGuest(Guest dto) {
		int result=0;
		try {
			result=dao.insertInformation("deGuest.insertGuest", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int dataCount(int demander_seq) {
		int result=0;
		try {
			result=dao.getIntValue("deGuest.dataCount", demander_seq);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public List<Guest> listGuest(Map<String, Object> map) {
		List<Guest> list=null;
		try {
			list=dao.getListInformation("deGuest.listGuest", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int deleteGuest(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.deleteInformation("deGuest.deleteGuest", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

}
