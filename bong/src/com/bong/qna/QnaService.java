package com.bong.qna;

import java.util.List;
import java.util.Map;

public interface QnaService {
  public int insertQna(Qna qna, String mode);
  public int insertQnaReply(Qna dto, int qnaIdx);
  public int dataCount(Map<String, Object> map);
  public List<Qna> listQna(Map<String, Object> map);
  public int updateHitCount(Map<String, Object> map);
  public int quserIdx(Qna dto);
  public Qna readQna(Map<String, Object> map);
  public int updateQna(Qna qna);
  public int deleteQna(Map<String, Object> map);
}
