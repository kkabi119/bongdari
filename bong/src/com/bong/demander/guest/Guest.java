package com.bong.demander.guest;

public class Guest {
	private int listNum,serviceIdx,userIdx, demander_seq, sguestIdx;
	private String userName, content, created;
	
	
	public int getSguestIdx() {
		return sguestIdx;
	}
	public void setSguestIdx(int sguestIdx) {
		this.sguestIdx = sguestIdx;
	}
	public int getDemander_seq() {
		return demander_seq;
	}
	public void setDemander_seq(int demander_seq) {
		this.demander_seq = demander_seq;
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
	public int getServiceIdx() {
		return serviceIdx;
	}
	public void setServiceIdx(int serviceIdx) {
		this.serviceIdx = serviceIdx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
