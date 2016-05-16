<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<head>
<meta charset='utf-8' />
<link href='<%=cp%>/res/calendar/fullcalendar.css' rel='stylesheet' />
<link href='<%=cp%>/res/calendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='<%=cp%>/res/calendar/lib/moment.min.js'></script>
<script src='<%=cp%>/res/calendar/lib/jquery.min.js'></script>
<script src='<%=cp%>/res/calendar/fullcalendar.min.js'></script>
<script src='<%=cp%>/res/calendar/lib/ko.js'></script>

<script>

	$(document).ready(function() {

		$('#calendar').fullCalendar({
			lang: 'ko',
			selectable: true,
			defaultDate: '2016-05-12',
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			select: function(){
				insertForm();
				},
			eventClick : function(){
				articleForm();
			},
			events: [
				{
					title: 'All Day Event',
					start: '2016-05-01'
				},
				{
					title: 'Long Event',
					start: '2016-05-07',
					end: '2016-05-10'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2016-05-09T16:00:00'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2016-05-16T16:00:00'
				},
				{
					title: 'Conference',
					start: '2016-05-11',
					end: '2016-05-13'
				},
				{
					title: 'Meeting',
					start: '2016-05-12T10:30:00',
					end: '2016-05-12T12:30:00'
				},
				{
					title: 'Lunch',
					start: '2016-05-12T12:00:00'
				},
				{
					title: 'Meeting',
					start: '2016-05-12T14:30:00'
				},
				{
					title: 'Happy Hour',
					start: '2016-05-12T17:30:00'
				},
				{
					title: 'Dinner',
					start: '2016-05-12T20:00:00'
				},
				{
					title: 'Birthday Party',
					start: '2016-05-13T07:00:00'
				},
				{
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2016-05-28'
				}
			]
		});
		
	});
	
	
	// 일정 등록 폼
	function insertForm() {
		/* load로 해야 넘어간다. 왜그런지 모르겠네 */
		var uri="<%=cp%>/cal/inputForm";
		$('#scheduleModal .modal-body').load(uri, function() {
		    $('#scheduleModal .modal-title').html('일정 추가');
			$('#scheduleModal').modal();
			calendar.fullCalendar('unselect');
		});
	}

	//-- 상세 일정 보기
	function articleForm() {
		$('#scheduleModal .modal-body').load("<%=cp%>/cal/articleForm", function() {
		    $('#scheduleModal .modal-title').html('상세 일정');
			$('#scheduleModal').modal('show');
		});	
	}
</script>

<script src="<%=cp%>/res/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<style>
	#calendar {
		max-width: 900px;
	}

</style>
</head>
<body>
	
	<!-- 달력 표시 div -->
	<div id='calendar'></div>
	
	<!-- 추가 또는 보기 모달창 div -->
	<div class="modal fade" id="scheduleModal" tabindex="-1" role="dialog" aria-labelledby="scheduleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
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
