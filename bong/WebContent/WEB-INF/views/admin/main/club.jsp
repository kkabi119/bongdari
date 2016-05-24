<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>


<style type="text/css">
 .row4 {
/* 	margin-top: 20px;
	margin-bottom: 60px;
	margin-left: 20px; */
	height: 400px;
	width: 600px;
} 
</style>
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
<!-- 검색결과는 ajax써서 jsp따로 빼야할 듯! -->
<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow" style="padding:0px;">
 					<div class="bodyFrame2">
          				<h3  style="font-size:30px;"> 동아리 목록<span style="margin-left:10px;color:gray; font-size:15px;">다음과 같은 동아리가 있어</span> </h3>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left; color:#3897f0;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 전체 ${dataCount}개 <span style="color:#777;">(1/10 페이지)</span> </div>
            			
        				</div> 
        		<!-- <hr style="margin-bottom:10px; margin-top:0px; border:1px solid gray;"> -->
        		
        				<div class="table-responsive" style="clear: both; "> <!-- 테이블 반응형 -->
            				<table class="table table-hover" style="overflow:hidden;" >
                				<thead style="min-width:100%; font-size:15px; background-color:#DFE6E8; color:#555; ">
                    				<tr >
                        				<th class="text-center" style="width:50px; font-weight:500;">번호</th>
                        				<th class="text-center" style="width:100px; font-weight:500;">동아리 이름</th>
                        				<th class="text-center" style="width:105px; font-weight:500;">개설일</th>
                        				<th class="text-center" style="width:105px; font-weight:500;">봉사 분야</th>
                        				<th class="text-center" style="width:105px; font-weight:500;">봉사시간 총합</th>
                        				<th class="text-center" style="width:105px; font-weight:500;">NoShow 총합</th>
                        				<th class="text-center" style="width:110px; font-weight:500;">지역</th>
                        				<th class="text-center" style="width:75px; ;font-weight:500;">인원</th>
                        				
                    				</tr>
                				</thead>
                				
                				<tbody>
                				<c:forEach var="dto" items="${list}">
									<tr>
                        				<td class="text-center">${dto.rNum}</td>
                        				<td class="text-center"><a href="<%=cp%>/admin/appDetail">${dto.clubName}</a></td>
                       				 	<td class="text-center">${dto.clubBirth}</td>
                       				 	<td class="text-center">${dto.typeIdx}</td>
                       				 	<td class="text-center">${dto.total_Hours}</td>
                       				 	<td class="text-center">${dto.total_NoShow}</td>
                       				 	<td class="text-center">${dto.clubAddr}</td>
                        				<td class="text-center" style="">${dto.member}명</td>
            						</tr>
								</c:forEach>            						
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
	

