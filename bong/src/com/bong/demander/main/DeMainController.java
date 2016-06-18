package com.bong.demander.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.club.ClubInfo;
import com.bong.demander.admin.Dadmin;
import com.bong.demander.admin.DadminService;
import com.bong.demander.review.DeReview;
import com.bong.demander.review.DeReviewService;
import com.bong.member.SessionInfo;

@Controller("demander.demainController")
public class DeMainController {

	@Autowired
	private DeMainService mainService;
	
	@Autowired
	private DeReviewService reviewService;
	
	@Autowired
	private DadminService adminService;
	
	
	
	@RequestMapping(value="/demander/{demander_seq}/main")
	public ModelAndView demanderMain(
			HttpServletRequest req,
			@PathVariable int demander_seq
			) throws Exception {
		String cp=req.getContextPath();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("demander_seq", demander_seq);
		
		/*수요처 메인 프로필*/
		DeMain mainProfile=mainService.deMainProfile(map);
		
		/*후기small게시판*/
		map.put("start", 1);
		map.put("end", 5);
		
		//메인에 띄울 후기small게시판
		List<DeReview> revList=reviewService.listDeReviewSmall(map);
		//List<DeReview> revPhoto=reviewService.listDeReview(map);
		
		//메인에 띄울 봉사일정small게시판
        List<Dadmin> volunList=adminService.AdminVolunListSmall(map);
		
		
		//메인에 띄울 사진 첫슬라이드
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("demander_seq", demander_seq);
		map1.put("start", 1);
		map1.put("end", 4);
		List<DeReview> revPhoto1=reviewService.listDeReviewSmall(map1);
		
		//메인에 띄울 사진 두번째 슬라이드
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("demander_seq", demander_seq);
		map2.put("start", 5);
		map2.put("end", 8);
		List<DeReview> revPhoto2=reviewService.listDeReviewSmall(map2);
		
	    Pattern pattern=Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		  
	    Matcher match;    
		int n=0;
		Iterator<DeReview> it1=revPhoto1.iterator();
		while(it1.hasNext()){
			DeReview data= it1.next();
			match=pattern.matcher(data.getContent());
	            if(match.find())
	            	data.setListImageName(match.group(1));
			n++;
		}
		
		Iterator<DeReview> it2=revPhoto2.iterator();
		while(it2.hasNext()){
			DeReview data= it2.next();
			match=pattern.matcher(data.getContent());
	            if(match.find())
	            	data.setListImageName(match.group(1));
			n++;
		}
		
		String urlRevList=cp+"/demander/"+demander_seq+"/review/list";
		String urlRevArticle=cp+"/demander/"+demander_seq+"/review/article?page="+1;
		String urlVolList=cp+"/demander/"+demander_seq+"/admin/admin";
	
		
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.main.");
		mav.addObject("mainProfile", mainProfile);
		mav.addObject("demander_seq", demander_seq);
		mav.addObject("revList", revList);
		mav.addObject("volunList", volunList);
		mav.addObject("revPhoto1", revPhoto1);
		mav.addObject("revPhoto2", revPhoto2);
		mav.addObject("urlRevList", urlRevList);
		mav.addObject("urlVolList", urlVolList);
		mav.addObject("urlRevArticle", urlRevArticle);
		return mav;
	}
	
	/*
	@RequestMapping(value="/demander/{demander_seq}/map")
	@ResponseBody
	public Map<String, Object> mapsLayout(
			HttpSession session,
			@PathVariable int demander_seq
			) throws Exception {
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("demander_seq", demander_seq);
		
		
		return map;
	}
	*/
	
	@RequestMapping(value="/demander/{demander_seq}/left")
	@ResponseBody
	public Map<String, Object> clubLeft(
			HttpSession session,
			@PathVariable int demander_seq
			) throws Exception {
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("demander_seq", demander_seq);
		DeMain serviceInfo=mainService.deMainProfile(map);
		map.put("serviceInfo", serviceInfo);
		
		return map;
	}
}
