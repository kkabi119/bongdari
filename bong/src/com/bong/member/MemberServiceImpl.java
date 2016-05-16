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
		    
			if(dto!=null){
				if(dto.getUserTel()!=null){
					String [] s=dto.getUserTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
			if(dto!=null){
				if(dto.getUserEmail()!=null){
					String [] s=dto.getUserEmail().split("@");
					dto.setEmail1(s[0]);
					dto.setEmail2(s[1]);
				}
			}
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
		    
			if(dto!=null){
				if(dto.getUserTel()!=null){
					String [] s=dto.getUserTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
			if(dto!=null){
				if(dto.getUserEmail()!=null){
					String [] s=dto.getUserEmail().split("@");
					dto.setEmail1(s[0]);
					dto.setEmail2(s[1]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	@Override
	public int insertMember(Member dto) throws Exception {
		int result = 0;
		try {
			if(dto.getTel1() != null && dto.getTel1().length()!=0 &&
					dto.getTel2() != null && dto.getTel2().length()!=0 &&
					dto.getTel3() != null && dto.getTel3().length()!=0)
				dto.setUserTel(dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3());
			System.out.println(dto.getUserTel());
			if(dto.getEmail1() != null && dto.getEmail1().length()!=0 &&
					dto.getEmail2() != null && dto.getEmail2().length()!=0 )
				dto.setUserEmail(dto.getEmail1() + "@" + dto.getEmail2());
			int seq=dao.getIntValue("member.memberSeq");
			dto.setUserIdx(seq);
			
			
			//회원정보 저장
			dao.insertInformation("member.insertMember", seq);
			dao.insertInformation("member.insertMemberInfo", dto);
			
			result = 1;
		} catch (Exception e) {
			System.out.println(e.toString());
			throw e;
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
