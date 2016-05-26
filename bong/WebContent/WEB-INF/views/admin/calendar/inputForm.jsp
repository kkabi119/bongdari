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
	 
	 $(function(){
		 $("#eachDay").hide();
		 $("#eachDayBtn").click(function(){
				url="<%=cp%>/cal/eachDay";
				$.post(url, {}, function(data){ 	
					$("#eachDay").html(data);
				});	
				if($("#eachDay").is(':visible')) {
					$("#eachDay").hide();
					$("#listClosed").val("1");
				} else {
					$("#eachDay").show();
					$("#listClosed").val("0");
				}
		 });
	 });
		 
	 
</script>

<script type="text/javascript" src="<%=cp%>/res/se/js/HuskyEZCreator.js" charset="utf-8"></script>

<div class="container-fluid">
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-md-2 control-label">제목</label>
			<div class="col-md-9" id="schTitle">
				<input class="form-control" name="title" type="text"
					placeholder="제목">
			</div>
		</div>


		<div class="form-group">
			<label class="col-md-2 control-label">봉사일정</label>
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
			<label class="col-md-2 control-label">봉사시간</label>
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
			<label class="col-md-2 control-label">봉사요일</label>
			<div class="col-md-7 control-label" style="text-align:left;" id="schAllDay">
				월&nbsp;<input type="checkbox" name="monday" value="true">&nbsp;&nbsp;
				화&nbsp;<input type="checkbox" name="tuesday" value="true">&nbsp;&nbsp;
				수&nbsp;<input type="checkbox" name="wednesday" value="true">&nbsp;&nbsp;
				목&nbsp;<input type="checkbox" name="thursday" value="true">&nbsp;&nbsp;
				금&nbsp;<input type="checkbox" name="friday" value="true">&nbsp;&nbsp;
				토&nbsp;<input type="checkbox" name="saturday" value="true">&nbsp;&nbsp;
				일&nbsp;<input type="checkbox" name="sunday" value="true">
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">모집인원 /일</label>
			<div class="col-md-4" id="maxMember">
				<input class="form-control" name="maxMember" type="text"
					placeholder="인원 수">
			</div>
			<div class="col-md-2"><button id="eachDayBtn" type="button" class="btn btn-primary">날짜 별 선택</button></div>
			<div class="col-md-2 control-label">10명/총</div>
		</div>
		
		<div id=eachDay class="col-md-12" style="margin-bottom:10px;"></div>

		<div class="form-group">
			<div>
			<label class="col-md-2 control-label">봉사분야</label>
			<div class="col-md-4" id="schClassify">
				<select class="form-control">
					<option>문화체육</option>
					<option>컬쳐엑서사이즈</option>
				</select>
			</div>
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
			<label class="col-md-2 control-label">봉사자 유형</label>
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
		
		<div class="form-group" id="schContent" style="min-height: 75px;">
			<label class="col-md-2 control-label">내용</label>
			<div class="col-md-10"><textarea id="content" name="content" class="form-control" style="max-width: 99%;"></textarea></div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">수요처이름</label>
			<div class="col-md-8" id="schUserName">
				<p class="form-control-static">희망복지관</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">수요처 장소</label>
			<div class="col-md-8" id="schUserName">
				<p class="form-control-static">서울특별시 노원구 삼양동 종합복지센터 장암역 1번출구</p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-2 control-label">파일 업로드</label>
			<div class="col-md-8" id="schUserName">
				<p class="form-control-static">파일 업로드 하는곳</p>
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

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "<%=cp%>/res/se/SmartEditor2Skin.html",	
	htParams : {bUseToolbar : true,
		fOnBeforeUnload : function(){
			//alert("아싸!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		//$("#smart_editor2").css('width: 100%;');
	},
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["content"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
	
	try {
		// elClickedObj.form.submit();
		return check();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '돋움';
	var nFontSize = 24;
	oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
}



</script>

 