package com.bong.demander.review;

import java.util.List;
import java.util.Map;

public interface DeReviewService {
	public int insertDeReview(DeReview dto, String path);
	public List<DeReview> listDeReview(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public DeReview readDeReview(Map<String, Object> map);
	public int updateHitCount(Map<String, Object> map);
	public DeReview preReadDeReview(Map<String, Object> map);
	public DeReview nextReadDeReview(Map<String, Object> map);
	public int updateDeReview(DeReview dto, String path);
	public int deleteDeReview(DeReview dto, String saveFilename, String path);
	

	public int insertFile(DeReview dto, Map<String, Object> map);
	public List<DeReview> listFile(Map<String, Object> map);
	public DeReview readFile(Map<String, Object> map);
	public int deleteFile(Map<String, Object> map);
	
	
	public int stateDeRevLike(DeReview dto);
	public int insertDeReviewLike(DeReview dto);
	public int deleteDeReviewLike(DeReview dto);
	public Map<String, Object> deRevCountLike(DeReview dto);
	public int updateDeRevLikeCount(DeReview dto);
	
	public int deleteDeReviewId(String userId, String root);
	
	public int insertDeReviewReply(DeReviewReply dto);
	public List<DeReviewReply> listDeReviewReply(Map<String, Object> map);
	public List<DeReviewReply> listDeReviewReplyAnswer(Map<String, Object> map);
	public int DeReviewReplyDataCount(Map<String, Object> map);
	public int DeReviewReplyCountAnswer(Map<String, Object> map);
	public int deleteDeReviewReply(Map<String, Object> map);
	
	public int stateDeRevReplyLike(DeReviewReply dto);
	public int insertDeReviewReplyLike(DeReviewReply dto);
	public int deleteDeRevReplyLike(DeReviewReply dto);
	public Map<String, Object> DeReviewReplyCountLike(Map<String, Object> map);
}
