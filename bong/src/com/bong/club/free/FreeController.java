package com.bong.club.free;

import java.io.File;
import java.io.PrintWriter;
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
	
	/*개인동아리 자유게시판*/
	@RequestMapping(value="/club/{clubSeq}/free/list")
	public ModelAndView ListClubFree(
			HttpServletRequest req,
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@PathVariable int clubSeq
			) throws Exception {
		
		int numPerPage   = 8;  // 한 화면에 보여주는 게시물 수
		int total_page = 0;
		int dataCount = 0;
		
		// 전체 페이지 수
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clubSeq", clubSeq);
        
        dataCount = service.dataCount(map);
        if(dataCount != 0){
        	if(dataCount > 0) {
    			if(dataCount % numPerPage == 0)
    				total_page=dataCount/numPerPage;
    			else
    				total_page=dataCount/numPerPage+1;
    		}
        }
        
        // 다른 사람이 자료를 삭제하여 전체 페이지수가 변화 된 경우
        if(total_page < current_page) 
            current_page = total_page;
        
        ModelAndView mav = new ModelAndView(".four.club.dari.free.list.자유게시판");
        mav.addObject("clubSeq",clubSeq);
        mav.addObject("total_page", total_page);
        mav.addObject("subMenu","5");
             
		return mav;
	}
	
	/* list Ajax 용 */
	@RequestMapping(value="/club/{clubSeq}/free/list2")
	public ModelAndView ListClubFree2(
			HttpServletRequest req,
			@PathVariable int clubSeq,
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		current_page++;
		String cp=req.getContextPath();
		
		int numPerPage   = 8;  // 한 화면에 보여주는 게시물 수
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
        if(dataCount != 0){
        	if(dataCount > 0) {
    			if(dataCount % numPerPage == 0)
    				total_page=dataCount/numPerPage;
    			else
    				total_page=dataCount/numPerPage+1;
    		}
        }
        // 다른 사람이 자료를 삭제하여 전체 페이지수가 변화 된 경우
        if(total_page < current_page) 
            current_page = total_page;

        // 리스트에 출력할 데이터를 가져오기
        int start = (current_page - 1) * numPerPage + 1;
        int end = current_page * numPerPage;
        map.put("start", start);
        map.put("end", end);
        
        // 글 리스트
        List<Free> list = service.listFree(map);
        
        // 리스트의 번호, 리스트상의 콘텐츠에서 이미지 없애기, 첫번째사진 썸내일로 올리기
        Pattern pattern=Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
        Matcher match;
        String content=null;
        int listNum, n = 0;
        Iterator<Free> it=list.iterator();
        while(it.hasNext()) {
            Free data = it.next();
            listNum = dataCount - (start + n - 1);
            
            data.setListNum(listNum);
            match=pattern.matcher(data.getContent());
            
            if(match.find())
            	data.setListImageName(match.group(0));
            
            content=data.getContent().replaceAll("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>","");
           
            if(content.length()<50){
            	 data.setContent(content);
                 n++;
            }
            else {
            content=content.substring(0, 50);
            data.setContent(content);
            n++;
            }
        } 
        String params = "";
        String urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page;
        if(searchValue.length()!=0) {
        	params = "searchKey=" +searchKey + 
        	             "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlArticle = cp+"/club/"+clubSeq+"/free/article?page=" + current_page + "&"+ params;
        }
        
        ModelAndView mav = new ModelAndView("club/dari/free/list2");
		
        mav.addObject("list", list);
        mav.addObject("clubSeq",clubSeq);
        mav.addObject("urlArticle", urlArticle);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        
		return mav;
	}
	
	/* 자유게시판 글쓰기 폼 */
	@RequestMapping(value="/club/{clubSeq}/free/created",method=RequestMethod.GET)
	public ModelAndView insertFreeForm(
			HttpSession session,
			@PathVariable int clubSeq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.created.자유글쓰기");
		mav.addObject("mode", "created");
		mav.addObject("clubSeq", clubSeq);
		mav.addObject("subMenu","5");
		return mav;
	}
	
	
	/* 자유게시판 글쓰기 폼 */
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
	
	@RequestMapping(value="/club/{clubSeq}/free/download")
	public void download(
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session,
			@PathVariable int clubSeq,
			@RequestParam(value="num") int num
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			resp.sendRedirect(cp+"/member/login");
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		String root=session.getServletContext().getRealPath("/");
		String path=root+File.separator+"uploads"+File.separator+"Free";
		Free dto=service.readFree(map);
		boolean flag=false;
		
		if(dto!=null) {
			flag=fileManager.doFileDownload(
					     dto.getSaveFilename(), 
					     dto.getOriginalFilename(), path, resp);
		}
		
		if(! flag) {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			out.print("<script>alert('파일 다운로드가 실패했습니다.');history.back();</script>");
		}
	}
	
	/*개인동아리 자유게시판 끝*/
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
		
		// 조회수 증가
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("clubSeq", clubSeq);
		service.updateHitCount(map);

		// 해당 레코드 가져 오기
		Free dto = service.readFree(map);

		if(dto==null)
			return new ModelAndView("redirect:.club.{clubSeq}.free.list?page="+page);
        
		// 이전 글, 다음 글
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
		
		ModelAndView mav = new ModelAndView(".four.club.dari.free.article.자유게시판 글보기");
		mav.addObject("dto", dto);
		mav.addObject("preReadDto", preReadDto);
		mav.addObject("nextReadDto", nextReadDto);

		mav.addObject("page", page);
		mav.addObject("subMenu","5");
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
		
		ModelAndView mav=new ModelAndView(".four.club.dari.free.created.자유글수정");
		mav.addObject("dto", dto);
		mav.addObject("clubSeq",clubSeq);
		mav.addObject("mode", "update");
		mav.addObject("subMenu","5");
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
	
		// 수정 하기
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
		
		// 해당 레코드 가져 오기
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
	
	// 댓글 및 리플별 답글 추가
			@RequestMapping(value="/club/{clubSeq}/free/createdReply",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  createdReply(
					HttpSession session,
					@RequestParam int num,
					@PathVariable int clubSeq,
					Reply dto
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				String state="true";
				if(info==null) { // 로그인이 되지 않는 경우
					state="loginFail";
				} else {
					dto.setUserIdx(info.getUserIdx());
					dto.setClubFreeIdx(num);
					
					int result=service.insertReply(dto);
					if(result==0)
						state="false";
				}
				
		   	    // 작업 결과를 json으로 전송
				Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				model.put("clubSeq", clubSeq);
				return model;
			}
			
			// 댓글 리스트
			@RequestMapping(value="/club/{clubSeq}/free/listReply")
			public ModelAndView listReply(
					@RequestParam(value="num") int num,
					@PathVariable int clubSeq,
					@RequestParam(value="pageNo", defaultValue="1") int current_page
					) throws Exception {
				int numPerPage=5;
				int total_page=0;
				int dataCount=0;
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("clubSeq", clubSeq);
				map.put("num", num);
				
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
				
				ModelAndView mav=new ModelAndView("/club/dari/free/listReply");
				
				// jsp로 넘길 데이터
				mav.addObject("listReply", listReply);
				mav.addObject("pageNo", current_page);
				mav.addObject("replyCount", dataCount);
				mav.addObject("total_page", total_page);
				mav.addObject("paging", paging);
				
				return mav;
			}
	
	// 댓글 및 댓글별답글 삭제
			@RequestMapping(value="/club/{clubSeq}/free/deleteReply",
					method=RequestMethod.POST)
			@ResponseBody	
			public Map<String, Object>  deleteReply(
					HttpSession session,
					@PathVariable int clubSeq,
					@RequestParam(value="replyNum") int replyNum,
					@RequestParam(value="mode") String mode
					) throws Exception {
				SessionInfo info=(SessionInfo) session.getAttribute("member");
				
				String state="true";
				if(info==null) { // 로그인이 되지 않는 경우
					state="loginFail";
				} else {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("clubSeq", clubSeq);
					map.put("mode", mode);
					map.put("replyNum", replyNum);

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
			
			// 댓글별 답글 개수
			@RequestMapping(value="/club/{clubSeq}/free/replyCountAnswer",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object>  replyCountAnswer(
					@PathVariable int clubSeq,
					@RequestParam(value="answer") int answer
					) throws Exception {
				
				int count=0;
				Map<String , Object> map = new HashMap<String, Object>();
				map.put("clubSeq", clubSeq);
				map.put("answer", answer);
				count=service.replyCountAnswer(map);
				
		   	    // 작업 결과를 json으로 전송
							
				map.put("count", count);
				return map;
			}
			
			@RequestMapping(value="/club/{clubSeq}/free/replyCount",
					method=RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> replyCount(
					@PathVariable int clubSeq,
					@RequestParam(value="num") int num
					) throws Exception {
				// AJAX(JSON) - 댓글별 개수

				String state="true";
				int count=0;
		        Map<String, Object> map=new HashMap<String, Object>();
		        map.put("clubSeq", clubSeq);
		   		map.put("num", num);
		  	    
		   	    count=service.replyDataCount(map);
		   	    
		   	    Map<String, Object> model = new HashMap<>(); 
				model.put("state", state);
				model.put("count", count);
				
				return model;
			}
			
			// 댓글별 답글 리스트
			@RequestMapping(value="/club/{clubSeq}/free/listReplyAnswer")
			public ModelAndView listReplyAnswer(
					@PathVariable int clubSeq,
					@RequestParam(value="answer") int answer
					) throws Exception {
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("clubSeq", clubSeq);
				map.put("answer", answer);
				
				List<Reply> listReplyAnswer=service.listReplyAnswer(map);
				
				// 엔터를 <br>
				Iterator<Reply> it=listReplyAnswer.iterator();
				while(it.hasNext()) {
					Reply dto=it.next();
					dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
				}
				
				ModelAndView mav=new ModelAndView("/club/dari/free/listReplyAnswer");

				// jsp로 넘길 데이터
				mav.addObject("listReplyAnswer", listReplyAnswer);
				
				return mav;
			}	
}
