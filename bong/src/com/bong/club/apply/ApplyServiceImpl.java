package com.bong.club.apply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("clubApply.applyService")
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	private bongDAO dao;
		
	@Override
	public List<Apply> listApply(Map<String, Object> map) {
		
		List<Apply> list=null;
		
		try {
			
			list=dao.getListInformation("clubApply.listApply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result=dao.getIntValue("clubApply.dataCount", map);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int applyCount_club(Map<String, Object> map) {
		
		int result = 0;
		
		try {
			System.out.println("으아아아");
			result=dao.getIntValue("clubApply.applyCount_club", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int applyCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result=dao.getIntValue("clubApply.applyCount", map);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	

	@Override
	public Apply readApply(int num) {
		Apply dto=null;
		
		try {
			//게시물 가져오기
			dto=dao.getReadInformation("clubApply.readApply", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public int updateHitCount(int num) {
		int result=0;
		
		try{
			// 조회수 증가
			result=dao.updateInformation("clubApply.updateHitCount", num);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public Apply preReadNotice(Map<String, Object> map) {
		Apply dto = null;
		
		try{
			dto=dao.getReadInformation("clubApply.preReadNotice", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public Apply nextReadNotice(Map<String, Object> map) {
		Apply dto = null;
		
		try{
			dto=dao.getReadInformation("clubApply.nextReadNotice", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public int updateNotice(Apply dto, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNotice(int num, String saveFilename, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNoticeId(String userId, String root) {
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
	public List<Reply> listReplyAnswer(int answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int replyDataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyCountAnswer(int answer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	}

	