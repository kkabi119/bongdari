package com.bong.club;

import org.springframework.web.multipart.MultipartFile;

public class ClubInfo {
	private int clubSeq;
	private String userId, nickName, userName;
	private int groupNum, themeNum, userIdx;
	private String introduce, clubname, city,  clubBirth;
	private String groupSubject, subject;
	private int  closed;
	private MultipartFile upload;
	private String photoFilename;
	private int isUserName, isCity;
	
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getClubBirth() {
		return clubBirth;
	}
	public void setClubBirth(String clubBirth) {
		this.clubBirth = clubBirth;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIsUserName() {
		return isUserName;
	}
	public void setIsUserName(int isUserName) {
		this.isUserName = isUserName;
	}
	public int getIsCity() {
		return isCity;
	}
	public void setIsCity(int isCity) {
		this.isCity = isCity;
	}
	public int getClubSeq() {
		return clubSeq;
	}
	public void setClubSeq(int clubSeq) {
		this.clubSeq = clubSeq;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public int getThemeNum() {
		return themeNum;
	}
	public void setThemeNum(int themeNum) {
		this.themeNum = themeNum;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getClubname() {
		return clubname;
	}
	public void setClubname(String clubname) {
		this.clubname = clubname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGroupSubject() {
		return groupSubject;
	}
	public void setGroupSubject(String groupSubject) {
		this.groupSubject = groupSubject;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getClosed() {
		return closed;
	}
	public void setClosed(int closed) {
		this.closed = closed;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getPhotoFilename() {
		return photoFilename;
	}
	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}
	
	
	
	
}
