package com.bong.demanderjoin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;

@Service("demanderjoin.demanderjoinService")
public class DemanderjoinServiceImpl implements DemanderjoinService {
    
	@Autowired
	private bongDAO dao;
	@Autowired
	private FileManager fileManager;
	@Override
	public Demanderjoin readDemanderjoin1(int serviceIdx) {
		Demanderjoin dto=null;
		try {
			dto=dao.getReadInformation("demanderjoin.readDemanderjoin1", serviceIdx);
			
			if(dto!=null){
				if(dto.getServiceTel()!=null){
					String [] s=dto.getServiceTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public Demanderjoin readDemanderjoinLogin(String serviceId) {
		Demanderjoin dto = null;
		try {
			dto=dao.getReadInformation("demanderjoin.readDemanderjoinLogin", serviceId);
			
			if(dto!=null){
				if(dto.getServiceTel()!=null){
					String [] s=dto.getServiceTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int insertDemanderjoin(Demanderjoin dto, String pathname) {
		int result = 0;
		try {
			if(dto.getTel1() != null && dto.getTel1().length()!=0 &&
					dto.getTel2() != null && dto.getTel2().length()!=0 &&
					dto.getTel3() != null && dto.getTel3().length()!=0)
				dto.setServiceTel(dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3());
			System.out.println(dto.getServiceTel());
			int seq = dao.getIntValue("demanderjoin.demanderjoinSeq");
			dto.setServiceIdx(seq);
			
			if(dto.getServiceImg()!=null && !dto.getServiceImg().isEmpty()){
			  String filename=fileManager.doFileUpload(dto.getServiceImg(), pathname);	
			  dto.setServiceImgname(filename);
			}
		
			//수요처 회원 정보 저장
			dao.insertInformation("demanderjoin.insertDemanderjoin", seq);
			dao.insertInformation("demanderjoin.insertDemanderjoinInfo", dto);
			
			result = 1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int Demanderjoin2(Demanderjoin dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDemanderjoin2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Demanderjoin> listDemanderjoin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
