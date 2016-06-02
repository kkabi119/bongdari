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
import com.bong.club.review.ClReview;

@Service("club_review.reviewService")
public class ClReviewServiceImpl implements ClReviewService {

	@Autowired
	private bongDAO dao;

	@Autowired
	private FileManager fileManager;

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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClReview(int num, String saveFilename, String path) {
		// TODO Auto-generated method stub
		return 0;
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
	public int stateClRevLike(ClReview dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertClReviewLike(ClReview dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClReviewLike(ClReview dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> ClRevCountLike(int num) {
		// TODO Auto-generated method stub
		return null;
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
	public int insertClReviewReply(ClReviewReply dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ClReviewReply> listClReviewReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClReviewReply> listClReviewReplyAnswer(int answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int ClReviewReplyDataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ClReviewReplyCountAnswer(int answer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClReviewReply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int stateClRevReplyLike(ClReviewReply dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertClReviewReplyLike(ClReviewReply dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClRevReplyLike(ClReviewReply dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> ClReviewReplyCountLike(int ClReviewReplyNum) {
		// TODO Auto-generated method stub
		return null;
	}


}
