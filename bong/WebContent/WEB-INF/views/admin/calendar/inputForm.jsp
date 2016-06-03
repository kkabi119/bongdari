<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp = request.getContextPath();
	
%>



<script type="text/javascript">
	 var f = document.myForm;
	 var rowid = '';             //체크된 체크박스의 모든 value 값을 담는다
	 $(function() {
	 $("input[name=startDay]").datepicker({
			onSelect : function () {eachDaySelector();}
	 		});
	 $("input[name=endDay]").datepicker({
			onSelect : function () {eachDaySelector();}
			});
	 themeList();
	 
	 });
	 
	 // 날짜별 선택에 데이터가 들어가도록 하는 함수
	 function eachDaySelector(){
		checkDay();
		var startDay = $("#startDay").val();
		var endDay = $("#endDay").val();
		$.post("<%=cp%>/cal/eachDay", {startDay:startDay, endDay:endDay, checkDay:rowid}, function(data){ 	
			$("#eachDay").html(data);
		});
		rowid="";
	 }
	 
	 // 요일 체크박스 값 가져오기
	 function checkDay() {
		 var chk = document.getElementsByName("checkDay"); // 체크박스객체를 담는다
		 var len = chk.length;    //체크박스의 전체 개수
		 var checkRow = '';      //체크된 체크박스의 value를 담기위한 변수
		 var checkCnt = 0;        //체크된 체크박스의 개수
		 var checkLast = '';      //체크된 체크박스 중 마지막 체크박스의 인덱스를 담기위한 변수
		 var cnt = 0;                 

		 for(var i=0; i<len; i++){
		 if(chk[i].checked == true){
		 checkCnt++;        //체크된 체크박스의 개수
		 checkLast = i;     //체크된 체크박스의 인덱스
		 }
		 } 

		 for(var i=0; i<len; i++){
		 if(chk[i].checked == true){  //체크가 되어있는 값 구분
		 checkRow = chk[i].value;
		             	
		 if(checkCnt == 1){                            //체크된 체크박스의 개수가 한 개 일때,
		 rowid += checkRow;        //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
		 }else{                                            //체크된 체크박스의 개수가 여러 개 일때,
		 if(i == checkLast){                     //체크된 체크박스 중 마지막 체크박스일 때,
		 rowid += checkRow;  //'value'의 형태 (뒤에 ,(콤마)가 붙지않게)
		 }else{
		 rowid += checkRow+", ";	 //'value',의 형태 (뒤에 ,(콤마)가 붙게)         			
		 }
		 					
		 }
		 cnt++;
		 checkRow = '';    //checkRow초기화.
		 }

		 }
	 }
	 
	 // 날짜별 선택 안할때 총 날짜 수에 인원을 곱해 총 인원수를 구한다.
	 $("#maxMember").blur(function() {
		 checkDay();
		 var startDay = $("#startDay").val();
		 var endDay = $("#endDay").val();
		 var start = new Date(startDay);
		 var end = new Date(endDay);
		 var maxAll=0;
		 
		 $.post("<%=cp%>/cal/eachDayCount", {startDay:startDay, endDay:endDay, checkDay:rowid}, function(data){
			$("#eachDay").html(data);
					maxAll = $("#maxMember").val()*(data.length-1);	
					$("#maxAll").val("총 "+maxAll+"명");
					rowid = data[data.length-1];
					
					url="<%=cp%>/cal/eachDayOk";
					$.get(url, {start:startDay, end:endDay, checkDay:rowid}, function(data){ 	
						for(var i=0; i<data.length-1;i++){
							$("#eachMember_"+data[i]).val($("#maxMember").val());
						}
					});
			});
		 rowid="";
	 });
	 
	 // 날짜별 선택 불러오기
	 $(function(){
		 $("#eachDay").hide();
		 $("#eachDayBtn").click(function(){
			 
				 
				if($("#eachDay").is(':visible')) {
					f.maxMember.readOnly=false;
					$("input[type='checkbox']").attr("disabled", false);
					$("#maxAll").val("총 0명");
					$("#maxMember").val("");
					$("#eachDay").hide();
					$("#listClosed").val("1");
				} else {
					eachDaySelector()
					$("input[type='checkbox']").attr("disabled", true);
					f.maxMember.readOnly=true;
					$("#maxMember").val("날짜별 인원 선택");
					$("#maxAll").val("총 0명");
					$("#eachDay").show();
					$("#listClosed").val("0");
				}
		 });
	});
	 
	 /* 테마 불러올때 쓰는 함수 */
	 function themeList() {
			var groupNum=$("#groupNum").val();
			if(groupNum=="") {
				$("#themeNum option").each(function() {
					$("#themeNum option:eq(0)").remove();
				});

				$("#themeNum").append("<option value=''>:: 중분류 ::</option>");
				return false;
			}
			
			var url="<%=cp%>/club/themeList";
			var params="groupNum="+groupNum;
			
			$.ajax({
				type:"post"
				,url:url
				,data:params
				,dataType:"json"
				,success:function(data){
					$("#themeNum option").each(function() {
						$("#themeNum option:eq(0)").remove();
					});

					 $("#themeNum").append("<option value=''>:: 중분류 ::</option>");
					 
					 var cn="${dto.themeNum}";
					 var s;
					 
					 for(var idx=0; idx<data.listTheme.length; idx++) {
						 s="";
						 if(cn==data.listTheme[idx].themeNum)
							 s=" selected='selected'";
						 $("#themeNum").append("<option value='"+data.listTheme[idx].themeNum+"' " + s +">"+data.listTheme[idx].subject+"</option>");
					 }
				}
			    ,error:function(e) {
			    	alert(e.responseText);
			    }
			});
		}
	 
	 
	 function eachOk(){
		 	var startDay = $("#startDay").val();
		 	var endDay = $("#endDay").val();
		 	checkDay();
			url="<%=cp%>/cal/eachDayOk";
			$.get(url, {start:startDay, end:endDay, checkDay:rowid}, function(data){ 	
				var i=0;
				var maxMem=0;
				for (var i=0; i<(data.length-1);i++) {
					maxMem = maxMem + parseInt($("#eachMember_"+data[i]).val());
					$("#eachMember_"+data[i]).attr("readonly", true);
					}
				$("#maxAll").val("총 "+maxMem+"명");
				f.btnEachOk.disabled=true;
				f.eachDayBtn.disabled=true;
			});
			rowid="";
		}

		function eachCalcel(){
			var startDay = $("#startDay").val();
			var endDay = $("#endDay").val();
			checkDay();
			$("#maxAll").val("총 0명");
			url="<%=cp%>/cal/eachDayOk";
			$.get(url, {start:startDay, end:endDay, checkDay:rowid}, function(data){ 	
				var i=0;
				var maxMem=0;
				for (var dto in data) {
					$("#eachMember_"+data[i]).attr("readonly", false);
					i++;
					}
				f.btnEachOk.disabled=false;
				f.eachDayBtn.disabled=false;
			});
			rowid="";
		}
		
		function insertOk(){
			checkDay();
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			var title=$.trim($("input[name='title']").val());
			var startDay=$.trim($("input[name='startDay']").val());
			var endDay=$.trim($("input[name='endDay']").val());
			var startTime=$.trim($("input[name='startTime']").val());
			var endTime=$.trim($("input[name='endTime']").val());
			var content=$.trim($("textarea[name='content']").val());
			// 봉사요일 추가해야함
			var maxAll=$.trim($("input[name='maxAll']").val());
			var themeNum=$.trim($("select[name='themeNum']").val());
			var volunteer_type=$.trim($("select[name='volunteer_type']").val());
			var place=$.trim($("input[name='place']").val());
			
			var urlE = "<%=cp%>/cal/eachDayInsert";
			var eachDayArray="";
			var eachDayValueArray="";
			$.post(urlE ,{startDay:startDay, endDay:endDay, checkDay:rowid}, function(data){
				var i=0;
				for (var dto in data) {
					eachDayArray = eachDayArray + data[i]+", ";
					eachDayValueArray = eachDayValueArray + parseInt($('#eachMember_'+data[i]).val())+", ";
					i++;
					}
				
				var params="title="+title
			       +"&startDay="+startDay
			       +"&endDay="+endDay
			       +"&startTime="+startTime
			       +"&endTime="+endTime
			       +"&content="+content
			       +"&themeNum="+themeNum
			       +"&volunteer_type="+volunteer_type
			       +"&place="+place
			       +"&eachDayArray="+eachDayArray
			       +"&eachDayValueArray="+eachDayValueArray;
				var url="<%=cp%>/cal/insertSchedule";
		        
		    	 
		    	 $.ajax({      
			         type:"POST",  
			         url:url,      
			         data:params,      
			         success:function(){
					        //  calendarReset();
					        location.reload();
			         },   
			         error:function(e){  
			             alert(e.responseText);  
			         }  
			     }); 
			     	$('#scheduleModal').modal('hide');
				});
			rowid="";
		 }
	 
