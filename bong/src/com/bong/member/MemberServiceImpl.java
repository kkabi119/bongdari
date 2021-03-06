package com.bong.member;

import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;
import com.bong.mypage.MyApply;


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
			if(dto!=null){
				if(dto.getUserAddr()!=null){
					String [] s=dto.getUserAddr().split(",");
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
	public Member readMemberCheck(String userId) {
		Member dto = null;
		
		try {
			dto =dao.getReadInformation("member.readMemberCheck", userId);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	@Override
	public Member readMemberLogin(String userIdx) {
		Member dto=null;
		try {
			dto=dao.getReadInformation("member.readMemberLogin", userIdx);
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
			if(dto!=null){
				if(dto.getUserAddr()!=null){
					String [] s=dto.getUserAddr().split(",");
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
	public Member readMemberInfo(String userIdx) {
		Member dto=null;
		try {
			dto=dao.getReadInformation("member.readMemberInfo", userIdx);
			//"member. -> mapper namespace, readMemberInfo=>실행할 쿼리이름
			
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
			if(dto!=null){
				if(dto.getUserAddr()!=null){
					String [] s=dto.getUserAddr().split(",");
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
	public int insertMember(Member dto, String pathname) throws Exception {
		int result = 0;
		try {
			//전화 번호 합쳐서 데이터 넣기
			if(dto.getTel1() != null && dto.getTel1().length()!=0 &&
					dto.getTel2() != null && dto.getTel2().length()!=0 &&
					dto.getTel3() != null && dto.getTel3().length()!=0)
				dto.setUserTel(dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3());
			
			//주소 합쳐서 넣기
			if(dto.getAddr1() != null && dto.getAddr1().length()!=0 &&
					dto.getAddr2() != null && dto.getAddr2().length()!=0 )
				dto.setUserAddr(dto.getAddr1() + "," + dto.getAddr2());
			
			//이메일 합쳐서 데이터 넣기
			if(dto.getEmail1() != null && dto.getEmail1().length()!=0 &&
					dto.getEmail2() != null && dto.getEmail2().length()!=0 )
				dto.setUserEmail(dto.getEmail1() + "@" + dto.getEmail2());
			int seq=dao.getIntValue("member.memberSeq");
			dto.setUserIdx(seq);
			//사진 
			if(dto.getUploads()!=null && !dto.getUploads().isEmpty()){
				String memImg=fileManager.doFileUpload(dto.getUploads(), pathname);
			    dto.setMemImg(memImg);
			    dto.setMemImgname(dto.getUploads().getOriginalFilename());
			    
			}
			//회원정보 저장
			dao.insertInformation("member.insertMember", seq);
			dao.insertInformation("member.insertCheck", dto);
			dao.insertInformation("member.insertMemberInfo", dto);
			dto.setAuthority("ROLE_USER");
			dao.insertInformation("member.insertAuthority", dto);
			
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
			//이메일 합쳐서 데이터 넣기
			if(dto.getEmail1() != null && dto.getEmail1().length()!=0 &&
					dto.getEmail2() != null && dto.getEmail2().length()!=0 )
				dto.setUserEmail(dto.getEmail1() + "@" + dto.getEmail2());
           // 이미지 파일 넣기
			if(dto.getUploads()!=null && !dto.getUploads().isEmpty()){
				String memImg=fileManager.doFileUpload(dto.getUploads(), pathname);
			    dto.setMemImg(memImg);
			    dto.setMemImgname(dto.getUploads().getOriginalFilename());
			}
			//주소 합쳐서 넣기
			if(dto.getAddr1() != null && dto.getAddr1().length()!=0 &&
					dto.getAddr2() != null && dto.getAddr2().length()!=0 )
				dto.setUserAddr(dto.getAddr1() + "," + dto.getAddr2());
			
		    result=dao.updateInformation("member.updatePwd", dto);
		    result=dao.updateInformation("member.updateMember2", dto);
			
			
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
   
	//회원 탈퇴/삭제
	@Override
	public int deleteMember2(Map<String, Object> map) {
		int result = 0;
		try {
			//memberInfo 테이블 삭제
			int userIdx=(Integer)map.get("userIdx");
			result = dao.deleteInformation("member.deleteMember2", userIdx);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public String managerCheck(String userId) {
		String result=null;
		try {
			result=dao.getReadInformation("member.managerCheck", userId);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public int deleteImage(String userId, String pathname, String filename) {
		int result=0;
		
		try {
	fileManager.doFileDelete(filename, pathname);
			
			result=dao.updateInformation("member.updateImage", userId);
		} catch (Exception e) {
		}
		return result;
	}

	

	@Override
	public List<Member> listMember(Map<String, Object> map) {
	
		return null;
	}
   
	@Override
	public int insertAuthority(Member dto) {
		int result=0;
		try {
			result=dao.insertInformation("member.insertAuthority", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int updateAuthority(Member dto) {
		int result=0;
		try {
			result=dao.updateInformation("member.updateAuthority", dto);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Member readAuthority(int num) {
		Member dto=null;
		try {
			dto=dao.getReadInformation("member.readAuthority", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public List<Member> listAuthority(String userId) {
		List<Member> list = null;
		
		try {
			list=dao.getListInformation("member.listAuthority", userId);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int deleteAuthority(Map<String, Object> map) {
		int result = 0;
		
		try {
			result=dao.deleteInformation("member.deleteAuthority", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	
	@Override
	public int dataCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

           	@Override
	public int readCheckId(String userId) {
		int result=0;
		try {
			int userIdx=Integer.parseInt(userId);
			System.out.println(userIdx);
			result=dao.getIntValue("member.readCheckId", userIdx);
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
		return result;
	}
        	@Override
        	public List<Member>listAddr(Map<String, Object> map) {
        		List<Member> list = null;
        		try {
        			list=dao.getListInformation("member.searchAddr", map);
        		} catch (Exception e) {
        		  System.out.println(e.toString());
        		}
        		return list;
        	}




	
}
