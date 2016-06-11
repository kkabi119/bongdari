package com.bong.club.apply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.club.apply.Reply;
import com.bong.club.notice.Notice;
import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;
import com.bong.demander.review.DeReview;
import com.bong.member.Member;

@Service("clubApply.applyService")
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	private bongDAO dao;
	
	@Autowired
	private FileManager fileManager;

	
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
			System.out.println("datacount의 result= "+result);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public Apply readApply(Map<String, Object> map) {
		Apply dto=null;
		
		try {
			//게시물 가져오기
			System.out.println("2. 서비스 -readAppy 매퍼로 이동 ");
			dto=dao.getReadInformation("clubApply.readApply", map);
			System.out.println("2. 서비스 -readApply 빠져나갑니다 ");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public int updateHitCount(Map<String, Object> map) {
		int result=0;
		
		try{
			// 조회수 증가
			result=dao.updateInformation("clubApply.updateHitCount", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	

	@Override
	public int updateHitCount_club( Map<String, Object> map) {

		int result=0;
		
		try{
			// 클럽봉사신청게시판 조회수 증가
			result=	dao.updateInformation("clubApply.updateHitCount_club",map);
		
			
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
	public int deleteApply(int num, String saveFileName, String path) {
		int result=0;

		try{			
			
			if(saveFileName != null ) {
			  fileManager.doFileDelete(saveFileName, path);
			}			
			dao.deleteInformation("clubApply.deleteApply", num);
			result=1;
			
		} catch(Exception e) {
		}
		return result;
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
	public int replyCountAnswer(Map<String, Object> map) {
	
		int result=0;
		try {
			result=dao.getIntValue("clubApply.replyCountAnswer", map);
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
	public Map<String, Object> replyCountLike(Map<String, Object> map) {
		Map<String, Object> resultMap=null;
		try {
			resultMap=dao.getReadInformation("clubApply.replyCountLike", map);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return resultMap;
	}

	@Override
	public int stateReplyLike(Reply dto) {
		int result=0;
		try {
			result=dao.getIntValue("clubApply.stateReplyLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}	
	
	@Override
	public int deleteReplyLike(Reply dto) {
		int result=0;
		try {
			result=dao.insertInformation("clubApply.deleteReplyLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<Member> readApplyList(Map<String, Object> map) {
		
		List<Member> list=null;
		try {
			//게시물 가져오기
			list=dao.getListInformation("clubApply.readApplyList", map);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}

	@Override
	public List<Member> readApplyList_date(Map<String, Object> map) {
		
		List<Member> list=null;
		
		try {	
			list=dao.getListInformation("clubApply.readApplyList_date", map);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	
	/*추가*/
	
	@Override
	public Apply readApplyOriginal(Map<String, Object> map) {
		Apply dto=null;
		
		try {
			
			dto=dao.getReadInformation("clubApply.readApplyOriginal", map);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	@Override
	public List<Apply> readApplyVolunData(int volunIdx) {
		
		List<Apply> list=null;
		
		try {
			
			list=dao.getListInformation("clubApply.readApplyVolunData", volunIdx);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	
	@Override
	public int insertMemList(Apply dto) {
		int result=0;
		try {
			result=dao.insertInformation("clubApply.insertMemList", dto);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int deleteMemList(Apply dto) {
		int result=0;
		try {
			result=dao.deleteInformation("clubApply.deleteMemList", dto);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int clubApprovalCheck(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("clubApply.clubApprovalCheck", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int applyCheckOk(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("clubApply.applyCheckOk", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public String clubIdCheck(int clubSeq) {
		String clubId="";
		try {
			clubId=dao.getReadInformation("clubApply.clubIdCheck", clubSeq);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return clubId;
	}
}

	