<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>

<head>
<meta charset='utf-8' />
<link href='<%=cp%>/res/calendar/fullcalendar.css' rel='stylesheet' />
<link href='<%=cp%>/res/calendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='<%=cp%>/res/calendar/lib/moment.min.js'></script>
<script src='<%=cp%>/res/calendar/fullcalendar.min.js'></script>
<script src='<%=cp%>/res/calendar/lib/lang-all.js'></script>

<script>


	$(function(){
		initcal();
	});
	
	
	
	function initcal() {

		$('#calendar').fullCalendar({
			lang: 'ko',
			selectable: true,
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			select: function(start, end){
				insertForm(start, end);
				},
			eventClick : function(calEvent){
				articleForm(calEvent);
			},
			events:
			function(start, end, timezone, callback){
				// 캘린더가 처음 실행되거나 월이 변경되면
				var startDay=start.format("YYYY-MM-DD");
				var endDay=end.format("YYYY-MM-DD");
				
		        
				var url="<%=cp%>/cal/${demander_seq}/list?start="+startDay+"&end="+endDay;

				$.ajax({
				    url: url,
				    dataType: 'json',
				    success: function(data, text, request) {
				    	
				    	
				    	
				    	/* 세션은 지금 필요없으니 주석처리 */
			        	<%--  var isLogin=data.isLogin;
			        	 if(isLogin=="false") {
			        		   location.href="<%=cp%>/member/login";
			        		   return;
			        	} --%>
				    	var events = eval(data.list);
				        callback(events);
				        
				        
				    }
				});
			}
		});
		
	}
	
	
	// 일정 등록 폼
	function insertForm(start, end) {
		/* load로 해야 넘어간다. 왜그런지 모르겠네 */
		var uri="<%=cp%>/cal/inputForm";
		$('#scheduleModal .modal-body').load(uri, function() {
			var startDay="", startTime="";
			var endDay="", endTime="";
			
			startDay=start.format("YYYY-MM-DD");
			endDay=end.format("YYYY-MM-DD");
			startTime=start.format("09:00");
			endTime=end.format("17:00");

			$("input[name='startDay']").val(startDay);
			$("input[name='startTime']").val(startTime);
			$("input[name='endTime']").val(endTime);

				$("#startTime").show();
				$("#endTime").show();
				if(startDay!=(end.add("-30", "minutes").format())) { // 2일 이상 선택했을 때
					endDay=end.format("YYYY-MM-DD");
					$("input[name='endDay']").val(endDay);
				} else {
				$("input[name='endDay']").val(startDay); // 하루 선택했을 때
				}
		    $('#scheduleModal .modal-title').html('일정 추가');
			$('#scheduleModal').modal();
		});
	}

	//-- 상세 일정 보기
	function articleForm(calEvent) {
		var url = "<%=cp%>/cal/articleForm";
		var id= calEvent.id;
		$.post(url, {id:id}, function(data) {
			$('#scheduleModal .modal-body').html(data);
		    $('#scheduleModal .modal-title').html('상세 일정');
			$('#scheduleModal').modal('show');
		});	
	}
	
	// 봉사활동 동아리로 가져오기
	function take(volunIdx){
		alert("내 동아리에 등록 되었습니다.");
		var url = "<%=cp%>/cal/takeVolun";
		$.post(url, {volunIdx:volunIdx}, function(data){
			$('#scheduleModal').modal('hide');
		});
	}
	
	
	
</script>


<style>
	#calendar {
		max-width: 900px;
	}

</style>
</head>
<body>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="tab1-item1">
                               <div id="calendar"></div>
                            </div>
                           
                        </div>
            

            <!-- 일정 달력으로 보기 부분 -->
            
            <!-- 추가 또는 보기 모달창 div -->
	<div class="modal fade" id="scheduleModal" tabindex="-1" role="dialog" aria-labelledby="scheduleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" style="width:800px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="scheduleModalLabel" style="font-family: 나눔고딕, 맑은 고딕, sans-serif; font-weight: bold;">일정</h4>
	      </div>
	      <div class="modal-body"></div>
	    </div>
	  </div>
	</div>
</body>
            