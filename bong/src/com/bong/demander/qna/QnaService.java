package com.bong.demander.qna;

import java.util.List;
import java.util.Map;

public interface QnaService {
	public int insertQna(Qna qna,String mode);
	public int insertQnaReply(Qna dto,int sqnaIdx);
	public int dataCount(Map<String, Object> map);
	public List<Qna> listQna(Map<String, Object> map);
	public int updateHitCount(int num);
	public int quserIdx(int groupNum);
	//public Qna deQnaReply(Map<String, Object> map);
	public Qna readQna(int num);
	public int updateQna(Qna qna);
	public int deleteQna(int num);
}
