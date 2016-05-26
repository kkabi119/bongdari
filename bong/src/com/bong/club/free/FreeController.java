package com.bong.club.free;

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
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;

@Controller("club.freeController")
public class FreeController {
	
	@Autowired
	private FreeService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	/*���ε��Ƹ� �����Խ���*/
	@RequestMapping(value="/club/{clubSeq}/free/list")
	public ModelAndView ListClubFree(
			HttpServletRequest req,
			@PathVariable int clubSeq,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		
		
		String cp=req.getContextPath();
		
		int numPerPage   = 10;  // �� ȭ�鿡 �����ִ� �Խù� ��
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET ����� ���
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		 // ��ü ������ ��
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchKey", searchKey);
        map.put("searchValue", searchValue);
        map.put("clubSeq", clubSeq);
        
        dataCount = service.dataCount(map);
        if(dataCount != 0)
            total_page = myUtil.pageCount(numPerPage,  dataCount) ;
		
        // �ٸ� ����� �ڷḦ �����Ͽ� ��ü ���������� ��ȭ �� ���
        if(total_page < current_page) 
            current_page = total_page;

        // ����Ʈ�� ����� �����͸� ��������
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);
        
     // �� ����Ʈ
        List<Free> list = service.listFree(map);
        
     // ����Ʈ�� ��ȣ
        int listNum, n = 0;
        Iterator<Free> it=list.iterator();
        while(it.hasNext()) {
            Free data = it.next();
            listNum = dataCount - (start + n - 1);
            data.setListNum(listNum);
            n++;
        } String params = "";
        String urlList = cp+"/club/"+clubSeq+"/free/list";
        String urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page;
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlList = cp+"/club/"+clubSeq+"/free/list?" + params;
            urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page + "&"+ params;
        }
        
        ModelAndView mav = new ModelAndView(".four.club.dari.free.list.�����Խ���");
		
        mav.addObject("list", list);
        mav.addObject("clubSeq",clubSeq);
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
        
		return mav;
	}
	
	/* �����Խ��� �۾��� �� */
	@RequestMapping(value="/club/{clubSeq}/free/created",method=RequestMethod.GET)
	public ModelAndView insertFreeForm(
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.created.�����۾���");
		mav.addObject("mode", "created");
		mav.addObject("clubSeq", clubSeq);
		return mav;
	}
	
	
	/* �����Խ��� �۾��� �� */
	@RequestMapping(value="/club/{clubSeq}/free/created",method=RequestMethod.POST)
	public ModelAndView insertClubFree(
			HttpSession session,
			@PathVariable int clubSeq,
			Free dto
			) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"free";
		
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		dto.setClubIdx(clubSeq);
		service.insertFree(dto, pathname);
		return new ModelAndView("redirect:/club/{clubSeq}/free/list");
	}
	
	
	/*���ε��Ƹ� �����Խ��� ��*/
	
	@RequestMapping(value="/club/{clubSeq}/free/article")
	public ModelAndView readClubFree(HttpSession session,
			@PathVariable int clubSeq,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}

		searchValue = URLDecoder.decode(searchValue, "utf-8");
		
		// ��ȸ�� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		service.updateHitCount(map);

		// �ش� ���ڵ� ���� ����
		Free dto = service.readFree(map);

		if(dto==null)
			return new ModelAndView("redirect:.club.{clubSeq}.free.list?page="+page);
        
		// ���� ��, ���� ��
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);

		Free preReadDto = service.preReadFree(map);
		Free nextReadDto = service.nextReadFree(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		int replyCount=service.replyDataCount(map);
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.�����Խ��� �ۺ���");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);

		mav.addObject("page", page);
		mav.addObject("params", params);
		mav.addObject("replyCount",replyCount);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/free/update", 
			method=RequestMethod.GET)
	public ModelAndView updateClubFree(
			HttpSession session,
			@PathVariable int clubSeq,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		Free dto = (Free) service.readFree(map);
		
		if(dto==null) {
			return new ModelAndView("redirect:/free/list?page="+page);
		}
			
		if(! info.getUserId().equals(dto.getUserId())) {
			return new ModelAndView("redirect:/free/list?page="+page);
		}
		
		ModelAndView mav=new ModelAndView(".four.club.dari.free.created.�����ۼ���");
		mav.addObject("dto", dto);
		mav.addObject("clubSeq",clubSeq);
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/free/update", 
			method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session,	
			Free dto, 
			@PathVariable int clubSeq,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "free";		
	
		// ���� �ϱ�
		dto.setClubIdx(clubSeq);
		service.updateFree(dto, path);
		
		return new ModelAndView("redirect:/club/"+clubSeq+"/free/list?page="+page);
	}
	
	@RequestMapping(value="/club/{clubSeq}/free/delete")
	public ModelAndView delete(
			HttpSession session,
			@PathVariable int clubSeq,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		// �ش� ���ڵ� ���� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		Free dto = service.readFree(map);
		if(dto==null) {
			return new ModelAndView("redirect:/club/{clubSeq}/free/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/club/{clubSeq}/free/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "free";		
 	
		service.deleteFree(map, dto.getSaveFilename(), path);
		
		return new ModelAndView("redirect:/club/{clubSeq}/free/list?page="+page);
	}
}
