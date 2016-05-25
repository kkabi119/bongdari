package com.bong.mypage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.common.dao.bongDAO;

@Service("mypage.myApplyService")
public class MyApplyServiceImpl implements MyApplyService{
    
	@Autowired
	private bongDAO dao;
	
	//나의 봉사 신청  현황 리스트
	@Override
	public List<MyApply> myApplyList(Map<String, Object> map, String option) {
		List<MyApply> list= null;
		String mode=""; 
	    if(option.equals("myApplyList")) // 전체보기일때
			mode="myApplyList";
		else if(option.equals("myClubList")) // 동아리 보기일때
			mode="myClubList"; 
		else if(option.equals("myIndividualList")) // 개인 보기일때
			mode="myIndividualList";
		try {
			list=dao.getListInformation("member."+mode, map); //mode에 따라서 나눔.
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
   // 리스트 글 개수 
	@Override
	public int dataCount(Map<String, Object> map, String option) {
		int result=0;
		String mode=""; 
        if(option.equals("myApplyList")) // 전체보기일때
			mode="dataCount";    // 전체 글 개수 
		else if(option.equals("myClubList")) // 동아리 보기일떄
			mode="clubDataCount";   // 동아리 신청한 리스트 글 개수 
		else if(option.equals("myIndividualList")) // 개인 일때
			mode="individualDataCount";  //개인으로 신청한 리스트 글 개수 
		try {
		  result=dao.getIntValue("member."+mode, map);
		} catch (Exception e) {
		   System.out.println(e.toString());
		}
		return result;
	}

	@Override
	public MyApply readMyApply(int num) {
        MyApply dto = null;
        try {
			//게시물 가져오기
        	System.out.println("");
        	dto=dao.getReadInformation("mypage.readMyApply", num);
        	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
}
