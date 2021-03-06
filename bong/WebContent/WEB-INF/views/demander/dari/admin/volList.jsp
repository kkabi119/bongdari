<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<style type="text/css">

.col-md-9{
	margin-top:-15px;
}
/* 후기게시판 타이틀 */
 .row3 {
	margin-top: 0px;
	margin-bottom: 0px; 
	margin-left:30px;
 	height: 40px; 
	width: 100%;
}  
/* 후기게시판 리스트row */
 .row2 {
/* 	margin-top: 20px;
 	margin-bottom: 60px;*/
	margin-left: 30px;
	height: 210px;
	width: 100%;
} 
/* 후기게시판 리스트 이미지 */
.img-responsive2 {
	display: block;
	width: 90%;
	
}
.carousel2 {
	
  /*   width: 230px;  *//* clubmain을 위해 오후 3:58 2016-05-10가로크기 변경 */
    margin-top:0px;
    height: 229px;
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
	width: 66%;
	height:97%;
	float: left;
	position: relative;
	padding:20px;
	padding-bottom:0px;
}

.col2 {
	width: 35%;
	float: left;
	position: relative;
	min-height: 1px;
	padding-right: 15px;
	padding-left: 0px;
}
.btnList{
	clear:both; margin-right:15px; margin-top:15px;float:right;
}
.h1, .h2, .h3, h1, h2, h3{
	margin-top:10px; 
}
.h4, .h5, .h6, h4, h5, h6{
	margin-top:0px;
}

.pagination li a:hover, .pagination .active a, .pagination a:active, .pagination .active a:hover, .pagination .active a:focus, .pagination a:focus
{
	border-color:#f0ad4e;
	color:#f0ad4e;
}

</style>
<script type="text/javascript">

function listButton(volunIdx){
		url="<%=cp%>/demander/${demander_seq}/admin/tab1/showList";
		$.post(url, {volunIdx:volunIdx}, function(data){
			$("#showList_"+volunIdx).html(data);
		});	
		if($("#showList_"+volunIdx).is(':visible')) {
			if($("#showList_"+volunIdx).val()=="0"){
				$("#showList_"+volunIdx).hide("fast");
				$("#showList_"+volunIdx).val("0");
			} else {
				$("#showList_"+volunIdx).val("0");
			}
		} else {
			$("#showList_"+volunIdx).show("fast");
			$("#showList_"+volunIdx).val("0");
		}
}

function approvalButton(volunIdx){	
		url="<%=cp%>/demander/${demander_seq}/admin/tab1/eval";
		$.post(url, {volunIdx:volunIdx}, function(data){ 	
			$("#showList_"+volunIdx).html(data);
		});	
		if($("#showList_"+volunIdx).is(':visible')) {
			if($("#showList_"+volunIdx).val()=='1'){
				$("#showList_"+volunIdx).hide("fast");
				$("#showList_"+volunIdx).val("1");
			} else {
				$("#showList_"+volunIdx).val("1");
			}
		} else {
			$("#showList_"+volunIdx).show("fast");
			$("#showList_"+volunIdx).val("1");
		}
}

