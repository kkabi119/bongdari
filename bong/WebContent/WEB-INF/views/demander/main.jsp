<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>

<link rel="stylesheet" href="<%=cp%>/res/css/style.css" type="text/css">

<style type="text/css">
 .row4 {
/* 	margin-top: 20px;
	margin-bottom: 60px;
	margin-left: 20px; */
	height: 400px;
	width: 600px;
} 
</style>
<script type="text/javascript" src="<%=cp%>/res/jquery/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript">
function cityList() {
	var snum=$("#sido").val();
	if(snum=="") {
		$("#city option").each(function() {
			$("#city option:eq(0)").remove();
		});

		$("#city").append("<option value=''>::도시선택::</option>");
		return false;
	}
	var url="<%=cp%>/main/demander";
	var params="snum="+snum;
	
	$.ajax({
		type:"post"
		,url:url
		,data:params
		,dataType:"json"
		,success:function(data){
			$("#city option").each(function() {
				$("#city option:eq(0)").remove();
			});

			 $("#city").append("<option value=''>::도시선택::</option>");
			 
			 for(var idx=0; idx<data.list.length; idx++) {
				 $("#city").append("<option value='"+data.list[idx].cnum+"'>"+data.list[idx].city+"</option>");
			 }
		}
	    ,error:function(e) {
	    	alert(e.responseText);
	    }
	});
}

function result() {
	var snum=$("#sido").val();
	var cnum=$("#city").val();
	var sido=$("#sido :selected").text();
	var city=$("#city :selected").text();

	if(! snum || !cnum)
		return false;
	
	var s=sido+":"+snum+", "+city+":"+cnum;
	alert(s);
}
</script>
<div style="margin: 50px auto 10px; height: 200px;" align="center">
	<div class="row4">
		<div class="col-md-12">
			<ul id="tab2" class="nav nav-pills">
				<li class="active"><a href="#tab2-item1" data-toggle="tab">지도로 찾기</a></li>
				<li><a href="#tab2-item2" data-toggle="tab" >분야로 찾기</a></li>

			</ul>
			<div class="tab-content" style="background: #F6F6F6; height: 200px;
			border-radius: 7px 7px 7px 7px ;margin-top:5px; ">
				<div class="tab-pane fade active in" id="tab2-item1">
					<div style="color: blue">지역별 검색</div>
					시도선택<select id="sido" onchange="cityList();" class="selectField"
						name="sido">
						<option value="">::시도선택::</option>
						<c:forEach var="dto" items="${list}">
							<option value="${dto.snum}">${dto.sido}</option>
						</c:forEach>
					</select> 도시선택<select id="city" class="selectField" name="city">
						<option value="">::도시선택::</option>
					</select> <br> 수요처 명<input type="text" name="demandName"
						class="textField"> <input type="button" value=" 확인 "
						onclick="result();" class="btn"> 
					<table>
						<tr>
							<td></td>
						</tr>
					</table>	
				</div>
				<div class="tab-pane fade" id="tab2-item2">
					<div style="color: blue">분야별 검색</div>
					시설유형<select id="demandType" class="selectField">
						<option value="">::시설유형::</option>
					</select> 수요처 명<input type="text" name="demandName" class="textField">
					<input type="button" value=" 확인 " onclick="result();" class="btn">

				</div>
				<div class="tab-pane fade" id="tab2-item2">
					<div style="color: blue">분야별 검색</div>
					시설유형<select id="demandType" class="selectField">
						<option value="">::시설유형::</option>
					</select> 수요처 명<input type="text" name="demandName" class="textField">
					<input type="button" value=" 확인 " onclick="result();" class="btn">

				</div>

			</div>
		</div>
	</div>
</div>

<%--         
<!-- 도시별/분야별 검색 -->
<div style="margin: 50px auto 10px;" align="center">
	<div style="color: blue">지역별 검색</div>
	시도선택<select id="sido" onchange="cityList();" class="selectField" name="sido">
		<option value="">::시도선택::</option>
		<c:forEach var="dto" items="${list}">
			<option value="${dto.snum}">${dto.sido}</option>
		</c:forEach>
	</select> 도시선택<select id="city" class="selectField" name="city">
		<option value="">::도시선택::</option>
	</select> <br> 수요처 명<input type="text" name="demandName" class="textField">
	<input type="button" value=" 확인 " onclick="result();" class="btn">
	<br>
	<hr>
	<br>
	<div style="color: blue">분야별 검색</div>
	시설유형<select id="demandType" class="selectField">
		<option value="">::시설유형::</option>
	</select> 수요처 명<input type="text" name="demandName" class="textField"> <input
		type="button" value=" 확인 " onclick="result();" class="btn">

</div> --%>

<!-- 리스트 -->
<div style="margin: 100px auto 10px; width: 1000px" align="center" >

        <div style="clear: both; height: 30px; line-height: 30px;">
            <div style="float: left;">${dataCount}개(${page}/${total_page} 페이지)</div>
            <div style="float: right;">&nbsp;</div>
        </div>
        
        <div class="table-responsive" style="clear: both;"> <!-- 테이블 반응형 -->
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th class="text-center" style="width: 100px;">번호</th>
                        <th class="text-center" style="width: 150px;">수요처명</th>
                        <th class="text-center" >주소</th>
                        <th class="text-center" style="width: 200px;">전화번호</th>
                        <th class="text-center" style="width: 100px;">시설유형</th>
                    </tr>
                </thead>
                <tbody>
              <%--   <c:forEach var="dto" items="${list}"> --%>
                    <tr> 
                        <td class="text-center">1</td>
                        <td class="text-center"><a href="<%=cp%>/main/articleDemander">[수요처명]</a></td>
  					    <td class="text-center">경기도 고양시 덕양구</td>
                        <td class="text-center">010-1111-2222</td>
                        <td class="text-center">보육</td>
                    </tr>
                <%-- </c:forEach> --%>
                </tbody>
            </table>
        </div>

        <div class="paging" style="text-align: center; min-height: 50px; line-height: 50px;">
          <c:if test="${dataCount==0}">
          	등록된 게시물이 없습니다
          </c:if>
          <c:if test="${dataCount!=0}">
          	${paging}
          </c:if>

    </div>    
</div>


