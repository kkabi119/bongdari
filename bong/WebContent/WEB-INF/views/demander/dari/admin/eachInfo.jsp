<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<script type="text/javascript">
	function selectedClub(clubApplyIdx, volunIdx, clubIdx){
		var url = "<%=cp%>/demander/admin/eachClubMember";
		$.post(url, {clubApplyIdx:clubApplyIdx, volunIdx:volunIdx, clubIdx:clubIdx}, function(data){
			$("#eachClubMember").html(data);
		});
		
		url = "<%=cp%>/demander/admin/clubInfoView";
		$.post(url, {clubIdx:clubIdx}, function(data){
			$("#infoView").html(data);
		});
	}
	
	function selectedMember(userIdx){
		var url = "<%=cp%>/demander/admin/memberInfoView";
		$.post(url, {userIdx:userIdx}, function(data){
			$("#infoView").html(data);
		});
	}
</script>

		<div class="col-md-2">
			<div style="clear: both; padding-bottom: 5px;">
				<span class="glyphicon glyphicon-menu-right"></span> <span
					style="font-weight: bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">동아리
					리스트</span>
					<div id="chatConnectList">
						<c:forEach var="dto" items="${volunList}">
							<input type='checkbox' value='친구아이디'><a onclick="selectedClub(${dto.clubApplyIdx},${dto.volunIdx}, ${dto.clubIdx});" style="cursor:pointer;">${dto.clubName}</a><br>
						</c:forEach>
					</div>
			</div>
			
		</div>
		<div class="col-md-2">
			<div style="clear: both; padding-bottom: 5px;">
				<span class="glyphicon glyphicon-menu-right"></span> <span
					style="font-weight: bold; font-family: 나눔고딕, 맑은 고딕, 돋움; color: #424951;">멤버
					리스트</span>
					<div id="chatConnectList">
						<div id="eachClubMember"></div>
					</div>
			</div>
		</div>
		<div class="col-md-8" id="infoView">
			<h2>동아리 또는 멤버의 정보가 표시됩니다.</h2>
		</div>
