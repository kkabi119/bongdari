package com.bong.demander.guest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	
	@RequestMapping(value="/demander/index/guest/list")
	@ResponseBody
	public Map<String, Object>  list(
			HttpSession session,
		    @RequestParam(value="pageNo", defaultValue="1") int current_page
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		int numPerPage=5;
		int dataCount=service.dataCount();
		System.out.println(dataCount);
		int total_page=myUtil.pageCount(numPerPage, dataCount);
		System.out.println("current_page:"+current_page+":"+dataCount+":"+total_page);
		if(current_page>total_page)
			current_page=total_page;
		
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		System.out.println(current_page+":"+start+":"+end);
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
		
		// �μ� 2��¥��
		String paging = myUtil.paging(current_page, total_page);
		
   	    // �۾� ����� json���� ����
		Map<String, Object> model = new HashMap<>(); 
		if(info==null)
			model.put("isLogin", "false");
		else
			model.put("isLogin", "true");
		// �����Ͱ���
		model.put("total_page", total_page);
		model.put("dataCount", dataCount);
		model.put("pageNo", current_page);
		model.put("paging", paging);
		// �Խù� ����Ʈ
		model.put("list", list);
		
		return model;
	}
	
	@RequestMapping(value="/demander/index/guest/created", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  createdSubmit(
			HttpSession session, Guest dto
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");

		// ���� �����(�α����� ���̵�)
		dto.setUserIdx(info.getUserIdx());
		service.insertGuest(dto);
		return list(session, 1);
	}
	
	@RequestMapping(value="/demander/index/guest/delete",
			method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  guestDelete(
			HttpSession session,
			@RequestParam(value="num") int num,
			@RequestParam(value="pageNo") int pageNo
			) throws Exception {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
	   	    // �α��� ���°� �ƴѰ��� json���� ����
			Map<String, Object> model = new HashMap<>(); 
			model.put("isLogin", "false");
			return model;
		}
		
		service.deleteGuest(num);
		
		return list(session, 1);
	}
}
