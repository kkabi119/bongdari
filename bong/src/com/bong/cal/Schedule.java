package com.bong.cal;

import org.springframework.web.multipart.MultipartFile;

public class Schedule {
	private int num;

	private int userIdx, serviceIdx, volunIdx;
	private String userId, userName;
	private String title;
	private String content;
	private String allDay;
	
	private String start, startDay;
	private String end, endDay;
	private String startTime;
	private String endTime;
	private String color;
	
	private String created;
	
	private int themeNum;
	private String volunteer_type, saveFilename, originalFilename, place, eachDayArray, eachDayValueArray, maxAll;
	private String eachDay, eachDayValue, dow, id, subject, sSubject, lSubject, volunDays, serviceName, hitCount, progress;

	private MultipartFile upload;
	
	
	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}

	public int getVolunIdx() {
		return volunIdx;
	}

	public void setVolunIdx(int volunIdx) {
		this.volunIdx = volunIdx;
	}

	public String getVolunDays() {
		return volunDays;
	}

	public void setVolunDays(String volunDays) {
		this.volunDays = volunDays;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getHitCount() {
		return hitCount;
	}

	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getsSubject() {
		return sSubject;
	}

	public void setsSubject(String sSubject) {
		this.sSubject = sSubject;
	}

	public String getlSubject() {
		return lSubject;
	}

	public void setlSubject(String lSubject) {
		this.lSubject = lSubject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}

	public String getEachDay() {
		return eachDay;
	}

	public void setEachDay(String eachDay) {
		this.eachDay = eachDay;
	}

	public String getEachDayValue() {
		return eachDayValue;
	}

	public void setEachDayValue(String eachDayValue) {
		this.eachDayValue = eachDayValue;
	}

	public String getMaxAll() {
		return maxAll;
	}

	public void setMaxAll(String maxAll) {
		this.maxAll = maxAll;
	}

	public String getEachDayArray() {
		return eachDayArray;
	}

	public void setEachDayArray(String eachDayArray) {
		this.eachDayArray = eachDayArray;
	}

	public String getEachDayValueArray() {
		return eachDayValueArray;
	}

	public void setEachDayValueArray(String eachDayValueArray) {
		this.eachDayValueArray = eachDayValueArray;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public int getThemeNum() {
		return themeNum;
	}

	public void setThemeNum(int themeNum) {
		this.themeNum = themeNum;
	}

	public String getVolunteer_type() {
		return volunteer_type;
	}

	public void setVolunteer_type(String volunteer_type) {
		this.volunteer_type = volunteer_type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAllDay() {
		return allDay;
	}

	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}


	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
}
