<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<script type="text/javascript">

function deleteClub(){
	
	var f = document.deleteClubForm;
	
	f.action="<%=cp%>/club/${clubSeq}/manage/deleteClub";
	
	if(confirm("정말로 동아리를 삭제 하시 겠습니까 ? "))
	
	f.submit();
}

</script>
<div>
	<form name="deleteClubForm" method="post">
		<div class="form-group">
  <div class="col-md-12 col-sm-12">
  
  	<h1>주의!!</h1>
  	<h3> 동아리 삭제 버튼을 누르시면 모든 동아리 활동 및 정보가 사라지고 복구할 수 없습니다. </h3>
  	<h3> 신중하게 결정해 주세요!</h3>
  	
		<div style="padding: 100px">
            <button type="button" class="btn btn-default" onclick="javascript:location.href='<%=cp%>/club/${clubSeq}/manage';" style="margin-right:20px; height:200px; width:200px;">
                 취 소 <span class="glyphicon glyphicon-remove"></span></button>   
            <button type="button" name="sendButton" class="btn btn-danger" onclick="deleteClub();" style="margin-right:20px; height:200px; width:200px;">
                   동아리 삭제 <span class="fa fa-trash"></span></button>
    	</div>
    	
    </div>
</div>
	</form>
</div>