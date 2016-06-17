<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<script type="text/javascript">
function enter(where){
	if(event.keyCode == 13){
		where.focus();
	}
}
</script>
	<div class="row" id="content">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow">
 					<div class="bodyFrame2">
          				
        		     			<form name="listForm" method="post" class="form-inline">
						  			<select id="searchSelect" class="form-control input-sm" style="height:36px; float:right;" onchange="searchList();">
						      			<option value="myApplyList">전체보기</option>
						      			<option value="myClubList">동아리만보기</option>
						      			<option value="myIndividualList">개인만 보기</option>
						  			</select>
        		     			</form> 
          				<c:if test="${dataCount!=0}">
          				
          				
            					
    					<div style="clear: both; height: 30px; line-height: 30px; margin-bottom: 10px;">
            				<div style="float: left; color:#68cabb; font-size: 13pt;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 
            					전체 ${dataCount}개 <span style="color:#777;">(${page}/${total_page}  페이지)</span> 
            					</div> 
            				<div style="float: right;">&nbsp;</div>
        				</div>
        
        				<div class="table-responsive" style="clear: both;"> <!-- 테이블 반응형 -->
            				<table class="table table-hover" style="border-bottom:3px solid #d0cccc; ">
                				<thead>
                    				<tr style="background:#eaf7f5; height:45px; color: #545252; border-top:3px solid #d0cccc; ">
                        				<th class="text-center" style="width: 70px;">번호</th>
                        				<th class="text-center" style="width: 50px;">봉사명</th>
                       				 	<th class="text-center" style="width: 70px;">봉사기간</th>
                        				<th class="text-center" style="width: 50px;">봉사장소</th>
                        				<th class="text-center" style="width: 50px;">봉사분야</th>
                        				<th class="text-center" style="width: 60px;">봉사자유형</th>
                        				<th class="text-center" style="width: 40px;">봉사 희망일</th>
                        				<th class="text-center" style="width: 50px;">신청현황</th>
                    				</tr>
                				</thead>
                				<tbody>
                				<c:forEach var="dto" items="${list}">
									<tr style="height:40px;  ">
                        				<td class="text-center" style="vertical-align: inherit;">${dto.listNum}</td>
                        				<td  style="vertical-align: inherit;"><a href="<%=cp%>/club/${club_seq}/apply/article">${dto.subject}</a></td>
                        				<td class="text-center" style="vertical-align: inherit;">${dto.startDay}~${dto.endDay}</td>
                       				 	<td class="text-center" style="vertical-align: inherit;">${dto.place}</td>
                       				 	<td class="text-center" style="vertical-align: inherit;">${dto.themenum}</td>
                       				 	<td class="text-center" style="vertical-align: inherit;">${dto.volunteer_Type}</td>
                       				 	<td class="text-center" style="vertical-align: inherit;">${dto.hopeDate}</td>                       
                       				    <c:if test="${dto.progress.equals('모집마감')}">
                       				 	<td class="text-center" style="font-weight:bold; color:white; font-size:16px; vertical-align: inherit;"> <span class="label label-default" style="padding:5px;">${dto.progress}</span></td>
                       				 	</c:if>
                       				 	<c:if test="${dto.progress.equals('모집중')}">
                       				 	<td class="text-center" style="font-weight:bold; color:white; font-size:16px; vertical-align: inherit;"><span class="label label-warning" style="padding:5px;">${dto.progress}</span></td>
                       				 	</c:if>
                    				</tr>
                    				</c:forEach>
                				</tbody>
            				</table>
        				</div>
          				</c:if>
        				<div class="paging" style="text-align: center; min-height: 50px; line-height: 50px;">
<c:if test="${dataCount==0 }">
                  등록된 게시물이 없습니다.
</c:if>
<c:if test="${dataCount!=0 }">
                ${paging}
</c:if>
        				</div>        
        
        				<div style="clear: both;">
        					<div style="float: left; width: 20%; min-width: 85px;">
        		    			<button type="button" class="btn btn-default" onclick="tabPageView();">새로고침</button>
        					</div>
        					<div style="float: left; width: 60%; text-align: center;">
        		     			<form name="searchForm" method="post" class="form-inline">
						  			<select class="form-control input-sm" name="searchKey" style="height:32px">
						      			<option value="subject">봉사명</option>
						      			<option value="place">지역</option>
						      			<option value="hopeDate">봉사희망일</option>
						  			</select>
						  			<input type="text" class="form-control input-sm input-search" name="searchValue" placeholder="검색" onkeydown="enter(search)" style="width:50%; height:32px;">
						  			<button type="button" class="btn btn-info" name="search" onclick="searchList();"><span class="glyphicon glyphicon-search"></span> 검색</button>
        		     			</form>
        					</div>
        				</div>
        				</div>
        			</div>
        		</div>
        	</div>
	</div>
 