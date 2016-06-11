package com.bong.demander;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.demanderjoin.Demanderjoin;
import com.bong.demanderjoin.DemanderjoinService;
import com.bong.member.SessionInfo;


@Controller("bong.demanderController")
public class DemanderController {
	
	@Autowired
	private DemanderjoinService service;
	
	@Autowired
	private DemanderService mainService;
	
	
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="/main/demander")
	public ModelAndView demanderMain() throws Exception {
	ModelAndView mav = new ModelAndView(".layout.demander.main.수요처 전체 메인페이지");
		return mav;
	}

	/*검색버튼 눌렀을때 mainResult.jsp가 ajax로 삽입*/
	/*이 컨드롤러에서 검색결과도 보여주는 list 처리 */
	@RequestMapping(value="/main/demander/mainResult")
	public ModelAndView mainResult(
			HttpServletRequest req,
			@RequestParam(value="page",defaultValue="1") int current_page,
			@RequestParam(value="demanderType",defaultValue="") String demanderType,
			@RequestParam(value="demanderName",defaultValue="") String demanderName
			) throws Exception {
		System.out.println("******controller**"+demanderName+":"+demanderType);
		String cp = req.getContextPath();
		
   	    
		int numPerPage = 10;  // 한 화면에 보여주는 게시물 수
		int total_page = 0;
		int dataCount = 0;
   	    
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			demanderName = URLDecoder.decode(demanderName, "utf-8");
		}
		
        // 전체 페이지 수
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("demanderType", demanderType);
        map.put("demanderName", demanderName);
       
        dataCount = mainService.dataCount(map);
        
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
        List<Demander> searchList = mainService.deSearchList(map);

        // 리스트의 번호 ,해당 수요처의 seq 
        int listNum = 0;
        int n=0;
        Iterator<Demander> it=searchList.iterator();
     
        while(it.hasNext()) {
        	Demander data = it.next();
            listNum = dataCount - (start + n - 1);
            data.setListNum(listNum);
            n++;
            
            }
     
        String params = "";
        String urlList = cp+"/main/demander/main";
        String urlArticle = cp+"/main/demander/mainResult?page=" + current_page;
      
        
        //수요처 분야 :select option 처리 
        if(demanderType.length()!=0) {
        	params = "demanderType="+demanderType ;
        	             
        }
        //수요처 명: input text처리 
        if(demanderName.length()!=0) {
        	params = "demanderName=" + URLEncoder.encode(demanderName, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlList = cp+"/main/demander/main?" + params;
            
        }

        ModelAndView mav = new ModelAndView(".layout.demander.main.수요처 전체 메인페이지");
        mav.addObject("searchList", searchList); //검색결과 리스트
        mav.addObject("urlArticle", urlArticle); 
       /* mav.addObject("urlDemander", urlDemander); //검색결과의 수요처 클릭시 연결되는 페이지
*/        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));	
       
		return mav;
	}
	 
	@RequestMapping(value="/main/searchDemander")
	public ModelAndView searchDemander() throws Exception {
		System.out.println("***********searchDemander");
		ModelAndView mav = new ModelAndView(".four.demander.search.수요처 검색하기");
		return mav;
	}


	
	@RequestMapping(value="/demander/{demander_seq}/calendar")
	public ModelAndView demanderCal(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.calendar.수요처달력");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/photo")
	public ModelAndView demanderPhoto(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.photo.포토게시판");
		return mav;
	}
	
	
	
	@RequestMapping(value="/demander/{demander_seq}/schedule")
	public ModelAndView demanderSchedule(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.schedule.일정등록게시판");
		return mav;
	}
	
	
	@RequestMapping(value="/demander/{demander_seq}/guest")
	public ModelAndView demanderGuest(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.guest.방명록");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/admin")
	public ModelAndView demanderadmin(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.admin.관리자페이지");
		
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/bookmark")
	public ModelAndView deadminbmark(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.bookmark.관심등록");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/volList")
	public ModelAndView volList(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.admin.volList.나눔복지관");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1")
	public ModelAndView tab1(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/volList");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab2")
	public ModelAndView tab2(
			@PathVariable int demander_seq
			) throws Exception {
		
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1/showList")
	public ModelAndView showList(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/appliedMembers");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab1/eval")
	public ModelAndView eval(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView("/demander/dari/admin/eval");
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab3", method=RequestMethod.GET)
	public ModelAndView tab3(
			HttpSession session,
			@PathVariable int demander_seq
			) throws Exception {
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
		//세션에 있는 수요처 정보 가져오기
		SessionInfo info=(SessionInfo)session.getAttribute("member");

		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}
		
		Demanderjoin dto=service.readDemanderjoinInfo(Integer.toString(info.getUserIdx()));
		if(dto==null){
			session.invalidate();
			return new ModelAndView("redirect:/");
		}

		//수정폼
	ModelAndView mav = new ModelAndView("/demander/dari/admin/demanderUpdate");
	    mav.addObject("mode", "update");
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/admin/tab3", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView demanderUpdateSubmit(
			HttpSession session
		   ,Demanderjoin dto,
		   @PathVariable int demander_seq
		   ){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"serviceImg";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		service.updateDemander2(dto, pathname);
		
		ModelAndView mav=new ModelAndView("redirect:/demander/"+demander_seq+"/admin/admin");
	    
		return mav;
	}
}
