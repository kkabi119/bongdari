package com.bong.club;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.bong.club.notice.Notice;
import com.bong.club.notice.NoticeService;
import com.bong.common.MyUtil;
import com.bong.member.Member;
import com.bong.member.SessionInfo;

@Controller("club.clubContoller")
public class ClubController {
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MyUtil util;
	
	@RequestMapping(value="/club/{clubSeq}/main")
	public ModelAndView myClubMain(
			HttpServletRequest req,
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		
		String cp=req.getContextPath();
	
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 1);
		map.put("end", 5);
		map.put("clubSeq", clubSeq);
		List<Notice> listN=noticeService.listNoticeSmall(map);
		ClubInfo clubInfo=clubService.readClubInfoSmall(map);
		 // 리스트의 번호
        int listNum, n = 0;
        Iterator<Notice> it=listN.iterator();
        while(it.hasNext()) {
            Notice data = it.next();
            listNum = 5 - (1 + n - 1);
            data.setListNum(listNum);
            n++;
        }
           
        String urlList = cp+"/club/"+clubSeq+"/notice/list";
        String urlArticle = cp+"/club/"+clubSeq+"/notice/article?page="+ 1;
        
		ModelAndView mav = new ModelAndView(".four.club.dari.main.동아리 메인");
		mav.addObject("subMenu", "2");
		mav.addObject("clubSeq", clubSeq);
		mav.addObject("listN", listN);
		mav.addObject("urlList", urlList);
		mav.addObject("urlArticle", urlArticle);
		mav.addObject("clubInfo",clubInfo);
		
