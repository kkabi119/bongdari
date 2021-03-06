package com.bong.club.apply;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.bong.member.SessionInfo;


@Controller("club.applyController")
public class ApplyController {
	
	@Autowired
	private ApplyService service;
	
	@Autowired
	private MyUtil myUtil;
	
	
	/*개인동아리 봉사 신청게시판*/
	
	///////////////////////////////////////////// 리스트 ///////////////////////////////////////////////////
	@RequestMapping(value="/club/{clubSeq}/apply/list")
	public ModelAndView clubApplyList(
			HttpServletRequest req,
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			,@PathVariable int clubSeq 
			) throws Exception {
		
		String cp=req.getContextPath();
		
		int numPerPage   = 10;  // 한 화면에 보여주는 게시물 수
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
		 // 전체 페이지 수
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchKey", searchKey);
        map.put("searchValue", searchValue);
        map.put("clubSeq", clubSeq);

        
        dataCount = service.dataCount(map);
        System.out.println("dataCount="+dataCount);
        
        if(dataCount != 0)
            total_page = myUtil.pageCount(numPerPage,  dataCount) ;
		
        System.out.println("total_page="+total_page);
    
		// 다른 사람이 자료를 삭제하여 전체 페이지수가 변화 된 경우
        if(total_page < current_page) 
            current_page = total_page;

        // 리스트에 출력할 데이터를 가져오기
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);

        System.out.println("start: "+start);
        System.out.println("end: "+end);
        System.out.println("current_page: "+current_page);
        System.out.println("numPerPage: "+numPerPage);
        // 글 리스트
        List<Apply> list = service.listApply(map);
                     
     // 리스트의 번호
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
      
