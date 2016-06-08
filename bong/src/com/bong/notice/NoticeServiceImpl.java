package com.bong.notice;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;
@Service("notice.noticeService")
public class NoticeServiceImpl implements NoticeService{
    
	@Autowired
	private bongDAO dao;
	
	@Autowired
	private FileManager fileManager;
	
	
	@Override
	public int insertNotice(Notice dto, String pathname) {
		int result=0;
		try {
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()){
				//업로드한 파일이 존재하는 경우
				String saveFilename=fileManager.doFileUpload(dto.getUpload(), pathname);
				dto.setSaveFilename(saveFilename);
				dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
		}
			result=dao.insertInformation("notice.insertNotice", dto);
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<Notice> listNotice(Map<String, Object> map) {
		List<Notice> list = null;
		try {
			list=dao.getListInformation("notice.listNotice", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public List<Notice> listNoticeSmall(Map<String, Object> map) {
		List<Notice> list = null;	
		try {
			list=dao.getListInformation("notice.listNoticeSmall", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result=dao.getIntValue("notice.dataCount",map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Notice readNotice(Map<String, Object> map) {
		Notice dto = null;
		try {
			//게시물 가져오기
			dto=dao.getReadInformation("notice.readNotice", map);
		} catch (Exception e) {
		 System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int updateHitCount(Map<String, Object> map) {
		int result = 0;
		try {
			//조회수증가
			result = dao.updateInformation("notice.updateHitCount", map);
		} catch (Exception e) {
		  System.out.println(e.toString());
		}
		return result;
	}
    //이전글
	@Override
	public Notice preReadNotice(Map<String, Object> map) {
		Notice dto = null;
		try {
			dto=dao.getReadInformation("notice.preReadNotice", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
    //다음글
	@Override
	public Notice nextReadNotice(Map<String, Object> map) {
		Notice dto = null;
		try {
			dto = dao.getReadInformation("notice.nextReadNotice", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int updateNotice(Notice dto, String path) {
		int result = 0;
		try {
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()){
				//이전 파일 지우기
				if(dto.getSaveFilename().length()!=0)
					fileManager.doFileDelete(dto.getSaveFilename(), path);
				
				String newFilename = fileManager.doFileUpload(dto.getUpload(), path);
				if(newFilename != null){
					dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
					dto.setSaveFilename(newFilename);
				}
			}
			
			dao.updateInformation("notice.updateNotice", dto);
			result = 1;
		} catch (Exception e) {
		  System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteNotice(Map<String, Object> map, String saveFilename, String path) {
		int result = 0;
		
		try {
			if(saveFilename !=null){
				fileManager.doFileDelete(saveFilename, path);
			}
			dao.deleteInformation("notice.deleteNotice", map);
			result=1;
		} catch (Exception e) {
		   System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteNoticeId(String userId, String root) {
		int result = 0;
		// 탈퇴한 경우 게시물 삭제
		// 좋아요,싫어요, 댓글 등은 ON DELETE CASCADE 옵션으로 자동삭제
        try {
			String path = root+File.separator+"uploads"+File.separator+"bbs";
			
			List<Notice>list = dao.getListInformation("notice.listNoticeId", userId);
			for(Notice dto:list){
				if(dto.getSaveFilename() != null && dto.getSaveFilename().length()!=0){
					fileManager.doFileDelete(dto.getSaveFilename(), path);
				}
			}
			dao.deleteInformation("notice.deleteNoticeId", userId);
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
		return result;
	}

	@Override
	public int insertReply(Reply dto) {
		int result=0;
		try {
			dto.setReplyNum(dao.getIntValue("notice.NRSeq"));
			result=dao.insertInformation("notice.insertNoticeReply", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public List<Reply> listReply(Map<String, Object> map) {
		List<Reply> list=null;
		try {
			list=dao.getListInformation("notice.listReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public List<Reply> listReplyAnswer(Map<String, Object> map) {
		List<Reply> list=null;
		try {
			list=dao.getListInformation("notice.listReplyAnswer", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int replyDataCount(Map<String, Object> map) {
		int result=0;
		
		try {
			result=dao.getIntValue("notice.replyDataCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int replyCountAnswer(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("notice.replyCountAnswer", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public int deleteReply(Map<String, Object> map) {
		int result = 0;
		try {
			result=dao.deleteInformation("notice.deleteReply", map);
		} catch (Exception e) {
		   System.out.println(e.toString());
		}
		return result;
	}

}