		return mav;
	}
	@RequestMapping(value="/club/{clubSeq}/left")
	@ResponseBody
	public Map<String, Object> clubLeft(
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("clubSeq", clubSeq);
		ClubInfo dto=clubService.readClubInfoSmall(map);
	
		//회원권한 가져오기 
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		
		map.put("userIdx",info.getUserIdx());
		String authority=clubService.readAuthority(map);
		System.out.println("authority:"+authority);
		
		map.put("dto", dto);
		map.put("authority", authority);
		return map;
	}
	
	@RequestMapping(value = "/club")
	public ModelAndView main(HttpServletRequest req,
			@RequestParam(value="theme", defaultValue="0") int themeNum,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="query", defaultValue="") String query,
			@RequestParam(value="idx", defaultValue="0") int menu) throws Exception {
		// 동아리 메인, 동아리 리스트
		
		String cp = req.getContextPath();

		int numPerPage = 10;
		int total_page = 0;
		int dataCount = 0;

		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			query = URLDecoder.decode(query, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("query", query);
		map.put("user", "general"); // "general": 일반 사용자, "admin":관리자
		map.put("themeNum", themeNum);

		dataCount = clubService.dataCountClub(map);
		if (dataCount != 0)
			total_page = util.pageCount(numPerPage, dataCount);

		if (total_page < current_page)
			current_page = total_page;

		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);

		List<ClubInfo> listBlog=clubService.listClub(map);
		
		String params = "";
		if (themeNum != 0) {
			params = "theme=" + themeNum + "&";
		}
		params += "idx=" + menu;
		if(query.length()!=0)
			params+="&query="+URLEncoder.encode(query, "UTF-8");

		String urlList = cp + "/club?" + params;

		ModelAndView mav = new ModelAndView(".club.main.clubList");
		mav.addObject("menu", menu);
		mav.addObject("listClub", listBlog);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", util.paging(current_page, total_page, urlList));
		return mav;
	}
	
	
	@RequestMapping(value="/club/created", method=RequestMethod.GET)
	public ModelAndView clubCreatedForm(HttpSession session) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		//동아리 만들기 폼
		
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("field", "userId");
		map.put("field_value", info.getUserId());
		ClubInfo clubInfo=clubService.readClubInfo(map);
		if(clubInfo!=null){
			ModelAndView mav = new ModelAndView(".layout.club.manage.message.메세지");
			mav.addObject("message", "동아리는 계정당 하나만 만들수 있습니다.");
			return mav;
		}
		List<ClubTheme> listGroup=clubService.listClubThemeGroup();

		ModelAndView mav = new ModelAndView(".layout.club.manage.clubCreated.동아리 만들기");
		ClubInfo dto = new ClubInfo();
		dto.setIsUserName(1);
		dto.setClosed(0);

		mav.addObject("mode", "created");
		mav.addObject("dto", dto);
		mav.addObject("listGroup", listGroup);
		return mav;
		
	}
	
	@RequestMapping(value = "/club/created", method = RequestMethod.POST)
	public ModelAndView clubCreatedSubmit(HttpSession session, ClubInfo dto) throws Exception {
		// 내 동아리 등록 완료
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		String root=session.getServletContext().getRealPath("/");
		int clubSeq=clubService.ReadSeqVal();
		String pathname=root+File.separator+"uploads"+File.separator+"club"+File.separator+clubSeq;

		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		dto.setClubSeq(clubSeq);
		int result=clubService.insertClub(dto, pathname);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId",info.getUserId());
		int clubIdx=clubService.ReadClubInfoSession(map);
		info.setClubIdx(clubIdx);
		
		session.setAttribute("member", info);
		
		if(result==0) {
			ModelAndView mav = new ModelAndView(".club.manage.message");
			mav.addObject("message", "동아리를 생성하지 못했습니다. 다시 시도 하시기 바랍니다.");
			return mav;
		}

		return new ModelAndView("redirect:/club/"+dto.getClubSeq()+"/main");
	}
	
	@RequestMapping(value = "/club/themeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> themeList(@RequestParam int groupNum) throws Exception {
		// AJAX(JSON)-동아리 생성 및 수정할 때 그룹별 주제(중분류)
		
		List<ClubTheme> listTheme = clubService.listClubTheme(groupNum);

		Map<String, Object> model = new HashMap<>();
		model.put("listTheme", listTheme);

		return model;
	}
	
	@RequestMapping(value = "/club/me")
	public ModelAndView clubMe(HttpSession session) throws Exception {
		// 내동아리
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}

		Map<String, Object> map=new HashMap<>();
		map.put("field", "userId");
		map.put("field_value", info.getUserId());
		ClubInfo clubInfo=clubService.readClubInfo(map);
		
		// 동아리가 없으면 생성
		if(clubInfo==null)
			return new ModelAndView("redirect:/club/created");
		
		// 내 동아리로 이동
		return new ModelAndView("redirect:/club/"+clubInfo.getClubSeq()+"/main");
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage")
	public ModelAndView clubManage(
			@PathVariable int clubSeq,
			HttpSession session
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.manage.admin.관리자페이지");
		mav.addObject("subMenu","9");
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage/clubInfo")
	public ModelAndView readClubInfo(
			@PathVariable int clubSeq,
			HttpSession session
			) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubSeq", clubSeq);
		ClubInfo clubInfo = clubService.readClubInfoSmall(map);
		
		ModelAndView mav = new ModelAndView("/club/manage/clubInfo");
		mav.addObject("clubSeq", clubSeq);
		mav.addObject("clubInfo", clubInfo);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage/updateClubInfo")
	public ModelAndView updateClubForm(
			@PathVariable int clubSeq,
			HttpSession session
			) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubSeq", clubSeq);
		ClubInfo dto = clubService.readClubInfoSmall(map);
		List<ClubTheme> listGroup=clubService.listClubThemeGroup();
		
		ModelAndView mav=new ModelAndView("/club/manage/clubUpdate");
		mav.addObject("clubSeq", clubSeq);
		mav.addObject("dto", dto);
		mav.addObject("listGroup", listGroup);
		mav.addObject("mode", "update");
		
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage/update", method=RequestMethod.POST)
	public ModelAndView updateClubInfo(
			@PathVariable int clubSeq,
			HttpSession session,
			ClubInfo dto
			) throws Exception{
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"club"+File.separator+clubSeq;
		
		clubService.updateClub(dto, pathname);
		
		ModelAndView mav=new ModelAndView("redirect:/club/"+clubSeq+"/manage");
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage/deleteClub",method=RequestMethod.GET)
	public ModelAndView deleteClubForm(
			@PathVariable int clubSeq,
			HttpSession session
			) throws Exception {
		
		ModelAndView mav=new ModelAndView("/club/manage/deleteClub");
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage/deleteClub",method=RequestMethod.POST)
	public String deleteClubInfo(
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		// 동아리 삭제
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if (info == null) {
			return "redirect:/member/login";
		}

		Map<String, Object> map=new HashMap<>();
		map.put("field", "userId");
		map.put("field_value", info.getUserId());
		ClubInfo dto=clubService.readClubInfo(map);
		if(dto==null || ! dto.getUserId().equals(info.getUserId())) {
			return "redirect:/";
		}		
		if(dto==null|| ! dto.getUserId().equals(info.getUserId())) {
			return "redirect:/";
		}
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"club"+File.separator+File.separator+clubSeq;
		
		clubService.deleteClub(clubSeq, pathname);
		info.setClubIdx(0);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/club/{clubSeq}/joinClub",method=RequestMethod.GET)
	public ModelAndView joinClub(
			@PathVariable int clubSeq
			,HttpSession session)
		throws Exception {		
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		int enabled=0; // 0: 가입가능 / 1: 3개이상가입해서 불가 / 2: 같은 지역이 아님 /3: 이미 가입신청을 했거나 회원인 경우
							// 가입신청을 누르면 해당 userIdx는 joinclub의 authority에 '가입대기'로 insert됨 + enabled가 3으로 바뀐다
		
		ModelAndView mav=new ModelAndView("/club/japply");
		mav.addObject("clubSeq",clubSeq);
		
		//가입가능한 회원인지 확인
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdx", info.getUserIdx());
		map.put("userId", info.getUserId());
		map.put("clubSeq",clubSeq);
		
		JoinClub joclub=clubService.joinClubEnabled(map);
		System.out.println(" getAuthority:"+ joclub.getAuthority()+" joincount:"+ joclub.getJoinCount());
		
		// 3개에 가입햇다면
		if(joclub.getJoinCount() >=3){
			enabled=1; // 가입불가
		}
		
		joclub.setMemAddr("서울"); // 주소를 아직 못짤라서 임시로 이러케 확인하는 걸로 함
		System.out.println(joclub.getMemAddr()+joclub.getClubAddr());
		
		if(! joclub.getMemAddr().equals(joclub.getClubAddr())){
			enabled=2; // 지역이 같지않으므로 가입 불가
		}
		if(joclub.getAuthority()!=null )
		{ //authority의 default값을 비회원/ 가입을 누르면 -승인대기/ 가입 후 일반 으로 변경
			enabled=3; //이미 가입신청한 회원
		}
		
		// 가입신청리스트로 값을 넘기는 mapper
		if(enabled==0){
			clubService.JoinApply(map);		
			System.out.println("가입신청완료");
		}
		mav.addObject("enabled",enabled);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage/joinClubList")
	public ModelAndView joinClubList(
			@PathVariable int clubSeq
			,HttpSession session)
		throws Exception {		
		
		ModelAndView mav=new ModelAndView("/club/manage/joinClubList");
		
		// 해당 레코드 가져 오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubSeq",clubSeq);
		
		List<Member> list = clubService.joinClubList(map);
						
		int n=0; //listnum 
		for(int i=0; i<list.size(); i++) {
			n++;
			list.get(i).setListNum(n);
		}
		
		mav.addObject("list", list);
		mav.addObject("n",n);
		mav.addObject("clubSeq",clubSeq);
				
		return mav;
	}	
}

	
