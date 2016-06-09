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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int quserIdx(Qna dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Qna readQna(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateQna(Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteQna(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
