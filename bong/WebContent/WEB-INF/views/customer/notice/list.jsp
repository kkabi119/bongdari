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
.list-page li a{
	
	border-color:#3897f0;
	color:#3897f0;
}

tr:last-of-type{

	border-bottom: 2px solid #807F7F; 
}

tr:first-of-type{

	border-top: 2px solid #807F7F; 
	
}

.table>thead>tr>th{
padding:10px 0 10px 0 ; 
}

.table>tbody>tr>td{

padding-top: 13px;
}

</style>
<script type="text/javascript">
function searchList() {
	var f=document.searchForm;
	f.action="<%=cp%>/notice/list";
	f.submit();
}

</script>

	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow">
 					<div class="bodyFrame2">
          				<h1 style="color:#F0AD4E;"><i class="fa fa-bell"></i>공지사항 </h1>
    		<c:if test="${dataCount!=0 }">
    					<div style="clear: both; height: 30px; line-height: 30px; color:#807F7F;">
            				<div style="float: left;">${dataCount}개(${page}/${total_page} 페이지)</div>
            				<div style="float: right;">&nbsp;</div>
        				</div>
        
        				<div class="table-responsive" style="clear: both;"> <!-- 테이블 반응형 -->
            				<table class="table table-hover">
                				<thead style="min-width:100%; font-size:15px; background-color:#FBF7EB; color:#555;">
                    				<tr>
                        				<th class="text-center" style="width: 70px;">번호</th>
                        				<th >제목</th>
                        				<th class="text-center" style="width: 100px;">글쓴이</th>
                        				<th class="text-center" style="width: 100px;">날짜</th>
                       				 	<th class="text-center" style="width: 70px;">조회수</th>
                        				<th class="text-center" style="width: 50px;">첨부</th>
                    				</tr>
                				</thead>
                				<tbody>
                		<c:forEach var="dto" items="${list}">
									<tr>
                        				<td class="text-center">${dto.listNum}</td>
                        				<td><a href="${urlArticle}&num=${dto.noticeIdx}">${dto.subject}</a></td>
                        				<td class="text-center" style="font-weight: bold; color:#807F7F;">${dto.userName }</td>
                       				 	<td class="text-center">${dto.created }</td>
                       				 	<td class="text-center">${dto.hitCount }</td>
                       				 	<td class="text-center">
<c:if test="${not empty dto.saveFilename}">
                                <a href="<%=cp%>/notice/download?num=${dto.noticeIdx}" class="fa fa-download"></a>
</c:if>
                        				</td>
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
        		    			<button type="button" class="btn btn-warning" onclick="javascript:location.href='<%=cp%>/notice/list';">새로고침</button>
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
						  			<button type="button" class="btn btn-default btn-sm wbtn" style="color:#F0AD4E;" onclick="searchList();"><span class="glyphicon glyphicon-search"></span> 검색</button>
        		     			</form>
        					</div>
        					<div style="float: left; width: 20%; min-width: 85px; text-align: right;">
        					<c:if test="${sessionScope.member.userId=='admin'}">        				
        		    			<button type="button" class="btn btn-warning" onclick="javascript:location.href='<%=cp%>/notice/created';"><span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기</button>
        		    		</c:if>
        					</div>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>
	</div>
