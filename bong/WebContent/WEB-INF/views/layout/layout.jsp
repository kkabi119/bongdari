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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | Triangle</title>
    <!-- Custom CSS -->
    <link href="<%=cp %>/res/css/modern-business.css" rel="stylesheet">
    
    <link href="<%=cp%>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/animate.min.css" rel="stylesheet"> 
    <link href="<%=cp%>/res/css/lightbox.css" rel="stylesheet"> 
	<link href="<%=cp%>/res/css/main.css" rel="stylesheet">
	<link href="<%=cp%>/res/css/responsive.css" rel="stylesheet">

    <!--[if lt IE 9]>
	    <script src="js/html5shiv.js"></script>
	    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="<%=cp%>/res/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=cp%>/res/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=cp%>/res/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=cp%>/res/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=cp%>/res/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
	<header id="header">      
        <tiles:insertAttribute name="header"/>
    </header>
    <!--/#header-->
	<section id="page-breadcrumb">
		<div class="vertical-center sun">
			<div class="container">
				<div class="row">
					<div class="action">
						<div class="col-sm-12">
							<h1>
								<tiles:insertAttribute name="title"/>
							</h1>
							<p>Blog with right sidebar</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- title -->
   
    <section id="blog" class="padding-top">
        <div class="container">
            <div class="row">
				<tiles:insertAttribute name="body"/>
				<!-- /#body -->
    		</div>
        </div>
    </section>

    <footer id="footer">
		<tiles:insertAttribute name="footer"/>
    </footer>
    <!--/#footer-->

    <script type="text/javascript" src="<%=cp%>/res/js/jquery.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/lightbox.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/wow.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/main.js"></script>   
    
   
</body>
</html>
