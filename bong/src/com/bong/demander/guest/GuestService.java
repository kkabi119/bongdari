package com.bong.demander.guest;

import java.util.List;
import java.util.Map;

public interface GuestService {
	public int insertGuest(Guest dto);
	public int dataCount(int demander_seq);
	public List<Guest> listGuest(Map<String, Object> map);
	public int deleteGuest(Map<String, Object> map);
}
