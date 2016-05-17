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
			//전화번호 나누기
			if(dto!=null){
				if(dto.getServiceTel()!=null){
					String [] s=dto.getServiceTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
			//이메일 나누기
			if(dto!=null){
				if(dto.getServiceEmail()!=null){
					String [] s=dto.getServiceEmail().split("@");
					dto.setEmail1(s[0]);
					dto.setEmail2(s[1]);
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
			//전화번호 나누기
			if(dto!=null){
				if(dto.getServiceTel()!=null){
					String [] s=dto.getServiceTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
			//이메일 나누기
			if(dto!=null){
				if(dto.getServiceEmail()!=null){
					String [] s=dto.getServiceEmail().split("@");
					dto.setEmail1(s[0]);
					dto.setEmail2(s[1]);
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
			//전화 번호 합쳐서 데이터 넣기
			if(dto.getTel1() != null && dto.getTel1().length()!=0 &&
					dto.getTel2() != null && dto.getTel2().length()!=0 &&
					dto.getTel3() != null && dto.getTel3().length()!=0)
				dto.setServiceTel(dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3());
			//이메일 합쳐서 데이터 넣기
			if(dto.getEmail1() != null && dto.getEmail1().length()!=0 &&
					dto.getEmail2() != null && dto.getEmail2().length()!=0 )
				dto.setServiceEmail(dto.getEmail1() + "@" + dto.getEmail2());
			
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
