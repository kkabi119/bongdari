package com.bong.club;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.FileManager;
import com.bong.common.dao.bongDAO;

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
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()) {
				String filename=fileManager.doFileUpload(
						dto.getUpload(), pathname);
				dto.setPhotoFilename(filename);
			}
			
			int seq=dao.getIntValue("club.clubseq");
			dto.setClubSeq(seq);
			
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
			if(dto.getUpload()!=null && !dto.getUpload().isEmpty()) {
				if(dto.getPhotoFilename().length()!=0) {
					fileManager.doFileDelete(dto.getPhotoFilename(), pathname);
				}
				
				String filename=fileManager.doFileUpload(dto.getUpload(), pathname);
				dto.setPhotoFilename(filename);
			}			
			
			dao.updateInformation("club.updateclubInfo", dto);
			dao.updateInformation("club.updateClubProfile", dto);
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
			
			dao.deleteInformation("club.deleteClubProfile", clubSeq);
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
	public ClubInfo readClubInfoProfile(int clubSeq) {
		ClubInfo dto=null;
		try {
			dto=dao.getReadInformation("club.readClubInfoProfile", clubSeq);
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
			/*dao.updateInformation("club.createBoardCategoryTable", clubSeq);
			dao.updateInformation("club.createBoardTable", clubSeq);
			dao.updateInformation("club.createBoardLikeTable", clubSeq);
			dao.updateInformation("club.createBoardFileTable", clubSeq);
			dao.updateInformation("club.createBoardReplyTable", clubSeq);
			dao.updateInformation("club.createBoardReplyLikeTable", clubSeq);
			dao.updateInformation("club.createGuestTable", clubSeq);
			dao.updateInformation("club.createPhotoTable", clubSeq);*/
			
			// 공지 테이블 추가
			/*Category dto=new Category();
			dto.setCategoryNum(1);
			dto.setClassify("공지");
			dto.setTableName("b_"+clubSeq);
			dao.insertInformation("boardCategory.insertCategory", dto);
			*/
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
			/*dao.updateInformation("club.dropBoardReplyLikeTable", clubSeq);
			dao.updateInformation("club.dropBoardReplyTable", clubSeq);
			dao.updateInformation("club.dropBoardFileTable", clubSeq);
			dao.updateInformation("club.dropBoardLikeTable", clubSeq);
			dao.updateInformation("club.dropBoardTable", clubSeq);
			dao.updateInformation("club.dropBoardCategoryTable", clubSeq);
			dao.updateInformation("club.dropGuestTable", clubSeq);
			dao.updateInformation("club.dropPhotoTable", clubSeq);*/
			result=1;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
}
