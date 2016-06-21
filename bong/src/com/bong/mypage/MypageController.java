package com.bong.mypage;

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
import com.bong.member.Member;
import com.bong.member.MemberService;
import com.bong.member.SessionInfo;

@Controller("mypage.mypageController")
public class MypageController {
	@Autowired
	private MemberService service; //����, ����, Ż�� � ����� ����(�ɹ�)
	@Autowired
	private MyApplyService service2; // ��û ����Ʈ ��Ȳ�� ����� ����
	@Autowired
	private MyUtil myUtil;
	//������ ���� 
	@RequestMapping(value="/member/index/myPage", method=RequestMethod.GET)
	public ModelAndView myPage(
			 HttpSession session
			) throws Exception{
		ModelAndView mav= new ModelAndView(".layout.mypage.mypageMain.������ ����");
		return mav;
	}
	
	@RequestMapping(value="/member/index/myInfo")
	public ModelAndView myInfo(
			 HttpSession session
			) throws Exception{
		SessionInfo info = (SessionInfo)session.getAttribute("member"); //���ǿ� �ִ� ��� ���� ��������
        
		Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
		//userIdx �� ���ؼ� db�� �ִ� ������ ��������.
		
		ModelAndView mav= new ModelAndView("mypage/myInfo");
		mav.addObject("dto", dto);
		return mav;
	}

	
	//���� ����
@RequestMapping(value="/member/index/updateInfo", method=RequestMethod.GET)
	public ModelAndView updateInfo(
			HttpSession session
			
			) throws Exception {
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		//���ǿ� �ִ� ��� ���� ��������
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null) {
			return new ModelAndView("redirect:/member/login");
		}
		//db�� �ִ� ������ ��������(userIdx�� ���ؼ�) mapper�� �ִ� ���� ����
		Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
	/*	if(dto==null) {
			session.invalidate();
			return new ModelAndView("redirect:/");
		}*/
		//service.updateMember2(dto,pathname); //serviceImpl�Ҹ���	
		// ȸ������������
	    ModelAndView mav=new ModelAndView("/mypage/updateInfo");
	    mav.addObject("mode", "update");
	    mav.addObject("dto", dto);
	    return mav;
	}

	@RequestMapping(value="/member/index/updateInfo", method=RequestMethod.POST)
	public ModelAndView updateSubmit(
			HttpSession session
           ,Member dto){
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"memImg";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		dto.setUserIdx(info.getUserIdx());
		service.updateMember2(dto, pathname);
		
		ModelAndView mav=new ModelAndView(".layout.member.complete.ȸ����������");
		
		StringBuffer sb=new StringBuffer();
		sb.append(dto.getUserName()+ "���� ȸ�������� ���������� ����Ǿ����ϴ�.<br>");
		sb.append("����ȭ������ �̵� �Ͻñ� �ٶ��ϴ�.<br>");
		
		mav.addObject("message", sb.toString());
		
		return mav;
	}
	// ���Ǻ����û ��Ȳ ����Ʈ
	@RequestMapping(value="/member/index/myApply")
	public ModelAndView MyApply(
			HttpServletRequest req
		   ,@RequestParam(value="page",defaultValue="1") int current_page
		   ,@RequestParam(value="searchKey", defaultValue="subject") String searchKey
		   ,@RequestParam(value="searchValue", defaultValue="") String searchValue
		   ,@RequestParam(value="option", defaultValue="myApplyList") String optionC // ��ü����, ����, ���Ƹ� �� 
		   ,HttpSession session
			) throws Exception{
		String cp=req.getContextPath();
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		int numPerPage = 10; //���������� ������ �Խù��� ����
		int total_page;  //��ü ������
		int dataCount;  // �� ����
		
		if(req.getMethod().equalsIgnoreCase("GET")){ //get ����ΰ��
			searchValue = URLDecoder.decode(searchValue,"utf-8");
		}
		//��ü ��������
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		map.put("userIdx", Integer.toString(info.getUserIdx()));
		
		dataCount = service2.dataCount(map, optionC); //�� ����(MyApplyServiceImpl�� dataCount)
		total_page= myUtil.pageCount(numPerPage, dataCount);
		
		if(total_page < current_page)
			current_page = total_page;
		//����Ʈ�� ����� ������ ��������
		int start = (current_page -1) * numPerPage +1;
		int end = current_page * numPerPage;
		
		map.put("start", start);
		map.put("end", end);
		
		// �� ����Ʈ
		List<MyApply> list = service2.myApplyList(map, optionC);
		// ����Ʈ ��ȣ
		int listNum, n=0;
		Iterator<MyApply> it= list.iterator();
		
		while(it.hasNext()){
			MyApply data = it.next();
			listNum = dataCount - (start+n-1);
			data.setListNum(listNum);
			n++;
		}
		String params = "";
		String urlList = cp+"/member/index/myPage"+params;
		String urlArticle = cp +"/member/index/myPage?page"+current_page;
		
       //�˻�
		if(searchValue.length()!=0){
			params = "searchKey="+searchKey+
					"&searchValue="+URLEncoder.encode(searchValue,"utf-8");
		}
		if(params.length()!=0){
			urlList =cp+"/member/index/myPage"+params;
		    urlArticle += "&" + params;
		}
		ModelAndView mav= new ModelAndView("/mypage/myApply");
		mav.addObject("list", list);
		mav.addObject("urlArticle", urlArticle);
		mav.addObject("page", current_page);
		mav.addObject("dataCount", dataCount);
		mav.addObject("total_page",total_page);
		mav.addObject("paging", myUtil.paging(current_page, total_page, urlList));
		return mav;
	}
	//�̹��� ����
	@RequestMapping(value="/member/index/imageDelete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> imageDelete(
			HttpSession session,
			@RequestParam String filename
			) throws Exception{
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		boolean isLogin=true;
		boolean state=false;
		
		if(info==null){
			isLogin=false;
		} else {
			String root=session.getServletContext().getRealPath("/");
			String pathname=root+File.separator+"uploads"+
			File.separator+"memImg";
			service.deleteImage(info.getUserId(), pathname, filename);
			state=true;
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("isLogin", isLogin);
		model.put("state", state);
		
		return model;
		
	}
	//ȸ��Ż��
	@RequestMapping(value="/member/index/deleteMember", method=RequestMethod.GET)
	public ModelAndView deleteCheck(
			 HttpServletRequest req
			,HttpSession session
			) {
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		if(info==null){
			return new ModelAndView("redirect:/member/login");
		}

		ModelAndView mav=new ModelAndView("/mypage/deleteMember");
		//mav.addObject("userId", info.getUserId());
		return mav;
	}
	//ȸ��Ż����
    @RequestMapping(value="/mypage/deleteMember", method=RequestMethod.POST)
	public ModelAndView deleteMember (
			HttpSession session
		   ,@RequestParam(value="userPwd") String userPwd
			) throws Exception {
           SessionInfo info=(SessionInfo)session.getAttribute("member"); //���ǿ� �ִ� ���� ��������
           if(info==null){
        	   return new ModelAndView("redirect:/member/login");
           }
           //readMemberInfo�� �ִ� �����͵� dto�� ��������
           Member dto=service.readMemberInfo(Integer.toString(info.getUserIdx()));
           
           if(dto==null){
        	   session.invalidate();
        	   return new ModelAndView("redirect:/");
           }
           //Ż���ϱ����� ��й�ȣ Ȯ�� �ϰ� ���ֱ�
           if(! dto.getUserPwd().equals(userPwd)){
        	   ModelAndView mav=new ModelAndView(".layout.mypage.deleteMember.ȸ��Ż��");
        	   mav.addObject("message", "�н����尡 ��ġ�����ʽ��ϴ�");
        	   return mav;
           }
           //���Ƹ� ������ ���Ƹ� ����� Ż�� ���ϵ��� ����
          String check=service.managerCheck(info.getUserId());
           if(check!=null){
        	   return new ModelAndView("redirect:/");
           }
   
           Map<String, Object> map = new HashMap<String, Object>();
           map.put("userIdx", info.getUserIdx());
           service.deleteMember2(map);
           
           session.removeAttribute("member");
           session.invalidate();
           
          
           
           ModelAndView mav=new ModelAndView(".layout.member.complete.ȸ��Ż��");
			
			StringBuffer sb=new StringBuffer();
			sb.append(dto.getUserName()+ "���� ȸ�� Ż�� ó���� ���������� ó���Ǿ����ϴ�.<br>");
			sb.append("����ȭ������ �̵� �Ͻñ� �ٶ��ϴ�.<br>");
			
			
			mav.addObject("message", sb.toString());
           
           return mav;
	}
}	
