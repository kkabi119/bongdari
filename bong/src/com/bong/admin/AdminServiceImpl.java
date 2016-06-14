package com.bong.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("admin.AdminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private bongDAO  dao;
	
	@Override
	public List<Member> listMember(Map<String, Object> map) {
		List<Member> list=null;
		try{
			list=dao.getListInformation("admin.memberList", map);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}

	@Override
	public int memberCount(Map<String, Object> map) {
		int result=0;
		
		try{
			result=dao.getIntValue("admin.memberCount", map);			
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}


	@Override
	public List<Club> listClub(Map<String, Object> map) {
		List<Club> list=null;
		try {
			list=dao.getListInformation("admin.listClub", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int clubCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("admin.clubCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	@Override
	public List<Demander> listDemander(Map<String, Object> map) {
		List<Demander> list=null;
		try {
			list=dao.getListInformation("admin.listDemander", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int demanderCount(Map<String, Object> map) {
		int result=0;
		try {
			result=dao.getIntValue("admin.demanderCount", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public Demander demanderArticle(String serviceIdx) {
		Demander dto=null;
		try {
			dto=dao.getReadInformation("admin.demanderArticle", Integer.parseInt(serviceIdx));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	@Override
	public int createDemanderTable(String serviceIdx) {
		int num = Integer.parseInt(serviceIdx);
		try {
			dao.insertInformation("admin.approvalUpdate", num);
			dao.insertInformation("admin.createServiceNotice", num);
			dao.insertInformation("admin.createServiceNoticeReply", num);
			dao.insertInformation("admin.createServiceReview", num);
			dao.insertInformation("admin.createServiceReviewLike", num);
			dao.insertInformation("admin.createServiceReviewReply", num);
			dao.insertInformation("admin.createServiceReviewFile", num);
			dao.insertInformation("admin.createServiceReviewReplyLike", num);
			dao.insertInformation("admin.createServiceGuest", num);
			dao.insertInformation("admin.createServiceQnA", num);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 1;
	}

	@Override
	public Club clubArticle(int clubIdx) {
		Club dto = null;
		try {
			dto = dao.getReadInformation("admin.clubArticle", clubIdx);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	
}
