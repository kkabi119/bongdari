package com.bong.volunList;

import java.util.List;
import java.util.Map;

public interface VolunListService {
	public int dataCount(Map<String, Object> map);
	public List<VolunList> volunSearchList(Map<String, Object> map);
	
}
