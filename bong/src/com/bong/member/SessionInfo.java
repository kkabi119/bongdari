package com.bong.member;

import org.springframework.web.multipart.MultipartFile;

public class SessionInfo {
	private int userIdx;
	private String userId;
	private String userName;
	private int memberLevel;
	private String memImgname;
	private MultipartFile memImg;
	
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