</script>
	<!-- Page Heading/Breadcrumbs -->
	<!-- <div class="row3">
		<div class="col-lg-12">
			<h3  style="color:#777; font-size:30px; margin-bottom:6px;"> 활동 내역<span style="margin-left:10px;color:gray; font-size:15px;"> 나눔복지관의 활동내역을 볼 수 있습니다</span> </h3>
		
		<hr style="margin-bottom:0px; margin-top:0px; border:1px solid #ec971f;">	
		
		</div>
	</div> -->
	<!-- /타이틀 -->

	<!-- Project One -->
		<!-- 사진 -->
	<c:forEach var="dto" items="${list}">
	<div class="row2" style="">
		<div class="col-md-4">
			<div class="carousel2">
				<div class="carousel-inner">
					<div class="item active">
						<div class="fill"
							style="background-image:url('<%=cp%>/uploads/volunbbs/${dto.saveFilename}');"></div>
					</div>
				</div>
			</div>
		</div>
			<!-- 글 -->
		<div class="col1">
			<div style="height:80%; ">
				<h4><span style="font-weight:normal; ;border-radius:100px; border:1px solid orange; background-color:#FFF; color:orange;"class="label label-default">
						${dto.progress}</span>
				</h4>
				<h3 style="margin-top:15px">${dto.subject}  </h3>
				<div style=" height:88%;  overflow:hidden ;">
					 <div class="table-responsive" style="margin-top:6px; font-size:13px;">
						
						 <div style="margin-bottom:6px; ">
	                        	<span style="border: none; text-align: left; padding-right:0px; ">
	                        	 	<span style="font-size:13px;font-weight:normal; background:none; color:gray;"class="label label-default">
	                        	 			봉사일 |
	                        	 	</span>
	                        	</span>
	                            <span  style="border: none; text-align: left; width:40%;">${dto.startDay} ~ ${dto.endDay}</span>
	                            
	                         	<span style="border: none; text-align: left;  padding-left:16px;">
	                        	 	<span style="font-size:13px; font-weight:normal; background:none; color:gray;"class="label label-default">
	                        	 			분야 |
	                        	 	</span>
	                        	</span>
	                            <span style="border: none;  width:100%; text-align: left;padding-left:-30px; ">문화체육 > 행사보조</span>
	                        
	                        </div>
	                        <div style="margin-bottom:6px; ">
	                             <span style="border: none; text-align: left; width:6%;  ">
	                        	 	<span style="font-size:13px; font-weight:normal; margin-bottom:5px; background:none; color:gray;"class="label label-default">
	                        	 			모집일 |
	                        	 	</span>
	                        	</span>
	                            <span style=" border: none; text-align: left; width:35%; height:45px; ">2016-03-22 ~ 2016-03-25</span>
	                            
	                          <span style="border: none; text-align: left; width:6%;padding-left:16px; ">
	                        	 	<span style="font-size:13px; font-weight:normal; margin-bottom:5px;background:none; color:gray;"class="label label-default">
											봉사자 |          	 			
	                        	 	</span>
	                        	</span>
	                            <span style="border: none;  text-align: left; ">7명</span>
	                        </div>
	                        
						<div >
	                             <span style="border: none; text-align: left; width:6%;  ">
	                        	 	<span style="font-size:13px; font-weight:normal; margin-bottom:5px; background:none; color:gray;"class="label label-default">
	                        	 			장소 |
	                        	 	</span>
	                        	</span>
	                            <span style=" border: none; text-align: left; width:35%; ; ">${dto.place} <a href="#" > [지도]</a> </span>
	                            
	                    <!--       <span style="border: none; text-align: left; width:6%;padding-left:16px; ">
	                        	 	<span style="font-size:13px;  font-weight:normal; margin-bottom:5px;background:none; color:gray;"class="label label-default">
											신청자 |          	 			
	                        	 	</span>
	                        	</span>
	                            <span style="border: none;  text-align: left; ">3봉 10명</span> -->
	                        </div>
						</div>
				</div>
		
			</div>
			<div class="btnList" >
				
				
				<button id="listBtn" onclick="listButton(${dto.volunIdx});" class="btn btn btn-default" style="margin-left:10px; color:gray;" >
					<span class="glyphicon glyphicon-user " aria-hidden="true"></span> 참여자
				</button>
				
				
			</div>
			
		</div>
		
	</div>
	<!-- /.row -->
	<div>
		<hr style="margin-left:43px; margin-top:0px; margin-bottom:-1px; width:98%; border-top:1px solid #DADADA;">
	</div>
	
	<div id="showList_${dto.volunIdx}" style="width:100%; margin-left:45px; padding:25px; padding-left:30px; padding-bottom:65px;" hidden="hidden">
			
	</div>
	
	</c:forEach>
	
	<!-- /.row -->
	
	<!-- Pagination -->
	${paging}
	<!-- /.row -->

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
				</select> <input type="text" class="form-control input-sm input-search"
					name="searchValue">
				<button type="button" class="btn btn-default btn-sm wbtn" style="color:#F0AD4E;" 
					onclick="searchList();">
					<span class="glyphicon glyphicon-search"></span> 검색
				</button>
			</form>
		</div>
		
	</div>