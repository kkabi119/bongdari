package com.bong.admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
	public List<Member> listMember(Map<String, Object> map);
	public int memberCount(Map<String, Object> map);
	
	public List<Club> listClub(Map<String, Object> map);
	public int clubCount(Map<String, Object> map);

	public List<Demander> listDemander(Map<String, Object> map);
	public int demanderCount(Map<String, Object> map);
	
}
