package com.bong.cal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;

@Service("cal.CalService")
public class CalServiceImpl implements CalService{
	@Autowired
	private bongDAO  dao;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<Schedule> listVolun(Map<String, Object> map) {
		List<Schedule> list=null;
		try{
			list=dao.getListInformation("cal.volunList", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}

	@Override
	public int VolunCount(Map<String, Object> map) {
		int result=0;
		
		try{
			result=dao.getIntValue("admin.volunCount", map);			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public int insertVolunbbs(Schedule dto, String pathname) {
		int result=0;
		
		try {
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()){
				//업로드한 파일이 존재하는 경우
				String saveFilename=fileManager.doFileUpload(dto.getUpload(), pathname);
				dto.setSaveFilename(saveFilename);
				dto.setOriginalFilename(dto.getUpload().getOriginalFilename());
			}
				result=dao.insertInformation("cal.volunInsert", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int insertVolunbbsEach(Schedule dto) {
		int result=0;
		try {
			result=dao.insertInformation("cal.volunInsertEach", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Schedule articleForm(Map<String, Object> map) {
		Schedule dto = null;
		try {
			dto = dao.getReadInformation("cal.articleForm", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int takeVolun(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.insertInformation("cal.takeVolun", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}


	
}
