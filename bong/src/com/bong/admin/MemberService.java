package com.bong.admin;

import java.util.List;
import java.util.Map;

public interface MemberService {
	public List<Member> listMember(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public Member readBoard(int num);
	
}
