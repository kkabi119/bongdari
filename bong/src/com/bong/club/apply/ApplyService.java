package com.bong.club.apply;

import java.util.List;
import java.util.Map;

import com.bong.club.apply.Reply;

public interface ApplyService {
	public List<Apply> listApply(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	
	public Apply readApply(int num);
	public int updateHitCount(int num);
	public Apply preReadApply(Map<String, Object> map);
	public Apply nextReadApply(Map<String, Object> map);
	public int updateApply(Apply dto, String path);
	public int deleteApply(int num, String saveFilename, String path);
	
	public int deleteApplyId(String userId, String root);
	
	public int insertReply(Reply dto) ;
	public List<Reply> listReply(Map<String, Object> map);
	public List<Reply> listReplyAnswer(Map<String, Object> map);
	public int replyDataCount(Map<String, Object> map);
	public int replyCountAnswer(int answer);
	public int deleteReply(Map<String, Object> map);
	int insertReplyLike(Reply dto);
	Map<String, Object> replyCountLike(int replyNum);
}
