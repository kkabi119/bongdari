package com.bong.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("bbs.boardService")
public class MemberServiceImpl implements MemberService{
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
	public int dataCount(Map<String, Object> map) {
		int result=0;
		
		try{
			result=dao.getIntValue("admin.dataCount", map);			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public Member readBoard(int num) {
		Member dto=null;
		
		try{
			dto=dao.getReadInformation("bbs.readBoard", num);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	
}
