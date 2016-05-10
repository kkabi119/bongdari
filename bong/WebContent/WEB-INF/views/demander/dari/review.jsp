<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
	String cp = request.getContextPath();
	// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<style type="text/css">
.row2{
margin-top:30px;
margin-bottom:30px;
height: 250px;}
.row3{
margin-top:30px;
margin-bottom:30px;
height: 200px;}

</style>

<div class="container" style="width: 63%;">

	<!-- Page Heading/Breadcrumbs -->
	<div class="row3"> 
		<div class="col-lg-12" >
			<h1 class="page-header">
				후기 게시판 <small>봉사활동 후기를 남겨주세요.</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="<%=cp%>/demander/index/main">수요처 메인</a></li>
				<li class="active">후기 게시판</li>
			</ol>
		</div>
	 </div> 
	<!-- /.row -->

	<!-- Project One -->
	<div class="row2">
		<div class="col-md-7">
			<a href="portfolio-item.html"> <img
				class="img-responsive img-hover" src="http://placehold.it/700x300"
				alt="">
			</a>
		</div>
		<div class="col-md-5">
			<h3>Project One</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				Laudantium veniam exercitationem expedita laborum at voluptate.
				Labore, voluptates totam at aut nemo deserunt rem magni pariatur
				quos perspiciatis atque eveniet unde.</p>
			<a class="btn btn-primary" href="portfolio-item.html">View
				Project</i>
			</a>
		</div>
	 </div> 
	<!-- /.row -->

	<hr>
	
	<!-- Project Two -->
	<div class="row2">
		<div class="col-md-7">
			<a href="portfolio-item.html"> <img
				class="img-responsive img-hover" src="http://placehold.it/700x300"
				alt="">
			</a>
		</div>
		<div class="col-md-5">
			<h3>Project Two</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut,
				odit velit cumque vero doloremque repellendus distinctio maiores rem
				expedita a nam vitae modi quidem similique ducimus! Velit, esse
				totam tempore.</p>
			<a class="btn btn-primary" href="portfolio-item.html">View
				Project</i>
			</a>
		</div>
	</div> 
	<!-- /.row -->

	<hr>

	<!-- Project Three -->
	 <div class="row2"> 
		<div class="col-md-7">
			<a href="portfolio-item.html"> <img
				class="img-responsive img-hover" src="http://placehold.it/700x300"
				alt="">
			</a>
		</div>
		<div class="col-md-5">
			<h3>Project Three</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				Omnis, temporibus, dolores, at, praesentium ut unde repudiandae
				voluptatum sit ab debitis suscipit fugiat natus velit excepturi amet
				commodi deleniti alias possimus!</p>
			<a class="btn btn-primary" href="portfolio-item.html">View
				Project</i>
			</a>
		</div>
	</div> 
	<!-- /.row -->

	<hr>

	<!-- Project Four -->
	 <div class="row2"> 

		<div class="col-md-7">
			<a href="portfolio-item.html"> <img
				class="img-responsive img-hover" src="http://placehold.it/700x300"
				alt="">
			</a>
		</div>
		<div class="col-md-5">
			<h3>Project Four</h3>
			<h4>Subheading</h4>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
				Explicabo, quidem, consectetur, officia rem officiis illum aliquam
				perspiciatis aspernatur quod modi hic nemo qui soluta aut eius fugit
				quam in suscipit?</p>
			<a class="btn btn-primary" href="portfolio-item.html">View
				Project</i>
			</a>
		</div>
	 </div> 
	<!-- /.row -->

	<hr>


</div>
<!--/#blog-->
