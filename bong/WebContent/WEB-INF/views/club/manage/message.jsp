<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>

<div style="min-height: 450px;">
		<div style="margin: 100px auto 20px; width: 400px; min-height: 400px;">
			<div style="margin: 0px auto; padding-top:40px; padding-bottom:10px; min-height: 50px; line-height: 130%;  text-align: center;">${message}</div>
			<div style="height: 50px; text-align: center;">
			    <input type="button" value=" 동아리 전체 메인으로 이동 >> "
			              class="moveButton"
			              onclick="javascript:location.href='<%=cp%>/main/club';">
			</div>  
		</div>
</div>
