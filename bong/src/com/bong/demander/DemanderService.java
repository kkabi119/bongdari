package com.bong.demander;

import java.util.List;
import java.util.Map;

public interface DemanderService {
	public Demander deMainProfile(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public List<Demander> deSearchList(Map<String, Object> map);
	
}
