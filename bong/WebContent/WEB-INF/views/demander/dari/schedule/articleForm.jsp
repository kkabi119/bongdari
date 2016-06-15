<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<script>



</script>

<div class="container-fluid">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-md-2 control-label">제목</label>
			<div class="col-md-7 control-label" id="schTitle">
				${dto.subject}
			</div>
		</div>

		<div>
		<div class="form-group">
			<label class="col-md-2 control-label">봉사일정</label>
			<div class="col-md-4 control-label" id="schEndDay" style="text-align:center;">
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">${dto.startDay}</div>
					<div class="col-md-2" style="text-align:center; width:1px;">~</div>
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">${dto.endDay}</div>
			</div>

			<label class="col-md-2 control-label">봉사시간</label>
			<div class="col-md-4 control-label" id="schStartDay" style="text-align:center;">
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">${dto.startTime}</div>
					<div class="col-md-2" style="text-align:center; width:1px;">~</div>
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">${dto.endTime}</div>
			</div>
		</div>
		</div>
		
		<div>
		<div class="form-group">
			<label class="col-md-2 control-label">봉사요일</label>
			<div class="col-md-4 control-label" style="text-align:center;">
				${dto.volunDays}
			</div>
			<label class="col-md-2 control-label">모집인원 /일</label>
			<div class="col-md-4 control-label" id="maxMember" style="text-align:center;">
				5명 / 일
			</div>
		</div>
		</div>
		
		<div>
		<div class="form-group">
			<label class="col-md-2 control-label">신청인원</label>
			<div class="col-md-4 control-label" id="maxMember" style="text-align:center;">
				3봉 15명
			</div>
			
			<label class="col-md-2 control-label">봉사분야</label>
			<div class="col-md-4">
			<div class="col-md-5 control-label" id="schClassify">
				${dto.lSubject}
			</div>
			<div class="col-md-2 control-label">></div>
			<div class="col-md-5 control-label" id="schClassify">
				${dto.sSubject}
			</div>
			</div>
		</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-2 control-label">봉사자 유형</label>
			<div class="col-md-4" style="text-align:center;">
				<div class="col-md-12 control-label" style="text-align:center;">${dto.volunteer_type}</div>
			</div>

			<label class="col-md-2 control-label">수요처이름</label>
			<div class="col-md-4" id="schUserName" style="text-align:center;">
				<p class="form-control-static">${dto.serviceName}</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">수요처 장소</label>
			<div class="col-md-7" id="schUserName">
				<p class="form-control-static">${dto.place}</p>
			</div>
		</div>


		<div class="form-group" id="schContent" style="min-height: 75px;">
			<label class="col-md-2 control-label">내용</label>
			<div class="col-md-10">
				<p class="control-lebel">
					${dto.content}
				</p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-2 control-label">첨부파일</label>
			<div class="col-md-10 control-label" style="text-align:center;">파일 내용</div>
		</div>
	</form>

	<div style="text-align: right;" id="schFooter">
		<button type="button" class="btn btn-primary" id="btnModalOk"
			onclick="take(${dto.volunIdx});">
			내 동아리로 가져오기 <span class="glyphicon glyphicon-ok"></span>
		</button>
		<button type="button" class="btn btn-primary" id="btnModalOk"
			onclick="#">
			마감하기 <span class="glyphicon glyphicon-ok"></span>
		</button>
		<button type="button" class="btn btn-default" data-dismiss="modal"
			style="margin-left: 0px;">닫기</button>
	</div>
</div>