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
 function searchMainList() {
		var f=document.searchForm2;
		f.action="<%=cp%>/main/demander/mainResult";
		f.submit(); 
	}

</script>
<div style="margin: 50px auto 10px; height: 200px; width:800px;" align="center">
 
	<div class="row4">
		<div class="col-md-12">
			<ul id="tab2" class="nav nav-pills">
				<li class="active"><a href="#tab2-item1" data-toggle="tab">지역</a></li>
				<li><a href="#tab2-item2" data-toggle="tab" >분야</a></li>
				<li><a href="#tab2-item3" data-toggle="tab" >수요처 이름</a></li>

			</ul>
		<form name="searchForm2" method="post" class="form-inline">	
			<div class="tab-content" style="background: #F6F6F6; height: 100px; 
			border-radius: 7px 7px 7px 7px ;margin-top:5px; ">
	
			<!-- 지역 검색 -->
				<div class="tab-pane fade active in" id="tab2-item1">
				 
					<select id="sido"  class="selectField"
						name="sido" style="margin-right: 10px; height: 30px; width: 100px;">
						<option value="">::시도선택::</option>
						<option value="서울특별시">서울특별시</option>
						<option value="인천광역시">인천광역시</option>
						<option value="세종특별자치시">세종특별자치시</option>
						<option value="경기도">경기도</option>
						<option value="강원도">강원도</option>
						<option value="대구광역시">대구광역시</option>
						<option value="부산광역시">부산광역시</option>
						<option value="울산광역시">울산광역시</option>
						<option value="경상남도">경상남도</option>
						<option value="경상북도">경상북도</option>
						<option value="전라남도">전라남도</option>
						<option value="광주광역시">광주광역시</option>
						<option value="충청남도">충청남도</option>
						<option value="충청북도">충청북도</option>
						<option value="대전광역시">대전광역시</option>
					</select> 
						<input type="text" class="textField" name="addr" style="margin-right: 10px;">
						<input type="button" value="검색" onclick="searchMainList();"
						id="searchBtn" class="btn">
					
					<table>
						<tr>
							<td></td>
						</tr>
					</table>	
				</div>
			<!-- 분야 검색 -->
				<div class="tab-pane fade" id="tab2-item2">
					<select name="demanderType" class="selectField" 
					style="height: 30px; width: 160px; border-radius: 5px 5px 5px 5px; margin-right: 10px;">
						<option value="">::분야 선택::</option>
						<option value="disabled">장애인</option>
						<option value="silver">노인</option>
						<option value="child">아동</option>
						<option value="etc">기타</option>
						</select>
					<input type="button" value="검색" onclick="searchMainList();"
					id="searchBtn" class="btn">
				</div>
			<!-- 수요처 검색 -->
				<div class="tab-pane fade" id="tab2-item3">
					<input type="text" class="textField" name="demanderName" style="margin-right: 10px;">
					<input type="button" value="검색" onclick="searchMainList();"
					id="searchBtn" class="btn">
				</div>
		</div>
	</form>
		</div>
	</div>
	

</div>


<div style="margin: auto; width: 1000px" align="center" ><!-- margin: 100px auto 10px; -->
<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow" style="padding:0px;">
 					<div class="bodyFrame2">
          				<h3  style="font-size:30px;"> 검색결과<span style="margin-left:10px;color:gray; font-size:15px;">다음과 같은 봉사활동이 있습니다.</span> </h3>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left; color:#3897f0;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 전체    ${dataCount}개 <span style="color:#777;">(${page}/${total_page} 페이지)</span> </div>
            			
        				</div> 
        		
        		
        				<div class="table-responsive" style="clear: both; "> <!-- 테이블 반응형 -->
            				<table class="table table-hover" style="overflow:hidden;" >
                				<thead style="min-width:100%; font-size:15px; background-color:#DFE6E8; color:#555; ">
                    				<tr >
                        				<th class="text-center" style="width: 60px; font-weight:500;  ">No.</th>
                        				<th class="text-center" style="width: 500px;">제목</th>
                        				<th class="text-center" style="width:160px; font-weight:500;">수요처 이름</th>
                        				<th class="text-center" style="width:200px; font-weight:500;">봉사기간</th>
                        				<th class="text-center" style="width:150px;font-weight:500;">위치</th>
                        				<th class="text-center" style="width:150px;font-weight:500;">분야</th>
                        				<th class="text-center" style="width:150px;font-weight:500;">상태</th>
                        				
                    				</tr>
                				</thead>
                				<tbody>
                				<c:forEach var="dto" items="${searchList}">
									<tr>
                        				<td class="text-center">${dto.listNum}</td>
                        				<td class="text-center" ><a href="<%=cp%>/demander/${dto.volunIdx}/main">${dto.subject}</a></td>
                       				 	<td class="text-center" >${dto.serviceName}</td>
                       				 	<td class="text-center" >${dto.startDay}~${dto.endDay}</td>
                       				 	<td class="text-center" >${dto.place}</td>
                       				 	<td class="text-center" >${dto.lSubject} > ${dto.sSubject}</td>
                       				 	<td class="text-center" >${dto.progress}</td>
                        				
                        				
            						</tr>
            					</c:forEach>	
            						
                				</tbody>
            				</table>
        				</div>

        				<div class="paging" style="text-align: center; min-height: 30px; line-height: 30px;">
<c:if test="${dataCount==0 }">
                 검색 결과가 없습니다. 
</c:if>
<c:if test="${dataCount!=0 }">
                ${paging}
</c:if>
        				</div>        
        
        				
        			</div>
        		</div>
        	</div>
        </div>
	</div> 
	</div> 
	

