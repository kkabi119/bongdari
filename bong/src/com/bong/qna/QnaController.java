package com.bong.qna;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;

@Controller("bong.qnaController")
public class QnaController {
   @Autowired
   private QnaService service;
   @Autowired
   private MyUtil myUtil;
   
   //리스트
   @RequestMapping(value="/qna/list")
   public ModelAndView qnaList(
		   HttpServletRequest req
		  ,@RequestParam(value= "page", defaultValue="1") int current_page
		  ,@RequestParam(value= "searchKey", defaultValue="subject") String searchKey
		  ,@RequestParam(value= "searchValue", defaultValue="") String searchValue
		   ) throws Exception {
	   
	   String cp = req.getContextPath();
	   
	   int numPerPage = 10; // 한 화면에 보여줄 게시물 개수
	   int total_page = 0;
	   int dataCount  = 0;
	   
	   if(req.getMethod().equalsIgnoreCase("GET")){
		   //GET 방식인 경우
		   searchValue = URLDecoder.decode(searchValue,"utf-8");
	   }
	   
	   // 전체 페이지 수 
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("searchKey", searchKey);
	   map.put("searchValue", searchValue);
	   
	   dataCount = service.dataCount(map);
	   
	   if(dataCount !=0)
		   total_page = myUtil.pageCount(numPerPage, dataCount);
	   
	   // 다른 사람이 자료 삭제 하여 전체 페이지 수 변화한 경우
	   if(total_page < current_page)
		   current_page = total_page;
	   
	   // 리스트에 출력할 데이터 가져오기
	   int start = (current_page - 1)*numPerPage+1;
	   int end   = current_page*numPerPage;
	   map.put("start", start);
	   map.put("end", end);
	   
	   List<Qna> list = service.listQna(map);
	   int listNum, n = 0;
	   Iterator<Qna> it = list.iterator();
	   
	   while(it.hasNext()){
		   Qna dto = it.next();
		   listNum = dataCount - (start+n-1);
		   dto.setListNum(listNum);
		   dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		   n++;
	   }
	   
	   String params = "";
	   String urlList = cp + "/qna/list";
	   String urlArticle = cp+"/qna/article?page="+current_page;
	   
	   if(searchValue.length() != 0){
		   params = "searchKey="+searchKey+"&searchValue="+URLEncoder.encode(searchValue, "utf-8");
	   }
	   
	   if(params.length() != 0){
		   urlList = cp + "/qna/list?"+params;
		   urlArticle = cp+"/qna/article?page="+current_page+"&"+params;
	   }
	   ModelAndView mav = new ModelAndView(".layout.customer.qna.list.Q&A 게시판");
	   mav.addObject("list", list);
	   mav.addObject("page", current_page);
	   mav.addObject("total_page", total_page);
	   mav.addObject("urlArticle", urlArticle);
	   mav.addObject("dataCount", dataCount);
	   mav.addObject("paging", myUtil.paging(current_page, total_page,urlList));
	   return mav;
   }
   // 질문 글쓰기
   @RequestMapping(value="/qna/create", method=RequestMethod.GET)
   public ModelAndView createForm() throws Exception{
	   ModelAndView mav = new ModelAndView(".layout.customer.qna.create.Q&A 게시판");
	   mav.addObject("mode", "create");
	   return mav;
   }
   @RequestMapping(value="/qna/create", method=RequestMethod.POST)
   public ModelAndView createSubmit(
		   HttpSession session
		  ,Qna dto) throws Exception{
	   SessionInfo info = (SessionInfo) session.getAttribute("member");
	   
	   dto.setUserId(info.getUserId());
	   dto.setUserIdx(info.getUserIdx());
	   dto.setQuserIdx(dto.getUserIdx());
	   
	   service.insertQna(dto, "create");
	  
	   return new ModelAndView("redirect:/qna/list");	   
   }
   // 글보기
   @RequestMapping(value= "/qna/article")
   public ModelAndView qnaArticle(
		   HttpSession session
		  ,@RequestParam(value="num") int num
		  ,@RequestParam(value="page") int page
		  ,@RequestParam(value="searchKey", defaultValue="subject") String searchKey
		  ,@RequestParam(value="searchValue", defaultValue="") String searchValue
		   ) throws Exception{
	   SessionInfo info= (SessionInfo) session.getAttribute("member");
	   
	   //검색값 decode
	   searchValue = URLDecoder.decode(searchValue,"utf-8");
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("qnaIdx", num);
	   
	   //조회수 증가
	   service.updateHitCount(map);
	   
	   // 아티클 가져오기
	   Qna dto= service.readQna(map);
	   
	   if(dto==null){
		   return new ModelAndView("redirect:/qna/list");
	   }
	   
	   String params = "page="+page;
	   if(searchValue.length()!=0){
		   params += "&searchKey="+searchKey+"&searchValue="+URLEncoder.encode(searchValue,"utf-8");
	   }
	   
	   String amode="article";
	   if(dto.getAnswer()!=0){ //답변 article이면
		   amode="reply"; 
	   }
	   ModelAndView mav = new ModelAndView(".layout.customer.qna.article.Q&A 게시판");
	   mav.addObject("dto", dto);
	   mav.addObject("page", page);
	   mav.addObject("amode", amode);
	   mav.addObject("params", params);	   
	   return mav;
   }
   
