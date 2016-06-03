package com.bong.club;

import java.util.List;
import java.util.Map;

public interface ClubService {
	public int insertClub(ClubInfo dto, String pathname);
	
	public List<ClubTheme> listClubThemeGroup();
	public List<ClubTheme> listClubTheme(int groupNum);
	public List<ClubTheme> listClubThemeAll();
	
	public int ReadClubInfoSession(Map<String, Object> map);
	
	public int updateClub(ClubInfo dto, String pathname);
	public int deleteImage(int clubSeq, String pathname, String filename);
	
	public int deleteClub(int clubSeq, String pathname);
	
	public int dataCountClub(Map<String, Object> map);
	public List<ClubInfo> listClub(Map<String, Object> map);

	public ClubInfo readClubInfo(Map<String, Object> map);
	public ClubInfo readClubInfoSmall(Map<String, Object> map);
	public ClubInfo readClubInfoHome(int clubSeq);
	
	public int updateClubVisitorCount(int clubSeq);

	public int createClubTable(int clubSeq);
	public int dropClubTable(int clubSeq);
}
