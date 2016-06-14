<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>봉다리</title>
    
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" 
      integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" 
      crossorigin="anonymous">    
    <link href="<%=cp %>/res/css/modern-business2.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/lightbox.css" rel="stylesheet"> 
    <link href="<%=cp%>/res/css/animate.min.css" rel="stylesheet"> 
	<link href="<%=cp%>/res/css/main.css" rel="stylesheet">
	<link href="<%=cp%>/res/css/responsive.css" rel="stylesheet">
	
	<script type="text/javascript" src="<%=cp%>/res/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="<%=cp%>/res/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=cp%>/res/calendar/jquery.ui.datepicker-ko.js"></script>
    <!--[if lt IE 9]>
	    <script src="<%=cp%>/res/js/html5shiv.js"></script>
	    <script src="<%=cp%>/res/js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="<%=cp%>/res/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=cp%>/res/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=cp%>/res/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=cp%>/res/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=cp%>/res/images/ico/apple-touch-icon-57-precomposed.png">
<style>
.col-md-9{
	width:76%; margin-left:15px;
}
</style>
</head><!--/head-->

<body>
	<header id="header">      
        <tiles:insertAttribute name="header"/>
    </header>
    <!--/#header-->
<div id=mainContent>    
    <section id="page-breadcrumb">
        <div class="vertical-center sun">
             <div class="container">
                <div class="row">
                    <div class="action">
                            <h1 style="margin-bottom: 0px">
                            
                            <c:if test="${clubSeq!=null}">
                          	  <a id="clubname" style="float:left; padding-right: 10px" href="<%=cp%>/club/${clubSeq}/main"></a>
                          	  </c:if>
                          	  <c:if test="${demander_seq!=null}">
                          	  <a id="demanderName" style="float:left; padding-right: 10px" href="<%=cp%>/demander/${demander_seq}/main"></a>
                          	  </c:if>
                          	  <div><tiles:insertAttribute name="title"/></div>
                            </h1>
                    </div>
                </div>
            </div>
        </div>
   </section>
   <!-- title -->
   
    <section id="blog" class="padding-top">
        <div class="container">
            <div class="row">
				<tiles:insertAttribute name="left"/>
				<!-- /#left -->
				
				<div class="col-md-9 col-sm-7">
				<tiles:insertAttribute name="body"/>
				</div>
				<!-- /#body -->
    		</div>
        </div>
    </section>
	</div>
    <footer id="footer">
        <tiles:insertAttribute name="footer"/>
    </footer>
    <!--/#footer-->
	<script type="text/javascript" src="<%=cp%>/res/calendar/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/lightbox.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/wow.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/main.js"></script> 
</body>
<!-- Script to Activate the Carousel -->
    
</html>