   //수정하기
   @RequestMapping(value="/qna/update", method=RequestMethod.GET)
   public ModelAndView updateForm(
		   HttpSession session
		  ,@RequestParam(value="num") int num
		  ,@RequestParam(value="page") String page		  
		   ) throws Exception{
	   
	   SessionInfo info = (SessionInfo) session.getAttribute("member");
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("qnaIdx", num);
	   
	   Qna dto = service.readQna(map);
	   
	   if(info.getUserIdx() != dto.getUserIdx())
		   return new ModelAndView("redirect:/qna/list?page="+page);
	   ModelAndView mav = new ModelAndView(".layout.customer.qna.create.Q&A 게시판");
	   mav.addObject("mode", "update");
	   mav.addObject("page", page);
	   mav.addObject("dto", dto);
	   return mav;
   }
   @RequestMapping(value="/qna/update", method=RequestMethod.POST)
   public String updateSubmit(
		   Qna dto
		  ,@RequestParam(value="page") String page) throws Exception{
	   
	   // 수정
     service.updateQna(dto);
     
     return "redirect:/qna/list?page="+page;
   }
   //글 삭제
   @RequestMapping(value="/qna/delete")
   public ModelAndView delete(
		   HttpSession session
		  ,@RequestParam(value="num") int num
		  ,@RequestParam(value="page") String page
		   ) throws Exception{
	   SessionInfo info = (SessionInfo) session.getAttribute("member");
	   
	   // 해당 레코드 가져오기
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("qnaIdx", num);
	   
	   Qna dto = service.readQna(map);
	   if(dto == null) {
		   return new ModelAndView("redirect:/qna/list?page="+page);
	   }
	   
	   if(!info.getUserId().equals(dto.getUserId()) && !info.getUserId().equals("admin")){
		   return new ModelAndView("redirect:/qna/list?page="+page);
	   } // 작성자가 아니거나 관리자가아니라면 글 삭제 못하도록 
	   
	   service.deleteQna(map);
	   
	   return new ModelAndView("redirect:/qna/list?page="+page);
   }
   //답변 작성
   @RequestMapping(value="/qna/reply", method=RequestMethod.GET)
   public ModelAndView replyForm(
		   HttpSession session
		  ,@RequestParam(value="num") int num
		  ,@RequestParam(value="page") String page
		   ) throws Exception{
	   SessionInfo info = (SessionInfo) session.getAttribute("member");
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("qnaIdx", num);
	   
	   if(info==null){
		   return new ModelAndView("redirect:/member/login");
	   }
	   
	   Qna dto = service.readQna(map);
	   if(dto==null){
		   return new ModelAndView("redirect:/qna/list?page="+page);
	   }
	   
	   String str = "["+dto.getSubject()+"] 에 대한 답변입니다.\n";
	   dto.setContent(str);
	   dto.setAnswer(dto.getQnaIdx());
	   
	   ModelAndView mav = new ModelAndView(".layout.customer.qna.create.Q&A 게시판");
	   mav.addObject("dto", dto);
	   mav.addObject("page", page);
	   mav.addObject("mode", "reply");
	   return mav;
   }
   @RequestMapping(value = "/qna/reply", method=RequestMethod.POST)
   public String replySubmit(
		   HttpSession session
		  ,Qna dto
		  ,@RequestParam(value="num") int num
		  ,@RequestParam(value="page") String page
		   ) throws Exception{
     SessionInfo info = (SessionInfo) session.getAttribute("member");
     
     dto.setUserIdx(info.getUserIdx());
     dto.setAnswer(num);
     int quserIdx= 0;
     
     if(dto.getAnswer()!=0){ // 답변 article일 경우
    	 quserIdx=service.quserIdx(dto);
     }
     dto.setQuserIdx(quserIdx);
     service.insertQnaReply(dto, num);
     
     return "redirect:/qna/list?page="+page;
   }
}
