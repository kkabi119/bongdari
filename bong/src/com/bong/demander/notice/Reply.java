package com.bong.demander.notice;

public class Reply {
	private int replyNum, num, listNum, demander_seq, serviceNOTICEIDX;
	private String userId, userName, content, created;
	private int answer ,userIdx;
	private int answerCount;
	
	
	public int getServiceNOTICEIDX() {
		return serviceNOTICEIDX;
	}
	public void setServiceNOTICEIDX(int serviceNOTICEIDX) {
		this.serviceNOTICEIDX = serviceNOTICEIDX;
	}
	public int getDemander_seq() {
		return demander_seq;
	}
	public void setDemander_seq(int demander_seq) {
		this.demander_seq = demander_seq;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

}
