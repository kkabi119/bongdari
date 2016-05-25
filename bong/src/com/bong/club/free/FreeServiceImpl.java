package com.bong.club.free;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;

@Service("club.freeService")
public class FreeServiceImpl implements FreeService {
	
	@Autowired
	private bongDAO dao;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public int insertFree(Free dto, String pathname) {
		int result=0;
		try {
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()){
				//업로드한 파일이 존재하는 경우
				String saveFilename=fileManager.doFileUpload(dto.getUpload(), pathname);
				dto.setSaveFilename(saveFilename);
				dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
			}
			int seq=dao.getIntValue("clubfree.clubFreeSeq");
			dto.setClubFreeIdx(seq);
			dao.insertInformation("clubfree.insertFree", dto);
			dao.insertInformation("clubfree.insertFreeP", dto);
			
			result=1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public List<Free> listFree(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Free readFree(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateHitCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Free preReadFree(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Free nextReadFree(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateFree(Free dto, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFree(Map<String, Object> map, String saveFilename, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFreeId(String userId, String root) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReply(Reply dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reply> listReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> listReplyAnswer(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int replyDataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyCountAnswer(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
