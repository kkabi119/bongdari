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

	@RequestMapping(value = "/demander/index/qna/list")
	public ModelAndView deQnaList(HttpServletRequest req,
			@RequestParam(value = "page", defaultValue = "1") int current_page,
			@RequestParam(value = "searchKey", defaultValue = "subject") String searchKey,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue) throws Exception {

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
		String urlList = cp + "/demander/index/qna/list";
		String urlArticle = cp + "/demander/index/qna/article?page="+current_page;
		if(searchValue.length() != 0) {
			params = "searchKey=" + searchKey + "&searchValue="+URLEncoder.encode(searchValue, "utf-8");
		}

		if(params.length() != 0) {
			urlList = cp + "/demander/index/qna/list?" + params;
			urlArticle = cp + "/demander/index/qna/article?page=" + current_page + "&" + params;
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

	@RequestMapping(value = "/demander/index/qna/create", method = RequestMethod.GET)
	public ModelAndView deRevCreateForm() throws Exception {
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.qna�Խ���");
		mav.addObject("mode", "created");
		return mav;
	}

	@RequestMapping(value = "/demander/index/qna/create", method = RequestMethod.POST)
	public ModelAndView deRevCreateSubmit(HttpSession session, Qna dto) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		dto.setUserId(info.getUserId());
		dto.setUserIdx(info.getUserIdx());
		dto.setQuserIdx(dto.getUserIdx());
		service.insertQna(dto, "created");
		return new ModelAndView("redirect:/demander/index/qna/list");

	}

	@RequestMapping(value = "/demander/index/qna/article")
	public ModelAndView deQnaArticle(
			HttpSession session, 
			@RequestParam(value = "num") int num,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "searchKey", defaultValue = "subject") String searchKey,
			@RequestParam(value = "searchValue", defaultValue = "") String searchValue) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");
		// �˻��� decode
		searchValue = URLDecoder.decode(searchValue, "utf-8");

		// ��ȸ������
		service.updateHitCount(num);
		
		// �ش��ƼŬ��������
		Qna dto = service.readQna(num);
		
		
		if (dto == null)
			return new ModelAndView("redirect:/demander/index/qna/list");

		
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

	@RequestMapping(value = "demander/index/qna/update", method = RequestMethod.GET)
	public ModelAndView deRevUpdateForm(HttpSession session, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page) throws Exception {

		SessionInfo info = (SessionInfo) session.getAttribute("member");

		Qna dto = service.readQna(num);

		if (info.getUserIdx() != dto.getUserIdx())
			return new ModelAndView("redirect:/demander/index/qna/list?page=" + page);

		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.�ı�Խ���");
		mav.addObject("mode", "update");
		mav.addObject("page", page);
		mav.addObject("dto", dto);
		return mav;
	}

	@RequestMapping(value = "demander/index/qna/update", method = RequestMethod.POST)
	public String deRevUpdateSubmit(Qna dto, @RequestParam(value = "page") String page) throws Exception {

		// ���� �ϱ�
		service.updateQna(dto);

		return "redirect:/demander/index/qna/list?page=" + page;
	}

	@RequestMapping(value = "/demander/index/qna/delete")
	public ModelAndView delete(HttpSession session, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");
	

		// �ش� ���ڵ� ���� ����
		Qna dto = service.readQna(num);
		if (dto == null) {
			return new ModelAndView("redirect:/demander/index/qna/list?page=" + page);
		}

		if (!info.getUserId().equals(dto.getUserId()) && !info.getUserId().equals("admin")) {
			return new ModelAndView("redirect:/demander/index/qna/list?page=" + page);
		}

		service.deleteQna(num);

		return new ModelAndView("redirect:/demander/index/qna/list?page=" + page);
	}

	@RequestMapping(value = "/demander/index/qna/reply", method = RequestMethod.GET)
	public ModelAndView replyForm(HttpSession session, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		if (info == null) {
			return new ModelAndView("redirect:/member/login");
		}

		Qna dto = service.readQna(num);
		if (dto == null) {
			return new ModelAndView("redirect:/board/list?page=" + page);
		}
	
		String str = "[" + dto.getSubject() + "] �� ���� �亯�Դϴ�.\n";
		dto.setContent(str);
		dto.setAnswer(dto.getSqnaIdx());
	
		ModelAndView mav = new ModelAndView(".four.demander.dari.qna.create.�ı�Խ���");
		mav.addObject("dto", dto);
		mav.addObject("page", page);
		mav.addObject("mode", "reply");
		return mav;
	}

	@RequestMapping(value = "/demander/index/qna/reply", method = RequestMethod.POST)
	public String replySubmit(HttpSession session, Qna dto, @RequestParam(value = "num") int num,
			@RequestParam(value = "page") String page) throws Exception {
		SessionInfo info = (SessionInfo) session.getAttribute("member");

		
		dto.setUserIdx(info.getUserIdx());
		dto.setAnswer(num);
		int quserIdx=0;
	
		if(dto.getAnswer()!=0){ //�亯article�̶�� 
			quserIdx=service.quserIdx(num);
		}
		dto.setQuserIdx(quserIdx);
		service.insertQnaReply(dto, num);

		return "redirect:/demander/index/qna/list?page=" + page;
	}
}
