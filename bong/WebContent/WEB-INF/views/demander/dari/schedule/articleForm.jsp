<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="container-fluid">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-md-2 control-label">제목</label>
			<div class="col-md-7 control-label" id="schTitle">
				노원 동네주민 축제의 안전요원을 모집합니다
			</div>
		</div>

		<div>
		<div class="form-group">
			<label class="col-md-2 control-label">봉사일정</label>
			<div class="col-md-4 control-label" id="schEndDay" style="text-align:center;">
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">2016-03-22</div>
					<div class="col-md-2" style="text-align:center; width:1px;">~</div>
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">2016-03-25</div>
			</div>

			<label class="col-md-2 control-label">봉사시간</label>
			<div class="col-md-4 control-label" id="schStartDay" style="text-align:center;">
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">9:00</div>
					<div class="col-md-2" style="text-align:center; width:1px;">~</div>
					<div class="col-md-5" style="text-align:center; padding-left:0px; padding-right:0px;">17:00</div>
			</div>
		</div>
		</div>
		
		<div>
		<div class="form-group">
			<label class="col-md-2 control-label">봉사요일</label>
			<div class="col-md-4 control-label" style="text-align:center;">
				금,토,일
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
				문화체육
			</div>
			<div class="col-md-2 control-label">></div>
			<div class="col-md-5 control-label" id="schClassify">
				행사보조
			</div>
			</div>
		</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-2 control-label">봉사자 유형</label>
			<div class="col-md-4" style="text-align:center;">
				<div class="col-md-5 control-label" style="text-align:center;">성인</div>
				<div class="col-md-2 control-label" style="text-align:center;">-</div>
				<div class="col-md-5 control-label" style="text-align:center;">남자</div>
			</div>

			<label class="col-md-2 control-label">수요처이름</label>
			<div class="col-md-4" id="schUserName" style="text-align:center;">
				<p class="form-control-static">희망복지관</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">수요처 장소</label>
			<div class="col-md-7" id="schUserName">
				<p class="form-control-static">서울특별시 노원구 삼양동 종합복지센터 장암역 1번출구</p>
			</div>
		</div>


		<div class="form-group" id="schContent" style="min-height: 75px;">
			<label class="col-md-2 control-label">내용</label>
			<div class="col-md-10">
				<p class="control-lebel">
					삼양동종합복지센터에서는 따뜻한 봄을 맞이하여 '봄맞이 어르신 봄나들이' 프로그램을 진행 할 예정입니다. 
					인솔을 함께할 자원봉사자를 모집합니다. 
					어르신을 공경하고, 서비스 마인드를 가지신 분들의 신청을 기다립니다^^ 
					
					일시: 2016년 5월 12일 목요일 08:30 ~ 16:30 (8시간) 
					장소: 롯데마트 삼양점 앞 집결 / 일산 아쿠아플라넷, 고양 꽃박람회장 이동 / 센터 도착 후 해산. 
					모집인원: 2명 
					신청자격: 남녀무관 20세 이상, 사회복지학과 재학중인 학생 우대 
					내용: 어르신 봄나들이 인솔 및 진행 보조, 중식 제공 
					담당자: 어르신복지팀 하나래 팀장 
					문의: 02) 945-1305~6 
					
					많은 지원 부탁드립니다^^ 
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
			onclick="insertOk();">
			확인 <span class="glyphicon glyphicon-ok"></span>
		</button>
		<button type="button" class="btn btn-default" data-dismiss="modal"
			style="margin-left: 0px;">닫기</button>
	</div>
</div>