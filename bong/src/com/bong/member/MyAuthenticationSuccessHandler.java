package com.bong.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.bong.club.ClubInfo;
import com.bong.club.ClubService;
import com.bong.demanderjoin.Demanderjoin;
import com.bong.demanderjoin.DemanderjoinService;

// 로그인 성공후 세션 및 쿠키등의 처리를 할 수 있다.
// 로그인 전 정보를 Cache : 로그인 되지 않은 상태에서 로그인 상태에서만 사용할 수 있는 페이지로 이동할 경우 로그인 페이지로 이동하고 로그인 후 로그인 전 페이지로 이동
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private DemanderjoinService demanderjoinService;

	@Autowired
	private ClubService clubService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse resp, Authentication authentication)
			throws ServletException, IOException {
		HttpSession session=req.getSession();
		
		// System.out.println(authentication.getName()); // 로그인 아이디
		
		
		
	    
	    
		Member member=memberService.readMemberLogin(authentication.getName());
		
		SessionInfo info=new SessionInfo();
		
		info.setIsService(member.getIsService());
		
		
		
		if(member.getIsService()==0){  
			//일반회원일때

			// 로그인 날짜 변경
			memberService.updateLastLogin(authentication.getName());
			info.setUserIdx(member.getUserIdx());
			info.setUserId(member.getUserId());
			info.setUserName(member.getUserName());			
		} else {
			//수요처 회원일경우

			// 로그인 날짜 변경
			demanderjoinService.updateLastLogin(authentication.getName());
			Demanderjoin demanderjoin=demanderjoinService.readDemanderjoinLogin(Integer.toString(member.getUserIdx()));	
			info.setUserIdx(demanderjoin.getUserIdx());
			info.setUserId(demanderjoin.getUserId());
			info.setUserName(demanderjoin.getServiceName());
			info.setDemander_seq(demanderjoin.getServiceIdx());
		}

		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId",member.getUserId());
				
		int clubIdx=clubService.ReadClubInfoSession(map);
		info.setClubIdx(clubIdx);
		
		map.put("clubSeq", clubIdx);
		ClubInfo clubinfo=clubService.readClubInfoSmall(map);
		if(clubinfo!=null)
		info.setClubname(clubinfo.getClubname());
		
		
		List<ClubInfo> list=clubService.readJoinedClub(info.getUserIdx());
		session.setAttribute("clubList", list);
		session.setAttribute("member", info);	

		super.onAuthenticationSuccess(req, resp, authentication);
	}

}
