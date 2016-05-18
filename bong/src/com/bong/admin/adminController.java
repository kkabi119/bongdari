package com.bong.admin;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;

@Controller("bong.adminController")
public class adminController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private MyUtil myutil;
	
	@RequestMapping(value="/admin")
	public ModelAndView adminMain() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4Layout");
		return mav;
	}
	
	// 회원 관리 리스트 출력하기
	@RequestMapping(value="/admin/member")
	public ModelAndView adminMember(
			HttpServletRequest req,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="userId") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		String cp = req.getContextPath();
		System.out.println(current_page);
		int numPerPage = 10;
		int total_page = 0;
		int dataCount = 0;
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		
		dataCount = mService.dataCount(map);
		if(dataCount != 0)
			total_page = myutil.pageCount(numPerPage, dataCount);
		
		if(total_page < current_page)
			current_page=total_page;
		
		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);
		
		List<Member> list = mService.listMember(map);
		
		int listNum, n = 0;
		Iterator<Member> it = list.iterator();
		while(it.hasNext()){
			Member data = it.next();
			listNum = dataCount - (start + n - 1);
			data.setListNum(listNum);
			n++;
		}
		
		String params = "";
		String urlList = cp+"/admin/member";
		String urlMember = cp+"/memberInfo?page=" + current_page;
		if(searchValue.length()!=0){
			params = "searchKey=" + searchKey+
					"&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		if(params.length()!=0){
			urlList = cp+"/admin/member?" + params;
			urlMember = cp+"/memberInfo?page=" + current_page + "&" + params;
		}
			
		ModelAndView mav = new ModelAndView(".admin4.main.member");
		mav.addObject("list", list);
		mav.addObject("urlMember", urlMember);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", myutil.paging(current_page, total_page, urlList));
		return mav;
	}
	
	@RequestMapping(value="/admin/club")
	public ModelAndView adminClub() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.main.club");
		return mav;
	}
	
	@RequestMapping(value="/admin/demander")
	public ModelAndView adminDemander() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.main.demander");
		return mav;
	}
	
	@RequestMapping(value="/admin/approval")
	public ModelAndView adminApproval() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.main.approval");
		return mav;
	}
	
	@RequestMapping(value="/admin/cal")
	public ModelAndView adminCal() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.calendar.calendar");
		return mav;
	}
	
	
}
