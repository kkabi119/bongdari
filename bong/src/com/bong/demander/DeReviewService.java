package com.bong.demander;

import java.util.List;
import java.util.Map;

public interface DeReviewService {
	public int insertDeReview(DeReview dto, String path);
	public int insertDeReFile(DeReview dto, String path);
	public List<DeReview> listDeReview(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public DeReview readDeReview(int num);
	public int updateHitCount(int num);
	public DeReview preReadDeReview(Map<String, Object> map);
	public DeReview nextReadDeReview(Map<String, Object> map);
	public int updateDeReview(DeReview dto, String path);
	public int deleteDeReview(int num, String saveFilename, String path);
	
	public int deleteDeReviewId(String userId, String root);
	
	public int insertDeReviewReply(DeReviewReply dto);
	public List<DeReviewReply> listDeReviewReply(Map<String, Object> map);
	public List<DeReviewReply> listDeReviewReplyAnswer(int answer);
	public int DeReviewReplyDataCount(Map<String, Object> map);
	public int DeReviewReplyCountAnswer(int answer);
	public int deleteDeReviewReply(Map<String, Object> map);

	public int insertDeReviewReplyLike(DeReviewReply dto);
	public Map<String, Object> DeReviewReplyCountLike(int DeReviewReplyNum);
}
