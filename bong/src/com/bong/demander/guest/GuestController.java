package com.bong.demander.guest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;


@Controller("guest.guestController")
public class GuestController {
	@Autowired
	private GuestService service;
	@Autowired
	private MyUtil myUtil;

	
	@RequestMapping(value="/demander/{demander_seq}/guest/list")
	@ResponseBody
	public Map<String, Object>  list(
			HttpSession session,
		    @RequestParam(value="pageNo", defaultValue="1") int current_page,
		    @PathVariable int demander_seq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("demander_seq", demander_seq);
		
		int numPerPage=5;
		int dataCount=service.dataCount(demander_seq);
		System.out.println(dataCount);
		int total_page=myUtil.pageCount(numPerPage, dataCount);
		if(current_page>total_page)
			current_page=total_page;
		
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		
		map.put("start", start);
		map.put("end", end);
		int listNum, n = 0;
		
		List<Guest> list=service.listGuest(map);
		Iterator<Guest> it=list.iterator();
		while(it.hasNext()) {
			Guest dto=it.next();
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			listNum = dataCount - (start + n - 1);
            dto.setListNum(listNum);
            n++;			
		}
		
		// 인수 2개짜리
		String paging = myUtil.paging(current_page, total_page);
		
   	    // 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>(); 
		if(info==null)
			model.put("isLogin", "false");
		else
			model.put("isLogin", "true");
		// 데이터개수
		model.put("total_page", total_page);
		model.put("dataCount", dataCount);
		model.put("pageNo", current_page);
		model.put("paging", paging);
		// 게시물 리스트
		model.put("list", list);
		model.put("demander_seq", demander_seq);
		
		return model;
	}
	
	@RequestMapping(value="/demander/{demander_seq}/guest/created", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  createdSubmit(
			HttpSession session, Guest dto,
			@PathVariable int demander_seq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");

		// 글을 쓴사람(로그인한 아이디)
		dto.setUserIdx(info.getUserIdx());
		dto.setDemander_seq(demander_seq);
		service.insertGuest(dto);
		return list(session, 1, demander_seq);
	}
	
	@RequestMapping(value="/demander/{demander_seq}/guest/delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  guestDelete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo") int pageNo,
			@PathVariable int demander_seq
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
	   	    // 로그인 상태가 아닌것을 json으로 전송
			Map<String, Object> model = new HashMap<>(); 
			model.put("isLogin", "false");
			return model;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("sguestIdx", num);
		map.put("demander_seq", demander_seq);
		service.deleteGuest(map);
		
		return list(session, 1, demander_seq);
	}
}
