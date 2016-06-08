<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<label class="col-md-2 control-label">날짜별 선택</label>
<div class="col-md-10">
<div>
<label class="col-md-6 control-label" style="text-align:center;">날짜</label>
<label class="col-md-6 control-label" style="text-align:center;">인원수</label>
</div>

<c:if test='${flag=="OK"}'>
<c:forEach var="date" items="${list}">
<div>
<div class="col-md-6 control-label" style="text-align:center;">${date}</div>
<div class="col-md-6" style="text-align:center;">
			<input class="form-control" name="eachMember_${date}" id="eachMember_${date}" type="text" placeholder="인원 수">
</div>
</div>
</c:forEach>	
</c:if>
<c:if test='${flag=="error"}'>
선택된 날짜가 없습니다. 날짜를 수정해주세요.
</c:if>
	
<div class="col-md-12" id="schFooter" style="margin-top:10px;">
		<button type="button" name="btnEachOk" class="col-md-5 btn btn-primary" id="btnEachOk"
			onclick="eachOk();">
			적용하기 <span class="glyphicon glyphicon-ok"></span>
		</button>
		<button type="button" style="margin-left:40px;" class="col-md-5 btn btn-primary" id="btnEachCalcel"
			onclick="eachCalcel();">
			취소하기 <span class="glyphicon glyphicon-ok"></span>
		</button>
</div>
</div>