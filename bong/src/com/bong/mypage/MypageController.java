package com.bong.mypage;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
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
import com.bong.member.Member;
import com.bong.member.MemberService;
import com.bong.member.SessionInfo;

@Controller("mypage.mypageController")
public class MypageController {
	@Autowired
	private MemberService service; //가입, 수정, 탈퇴 등에 사용할 서비스(맴버)
	@Autowired
	private MyApplyService service2; // 신청 리스트 현황에 사용할 서비스
	@Autowired
	private MyUtil myUtil;
	//내정보 보기 
	@RequestMapping(value="/member/index/myPage", method=RequestMethod.GET)
	public ModelAndView myPage(
			 HttpSession session
			) throws Exception{
		ModelAndView mav= new ModelAndView(".layout.mypage.mypageMain.내정보 보기");
		return mav;
	}
	
	@RequestMapping(value="/member/index/myInfo")
	public ModelAndView myInfo(
			 HttpSession session
			) throws Exception{
		SessionInfo info = (SessionInfo)session.getAttribute("member"); //세션에 있는 멤버 정보 가져오기
        
		Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
		//userIdx 를 통해서 db에 있는 데이터 가져오기.
		
		ModelAndView mav= new ModelAndView("mypage/myInfo");
		mav.addObject("dto", dto);
		return mav;
	}

	
	//정보 수정
@RequestMapping(value="/member/index/updateInfo", method=RequestMethod.GET)
	public ModelAndView updateInfo(
			HttpSession session
			
			) throws Exception {
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		//세션에 있는 멤버 정보 가져오기
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		//db에 있는 데이터 가져오기(userIdx를 통해서) mapper에 있는 쿼리 실행
		Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
	/*	if(dto==null) {
			session.invalidate();
			return new ModelAndView("redirect:/");
		}*/
		//service.updateMember2(dto,pathname); //serviceImpl불르기	
		// 회원정보수정폼
	    ModelAndView mav=new ModelAndView("/mypage/updateInfo");
	    mav.addObject("mode", "update");
	    mav.addObject("dto", dto);
	    return mav;
	}

