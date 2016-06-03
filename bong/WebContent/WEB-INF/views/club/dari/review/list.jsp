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
		f.action="<%=cp%>/demander/index/review/list";
		f.submit();
}
</script>
<style type="text/css">

.col-md-9{
	margin-top:-30px; width:7%;

}
.col-md-7{

	width: 46%;
}
/* 후기게시판 리스트row */
 .row2 {
   margin-top: 10px;
   margin-bottom: 35px;
   margin-left: 20px;
   height: 225px;
   width: 100%;
} 
/* 후기게시판 타이틀 */
 .row3 {
   margin-top: 5px;
 
   height: 100px;
   width: 100%;
}  

.page-header{

	margin:0px;
}

.breadcrumb{
	margin-bottom:0px;
}

/* 후기게시판 리스트 이미지 */
.img-responsive2 {
   display: block;
   width: 90%;
   
}
.carousel2 {
   
    width: 360px; /* clubmain을 위해 오후 3:58 2016-05-10가로크기 변경 */
    margin-top:0px;
    height: 250px;
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
   width: 50%;
   float: left;
   position: relative;
   min-height: 1px;
   padding-right: 15px;
   padding-left: 0px;
   margin-top:10px;
}

.col2 {
   
   float: left;
   position: relative;
   min-height: 380px;
   padding-right: 15px;
   padding-left: 0px;
}

.btn {
	padding: 10px 15px;
}

hr:LAST-CHILD {
	        	 margin:0px; width:98%; border-top:2px solid #DADADA;
	
}

</style>
   <!-- Page Heading/Breadcrumbs -->
   <div class="row3"  style="margin-left:15px;">
      <div class="col-lg-12">
            <h3  style="font-size:30px;"> 봉사 후기<span style="margin-left:10px;color:gray; font-size:15px;">  우리의 봉사후기를 나누어 보아요~</span> </h3>

         <ol class="breadcrumb">
            <li><a href="<%=cp%>/demander/index/main" style="color:gray; font-weight: bold;">우리 동아리</a></li>
            <li class="active">후기 게시판 </li>
         </ol>
      </div>
   </div>
   <!-- /.row -->
 <c:if test="${dataCount!=0 }"> 
			<div style="clear: both; height: 30px; line-height: 30px; margin-left:35px; margin-top:20px ;">
            				<div style="float: left; color:#00aeef;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 
            					전체 ${dataCount}개 <span style="color:#777;">(${page}/${total_page}  페이지)</span> </div>
        	  <hr style="margin:0px; width:98%; border-top:2px solid #DADADA;">
        	</div> 
  
   <!-- Project One -->
    
   
 <c:forEach var="dto" items="${list}"> 
   <div class="row2" >
      <div class="col-md-7" >
         <div class="carousel2">
            <div class="carousel-inner">
               <div class="item active">
                  <div class="fill"
                     style="background-image:url('<%=cp%>/res/images/mainslide/12345.jpg');"></div>
               </div>
            </div>
         </div>
      </div>
      
      <div class="col1">
        <div style="margin-top:5px; max-height:45px; overflow:hidden; text-overflow:elevation; ">
        	<span style="font-weight:600; color:#757575; font-size: 14pt;line-height: 24px; max-height:37px;margin-top:5px; overflow:hidden; width:95% ">
         	  <a href="${urlArticle}&num=${dto.clubReviewIdx}" style="color:#4E4E4E; max-height:37px; overflow:hidden; ">
         	  	${dto.subject}
	         </a>
	         </span><span style="color: black; font-size: 14pt;"> &nbsp;(${dto.replyCount})</span>
         
         </div> <hr style="margin-top:10px; border:1px solid #ddd; margin-bottom:5px;">
         <h5 style="font-size:14px;margin-top:10px;line-height: 14px;"> 
         		작성자 <span style="font-weight: 600; color:#757575;"> ${dto.userName}</span> 
         		&nbsp;&nbsp;|&nbsp;&nbsp; 조회수  ${dto.hitCount} &nbsp;&nbsp;|&nbsp;&nbsp;  작성일 &nbsp; ${dto.created}&nbsp;&nbsp;|&nbsp;&nbsp;No. ${dto.listNum}   </h5>
         
         <h5 style="font-size:14px;"><img Id="listBtn"style=" width:15px; height:15px; background-size:cover; "src="<%=cp%>/res/images/myclub/heart.png" alt="">
	                                	 	&nbsp; ${dto.likeCount}</h5> 
         
         <div style="block; margin-top:28px; width:93%;  height:50px;overflow:hidden; text-overflow:elevation; color:gray; ">
	      <div style="font-size:15px !important; line-height: 20px ; font-weight: 400  ! important; 
	      				 font-family: 'NanumGothic', '나눔고딕',' Malgun Gothic', 'sans-serif'  ! important;  ! important">
	  			  <%--  ${dto.content} --%>
	     
		    </div><span>...</span> 
		 
        </div>
      </div>
      
        
   </div>

    <hr style="margin-left:35px; margin-top:0px; margin-bottom:-1px; width:98%; border-top:1px solid #DADADA;">
  
   </c:forEach>
    </c:if> 
      <hr style="margin-left:35px; margin-top:-2px; margin-bottom:-1px; width:98%; border-top:2px solid #DADADA;">
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
   <div style="clear: both; border-radius:0px;">
      <div style="float: left; width: 20%; min-width: 85px;">
         <button type="button" class="btn btn-default " style=" color: gray; border:1px solid gray; margin-left:30px;" 
            onclick="javascript:location.href='<%=cp%>/demander/index/review/list';">새로고침</button>
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
      <div
         style="float: left; width: 20%; min-width: 85px; text-align: right;">
         <button type="button" class="btn btn-warning" style="background-color: #00aeef; border:none;"
            onclick="javascript:location.href='<%=cp%>/club/index/review/create';">
            <span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기
         </button>
      </div>
   </div>