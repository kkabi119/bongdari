<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<link rel="stylesheet" href="<%=cp%>/res/css/fileinput.css" type="text/css">

<script type="text/javascript" src="<%=cp%>/res/js/util.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/fileinput.js"></script>

<script type="text/javascript">
$(function(){
	/* $("#introduce").html(introduce); */
});
</script>

<style>
.btn .btn-success:hover{
	backgroundr:#FFE258;
	color:white;
}
</style>

<div class="container" style="width:100%; margin-bottom:10px;">
<div style=" text-align:center; vertical-align:center; ">
	
	<h3 style="color:#333; font-weight:800; line-height: 27px;">
	<c:if test="${enabled==0}">
	<div>
	    <img style=" width:75px; height:75px; background-size:cover; margin-bottom:25px; "src="<%=cp%>/res/images/myclub/success.png" alt="">
	</div>
	가입신청이 완료되었습니다 !<br> 
		<span style="font-size:14px; color:#777; ">봉다리장이 승인 후 활동 가능합니다 </span>
		
	</c:if>
	<c:if test="${enabled==1}">
	<div>
	 <img style=" width:75px; height:75px; background-size:cover; margin-bottom:25px; "
	 			src="<%=cp%>/res/images/myclub/error.png" alt="">
	 </div>
		가입에 실패하였습니다!<br>
			<span style="font-size:14px; color:#777; ">이미 3개의 봉다리에 가입하셨습니다 </span> 
		
	</c:if>
	<c:if test="${enabled==2}">
 	<div>	<img style=" width:75px; height:75px; background-size:cover; margin-bottom:25px; "
	 			src="<%=cp%>/res/images/myclub/error.png" alt="">
	 	</div>
	
		가입에 실패하였습니다!<br>
		<span style="font-size:14px; color:#777; ">	같은 지역의 봉다리에만 참여하실 수 있습니다</span> 
	</c:if>
	<c:if test="${enabled==3}">
		 <div>
		 <img style=" width:75px; height:75px; background-size:cover; margin-bottom:25px; "
	 			src="<%=cp%>/res/images/myclub/error.png" alt="">
	 	</div>
		이미 해당 봉다리에 가입을 신청하셨습니다! <br>
	<span style="font-size:14px; color:#777; ">	봉다리장의 승인 후 활동 가능하니 기다려주시길 바랍니다</span>  
	</c:if>
	</h3> 
<button type="button" class="btn btn-success" data-dismiss="modal" style="auto; padding: 10px 55px; margin-left:36%; font-weight:bold;
			border-radius: 1px; margin-top:15px;background-color: #f5f9f9;  border:1px solid #ccc; float:left; color:#777;">
		 확 인
	 </button>
</div>
</div>