package com.bong.club;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;
import com.bong.member.Member;

@Service("club.clubService")
public class ClubServiceImpl  implements ClubService {
	
	@Autowired
	private bongDAO dao;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public int insertClub(ClubInfo dto, String pathname) {
		int result=0;
		try {
			if(dto.getUploads()!=null && !dto.getUploads().isEmpty()) {
				String filename=fileManager.doFileUpload(
						dto.getUploads(), pathname);
				dto.setPhotoFilename(filename);
			}
			
			int seq=	dto.getClubSeq();
			
			dao.insertInformation("club.insertClub", seq);
			dao.insertInformation("club.insertClubInfo", dto);
			dao.insertInformation("club.insertJoinClub", dto);
			
			createClubTable(seq);
			
			result=1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<ClubTheme> listClubThemeGroup() {
		List<ClubTheme> list=null;
		try {
			list=dao.getListInformation("club.listClubThemeGroup");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public List<ClubTheme> listClubTheme(int themeNum) {
		List<ClubTheme> list=null;
		try {
			list=dao.getListInformation("club.listClubTheme", themeNum);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public List<ClubTheme> listClubThemeAll() {
		List<ClubTheme> list=null;
		try {
			list=dao.getListInformation("club.listClubThemeAll");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int updateClub(ClubInfo dto, String pathname) {
		int result=0;
		try {
			String filename=fileManager.doFileUpload(dto.getUploads(), pathname);
			dto.setPhotoFilename(filename);
			
			dao.updateInformation("club.updateClubInfo", dto);
			result=1;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int deleteImage(int clubSeq, String pathname, String filename) {
		int result=0;
		try {
			fileManager.doFileDelete(filename, pathname);
			
			result=dao.updateInformation("club.deleteClubProfileImage", clubSeq);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public int deleteClub(int clubSeq, String pathname) {
		int result=0;
		try {
			// 개인 블로그 파일 폴더 및 파일 삭제  
			fileManager.removePathname(pathname);
			
			dao.deleteInformation("club.deleteClubInfo", clubSeq);
			
			// 테이블 지우기
			dropClubTable(clubSeq);
			
			result=1;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int dataCountClub(Map<String, Object> map) {
			int result=0;
			try {
				result=dao.getIntValue("club.dataCountClub", map);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return result;
	}

	@Override
	public List<ClubInfo> listClub(Map<String, Object> map) {
		List<ClubInfo> list=null;
		try {
			list=dao.getListInformation("club.listClub", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public ClubInfo readClubInfo(Map<String, Object> map) {
		ClubInfo dto=null;
		try {
			dto=dao.getReadInformation("club.readClubInfo", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public ClubInfo readClubInfoSmall(Map<String, Object> map) {
		ClubInfo dto=null;
		try {
			dto=dao.getReadInformation("club.readClubInfoSmall", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	
	@Override
	public ClubInfo readClubInfoHome(int clubSeq) {
		ClubInfo dto=null;
		try {
			dto=dao.getReadInformation("club.readClubInfoHome", clubSeq);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int updateClubVisitorCount(int clubSeq) {
		int result=0;
		try {
			result=dao.updateInformation("club.updateClubVisitorCount", clubSeq);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int createClubTable(int clubSeq) {
		int result=0;
		try {
			dao.updateInformation("club.createClubNotice", clubSeq);
			dao.updateInformation("club.createClubNoticeReply", clubSeq);
			dao.updateInformation("club.createClubFree", clubSeq);
			dao.updateInformation("club.createClubFreeReply", clubSeq);
			dao.updateInformation("club.createClubCalendar", clubSeq);
			dao.updateInformation("club.createClubCalendarSeq", clubSeq);
			dao.updateInformation("club.createClubReview", clubSeq);
			dao.updateInformation("club.createClubReviewR", clubSeq);
			dao.updateInformation("club.createClubReviewRLike", clubSeq);
			dao.updateInformation("club.createClubReviewFile", clubSeq);
			dao.updateInformation("club.createClubReviewLike", clubSeq);
			dao.updateInformation("club.createClubApply", clubSeq);
			dao.updateInformation("club.createClubApplyR", clubSeq);
			dao.updateInformation("club.createClubApplyMemList", clubSeq);
			dao.updateInformation("club.createClubApplyRLike", clubSeq);
			
			result=1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int dropClubTable(int clubSeq) {
		int result=0;
		try {
			dao.updateInformation("club.dropClubNoticeReply", clubSeq);
			dao.updateInformation("club.dropClubNotice", clubSeq);
			dao.updateInformation("club.dropClubFreeReply", clubSeq);
			dao.updateInformation("club.dropClubFree", clubSeq);
			dao.updateInformation("club.dropClubCalendar", clubSeq);
			dao.updateInformation("club.dropClubCalendarSeq", clubSeq);
			dao.updateInformation("club.dropClubReviewRLike", clubSeq);
			dao.updateInformation("club.dropClubReviewFile", clubSeq);
			dao.updateInformation("club.dropClubReviewLike", clubSeq);
			dao.updateInformation("club.dropClubReviewR", clubSeq);
			dao.updateInformation("club.dropClubApplyRLike", clubSeq);
			dao.updateInformation("club.dropClubReview", clubSeq);
			dao.updateInformation("club.dropClubApplyMemList", clubSeq);
			dao.updateInformation("club.dropClubApplyR", clubSeq);
			dao.updateInformation("club.dropClubApply", clubSeq);
			result=1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public int ReadClubInfoSession(Map<String, Object> map) {
		int result=0;
		String value=null;
		
		try {
			value=dao.getReadInformation("club.readClubInfoSession", map);
			if(value==null)
				result=0;
			else
				result=Integer.parseInt(value);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public int ReadSeqVal() {
		int result=0;
		try {
			result=dao.getIntValue("club.readSeqVal");
			result=result+1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public JoinClub joinClubEnabled(Map<String, Object> map) {
		
		JoinClub dto=null;
		try {
			dto=dao.getReadInformation("club.joinClubEnabled", map);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int JoinApply(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.insertInformation("club.joinApply", map);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public String readAuthority(Map<String, Object> map) {
		String result="";
		try {
			result=dao.getReadInformation("club.readAuthority", map);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public List<Member> joinClubList(Map<String, Object> map) {
		List<Member> list=null;
		try {
			//게시물 가져오기
			list=dao.getListInformation("club.joinClubList", map);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}

	@Override
	public int joinClubOk(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.updateInformation("club.joinClubOk", map);
			System.out.println("성공 result="+result);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}

	@Override
	public List<ClubInfo> readJoinedClub(int userIdx) {
		List<ClubInfo> list=null;
		try {
			list=dao.getListInformation("club.readJoinedClub", userIdx);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	
	
	
}
