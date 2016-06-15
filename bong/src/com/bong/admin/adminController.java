package com.bong.admin;

import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;

@Controller("bong.adminController")
public class adminController {
	
	@Autowired
	private AdminService aService;
	
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
		
		dataCount = aService.memberCount(map);
		if(dataCount != 0)
			total_page = myutil.pageCount(numPerPage, dataCount);
		
		if(total_page < current_page)
			current_page=total_page;
		
		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);
		
		List<Member> list = aService.listMember(map);
		
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
	
	// 동아리 관리 리스트 출력
	@RequestMapping(value="/admin/club")
	public ModelAndView adminClub(
			HttpServletRequest req,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		String cp=req.getContextPath();
		
		int numPerPage = 10;
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equals("GET")){
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		
		dataCount = aService.clubCount(map);
		if(dataCount != 0)
			total_page = myutil.pageCount(numPerPage, dataCount);
		
		if(total_page < current_page)
			current_page = total_page;
		
		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);
		
		List<Club> list = aService.listClub(map);
		
		int listNum, n=0;
		
		Iterator<Club> it=list.iterator();
		
		while(it.hasNext()){
			Club data = it.next();
			listNum = dataCount - (start + n - 1);
			data.setListNum(listNum);
			n++;
		}
		
		String params="";
		String urlList = cp+"/admin/club";
		if(searchValue.length()!=0){
			params = "searchKey=" + searchKey +
						"&searchValue="+URLEncoder.encode(searchValue, "utf-8");
		}
		
		if(params.length()!=0){
			urlList = cp+"/admin/club?" + params;
		}
		
		ModelAndView mav = new ModelAndView(".admin4.main.club");
		mav.addObject("list", list);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", myutil.paging(current_page, total_page, urlList));
		return mav;
	}
	
	// 수요처 목록 및 승인페이지
	@RequestMapping(value="/admin/demander")
	public ModelAndView adminDemander(
			HttpServletRequest req,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue,
			@RequestParam(value="switching") int switching
			) throws Exception {
		String cp=req.getContextPath();
		
		int numPerPage = 10;
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")){
			searchValue = URLDecoder.decode(searchValue, "utf-8");					
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		
		// 수요처 목록 또는 승인페이지 구분하는 변수를 넣어줌 0 또는 1
		// service테이블의 isService로 구분된다.
		map.put("switching", switching);
		
		dataCount = aService.demanderCount(map);
		if(dataCount !=0)
			total_page = myutil.pageCount(numPerPage, dataCount);
		
		if(total_page < current_page)
			current_page = total_page;
		
		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);
		
		List<Demander> list = aService.listDemander(map);
		
		String params = "";
		String urlList = cp+"/admin/demander";
		if(searchValue.length()!=0){
			params = "searchKey=" + searchKey +
					"&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		if(params.length()!=0){
			urlList = cp+"/admin/demander?" + params;
		}
		
		// 수요처 목록 리스트 uri
		String selectUri = ".admin4.main.demander";
		
		// 수요처 승인으로 갈 경우 uri를 바꿔줌
		if(switching == 0)
			selectUri = ".admin4.main.approval";
		
		ModelAndView mav = new ModelAndView(selectUri);
		mav.addObject("switching", switching);
		mav.addObject("list", list);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", myutil.paging(current_page, total_page, urlList));
		return mav;
	}
	
	@RequestMapping(value="/admin/approvalDetail")
	@ResponseBody
	public ModelAndView approvalDetail(
			@RequestParam(value="serviceIdx") String serviceIdx
			) throws Exception {
		Demander dto = aService.demanderArticle(serviceIdx);
		ModelAndView mav = new ModelAndView("/admin/main/approvalDetail");
		mav.addObject("dto", dto);
		return mav;
	}
	
	// 수요처 승인하기를 눌렀을 때
	@RequestMapping(value="/admin/approvalDetailOk")
	@ResponseBody
	public String approvalDetailOk(
			@RequestParam(value="serviceIdx") String serviceIdx
			) throws Exception {
		String flag="OK";
		aService.createDemanderTable(serviceIdx);
		return flag;
	}
	
	@RequestMapping(value="/admin/appDetail")
	public ModelAndView adminAppDetail() throws Exception {
		ModelAndView mav = new ModelAndView(".admin4.main.appDetail");
		return mav;
	}
	
	@RequestMapping(value="/admin/cal")
	public ModelAndView adminCal() throws Exception {
		
		ModelAndView mav = new ModelAndView(".admin4.calendar.calendar");
		return mav;
	}
	
	@RequestMapping(value="/admin/clubDetail")
	@ResponseBody
	public ModelAndView clubDetail(
			@RequestParam(value="clubIdx") int clubIdx
			) throws Exception {
		Club dto = aService.clubArticle(clubIdx);
		ModelAndView mav = new ModelAndView("/admin/main/clubDetail");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value="/admin/demanderDetail")
	@ResponseBody
	public ModelAndView demanderDetail(
			@RequestParam(value="serviceIdx") String serviceIdx
			) throws Exception {
		Demander dto = aService.demanderArticle(serviceIdx);
		ModelAndView mav = new ModelAndView("/admin/main/demanderDetail");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value="/admin/memberDetail")
	@ResponseBody
	public ModelAndView memberDetail(
			@RequestParam(value="userIdx") int userIdx
			) throws Exception {
		Member dto = aService.memberArticle(userIdx);
		ModelAndView mav = new ModelAndView("/admin/main/memberDetail");
		mav.addObject("dto", dto);
		return mav;
	}
	
}
