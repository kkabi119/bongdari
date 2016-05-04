package com.bong.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("member.memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private bongDAO dao;

	@Override
	public Member readMember1(int userIdx) {
		Member dto=null;
		try {
			dto=dao.getReadInformation("member.readMember1", userIdx);
		
		} catch (Exception e) {
		  System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public Member readMemberLogin(String userId) {
		Member dto=null;
		try {
			dto=dao.getReadInformation("member.readMemberLogin", userId);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	@Override
	public int insertMember(Member dto) {
		int result = 0;
		try {
			int seq=dao.getIntValue("member.memberSeq");
			dto.setUserIdx(seq);
			
			//회원정보 저장
			dao.insertInformation("member.insertMember", seq);
			dao.insertInformation("member.insertMemberInfo", dto);
			
			result = 1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int updateMember2(Member dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLastLogin(String userId) {
		int result=0;
		try {
			result=dao.updateInformation("member.updateLastLogin", userId);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteMember2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> listMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertAuthority(Member dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAuthority(Member dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member readAuthority(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> listAuthority(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAuthority(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
