package com.bong.club.notice;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;

@Service("clubnotice.noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private bongDAO dao;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public int insertNotice(Notice dto, String pathname) {
		int result=0;
		try {
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()){
				//���ε��� ������ �����ϴ� ���
				String saveFilename=fileManager.doFileUpload(dto.getUpload(), pathname);
				dto.setSaveFilename(saveFilename);
				dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
			}
			result=dao.insertInformation("clubnotice.insertNotice", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public List<Notice> listNotice(Map<String, Object> map) {
		
		List<Notice> list=null;
		try {
			list=dao.getListInformation("clubnotice.listNotice", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result=dao.getIntValue("clubnotice.dataCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Notice readNotice(int num) {
		Notice dto=null;
		
		try {
			//�Խù� ��������
			dto=dao.getReadInformation("clubnotice.readNotice", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public int updateHitCount(int num) {
int result=0;
		
		try{
			// ��ȸ�� ����
			result=dao.updateInformation("clubnotice.updateHitCount", num);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public Notice preReadNotice(Map<String, Object> map) {
		Notice dto = null;
		
		try{
			dto=dao.getReadInformation("clubnotice.preReadNotice", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public Notice nextReadNotice(Map<String, Object> map) {
		Notice dto = null;
		
		try{
			dto=dao.getReadInformation("clubnotice.nextReadNotice", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}

	@Override
	public int updateNotice(Notice dto, String path) {
		int result=0;

		try{
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()) {
				// �������� �����
				if(dto.getSaveFilename().length()!=0)
					fileManager.doFileDelete(dto.getSaveFilename(), path);
				
				String newFilename = fileManager.doFileUpload(dto.getUpload(), path);
				if (newFilename != null) {
					dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
					dto.setSaveFilename(newFilename);
				}
			}
			
			dao.updateInformation("clubnotice.updateNotice", dto);
			result=1;
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteNotice(int num, String saveFilename, String path) {
		int result=0;

		try{
			
			if(saveFilename != null ) {
			  fileManager.doFileDelete(saveFilename, path);
			}
			
			dao.deleteInformation("clubnotice.deleteNotice", num);
			result=1;
		} catch(Exception e) {
		}
		return result;
	}

	@Override
	public int deleteNoticeId(String userId, String root) {
		int result=0;
		// ȸ���� Ż���� ��� �Խù� ����.
		// ���ƿ�/�Ⱦ��, ����� ON DELETE CASCADE �ɼ����� �ڵ� ����
		try {
			String path=root+File.separator+"uploads"+File.separator+"bbs";
			
			List<Notice>list=dao.getListInformation("clubnotice.listNoticeId", userId);
			for(Notice dto:list) {
				if(dto.getSaveFilename() != null && dto.getSaveFilename().length()!=0) {
					  fileManager.doFileDelete(dto.getSaveFilename(), path);
		       }
			}
			
			dao.deleteInformation("clubnotice.deletenoticeId", userId);
			
		} catch (Exception e) {
		}
		
		
		return result;
	}

	@Override
	public int insertReply(Reply dto) {
		int result=0;
		try {
			result=dao.insertInformation("clubnotice.insertReply", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<Reply> listReply(Map<String, Object> map) {
		List<Reply> list=null;
		try {
			list=dao.getListInformation("clubnotice.listReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public List<Reply> listReplyAnswer(int answer) {
		List<Reply> list=null;
		try {
			list=dao.getListInformation("clubnotice.listReplyAnswer", answer);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int replyDataCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("clubnotice.replyDataCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int replyCountAnswer(int answer) {
		int result=0;
		try {
			result=dao.getIntValue("clubnotice.replyCountAnswer", answer);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteReply(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.deleteInformation("clubnotice.deleteReply", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
}
