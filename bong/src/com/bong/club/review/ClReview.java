package com.bong.club.review;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ClReview {
	private int clubReviewIdx,clubFileIdx,listNum;
    private int userIdx,clubIdx;
    private String demanderName;
    private String userName, userId, subject, content, created;
    private int hitCount;
    private int replyCount;
    private int likeCount;
	private int reviewLikeCount;
	private int clubSeq;


	// 스프링에서 파일 받기
	private List<MultipartFile> upload; // <input type='file' name='upload' ..
    private int fileNum;
	private String saveFilename;
	private String originalFilename;
	private long fileSize;
	private String listImageName;
	
	public String getListImageName() {
		return listImageName;
	}
	public void setListImageName(String listImageName) {
		this.listImageName = listImageName;
	}
	public int getClubReviewIdx() {
		return clubReviewIdx;
	}
	public void setClubReviewIdx(int clubReviewIdx) {
		this.clubReviewIdx = clubReviewIdx;
	}
	public int getClubFileIdx() {
		return clubFileIdx;
	}
	public void setClubFileIdx(int clubFileIdx) {
		this.clubFileIdx = clubFileIdx;
	}
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
	public int getClubIdx() {
		return clubIdx;
	}
	public void setClubIdx(int clubIdx) {
		this.clubIdx = clubIdx;
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
	public int getReviewLikeCount() {
		return reviewLikeCount;
	}
	public void setReviewLikeCount(int reviewLikeCount) {
		this.reviewLikeCount = reviewLikeCount;
	}

	
	public int getClubSeq() {
		return clubSeq;
	}
	public void setClubSeq(int clubSeq) {
		this.clubSeq = clubSeq;
	}
	public List<MultipartFile> getUpload() {
		return upload;
	}
	public void setUpload(List<MultipartFile> upload) {
		this.upload = upload;
	}
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
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
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
}
