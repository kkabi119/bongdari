package com.bong.member;

import java.util.List;
import java.util.Map;

public interface MemberService {
	
	public Member readMember1(int memberIdx);
	public Member readMember2(String userId);
	
	public int insertMember(Member dto);
	
	public int updateMember2(Member dto);
	public int updateLastLogin(String userId);
	
	public int deleteMember2(Map<String, Object> map);
	
	public int dataCount(Map<String, Object> map);
	public List<Member> listMember(Map<String, Object> map);
	
	public int insertAuthority(Member dto);
	public int updateAuthority(Member dto);
	public Member readAuthority(int num);
	public List<Member> listAuthority(String userId);
	public int deleteAuthority(Map<String, Object> map);

	
}
