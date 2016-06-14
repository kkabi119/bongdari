<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>봉다리</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=cp%>/res/admin/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- MetisMenu CSS -->
    <link href="<%=cp%>/res/admin/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="<%=cp%>/res/admin/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=cp%>/res/admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="<%=cp%>/res/admin/bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=cp%>/res/admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <link rel="stylesheet" href="<%=cp%>/res/calendar/jquery-ui.min.css" type="text/css"/>
    
    
    <link href="<%=cp%>/res/css/lightbox.css" rel="stylesheet"> 
    <link href="<%=cp%>/res/css/animate.min.css" rel="stylesheet"> 
    <link href="<%=cp%>/res/css/responsive.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/font-awesome.min.css" rel="stylesheet">
    
    <!-- jQuery -->
    <script src="<%=cp%>/res/admin/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
    <script src="<%=cp%>/res/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    
    <!-- Morris Charts JavaScript -->
    <script src="<%=cp%>/res/admin/bower_components/raphael/raphael-min.js"></script>
    <script src="<%=cp%>/res/admin/bower_components/morrisjs/morris.min.js"></script>
    <script src="<%=cp%>/res/admin/js/morris-data.js"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="<%=cp%>/res/admin/dist/js/sb-admin-2.js"></script>
    
    <script type="text/javascript" src="<%=cp%>/res/js/lightbox.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/wow.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/main.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/calendar/jquery-1.12.3.min.js"></script>
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div id="wrapper">

		<!-- 네비 탑 -->
		<header id="header">
			<tiles:insertAttribute name="header" />
		</header>
		<!-- /네비 탑 -->

		<div class="container">
		<!-- 왼쪽 네비 -->
		<tiles:insertAttribute name="left" />
		<!-- /왼쪽 네비 -->

		<!-- 메인화면 -->
		<div id="page-wrapper">
				<tiles:insertAttribute name="body"/>
		</div>
		<!-- /메인화면 -->
		</div>
		
	</div>

</body>


<script type="text/javascript" src="<%=cp%>/res/calendar/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=cp%>/res/calendar/jquery.ui.datepicker-ko.js"></script>
	
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=cp%>/res/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

</html>
