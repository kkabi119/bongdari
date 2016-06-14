package com.bong.demander;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.demanderjoin.DemanderjoinService;


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
	ModelAndView mav = new ModelAndView(".layout.demander.main.수요처 검색");
		return mav;
	}

	/*수요처 전체페이지=검색페이지*/
	/*이 컨드롤러에서 검색결과도 보여주는 list 처리 */
	@RequestMapping(value="/main/demander/mainResult")
	public ModelAndView mainResult(
			HttpServletRequest req,
			@RequestParam(value="page",defaultValue="1") int current_page,
			@RequestParam(value="sido",defaultValue="") String sido,
			@RequestParam(value="addr",defaultValue="") String addr,
			@RequestParam(value="demanderType",defaultValue="") String demanderType,
			@RequestParam(value="demanderName",defaultValue="") String demanderName
			) throws Exception {
		
		System.out.println("******sido:"+sido+"/addr:"+addr);
		System.out.println("******ggdemanderName:"+demanderName+"/demanderType:"+demanderType);
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
        map.put("sido", sido);
        map.put("addr", addr);
       
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
        	 n++;
        	 listNum=n;
        	// listNum = dataCount - (start + n - 1);
            data.setListNum(listNum);
           
            
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

        ModelAndView mav = new ModelAndView(".layout.demander.main.수요처 검색");
        mav.addObject("searchList", searchList); //검색결과 리스트
        mav.addObject("urlArticle", urlArticle); 
       /* mav.addObject("urlDemander", urlDemander); //검색결과의 수요처 클릭시 연결되는 페이지
*/        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));	
       
		return mav;
	}
	 
/*	@RequestMapping(value="/main/searchDemander")
	public ModelAndView searchDemander() throws Exception {
		System.out.println("***********searchDemander");
		ModelAndView mav = new ModelAndView(".four.demander.search.수요처 검색하기");
		return mav;
	}
*/

	
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
	
	
}
