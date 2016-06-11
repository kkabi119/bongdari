package com.bong.demander;

public class Demander {

	//프로필 관련
	private int serviceIdx;
	private int listNum;
	private String serviceName,serviceBirth,serviceTel;
	private String serviceIntro,serviceAddr,serviceMail;
	private String serviceImg,serviceImgName;
	private String themeName; //theme테이블과 조인해서 name가져오기
	
	
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
