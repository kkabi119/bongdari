package com.bong.demander.admin;

public class Dadmin {

	//프로필 관련
	private int serviceIdx, clubIdx, enabled, clubApplyIdx;
	private int listNum;
	private String serviceName,serviceBirth,serviceTel;
	private String serviceIntro,serviceAddr,serviceMail;
	private String serviceImg,serviceImgName;
	private String themeName; //theme테이블과 조인해서 name가져오기
	
	private int volunIdx, userIdx;
	private String subject, content, startDay, endDay, startTime, endTime,
	place, progress, volunteer_type, volunDays, clubApplyTable, clubName, memImg, memimgname;
	
	private String userName, userBirth, userAddr, userGender, userTel, userEmail, userJob, clubBirth, clubAddr, userId, introduce, themeNum, created_date, saveFilename;
	
	
	
	public String getSaveFilename() {
		return saveFilename;
	}
	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}
	public int getClubIdx() {
		return clubIdx;
	}
	public void setClubIdx(int clubIdx) {
		this.clubIdx = clubIdx;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getClubApplyIdx() {
		return clubApplyIdx;
	}
	public void setClubApplyIdx(int clubApplyIdx) {
		this.clubApplyIdx = clubApplyIdx;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getClubApplyTable() {
		return clubApplyTable;
	}
	public void setClubApplyTable(String clubApplyTable) {
		this.clubApplyTable = clubApplyTable;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getMemImg() {
		return memImg;
	}
	public void setMemImg(String memImg) {
		this.memImg = memImg;
	}
	public String getMemimgname() {
		return memimgname;
	}
	public void setMemimgname(String memimgname) {
		this.memimgname = memimgname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public String getClubBirth() {
		return clubBirth;
	}
	public void setClubBirth(String clubBirth) {
		this.clubBirth = clubBirth;
	}
	public String getClubAddr() {
		return clubAddr;
	}
	public void setClubAddr(String clubAddr) {
		this.clubAddr = clubAddr;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getThemeNum() {
		return themeNum;
	}
	public void setThemeNum(String themeNum) {
		this.themeNum = themeNum;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public int getVolunIdx() {
		return volunIdx;
	}
	public void setVolunIdx(int volunIdx) {
		this.volunIdx = volunIdx;
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getVolunteer_type() {
		return volunteer_type;
	}
	public void setVolunteer_type(String volunteer_type) {
		this.volunteer_type = volunteer_type;
	}
	public String getVolunDays() {
		return volunDays;
	}
	public void setVolunDays(String volunDays) {
		this.volunDays = volunDays;
	}
	public int getServiceIdx() {
		return serviceIdx;
	}
	public void setServiceIdx(int serviceIdx) {
		this.serviceIdx = serviceIdx;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceBirth() {
		return serviceBirth;
	}
	public void setServiceBirth(String serviceBirth) {
		this.serviceBirth = serviceBirth;
	}
	public String getServiceTel() {
		return serviceTel;
	}
	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}
	public String getServiceIntro() {
		return serviceIntro;
	}
	public void setServiceIntro(String serviceIntro) {
		this.serviceIntro = serviceIntro;
	}
	public String getServiceAddr() {
		return serviceAddr;
	}
	public void setServiceAddr(String serviceAddr) {
		this.serviceAddr = serviceAddr;
	}
	public String getServiceMail() {
		return serviceMail;
	}
	public void setServiceMail(String serviceMail) {
		this.serviceMail = serviceMail;
	}
	public String getServiceImg() {
		return serviceImg;
	}
	public void setServiceImg(String serviceImg) {
		this.serviceImg = serviceImg;
	}
	public String getServiceImgName() {
		return serviceImgName;
	}
	public void setServiceImgName(String serviceImgName) {
		this.serviceImgName = serviceImgName;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	
	
}
