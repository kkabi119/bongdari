<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="col-md-12">
				<span class="glyphicon glyphicon-menu-right"></span> <span
					style="font-weight: bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">동아리
					정보</span>
			</div>

			<div class="col-md-12" style="float: clear;"></div>

					<img src="<%=cp%>/uploads/memImg/${dto.memImg}"
						style="width: 350px; height: 150px;">
					<div class="col-md-12">
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
							동아리명
						</div>
						<div class="col-md-8" style="text-align: left;">${dto.clubName}</div>
	
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;동아리장
						</div>
						<div class="col-md-8" style="text-align: left;">${dto.userId}</div>
	
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;활동 분야
						</div>
						<div class="col-md-8" style="text-align: left;">노인 > 공경</div>
	
						<div class="col-md-4"
							style="color: black;; border-top: none; border-top: none; text-align: left;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;개설 날짜
						</div>
						<div class="col-md-8" style="text-align: left;">${dto.clubBirth}</div>
	
						<div class="col-md-4"
							style="color: black;; border-top: none; border-top: none; text-align: left;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이메일
						</div>
						<div class="col-md-8" style="text-align: left;">
							susanghanja@naver.com</div>
						<div class="col-md-4"
							style="color: black;; border-top: none; text-align: left;">
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;주소
						</div>
						<div class="col-md-8" style="text-align: left;">${dto.clubAddr}</div>
					</div>
			<div class="col-md-12" style="float: clear; padding-top: 10px;"></div>
			<div class="col-md-4" align="center">
				<button type="button" class="btn btn-info"
					onclick="javascript:location.href='<%=cp%>/club/${club_seq}/notice/created';">
					<span class="fa fa">완료하기</span>
				</button>
			</div>