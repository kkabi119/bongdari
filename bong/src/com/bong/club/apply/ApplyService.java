package com.bong.club.apply;

import java.util.List;
import java.util.Map;

import com.bong.club.apply.Reply;
import com.bong.club.notice.Notice;
import com.bong.demander.review.DeReview;
import com.bong.member.Member;

public interface ApplyService {
	public List<Apply> listApply(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	
	public Apply readApply(int num); //v
	public int updateHitCount(int num); //v
	public Apply preReadApply(Map<String, Object> map);//v
	public Apply nextReadApply(Map<String, Object> map);//v
	
	public int updateApply(Apply dto, String path);
	public int deleteApply(int num, String saveFilename, String path);//v
	
	public int deleteApplyId(String userId, String root);
	
	public int insertReply(Reply dto) ;//v
	public List<Reply> listReply(Map<String, Object> map);//v
	public List<Reply> listReplyAnswer(Map<String, Object> map);//v
	public int replyDataCount(Map<String, Object> map);//v
	public int replyCountAnswer(int answer);//v
	public int deleteReply(Map<String, Object> map);//v
	int insertReplyLike(Reply dto);//v
	Map<String, Object> replyCountLike(int replyNum);//v
	public int stateReplyLike(Reply dto);//v
	int deleteReplyLike(Reply dto);//v
	public int updateHitCount_club(int clubApplyIdx);//v
	
	public List<Member> readApplyList(int clubApplyIdx);
	public List<Member> readApplyList_date(int clubApplyIdx);
	
	
	
}
