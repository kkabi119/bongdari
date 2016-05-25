package com.bong.club;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;

@Controller("club.clubContoller")
public class ClubController {
	
	@Autowired
	private ClubService service;
	
	@Autowired
	private MyUtil util;
	
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

		dataCount = service.dataCountClub(map);
		if (dataCount != 0)
			total_page = util.pageCount(numPerPage, dataCount);

		if (total_page < current_page)
			current_page = total_page;

		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);

		List<ClubInfo> listBlog=service.listClub(map);
		
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
		ClubInfo clubInfo=service.readClubInfo(map);
		if(clubInfo!=null){
			ModelAndView mav = new ModelAndView(".layout.club.manage.message.�޼���");
			mav.addObject("message", "���Ƹ��� ������ �ϳ��� ����� �ֽ��ϴ�.");
			return mav;
		}
		List<ClubTheme> listGroup=service.listClubThemeGroup();

		ModelAndView mav = new ModelAndView(".layout.club.manage.clubCreated.���Ƹ� �����");
		ClubInfo dto = new ClubInfo();
		dto.setIsUserName(1);
		dto.setGender("����");
		dto.setClosed(0);
		dto.setIsCity(1);
		dto.setIsGender(1);
		dto.setIsHobby(1);

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
		String pathname=root+File.separator+"uploads"+File.separator+"club"+
				File.separator+info.getUserId();

		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		int result=service.insertClub(dto, pathname);
		
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
		
		List<ClubTheme> listTheme = service.listClubTheme(groupNum);

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
		ClubInfo clubInfo=service.readClubInfo(map);
		
		// ���Ƹ��� ������ ����
		if(clubInfo==null)
			return new ModelAndView("redirect:/club/created");
		
		// �� ���Ƹ��� �̵�
		return new ModelAndView("redirect:/club/"+clubInfo.getClubSeq()+"/main");
	}
	
}
