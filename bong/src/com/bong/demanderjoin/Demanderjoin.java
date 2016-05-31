package com.bong.demanderjoin;

import org.springframework.web.multipart.MultipartFile;

public class Demanderjoin {
  private String serviceName, serviceBirth, servicePwd;
  private String serviceTel, tel1, tel2, tel3;
  private String serviceAddr, serviceEmail;
  private String email1, email2;
  private String serviceIntro,serviceImgname,serviceImg;
  private MultipartFile uploads;
  private int serviceIdx, isService, userIdx,enabled;
  private String authority;
  private String userId, userPwd;
  
  
  
public MultipartFile getUploads() {
	return uploads;
}
public void setUploads(MultipartFile uploads) {
	this.uploads = uploads;
}
public void setServiceImg(String serviceImg) {
	this.serviceImg = serviceImg;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserPwd() {
	return userPwd;
}
public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
}
public int getUserIdx() {
	return userIdx;
}
public void setUserIdx(int userIdx) {
	this.userIdx = userIdx;
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
public String getServicePwd() {
	return servicePwd;
}
public void setServicePwd(String servicePwd) {
	this.servicePwd = servicePwd;
}
public String getServiceTel() {
	return serviceTel;
}
public void setServiceTel(String serviceTel) {
	this.serviceTel = serviceTel;
}
public String getTel1() {
	return tel1;
}
public void setTel1(String tel1) {
	this.tel1 = tel1;
}
public String getTel2() {
	return tel2;
}
public void setTel2(String tel2) {
	this.tel2 = tel2;
}
public String getTel3() {
	return tel3;
}
public void setTel3(String tel3) {
	this.tel3 = tel3;
}
public String getServiceAddr() {
	return serviceAddr;
}
public void setServiceAddr(String serviceAddr) {
	this.serviceAddr = serviceAddr;
}
public String getServiceEmail() {
	return serviceEmail;
}
public void setServiceEmail(String serviceEmail) {
	this.serviceEmail = serviceEmail;
}
public String getEmail1() {
	return email1;
}
public void setEmail1(String email1) {
	this.email1 = email1;
}
public String getEmail2() {
	return email2;
}
public void setEmail2(String email2) {
	this.email2 = email2;
}
public String getServiceIntro() {
	return serviceIntro;
}
public void setServiceIntro(String serviceIntro) {
	this.serviceIntro = serviceIntro;
}
public String getServiceImgname() {
	return serviceImgname;
}
public void setServiceImgname(String serviceImgname) {
	this.serviceImgname = serviceImgname;
}
public int getServiceIdx() {
	return serviceIdx;
}
public void setServiceIdx(int serviceIdx) {
	this.serviceIdx = serviceIdx;
}
public int getIsService() {
	return isService;
}
public void setIsService(int isService) {
	this.isService = isService;
}
public int getEnabled() {
	return enabled;
}
public void setEnabled(int enabled) {
	this.enabled = enabled;
}
public String getAuthority() {
	return authority;
}
public void setAuthority(String authority) {
	this.authority = authority;
}
  
  
}
