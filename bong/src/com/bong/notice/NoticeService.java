package com.bong.notice;

import java.util.List;
import java.util.Map;

import com.bong.notice.Notice;
import com.bong.notice.Reply;

public interface NoticeService {
	public int insertNotice(Notice dto, String pathname);
	public List<Notice> listNotice(Map<String, Object> map);
	public List<Notice> listNoticeSmall(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	
	public Notice readNotice(Map<String, Object> map);
	public int updateHitCount(Map<String, Object> map);
	public Notice preReadNotice(Map<String, Object> map);
	public Notice nextReadNotice(Map<String, Object> map);
	public int updateNotice(Notice dto, String path);
	public int deleteNotice(Map<String, Object> map, String saveFilename, String path);
	
	public int deleteNoticeId(String userId, String root);
	
	public int insertReply(Reply dto);
	public List<Reply> listReply(Map<String, Object> map);
	public List<Reply> listReplyAnswer(Map<String, Object> map);
	public int replyDataCount(Map<String, Object> map);
	public int replyCountAnswer(Map<String, Object> map);
	public int deleteReply(Map<String, Object> map);
}
