package com.bong.demander.qna;

import java.util.List;
import java.util.Map;

import com.bong.demander.review.DeReview;

public interface QnaService {
	public int insertQna(Qna qna);
	public int dataCount(Map<String, Object> map);
	public List<Qna> listQna(Map<String, Object> map);
	public int updateHitCount(int num);

	public Qna readQna(int num);
	public int updateQna(Qna qna);
	public int deleteQna(int num);
}
