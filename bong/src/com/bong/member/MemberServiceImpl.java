package com.bong.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;

@Service("member.memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private bongDAO dao;
    @Autowired
    private FileManager fileManager;
	@Override
	public Member readMember1(int userIdx) {
		Member dto=null;
		try {
			dto=dao.getReadInformation("member.readMember1", userIdx);
			//전화번호 나누기
			if(dto!=null){
				if(dto.getUserTel()!=null){
					String [] s=dto.getUserTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
			//이메일 나누기
			if(dto!=null){
				if(dto.getUserEmail()!=null){
					String [] s=dto.getUserEmail().split("@");
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
	public Member readMemberLogin(String userId) {
		Member dto=null;
		try {
			dto=dao.getReadInformation("member.readMemberLogin", userId);
			//전화번호 나누기
			if(dto!=null){
				if(dto.getUserTel()!=null){
					String [] s=dto.getUserTel().split("-");
					dto.setTel1(s[0]);
					dto.setTel2(s[1]);
					dto.setTel3(s[2]);
				}
			}
			//이메일 나누기
			if(dto!=null){
				if(dto.getUserEmail()!=null){
					String [] s=dto.getUserEmail().split("@");
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
	public int insertMember(Member dto, String pathname) throws Exception {
		int result = 0;
		try {
			//전화 번호 합쳐서 데이터 넣기
			if(dto.getTel1() != null && dto.getTel1().length()!=0 &&
					dto.getTel2() != null && dto.getTel2().length()!=0 &&
					dto.getTel3() != null && dto.getTel3().length()!=0)
				dto.setUserTel(dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3());
			System.out.println(dto.getUserTel());
			//이메일 합쳐서 데이터 넣기
			if(dto.getEmail1() != null && dto.getEmail1().length()!=0 &&
					dto.getEmail2() != null && dto.getEmail2().length()!=0 )
				dto.setUserEmail(dto.getEmail1() + "@" + dto.getEmail2());
			int seq=dao.getIntValue("member.memberSeq");
			dto.setUserIdx(seq);
			
			if(dto.getUploads()!=null && !dto.getUploads().isEmpty()){
				String memImg=fileManager.doFileUpload(dto.getUploads(), pathname);
			    dto.setMemImg(memImg);
			    dto.setMemImgname(dto.getUploads().getOriginalFilename());
			    
			}
			//회원정보 저장
			dao.insertInformation("member.insertMember", seq);
			dao.insertInformation("member.insertMemberInfo", dto);
			
			result = 1;
		} catch (Exception e) {
			System.out.println(e.toString());
			throw e;
		}
		return result;
	}

	@Override
	public int updateMember2(Member dto, String pathname) {
		int result=0; 
		try {
			//전화 번호 합쳐서 데이터 넣기
			if(dto.getTel1() != null && dto.getTel1().length()!=0 &&
					dto.getTel2() != null && dto.getTel2().length()!=0 &&
					dto.getTel3() != null && dto.getTel3().length()!=0)
				dto.setUserTel(dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3());
			System.out.println(dto.getUserTel());
			//이메일 합쳐서 데이터 넣기
			if(dto.getEmail1() != null && dto.getEmail1().length()!=0 &&
					dto.getEmail2() != null && dto.getEmail2().length()!=0 )
				dto.setUserEmail(dto.getEmail1() + "@" + dto.getEmail2());
			int seq=dao.getIntValue("member.memberSeq");
			dto.setUserIdx(seq);
			
			if(dto.getUploads()!=null && !dto.getUploads().isEmpty()){
				String memImg=fileManager.doFileUpload(dto.getUploads(), pathname);
			    dto.setMemImg(memImg);
			    dto.setMemImgname(dto.getUploads().getOriginalFilename());
			    
			    result=dao.updateInformation("member.updateMember2", dto);
			}
		} catch (Exception e) {
			
		}
		return result;
	}

	@Override
	public int updateLastLogin(String userId) {
		int result=0;
		
		try {
			result=dao.updateInformation("member.updateLastLogin", userId);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteMember2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> listMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertAuthority(Member dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAuthority(Member dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member readAuthority(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> listAuthority(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAuthority(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
