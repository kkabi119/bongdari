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
		
		System.out.println("controller 들어옴");
		String cp = req.getContextPath();
	   	    
			int numPerPage = 5;  // 한 화면에 보여주는 게시물 수
			int total_page = 0;
			int dataCount = 0;
	   	    
			if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
				searchValue = URLDecoder.decode(searchValue, "utf-8");
			}
			
	        // 전체 페이지 수
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("searchKey", searchKey);
	        map.put("searchValue", searchValue);
	        map.put("clubSeq",clubSeq);
	        
	        dataCount = service.dataCount(map);
	        
	        if(dataCount != 0)
	            total_page = myUtil.pageCount(numPerPage,  dataCount) ;

	        // 다른 사람이 자료를 삭제하여 전체 페이지수가 변화 된 경우
	        if(total_page < current_page) 
	            current_page = total_page;

	        // 리스트에 출력할 데이터를 가져오기
	        int start = (current_page - 1) * numPerPage + 1;
	        int end = current_page * numPerPage;
	        map.put("start", start);
	        map.put("end", end);

	        // 글 리스트
	        List<ClReview> list = service.listClReview(map);

	        //첫번째 사진 썸네일로 지정하기 
	        Pattern pattern=Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
	        
	        Matcher match;
	        
	        // 리스트의 번호
	        int listNum, n = 0;
	        Iterator<ClReview> it=list.iterator();
	        
	        String content=null; //글 미리보기 를 담을 변수
	        
	        while(it.hasNext()) {
	        	ClReview data = it.next();
	            listNum = dataCount - (start + n - 1);
	            data.setListNum(listNum);
	            
	            match=pattern.matcher(data.getContent());
	            if(match.find())
	            	data.setListImageName(match.group(1));
	            
	           content=data.getContent().replaceAll("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>","");
	           content=content.replaceAll("<p>&nbsp;</p>", ""); //태그가 너무 많아서 미리보기할때 글이 안떳다 그래서 가장 많은 p태그를 없애줌
	           content=content.replaceAll("<br>", " ");
	           data.setContent(content); //content 변수로 미리보기 하는게 아니라 그냥 dto안의 content로 찍는당
	           System.out.println("content:"+content);
		       n++;
	            
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
	        
	        ModelAndView mav=new ModelAndView(".four.club.dari.review.list.후기게시판");
	
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
	
		ModelAndView mav=new ModelAndView(".four.club.dari.review.create.후기게시판");
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
		System.out.println("글쓰기 컨트롤러로 옴 ");
		
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"review";
		
		System.out.println("글쓰기 컨트롤러 - USER정보받기 ");
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx()); //유저idx 저장 
		System.out.println("글쓰기 컨트롤러 - CLUB_SEQ 받기 ");
		dto.setClubSeq(clubSeq);
		
		System.out.println("유저 인덱스:"+dto.getUserId());
		
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
		//검색값 decode
		searchValue= URLDecoder.decode(searchValue,"utf-8");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubSeq", clubSeq); 
		System.out.println("clubSeq: "+clubSeq);
		
		map.put("num",num);
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		
		//조회수증가
		service.updateHitCount(map);
		
		ClReview preReadDto = service.preReadClReview(map);
		ClReview nextReadDto = service.nextReadClReview(map);
				
		//해당아티클가져오기
		ClReview dto=service.readClReview(map);
		System.out.println("listFile전");
		List<ClReview> listFile=service.listFile(map);
		
		if(dto==null)
			return new ModelAndView("redirect:/club/"+clubSeq+"/review/list");
			//return new ModelAndView("redirect:.club.index.review.list?page="+page);
				
		String params = "page="+page;
		if(searchValue.length()!=0) {
		    params += "&searchKey=" + searchKey + 
		                    "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		ModelAndView mav=new ModelAndView(".four.club.dari.review.article.후기게시판");
		mav.addObject("subMenu","6");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);
		mav.addObject("listFile", listFile);
		mav.addObject("page", page);
		mav.addObject("params", params);
        return mav;
	}
	
	//다운로드
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
		
		System.out.println("readFile 들어감");
		ClReview dto=service.readFile(map);
		System.out.println("readFile 넘어옴");
		
		boolean flag=false;
		if(dto!=null) {
			String saveFilename=dto.getSaveFilename();
			String originalFilename=dto.getOriginalFilename();
			flag=fileManager.doFileDownload(saveFilename, originalFilename, path, resp);
		}
		
		if(! flag) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			out.print("<script>alert('파일 다운로드가 실패했습니다.');history.back();</script>");
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
		
		System.out.println("readClReview 서비스 들어가기전");
		ClReview dto = service.readClReview(map);
		List<ClReview> listFile=service.listFile(map);
			
		if (info.getUserIdx()!=dto.getUserIdx())
			return new ModelAndView("redirect:/club/"+clubSeq+"/review/list?page="+page);

		ModelAndView mav=new ModelAndView(".four.club.dari.review.create.후기게시판");
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
		// 수정 하기
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
		System.out.println("deleteFile 컨트롤러 빠져나감 ");
		model.put("state", "true");
		
		return model;
	}
	
