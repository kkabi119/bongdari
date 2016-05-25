package com.bong.club.apply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.club.apply.Reply;
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
	public Apply readApply(int num) {
		Apply dto=null;
		
		try {
			//게시물 가져오기
			System.out.println("후후후후");
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
	public Apply preReadApply(Map<String, Object> map) {
		Apply dto = null;
		
		try{
			dto=dao.getReadInformation("clubApply.preReadApply", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public Apply nextReadApply(Map<String, Object> map) {
		Apply dto = null;
		
		try{
			dto=dao.getReadInformation("clubApply.nextReadApply", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public int updateApply(Apply dto, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteApply(int num, String saveFilename, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteApplyId(String userId, String root) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reply> listReply(Map<String, Object> map) {
		List<Reply> list=null;
		try {
			list=dao.getListInformation("clubApply.listReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	@Override
	public int insertReply(Reply dto) {
		int result=0;
		try {
			dto.setReplyNum(dao.getIntValue("clubApply.CARSeq"));
			result=dao.insertInformation("clubApply.insertApplyReply", dto);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int replyDataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("clubApply.replyDataCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	
	@Override
	public int replyCountAnswer(int answer) {
	
		int result=0;
		try {
			result=dao.getIntValue("clubApply.replyCountAnswer", answer);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteReply(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.deleteInformation("clubApply.deleteReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}

	@Override
	public List<Reply> listReplyAnswer(Map<String, Object> map) {
		List<Reply> list=null;
		try {
			list=dao.getListInformation("clubApply.listReplyAnswer", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
		
	}

	@Override
	public int insertReplyLike(Reply dto) {
		int result=0;
		try {
			result=dao.insertInformation("clubApply.insertReplyLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Map<String, Object> replyCountLike(int replyNum) {
		Map<String, Object> map=null;
		try {
			map=dao.getReadInformation("clubApply.replyCountLike", replyNum);
			System.out.println(map+"ㅠㅠㅠㅠ");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return map;
	}	
}

	