	@RequestMapping(value="/member/index/updateInfo", method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session
           ,Member dto){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		service.updateMember2(dto, pathname);
		
		ModelAndView mav=new ModelAndView(".layout.member.complete.회원정보수정");
		
		StringBuffer sb=new StringBuffer();
		sb.append(dto.getUserName()+ "님의 회원정보가 정상적으로 변경되었습니다.<br>");
		sb.append("메인화면으로 이동 하시기 바랍니다.<br>");
		
		mav.addObject("message", sb.toString());
		
		return mav;
	}
	// 나의봉사신청 현황 리스트
	@RequestMapping(value="/member/index/myApply")
	public ModelAndView MyApply(
			HttpServletRequest req
		   ,@RequestParam(value="page",defaultValue="1") int current_page
		   ,@RequestParam(value="searchKey", defaultValue="subject") String searchKey
		   ,@RequestParam(value="searchValue", defaultValue="") String searchValue
		   ,@RequestParam(value="option", defaultValue="myApplyList") String optionC // 전체보기, 개인, 동아리 별 
		   ,HttpSession session
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		int numPerPage = 10; //한페이지당 보여줄 게시물의 개수
		int total_page;  //전체 페이지
		int dataCount;  // 글 개수
		
		if(req.getMethod().equalsIgnoreCase("GET")){ //get 방식인경우
			searchValue = URLDecoder.decode(searchValue,"utf-8");
		}
		//전체 페이지수
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("userIdx", Integer.toString(info.getUserIdx()));
		
		dataCount = service2.dataCount(map, optionC); //글 개수(MyApplyServiceImpl의 dataCount)
		total_page= myUtil.pageCount(numPerPage, dataCount);
		
		if(total_page < current_page)
			current_page = total_page;
		//리스트에 출력할 데이터 가져오기
		int start = (current_page -1) * numPerPage +1;
		int end = current_page * numPerPage;
		
		map.put("start", start);
		map.put("end", end);
		
		// 글 리스트
		List<MyApply> list = service2.myApplyList(map, optionC);
		// 리스트 번호
		int listNum, n=0;
		Iterator<MyApply> it= list.iterator();
		
		while(it.hasNext()){
			MyApply data = it.next();
			listNum = dataCount - (start+n-1);
			data.setListNum(listNum);
			n++;
		}
		String params = "";
		String urlList = cp+"/member/index/myPage"+params;
		String urlArticle = cp +"/member/index/myPage?page"+current_page;
		
       //검색
		if(searchValue.length()!=0){
			params = "searchKey="+searchKey+
					"&searchValue="+URLEncoder.encode(searchValue,"utf-8");
		}
		if(params.length()!=0){
			urlList =cp+"/member/index/myPage"+params;
		    urlArticle += "&" + params;
		}
		ModelAndView mav= new ModelAndView("/mypage/myApply");
		mav.addObject("list", list);
		mav.addObject("urlArticle", urlArticle);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("total_page",total_page);
		mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
		return mav;
	}
	//이미지 삭제
	@RequestMapping(value="/member/index/imageDelete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> imageDelete(
			HttpSession session,
			@RequestParam String filename
			) throws Exception{
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		boolean isLogin=true;
		boolean state=false;
		
		if(info==null){
			isLogin=false;
		} else {
			String root=session.getServletContext().getRealPath("/");
			String pathname=root+File.separator+"uploads"+
			File.separator+"memImg";
			service.deleteImage(info.getUserId(), pathname, filename);
			state=true;
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("isLogin", isLogin);
		model.put("state", state);
		
		return model;
		
	}
	//회원탈퇴
	@RequestMapping(value="/member/index/deleteMember", method=RequestMethod.GET)
	public ModelAndView deleteCheck(
			 HttpServletRequest req
			,HttpSession session
			) {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}

		ModelAndView mav=new ModelAndView("/mypage/deleteMember");
		//mav.addObject("userId", info.getUserId());
		return mav;
	}
	//회원탈퇴폼
    @RequestMapping(value="/mypage/deleteMember", method=RequestMethod.POST)
	public ModelAndView deleteMember (
			HttpSession session
		   ,@RequestParam(value="userPwd") String userPwd
			) throws Exception {
           SessionInfo info=(SessionInfo)session.getAttribute("member"); //세션에 있는 정보 가져오기
           if(info==null){
        	   return new ModelAndView("redirect:/member/login");
           }
           //readMemberInfo에 있는 데이터들 dto로 가져오기
           Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
           
           if(dto==null){
        	   session.invalidate();
        	   return new ModelAndView("redirect:/");
           }
           //탈퇴하기전에 비밀번호 확인 하게 해주기
           if(! dto.getUserPwd().equals(userPwd)){
        	   ModelAndView mav=new ModelAndView(".layout.mypage.deleteMember.회원탈퇴");
        	   mav.addObject("message", "패스워드가 일치하지않습니다");
        	   return mav;
           }
           //동아리 개설한 동아리 장들은 탈퇴 못하도록 막기
          String check=service.managerCheck(info.getUserId());
           if(check!=null){
        	   return new ModelAndView("redirect:/");
           }
   
           Map<String, Object> map = new HashMap<String, Object>();
           map.put("userIdx", info.getUserIdx());
           service.deleteMember2(map);
           
           session.removeAttribute("member");
           session.invalidate();
           
          
           
           ModelAndView mav=new ModelAndView(".layout.member.complete.회원탈퇴");
			
			StringBuffer sb=new StringBuffer();
			sb.append(dto.getUserName()+ "님의 회원 탈퇴 처리가 정상적으로 처리되었습니다.<br>");
			sb.append("메인화면으로 이동 하시기 바랍니다.<br>");
			
			
			mav.addObject("message", sb.toString());
           
           return mav;
	}
}	
