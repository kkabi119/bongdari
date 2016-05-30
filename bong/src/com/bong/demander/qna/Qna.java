package com.bong.demander.qna;

public class Qna {
	private int sqnaIdx,userIdx;
    private String userId,userName;
	private String subject;
    private String content;
    private int hitCount;
	
    
    
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
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public int getSqnaIdx() {
		return sqnaIdx;
	}
	public void setSqnaIdx(int sqnaIdx) {
		this.sqnaIdx = sqnaIdx;
	}
    
    
}
