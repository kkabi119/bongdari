package com.bong.club.apply;

import java.util.List;
import java.util.Map;

public interface ApplyService {
	public List<Apply> listApply(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	
	public Apply readApply(int num);
	public int updateHitCount(int num);
	public Apply preReadApply(Map<String, Object> map);
	public Apply nextReadApply(Map<String, Object> map);
	public int updateNotice(Apply dto, String path);
	public int deleteNotice(int num, String saveFilename, String path);
	
	public int deleteNoticeId(String userId, String root);
	
	public int insertReply(Reply dto);
	public List<Reply> listReply(Map<String, Object> map);
	public List<Reply> listReplyAnswer(int answer);
	public int replyDataCount(Map<String, Object> map);
	public int replyCountAnswer(int answer);
	public int deleteReply(Map<String, Object> map);
}
