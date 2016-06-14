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
		var volunIdx=${volunIdx};
		var url = "<%=cp%>/demander/admin/eachInfo";
		$.post(url,{volunIdx:volunIdx},function(data){
			$("#eachInfo_"+volunIdx).html(data);
		});
	});
</script>
<div class="bodyFrame2">
	<div class="body-title">
		<h3>
			<span class="glyphicon glyphicon-send"></span> 신청자 리스트
		</h3>
	</div>

	<div id="eachInfo_${volunIdx}" style="clear: both;">

	</div>
	<div class="col-md-12" style="margin-bottom:10px;"></div>
	<input type="hidden" name="listClosed" id="listClosed"
		value="${listClosed}">
</div>