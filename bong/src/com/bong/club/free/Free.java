package com.bong.club.free;

import org.springframework.web.multipart.MultipartFile;

public class Free {
	private int clubFreeIdx, userIdx, clubIdx,hitCount, listNum, replyDataCount, rnum;
	private String subject, content, created, modified, userId, userName, listImageName;
	private MultipartFile upload; 
	
	private int clubFreePIdx;
	private String saveFilename, originalFilename;
	
	public String getListImageName() {
		return listImageName;
	}
	public void setListImageName(String listImageName) {
		this.listImageName = listImageName;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getReplyDataCount() {
		return replyDataCount;
	}
	public void setReplyDataCount(int replyDataCount) {
		this.replyDataCount = replyDataCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getClubFreePIdx() {
		return clubFreePIdx;
	}
	public void setClubFreePIdx(int clubFreePIdx) {
		this.clubFreePIdx = clubFreePIdx;
	}
	public String getSaveFilename() {
		return saveFilename;
	}
	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public int getClubFreeIdx() {
		return clubFreeIdx;
	}
	public void setClubFreeIdx(int clubFreeIdx) {
		this.clubFreeIdx = clubFreeIdx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getClubIdx() {
		return clubIdx;
	}
	public void setClubIdx(int clubIdx) {
		this.clubIdx = clubIdx;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	
	
}
