<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<link rel="stylesheet" href="<%=cp%>/res/css/fileinput.css" type="text/css">

<script type="text/javascript" src="<%=cp%>/res/js/util.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/fileinput.js"></script>

<script type="text/javascript">
</script>

	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow">
 					<div class="bodyFrame2">
          				<h1 style="color:#5bc0de"><i class="fa fa-bell"></i>나의 봉사신청 현황</h1>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left;">1개(1/10 페이지)</div>
            				<div style="float: right;">&nbsp;</div>
        				</div>
        
        				<div class="table-responsive" style="clear: both;"> <!-- 테이블 반응형 -->
            				<table class="table table-hover">
                				<thead>
                    				<tr>
                        				<th class="text-center" style="width: 70px;">번호</th>
                        				<th class="text-left" style="width: 50px;">봉사명</th>
                        				<th class="text-center" style="width: 50px;">봉사주기</th>
                        				<th class="text-center" style="width: 50px;">봉사지역</th>
                       				 	<th class="text-center" style="width: 70px;">봉사기간</th>
                        				<th class="text-center" style="width: 50px;">봉사분야</th>
                        				<th class="text-center" style="width: 50px;">봉사자유형</th>
                        				<th class="text-center" style="width: 50px;">신청현황</th>
                    				</tr>
                				</thead>
                				<tbody>
									<tr>
                        				<td class="text-center">6</td>
                        				<td><a href="<%=cp%>/club/index/notice/article">노인봉사</a></td>
                        				<td class="text-center">정기</td>
                       				 	<td class="text-center">서울시종로구</td>
                       				 	<td class="text-center">2016-06-01</td>
                       				 	<td class="text-center">노인 복지</td>
                       				 	<td class="text-center">승인</td>
                       				 	<td class="text-center">노인 복지 동아리</td>
                    				</tr>
                				</tbody>
            				</table>
        				</div>

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
        		    			<button type="button" class="btn btn-default" onclick="javascript:location.href='<%=cp%>/bong/index/member/myPage;">새로고침</button>
        					</div>
        					<div style="float: left; width: 60%; text-align: center;">
        		     			<form name="searchForm" method="post" class="form-inline">
						  			<select class="form-control input-sm" name="searchKey" style="height:32px">
						      			<option value="subject">검색어</option>
						      			<option value="userName">지역</option>
						      			<option value="content">내용</option>
						      			<option value="created">등록일</option>
						  			</select>
						  			<input type="text" class="form-control input-sm input-search" name="searchValue" placeholder="검색" style="width:50%; height:32px;">
						  			<button type="button" class="btn btn-info" onclick="searchList();"><span class="glyphicon glyphicon-search"></span> 검색</button>
        		     			</form>
        					</div>

        				</div>
        			</div>
        		</div>
        	</div>
        </div>
	</div>
    <div class="blog-pagination">
		<ul class="pagination">
			<li><a href="#">left</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li class="active"><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">6</a></li>
			<li><a href="#">7</a></li>
			<li><a href="#">8</a></li>
			<li><a href="#">9</a></li>
			<li><a href="#">right</a></li>
		</ul>
	</div>
