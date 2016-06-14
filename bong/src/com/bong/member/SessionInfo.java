package com.bong.member;

import org.springframework.web.multipart.MultipartFile;

public class SessionInfo {
	private int userIdx;
	private String userId;
	private String userName, clubname;
	private int clubIdx, isService, demander_seq;
	private int memberLevel;
	private String memImgname;
	private MultipartFile memImg;
	
	
	public String getClubname() {
		return clubname;
	}
	public void setClubname(String clubname) {
		this.clubname = clubname;
	}
	public int getDemander_seq() {
		return demander_seq;
	}
	public void setDemander_seq(int demander_seq) {
		this.demander_seq = demander_seq;
	}
	public int getIsService() {
		return isService;
	}
	public void setIsService(int isService) {
		this.isService = isService;
	}
	public int getClubIdx() {
		return clubIdx;
	}
	public void setClubIdx(int clubIdx) {
		this.clubIdx = clubIdx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getMemImgname() {
		return memImgname;
	}
	public void setMemImgname(String memImgname) {
		this.memImgname = memImgname;
	}
	public MultipartFile getMemImg() {
		return memImg;
	}
	public void setMemImg(MultipartFile memImg) {
		this.memImg = memImg;
	}
	
}
