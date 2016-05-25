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
	
	//���� ���� ��û  ��Ȳ ����Ʈ
	@Override
	public List<MyApply> myApplyList(Map<String, Object> map, String option) {
		List<MyApply> list= null;
		String mode=""; 
	    if(option.equals("myApplyList")) // ��ü�����϶�
			mode="myApplyList";
		else if(option.equals("myClubList")) // ���Ƹ� �����϶�
			mode="myClubList"; 
		else if(option.equals("myIndividualList")) // ���� �����϶�
			mode="myIndividualList";
		try {
			list=dao.getListInformation("member."+mode, map); //mode�� ���� ����.
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
   // ����Ʈ �� ���� 
	@Override
	public int dataCount(Map<String, Object> map, String option) {
		int result=0;
		String mode=""; 
        if(option.equals("myApplyList")) // ��ü�����϶�
			mode="dataCount";    // ��ü �� ���� 
		else if(option.equals("myClubList")) // ���Ƹ� �����ϋ�
			mode="clubDataCount";   // ���Ƹ� ��û�� ����Ʈ �� ���� 
		else if(option.equals("myIndividualList")) // ���� �϶�
			mode="individualDataCount";  //�������� ��û�� ����Ʈ �� ���� 
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
			//�Խù� ��������
        	System.out.println("");
        	dto=dao.getReadInformation("mypage.readMyApply", num);
        	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
}
