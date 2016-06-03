package com.bong.notice;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateHitCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notice preReadNotice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice nextReadNotice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateNotice(Notice dto, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNotice(Map<String, Object> map, String saveFilename, String path) {
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
