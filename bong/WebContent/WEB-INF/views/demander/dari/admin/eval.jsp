<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>
<style type="text/css">
#chatMsgContainer {
	clear: both;
	border: 1px solid #ccc;
	height: 277px;
	overflow-y: scroll;
	padding: 3px;
	width: 100%;
}

#chatMsgContainer p {
	padding-bottom: 0px;
	margin-bottom: 0px;
}

#chatConnectList {
	clear: both;
	width: 100%;
	height: 315px;
	text-align: left;
	padding: 5px 5px 5px 5px;
	overflow-y: scroll;
	border: 1px solid #ccc;
}
</style>
<script type="text/javascript">
	$(function() {
		var listClosed = "${listClosed}";
		/* if(listClosed=="1") {
			$("#bodyFrame2").hide("slow", function() {
			    alert( "Animation complete." );
			  });
		} */
	});
</script>
<div class="bodyFrame2">
	<div class="body-title">
		<h3>
			<span class="glyphicon glyphicon-send"></span> 평가하기
		</h3>
	</div>

	<div style="clear: both;">
		<div class="col-md-2">
			<div style="clear: both; padding-bottom: 5px;">
				<span class="glyphicon glyphicon-menu-right"></span> <span
					style="font-weight: bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">봉사자
					리스트</span>
			</div>
			<div id="chatConnectList">
				<input type='checkbox' value='친구아이디'> 아이디
			</div>
		</div>
		<div class="col-md-8">
			<div class="col-md-12">
				<span class="glyphicon glyphicon-menu-right"></span> <span
					style="font-weight: bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">봉사자
					정보</span>
			</div>

			<div class="col-md-12" style="float: clear; padding-top: 25px;"></div>

			<div class="col-md-12">
				<div class="col-md-5" style="text-align: left;">
					<img src="<%=cp%>/uploads/memImg/${dto.memImg}"
						style="width: 150px; height: 150px;">
				</div>
				<div class="col-md-7">

					<div class="col-md-6"
						style="color: black;; border-top: none; text-align: left;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
						아이디
					</div>
					<div class="col-md-6" style="text-align: left;">kimchul</div>

					<div class="col-md-6"
						style="color: black;; border-top: none; text-align: left;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이름
					</div>
					<div class="col-md-6" style="text-align: left;">김철수</div>

					<div class="col-md-6"
						style="color: black;; border-top: none; text-align: left;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;성별
					</div>
					<div class="col-md-6" style="text-align: left;">남자</div>

					<div class="col-md-6"
						style="color: black;; border-top: none; border-top: none; text-align: left;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;직업
					</div>
					<div class="col-md-6" style="text-align: left;">학생</div>
					<div class="col-md-6"
						style="color: black;; border-top: none; text-align: left;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;생년월일
					</div>
					<div class="col-md-6" style="text-align: left;">1989-01-20</div>
					<div class="col-md-6"
						style="color: black;; border-top: none; text-align: left;">
						<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;전화번호
					</div>
					<div class="col-md-6" style="text-align: left;">010-0000-0000</div>
				</div>
			</div>
			<div class="col-md-12" style="float: clear; padding-top: 10px;"></div>

			<div class="col-md-3"
				style="color: black;; border-top: none; border-top: none; text-align: left;">
				<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;이메일
			</div>
			<div class="col-md-8" style="text-align: left;">
				susanghanja@naver.com</div>

			<div class="col-md-12" style="float: clear; padding-top: 10px;"></div>

			<div class="col-md-3"
				style="color: black;; border-top: none; text-align: left;">
				<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;주소
			</div>
			<div class="col-md-8" style="text-align: left;">인천광역시 경서동</div>

			<div class="col-md-12" style="float: clear; padding-top: 10px;"></div>

			<div class="col-md-4">
				<button type="button" class="btn btn-info"
					onclick="javascript:location.href='<%=cp%>/club/${club_seq}/notice/created';">
					<span class="fa fa-arrow-right"></span>
				</button>
			</div>
			
			<div class="col-md-4" align="center">
				<button type="button" class="btn btn-info"
					onclick="javascript:location.href='<%=cp%>/club/${club_seq}/notice/created';">
					<span class="fa fa">완료하기</span>
				</button>
			</div>

			<div class="col-md-4" align="right">
				<button type="button" class="btn btn-info"
					onclick="javascript:location.href='<%=cp%>/club/${club_seq}/notice/created';">
					<span class="fa fa-arrow-left"></span>
				</button>
			</div>
		</div>

		<div class="col-md-2" style="margin-bottom: 10px;">
			<div style="clear: both; padding-bottom: 5px;">
				<span class="glyphicon glyphicon-menu-right"></span> <span
					style="font-weight: bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">확정자
					리스트</span>
			</div>
			<div id="chatConnectList"></div>
		</div>

	</div>
	<input type="hidden" name="listClosed" id="listClosed"
		value="${listClosed}">
</div>