package com.bong.demander;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;

@Service("demander.reviewService")
public class DeReviewServiceImpl implements DeReviewService {

	@Autowired
	private bongDAO dao;
	
	@Autowired
	private FileManager fileManager;
	@Override
	public int insertDeReview(DeReview dto, String path) {
		int result=0;
		try{
			if(dto.getUpload()!=null && ! dto.getUpload().isEmpty()) {
				String saveFilename=fileManager.doFileUpload(dto.getUpload(), path);
				dto.setSaveFilename(saveFilename);
				dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
				//result=dao.insertInformation("demander.insertDeReFile", dto);
			}
			result=dao.insertInformation("demander.insertDeReview", dto);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<DeReview> listDeReview(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeReview readDeReview(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateHitCount(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeReview preReadDeReview(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeReview nextReadDeReview(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateDeReview(DeReview dto, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDeReview(int num, String saveFilename, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDeReviewId(String userId, String root) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertDeReviewReply(DeReviewReply dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DeReviewReply> listDeReviewReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeReviewReply> listDeReviewReplyAnswer(int answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int DeReviewReplyDataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DeReviewReplyCountAnswer(int answer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDeReviewReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertDeReviewReplyLike(DeReviewReply dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> DeReviewReplyCountLike(int DeReviewReplyNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
