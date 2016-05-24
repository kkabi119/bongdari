package com.bong.club.post;

public class Category {
	private int categoryNum;
	private String classify, groupClassify;
	private int closed, parent, themeNum;
	private String themeSubject;
	private String tableName;
	
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getGroupClassify() {
		return groupClassify;
	}
	public void setGroupClassify(String groupClassify) {
		this.groupClassify = groupClassify;
	}
	public int getClosed() {
		return closed;
	}
	public void setClosed(int closed) {
		this.closed = closed;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getThemeNum() {
		return themeNum;
	}
	public void setThemeNum(int themeNum) {
		this.themeNum = themeNum;
	}
	public String getThemeSubject() {
		return themeSubject;
	}
	public void setThemeSubject(String themeSubject) {
		this.themeSubject = themeSubject;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
