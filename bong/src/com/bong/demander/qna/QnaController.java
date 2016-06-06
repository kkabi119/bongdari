package com.bong.demander.qna;

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
import org.springframework.web.servlet.ModelAndView;

import com.bong.common.MyUtil;
import com.bong.member.SessionInfo;

@Controller("bong.deQnaController")
public class QnaController {
	@Autowired
	private QnaService service;
	@Autowired
	private MyUtil myUtil;

	@RequestMapping(value = "/demander/{demander_seq}/qna/list")
	public ModelAndView deQnaList(HttpServletRequest req,
			@RequestParam(value = "page", defaultValue = "1") int current_page,
			@RequestParam(value = "searchKey", defaultValue = "subject") String searchKey,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue,
			@PathVariable int demander_seq
			) throws Exception {

		String cp = req.getContextPath();
		
		int numPerPage = 10; // �� ȭ�鿡 �����ִ� �Խù� ��
		int total_page = 0;
		int dataCount = 0;

		if (req.getMethod().equalsIgnoreCase("GET")) { // GET ����� ���
			searchValue = URLDecoder.decode(searchValue, "utf-8");
		}

		// ��ü ������ ��
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("demander_seq", demander_seq);

		dataCount = service.dataCount(map);
		
		if (dataCount != 0)
			total_page = myUtil.pageCount(numPerPage, dataCount);

		// �ٸ� ����� �ڷḦ �����Ͽ� ��ü ���������� ��ȭ �� ���
		if (total_page < current_page)
			current_page = total_page;

		// ����Ʈ�� ����� �����͸� ��������
		int start = (current_page - 1) * numPerPage + 1;
		int end = current_page * numPerPage;
		map.put("start", start);
		map.put("end", end);

		List<Qna> list = service.listQna(map);
		int listNum, n = 0;
		Iterator<Qna> it = list.iterator();

		while (it.hasNext()) {
			Qna dto = it.next();
			listNum = dataCount - (start + n - 1);
			dto.setListNum(listNum);
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			n++;
		}
		
		String params = "";
		String urlList = cp + "/demander/"+demander_seq+"/qna/list";
		String urlArticle = cp + "/demander/"+demander_seq+"/qna/article?page="+current_page;
		if(searchValue.length() != 0) {
			params = "searchKey=" + searchKey + "&searchValue="+URLEncoder.encode(searchValue, "utf-8");
		}

		if(params.length() != 0) {
			urlList = cp + "/demander/"+demander_seq+"/qna/list?" + params;
			urlArticle = cp + "/demander/"+demander_seq+"/qna/article?page=" + current_page + "&" + params;
		}
		
		//int secret=0;
		/*int quserIdx=0;
		int groupNum=dto.getGroupNum();
		if(dto.getAnswer()!=0){ //�亯article�̶�� 
			quserIdx=service.quserIdx(groupNum);
		}
		System.out.println("********quserIdx:"+quserIdx);
		*/

		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.list.QnA �Խ���");
		mav.addObject("list", list);
		mav.addObject("page", current_page);
		mav.addObject("total_page", total_page);
		mav.addObject("urlArticle", urlArticle);
		mav.addObject("dataCount", dataCount);
		mav.addObject("paging", myUtil.paging(current_page,total_page,urlList));
		return mav;
	}

