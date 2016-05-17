<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>

	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow">
 					<div class="bodyFrame2">
          				<div class="col-lg-12">
			<h1 class="page-header" style="color:#F0AD4E;">
				관심등록 동아리 
			</h1>
		</div>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left;">1개(1/10 페이지)</div>
            				<div style="float: right;">&nbsp;</div>
        				</div>
        
        				<div class="table-responsive" style="clear: both;"> <!-- 테이블 반응형 -->
            				<table class="table table-hover">
                				<thead>
                    				<tr>
                        				<th class="text-center" style="width: 70px;">번호</th>
                        				<th class="text-center">동아리 이름</th>
                        				<th class="text-center" style="width: 100px;">지역</th>
                        				<th class="text-center" style="width: 80px;">분야</th>
                       				 	<th class="text-center" style="width: 90px;">참여 횟수</th>
                        				
                    				</tr>
                				</thead>
                				<tbody>
									<tr>
                        				<td class="text-center">1</td>
                        				<td class="text-center"><a href="<%=cp%>/club/index/calendar">봉사 동아리 A</a></td>
                        				<td class="text-center">서울 북부</td>
                       				 	<td class="text-center">보육</td>
                       				 	<td class="text-center">5</td>
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
        					<div style="float: right; ">
        		    			<button type="button" class="btn btn-default" 
        		    			onclick="javascript:location.href='<%=cp%>/demander/index/bookmark';">새로고침</button>
        				
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
