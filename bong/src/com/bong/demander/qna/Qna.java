package com.bong.demander.qna;

public class Qna {
	private int listNum,sqnaIdx,userIdx; //sqnaIdx °¡ boardNum
    private String userId,userName;
	private String subject,created;
    private String content;
    private int hitCount;
    private int answer;
    private int groupNum;
    private int quserIdx;
    private int demander_seq;
	
    
	public int getDemander_seq() {
		return demander_seq;
	}
	public void setDemander_seq(int demander_seq) {
		this.demander_seq = demander_seq;
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
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public int getQuserIdx() {
		return quserIdx;
	}
	public void setQuserIdx(int quserIdx) {
		this.quserIdx = quserIdx;
	}
	
	
    
}
