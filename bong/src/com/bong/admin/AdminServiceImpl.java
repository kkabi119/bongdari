package com.bong.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("admin.AdminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private bongDAO  dao;
	
	@Override
	public List<Member> listMember(Map<String, Object> map) {
		List<Member> list=null;
		try{
			list=dao.getListInformation("admin.memberList", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}

	@Override
	public int memberCount(Map<String, Object> map) {
		int result=0;
		
		try{
			result=dao.getIntValue("admin.memberCount", map);			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}


	@Override
	public List<Club> listClub(Map<String, Object> map) {
		List<Club> list=null;
		try {
			list=dao.getListInformation("admin.listClub", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int clubCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("admin.clubCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
}
