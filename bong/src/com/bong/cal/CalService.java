package com.bong.cal;

import java.util.List;
import java.util.Map;

import com.bong.club.free.Reply;

public interface CalService {
	public List<Schedule> listVolun(Map<String, Object> map);
	public int VolunCount(Map<String, Object> map);
	
	public int insertVolunbbs(Schedule dto);
	public int insertVolunbbsEach(Schedule dto);
	
	public int takeVolun(Map<String, Object> map);
	
	public Schedule articleForm(Map<String, Object> map);
	
	
}
