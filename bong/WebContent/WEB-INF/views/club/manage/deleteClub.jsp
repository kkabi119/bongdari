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
	
	if(confirm("������ ���Ƹ��� ���� �Ͻ� �ڽ��ϱ� ? "))
	
	f.submit();
}

</script>
<div>
	<form name="deleteClubForm" method="post">
		<div class="form-group">
  <div class="col-md-12 col-sm-12">
  
  	<h1>����!!</h1>
  	<h3> ���Ƹ� ���� ��ư�� �����ø� ��� ���Ƹ� Ȱ�� �� ������ ������� ������ �� �����ϴ�. </h3>
  	<h3> �����ϰ� ������ �ּ���!</h3>
  	
		<div style="padding: 100px">
            <button type="button" class="btn btn-default" onclick="javascript:location.href='<%=cp%>/club/${clubSeq}/manage';" style="margin-right:20px; height:200px; width:200px;">
                 �� �� <span class="glyphicon glyphicon-remove"></span></button>   
            <button type="button" name="sendButton" class="btn btn-danger" onclick="deleteClub();" style="margin-right:20px; height:200px; width:200px;">
                   ���Ƹ� ���� <span class="fa fa-trash"></span></button>
    	</div>
    	
    </div>
</div>
	</form>
</div>