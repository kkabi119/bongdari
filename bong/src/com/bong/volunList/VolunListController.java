package com.bong.volunList;

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


@Controller("bong.volunListController")
public class VolunListController {
	
	@Autowired
	private VolunListService service;
	
	
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="/main/volunList")
	public ModelAndView volunList(
			HttpServletRequest req,
			@RequestParam(value="page",defaultValue="1") int current_page,
			@RequestParam(value="searchKey", defaultValue="subject") String searchKey,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		
		String cp = req.getContextPath();
		
   	    
		int numPerPage = 10;  // 한 화면에 보여주는 게시물 수
		int total_page = 0;
		int dataCount = 0;
   	    
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}
		
        // 전체 페이지 수
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchKey", searchKey);
        map.put("searchValue", searchValue);
       
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
        List<VolunList> searchList = service.volunSearchList(map);

        // 리스트의 번호 ,해당 수요처의 seq 
        int listNum = 0;
        int n=0;
        Iterator<VolunList> it=searchList.iterator();
     
        while(it.hasNext()) {
        	VolunList data = it.next();
        	 n++;
        	 listNum=n;
        	// listNum = dataCount - (start + n - 1);
            data.setListNum(listNum);
           
            
            }
     
        String params = "";
        String urlList = cp+"/main/volunList";
        // String urlArticle = cp+"/main/demander/mainResult?page=" + current_page;
      
        
        if(searchValue.length()!=0) {
        	params = "searchValue=" + URLEncoder.encode(searchValue, "utf-8");	
        }
        
        if(params.length()!=0) {
            urlList = cp+"/main/volunList?" + params;
            
        }

        ModelAndView mav = new ModelAndView(".layout.customer.volunList.봉사활동검색");
        mav.addObject("searchList", searchList);
        mav.addObject("page", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("total_page", total_page);
        mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));	
       
		return mav;
	}
	
	
}
