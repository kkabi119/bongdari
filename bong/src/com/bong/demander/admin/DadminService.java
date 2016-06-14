package com.bong.demander.admin;

import java.util.List;
import java.util.Map;

public interface DadminService {
	public Dadmin deMainProfile(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public List<Dadmin> AdminVolunList(Map<String, Object> map);
	public List<Dadmin> AdminVolunListSmall(Map<String, Object> map);
	public List<Dadmin> searchTable();	
	public Dadmin AdminClubVolun(Map<String, Object> map);
	public int clubApplyCount(Map<String, Object> map);
	public List<Dadmin> clubMemberList(Map<String, Object> map);
	public Dadmin clubInfoView(int clubIdx);
	public Dadmin memberInfoView(int clubIdx);
}
