package com.bong.free;

import org.springframework.web.multipart.MultipartFile;

public class Free {
	private int clubFreeIdx, userIdx, clubIdx,hitCount;
	private String subject, content, created, modified;
	private MultipartFile upload; 
	
	private int clubFreePIdx;
	private String saveFilename, OriginalFilename;
	
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
		return OriginalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		OriginalFilename = originalFilename;
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
