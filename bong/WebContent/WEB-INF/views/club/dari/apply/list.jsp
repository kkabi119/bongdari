<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<style>
.list-page li a{
	
	border-color:#3897f0;
	color:#3897f0;
}

tr:last-of-type{

	border-bottom: 2px solid #333; 
}

tr:first-of-type{

	border-top: 2px solid #333; 
	
}

.table>thead>tr>th{
padding:10px 0 10px 0 ; 
}

.table>tbody>tr>td{

padding-top: 13px;
}

</style>
<div class="col-md-9 col-sm-7">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow" style="padding:0px;">
 					<div class="bodyFrame2">
          				<h3  style="font-size:30px;"> 봉사 신청<span style="margin-left:10px;color:gray; font-size:15px;">  봉사를 신청할 수 있는 게시판입니다</span> </h3>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left; color:#3897f0;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 전체 132개 <span style="color:#777;">(1/10 페이지)</span> </div>
            			
        				</div> 
        		<!-- <hr style="margin-bottom:10px; margin-top:0px; border:1px solid gray;"> -->
        		
        				<div class="table-responsive" style="clear: both; "> <!-- 테이블 반응형 -->
            				<table class="table table-hover" style="overflow:hidden;" >
                				<thead style="min-width:100%; font-size:15px; background-color:#DFE6E8; color:#555; ">
                    				<tr >
                        				<th class="text-center" style="width: 60px; font-weight:500;  ">번호</th>
                        				<th colspan="4" style="white-space: nowrap;  font-weight:500; ">  제목</th>
                        				<th class="text-center" style="width:105px; font-weight:500;">봉사기간</th>
                        				<th class="text-center" style="width:110px; font-weight:500;">봉사장소</th>
                        				<th class="text-center" style="width:90px;font-weight:500;">수요처</th>
                        				<th class="text-center" style="width:75px; ;font-weight:500;">인원</th>
                        				<th class="text-center" style="font-weight:500;">상태</th>
                    				</tr>
                				</thead>
                				
                				<tbody>
									<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" style=""><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다</td>
                       				 	<td class="text-center" >2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" ><a href="<%=cp%>/club/index/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다 </td>
                       				 	<td class="text-center">2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
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
    <div class="blog-pagination">
    
		<ul class="pagination">
			<li><a href="#">left</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li class="list-page" ><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">6</a></li>
			<li><a href="#">7</a></li>
			<li><a href="#">8</a></li>
			<li><a href="#">9</a></li>
			<li><a href="#">right</a></li>
		</ul>
	</div>
</div>