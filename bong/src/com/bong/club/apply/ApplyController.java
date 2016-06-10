package com.bong.club.apply;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
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
import com.bong.member.Member;
import com.bong.member.SessionInfo;


@Controller("club.applyController")
public class ApplyController {
	
	@Autowired
	private ApplyService service;
	
	@Autowired
	private MyUtil myUtil;
	
	
	/*���ε��Ƹ� ���� ��û�Խ���*/
	
	///////////////////////////////////////////// ����Ʈ ///////////////////////////////////////////////////
	@RequestMapping(value="/club/{clubSeq}/apply/list")
	public ModelAndView clubApplyList(
			HttpServletRequest req,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			,@PathVariable int clubSeq 
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
        System.out.println("dataCount="+dataCount);
        
        if(dataCount != 0)
            total_page = myUtil.pageCount(numPerPage,  dataCount) ;
		
        System.out.println("total_page="+total_page);
    
		// �ٸ� ����� �ڷḦ �����Ͽ� ��ü ���������� ��ȭ �� ���
        if(total_page < current_page) 
            current_page = total_page;

        // ����Ʈ�� ����� �����͸� ��������
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);

        System.out.println("start: "+start);
        System.out.println("end: "+end);
        System.out.println("current_page: "+current_page);
        System.out.println("numPerPage: "+numPerPage);
        // �� ����Ʈ
        List<Apply> list = service.listApply(map);
                     
     // ����Ʈ�� ��ȣ
        int listNum, n = 0;
        Iterator<Apply> it=list.iterator();
        
        while(it.hasNext()) {
            Apply data = it.next();
            listNum = dataCount - (start + n - 1);
            
            data.setListNum(listNum);
              
            n++;
        }
        
        String params = "";
        String urlList = cp+"/club/"+clubSeq+"/apply/list";
        String urlArticle = cp+"/club/"+clubSeq+"/apply/article?page=" + current_page;
      
