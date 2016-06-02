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

<!-- 검색결과는 ajax써서 jsp따로 빼야할 듯! -->
<div style="margin: 100px auto 10px; width: 1000px" align="center" >
<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow" style="padding:0px;">
 					<div class="bodyFrame2">
          				<h3  style="font-size:30px;"> 검색결과<span style="margin-left:10px;color:gray; font-size:15px;">다음과 같은 수요처가 있습니다.</span> </h3>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left; color:#3897f0;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 전체    개 <span style="color:#777;">(1/10 페이지)</span> </div>
            			
        				</div> 
        		<!-- <hr style="margin-bottom:10px; margin-top:0px; border:1px solid gray;"> -->
        		
        				<div class="table-responsive" style="clear: both; "> <!-- 테이블 반응형 -->
            				<table class="table table-hover" style="overflow:hidden;" >
                				<thead style="min-width:100%; font-size:15px; background-color:#DFE6E8; color:#555; ">
                    				<tr >
                        				<th class="text-center" style="width: 60px; font-weight:500;  ">번호</th>
                        				<th class="text-center"colspan="4" style="white-space: nowrap;  font-weight:500; ">
                        				수요처 이름</th>
                        				<th class="text-center" style="width:105px; font-weight:500;">봉사 분야</th>
                        				<th class="text-center" style="width:110px; font-weight:500;">지역</th>
                        				<th class="text-center" style="width:150px;font-weight:500;">전화번호</th>
                        				<th class="text-center" style="width:75px; ;font-weight:500;">인원</th>
                        				
                    				</tr>
                				</thead>
                				
                				<tbody>
									<tr>
                        				<td class="text-center">1</td>
                        				<td class="text-center" colspan="4" ><a href="<%=cp%>/demander/index/main">희망 복지관</a></td>
                       				 	<td class="text-center" >보육</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style="">010-1111-1111</a></td>
                        				<td class="text-center" style="">10명</td>
                        				
            						</tr>
            						
            						<tr>
                        				<td class="text-center">2</td>
                        				<td class="text-center" colspan="4" ><a href="<%=cp%>/demander/index/main">소망 복지관</a></td>
                       				 	<td class="text-center" >보육</td>
                       				 	<td class="text-center">덕양구 삼송역</td>
                       				 	<td class="text-center" style="">010-2222-2222</td>
                        				<td class="text-center" style="">20명</td>
            						</tr>
            						
            						
                				</tbody>
            				</table>
        				</div>

        				<div class="paging" style="text-align: center; min-height: 30px; line-height: 30px;">
<c:if test="${dataCount==0 }">
                  등록된 게시물이 없습니다.
</c:if>
<c:if test="${dataCount!=0 }">
                ${paging}
</c:if>
        				</div>        
        
        				<div style="clear: both;">
        					<div style="float: left; width: 20%; min-width: 85px;">
        		    			<button type="button" class="btn btn-default" onclick="javascript:location.href='<%=cp%>/bbs/list';">새로고침</button>
        					</div>
        					<div style="float: left; width: 60%; text-align: center;">
        		     			<form name="searchForm" method="post" class="form-inline">
						  			<select class="form-control input-sm" name="searchKey" style="height:32px">
						      			<option value="subject">제목</option>
						      			<option value="userName">작성자</option>
						      			<option value="content">내용</option>
						      			<option value="created">등록일</option>
						  			</select>
						  			<input type="text" class="form-control input-sm input-search" name="searchValue" placeholder="검색" style="width:50%; height:32px;">
						  			<button type="button" class="btn btn-success" onclick="searchList();" style="background-color: #3897f0; border:none;"><span class="glyphicon glyphicon-search" ></span> 검색</button>
        		     			</form>
        					</div>
        					
        				</div>
        			</div>
        		</div>
        	</div>
        </div>
	</div>
	</div>
	

