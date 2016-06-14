<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<c:forEach var="dto" items="${list}">
<a onclick="selectedMember(${dto.userIdx});" style="cursor:pointer">${dto.userName}</a><br>
</c:forEach>


