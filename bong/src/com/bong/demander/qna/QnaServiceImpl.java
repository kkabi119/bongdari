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
	public int insertQna(Qna qna) {
		int result=0;
		try {
			result=dao.insertInformation("deQna.insertQna", qna);
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

}
