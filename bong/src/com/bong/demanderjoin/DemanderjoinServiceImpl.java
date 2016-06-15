package com.bong.demanderjoin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.club.ClubTheme;
import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;
import com.bong.member.Member;

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
			
			if(dto!=null){
				if(dto.getServiceAddr()!=null){
					String [] s=dto.getServiceAddr().split(",");
					dto.setAddr1(s[0]);
					dto.setAddr2(s[1]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	@Override
	public Demanderjoin readDemanderCheck(String userId) {
	    Demanderjoin dto = null;
	    		
	  	try {
		 dto = dao.getReadInformation("demanderjoin.readDemanderCheck", userId);
		} catch (Exception e) {	
			System.out.println(e.toString());
		}
		return dto;
	}
	@Override
	public Demanderjoin readDemanderjoinLogin(String userIdx) {
		Demanderjoin dto = null;
		try {
			dto=dao.getReadInformation("demanderjoin.readDemanderjoinLogin", userIdx);
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
			
			if(dto!=null){
				if(dto.getServiceAddr()!=null){
					String [] s=dto.getServiceAddr().split(",");
					dto.setAddr1(s[0]);
					dto.setAddr2(s[1]);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	@Override
	public Demanderjoin readDemanderjoinInfo(String userIdx) {
		Demanderjoin dto = null;
		try {
			dto=dao.getReadInformation("demanderjoin.readDemanderjoinInfo", userIdx);
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
			if(dto!=null){
				if(dto.getServiceAddr()!=null){
					String [] s=dto.getServiceAddr().split(",");
					dto.setAddr1(s[0]);
					dto.setAddr2(s[1]);
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
			//주소 합쳐서 넣기
			if(dto.getAddr1() != null && dto.getAddr1().length()!=0 &&
					dto.getAddr2() != null && dto.getAddr2().length()!=0 )
				dto.setServiceAddr(dto.getAddr1() + "," + dto.getAddr2());
			
			int memSeq = dao.getIntValue("member.memberSeq");
			dto.setUserIdx(memSeq);			
			int serviceSeq = dao.getIntValue("demanderjoin.demanderjoinSeq");
			dto.setServiceIdx(serviceSeq);
			
			if(dto.getUploads()!=null && !dto.getUploads().isEmpty()){
				String serviceImg=fileManager.doFileUpload(dto.getUploads(), pathname);
			    dto.setServiceImg(serviceImg);
			    dto.setServiceImgname(dto.getUploads().getOriginalFilename());
			    
			}
			//수요처 회원 정보 저장
			dao.insertInformation("demanderjoin.insertMember", memSeq);
			dao.insertInformation("demanderjoin.insertDemanderCheck", dto);
			dao.insertInformation("demanderjoin.insertDemanderjoin", serviceSeq);
			dao.insertInformation("demanderjoin.insertDemanderjoinInfo", dto);
			dto.setAuthority("ROLE_SERVICE");
			dao.insertInformation("demanderjoin.insertAuthority", dto);
			result=1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	@Override
	public int updateDemander2(Demanderjoin dto, String pathname) {
		int result =0;
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
			
           // 이미지 파일 넣기
		    if(dto.getUploads()!=null && !dto.getUploads().isEmpty()){
				String serviceImg=fileManager.doFileUpload(dto.getUploads(), pathname);
			    dto.setServiceImg(serviceImg);
			    dto.setServiceImgname(dto.getUploads().getOriginalFilename());    
			}
		    
		    //주소 합쳐서 넣기
		    if(dto.getAddr1() != null && dto.getAddr1().length()!=0 &&
		    		dto.getAddr2() != null && dto.getAddr2().length()!=0 )
		    	dto.setServiceAddr(dto.getAddr1() + "," + dto.getAddr2());
			result=dao.updateInformation("demanderjoin.updatePwd", dto);
			result=dao.updateInformation("demanderjoin.updateDemander2", dto);
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

	@Override
	public int updateLastLogin(String userId) {
		int result = 0;
		
		try {
			result=dao.updateInformation("demanderjoin.updateLastLogin", userId);	
		} catch (Exception e) {	
			System.out.println(e.toString());
		}
		return result;
	}
	@Override
	public List<Demanderjoin> listDemanderAddr(Map<String, Object> map) {
		List<Demanderjoin> list = null;
		try {
			list=dao.getListInformation("demanderjoin.searchDemanderAddr", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
/*
	@Override
	public List<Demanderjoin> listClubTheme(int themeNum) {
		List<Demanderjoin> list=null;
		
		try {
			list=dao.getListInformation("demanderjoin.listClubTheme", themeNum);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	@Override
	public List<ClubTheme> listClubThemeAll() {
		// TODO Auto-generated method stub
		return null;
	}*/




}
