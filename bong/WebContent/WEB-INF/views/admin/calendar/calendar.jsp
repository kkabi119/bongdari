<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<!DOCTYPE html>
<html>
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
	
	//-- 상세 일정 보기
	function articleForm() {
		$('#scheduleModal .modal-body').get("<%=cp%>/cal/articleForm", function() {
		    $('#scheduleModal .modal-title').html('상세 일정');
			$('#scheduleModal').modal('show');
		});	
	}
	
	// 일정 등록 폼
	function insertForm() {
		$('#scheduleModal .modal-body').get("<%=cp%>/cal/inputForm", function() {
		    $('#scheduleModal .modal-title').html('일정 추가');
			$('#scheduleModal').modal('show');
			calendar.fullCalendar('unselect');
		});
	}

</script>
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>
</head>
<body>

	<div id='calendar'></div>

</body>
</html>
