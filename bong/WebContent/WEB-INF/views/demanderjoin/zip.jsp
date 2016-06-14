<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>


<c:forEach var="dto" items="${list}">
   <div onclick="selectDemanderZip('${dto.category1} ${dto.category2} ${dto.dong2} ${dto.streetname}')">
   <a>${dto.category1} ${dto.category2} ${dto.dong2} ${dto.streetname}</a></div><br>
</c:forEach>
