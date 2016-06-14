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

.labelNotice{
    display: inline;
    padding: .2em .6em .3em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    color: #f0ad4e;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: .25em;
    border-color:pink 2px;
  
    margin-right: 10px;
}
}
</style>
	
	
<script type="text/javascript">
function noticeSearchList() {
		var f=document.searchForm;
		f.action="<%=cp%>/demander/${demander_seq}/notice/list";
		f.submit();
}
</script>

	   <div class="col-lg-12">
			<h1 class="page-header" style="color:#F0AD4E;">공지사항</h1>
			<ol class="breadcrumb">
				<li><a href="<%=cp%>/demander/${demander_seq}/main" style="color:#F0AD4E;">수요처 메인</a></li>
				<li class="active">공지사항 </li>
			</ol>
		</div>
	    <div>
	        <div style="clear: both; height: 30px; line-height: 30px; color:#807F7F;">
	            <div style="float: left;">${dataCount}개(${page}/${total_page} 페이지)</div>
	            <div style="float: right;">&nbsp;</div>
	        </div>
	        
	        <div class="table-responsive" style="clear: both;"> <!-- 테이블 반응형 -->
	            <table class="table table-hover">
	                <thead style="min-width:100%; font-size:15px; background-color:#FBF7EB; color:#555;">
	                <!-- style="border-top: 2px solid black; height: 40px; font-size: 12pt;font-weight: normal;" -->
	                    <tr >
	                        <th class="text-center" style="width: 100px;">번호</th>
	                        <th class="text-center">제목</th>
	                        <th class="text-center" style="width: 100px;">글쓴이</th>
	                        <th class="text-center" style="width: 100px;">작성일</th>
	                        <th class="text-center" style="width: 70px;">조회수</th> 
	                    </tr>
	                </thead>
	                <tbody>
					  <c:forEach var="dto" items="${list}">
	                    <tr>
	                        <td class="text-center">${dto.listNum}</td>
	                        <td><a href="${urlArticle}&num=${dto.demanderNoticeIdx}">
	                        	<span class="glyphicon glyphicon-bell" style="padding:5px; color: #ef5b12;"></span>
	                        	${dto.subject}</a></td>
	                        <td class="text-center" style="font-weight: bold; color:#807F7F; ">${dto.userName}</td>
	                        <td class="text-center">${dto.created}</td>
	                        <td class="text-center">${dto.hitCount}</td> 
	                   
	                   
	                    </tr>
	                   </c:forEach>
	                </tbody>
	            </table>
	        </div>
	        

	</div>
		<div class="paging" style="text-align: center; min-height: 50px; line-height: 50px;">
			<c:if test="${dataCount==0}">
		                  등록된 게시물이 없습니다.
		</c:if>
			<c:if test="${dataCount!=0}">
		            ${paging}
		</c:if>
		</div>
	

	<!-- 검색 -->
	
		<div style="clear: both;">
		<div style="float: left; width: 20%; min-width: 85px;">
			<button type="button" class="btn btn-warning"  
				onclick="javascript:location.href='<%=cp%>/demander/${demander_seq}/notice/list';">새로고침</button>
		</div>
		<div style="float: left; width: 60%; text-align: center;">
			<form name="searchForm" method="post" class="form-inline">
				<select class="form-control input-sm" name="searchKey">
					<option value="subject">제목</option>
					<option value="userName">작성자</option>
					<option value="content">내용</option>
					<option value="created">등록일</option>
				</select> 
				<input type="text" class="form-control input-sm input-search" name="searchValue">
				<button type="button" class="btn btn-default btn-sm wbtn" style="color:#F0AD4E;" 
					onclick="noticeSearchList();">
					<span class="glyphicon glyphicon-search"></span> 검색
				</button>
			</form>
		</div>
		<div
			style="float: left; width: 20%; min-width: 85px; text-align: right;">
			<button type="button" class="btn btn-warning" 
				onclick="javascript:location.href='<%=cp%>/demander/${demander_seq}/notice/created';">
				<span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기
			</button>
		</div>
	</div>

    



