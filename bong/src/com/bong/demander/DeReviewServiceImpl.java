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
		int result = 0;

		try {
			if (dto.getUpload() != null && !dto.getUpload().isEmpty()) {
				String saveFilename = fileManager.doFileUpload(dto.getUpload(), path);
				dto.setSaveFilename(saveFilename);
				dto.setOriginalFilename(dto.getUpload().getOriginalFilename());

			}

			int seq = dao.getIntValue("demander.DeReviewSeq");
			dto.setServiceReviewIdx(seq);
			result = dao.insertInformation("demander.insertDeReview", dto);
			dao.insertInformation("demander.insertDeReFile", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int insertDeReFile(DeReview dto, String path) {
		int result = 0;

		try {
			if (dto.getUpload() != null && !dto.getUpload().isEmpty()) {
				String saveFilename = fileManager.doFileUpload(dto.getUpload(), path);
				dto.setSaveFilename(saveFilename);
				dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
				// result=dao.insertInformation("demander.insertDeReFile", dto);
			}
			result = dao.insertInformation("demander.insertDeReFile", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<DeReview> listDeReview(Map<String, Object> map) {
		List<DeReview> list=null;
		try {
			list=dao.getListInformation("demander.listDeReview", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("demander.dataCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public DeReview readDeReview(int num) {
		DeReview dto=null;
		try {
			dto=dao.getReadInformation("demander.readDeReview", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int updateHitCount(int num) {
		int result=0;
		try {
			result=dao.updateInformation("demander.hitCountDeReview", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public DeReview preReadDeReview(Map<String, Object> map) {
		DeReview dto=null;
		try {
			dto=dao.getReadInformation("demander.preReadDeReview", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public DeReview nextReadDeReview(Map<String, Object> map) {
		DeReview dto=null;
		try {
			dto=dao.getReadInformation("demander.nextReadDeReview", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int updateDeReview(DeReview dto, String path) {
		int result=0;

		try{
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()) {
				// 이전파일 지우기
				if(dto.getSaveFilename().length()!=0)
					fileManager.doFileDelete(dto.getSaveFilename(), path);
				
				String newFilename = fileManager.doFileUpload(dto.getUpload(), path);
				if (newFilename != null) {
					dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
					dto.setSaveFilename(newFilename);
				}
			}
			dao.updateInformation("demander.updateDeReview", dto);
			result=1;
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteDeReview(int num, String saveFilename, String path) {
		int result=0;

		try{
			
			if(saveFilename != null ) {
			  fileManager.doFileDelete(saveFilename, path);
			}
			
			dao.deleteInformation("demander.deleteDeReview", num);
			result=1;
		} catch(Exception e) {
		}
		return result;
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
