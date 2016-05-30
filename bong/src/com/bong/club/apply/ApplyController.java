package com.bong.club.apply;

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



@Controller("club.applyController")
public class ApplyController {
	
	@Autowired
	private ApplyService service;
	
	@Autowired
	private MyUtil myUtil;
	
	
	/*���ε��Ƹ� ���� ��û�Խ���*/
	
	///////////////////////////////////////////// ����Ʈ ///////////////////////////////////////////////////
	@RequestMapping(value="/club/index/apply/list")
	public ModelAndView clubApplyList(
			HttpServletRequest req,
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
        String urlList = cp+"/club/index/apply/list";
        String urlArticle = cp+"/club/index/apply/article?page=" + current_page;
      
        //�˻��ΰ��
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }        
        if(params.length()!=0) {
            urlList = cp+"/club/index/apply/list?" + params;
            urlArticle = cp+"/club/index/apply/article?page=" + current_page + "&"+ params;
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
	@RequestMapping(value="/club/index/apply/article")
	public ModelAndView readClubApply(HttpSession session,
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
		
		// �ش� ���ڵ� ���� ����
		Apply dto = service.readApply(num);
		System.out.println(dto+" dto�� �����Ӵ�");
		if(dto==null)
			return new ModelAndView("redirect:.club.index.apply?page="+page);
		
		// ��ȸ�� ����
		service.updateHitCount(dto.getVolunIdx());
		service.updateHitCount_club(dto.getClubApplyIdx());
		
		// ��ü ���μ�
        // int linesu = dto.getContent().split("\n").length;
		
		// ����Ʈ�����͸� ����ϹǷ� ���͸� <br>�� �������� ����
        // dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        
		/////////////////// ���� ��, ���� �� 05/23 3�ý���
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("num", num);

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
	@RequestMapping(value="/club/index/apply/createdReply",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  createdReply(	HttpSession session, Reply dto) throws Exception {
		
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		String state="true";
		if(info==null) { // �α����� ���� �ʴ� ���
			state="loginFail";
		} else {
			
			System.out.println("���� idx="+info.getUserIdx());
			System.out.println("���� id="+info.getUserId());
			
			dto.setUserIdx(info.getUserIdx());
			dto.setUserId(info.getUserId());
			System.out.println("dto���� id"+dto.getUserIdx());
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
	@RequestMapping(value="/club/index/apply/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			) throws Exception {
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		System.out.println("listReply�� �Ѿ�� ");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		
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
	@RequestMapping(value="/club/index/apply/replyCount",  method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyCount(	@RequestParam(value="num") int num) throws Exception {

		String state="true";
		int count=0;
		System.out.println("replyCount�� �Ѿ�� ");
		//String tableName="b_"+blogSeq;
        Map<String, Object> map=new HashMap<String, Object>();
 		//map.put("tableName", tableName);
   		map.put("num", num);
  	    
   	    count=service.replyDataCount(map);
   	    
   	    Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		model.put("count", count);
		
		return model;
	}
	
	//////////////////////////////////////////////////// ��� �� ���� ����
			@RequestMapping(value="/club/index/apply/deleteReply", method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,	@RequestParam(value="replyNum") int replyNum,	@RequestParam(value="mode") String mode
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				String state="true";
				if(info==null) { // �α����� ���� �ʴ� ���
					state="loginFail";
				} else {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("mode", mode);
					map.put("replyNum", replyNum);

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
			
			//////////////////////////////////////////////////////////////////// ��ۺ� ��� ����Ʈ
			@RequestMapping(value="/club/index/apply/listReplyAnswer")
			
			public ModelAndView listReplyAnswer(@RequestParam(value="answer") int answer
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				
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
			
			//////////////////////////////////////////////////////////////////// ��ۺ� ��� ����
			@RequestMapping(value="/club/index/apply/replyCountAnswer",method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer( @RequestParam(value="answer") int answer) 
					throws Exception {
				
				int count=0;
				
				count=service.replyCountAnswer(answer);
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("count", count);
				return model;
			}
			/////////////////////////////////////////////////////////////////////////////////////// 	�� �� �� 
			//////////////////////////////////////////////////////////////// ��� ���ƿ� �߰�
			@RequestMapping(value="/apply/replyLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyLike(
					HttpSession session, Reply dto) throws Exception {
			
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				dto.setUserIdx(info.getUserIdx());
				
				int state=service.stateReplyLike(dto);
				System.out.println(state+"= state");
				
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
			@RequestMapping(value="/apply/countLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  countLike(
					@RequestParam(value="replyNum") int replyNum) throws Exception {
				
				int likeCount=0, disLikeCount=0;
				Map<String, Object> map=service.replyCountLike(replyNum);
				if(map!=null) {
					// resultType�� map�� ��� int�� BigDecimal�� �Ѿ��
					likeCount=((BigDecimal)map.get("LIKECOUNT")).intValue();
				}
				
		   	    // �۾� ����� json���� ����
				Map<String, Object> model = new HashMap<>(); 
				model.put("likeCount", likeCount);
				return model;
			}
			
			
			@RequestMapping(value="/club/index/apply/delete")
			public ModelAndView delete(
					HttpSession session, @RequestParam(value="num") int num,	@RequestParam(value="page") String page	)
			throws Exception {
				
				SessionInfo info=(SessionInfo)session.getAttribute("member");
				if(info==null) {
					return new ModelAndView("redirect:/member/login");
				}
				
				// �ش� ���ڵ� ���� ����
				Apply dto = service.readApply(num);
				
				if(dto==null) {
					return new ModelAndView("redirect:/club/index/apply/list?page="+page);
				}				
								
				String root = session.getServletContext().getRealPath("/");
				String path = root + File.separator + "uploads" + File.separator + "notice";		
		 	
				service.deleteApply(num, dto.getSaveFileName(), path);
				
				return new ModelAndView("redirect:/club/index/apply/list?page="+page);
			}
			
			
			@RequestMapping(value="/club/index/apply/applyList")
			public ModelAndView applyList( HttpSession session) {
				//   /club/index/apply/applyList
				ModelAndView mav = new ModelAndView(".four.club.dari.apply.applyList.���ٸ� ����������");
				
				return mav;
			}
}
	
	