package com.bong.demander.qna;

import java.util.List;
import java.util.Map;

public interface QnaService {
	public int insertFaq(Qna faq);
	public int dataCount(Map<String, Object> map);
	public List<Qna> listFaq(Map<String, Object> map);
	
	public Qna readFaq(int num);
	public int updateFaq(Qna faq);
	public int deleteFaq(int num);
}
