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

.btn {
	padding: 10px 15px;
}
.col-md-9{
	margin-top:-30px;

}
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
vertical-align:inherit;
}
a{
	color:#4a4a4a;
}

.pagination{
	margin-left:130px;
}
</style>
	
<script type="text/javascript">

function searchList() {
	var f=document.searchForm;
	f.action="<%=cp%>/club/${club_seq}/apply/list";
	f.submit();
}

</script>	
	<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow" style="padding:0px;">
 					<div class="bodyFrame2">
          				<h3  style="font-size:30px;"> 봉사 신청<span style="margin-left:10px;color:gray; font-size:15px;">  봉사를 신청할 수 있는 게시판입니다</span> </h3>
 <c:if test="${dataCount!=0 }">    					
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left; color:#00aeef;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 
            					전체 ${dataCount}개 <span style="color:#777;">(${page}/${total_page}  페이지)</span> </div>
        				</div> 
        		<!-- <hr style="margin-bottom:10px; margin-top:0px; border:1px solid gray;"> -->
        		
        				<div class="table-responsive" style="clear: both; ">
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
 <!--  리스트 시작 -->
<c:forEach var="dto" items="${list}">
									<tr height="20">
                        				<td class="text-center">${dto.listNum}</td>
                        				<td colspan="4">
                        					<a style="font-weight:bold; font-size:14px; "href="${urlArticle}&clubApplyIdx=${dto.clubApplyIdx}&volunIdx=${dto.volunIdx}">
                        							 ${dto.subject} 
                        						    <span id="replyCountView" class="item-title" style='color:#f0ad4e; font-size:12px; font-weight: bold;'> (${dto.replyCount}) </span>
                        					</a>
                        				</td>
                        				<td class="text-center">${dto.startDay } ~ ${dto.endDay }</td>
                        				<td class="text-center"  style="overflow:hidden">${dto.place } </td>
                        				<td class="text-center"><a href="#" style="font-weight:bold; font-size:14px; ;"  >${dto.serviceName }</a></td>
                       				 	<td class="text-center" style=""><span style="font-weight:bold;">${dto.applyNum}명</span>/${dto.maxNum}명</td>
                       				 	<c:if test="${dto.progress.equals('모집마감')}">
                       				 		<td class="text-center" style="font-weight:bold; color:white; font-size:16px;"> <span class="label label-default" style="padding:5px;">${dto.progress}</span></td>
                       				 	</c:if>
                       				 	<c:if test="${dto.progress.equals('모집중')}">
                       				 		<td class="text-center" style="font-weight:bold; color:white; font-size:16px;"><span class="label label-warning" style="padding:5px;">${dto.progress}</span></td>
                       				 	</c:if>
                    				</tr>
 </c:forEach><!-- 리스트끝 -->
								<%-- 	<tr>
                        				<td class="text-center">1</td>
                        				<td colspan="4" style=""><a href="<%=cp%>/club/${club_seq}/notice/article"></a>노원 동네주민 축제의 안전요원을 모집합니다</td>
                       				 	<td class="text-center" >2016-10-10 ~ 2016-10-13</td>
                       				 	<td class="text-center">중랑구 산천역</td>
                       				 	<td class="text-center" style=""><a href="#">희망복지관</a></td>
                        				<td class="text-center" style="">15/10명</td>
                        				<td class="text-center" style="font-weight:bold; color:#E0844F; ">[ 모집중 ]</td>
            						</tr>
            						
            						 --%>
                				</tbody>
            				</table>
        				</div>
  </c:if>


<!-- 검색 -->
   <div style="clear: both; border-radius:0px;">
      <div style="float: left; width: 20%; min-width: 85px;">
         <button type="button" class="btn btn-default " style=" color: gray; border:1px solid gray; margin-left:10px;" 
            onclick="javascript:location.href='<%=cp%>/demander/${clubSeq}/review/list';">
           <img Id="listBtn"style="width:18px; height:18px; background-size:cover; "src="<%=cp%>/res/images/myclub/refresh.png" alt="">&nbsp;새로고침</button>
      </div>
      <div style="float: left; width: 60%; height:41px; text-align: center; margin-top:0px; padding-top:0px;">
         <form name="searchForm" method="post" class="form-inline" style=" ">
            <select class="form-control input-sm" name="searchKey" style="height:40px; margin-top:-2px; border:1px solid #00aeef; border-radius:0px;">
               <option value="subject">제목</option>
               <option value="userName">작성자</option>
               <option value="content">내용</option>
               <option value="created">등록일</option>
            </select> 
            <input type="text" class="search-form" style="margin-top:0px; margin-left:-5px; border-radius:0px; "
               name="searchValue">
            <button type="button" class="btn btn-warning btn-sm wbtn" style="background-color: #00aeef; border:2px solid #00aeef; margin-top:-2px; "
               onclick="searchList();">
               <span class="glyphicon glyphicon-search"></span> 
            </button>
         </form>
      </div>
      <div style="float: left; width: 20%; min-width: 85px; text-align: right;">
         <button type="button" class="btn btn-warning" style="background-color: #00aeef; border:none;"
            onclick="javascript:location.href='<%=cp%>/club/${clubSeq}/review/create';">
            <span class="glyphicon glyphicon glyphicon-pencil"></span>
         </button>
      </div>
   </div>
<!------------------------------------------------ paging 처리  ----------------------------------------------------------->
<div class="paging" style="text-align:; min-height: 30px; line-height: 30px;">
<c:if test="${dataCount==0 }">
                  등록된 게시물이 없습니다.
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
    