        //�˻��ΰ��
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }        
        if(params.length()!=0) {
            urlList = cp+"/club/"+clubSeq+"/apply/list?" + params;
            urlArticle = cp+"/club/"+clubSeq+"/apply/article?page=" + current_page + "&"+ params;
        }
        
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.list.�����û");
		
        mav.addObject("list", list);
       
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
        
		return mav;
	}
	
	///////////////////////////////////////////// �ۺ��� ///////////////////////////////////////////////////
	@RequestMapping(value="/club/{clubSeq}/apply/article")
	public ModelAndView readClubApply(HttpSession session,
			@RequestParam(value="volunIdx") int volunIdx,
			@RequestParam(value="clubApplyIdx") int clubApplyIdx,
			@RequestParam(value="page") String page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			,@PathVariable int clubSeq )
					throws Exception {
		
		System.out.println("1. ��Ʈ�ѷ� - readClubApply�� ���Խ��ϴ� > volunIdx= "+volunIdx);
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		searchValue = URLDecoder.decode(searchValue, "utf-8"); //�˻��� �ڵ�
		
		///////1) ��ȸ������
			// map1�� �۹�ȣ�� Ŭ��seq�� ��´�
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("volunIdx", volunIdx);
		map1.put("clubApplyIdx", clubApplyIdx);
		map1.put("clubSeq", clubSeq);
		
			// ��ȸ�� ����
		service.updateHitCount(map1);
		service.updateHitCount_club(map1);
		
		
		// map: ��, ������ ������ �� �������� ���� �ʿ��� �������� �ִ´�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("clubApplyIdx", clubApplyIdx);
		map.put("clubSeq", clubSeq);
		
		// �ش� ���ڵ� ���� ����
		Apply dto = service.readApply(map);
		if(dto==null)
			return new ModelAndView("redirect:.club/{clubSeq}/apply/list?page="+page);
		System.out.println("3. ��Ʈ�ѷ� - ��ƼŬ�� �о�Խ��ϴ� ");
		// ��ü ���μ�			
        // int linesu = dto.getContent().split("\n").length;
		
		// ����Ʈ�����͸� ����ϹǷ� ���͸� <br>�� �������� ����
        // dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        		
		
		///////////// 2) ���� ��, ���� �� 
		Apply preReadDto = service.preReadApply(map);
		Apply nextReadDto = service.nextReadApply(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.article.���ٸ� ����������");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		mav.addObject("page", page);
		mav.addObject("params", params);
		
		return mav;
	}
	
	////////////////////////////////////////////////////////// 		��۰��� 	///////////////////////////////////////////////////////
	////////////////////////////��� �� ���� ���� 
	@RequestMapping(value="/club/{clubSeq}/apply/createdReply", 		method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  createdReply(	
			HttpSession session
			, Reply dto
			,@PathVariable int clubSeq ) 
					throws Exception {
		
		System.out.println("��Ʈ�ѷ� - createdReply�� ���Խ��ϴ� ");
		
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		String state="true";
		if(info==null) { // �α����� ���� �ʴ� ���
			state="loginFail";
		} else {				
			dto.setUserIdx(info.getUserIdx());
			dto.setUserId(info.getUserId());
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
	/////////////////////////////////////////////////////////////////////////////////////////		��� ����Ʈ 
	@RequestMapping(value="/club/{clubSeq}/apply/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			,@PathVariable int clubSeq ) throws Exception {
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		
		System.out.println("listReply�� �Ѿ�� ");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		
		dataCount=service.replyDataCount(map);
		total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		// ����Ʈ�� ����� ������
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		map.put("start", start);
		map.put("end", end);
		List<Reply> listReply=service.listReply(map);
		
		// ���͸� <br>
		Iterator<Reply> it=listReply.iterator();
		int listNum, n=0;
		
		while(it.hasNext()) {
			Reply dto=it.next();
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			n++;
		}
		// ����¡ó��(�μ�2�� ¥�� js�� ó��)
		String paging=myUtil.paging(current_page, total_page);
		
		ModelAndView mav=new ModelAndView("/club/dari/apply/listReply");
		
		// jsp�� �ѱ� ������
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount); //
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	//////////////////////////////////// AJAX(JSON) - ��ۺ� ����
	@RequestMapping(value="/club/{clubSeq}/apply/replyCount",  method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyCount(	
			@RequestParam(value="num") int num
			,@PathVariable int clubSeq ) throws Exception {
		
		String state="true";
		int count=0;
		
		System.out.println("replyCount�� �Ѿ�� ");
		
		
		//String tableName="b_"+blogSeq;
        Map<String, Object> map=new HashMap<String, Object>();
 		//map.put("tableName", tableName);
   		map.put("num", num);
  	    map.put("clubSeq", clubSeq);
  	    
   	    count=service.replyDataCount(map);
   	    
   	    Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		model.put("count", count);
		
		return model;
	}
	
	//////////////////////////////////////////////////// ��� �� ���� ����
			@RequestMapping(value="/club/{clubSeq}/apply/deleteReply", method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,	@RequestParam(value="replyNum") int replyNum,	
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
			
			//////////////////////////////////////////////////////////////////// ���� ����Ʈ
			@RequestMapping(value="/club/{clubSeq}/apply/listReplyAnswer")
			
			public ModelAndView listReplyAnswer(
					@RequestParam(value="answer") int answer
					,@PathVariable int clubSeq )
				throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				map.put("clubSeq", clubSeq);
				
				List<Reply> listReplyAnswer=service.listReplyAnswer(map);
				
				// ���͸� <br>
				Iterator<Reply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {					
					Reply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/club/dari/apply/listReplyAnswer");

				// jsp�� �ѱ� ������
				mav.addObject("listReplyAnswer", listReplyAnswer);
				
				return mav;
			}
			
			//////////////////////////////////////////////////////////////////// ���� ����
			@RequestMapping(value="/club/{clubSeq}/apply/replyCountAnswer",method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer(
					@RequestParam(value="answer") int answer,
					@PathVariable int clubSeq)
				throws Exception {
				
				int count=0;
				
				Map<String, Object> map=new HashMap<String, Object>();
		 		
		   		map.put("answer", answer);
		  	    map.put("clubSeq", clubSeq);
		  	    
				count=service.replyCountAnswer(map);
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("count", count);
				model.put("clubSeq", clubSeq);
				
				return model;
			}
			/////////////////////////////////////////////////////////////////////////////////////// 	�� �� �� 
			//////////////////////////////////////////////////////////////// ��� ���ƿ� �߰�
			@RequestMapping(value="/club/{clubSeq}/apply/sendReplyLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  sendReplyLike(
						HttpSession session, Reply dto
						,@PathVariable int clubSeq )
				throws Exception {
				
				System.out.println("1 ��Ʈ�ѷ� sendReplyLike�� ���Խ��ϴ� > replyNum: "+dto.getReplyNum());
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				dto.setUserIdx(info.getUserIdx());
				dto.setClubSeq(clubSeq);
				
				int state=service.stateReplyLike(dto);
				
				if(state==0){	//���ƿ䰡 ó���̶��
				
					service.insertReplyLike(dto);
					
				}else if(state==1){ // �� �����Ŷ�� ��� 
					dto.setUserIdx(info.getUserIdx());
					service.deleteReplyLike(dto);
				}
				
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			//////////////////////////////////////////////////////////////// ��� ���ƿ� ����
			@RequestMapping(value="/club/{clubSeq}/apply/countLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  countLike(
						@RequestParam(value="replyNum") int replyNum
						,@PathVariable int clubSeq ) 
				throws Exception {
				
				int likeCount=0;
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("replyNum", replyNum);
				map.put("clubSeq",clubSeq);
				
				Map<String, Object> resultMap=service.replyCountLike(map);
				
				if(resultMap!=null) {
					// resultType�� map�� ��� int�� BigDecimal�� �Ѿ��
					likeCount=((BigDecimal)resultMap.get("LIKECOUNT")).intValue();
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("likeCount", likeCount);
				
				return model;
			}
			
			
			@RequestMapping(value="/club/{clubSeq}/apply/delete")
			public ModelAndView delete(
						HttpSession session, 
						@RequestParam(value="num") int num
						,@RequestParam(value="page") String page
						,@PathVariable int clubSeq)
				throws Exception {
				
				SessionInfo info=(SessionInfo)session.getAttribute("member");
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("num", num);
				map.put("clubSeq",clubSeq);
				
				// �ش� ���ڵ� ���� ����
				Apply dto = service.readApply(map);
				
				if(dto==null) {
					return new ModelAndView("redirect:/club/"+clubSeq+"/apply/list?page="+page);
				}
				
				if(! info.getUserId().equals(dto.getUserId()) && ! info.getUserId().equals("admin")) {
					return new ModelAndView("redirect:/club/"+clubSeq+"/apply/list?page="+page);
				}
								
				String root = session.getServletContext().getRealPath("/");
				String path = root + File.separator + "uploads" + File.separator + "notice";		
		 	
				service.deleteApply(num, dto.getSaveFileName(), path);
				
				return new ModelAndView("redirect:/club/"+clubSeq+"/apply/list?page="+page);
			}
			
			
			@RequestMapping(value="/club/{clubSeq}/apply/applyList2")
			public ModelAndView applyList2( HttpSession session) {
				//   /club/index/apply/applyList
				ModelAndView mav = new ModelAndView(".four.club.dari.apply.applyList.���ٸ� ����������");
				
				return mav;
			}
			
			// �����û�� ȸ������Ʈ ���â 
			@RequestMapping(value="/club/{clubSeq}/apply/applyList1")
			public ModelAndView applyList1(HttpSession session, 
					@RequestParam(value="num") int clubApplyIdx,
					@RequestParam(value="page") String page	,@PathVariable int clubSeq ) throws Exception {
				
				// �����û�� ���� ���Ƹ��� ȸ���� dto�� ��ƿ;��� 
				SessionInfo info=(SessionInfo)session.getAttribute("member");
				
				ModelAndView mav = new ModelAndView("/club/dari/apply/applyList");
				
				// �ش� ���ڵ� ���� ����
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("clubApplyIdx", clubApplyIdx);
				map.put("clubSeq",clubSeq);
				
				List<Member> list = service.readApplyList(map);
				List<Member> date_list = new ArrayList<>();
								
				int n=0; //listnum 
				for(int i=0; i<list.size(); i++) {
					
					if(i>0) {
						if(list.get(i).getUserIdx()==date_list.get(date_list.size()-1).getUserIdx()) {
							
							date_list.get(date_list.size()-1).setHopeDate(
												date_list.get(date_list.size()-1).getHopeDate()+", "+ list.get(i).getHopeDate().substring(5));
							
						}
						else {							
							n++;
							list.get(i).setListNum(n);
							date_list.add(list.get(i));
						}
					} else {
						n++;
						list.get(i).setListNum(n);
						date_list.add(list.get(i));
					}
				}
				
				mav.addObject("list", date_list);
				mav.addObject("n",n);
				return mav;
			}
}
	
	