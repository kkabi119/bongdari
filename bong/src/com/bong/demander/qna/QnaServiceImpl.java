package com.bong.demander.qna;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("demander.qnaService")
public class QnaServiceImpl implements QnaService {

	@Autowired
	private bongDAO dao;
	
	@Override
	public int insertQna(Qna dto,String mode) {
		int result=0;
		try {
			int seq = dao.getIntValue("deQna.DeQnaSeq");
			dto.setSqnaIdx(seq);
			result=dao.insertInformation("deQna.insertQna", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int insertQnaReply(Qna dto,int sqnaIdx) {
		int result=0;
		try {
			dto.setAnswer(sqnaIdx);
			result=dao.insertInformation("deQna.insertQnaReply",dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}


	@Override
	public int dataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("deQna.dataCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<Qna> listQna(Map<String, Object> map) {
		List<Qna> list=null;
		try {
			list=dao.getListInformation("deQna.listQna", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public Qna readQna(int num) {
		Qna qna=null;
		try {
			qna=dao.getReadInformation("deQna.readQna",num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return qna;
	}

	@Override
	public int updateHitCount(int num) {
		int result=0;
		try {
			result=dao.updateInformation("deQna.updateHitCount", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	
	
	@Override
	public int updateQna(Qna qna) {
		int result=0;
		try {
			result=dao.updateInformation("deQna.updateQna", qna);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteQna(int num) {
		int result=0;
		try{
			result=dao.deleteInformation("deQna.deleteQna", num);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	/*@Override
	public Qna deQnaReply(Map<String, Object> map) {
		Qna dto=null;
		try {
			dto=dao.getReadInformation("deQna.deQnaReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}*/


	

}
