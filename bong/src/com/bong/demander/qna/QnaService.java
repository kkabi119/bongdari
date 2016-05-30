package com.bong.demander.qna;

import java.util.List;
import java.util.Map;

public interface QnaService {
	public int insertQna(Qna qna);
	public int dataCount(Map<String, Object> map);
	public List<Qna> listQna(Map<String, Object> map);
	
	public Qna readQna(int num);
	public int updateQna(Qna qna);
	public int deleteQna(int num);
}
