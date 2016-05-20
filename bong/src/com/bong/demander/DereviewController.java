package com.bong.demander;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;



@Controller("bong.dereviewController")
public class DereviewController {
	@Autowired
	private DeReviewService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/demander/index/review/list")
	public ModelAndView deReviewList(
			HttpServletRequest req,
			@RequestParam(value="page",defaultValue="1")int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			
			) throws Exception {
		String cp = req.getContextPath();
	   	    
			int numPerPage = 5;  // �� ȭ�鿡 �����ִ� �Խù� ��
			int total_page = 0;
			int dataCount = 0;
	   	    
			if(req.getMethod().equalsIgnoreCase("GET")) { // GET ����� ���
				searchValue = URLDecoder.decode(searchValue, "utf-8");
			}
			
	        // ��ü ������ ��
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("searchKey", searchKey);
	        map.put("searchValue", searchValue);

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
	        List<DeReview> list = service.listDeReview(map);

	        // ����Ʈ�� ��ȣ
	        int listNum, n = 0;
	        Iterator<DeReview> it=list.iterator();
	        while(it.hasNext()) {
	        	DeReview data = it.next();
	            listNum = dataCount - (start + n - 1);
	            data.setListNum(listNum);
	            n++;
	        }
	        
	        String params = "";
	        String urlList = cp+"/demander/index/review/list";
	        String urlArticle = cp+"/demander/index/review/article?page=" + current_page;
	        if(searchValue.length()!=0) {
	        	params = "searchKey=" +searchKey + 
	        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
	        }
	        
	        if(params.length()!=0) {
	            urlList = cp+"/demander/index/review/list?" + params;
	            urlArticle = cp+"/demander/index/review/article?page=" + current_page + "&"+ params;
	        }

	        ModelAndView mav=new ModelAndView(".four.demander.dari.review.list.�ı�Խ���");
	        
	        mav.addObject("list", list);
	        mav.addObject("urlArticle", urlArticle);
	        mav.addObject("page", current_page);
	        mav.addObject("dataCount", dataCount);
	        mav.addObject("total_page", total_page);
	        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));	
	        
		
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review/create",method=RequestMethod.GET)
	public ModelAndView deRevCreateForm(
			HttpSession session
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/");
		}*/
		
		ModelAndView mav=new ModelAndView(".four.demander.dari.review.create.�ı�Խ���");
		mav.addObject("mode", "created");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/review/create",method=RequestMethod.POST)
	public ModelAndView deRevCreateSubmit(
			HttpSession session,
			DeReview dto
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		
		//dto.setUserId(info.getUserId());
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		
		service.insertDeReview(dto, path);
		
		
		
		return new ModelAndView("redirect:/demander/index/review/list");
		
	}
	
	
	@RequestMapping(value="/demander/index/review/article")
	public ModelAndView deReArticle(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") int page,
			@RequestParam(value="searchKey",defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue",defaultValue="") String searchValue
			) throws Exception {
		
	
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		//�˻��� decode
		searchValue= URLDecoder.decode(searchValue,"utf-8");
		
		//��ȸ������
		service.updateHitCount(num);
		
		//�ش��ƼŬ��������
		DeReview dto=service.readDeReview(num);
		if(dto==null)
			return new ModelAndView("redirect:/");
			//return new ModelAndView("redirect:.demander.index.review.list?page="+page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("num", num);
		DeReview preReadDto = service.preReadDeReview(map);
		DeReview nextReadDto = service.nextReadDeReview(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		ModelAndView mav=new ModelAndView(".four.demander.dari.review.article.�ı�Խ���");
		
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		mav.addObject("page", page);
		mav.addObject("params", params);
        return mav;
	}
	
	//�ٿ�ε�
	@RequestMapping(value="/demander/index/review/download")
	public void deReviewDownload(
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session,
			@RequestParam(value="num") int num
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			resp.sendRedirect(cp+"/member/login");
			return;
		}*/
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		DeReview dto=service.readDeReview(num);
		boolean flag=false;
		
		if(dto!=null) {
			flag=fileManager.doFileDownload(
					     dto.getSaveFilename(), 
					     dto.getOriginalFilename(), path, resp);
		}
		
		if(! flag) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			out.print("<script>alert('���� �ٿ�ε尡 �����߽��ϴ�.');history.back();</script>");
		}
	}
	
	@RequestMapping(value = "demander/index/review/update", method=RequestMethod.GET)
	public ModelAndView deRevUpdateForm(HttpSession session,
			@RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page
			) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");
		

		DeReview dto = service.readDeReview(num);
		/*if (dto == null) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}*/
	
		if (info.getUserIdx()!=dto.getUserIdx())
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);

		ModelAndView mav=new ModelAndView(".four.demander.dari.review.create.�ı�Խ���");
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		mav.addObject("dto", dto);
		return mav;
	}

	@RequestMapping(value = "demander/index/review/update", method=RequestMethod.POST)
	public String deRevUpdateSubmit(HttpSession session, 
			DeReview dto,
			@RequestParam(value = "page") String page) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		/*
		if (info == null) {
			return "redirect:/member/login";
		}*/
		
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
				
		// ���� �ϱ�
		service.updateDeReview(dto, path);
		
		return "redirect:/demander/index/review/list?page="+page;
	}

	@RequestMapping(value="/demander/index/review/deleteFile", 
			method=RequestMethod.GET)
	public ModelAndView deleteFile(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		DeReview dto = service.readDeReview(num);
		if(dto==null) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}
			
		if(! info.getUserId().equals(dto.getUserId())) {
			return new ModelAndView("redirect:.four.demander.dari.review.list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
		
		if(dto.getSaveFilename() != null && dto.getSaveFilename().length()!=0) {
			  fileManager.doFileDelete(dto.getSaveFilename(), path);
			  
			  dto.setSaveFilename("");
			  dto.setOriginalFilename("");
			  service.updateDeReview(dto, path);
       }
		
		return new ModelAndView("redirect:/demander/index/review/update?num="+num+"&page="+page);
	}
	

	@RequestMapping(value="/demander/index/review/delete")
	public ModelAndView delete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		// �ش� ���ڵ� ���� ����
		DeReview dto = service.readDeReview(num);
		if(dto==null) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/demander/index/review/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "notice";		
 	
		service.deleteDeReview(num, dto.getSaveFilename(), path);
		
		return new ModelAndView("redirect:/demander/index/review/list?page="+page);
	}
	
	//���ƿ�
	@RequestMapping(value="/demander/index/review/sendLike",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deReviewLike(
			HttpSession session,
			DeReview dto) throws Exception {
	
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		int state=service.stateDeRevLike(dto);
		if(state==0){
			dto.setUserIdx(info.getUserIdx());
			service.insertDeReviewLike(dto);
		}else if(state==1){
			dto.setUserIdx(info.getUserIdx());
			service.deleteDeReviewLike(dto);
		}
		
   	    // �۾� ����� json���� ����
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		return model;
	}
	
	// ���ƿ�/�Ⱦ�� ����
	@RequestMapping(value="/demander/index/review/countLike",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  countLike(
			@RequestParam(value="serviceReviewIdx") int num) throws Exception {
		System.out.println("asd");
		int likeCount=0;
		Map<String, Object> map=service.deRevCountLike(num);
		if(map!=null) {
			// resultType�� map�� ��� int�� BigDecimal�� �Ѿ��
			likeCount=((BigDecimal)map.get("LIKECOUNT")).intValue();
		}
		
   	    // �۾� ����� json���� ����
		Map<String, Object> model = new HashMap<>(); 
		model.put("likeCount", likeCount);
		
		System.out.println("controller_likeCount:"+likeCount);
		return model;
	}
	
	
}
