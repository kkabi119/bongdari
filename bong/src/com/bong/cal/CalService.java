package com.bong.cal;

import java.util.List;
import java.util.Map;

public interface CalService {
	public List<Schedule> listVolun(Map<String, Object> map);
	public int VolunCount(Map<String, Object> map);
	
	
}
