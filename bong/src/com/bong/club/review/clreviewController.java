package com.bong.club.review;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.bong.club.apply.Reply;
import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.demander.review.DeReview;
import com.bong.member.SessionInfo;

@Controller("club.clreviewController")
public class clreviewController {
	@Autowired
	private ClReviewService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/club/{clubSeq}/review/list")
	public ModelAndView ClReviewList(
			HttpServletRequest req,
			@RequestParam(value="page",defaultValue="1")int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue,
			@PathVariable int clubSeq
			
			) throws Exception {
		
		System.out.println("controller ����");
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
	        map.put("clubSeq",clubSeq);
	        
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
	        List<ClReview> list = service.listClReview(map);

	        //ù��° ���� ����Ϸ� �����ϱ� 
	        Pattern pattern=Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
	        
	        Matcher match;
	        
	        // ����Ʈ�� ��ȣ
	        int listNum, n = 0;
	        Iterator<ClReview> it=list.iterator();
	        
	        String content=null; //�� �̸����� �� ���� ����
	        
	        while(it.hasNext()) {
	        	ClReview data = it.next();
	            listNum = dataCount - (start + n - 1);
	            data.setListNum(listNum);
	            
	            match=pattern.matcher(data.getContent());
	            if(match.find())
	            	data.setListImageName(match.group(1));
	            
	            content=data.getContent().replaceAll("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>","");
	      

	            if(content.length()<60){
	            	 data.setContent(content);
	                 n++;
	            }
	            else {
	            	
		            content=content.substring(0, 60);
		            data.setContent(content);
		            System.out.println("content+"+content);
		            n++;
	            }
	        }
	        
	        String params = "";
	        String urlList = cp+"/club/"+clubSeq+"/review/list";
	        String urlArticle = cp+"/club/"+clubSeq+"/review/article?page=" + current_page;
	        if(searchValue.length()!=0) {
	        	params = "searchKey=" +searchKey + 
	        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
	        }
	        
	        if(params.length()!=0) {
	            urlList = cp+"/club/"+clubSeq+"/review/list?" + params;
	            urlArticle = cp+"/club/"+clubSeq+"/review/article?page=" + current_page + "&"+ params;
	        }
	        
	        ModelAndView mav=new ModelAndView(".four.club.dari.review.list.�ı�Խ���");
	
	        mav.addObject("list", list);
	        mav.addObject("urlArticle", urlArticle);
	        mav.addObject("page", current_page);
	        mav.addObject("dataCount", dataCount);
	        mav.addObject("total_page", total_page);
	        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));	
	        mav.addObject("subMenu","6");
		
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/review/create",method=RequestMethod.GET)
	public ModelAndView ClRevCreateForm(
			HttpSession session
			, @PathVariable int clubSeq
			) throws Exception {
	
		SessionInfo info=(SessionInfo)session.getAttribute("member");
	
		ModelAndView mav=new ModelAndView(".four.club.dari.review.create.�ı�Խ���");
		mav.addObject("mode", "created"); 
		mav.addObject("subMenu","6");
		mav.addObject("clubSeq", clubSeq);
		return mav;
	}
	
	@RequestMapping(value="/club/{clubSeq}/review/create",method=RequestMethod.POST)
	public ModelAndView ClRevCreateSubmit(
			HttpSession session,
			ClReview dto,
			@PathVariable int clubSeq
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		System.out.println("�۾��� ��Ʈ�ѷ��� �� ");
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		
		System.out.println("�۾��� ��Ʈ�ѷ� - USER�����ޱ� ");
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx()); //����idx ���� 
		System.out.println("�۾��� ��Ʈ�ѷ� - CLUB_SEQ �ޱ� ");
		dto.setClubSeq(clubSeq);
		
		System.out.println("���� �ε���:"+dto.getUserId());
		
		service.insertClReview(dto, path);		
		
		return new ModelAndView("redirect:/club/"+clubSeq+"/review/list");
		
	}
	
	
	@RequestMapping(value="/club/{clubSeq}/review/article")
	public ModelAndView ClReArticle(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") int page,
			@RequestParam(value="searchKey",defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue",defaultValue="") String searchValue
			,@PathVariable int clubSeq
			) throws Exception {
		
	
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		//�˻��� decode
		searchValue= URLDecoder.decode(searchValue,"utf-8");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubSeq", clubSeq); 
		System.out.println("clubSeq: "+clubSeq);
		
		map.put("num",num);
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		
		//��ȸ������
		service.updateHitCount(map);
		
		ClReview preReadDto = service.preReadClReview(map);
		ClReview nextReadDto = service.nextReadClReview(map);
				
		//�ش��ƼŬ��������
		ClReview dto=service.readClReview(map);
		System.out.println("listFile��");
		List<ClReview> listFile=service.listFile(map);
		
		if(dto==null)
			return new ModelAndView("redirect:/club/"+clubSeq+"/review/list");
			//return new ModelAndView("redirect:.club.index.review.list?page="+page);
				
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		ModelAndView mav=new ModelAndView(".four.club.dari.review.article.�ı�Խ���");
		mav.addObject("subMenu","6");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		mav.addObject("listFile", listFile);
		mav.addObject("page", page);
		mav.addObject("params", params);
        return mav;
	}
	
	//�ٿ�ε�
	@RequestMapping(value="/club/{clubSeq}/review/download")
	public void ClReviewDownload(
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="clubFileIdx") int clubFileIdx,
			@PathVariable int clubSeq
			) throws Exception{
		
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			resp.sendRedirect(cp+"/member/login");
			return;
		}*/
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num); //fileIndex 
		map.put("clubFileIdx", clubFileIdx); //fileIndex 
		map.put("clubSeq", clubSeq);
		
		System.out.println("readFile ��");
		ClReview dto=service.readFile(map);
		System.out.println("readFile �Ѿ��");
		
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
	
	@RequestMapping(value = "club/{clubSeq}/review/update", method=RequestMethod.GET)
	public ModelAndView ClRevUpdateForm(HttpSession session,
			@RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page
			,@PathVariable int clubSeq
			) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		
		System.out.println("readClReview ���� ������");
		ClReview dto = service.readClReview(map);
		List<ClReview> listFile=service.listFile(map);
			
		if (info.getUserIdx()!=dto.getUserIdx())
			return new ModelAndView("redirect:/club/"+clubSeq+"/review/list?page="+page);

		ModelAndView mav=new ModelAndView(".four.club.dari.review.create.�ı�Խ���");
		mav.addObject("mode", "update");
		mav.addObject("listFile", listFile);
		mav.addObject("page", page);
		mav.addObject("dto", dto); 
		mav.addObject("subMenu","6");
		return mav;
	}

	@RequestMapping(value = "club/{clubSeq}/review/update", method=RequestMethod.POST)
	public String ClRevUpdateSubmit(HttpSession session, 
			ClReview dto,
			@RequestParam(value = "page") String page
			,@PathVariable int clubSeq
		) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		/*
		if (info == null) {
			return "redirect:/member/login";
		}*/
				
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		
		dto.setClubSeq(clubSeq);
		// ���� �ϱ�
		service.updateClReview(dto, path);
		
		return "redirect:/club/"+clubSeq+"/review/list?page="+page;
	}

	@RequestMapping(value="/club/{clubSeq}/review/deleteFile", 
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteFile(
			HttpSession session,	
			@RequestParam(value="clubFileIdx") int clubFileIdx,
			@PathVariable int clubSeq
			) throws Exception {
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		Map<String, Object> model = new HashMap<>(); 

		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"blog"+File.separator+info.getUserId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubFileIdx", clubFileIdx);
		map.put("num", clubFileIdx);
		map.put("clubSeq", clubSeq);
		
		ClReview dto = service.readFile(map);
		if(dto!=null) {
			fileManager.doFileDelete(dto.getSaveFilename(), pathname);
		}
		
		service.deleteFile(map);
		System.out.println("deleteFile ��Ʈ�ѷ� �������� ");
		model.put("state", "true");
		
		return model;
	}
	
////////////////////////////////////////////////////////////////////		�Խñ� ���� 
	@RequestMapping(value="/club/{clubSeq}/review/delete")
	public ModelAndView delete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="page") String page
			,@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		/*if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);

		// �ش� ���ڵ� ���� ����
		ClReview dto = service.readClReview(map);
		if(dto==null) {
			return new ModelAndView("redirect:/club/"+clubSeq+"/review/list?page="+page);
		}
		
		if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/club/"+clubSeq+"/review/list?page="+page);
		}
		
		String root = session.getServletContext().getRealPath("/");
		String path = root + File.separator + "uploads" + File.separator + "review";		
		
		dto.setClubReviewIdx(num);
		dto.setClubSeq(clubSeq);
		
		System.out.println("���� delete ���� �� ClubReviewIdx ="+dto.getClubReviewIdx());
		
		service.deleteClReview(dto, dto.getSaveFilename(), path);

		System.out.println("���ٿ�");
		return new ModelAndView("redirect:/club/"+clubSeq+"/review/list?page="+page);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	�Խñ��� ���ƿ� ó�� 
	
	/////////////// 	�Խñ��� ���ƿ��߰�
	@RequestMapping(value="/club/{clubSeq}/review/sendLike",	method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendLike(	
			HttpSession session,
			ClReview dto
			,@PathVariable int clubSeq     )
			throws Exception {
	
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		
		dto.setUserIdx(info.getUserIdx());
		dto.setClubSeq(clubSeq);
		
		int state=service.stateLike(dto);
		
		if(state==0){ // �Խù��� ���ƿ䰡 ���� �ȴ������ٸ� > 1�߰� 
			dto.setUserIdx(info.getUserIdx());
			service.insertLike(dto);
			System.out.println("�Խù��� ���ƿ並 �߰��Ͽ����ϴ� ");
			
		}else if(state==1){ //�̹� ���ƿ並 �����ٸ� -1 
			dto.setUserIdx(info.getUserIdx());
			service.deleteLike(dto);
			System.out.println("�Խù��� ���ƿ並 ����Ͽ����ϴ� ");
		}
		
   	    // �۾� ����� json���� ����
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		System.out.println("�Խù� ���ƿ� ��Ʈ�ѷ� �����ϰ� ����");
		return model;
	}
	
	////////////////////////////////////////////////////////////////////		�Խñ��� ���ƿ�/�Ⱦ�� ����
	@RequestMapping(value="/club/{clubSeq}/review/countLike",   	method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  countLike(	
			@RequestParam(value="clubReviewIdx") int clubReviewIdx,
			ClReview dto,
			@PathVariable int clubSeq) 
			throws Exception {
		
		int likeCount=0;

		System.out.println("�Խñ� ���ƿ� ���� ��Ʈ�ѷ� ) club���� �ε���: "+clubReviewIdx);
		
		dto.setClubSeq(clubSeq);
		dto.setClubReviewIdx(clubReviewIdx);
		
		Map<String, Object> map=service.countLike(dto);
		
		if(map!=null) {
			// resultType�� map�� ��� int�� BigDecimal�� �Ѿ��
			likeCount=((BigDecimal)map.get("LIKECOUNT")).intValue();
		}
		
   	    // �۾� ����� json���� ����
		Map<String, Object> model = new HashMap<>(); 
		System.out.println();
		model.put("likeCount", likeCount);
		
		return model;
	}
	
	//////--------------------------------------------------------------------------------------		��� ����
	
	//////////////////////////////////////////////////////////////////				��۸���Ʈ
	@RequestMapping(value="/club/{clubSeq}/review/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num, //ClubReviewIdx�� �Ѿ�Դ�
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			,@PathVariable int clubSeq
			) throws Exception {
		
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("clubReviewIdx", num);
		map.put("clubSeq", clubSeq);
		
		dataCount=service.replyDataCount(map); //��� ���� ���� -clubReviewIdx�ѱ�
		total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		// ����Ʈ�� ����� ������
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		map.put("start", start);
		map.put("end", end);
		List<ClReviewReply> listReply=service.listReply(map);
		
		// ���͸� <br>
		Iterator<ClReviewReply> it=listReply.iterator();
		int listNum, n=0;
		
		while(it.hasNext()) {
			
			ClReviewReply dto=it.next();
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			n++;
		}
		// ����¡ó��(�μ�2�� ¥�� js�� ó��)
		String paging=myUtil.paging(current_page, total_page);
		
		ModelAndView mav=new ModelAndView("/club/dari/review/listReply");
		
		// jsp�� �ѱ� ������
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount); //
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////					 ���� ����Ʈ
			@RequestMapping(value="/club/{clubSeq}/review/listReplyAnswer")
			public ModelAndView listReplyAnswer(	
					@RequestParam(value="answer") int answer
					,@PathVariable int clubSeq
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				map.put("clubSeq", clubSeq);

				List<ClReviewReply> listReplyAnswer=service.listReplyAnswer(map);
	
				// ���͸� <br>
				Iterator<ClReviewReply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {
					ClReviewReply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/club/dari/review/listReplyAnswer");

				// jsp�� �ѱ� ������
				mav.addObject("listReplyAnswer", listReplyAnswer);
				return mav;
			}
	
			@RequestMapping(value="/club/{clubSeq}/review/replyCount", method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> replyCount(	
					@RequestParam(value="num") int num ,@PathVariable int clubSeq
					) throws Exception {
				// AJAX(JSON) - ��ۺ� ����

				String state="true";
				int count=0;

				//String tableName="b_"+blogSeq;
		        Map<String, Object> map=new HashMap<String, Object>();
		 		//map.put("tableName", tableName);
		   		map.put("clubReviewIdx", num);
		   		map.put("clubSeq", clubSeq);
		   		
		   	    count=service.replyDataCount(map);
		   	    
		   	    Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				model.put("count", count);
				
				return model;
			}
			
			@RequestMapping(value="/club/{clubSeq}/review/replyCountAnswer", method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer( 
					@RequestParam(value="answer") int answer
					,@PathVariable int clubSeq)
					throws Exception {
				
				int count=0;
				Map<String, Object> map = new HashMap<>(); 
				
				map.put("answer", answer);
				map.put("clubSeq", clubSeq);
				
				count=service.replyCountAnswer(map);
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
		
				model.put("count", count);
				return model;
			}
			
			// ��� + ���� �߰�
			@RequestMapping(value="/club/{clubSeq}/review/createdReply", method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  createdReply(	HttpSession session,
					ClReviewReply dto ,@PathVariable int clubSeq) 
					throws Exception {
				
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				String state="true";
				if(info==null) { // �α����� ���� �ʴ� ���
					state="loginFail";
				}
				else {
					dto.setUserIdx(info.getUserIdx());
					dto.setClubSeq(clubSeq);
					
					int result=service.insertReply(dto);
					if(result==0)
						state="false";
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			// ��� �� ���� ����
			@RequestMapping(value="/club/{clubSeq}/review/deleteReply",		method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,
					@RequestParam(value="replyNum") int replyNum,
					@RequestParam(value="mode") String mode
					,@PathVariable int clubSeq
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				String state="true";
				if(info==null) { // �α����� ���� �ʴ� ���
					state="loginFail";
				} else {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("mode", mode);
					map.put("replyNum", replyNum);
					map.put("clubSeq", clubSeq);
					// ���ƿ�/�Ⱦ�� �� ON DELETE CASCADE �� �ڵ� ����

		            // ��ۻ���
					int result=service.deleteReply(map);

					if(result==0)
						state="false";
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
	//*********								��� ���ƿ�											*************
			//���ƿ�
			@RequestMapping(value="/club/{clubSeq}/review/insertReplyLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> insertReplyLike(
					HttpSession session,
					ClReviewReply dto
					,@PathVariable int clubSeq)
				throws Exception {
				
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				System.out.println("��Ʈ�ѷ�-���ƿ� �Լ�");
				
				dto.setUserIdx(info.getUserIdx());
				dto.setClubSeq(clubSeq);
								
				int state=service.stateReplyLike(dto);
				
				System.out.println("��Ʈ�ѷ�state:"+state);
				if(state==0){ //���� ���ƿ䰡 ������ !
					
					dto.setUserIdx(info.getUserIdx());
					service.insertReplyLike(dto);
					
				}else if(state==1){ //�̹� ���ƿ並 �� �����϶�!
					
					dto.setUserIdx(info.getUserIdx());
					service.deleteReplyLike(dto);
					System.out.println("���ƿ� ����޴�(��Ʈ�ѷ�)");
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			// ���ƿ�/�Ⱦ�� ����
			@RequestMapping(value="/club/{clubSeq}/review/replyCountLike",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountLike(
					@RequestParam(value="replyNum") int replyNum
					,@PathVariable int clubSeq )
					throws Exception {
			
				int likeCount=0;
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("replyNum", replyNum);
				map.put("clubSeq", clubSeq);
				System.out.println("replyCountLike�� ������ replyNum="+replyNum);
				
				Map<String, Object> resultMap=service.replyCountLike(map);
				
				if(resultMap!=null) {
					
					// resultType�� map�� ��� int�� BigDecimal�� �Ѿ��
					likeCount=((BigDecimal)resultMap.get("LIKECOUNT")).intValue();
				}
			
				
				System.out.println("likecount="+likeCount);
				
				Map<String, Object> model = new HashMap<>(); 
				model.put("likeCount", likeCount);
				
				return model;
			}
			
	
}
