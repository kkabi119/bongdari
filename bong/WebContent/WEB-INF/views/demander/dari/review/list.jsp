<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
   // String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>

<script type="text/javascript">
function searchList() {
		var f=document.searchForm;
		f.action="<%=cp%>/demander/${demander_seq}/review/list";
		f.submit();
}
</script>
<style type="text/css">
/* 후기게시판 리스트row */
 .row2 {
   margin-top: 20px;
   margin-bottom: 60px;
   margin-left: 20px;
   height: 280px;
   width: 100%;
} 
/* 후기게시판 타이틀 */
 .row3 {
   margin-top: 20px;
   margin-bottom: 20px;
   height: 180px;
   width: 100%;
}  
/* 후기게시판 리스트 이미지 */
.img-responsive2 {
   display: block;
   width: 90%;
   
}
.carousel2 {
   margin: 0px auto;
    width: 450px; /* clubmain을 위해 오후 3:58 2016-05-10가로크기 변경 */
    margin-top:0px;
    height: 300px;
}

.carousel2 .item,
.carousel2 .item.active,
.carousel2 .carousel-inner {
    width:100%;
    height: 100%;
}

.carousel2 .fill {
    width: 100%;
    height: 100%;
    background-position: center;
    background-size: cover; 
}


/* 후기게시판 리스트  */
.col1 {
   width: 35%;
   float: left;
   position: relative;
   min-height: 1px;
   padding-right: 15px;
   padding-left: 0px;
}

.col2 {
   width: 35%;
   float: left;
   position: relative;
   min-height: 1px;
   padding-right: 15px;
   padding-left: 0px;
}

.subStyle{
overflow:hidden;
white-space:nowrap; 
text-overflow:ellipsis;
}
</style>
   <!-- Page Heading/Breadcrumbs -->
   <div class="row3">
      <div class="col-lg-12">
         <h1 class="page-header" style="color:#F0AD4E;">
            후기 게시판 <small style="color:#A6A6A6;">봉사활동 후기를 남겨주세요.</small>
         </h1>
         <ol class="breadcrumb">
            <li><a href="<%=cp%>/demander/${demander_seq}/main" style="color:#F0AD4E;">수요처 메인</a></li>
            <li class="active">후기 게시판</li>
         </ol>
      </div>
   </div>
   <!-- /.row -->
 <c:if test="${dataCount!=0 }"> 

        <div style="clear: both; height: 30px; line-height: 30px;">
            <div style="float: left;">${dataCount}개(${page}/${total_page} 페이지)</div>
            <div style="float: right;">&nbsp;</div>
        </div>
  
   <!-- Project One -->
 <c:forEach var="dto" items="${list}"> 
   <div class="row2">
      <div class="col-md-7">
         <div class="carousel2">
            <div class="carousel-inner">
               <div class="item active">
                 <c:if test="${not empty dto.listImageName}">
                     <div class="fill" style="background-image:url('${dto.listImageName}');">
                         <%-- ${dto.listImageName}  --%> 
                      </div>   
                            <!-- style="background-image:url('/bong/uploads/image/201606081632001928924125029041.jpg'); -->   
                           
                  </c:if>
                  <c:if test="${empty dto.listImageName}">
                            <div class="fill" style="background-image:url('<%=cp%>/res/images/demander/demander2.jpg');">
                                   
                            </div>      
                </c:if>
                <%-- <div class="fill"
                     style="background-image:url('<%=cp%>/res/images/demander/demander3.jpg');"></div>
               </div> --%>
            </div>
         </div>
      </div>
      </div>
      
      <div class="col1">
   	 	<div style="margin-top:5px; max-height:60px; overflow:hidden; text-overflow:elevation; ">
        	<div style="font-weight:600; color:#757575; font-size: 14pt;line-height: 24px; max-height:37px;margin-top:5px; overflow:hidden; 
        	width:80%;white-space:nowrap; text-overflow:ellipsis;float:left; ">
         	  <span style="color:#4E4E4E; max-height:37px;">
         	  	${dto.subject}
	         </span>
	         </div><span style="color: black;line-height: 24px; font-size: 14pt; float: left;"> &nbsp;(${dto.replyCount})</span>
         </div> 
         <hr style="margin-top:10px; border:1px solid #ddd; margin-bottom:5px;">
         
      	<%-- <h3 class="subStyle" style="font-weight:600; color:#908E8A; font-size: 16pt; line-height: 6px;" >
         ${dto.subject}
      	<small style="color: black; font-size: 13pt;"> &nbsp;(${dto.replyCount})</small></h3>  --%>
      	 
         <h5>작성자 : ${dto.userName} &nbsp;&nbsp;|&nbsp;&nbsp; 조회수 : ${dto.hitCount}</h5> 
         <h5>No. ${dto.serviceReviewIdx}</h5>
         <h5><img style="width:15px; height:15px; background-size:cover;" src="<%=cp%>/res/images/myclub/heart.png" >
          &nbsp;${dto.likeCount}</h5>
         <br>
         <a class="btn btn-lg btn-default" style="color:#F0AD4E;" 
         href="${urlArticle}&num=${dto.serviceReviewIdx}">Review
         </a>
        
      </div>
      
      
   </div>
   <hr>
   </c:forEach>
    </c:if> 
   <!-- /.row -->

	<div class="paging"
		style="text-align: center; min-height: 50px; line-height: 50px;">
		<c:if test="${dataCount==0 }">
	                  등록된 게시물이 없습니다.
	</c:if>
		<c:if test="${dataCount!=0 }">
	                ${paging}
	</c:if>
</div>



<!-- 검색 -->
   <div style="clear: both;">
      <div style="float: left; width: 20%; min-width: 85px;">
         <button type="button" class="btn btn-warning"  
            onclick="javascript:location.href='<%=cp%>/demander/${demander_seq}/review/list';">새로고침</button>
      </div>
      <div style="float: left; width: 60%; text-align: center;">
         <form name="searchForm" method="post" class="form-inline">
            <select class="form-control input-sm" name="searchKey">
               <option value="subject">제목</option>
               <option value="userName">작성자</option>
               <option value="content">내용</option>
               <option value="created">등록일</option>
            </select> <input type="text" class="form-control input-sm input-search"
               name="searchValue">
            <button type="button" class="btn btn-default btn-sm wbtn" style="color:#F0AD4E;" 
               onclick="searchList();">
               <span class="glyphicon glyphicon-search"></span> 검색
            </button>
         </form>
      </div>
      <div
         style="float: left; width: 20%; min-width: 85px; text-align: right;">
         <button type="button" class="btn btn-warning" 
            onclick="javascript:location.href='<%=cp%>/demander/${demander_seq}/review/create';">
            <span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기
         </button>
      </div>
   </div>