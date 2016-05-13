<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
	String cp = request.getContextPath();
	// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
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
</style>
	<!-- Page Heading/Breadcrumbs -->
	<div class="row3">
		<div class="col-lg-12">
			<h1 class="page-header" style="color:#F0AD4E;">
				후기 게시판 <small style="color:#A6A6A6;">봉사활동 후기를 남겨주세요.</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="<%=cp%>/demander/index/main" style="color:#F0AD4E;">수요처 메인</a></li>
				<li class="active">후기 게시판</li>
			</ol>
		</div>
	</div>
	<!-- /.row -->

	<!-- Project One -->
	<div class="row2">
		<div class="col-md-7">
			<div class="carousel2">
				<div class="carousel-inner">
					<div class="item active">
						<div class="fill"
							style="background-image:url('<%=cp%>/res/images/demander/demander3.jpg');"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="col1">
			<h3>Project One</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				Laudantium veniam exercitationem expedita laborum at voluptate.
				Labore, voluptates totam at aut nemo deserunt rem magni pariatur
				quos perspiciatis atque eveniet unde.</p>
			<a class="btn btn-lg btn-default" style="color:#F0AD4E;" 
			href="<%=cp%>/demander/index/review/article">View Project
			</a>
			
		
			
		</div>
	</div>
	<!-- /.row -->

	<hr>

	<!-- Project Two -->
	<div class="row2">
		<div class="col-md-7">
			
			<div class="carousel2">
				<div class="carousel-inner">
					<div class="item active">
						<div class="fill"
							style="background-image:url('<%=cp%>/res/images/demander/demander4.jpg');"></div>

					</div>
				</div>
			</div>
		</div>
		<div class="col1">
			<h3>Project Two</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut,
				odit velit cumque vero doloremque repellendus distinctio maiores rem
				expedita a nam vitae modi quidem similique ducimus! Velit, esse
				totam tempore.</p>
			<a class="btn btn-lg btn-default" style="color:#F0AD4E;" 
			href="<%=cp%>/demander/index/review/article">View Project
			</a>
			
		</div>
	</div>
	<!-- /.row -->

	<hr>

	<!-- Project Three -->
	<div class="row2">
		<div class="col-md-7">
			
			<div class="carousel2">
				<div class="carousel-inner">
					<div class="item active">
						<div class="fill"
							style="background-image:url('<%=cp%>/res/images/demander/demander1.jpg');"></div>

					</div>
				</div>
			</div>
		</div>
		<div class="col1">
			<h3>Project Three</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				Omnis, temporibus, dolores, at, praesentium ut unde repudiandae
				voluptatum sit ab debitis suscipit fugiat natus velit excepturi amet
				commodi deleniti alias possimus!</p>
			<a class="btn btn-lg btn-default" style="color:#F0AD4E;" 
			href="<%=cp%>/demander/index/review/article">View Project
			</a>
			
		</div>
	</div>
	<!-- /.row -->

	<hr>

	<!-- Project Four -->
	<div class="row2">

		<div class="col-md-7">
			<div class="carousel2">
				<div class="carousel-inner">
					<div class="item active">
						<div class="fill"
							style="background-image:url('<%=cp%>/res/images/demander/demander2.jpg');"></div>

					</div>
				</div>
			</div>
		</div>
		<div class="col1">
			<h3>Project Four</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				Explicabo, quidem, consectetur, officia rem officiis illum aliquam
				perspiciatis aspernatur quod modi hic nemo qui soluta aut eius fugit
				quam in suscipit?</p>
			<a class="btn btn-lg btn-default" style="color:#F0AD4E;" 
			href="<%=cp%>/demander/index/review/article">View Project
			</a>
			
		</div>
	</div>
	<!-- /.row -->

	<hr>

	<!-- Pagination -->
	<div class="row text-center">
		<div class="col-lg-12">
			<ul class="pagination">
				<li><a href="#">&laquo;</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">&raquo;</a></li>
			</ul>
		</div>
	</div>
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
		<div
			style="float: left; width: 20%; min-width: 85px; text-align: right;">
			<button type="button" class="btn btn-warning" 
				onclick="javascript:location.href='<%=cp%>/demander/index/review/create';">
				<span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기
			</button>
		</div>
	</div>