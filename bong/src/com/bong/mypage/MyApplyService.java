package com.bong.mypage;

import java.util.List;
import java.util.Map;



public interface MyApplyService {
	
	public List<MyApply> myApplyList(Map<String, Object> map, String option);
	public int dataCount(Map<String, Object> map, String option);
	public MyApply readMyApply(int num);

}
