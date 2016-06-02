package com.bong.club.review;

import java.util.List;
import java.util.Map;

public interface ClReviewService {
	public int insertClReview(ClReview dto, String path);
	public List<ClReview> listClReview(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public ClReview readClReview(Map<String, Object> map);
	public int updateHitCount(int num);
	public ClReview preReadClReview(Map<String, Object> map);
	public ClReview nextReadClReview(Map<String, Object> map);
	public int updateClReview(ClReview dto, String path);
	public int deleteClReview(int num, String saveFilename, String path);
	

	public int insertFile(ClReview dto, Map<String, Object> map);
	public List<ClReview> listFile(Map<String, Object> map);
	public ClReview readFile(Map<String, Object> map);
	public int deleteFile(Map<String, Object> map);
	
	
	public int stateClRevLike(ClReview dto);
	public int insertClReviewLike(ClReview dto);
	public int deleteClReviewLike(ClReview dto);
	public Map<String, Object> ClRevCountLike(int num);
	public int updateClRevLikeCount(ClReview dto);
	
	public int deleteClReviewId(String userId, String root);
	
	public int insertClReviewReply(ClReviewReply dto);
	public List<ClReviewReply> listClReviewReply(Map<String, Object> map);
	public List<ClReviewReply> listClReviewReplyAnswer(int answer);
	public int ClReviewReplyDataCount(Map<String, Object> map);
	public int ClReviewReplyCountAnswer(int answer);
	public int deleteClReviewReply(Map<String, Object> map);
	
	public int stateClRevReplyLike(ClReviewReply dto);
	public int insertClReviewReplyLike(ClReviewReply dto);
	public int deleteClRevReplyLike(ClReviewReply dto);
	public Map<String, Object> ClReviewReplyCountLike(int ClReviewReplyNum);
}
