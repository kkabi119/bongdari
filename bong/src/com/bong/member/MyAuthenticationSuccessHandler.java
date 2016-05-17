package com.bong.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

// �α��� ������ ���� �� ��Ű���� ó���� �� �� �ִ�.
// �α��� �� ������ Cache : �α��� ���� ���� ���¿��� �α��� ���¿����� ����� �� �ִ� �������� �̵��� ��� �α��� �������� �̵��ϰ� �α��� �� �α��� �� �������� �̵�
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private MemberService memberService;

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
		
		session.setAttribute("member", info);	

		super.onAuthenticationSuccess(req, resp, authentication);
	}

}
