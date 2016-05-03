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
    <title>404 Error | Triangle</title>
    <link href="<%=cp%>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/font-awesome.min.css" rel="stylesheet"> 
    <link href="<%=cp%>/res/css/main.css" rel="stylesheet">
    <link href="<%=cp%>/res/css/responsive.css" rel="stylesheet">

    <!--[if lt IE 9]>
        <script src="<%=cp%>/res/js/html5shiv.js"></script>
        <script src="<%=cp%>/res/js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="<%=cp%>/res/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=cp%>/res/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=cp%>/res/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=cp%>/res/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=cp%>/res/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
    <section id="error-page">
        <div class="error-page-inner">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="text-center">
                            <div class="bg-404">
                                <div class="error-image">                                
                                    <img class="img-responsive" src="<%=cp%>/res/images/404.png" alt="">  
                                </div>
                            </div>
                            <h2>PAGE NOT FOUND</h2>
                            <p>The page you are looking for might have been removed, had its name changed.</p>
                            <a href="index.html" class="btn btn-error">RETURN TO THE HOMEPAGE</a>
                            <div class="social-link">
                                <span><a href="#"><i class="fa fa-facebook"></i></a></span>
                                <span><a href="#"><i class="fa fa-twitter"></i></a></span>
                                <span><a href="#"><i class="fa fa-google-plus"></i></a></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    

    <script type="text/javascript" src="<%=cp%>/res/js/jquery.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/wow.min.js"></script>
    <script type="text/javascript" src="<%=cp%>/res/js/main.js"></script>
</body>
</html>