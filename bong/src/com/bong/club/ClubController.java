package com.bong.club;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.club.notice.Notice;
import com.bong.club.notice.NoticeService;
import com.bong.club.review.ClReview;
import com.bong.club.review.ClReviewService;
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
	private ClReviewService reviewService;
	
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
		List<ClReview> listR=reviewService.listReviewSmall(map);
		
		ClubInfo clubInfo=clubService.readClubInfoSmall(map);
		
		 // ��������, ����Ʈ�� ��ȣ
        int listNum1, n1 = 0;
        Iterator<Notice> it1=listN.iterator();
        while(it1.hasNext()) {
            Notice data = it1.next();
            listNum1 = 5 - (1 + n1 - 1);
            data.setListNum(listNum1);
            n1++;
        }
        
     // �ı�, ����Ʈ�� ��ȣ
        int listNum2, n2 = 0;
        Iterator<ClReview> it2=listR.iterator();
        while(it2.hasNext()) {
            ClReview data = it2.next();
            listNum2 = 5 - (1 + n2 - 1);
            data.setListNum(listNum2);
            n2++;
        }
        
        
      //���ο� ��� ���� ù�����̵�
      		Map<String, Object> mapPho1 = new HashMap<String, Object>();
      		mapPho1.put("clubSeq", clubSeq);
      		mapPho1.put("start", 1);
      		mapPho1.put("end", 4);
      		List<ClReview> revPhoto1=reviewService.listReviewSmall(mapPho1);
      		
      		//���ο� ��� ���� �ι�° �����̵�
      		Map<String, Object> mapPho2 = new HashMap<String, Object>();
      		mapPho2.put("clubSeq", clubSeq);
      		mapPho2.put("start", 5);
      		mapPho2.put("end", 8);
      		List<ClReview> revPhoto2=reviewService.listReviewSmall(mapPho2);
      		
      	    Pattern pattern=Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
      		  
      	    Matcher match;    
      	    
      		Iterator<ClReview> it3=revPhoto1.iterator();
      		while(it3.hasNext()){
      			ClReview data= it3.next();
      			match=pattern.matcher(data.getContent());
      	            if(match.find())
      	            	data.setListImageName(match.group(1));
      		}
      		
      		Iterator<ClReview> it4=revPhoto2.iterator();
      		while(it4.hasNext()){
      			ClReview data= it4.next();
      			match=pattern.matcher(data.getContent());
      	            if(match.find())
      	            	data.setListImageName(match.group(1));
      		}
      		
      		
        String urlListN = cp+"/club/"+clubSeq+"/notice/list";
        String urlArticleN = cp+"/club/"+clubSeq+"/notice/article?page="+ 1;
        
        String urlListR = cp+"/club/"+clubSeq+"/review/list";
        String urlArticleR = cp+"/club/"+clubSeq+"/review/article?page="+ 1;
        
        String urlRevList=cp+"/club/"+clubSeq+"/review/list";
		String urlRevArticle=cp+"/club/"+clubSeq+"/review/article?page="+1;
        
		ModelAndView mav = new ModelAndView(".four.club.dari.main.���Ƹ� ����");
		mav.addObject("subMenu", "2");
		mav.addObject("clubSeq", clubSeq);
		mav.addObject("listN", listN);
		mav.addObject("listR", listR);
		mav.addObject("urlListN", urlListN);
		mav.addObject("urlListR", urlListR);
		mav.addObject("urlArticleN", urlArticleN);
		mav.addObject("urlArticleR", urlArticleR);
		mav.addObject("revPhoto1", revPhoto1);
		mav.addObject("revPhoto2", revPhoto2);
		mav.addObject("urlRevList", urlRevList);
		mav.addObject("urlRevArticle", urlRevArticle);
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
	
		//ȸ������ �������� 
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
		// ���Ƹ� ����, ���Ƹ� ����Ʈ
		
		String cp = req.getContextPath();

		int numPerPage = 10;
		int total_page = 0;
		int dataCount = 0;

		if(req.getMethod().equalsIgnoreCase("GET")) { // GET ����� ���
			query = URLDecoder.decode(query, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("query", query);
		map.put("user", "general"); // "general": �Ϲ� �����, "admin":������
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
		//���Ƹ� ����� ��
		
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("field", "userId");
		map.put("field_value", info.getUserId());
		ClubInfo clubInfo=clubService.readClubInfo(map);
		if(clubInfo!=null){
			ModelAndView mav = new ModelAndView(".layout.club.manage.message.�޼���");
			mav.addObject("message", "���Ƹ��� ������ �ϳ��� ����� �ֽ��ϴ�.");
			return mav;
		}
		List<ClubTheme> listGroup=clubService.listClubThemeGroup();

		ModelAndView mav = new ModelAndView(".layout.club.manage.clubCreated.���Ƹ� �����");
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
		// �� ���Ƹ� ��� �Ϸ�
		
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
			mav.addObject("message", "���Ƹ��� �������� ���߽��ϴ�. �ٽ� �õ� �Ͻñ� �ٶ��ϴ�.");
			return mav;
		}

		return new ModelAndView("redirect:/club/"+dto.getClubSeq()+"/main");
	}
	
	@RequestMapping(value = "/club/themeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> themeList(@RequestParam int groupNum) throws Exception {
		// AJAX(JSON)-���Ƹ� ���� �� ������ �� �׷캰 ����(�ߺз�)
		
		List<ClubTheme> listTheme = clubService.listClubTheme(groupNum);

		Map<String, Object> model = new HashMap<>();
		model.put("listTheme", listTheme);

		return model;
	}
	
	@RequestMapping(value = "/club/me")
	public ModelAndView clubMe(HttpSession session) throws Exception {
		// �����Ƹ�
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}

		Map<String, Object> map=new HashMap<>();
		map.put("field", "userId");
		map.put("field_value", info.getUserId());
		ClubInfo clubInfo=clubService.readClubInfo(map);
		
		// ���Ƹ��� ������ ����
		if(clubInfo==null)
			return new ModelAndView("redirect:/club/created");
		
		// �� ���Ƹ��� �̵�
		return new ModelAndView("redirect:/club/"+clubInfo.getClubSeq()+"/main");
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage")
	public ModelAndView clubManage(
			@PathVariable int clubSeq,
			HttpSession session
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.club.manage.admin.������������");
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
		// ���Ƹ� ����
		
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

		int enabled=0; // 0: ���԰��� / 1: 3���̻����ؼ� �Ұ� / 2: ���� ������ �ƴ� /3: �̹� ���Խ�û�� �߰ų� ȸ���� ���
							// ���Խ�û�� ������ �ش� userIdx�� joinclub�� authority�� '���Դ��'�� insert�� + enabled�� 3���� �ٲ��
		
		ModelAndView mav=new ModelAndView("/club/japply");
		mav.addObject("clubSeq",clubSeq);
		
		//���԰����� ȸ������ Ȯ��
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdx", info.getUserIdx());
		map.put("userId", info.getUserId());
		map.put("clubSeq",clubSeq);
		System.out.println("userIdx="+info.getUserIdx());
		
		JoinClub joclub=clubService.joinClubEnabled(map);
		System.out.println(" getAuthority:"+ joclub.getAuthority()+" joincount:"+ joclub.getJoinCount());
		
		// 3���� �����޴ٸ�
		if(joclub.getJoinCount() >=3){
			enabled=1; // ���ԺҰ�
		}
		
		joclub.setMemAddr("����"); // �ּҸ� ���� ��©�� �ӽ÷� �̷��� Ȯ���ϴ� �ɷ� ��
		System.out.println(joclub.getMemAddr()+joclub.getClubAddr());
		
		if(! joclub.getMemAddr().equals(joclub.getClubAddr())){
			enabled=2; // ������ ���������Ƿ� ���� �Ұ�
		}
		if(joclub.getAuthority()!=null )
		{ //authority�� default���� ��ȸ��/ ������ ������ -���δ��/ ���� �� �Ϲ� ���� ����
			enabled=3; //�̹� ���Խ�û�� ȸ��
		}
		
		// ���Խ�û����Ʈ�� ���� �ѱ�� mapper
		if(enabled==0){
			clubService.JoinApply(map);		
			System.out.println("���Խ�û�Ϸ�");
		}
		System.out.println("enabled:"+enabled);
		mav.addObject("enabled",enabled);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/manage/joinClubList")
	public ModelAndView joinClubList(
			@PathVariable int clubSeq
			,HttpSession session)
		throws Exception {		
		
		ModelAndView mav=new ModelAndView("/club/manage/joinClubList");
		//��̸���Ʈ�� �ٲ۴� - where���� in�� �Ἥ 
		// �ش� ���ڵ� ���� ����
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
	
	@RequestMapping(value="/club/{clubSeq}/manage/joinClubOk")
	public ModelAndView joinClubOk(
			@PathVariable int clubSeq
			,HttpSession session
			,@RequestParam(value="selectUser",defaultValue="") String selectUser)
		throws Exception {		
		
		System.out.println(selectUser);

		String[] strs = selectUser.split(",");
  		   
		int[] intArray = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
		     String numberAsString = strs[i];
		     intArray[i] = Integer.parseInt(numberAsString);
		}
		
		 List<Integer> intList = new ArrayList<Integer>();
		      
		  	Map<String, Object> map = new HashMap<String, Object>();
		  	map.put("usridx", strs);
			map.put("clubSeq",clubSeq);
			clubService.joinClubOk(map);
							  
		ModelAndView mav=new ModelAndView("redirect:/club/{clubSeq}/manage/joinClubList");
		
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

	
