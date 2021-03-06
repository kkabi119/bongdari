package com.bong.club.review;

import java.util.List;
import java.util.Map;

import com.bong.club.notice.Notice;

public interface ClReviewService {
	public int insertClReview(ClReview dto, String path);
	public List<ClReview> listClReview(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public ClReview readClReview(Map<String, Object> map);
	public int updateHitCount(Map<String, Object> map);
	public ClReview preReadClReview(Map<String, Object> map);
	public ClReview nextReadClReview(Map<String, Object> map);
	public int updateClReview(ClReview dto, String path);
	public int deleteClReview(ClReview dto, String saveFilename, String path);
	

	public int insertFile(ClReview dto, Map<String, Object> map);
	public List<ClReview> listFile(Map<String, Object> map);
	public ClReview readFile(Map<String, Object> map);
	public int deleteFile(Map<String, Object> map);
	
	public List<ClReview> listReviewSmall(Map<String, Object> map);	
	
	public int stateLike(ClReview dto);
	public int insertLike(ClReview dto);
	public int deleteLike(ClReview dto);
	public Map<String, Object> countLike(ClReview dto);
	public int updateClRevLikeCount(ClReview dto);
	
	public int deleteClReviewId(String userId, String root);
	
	/// 댓글관련
	public int insertReply(ClReviewReply dto) ;//v
	public List<ClReviewReply> listReply(Map<String, Object> map);//v
	public List<ClReviewReply> listReplyAnswer(Map<String, Object> map);//v
	public int replyDataCount(Map<String, Object> map);//v
	public int replyCountAnswer(Map<String, Object> map);//v
	public int deleteReply(Map<String, Object> map);//v
	
	/// 좋아요 관련
	int insertReplyLike(ClReviewReply dto);//v
	Map<String, Object> replyCountLike(Map<String, Object> map);//v
	public int stateReplyLike(ClReviewReply dto);//v
	int deleteReplyLike(ClReviewReply dto);//v
	
}
