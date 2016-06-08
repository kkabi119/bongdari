package com.bong.club.review;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;
import com.bong.demander.review.DeReview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;
import com.bong.club.apply.Reply;
import com.bong.club.review.ClReview;

@Service("club_review.reviewService")
public class ClReviewServiceImpl implements ClReviewService {

	@Autowired
	private bongDAO dao;

	@Autowired
	private FileManager fileManager;
	
	////////////////////////////////////////////////////////////////// 	게시물 작성
	@Override
	public int insertClReview(ClReview dto, String path) {
		int result = 0;

		try {
			Map<String, Object> map=new HashMap<String, Object>();
			
			int seq = dao.getIntValue("club_review.ClReviewSeq");
			System.out.println("SEQ: "+seq);
			dto.setClubReviewIdx(seq);
			result = dao.insertInformation("club_review.insertClReview", dto);
		
		// 파일 업로드
		if(! dto.getUpload().isEmpty()) {
			for(MultipartFile mf:dto.getUpload()) {
				if(mf.isEmpty())
					continue;
				
				String saveFilename=fileManager.doFileUpload(mf, path);
				if(saveFilename!=null) {
					String originalFilename=mf.getOriginalFilename();
					long fileSize=mf.getSize();
					
					dto.setOriginalFilename(originalFilename);
					dto.setSaveFilename(saveFilename);
					dto.setFileSize(fileSize);
										
					insertFile(dto, map);
					
				}
			}
		}
		
	} catch (Exception e) {
		System.out.println(e.toString());
	}
		return result;
	}

	////////////////////////////////////////////////////////////////// 	게시물 리스트 뽑기
	@Override
	public List<ClReview> listClReview(Map<String, Object> map) {
		List<ClReview> list=null;
		try {
			list=dao.getListInformation("club_review.listClReview", map);
			if(list ==null){
				System.out.println("널이다");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	////////////////////////////////////////////////////////////////// 	게시물 개수 세기 
	@Override 
	public int dataCount(Map<String, Object> map) {
	
		int result=0;
		try {
			result=dao.getIntValue("club_review.dataCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public ClReview readClReview(Map<String, Object> map) {
		ClReview dto=null;
		try {
			dto=dao.getReadInformation("club_review.readClReview", map);
			
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
			result=dao.updateInformation("club_review.hitCountClReview", num);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public ClReview preReadClReview(Map<String, Object> map) {
		ClReview dto=null;
		try {
			dto=dao.getReadInformation("club_review.preReadClReview", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public ClReview nextReadClReview(Map<String, Object> map) {
		ClReview dto=null;
		try {
			dto=dao.getReadInformation("club_review.nextReadClReview", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	
	@Override
	public int updateClReview(ClReview dto, String path) {
		
		int result=0;
		
		try {
			Map<String, Object> map=new HashMap<String, Object>();
		
			if(! dto.getUpload().isEmpty()) {
				for(MultipartFile mf:dto.getUpload()) {
					if(mf.isEmpty())
						continue;
					
					String saveFilename=fileManager.doFileUpload(mf, path);
					if(saveFilename!=null) {
						String originalFilename=mf.getOriginalFilename();
						long fileSize=mf.getSize();
						
						dto.setOriginalFilename(originalFilename);
						dto.setSaveFilename(saveFilename);
						dto.setFileSize(fileSize);
						
						insertFile(dto, map);
					}
				}
			}
			
		} catch (Exception e) {
		}
		
		return result;
	}

	@Override
	public int deleteClReview(int num, String saveFilename, String path) {
		int result=0;

		try{
			
			if(saveFilename != null ) {
			  fileManager.doFileDelete(saveFilename, path);
			}
			
			result=dao.deleteInformation("club_review.deleteReview", num);
			result=1;
			
		} catch(Exception e) {
		}
		return result;
	}

	@Override
	public int stateLike(ClReview dto) {
		int result=0;
		try {
			System.out.println("service 들어왔다 ");
			result=dao.getIntValue("club_review.stateLike", dto);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int insertLike(ClReview dto) {
		int result=0;
		try {
			result=dao.insertInformation("club_review.insertLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteLike(ClReview dto) {
		int result=0;
		try {
			result=dao.insertInformation("club_review.deleteLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Map<String, Object> countLike(int num) {//게시글 좋아요 개수
	
		Map<String, Object> map=null;
		try {
			map=dao.getReadInformation("club_review.countLike", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return map;
	}

	//////////////////////////////////////////////////////			댓글 관련 	/////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////  댓글 개수세기
	@Override
	public int replyDataCount(Map<String, Object> map) {
		
		int result=0;
		try {
			result=dao.getIntValue("club_review.replyDataCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	/////////////////////////////////////////////////////////  댓글 목록 뽑기
	@Override
	public List<ClReviewReply> listReply(Map<String, Object> map) {
		List<ClReviewReply> list=null;
		try {
			list=dao.getListInformation("club_review.listReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	
	/////////////////////////////////////////////////////////  댓글 등록
	@Override
	public int insertReply(ClReviewReply dto) {
		int result=0;
		try {
			dto.setReplyNum(dao.getIntValue("club_review.CRRSeq"));
			result=dao.insertInformation("club_review.insertReviewReply", dto);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}


	@Override
	public int insertFile(ClReview dto, Map<String, Object> map) {
		int result=0;
		try {
			
			result=dao.insertInformation("club_review.insertFile", dto);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<ClReview> listFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClReview readFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int updateClRevLikeCount(ClReview dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClReviewId(String userId, String root) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public List<ClReviewReply> listReplyAnswer(Map<String, Object> map) {
	
		List<ClReviewReply> list=null;
		try {
			list=dao.getListInformation("club_review.listReplyAnswer", map);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
		
	}

	@Override
	public int replyCountAnswer(int answer) {
		int result=0;
		try {
			result=dao.getIntValue("club_review.replyCountAnswer", answer);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteReply(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.deleteInformation("club_review.deleteReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int stateReplyLike(ClReviewReply dto) {
		int result=0;
		try {
			result=dao.getIntValue("club_review.stateReplyLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int insertReplyLike(ClReviewReply dto) {
		int result=0;
		try {
			result=dao.insertInformation("club_review.insertReplyLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int deleteReplyLike(ClReviewReply dto) {
		int result=0;
		try {
			result=dao.insertInformation("club_review.deleteReplyLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public Map<String, Object> replyCountLike(int replyNum) {
		
		Map<String, Object> map=null;
		try {
			map=dao.getReadInformation("club_review.replyCountLike", replyNum);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return map;
	}
	

	

	

}
