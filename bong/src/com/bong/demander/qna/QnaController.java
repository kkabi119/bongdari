package com.bong.demander.qna;

import java.io.File;
import java.net.URLDecoder;
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

import com.bong.common.FileManager;
import com.bong.common.MyUtil;
import com.bong.demander.review.DeReview;
import com.bong.member.SessionInfo;

@Controller("bong.deQnaController")
public class QnaController {
	@Autowired
	private QnaService service;
	@Autowired
	private MyUtil myUtil;
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/demander/index/qna/list")
	public ModelAndView deQnaList(
			HttpServletRequest req,
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@RequestParam(value="searchValue", defaultValue="") String searchValue
			) throws Exception {
		
		int numPerPage = 10;  // �� ȭ�鿡 �����ִ� �Խù� ��
		int total_page = 0;
		int dataCount = 0;
   	    
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET ����� ���
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}

        // ��ü ������ ��
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchValue", searchValue);

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

        List<Qna> list = service.listQna(map);
        Iterator<Qna> it=list.iterator();
        while(it.hasNext()) {
        	Qna dto=it.next();
        	dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
        }
        
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.list.QnA �Խ���");
        mav.addObject("list", list);
        mav.addObject("pageNo", current_page);
        mav.addObject("dataCount", dataCount);
        mav.addObject("paging", myUtil.paging(current_page, total_page));
		return mav;
	}
	
	@RequestMapping(value="/demander/index/qna/create",method=RequestMethod.GET)
	public ModelAndView deRevCreateForm(
			) throws Exception {
		ModelAndView mav=new ModelAndView(".four.demander.dari.qna.create.qna�Խ���");
		mav.addObject("mode", "created");
		return mav;
	}
	
	@RequestMapping(value="/demander/index/qna/create",method=RequestMethod.POST)
	public ModelAndView deRevCreateSubmit(
			HttpSession session,
			Qna dto
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		
		service.insertQna(dto);
		return new ModelAndView("redirect:/demander/index/qna/list");
		
	}
	@RequestMapping(value="/demander/index/qna/article")
	public ModelAndView deQnaArticle() throws Exception {
		
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.QnA �Խ���");
		return mav;
	}

}
