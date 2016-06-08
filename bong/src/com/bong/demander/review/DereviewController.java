package com.bong.demander.review;

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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/demander/{demander_seq}/review/list")
	public ModelAndView deReviewList(
			HttpServletRequest req,
			@RequestParam(value="page",defaultValue="1")int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue,
			@PathVariable int demander_seq
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
	        map.put("demander_seq",demander_seq);

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
	        String urlList = cp+"/demander/"+demander_seq+"/review/list";
	        String urlArticle = cp+"/demander/"+demander_seq+"/review/article?page=" + current_page;
	        if(searchValue.length()!=0) {
	        	params = "searchKey=" +searchKey + 
	        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
	        }
	        
	        if(params.length()!=0) {
	            urlList = cp+"/demander/"+demander_seq+"/review/list?" + params;
	            urlArticle = cp+"/demander/"+demander_seq+"/review/article?page=" + current_page + "&"+ params;
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
	
	@RequestMapping(value="/demander/{demander_seq}/review/create",method=RequestMethod.GET)
	public ModelAndView deRevCreateForm(
			HttpSession session,
			@PathVariable int demander_seq
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/");
		}*/
		
		ModelAndView mav=new ModelAndView(".four.demander.dari.review.create.�ı�Խ���");
		mav.addObject("mode", "created");
		mav.addObject("demander_seq", demander_seq);
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/review/create",method=RequestMethod.POST)
	public ModelAndView deRevCreateSubmit(
			HttpSession session,
			DeReview dto,
			@PathVariable int demander_seq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review"+File.separator+demander_seq;
		
		//dto.setUserId(info.getUserId());
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		dto.setDemander_seq(demander_seq);
		
		service.insertDeReview(dto, path);
		
		return new ModelAndView("redirect:/demander/{demander_seq}/review/list");
		
	}
	
	
	@RequestMapping(value="/demander/{demander_seq}/review/article")
	public ModelAndView deReArticle(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") int page,
			@RequestParam(value="searchKey",defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue",defaultValue="") String searchValue
			,@PathVariable int demander_seq
			) throws Exception {
		
	
		SessionInfo info=(SessionInfo)session.getAttribute("member");
	
		//�˻��� decode
		searchValue= URLDecoder.decode(searchValue,"utf-8");
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("serviceReviewIdx", num);
		map1.put("demander_seq", demander_seq);
		
		//��ȸ������
		service.updateHitCount(map1);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("demander_seq",demander_seq);
		map.put("serviceReviewIdx", num);
		DeReview preReadDto = service.preReadDeReview(map);
		DeReview nextReadDto = service.nextReadDeReview(map);
		
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("serviceReviewIdx", num);
		map2.put("demander_seq",demander_seq);
		//�ش��ƼŬ��������
		DeReview dto=service.readDeReview(map2);
		List<DeReview> listFile=service.listFile(map2);
		
		if(dto==null)
			return new ModelAndView("redirect:/demander/{demander_seq}/review/list");
		
		
		
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		ModelAndView mav=new ModelAndView(".four.demander.dari.review.article.�ı�Խ���");
		
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		mav.addObject("listFile", listFile);
		mav.addObject("page", page);
		mav.addObject("params", params);
        return mav;
	}
	
	//�ٿ�ε�
	@RequestMapping(value="/demander/{demander_seq}/review/download")
	public void deReviewDownload(
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="fileNum") int fileNum
			,@PathVariable int demander_seq
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
	
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review"+File.separator+demander_seq;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceReviewIdx", num);  
		map.put("serviceFileIdx", fileNum); //fileIndex 
		map.put("demander_seq", demander_seq);
		DeReview dto=service.readFile(map);
		
		boolean flag=false;
		if(dto!=null) {
			String saveFilename=dto.getSaveFilename();
			String originalFilename=dto.getOriginalFilename();
			flag=fileManager.doFileDownload(saveFilename, originalFilename, path, resp);
		}
		
		if(! flag) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			out.print("<script>alert('���� �ٿ�ε尡 �����߽��ϴ�.');history.back();</script>");
		}
	}
	
	@RequestMapping(value = "demander/{demander_seq}/review/update", method=RequestMethod.GET)
	public ModelAndView deRevUpdateForm(HttpSession session,
			@RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page,
			@PathVariable int demander_seq
			) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		System.out.println("*****update:"+num+":"+page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceReviewIdx", num);
		map.put("demander_seq",demander_seq);
		
		DeReview dto = service.readDeReview(map);
		List<DeReview> listFile=service.listFile(map);
	
		if (info.getUserIdx()!=dto.getUserIdx())
			return new ModelAndView("redirect:/demander/"+demander_seq+"/review/list?page="+page);

		ModelAndView mav=new ModelAndView(".four.demander.dari.review.create.�ı�Խ���");
		mav.addObject("mode", "update");
		mav.addObject("listFile", listFile);
		mav.addObject("page", page);
		mav.addObject("dto", dto);
		return mav;
	}

	@RequestMapping(value = "demander/{demander_seq}/review/update", method=RequestMethod.POST)
	public String deRevUpdateSubmit(HttpSession session, 
			DeReview dto,
			@PathVariable int demander_seq,
			@RequestParam(value = "page") String page) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review"+File.separator+demander_seq;
		
		dto.setDemander_seq(demander_seq);
		// ���� �ϱ�
		System.out.println("*****update2:"+page);
		service.updateDeReview(dto, path);
		
		return "redirect:/demander/"+demander_seq+"/review/list?page="+page;
	}

	@RequestMapping(value="/demander/{demander_seq}/review/deleteFile", 
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteFile(
			HttpSession session,
			@PathVariable int demander_seq,
			@RequestParam(value="fileNum") int fileNum
			) throws Exception {
		// AJAX(JSON) - ����Ʈ �������� ���� ����
		System.out.println(demander_seq+"jiji");
		Map<String, Object> model = new HashMap<>(); 
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		
			String root=session.getServletContext().getRealPath("/");
			String pathname=root+File.separator+"uploads"+File.separator+"blog"+File.separator+info.getUserId();
			
	
			Map<String, Object> map=new HashMap<>();
			map.put("demander_seq", demander_seq);
			map.put("serviceFileIdx", fileNum);
			
			DeReview dto=service.readFile(map);
			if(dto!=null) {
				fileManager.doFileDelete(dto.getSaveFilename(), pathname);
			}
			
			service.deleteFile(map);
	   	    // �۾� ����� json���� ����
			model.put("state", "true");
		
		return model;
	}
		/*SessionInfo info=(SessionInfo)session.getAttribute("member");
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceReviewIdx", num);
		map.put("demander_seq", demander_seq);
		DeReview dto = service.readDeReview(map);
		if(dto==null) {
			return new ModelAndView("redirect:/demander/"+demander_seq+"/review/list?page="+page);
		}
			
		if(! info.getUserId().equals(dto.getUserId())) {
			return new ModelAndView("redirect:.four.demander.dari.review.list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator +"review"+File.separator+demander_seq;	
		
		if(dto.getSaveFilename() != null && dto.getSaveFilename().length()!=0) {
			  fileManager.doFileDelete(dto.getSaveFilename(), path);
			  dto.setDemander_seq(demander_seq);
			  dto.setSaveFilename("");
			  dto.setOriginalFilename("");
			  service.updateDeReview(dto, path);
       }
		
		return new ModelAndView("redirect:/demander/"+demander_seq+"/review/update?num="+num+"&page="+page);*/

	

	@RequestMapping(value="/demander/{demander_seq}/review/delete")
	public ModelAndView delete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page,
			@PathVariable int demander_seq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceReviewIdx", num);
		map.put("demander_seq",demander_seq);
		// �ش� ���ڵ� ���� ����
		DeReview dto = service.readDeReview(map);
		if(dto==null) {
			return new ModelAndView("redirect:/demander/"+demander_seq+"/review/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/demander/"+demander_seq+"/review/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "review"+ File.separator+demander_seq;		
		
		dto.setServiceReviewIdx(num);
		dto.setDemander_seq(demander_seq);
		service.deleteDeReview(dto, dto.getSaveFilename(), path);
		
		return new ModelAndView("redirect:/demander/"+demander_seq+"/review/list?page="+page);
	}
	
	//���ƿ�
	@RequestMapping(value="/demander/{demander_seq}/review/sendLike",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deReviewLike(
			HttpSession session,
			@PathVariable int demander_seq,
			DeReview dto) throws Exception {
	
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		dto.setDemander_seq(demander_seq);
		
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
	
	// ���ƿ�/�Ⱦ�� ���� �١ڵ������� ����
	@RequestMapping(value="/demander/{demander_seq}/review/countLike",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  countLike(
			@RequestParam(value="abc") int num,
			DeReview dto,
			@PathVariable int demander_seq) throws Exception {
		
		int likeCount=0;
		dto.setDemander_seq(demander_seq);
		dto.setServiceReviewIdx(num);
		Map<String, Object> map=service.deRevCountLike(dto);
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
	
	//��۸���Ʈ
	@RequestMapping(value="/demander/{demander_seq}/review/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			,@PathVariable int demander_seq
			) throws Exception {
		
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("serviceReviewIdx", num);
		map.put("demander_seq", demander_seq);
		
		
		dataCount=service.DeReviewReplyDataCount(map);
		
		total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		
		// ����Ʈ�� ����� ������
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		map.put("start", start);
		map.put("end", end);
		List<DeReviewReply> listReply=service.listDeReviewReply(map);
		
	
		// ���͸� <br>
		Iterator<DeReviewReply> it=listReply.iterator();
		int listNum, n=0;
		while(it.hasNext()) {
			DeReviewReply dto=it.next();
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			n++;
		}
		
		// ����¡ó��(�μ�2�� ¥�� js�� ó��)
		String paging=myUtil.paging(current_page, total_page);
		
		ModelAndView mav=new ModelAndView("/demander/dari/review/listReply");
		
		// jsp�� �ѱ� ������
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount);
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	// ��ۺ� ��� ����Ʈ
			@RequestMapping(value="/demander/{demander_seq}/review/listReplyAnswer")
			public ModelAndView listReplyAnswer(
					@RequestParam(value="answer") int answer
					,@PathVariable int demander_seq
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				map.put("demander_seq", demander_seq);
				List<DeReviewReply> listReplyAnswer=service.listDeReviewReplyAnswer(map);
				
				// ���͸� <br>
				Iterator<DeReviewReply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {
					DeReviewReply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/demander/dari/review/listReplyAnswer");

				// jsp�� �ѱ� ������
				mav.addObject("listReplyAnswer", listReplyAnswer);
				
				return mav;
			}
	
			@RequestMapping(value="/demander/{demander_seq}/review/replyCount",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> replyCount(
					@RequestParam(value="num") int num
					,@PathVariable int demander_seq
					) throws Exception {
				// AJAX(JSON) - ��ۺ� ����

				String state="true";
				int count=0;

				//String tableName="b_"+blogSeq;
		        Map<String, Object> map=new HashMap<String, Object>();
		 		//map.put("tableName", tableName);
		   		map.put("serviceReviewIdx", num);
		   		map.put("demander_seq", demander_seq);
		  	    
		   	    count=service.DeReviewReplyDataCount(map);
		   	    
		   	    Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				model.put("count", count);
				
				return model;
			}
			
			@RequestMapping(value="/demander/{demander_seq}/review/replyCountAnswer",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer(
					@RequestParam(value="answer") int answer,
					@PathVariable int demander_seq) throws Exception {
				int count=0;
				
				 Map<String, Object> map=new HashMap<String, Object>();
			   		map.put("answer", answer);
			   		map.put("demander_seq", demander_seq);
			   		
				count=service.DeReviewReplyCountAnswer(map);
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("count", count);
				return model;
			}
			
			// ��� �� ���ú� ��� �߰�
			@RequestMapping(value="/demander/{demander_seq}/review/createdReply",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  createdReply(
					HttpSession session,
					@PathVariable int demander_seq,
					DeReviewReply dto) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				String state="true";
				if(info==null) { // �α����� ���� �ʴ� ���
					state="loginFail";
				} else {
					dto.setUserIdx(info.getUserIdx());
					dto.setDemander_seq(demander_seq);
					int result=service.insertDeReviewReply(dto);
					if(result==0)
						state="false";
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			// ��� �� ��ۺ���� ����
			@RequestMapping(value="/demander/{demander_seq}/review/deleteReply",
					method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,
					@RequestParam(value="replyNum") int replyNum,
					@RequestParam(value="mode") String mode,
					@PathVariable int demander_seq
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				System.out.println("******/review/deleteReply:"+replyNum);
				String state="true";
				if(info==null) { // �α����� ���� �ʴ� ���
					state="loginFail";
				} else {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("mode", mode);
					map.put("replyNum", replyNum);
					map.put("demander_seq", demander_seq);

					System.out.println("******mapp��������");
		            // ��ۻ���
					int result=service.deleteDeReviewReply(map);
					System.out.println("******result:"+result);
					if(result==0)
						state="false";
					
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
	//*********��� ���ƿ�*************
			//���ƿ�
			@RequestMapping(value="/demander/{demander_seq}/review/sendLikeReply",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> deReviewLikeReply(
					HttpSession session,
					DeReviewReply dto,
					@PathVariable int demander_seq) throws Exception {
				
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				dto.setUserIdx(info.getUserIdx());
				dto.setDemander_seq(demander_seq);
				int state=service.stateDeRevReplyLike(dto);
				
				if(state==0){
					dto.setUserIdx(info.getUserIdx());
					service.insertDeReviewReplyLike(dto);
				}else if(state==1){
					dto.setUserIdx(info.getUserIdx());
					service.deleteDeRevReplyLike(dto);
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			// ���ƿ�/�Ⱦ�� ����
			@RequestMapping(value="/demander/{demander_seq}/review/countLikeReply",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  countLikeReply(
					@RequestParam(value="replyNum") int num,
					@PathVariable int demander_seq) throws Exception {
				
				int likeCount=0;
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("replyNum", num);
				map.put("demander_seq",demander_seq);
			
				Map<String, Object> resultMap=service.DeReviewReplyCountLike(map);
				if(resultMap!=null) {
					// resultType�� map�� ��� int�� BigDecimal�� �Ѿ��
					likeCount=((BigDecimal)resultMap.get("LIKECOUNT")).intValue();
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("likeCount", likeCount);
				
				return model;
			}
			
	
}
