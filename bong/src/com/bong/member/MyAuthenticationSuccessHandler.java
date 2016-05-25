package com.bong.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.bong.club.ClubService;

// �α��� ������ ���� �� ��Ű���� ó���� �� �� �ִ�.
// �α��� �� ������ Cache : �α��� ���� ���� ���¿��� �α��� ���¿����� ����� �� �ִ� �������� �̵��� ��� �α��� �������� �̵��ϰ� �α��� �� �α��� �� �������� �̵�
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private MemberService memberService;

	@Autowired
	private ClubService clubService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse resp, Authentication authentication)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		
		// System.out.println(authentication.getName()); // �α��� ���̵�
		
		// �α��� ��¥ ����
		memberService.updateLastLogin(authentication.getName());
				
		Member member=memberService.readMemberLogin(authentication.getName());
		SessionInfo info=new SessionInfo();
		info.setUserIdx(member.getUserIdx());
		info.setUserId(member.getUserId());
		info.setUserName(member.getUserName());
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId",info.getUserId());
				
		int clubIdx=clubService.ReadClubInfoSession(map);
		info.setClubIdx(clubIdx);

		session.setAttribute("member", info);	

		super.onAuthenticationSuccess(req, resp, authentication);
	}

}
