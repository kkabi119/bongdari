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
	@Override
	public List<MyApply> myApplyList(Map<String, Object> map) {
		List<MyApply> list= null;
		
		try {
			list=dao.getListInformation("member.myApplyList", map);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result=0;
		
		try {
		  result=dao.getIntValue("member.dataCount", map);	
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
