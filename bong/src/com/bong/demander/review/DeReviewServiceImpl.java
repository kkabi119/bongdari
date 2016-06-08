package com.bong.demander.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
			Map<String, Object> map=new HashMap<String, Object>();
			
			int seq = dao.getIntValue("demander.DeReviewSeq");
			dto.setServiceReviewIdx(seq);
			result = dao.insertInformation("demander.insertDeReview", dto);
		
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
	public List<DeReview> listDeReview(Map<String, Object> map) {
		List<DeReview> list=null;
		try {
			list=dao.getListInformation("demander.listDeReview", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	//파일************************
	@Override
	public int insertFile(DeReview dto, Map<String, Object> map) {
	/*	int result = 0;

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
		return result;*/
		
		int result=0;
		try {
			/*int maxNum=dao.getIntValue("demander.maxFileNum", map);
			dto.setFileNum(maxNum+1);*/
			
			result=dao.insertInformation("demander.insertFile", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}


	@Override
	public List<DeReview> listFile(Map<String, Object> map) {
		List<DeReview> listFile=null;
		
		try {
			listFile=dao.getListInformation("demander.listFile", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return listFile;
	}

	@Override
	public DeReview readFile(Map<String, Object> map) {
		DeReview dto=null;
		
		try {
			dto=dao.getReadInformation("demander.readFile", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public int deleteFile(Map<String, Object> map) {
		int result=0;

		try {
			result=dao.deleteInformation("demander.deleteFile", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//파일 끝**************************************
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
	public DeReview readDeReview(Map<String, Object> map) {
		DeReview dto=null;
		try {
			dto=dao.getReadInformation("demander.readDeReview", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int updateHitCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.updateInformation("demander.hitCountDeReview", map);
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
		/*int result=0;
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
			dao.updateInformation("demander.updateDeReviewFile", dto);
			result=1;
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;*/
		
		int result=0;
		
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			/*map.put("tableName", dto.getTableName());
			
			result=dao.updateData("demander.updateBoard", dto);*/
			
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
			dao.updateInformation("demander.updateDeReview", dto);
			
		} catch (Exception e) {
		}
		
		return result;
	}

	@Override
	public int deleteDeReview(DeReview dto, String saveFilename, String path) {
		int result=0;

		try{
			if(saveFilename != null ) {
			  fileManager.doFileDelete(saveFilename, path);
			}
			
			dao.deleteInformation("demander.deleteDeReview", dto);
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

	//댓글*****************************************
	@Override
	public int insertDeReviewReply(DeReviewReply dto) {
		int result = 0;
		try {
			int seq = dao.getIntValue("demander.SRRSeq");
			dto.setReplyNum(seq);
			result = dao.insertInformation("demander.deRevInsertReply", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<DeReviewReply> listDeReviewReply(Map<String, Object> map) {
		List<DeReviewReply> list=null;
		try {
			list=dao.getListInformation("demander.listDeReviewReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public List<DeReviewReply> listDeReviewReplyAnswer(Map<String, Object> map) {
		List<DeReviewReply> list=null;
		try {
			list=dao.getListInformation("demander.listDeRevReplyAnswer", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int DeReviewReplyDataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("demander.DeRevReplyDataCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int DeReviewReplyCountAnswer(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("demander.DeRevReplyCountAnswer", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteDeReviewReply(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.deleteInformation("demander.deleteDeRevReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	//댓글 끝*********************************************
	
	//좋아요*********************************************
	//좋아요상태
	@Override
	public int stateDeRevLike(DeReview dto) {
		int result=0;
		try {
			result=dao.getIntValue("demander.stateDeRevLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	
	//좋아요 입력
	@Override
	public int insertDeReviewLike(DeReview dto) {
		int result=0;
		try {
			result=dao.insertInformation("demander.insertDeReviewLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int deleteDeReviewLike(DeReview dto) {
		int result=0;
		try {
			result=dao.insertInformation("demander.deleteDeReviewLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	
	//좋아요 개수 올리기 
	@Override
	public int updateDeRevLikeCount(DeReview dto) {
		int result=0;
		try {
			result=dao.updateInformation("demander.updateDeRevLikeCount",dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//좋아요 개수 세기
	@Override
	public Map<String, Object> deRevCountLike(DeReview dto) {
		Map<String, Object> map=null;
		try {
			map=dao.getReadInformation("demander.deRevCountLike", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return map;
	}
	//좋아요 끝 ***************************************************** 
	
	//댓글좋아요***************************************************** 
	//좋아요상태
	@Override
	public int stateDeRevReplyLike(DeReviewReply dto) {
			int result=0;
			try {
				result=dao.getIntValue("demander.stateDeRevLikeRe", dto);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return result;
		}
	
	@Override
	public int insertDeReviewReplyLike(DeReviewReply dto) {
		int result=0;
		try {
			result=dao.insertInformation("demander.insertDeReviewLikeRe", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int deleteDeRevReplyLike(DeReviewReply dto) {
		int result=0;
		try {
			result=dao.insertInformation("demander.deleteDeReviewLikeRe", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public Map<String, Object> DeReviewReplyCountLike(Map<String, Object> map) {
		Map<String, Object> resultMap=null;
		try {
			resultMap=dao.getReadInformation("demander.deRevCountLikeRe", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return resultMap;
	}


	
	//댓글좋아요 끝*****************************************************
	


}
