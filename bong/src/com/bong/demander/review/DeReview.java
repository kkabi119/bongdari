package com.bong.demander.review;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class DeReview {
	private int serviceReviewIdx,serviceFileIdx,listNum;
    private int userIdx,serviceIdx;
    private String demanderName;
    private String userName, userId, subject, content, created;
    private int hitCount;
    private int replyCount;
    private int likeCount;
	private int reviewLikeCount;
	private int demander_seq;
	
	
	// 스프링에서 파일 받기
	private List<MultipartFile> upload; // <input type='file' name='upload' ..
    private int fileNum;
	private String saveFilename;
	private String originalFilename;
	private long fileSize;
	
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getDemanderName() {
		return demanderName;
	}
	public void setDemanderName(String demanderName) {
		this.demanderName = demanderName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
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
	public int getServiceIdx() {
		return serviceIdx;
	}
	public void setServiceIdx(int serviceIdx) {
		this.serviceIdx = serviceIdx;
	}
	public int getServiceReviewIdx() {
		return serviceReviewIdx;
	}
	public void setServiceReviewIdx(int serviceReviewIdx) {
		this.serviceReviewIdx = serviceReviewIdx;
	}

	public int getReviewLikeCount() {
		return reviewLikeCount;
	}
	public void setReviewLikeCount(int reviewLikeCount) {
		this.reviewLikeCount = reviewLikeCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public List<MultipartFile> getUpload() {
		return upload;
	}
	public void setUpload(List<MultipartFile> upload) {
		this.upload = upload;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getServiceFileIdx() {
		return serviceFileIdx;
	}
	public void setServiceFileIdx(int serviceFileIdx) {
		this.serviceFileIdx = serviceFileIdx;
	}
	public int getDemander_seq() {
		return demander_seq;
	}
	public void setDemander_seq(int demander_seq) {
		this.demander_seq = demander_seq;
	}

	
	
	
}