	@RequestMapping(value = "/demander/{demander_seq}/qna/create", method = RequestMethod.GET)
	public ModelAndView deRevCreateForm(
			@PathVariable int demander_seq
			) throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.qna�Խ���");
		mav.addObject("mode", "created");
		mav.addObject("demander_seq", demander_seq);
		return mav;
	}

	@RequestMapping(value = "/demander/{demander_seq}/qna/create", method = RequestMethod.POST)
	public ModelAndView deRevCreateSubmit(HttpSession session, Qna dto,
			@PathVariable int demander_seq) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		dto.setQuserIdx(dto.getUserIdx());
		dto.setDemander_seq(demander_seq);
		
		service.insertQna(dto, "created");
		return new ModelAndView("redirect:/demander/{demander_seq}/qna/list");

	}

	@RequestMapping(value = "/demander/{demander_seq}/qna/article")
	public ModelAndView deQnaArticle(
			HttpSession session,
			@RequestParam(value = "num") int num,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "searchKey", defaultValue = "subject") String searchKey,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue,
			@PathVariable int demander_seq) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");
		// �˻��� decode
		searchValue = URLDecoder.decode(searchValue, "utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sqnaIdx", num);
		map.put("demander_seq", demander_seq);

		// ��ȸ������
		service.updateHitCount(map);
		
		// �ش��ƼŬ��������
		Qna dto = service.readQna(map);
		
		
		if (dto == null)
			return new ModelAndView("redirect:/demander/{demander_seq}/qna/list");

		
		String params = "page=" + page;
		if (searchValue.length() != 0) {
			params += "&searchKey=" + searchKey + "&searchValue=" + URLEncoder.encode(searchValue, "utf-8");
		}
		
		String amode="article";
		if(dto.getAnswer()!=0){ //�亯article�̶�� 
			amode="reply";
		}
		
		
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.article.QnA �Խ���");
		mav.addObject("dto", dto);
		mav.addObject("page", page);
		mav.addObject("amode",amode);
		mav.addObject("params", params);
		return mav;
	}

	@RequestMapping(value = "demander/{demander_seq}/qna/update", method = RequestMethod.GET)
	public ModelAndView deRevUpdateForm(HttpSession session, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page,
			@PathVariable int demander_seq) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sqnaIdx", num);
		map.put("demander_seq", demander_seq);
		Qna dto = service.readQna(map);
		

		if (info.getUserIdx() != dto.getUserIdx())
			return new ModelAndView("redirect:/demander/"+demander_seq+"/qna/list?page=" + page);

		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.QnA �Խ���");
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		mav.addObject("dto", dto);
		return mav;
	}

	@RequestMapping(value = "demander/{demander_seq}/qna/update", method = RequestMethod.POST)
	public String deRevUpdateSubmit(Qna dto, @RequestParam(value = "page") String page,
			@PathVariable int demander_seq) throws Exception {

		// ���� �ϱ�
		dto.setDemander_seq(demander_seq);
		service.updateQna(dto);

		return "redirect:/demander/"+demander_seq+"/qna/list?page=" + page;
	}

	@RequestMapping(value = "/demander/{demander_seq}/qna/delete")
	public ModelAndView delete(HttpSession session, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page,
			@PathVariable int demander_seq) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
	

		// �ش� ���ڵ� ���� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sqnaIdx", num);
		map.put("demander_seq", demander_seq);
		Qna dto = service.readQna(map);
		if (dto == null) {
			return new ModelAndView("redirect:/demander/"+demander_seq+"/qna/list?page=" + page);
		}

		if (!info.getUserId().equals(dto.getUserId()) && !info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/demander/"+demander_seq+"/qna/list?page=" + page);
		}

		service.deleteQna(map);

		return new ModelAndView("redirect:/demander/"+demander_seq+"/qna/list?page=" + page);
	}

	@RequestMapping(value = "/demander/{demander_seq}/qna/reply", method = RequestMethod.GET)
	public ModelAndView replyForm(HttpSession session, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page,
			@PathVariable int demander_seq) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sqnaIdx", num);
		map.put("demander_seq", demander_seq);
		
		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}

		Qna dto = service.readQna(map);
		if (dto == null) {
			return new ModelAndView("redirect:/board/list?page=" + page);
		}
	
		String str = "[" + dto.getSubject() + "] �� ���� �亯�Դϴ�.\n";
		dto.setContent(str);
		dto.setAnswer(dto.getSqnaIdx());
	
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.QnA �Խ���");
		mav.addObject("dto", dto);
		mav.addObject("page", page);
		mav.addObject("mode", "reply");
		return mav;
	}

	@RequestMapping(value = "/demander/{demander_seq}/qna/reply", method = RequestMethod.POST)
	public String replySubmit(HttpSession session, Qna dto, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page,
			@PathVariable int demander_seq) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		dto.setDemander_seq(demander_seq);
		dto.setUserIdx(info.getUserIdx());
		dto.setAnswer(num);
		int quserIdx=0;
	
		if(dto.getAnswer()!=0){ //�亯article�̶�� 
			quserIdx=service.quserIdx(dto);
		}
		dto.setQuserIdx(quserIdx);
		service.insertQnaReply(dto, num);

		return "redirect:/demander/"+demander_seq+"/qna/list?page=" + page;
	}
}
