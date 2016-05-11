package com.bong.demanderjoin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("demanderjoin.demanderjoinService")
public class DemanderjoinServiceImpl implements DemanderjoinService {
    
	@Autowired
	private bongDAO dao;
	@Override
	public Demanderjoin readDemanderjoin1(int serviceIdx) {
		Demanderjoin dto=null;
		try {
			dto=dao.getReadInformation("demanderjoin.readDemanderjoin1", serviceIdx);
			
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
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int insertDemanderjoin(Demanderjoin dto) {
		int result = 0;
		try {
			int seq = dao.getIntValue("demanderjoin.demanderjoinSeq");
			dto.setServiceIdx(seq);
			
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