        //검색인경우
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }        
        if(params.length()!=0) {
            urlList = cp+"/club/"+clubSeq+"/apply/list?" + params;
            urlArticle = cp+"/club/"+clubSeq+"/apply/article?page=" + current_page + "&"+ params;
        }
        
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.list.봉사신청");
		
        mav.addObject("list", list);
       
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
        mav.addObject("clubSeq", clubSeq);
        
		return mav;
	}
	
	///////////////////////////////////////////// 글보기 ///////////////////////////////////////////////////
	@RequestMapping(value="/club/{clubSeq}/apply/article")
	public ModelAndView readClubApply(HttpSession session,
			@RequestParam(value="volunIdx") int volunIdx,
			@RequestParam(value="clubApplyIdx") int clubApplyIdx,
			@RequestParam(value="page") String page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			,@PathVariable int clubSeq )
					throws Exception {
		
		System.out.println("1. 컨트롤러 - readClubApply에 들어왔습니다 > volunIdx= "+volunIdx);
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		searchValue = URLDecoder.decode(searchValue, "utf-8"); //검색값 코드
		
		///////1) 조회수증가
			// map1에 글번호와 클럽seq를 담는다
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("volunIdx", volunIdx);
		map1.put("clubApplyIdx", clubApplyIdx);
		map1.put("clubSeq", clubSeq);
		
			// 조회수 증가
		service.updateHitCount(map1);
		service.updateHitCount_club(map1);
		
		
		// map: 글, 이전글 다음글 을 가져오기 위해 필요한 변수들을 넣는다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("clubApplyIdx", clubApplyIdx);
		map.put("clubSeq", clubSeq);
		
		// 해당 레코드 가져 오기
		Apply dto = service.readApply(map);
		if(dto==null)
			return new ModelAndView("redirect:.club/{clubSeq}/apply/list?page="+page);
		System.out.println("3. 컨트롤러 - 아티클을 읽어왔습니다 ");
		// 전체 라인수			
        // int linesu = dto.getContent().split("\n").length;
		
		// 스마트에디터를 사용하므로 엔터를 <br>로 변경하지 않음
        // dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        		
		
		///////////// 2) 이전 글, 다음 글 
		Apply preReadDto = service.preReadApply(map);
		Apply nextReadDto = service.nextReadApply(map);
        
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		// 승인 여부 확인할 때 쓰는 변수
		int enabled=service.clubApprovalCheck(map);
		
		ModelAndView mav = new ModelAndView(".four.club.dari.apply.article.봉다리 개인페이지");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		mav.addObject("page", page);
		mav.addObject("params", params);
		mav.addObject("enabled", enabled);
		mav.addObject("clubSeq", clubSeq);
		
		return mav;
	}
	
	////////////////////////////////////////////////////////// 		댓글관련 	///////////////////////////////////////////////////////
	////////////////////////////댓글 및 대댓글 쓰기 
	@RequestMapping(value="/club/{clubSeq}/apply/createdReply", 		method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  createdReply(	
			HttpSession session
			, Reply dto
			,@PathVariable int clubSeq ) 
					throws Exception {
		
		System.out.println("컨트롤러 - createdReply에 들어왔습니다 ");
		
		SessionInfo info=(SessionInfo) session.getAttribute("member");
		String state="true";
		if(info==null) { // 로그인이 되지 않는 경우
			state="loginFail";
		} else {				
			dto.setUserIdx(info.getUserIdx());
			dto.setUserId(info.getUserId());
			dto.setClubSeq(clubSeq);
			
			int result=service.insertReply(dto);
			if(result==0)
				state="false";
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		return model;
	}
	/////////////////////////////////////////////////////////////////////////////////////////		댓글 리스트 
	@RequestMapping(value="/club/{clubSeq}/apply/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			,@PathVariable int clubSeq ) throws Exception {
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		
		System.out.println("listReply로 넘어옴 ");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		
		dataCount=service.replyDataCount(map);
		total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		// 리스트에 출력할 데이터
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		map.put("start", start);
		map.put("end", end);
		List<Reply> listReply=service.listReply(map);
		
		// 엔터를 <br>
		Iterator<Reply> it=listReply.iterator();
		int listNum, n=0;
		
		while(it.hasNext()) {
			Reply dto=it.next();
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			n++;
		}
		// 페이징처리(인수2개 짜리 js로 처리)
		String paging=myUtil.paging(current_page, total_page);
		
		ModelAndView mav=new ModelAndView("/club/dari/apply/listReply");
		
		// jsp로 넘길 데이터
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount); //
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	//////////////////////////////////// AJAX(JSON) - 댓글별 개수
	@RequestMapping(value="/club/{clubSeq}/apply/replyCount",  method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> replyCount(	
			@RequestParam(value="num") int num
			,@PathVariable int clubSeq ) throws Exception {
		
		String state="true";
		int count=0;
		
		System.out.println("replyCount로 넘어옴 ");
		
		
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
	
	//////////////////////////////////////////////////// 댓글 및 대댓글 삭제
			@RequestMapping(value="/club/{clubSeq}/apply/deleteReply", method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,	@RequestParam(value="replyNum") int replyNum,	
					@RequestParam(value="mode") String mode
					,@PathVariable int clubSeq 
					) throws Exception {
				
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				String state="true";
				if(info==null) { // 로그인이 되지 않는 경우
					state="loginFail";
				} else {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("mode", mode);
					map.put("replyNum", replyNum);
					map.put("clubSeq", clubSeq);

					// 좋아요/싫어요 는 ON DELETE CASCADE 로 자동 삭제
		            // 댓글삭제
					int result=service.deleteReply(map);

					if(result==0)
						state="false";
				}
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			//////////////////////////////////////////////////////////////////// 대댓글 리스트
			@RequestMapping(value="/club/{clubSeq}/apply/listReplyAnswer")
			
			public ModelAndView listReplyAnswer(
					@RequestParam(value="answer") int answer
					,@PathVariable int clubSeq )
				throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				map.put("clubSeq", clubSeq);
				
				List<Reply> listReplyAnswer=service.listReplyAnswer(map);
				
				// 엔터를 <br>
				Iterator<Reply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {					
					Reply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/club/dari/apply/listReplyAnswer");

				// jsp로 넘길 데이터
				mav.addObject("listReplyAnswer", listReplyAnswer);
				
				return mav;
			}
			
			//////////////////////////////////////////////////////////////////// 대댓글 개수
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
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("count", count);
				model.put("clubSeq", clubSeq);
				
				return model;
			}
			/////////////////////////////////////////////////////////////////////////////////////// 	좋 아 요 
			//////////////////////////////////////////////////////////////// 댓글 좋아요 추가
			@RequestMapping(value="/club/{clubSeq}/apply/sendReplyLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  sendReplyLike(
						HttpSession session, Reply dto
						,@PathVariable int clubSeq )
				throws Exception {
				
				System.out.println("1 컨트롤러 sendReplyLike에 들어왔습니다 > replyNum: "+dto.getReplyNum());
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				dto.setUserIdx(info.getUserIdx());
				dto.setClubSeq(clubSeq);
				
				int state=service.stateReplyLike(dto);
				
				if(state==0){	//좋아요가 처음이라면
				
					service.insertReplyLike(dto);
					
				}else if(state==1){ // 또 누른거라면 취소 
					dto.setUserIdx(info.getUserIdx());
					service.deleteReplyLike(dto);
				}
				
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			//////////////////////////////////////////////////////////////// 댓글 좋아요 개수
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
					// resultType이 map인 경우 int는 BigDecimal로 넘어옴
					likeCount=((BigDecimal)resultMap.get("LIKECOUNT")).intValue();
				}
				
		   	    // 작업 결과를 json으로 전송
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
				
				// 해당 레코드 가져 오기
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
				ModelAndView mav = new ModelAndView(".four.club.dari.apply.applyList.봉다리 개인페이지");
				
				return mav;
			}
			
			// 봉사신청한 회원리스트 모달창 
			@RequestMapping(value="/club/{clubSeq}/apply/applyList")
			public ModelAndView applyList1(HttpSession session, 
					@RequestParam(value="num") int clubApplyIdx,
					@RequestParam(value="enabled") int enabled,
					@PathVariable int clubSeq
					) throws Exception {
				
				// 봉사신청한 같은 동아리의 회원을 dto에 담아와야함 
				
				ModelAndView mav = new ModelAndView("/club/dari/apply/applyList");
				
				// 해당 레코드 가져 오기
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
												date_list.get(date_list.size()-1).getHopeDate()+" "+ list.get(i).getHopeDate().substring(5));
							
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
				
				String club_id=service.clubIdCheck(clubSeq);
				List<Member> original_list = service.readApplyList(map);
				mav.addObject("myList", original_list);
				mav.addObject("enabled", enabled);
				mav.addObject("list", date_list);
				mav.addObject("n",n);
				mav.addObject("clubApplyIdx", clubApplyIdx);
				mav.addObject("clubUserId", club_id); // 클럽 아이디 임시로 넘김
				return mav;
			}
			
			// 봉사신청하기  
				@RequestMapping(value="/club/{clubSeq}/apply/applyRegister")
				public ModelAndView applyRegister(HttpSession session, 
						@RequestParam(value="num") int clubApplyIdx,
						@RequestParam(value="page") String page,
						@PathVariable int clubSeq
						) throws Exception {
					SessionInfo info=(SessionInfo)session.getAttribute("member");
								
					if(info==null) {
						return new ModelAndView("redirect:/member/login");
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("clubSeq", clubSeq);
					map.put("clubApplyIdx", clubApplyIdx);
					
					ModelAndView mav = new ModelAndView("/club/dari/apply/applyRegister");
					List<String> listWeek = new ArrayList<String>();
					List<Object> listDays = new ArrayList<Object>();
					List<Apply> tempDate = null;
					// 시작날짜, 끝 날짜, 요일 가져오기
					Apply dto = null;
					dto= service.readApplyOriginal(map);
					tempDate = service.readApplyVolunData(dto.getVolunIdx());
					List<String> dateArray = new ArrayList<String>();
					 
					// 이렇게까지 해야하나...
					Iterator<Apply> it=tempDate.iterator();				        
				        while(it.hasNext()) {
				            Apply data = it.next();
				            dateArray.add(data.getStartDay());
				        }
				        
					DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
					
					// 사이 날짜 구하기 참고 : http://bugnote.tistory.com/26
					int startYear = Integer.parseInt(dto.getStartDay().substring(0,4));
			        int startMonth= Integer.parseInt(dto.getStartDay().substring(5,7));
			        int startDate = Integer.parseInt(dto.getStartDay().substring(8,10));
			        int endYear = Integer.parseInt(dto.getEndDay().substring(0,4));
			        int endMonth= Integer.parseInt(dto.getEndDay().substring(5,7));
			        int endDate = Integer.parseInt(dto.getEndDay().substring(8,10));
			        Calendar calStart = Calendar.getInstance();
			        Calendar calEnd = Calendar.getInstance();
			        // Calendar의 Month는 0부터 시작하므로 -1 해준다.
			        // Calendar의 기본 날짜를 startDt로 셋팅해준다.
			        calStart.set(startYear, startMonth -1, startDate, 0, 0, 0);
			        calEnd.set(endYear, endMonth -1, endDate, 0, 0, 1);
			        
			        // 달력의 시작 일과 끝 일을 일요일과 토요일로 맞춰준다.
			        if(calStart.get(Calendar.DAY_OF_WEEK)!=1){
			        	calStart.add(Calendar.DATE, -(calStart.get(Calendar.DAY_OF_WEEK))+1);
			        }
			        if(calEnd.get(Calendar.DAY_OF_WEEK)!=7){
			        	calEnd.add(Calendar.DATE, (7-(calEnd.get(Calendar.DAY_OF_WEEK))));
			        }
			        
			        String theDate = "";
			        int flag=0;
						while(true){
							while(true){
								for(int i=0;i<dateArray.size();i++){
									if(dateArray.get(i).equals(sdFormat.format(calStart.getTime()))){
										switch(calStart.get(Calendar.DATE)){
										case 1 : theDate = "<label id='"+sdFormat.format(calStart.getTime())+
												"' onclick = 'dateInput("+'"'+sdFormat.format(calStart.getTime())+'"'
												+")' style='font-size:16pt; color : green; cursor:pointer;'>"+Integer.toString(calStart.get(Calendar.MONTH)+1)+"월 "+Integer.toString(calStart.get(Calendar.DATE))+"</label>"; break;
										default : theDate = "<label id='"+sdFormat.format(calStart.getTime())+
												"' onclick = 'dateInput("+'"'+sdFormat.format(calStart.getTime())+'"'+")' style='font-size:16pt; color :#3897f0; cursor:pointer;'>"+Integer.toString(calStart.get(Calendar.DATE))+"</label>"; break;
										}
										flag=1;
									}
								}
								if(flag==0){
									switch(calStart.get(Calendar.DATE)){
									case 1 : theDate = Integer.toString(calStart.get(Calendar.MONTH)+1)+"월 "+Integer.toString(calStart.get(Calendar.DATE)); break;
									default : theDate = Integer.toString(calStart.get(Calendar.DATE)); break;
									}
								}
								listWeek.add(theDate);
								calStart.add(Calendar.DATE, 1);
								flag=0;
								if(calStart.get(Calendar.DAY_OF_WEEK)==1){
									break;
								}
							}
							listDays.add(listWeek);
							listWeek = new ArrayList<String>();
							if(calStart.after(calEnd)){
								break;
							}
						}
					
					
					mav.addObject("list", listDays);
					mav.addObject("startDay", dto.getStartDay());
					mav.addObject("endDay", dto.getEndDay());
					mav.addObject("checkDate", dateArray);
					mav.addObject("clubApplyIdx", clubApplyIdx);
					mav.addObject("clubSeq", clubSeq);
					return mav;
				}
				
				// 동아리원이 봉사 신청시 리스트에 날짜 추가
				@RequestMapping(value="/club/{clubSeq}/apply/applyOk")
				@ResponseBody
				public void applyOk(
						HttpSession session,
						@RequestParam(value="checkDateList") String[] checkDateList,
						@RequestParam(value="clubApplyIdx") int clubApplyIdx,
						@PathVariable int clubSeq
						) throws Exception{
					SessionInfo info=(SessionInfo)session.getAttribute("member");
					
					Apply dto = new Apply();
					dto.setClub_seq(clubSeq);
					dto.setClubApplyIdx(clubApplyIdx);
					dto.setUserIdx(info.getUserIdx());
					for(int i=0;i<checkDateList.length;i++){
						dto.setHopeDate(checkDateList[i]);
						service.insertMemList(dto);
					}
					return ;
				}
				
				// 동아리원이 봉사 취소시 리스트 삭제
				@RequestMapping(value="/club/{clubSeq}/apply/deleteCheckOk")
				@ResponseBody
				public void applyDeleteCheckOk(
						HttpSession session,
						@RequestParam(value="deleteCheckList") String[] deleteCheckList,
						@RequestParam(value="clubApplyIdx") int clubApplyIdx,
						@PathVariable int clubSeq
						) throws Exception{
					SessionInfo info=(SessionInfo)session.getAttribute("member");
					
					Apply dto = new Apply();
					dto.setClubApplyIdx(clubApplyIdx);
					dto.setUserIdx(info.getUserIdx());
					for(int i=0;i<deleteCheckList.length;i++){
						dto.setHopeDate(deleteCheckList[i]);
						dto.setClub_seq(clubSeq);
						service.deleteMemList(dto);
					}
					return ;
				}
				
				// 동아리장이 봉사신청 수요처로 넘기기 // enabled를 3으로 수정해준다. 승인 대기상태
				@RequestMapping(value="/club/{clubSeq}/apply/applyCheckOk")
				@ResponseBody
				public void applyPass(
						@RequestParam(value="clubApplyIdx") int clubApplyIdx,
						@PathVariable int clubSeq
						) throws Exception{
					Map<String, Object> map = new HashMap<>();
					map.put("clubApplyIdx", clubApplyIdx);
					map.put("clubSeq", clubSeq);
					service.applyCheckOk(map);
					
					return ;
				}
}
	
	