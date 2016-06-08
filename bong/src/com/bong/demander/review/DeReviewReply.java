package com.bong.demander.review;

public class DeReviewReply {
	//replyNum 에 srridx(serviceReviewReplyIndex 값이 들어간다 )
	private int replyNum, serviceReviewIdx, listNum;
	private String userId, userName, content, created;
	private int answer,userIdx;
	private int replyLike;
	
	private int likeCount;
	private int answerCount;
	
	private int demander_seq;

	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getReplyLike() {
		return replyLike;
	}
	public void setReplyLike(int replyLike) {
		this.replyLike = replyLike;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getDemander_seq() {
		return demander_seq;
	}
	public void setDemander_seq(int demander_seq) {
		this.demander_seq = demander_seq;
	}
	public int getServiceReviewIdx() {
		return serviceReviewIdx;
	}
	public void setServiceReviewIdx(int serviceReviewIdx) {
		this.serviceReviewIdx = serviceReviewIdx;
	}

	
	
	
}