</script>

<script type="text/javascript" src="<%=cp%>/res/se/js/HuskyEZCreator.js" charset="utf-8"></script>

<div class="container-fluid">
	<form class="form-horizontal" name="myForm">
		<div class="form-group">
			<label class="col-md-2 control-label">제목</label>
			<div class="col-md-10" id="schTitle">
				<input class="form-control" name="title" type="text"
					placeholder="제목">
			</div>
		</div>


		<div class="form-group">
			<label class="col-md-2 control-label">봉사일정</label>
			<div class="col-md-10" id="schEndDay">
				<div class="row">
					<div class="col-md-5" style="padding-right: 0px;">
						<input id="startDay" class="form-control" name="startDay" type="text"
							readonly="readonly" style="background: #fff;" placeholder="시작날짜">
					</div>
					<div class="col-md-1 control-label">~</div>
					<div class="col-md-5" style="padding-left: 0px;">
						<input id="endDay" class="form-control" name="endDay" type="text"
							readonly="readonly" style="background: #fff;" placeholder="종료날짜">
					</div>

				</div>
			</div>

		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">봉사시간</label>
			<div class="col-md-10" id="schStartDay">
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
				월&nbsp;<input type="checkbox" name="checkDay" value="2" checked="checked">&nbsp;&nbsp;
				화&nbsp;<input type="checkbox" name="checkDay" value="3" checked="checked">&nbsp;&nbsp;
				수&nbsp;<input type="checkbox" name="checkDay" value="4" checked="checked">&nbsp;&nbsp;
				목&nbsp;<input type="checkbox" name="checkDay" value="5" checked="checked">&nbsp;&nbsp;
				금&nbsp;<input type="checkbox" name="checkDay" value="6" checked="checked">&nbsp;&nbsp;
				토&nbsp;<input type="checkbox" name="checkDay" value="7" checked="checked">&nbsp;&nbsp;
				일&nbsp;<input type="checkbox" name="checkDay" value="1" checked="checked">
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">모집인원 /일</label>
			<div class="col-md-4">
				<input class="form-control" name="maxMember" id="maxMember" type="text"
					placeholder="인원 수">
			</div>
			<div class="col-md-2"><button id="eachDayBtn" name="eachDayBtn" type="button" class="btn btn-primary">날짜 별 선택</button></div>
			<input id="maxAll" class="form-control col-md-2" style="width:120px; text-align:right;" readonly="readonly" value="총 0명">
		</div>
		
		<div id=eachDay class="form-group"></div>
		<div class="form-group">
			<div>
			<label class="col-md-2 control-label">봉사분야</label>
			<div class="col-md-5" id="schClassify" style="width:280px;">
				<select name="groupNum" id="groupNum" class="form-control" onchange="themeList();" style="float:left; width:120px; margin-right:10px;">
						<option value="">:: 대분류 ::</option>
	             	<c:forEach var="vo" items="${listGroup}">
			            <option value="${vo.themeNum}" ${vo.themeNum==dto.groupNum ? "selected='selected'" : ""}>${vo.subject}</option>
				    </c:forEach>
				</select>
				<select name="themeNum" id="themeNum" class="form-control" style="float:left; width:120px;">
				    <option value="">:: 중분류 ::</option>
				</select>
			</div>
			</div>
			<label class="col-md-2 control-label">봉사자 유형</label>
			<div class="col-md-3">
				<select name="volunteer_type" class="form-control">
					<option>성인</option>
					<option>청소년</option>
				</select>
			</div>
		</div>
		
		<div class="form-group" id="schContent" style="min-height: 75px;">
			<label class="col-md-2 control-label">내용</label>
			<div class="col-md-10"><textarea id="content" name="content" class="form-control" style="max-width: 99%;"></textarea></div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">수요처이름</label>
			<div class="col-md-10" id="schUserName">
				<p class="form-control-static">희망복지관</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">수요처 장소</label>
			<div class="col-md-10" id="schUserName">
				<input class="form-control" name="place" type="text" value="서울특별시 노원구 삼양동 종합복지센터 장암역 1번출구">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-2 control-label">파일 업로드</label>
			<div class="col-md-10" id="schUserName">
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

 