package com.bong.club.free;

import java.util.List;
import java.util.Map;

public interface FreeService {
	public int insertFree(Free dto, String pathname);
	public List<Free> listFree(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	
	public Free readFree(Map<String, Object> map);
	public int updateHitCount(Map<String, Object> map);
	public Free preReadFree(Map<String, Object> map);
	public Free nextReadFree(Map<String, Object> map);
	public int updateFree(Free dto, String path);
	public int deleteFree(Map<String, Object> map, String saveFilename, String path);
	
	public int deleteFreeId(String userId, String root);
	
	public int insertReply(Reply dto);
	public List<Reply> listReply(Map<String, Object> map);
	public List<Reply> listReplyAnswer(Map<String, Object> map);
	public int replyDataCount(Map<String, Object> map);
	public int replyCountAnswer(Map<String, Object> map);
	public int deleteReply(Map<String, Object> map);

}
