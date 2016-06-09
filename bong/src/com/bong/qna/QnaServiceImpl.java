package com.bong.qna;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;
@Service("qna.qnaService")
public class QnaServiceImpl implements QnaService{
    
	@Autowired
	private bongDAO dao;
	
	@Override
	public int insertQna(Qna dto, String mode) {
	    int result = 0;
	    
	    try {
			int seq = dao.getIntValue("qna.qnaSeq");
			dto.setQnaIdx(seq);
			result=dao.insertInformation("qna.insertQna", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int insertQnaReply(Qna dto, int qnaIdx) {
		int result = 0;
		try {
			dto.setAnswer(qnaIdx);
			result=dao.insertInformation("qna.insertQnaReply", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		try {
			result = dao.getIntValue("qna.dataCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<Qna> listQna(Map<String, Object> map) {
		List<Qna> list = null;
		try {
			list=dao.getListInformation("qna.listQna", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int updateHitCount(Map<String, Object> map) {
		int result = 0;
		try {
			result = dao.updateInformation("qna.updateHitCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int quserIdx(Qna dto) {
		int result = 0;
		try {
			result = dao.getIntValue("qna.quserIdx", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Qna readQna(Map<String, Object> map) {
		Qna qna = null;
		try {
			qna = dao.getReadInformation("qna.readQna", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return qna;
	}

	@Override
	public int updateQna(Qna qna) {
		int result = 0;
		try {
			result = dao.updateInformation("qna.updateQna", qna);
		} catch (Exception e) {
          System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteQna(Map<String, Object> map) {
		int result = 0;
		try {
			result = dao.deleteInformation("qna.deleteQna", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

}
