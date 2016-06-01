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
function QnasearchList() {
		var f=document.searchForm;
		f.action="<%=cp%>/demander/index/qna/list";
		f.submit();
}
</script>

	   <div class="col-lg-12">
			<h1 class="page-header" style="color:#F0AD4E;">QnA</h1>
			<ol class="breadcrumb">
				<li><a href="<%=cp%>/demander/index/main" style="color:#F0AD4E;">수요처 메인</a></li>
				<li class="active">질문과 답변 </li>
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
	                        <td style="text-align:left;">
	                   			<c:choose>
	                   				 <c:when test="${dto.userId!=sessionScope.member.userId&&sessionScope.member.userId!='admin'&&dto.quserIdx!=sessionScope.member.userIdx}">
	                   					<i class="glyphicon glyphicon-lock" style="color:#F0AD4E;"></i>&nbsp;&nbsp;<span style="color: #807F7F;">비밀글 입니다.</span>
	                   				</c:when>
	                   				<c:otherwise>
	                   					<c:if test="${dto.answer!=0}">
	                   						&nbsp;&nbsp;<img src="<%=cp%>/res/images/demander/re.gif">
	                   					</c:if>
	                   					<a href='${urlArticle}&num=${dto.sqnaIdx}'>&nbsp;&nbsp;${dto.subject}</a>
	                   				</c:otherwise> 
	                   			</c:choose>
	                        </td>
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
				onclick="javascript:location.href='<%=cp%>/bbs/list';">새로고침</button>
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
					onclick="QnasearchList();">
					<span class="glyphicon glyphicon-search"></span> 검색
				</button>
			</form>
		</div>
		<div
			style="float: left; width: 20%; min-width: 85px; text-align: right;">
			<button type="button" class="btn btn-warning" 
				onclick="javascript:location.href='<%=cp%>/demander/index/qna/create';">
				<span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기
			</button>
		</div>
	</div>

    