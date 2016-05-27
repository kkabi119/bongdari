<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<label class="col-md-3 control-label">날짜별 선택</label>
<div class="col-md-8">
<div>
<label class="col-md-6 control-label" style="text-align:center;">날짜</label>
<label class="col-md-6 control-label" style="text-align:center;">인원수</label>
</div>


<div>
<div class="col-md-6 control-label" style="text-align:center;">2016-05-28</div>
<div class="col-md-6" style="text-align:center;">
			<input class="form-control" name="eachMember_index" type="text" placeholder="인원 수">
</div>
</div>	

<div>
<div class="col-md-6 control-label" style="text-align:center;">2016-05-29</div>
<div class="col-md-6" style="text-align:center;">
			<input class="form-control" name="eachMember_index" type="text" placeholder="인원 수">
</div>
</div>

<div>
<div class="col-md-6 control-label" style="text-align:center;">2016-05-30</div>
<div class="col-md-6" style="text-align:center;">
			<input class="form-control" name="eachMember_index" type="text" placeholder="인원 수">
</div>
</div>	

	
<div class="col-md-12" id="schFooter" style="margin-top:10px;">
		<button type="button" class="col-md-5 btn btn-primary" id="btnModalOk"
			onclick="insertOk();">
			적용하기 <span class="glyphicon glyphicon-ok"></span>
		</button>
		<button type="button" style="margin-left:40px;" class="col-md-5 btn btn-primary" id="btnModalOk"
			onclick="">
			취소하기 <span class="glyphicon glyphicon-ok"></span>
		</button>
</div>
</div>