<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<div class="container-fluid">
	<form class="form-horizontal">
		<div class="col-md-12">
				<div class="col-md-5" style="text-align: left;">
					<img src="<%=cp%>/uploads/memImg/${dto.memImg}"
						style="width: 150px; height: 150px;">
				</div>
				<div class="col-md-7" style=" padding-bottom : 10px;">

					<div class="col-md-6"
						style="color: black; border-top: none; text-align: left; padding-bottom : 10px;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
						아이디
					</div>
					<div class="col-md-6" style="text-align: left; padding-bottom : 10px;">${dto.userId}</div>

					<div class="col-md-6"
						style="color: black; border-top: none; text-align: left; padding-bottom : 10px;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이름
					</div>
					<div class="col-md-6" style="text-align: left; padding-bottom : 10px;">${dto.userName}</div>

					<div class="col-md-6"
						style="color: black; border-top: none; text-align: left; padding-bottom : 10px;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;생일
					</div>
					<div class="col-md-6" style="text-align: left; padding-bottom : 10px;">${dto.userBirth}</div>

					
				</div>
			</div>


			
			<div class="col-md-3"
				style="color: black; border-top: none; text-align: left; padding-bottom : 10px;">
				<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;주소
			</div>
			<div class="col-md-8" style="text-align: left; padding-bottom : 10px;">${dto.userAddr}</div>
			
			<div class="col-md-3"
				style="color: black; border-top: none; text-align: left; padding-bottom : 10px;">
				<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;가입날짜
			</div>
			<div class="col-md-8" style="text-align: left; padding-bottom : 10px;">${dto.created_date}</div>
			

			<div class="col-md-12" style="float: clear; padding-top: 10px;"></div>
			
	</form>

	<div style="text-align: right;" id="schFooter">
		<button type="button" class="btn btn-primary" id="btnModalOk"
			onclick="approvalOk(${dto.userIdx});">
			활동 중단시키기 <span class="glyphicon glyphicon-ok"></span>
		</button>
		<button type="button" class="btn btn-default" data-dismiss="modal"
			style="margin-left: 0px;">닫기</button>
	</div>
</div>