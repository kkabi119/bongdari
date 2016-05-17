<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
%>

<script type="text/javascript">
	 $(function() {
	 $("input[name=startDay]").datepicker();
	 $("input[name=endDay]").datepicker();
	 });
</script>
<div class="container-fluid">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-md-3 control-label">제목</label>
			<div class="col-md-9" id="schTitle">
				<input class="form-control" name="title" type="text"
					placeholder="제목">
			</div>
		</div>


		<div class="form-group">
			<label class="col-md-3 control-label">봉사일정</label>
			<div class="col-md-9" id="schEndDay">
				<div class="row">
					<div class="col-md-5" style="padding-right: 0px;">
						<input class="form-control" name="startDay" type="text"
							readonly="readonly" style="background: #fff;" placeholder="시작날짜">
					</div>
					<div class="col-md-1 control-label">~</div>
					<div class="col-md-5" style="padding-left: 0px;">
						<input class="form-control" name="endDay" type="text"
							readonly="readonly" style="background: #fff;" placeholder="종료날짜">
					</div>

				</div>
			</div>

		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">봉사시간</label>
			<div class="col-md-9" id="schStartDay">
				<div class="row">

					<div class="col-md-5" style="padding-right: 0px;">
						<input class="form-control" id="startTime" name="startTime"
							type="text" placeholder="시작시간">
					</div>
					<div class="col-md-1 control-label">~</div>
					<div class="col-md-5" style="padding-left: 0px;">
						<input class="form-control" id="endTime" name="endTime"
							type="text" placeholder="종료시간">
					</div>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">봉사요일</label>
			<div class="col-md-7 control-label" id="schAllDay">
				월&nbsp;&nbsp;<input type="checkbox" name="monday" value="true">
				화&nbsp;&nbsp;<input type="checkbox" name="tuesday" value="true">
				수&nbsp;&nbsp;<input type="checkbox" name="wednesday" value="true">
				목&nbsp;&nbsp;<input type="checkbox" name="thursday" value="true">
				금&nbsp;&nbsp;<input type="checkbox" name="friday" value="true">
				토&nbsp;&nbsp;<input type="checkbox" name="saturday" value="true">
				일&nbsp;&nbsp;<input type="checkbox" name="sunday" value="true">
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">모집인원 /일</label>
			<div class="col-md-4" id="maxMember">
				<input class="form-control" name="maxMember" type="text"
					placeholder="인원 수">
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">봉사분야</label>
			<div class="col-md-4" id="schClassify">
				<select class="form-control">
					<option>문화체육</option>
					<option>컬쳐엑서사이즈</option>
				</select>
			</div>
			<div class="col-md-1 control-label">></div>
			<div class="col-md-4" id="schClassify">
				<select class="form-control">
					<option>행사보조</option>
					<option>퍼포먼스어시스턴트</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">봉사자 유형</label>
			<div class="col-md-4">
				<select class="form-control">
					<option>성인</option>
					<option>청소년</option>
				</select>
			</div>
			<div class="col-md-1 control-label">></div>
			<div class="col-md-4">
				<select class="form-control">
					<option>남자</option>
					<option>여자</option>
					<option>무관</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">작성자/수요처이름</label>
			<div class="col-md-8" id="schUserName">
				<p class="form-control-static">희망복지관</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">수요처 장소</label>
			<div class="col-md-8" id="schUserName">
				<p class="form-control-static">서울특별시 노원구 삼양동 종합복지센터 장암역 1번출구</p>
			</div>
		</div>


		<div class="form-group" id="schContent" style="min-height: 75px;">
			<label class="col-md-2 control-label">내용</label>
			<div class="col-md-10">
				<textarea name="content" class="form-control" rows="3"></textarea>
			</div>
		</div>
	</form>

	<div style="text-align: right;" id="schFooter">
		<button type="button" class="btn btn-primary" id="btnModalOk"
			onclick="insertOk();">
			확인 <span class="glyphicon glyphicon-ok"></span>
		</button>
		<button type="button" class="btn btn-default" data-dismiss="modal"
			style="margin-left: 0px;">닫기</button>
	</div>
</div>