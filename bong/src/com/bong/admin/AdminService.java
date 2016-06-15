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
	
	public Demander demanderArticle(String serviceIdx);
	public Club clubArticle(int clubIdx);
	public Member memberArticle(int userIdx);
	
	public int createDemanderTable(String serviceIdx);
	
}
