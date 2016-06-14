package com.bong.demander.admin;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.demanderjoin.Demanderjoin;
import com.bong.demanderjoin.DemanderjoinService;
import com.bong.member.SessionInfo;


@Controller("bong.demanderAdminController")
public class DadminController {
	
	@Autowired
	private DemanderjoinService service;
	
	@Autowired
	private DadminService adminService;
	
	
	@Autowired
	private MyUtil myUtil;
	
	
	@RequestMapping(value="/demander/{demander_seq}/admin/admin")
	public ModelAndView demanderadmin(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.admin.관리자페이지");
		
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/bookmark")
	public ModelAndView deadminbmark(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.bookmark.관심등록");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/volList")
	public ModelAndView volList(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.volList.나눔복지관");
		return mav;
	}
	
	// 수요처 봉사 승인 및 평가 리스트
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1")
	public ModelAndView tab1(
			HttpServletRequest req,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue,
			@PathVariable int demander_seq
			) throws Exception {
		String cp = req.getContextPath();
		
		int numPerPage = 10;
		int total_page = 0;
		int dataCount = 0;
		if(req.getMethod().equalsIgnoreCase("GET")){
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("demander_seq", demander_seq);
		
		dataCount = adminService.dataCount(map);
		if(dataCount != 0)
			total_page = myUtil.pageCount(numPerPage, dataCount);
		
		if(total_page < current_page)
			current_page = total_page;
		
		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);
		List<Dadmin> list = adminService.AdminVolunList(map);
		
		String params = "";
		String urlList = cp+"/demander/"+demander_seq+"/admin/tab1";
		String urlArticle = cp+"/demander"+demander_seq+"/admin/article?page="+current_page;
		if(searchValue.length()!=0){
			params = "searchKey="+searchKey+ "&searchValue="+URLEncoder.encode(searchValue, "utf-8");
		}
		ModelAndView mav = new ModelAndView("/demander/dari/admin/volList");
		
		mav.addObject("list", list);
		mav.addObject("demander_seq", demander_seq);
		mav.addObject("urlArticle", urlArticle);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab2")
	public ModelAndView tab2(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1/showList")
	public ModelAndView showList(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/appliedMembers");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1/eval")
	public ModelAndView eval(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	   @RequestMapping(value="/demander/{demander_seq}/admin/tab3", method=RequestMethod.GET)
	   public ModelAndView tab3(
	         HttpSession session,
	         @PathVariable int demander_seq
	         ) throws Exception {
	      String root=session.getServletContext().getRealPath("/");
	      String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
	      //세션에 있는 수요처 정보 가져오기
	      SessionInfo info=(SessionInfo)session.getAttribute("member");

	      if(info==null){
	         return new ModelAndView("redirect:/member/login");
	      }
	      
	      Demanderjoin dto=service.readDemanderjoinInfo(Integer.toString(info.getUserIdx()));
	      if(dto==null){
	         session.invalidate();
	         return new ModelAndView("redirect:/");
	      }

	      //수정폼
	   ModelAndView mav = new ModelAndView("/demander/dari/admin/demanderUpdate");
	       mav.addObject("mode", "update");
	      mav.addObject("dto", dto);
	      return mav;
	   }
	   
	   @RequestMapping(value="/demander/{demander_seq}/admin/tab3", method=RequestMethod.POST)
	   @ResponseBody
	   public ModelAndView demanderUpdateSubmit(
	         HttpSession session
	         ,Demanderjoin dto,
	         @PathVariable int demander_seq
	         ){
	      String root=session.getServletContext().getRealPath("/");
	      String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
	      
	      SessionInfo info=(SessionInfo)session.getAttribute("member");
	      dto.setUserIdx(info.getUserIdx());
	      service.updateDemander2(dto, pathname);
	      
	    ModelAndView mav=new ModelAndView(".layout.demanderjoin.complete.회원정보수정");
		
		StringBuffer sb=new StringBuffer();
		sb.append(dto.getServiceName()+ "님의 회원정보가 정상적으로 변경되었습니다.<br>");
		sb.append("메인화면으로 이동 하시기 바랍니다.<br>");
		
		mav.addObject("message", sb.toString());
		
	       
	      return mav;
	   }
	
	@RequestMapping(value="/demander/admin/eachInfo")
	@ResponseBody
	public ModelAndView eachInfo(
			@RequestParam(value="volunIdx") int volunIdx
			) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("volunIdx", volunIdx);
		List<Dadmin> list = adminService.searchTable();
		List<Dadmin> volunList = new ArrayList<>();
		for(Dadmin name : list){
			map.put("clubApplyTable", name.getClubApplyTable());
			if(adminService.clubApplyCount(map)!=0)
			{
				name = adminService.AdminClubVolun(map);
				volunList.add(name);
			}
		}
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eachInfo");
		mav.addObject("volunList", volunList);
		return mav;
	}
	
	@RequestMapping(value="/demander/admin/eachClubMember")
	@ResponseBody
	public ModelAndView eachClubMember(
			@RequestParam(value="clubApplyIdx") int clubApplyIdx,
			@RequestParam(value="volunIdx") int volunIdx,
			@RequestParam(value="clubIdx") int clubIdx
			) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("clubApplyIdx", clubApplyIdx);
		map.put("volunIdx", volunIdx);
		map.put("clubIdx", clubIdx);
		List<Dadmin> list = null;
		list = adminService.clubMemberList(map);
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eachClubMember");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping(value="/demander/admin/clubInfoView")
	@ResponseBody
	public ModelAndView clubInfoView(
			@RequestParam(value="clubIdx") int clubIdx
			) throws Exception{
		Dadmin dto = adminService.clubInfoView(clubIdx);
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/clubInfoView");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value="/demander/admin/memberInfoView")
	@ResponseBody
	public ModelAndView memberInfoView(
			@RequestParam(value="userIdx") int userIdx
			) throws Exception{
		Dadmin dto = adminService.memberInfoView(userIdx);
		ModelAndView mav = new ModelAndView("/demander/dari/admin/memberInfoView");
		mav.addObject("dto", dto);
		return mav;
		
	}
}