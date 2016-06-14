<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="col-md-12">
				<span class="glyphicon glyphicon-menu-right"></span> <span
					style="font-weight: bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">멤버 정보</span>
			</div>

			<div class="col-md-12" style="float: clear;"></div>
						<div class="col-md-4">
						<img src="<%=cp%>/uploads/memImg/${dto.memImg}"
						style="width: 150px; height: 150px;">
						</div>
						<div class="col-md-8">
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
							이름
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">${dto.userName}</div>
	
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;성별
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">${dto.userGender}</div>
	
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;직업
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">${dto.userJob}</div>
						
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;가입일
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">${dto.created_date}</div>
	
						</div>
					<div class="col-md-12">
	
						<div class="col-md-4"
							style="color: black;; border-top: none; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;생년월일
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">${dto.userBirth}</div>
						<div class="col-md-4"
							style="color: black;; border-top: none; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이메일
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">
							${dto.userEmail}</div>
							<div class="col-md-4"
							style="color: black;; border-top: none; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;전화번호
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">
							${dto.userTel}</div>
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left; margin-top:10px;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;주소
						</div>
						<div class="col-md-8" style="text-align: left; margin-top:10px;">${dto.userAddr}</div>
					</div>
			<div class="col-md-12" style="float: clear; padding-top: 10px;"></div>
			<div class="col-md-4" align="center">
				<button type="button" class="btn btn-info"
					onclick="javascript:location.href='<%=cp%>/club/${club_seq}/notice/created';">
					<span class="fa fa">완료하기</span>
				</button>
			</div>