////////////////////////////////////////////////////////////////////		게시글 삭제 
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

		// 해당 레코드 가져 오기
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
		
		System.out.println("서비스 delete 들어가기 전 ClubReviewIdx ="+dto.getClubReviewIdx());
		
		service.deleteClReview(dto, dto.getSaveFilename(), path);

		System.out.println("갔다옴");
		return new ModelAndView("redirect:/club/"+clubSeq+"/review/list?page="+page);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	게시글의 좋아요 처리 
	
	/////////////// 	게시글의 좋아요추가
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
		
		if(state==0){ // 게시물의 좋아요가 아직 안누러졌다면 > 1추가 
			dto.setUserIdx(info.getUserIdx());
			service.insertLike(dto);
			System.out.println("게시물의 좋아요를 추가하였습니다 ");
			
		}else if(state==1){ //이미 좋아요를 눌렀다면 -1 
			dto.setUserIdx(info.getUserIdx());
			service.deleteLike(dto);
			System.out.println("게시물의 좋아요를 취소하였습니다 ");
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		model.put("state", state);
		System.out.println("게시물 좋아요 컨트롤러 성공하고 나감");
		return model;
	}
	
	////////////////////////////////////////////////////////////////////		게시글의 좋아요/싫어요 개수
	@RequestMapping(value="/club/{clubSeq}/review/countLike",   	method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  countLike(	
			@RequestParam(value="clubReviewIdx") int clubReviewIdx,
			ClReview dto,
			@PathVariable int clubSeq) 
			throws Exception {
		
		int likeCount=0;

		System.out.println("게시글 좋아요 세기 컨트롤러 ) club리뷰 인덱스: "+clubReviewIdx);
		
		dto.setClubSeq(clubSeq);
		dto.setClubReviewIdx(clubReviewIdx);
		
		Map<String, Object> map=service.countLike(dto);
		
		if(map!=null) {
			// resultType이 map인 경우 int는 BigDecimal로 넘어옴
			likeCount=((BigDecimal)map.get("LIKECOUNT")).intValue();
		}
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		System.out.println();
		model.put("likeCount", likeCount);
		
		return model;
	}
	
	//////--------------------------------------------------------------------------------------		댓글 관련
	
	//////////////////////////////////////////////////////////////////				댓글리스트
	@RequestMapping(value="/club/{clubSeq}/review/listReply")
	public ModelAndView listReply(
			@RequestParam(value="num") int num, //ClubReviewIdx가 넘어왔다
			@RequestParam(value="pageNo", defaultValue="1") int current_page
			,@PathVariable int clubSeq
			) throws Exception {
		
		int numPerPage=5;
		int total_page=0;
		int dataCount=0;
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("clubReviewIdx", num);
		map.put("clubSeq", clubSeq);
		
		dataCount=service.replyDataCount(map); //댓글 개수 세기 -clubReviewIdx넘김
		total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		// 리스트에 출력할 데이터
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		map.put("start", start);
		map.put("end", end);
		List<ClReviewReply> listReply=service.listReply(map);
		
		// 엔터를 <br>
		Iterator<ClReviewReply> it=listReply.iterator();
		int listNum, n=0;
		
		while(it.hasNext()) {
			
			ClReviewReply dto=it.next();
			listNum=dataCount-(start+n-1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			n++;
		}
		// 페이징처리(인수2개 짜리 js로 처리)
		String paging=myUtil.paging(current_page, total_page);
		
		ModelAndView mav=new ModelAndView("/club/dari/review/listReply");
		
		// jsp로 넘길 데이터
		mav.addObject("listReply", listReply);
		mav.addObject("pageNo", current_page);
		mav.addObject("replyCount", dataCount); //
		mav.addObject("total_page", total_page);
		mav.addObject("paging", paging);
		
		return mav;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////					 대댓글 리스트
			@RequestMapping(value="/club/{clubSeq}/review/listReplyAnswer")
			public ModelAndView listReplyAnswer(	
					@RequestParam(value="answer") int answer
					,@PathVariable int clubSeq
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("answer", answer);
				map.put("clubSeq", clubSeq);

				List<ClReviewReply> listReplyAnswer=service.listReplyAnswer(map);
	
				// 엔터를 <br>
				Iterator<ClReviewReply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {
					ClReviewReply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/club/dari/review/listReplyAnswer");

				// jsp로 넘길 데이터
				mav.addObject("listReplyAnswer", listReplyAnswer);
				return mav;
			}
	
			@RequestMapping(value="/club/{clubSeq}/review/replyCount", method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> replyCount(	
					@RequestParam(value="num") int num ,@PathVariable int clubSeq
					) throws Exception {
				// AJAX(JSON) - 댓글별 개수

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
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
		
				model.put("count", count);
				return model;
			}
			
			// 댓글 + 대댓글 추가
			@RequestMapping(value="/club/{clubSeq}/review/createdReply", method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  createdReply(	HttpSession session,
					ClReviewReply dto ,@PathVariable int clubSeq) 
					throws Exception {
				
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				String state="true";
				if(info==null) { // 로그인이 되지 않는 경우
					state="loginFail";
				}
				else {
					dto.setUserIdx(info.getUserIdx());
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
			
			// 댓글 및 대댓글 삭제
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
			
	//*********								댓글 좋아요											*************
			//좋아요
			@RequestMapping(value="/club/{clubSeq}/review/insertReplyLike",	method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> insertReplyLike(
					HttpSession session,
					ClReviewReply dto
					,@PathVariable int clubSeq)
				throws Exception {
				
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				System.out.println("컨트롤러-좋아요 입성");
				
				dto.setUserIdx(info.getUserIdx());
				dto.setClubSeq(clubSeq);
								
				int state=service.stateReplyLike(dto);
				
				System.out.println("컨트롤러state:"+state);
				if(state==0){ //아직 좋아요가 없을때 !
					
					dto.setUserIdx(info.getUserIdx());
					service.insertReplyLike(dto);
					
				}else if(state==1){ //이미 좋아요를 한 상태일때!
					
					dto.setUserIdx(info.getUserIdx());
					service.deleteReplyLike(dto);
					System.out.println("좋아요 취소햇다(컨트롤러)");
				}
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				return model;
			}
			
			// 좋아요/싫어요 개수
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
				System.out.println("replyCountLike에 들어가기전 replyNum="+replyNum);
				
				Map<String, Object> resultMap=service.replyCountLike(map);
				
				if(resultMap!=null) {
					
					// resultType이 map인 경우 int는 BigDecimal로 넘어옴
					likeCount=((BigDecimal)resultMap.get("LIKECOUNT")).intValue();
				}
			
				
				System.out.println("likecount="+likeCount);
				
				Map<String, Object> model = new HashMap<>(); 
				model.put("likeCount", likeCount);
				
				return model;
			}
			
	
}
