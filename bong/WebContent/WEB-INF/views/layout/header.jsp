<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
	<header id="header">      
        <div class="container">
            <div class="row">
                <div class="col-sm-12 overflow">
                   <div class="social-icons pull-right">
                        <ul class="nav nav-pills">
                            <c:if test="${not empty sessionScope.member}">
                        	<span style="color:blue;">${sessionScope.member.userName}</span>님 <i></i>
                        	<li><a href="<%=cp%>/member/logout"><i class="fa fa-sign-out"> Logout</i></a></li>
                            </c:if>
                            <c:if test="${empty sessionScope.member}">
                            <li><a href="<%=cp%>/member/login"><i class="fa fa-sign-in"> Login</i></a></li>
                            </c:if><li><a href="<%=cp%>/member/myPage"><i class="glyphicon glyphicon-user">MyPage</i></a></li>
                            <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                            <li><a href=""><i class="fa fa-dribbble">My Club</i></a></li>
                            <c:if test="${sessionScope.member.userId=='admin'}">
                            <li><a href="<%=cp%>/admin"><i class="glyphicon glyphicon-cog">Admin</i></a></li>
                            </c:if>
                        </ul>
                    </div> 
                </div>
             </div>
        </div>
        <div class="navbar navbar-inverse" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a class="navbar-brand" href="<%=cp%>/main">
                    	<h1><img src="<%=cp%>/res/images/logo.png" alt="logo"></h1>
                    </a>
                    
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="<%=cp%>/main">Home</a></li>
                        <li class="dropdown"><a href="#">동아리 <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="<%=cp%>/main/createClub">동아리 개설</a></li>
                                <li><a href="<%=cp%>/main/club">전체 동아리 메인</a></li>
                                <li><a href="<%=cp%>/main/searchClub">동아리 검색하기</a></li>
                                
                            </ul>
                        </li>                    
                        <li class="dropdown"><a href="blog.html">수요처 <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="<%=cp%>/main/demander">수요처 전체 메인</a></li>
                                <li><a href="<%=cp%>/main/searchDemander">수요처 검색</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="portfolio.html">Portfolio <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="portfolio.html">Portfolio Default</a></li>
                                <li><a href="portfoliofour.html">Isotope 3 Columns + Right Sidebar</a></li>
                                <li><a href="portfolioone.html">3 Columns + Right Sidebar</a></li>
                                <li><a href="portfoliotwo.html">3 Columns + Left Sidebar</a></li>
                                <li><a href="portfoliothree.html">2 Columns</a></li>
                                <li><a href="portfolio-details.html">Portfolio Details</a></li>
                            </ul>
                        </li>                         
                        <li><a href="shortcodes.html ">Shortcodes</a></li>                    
                    </ul>
                </div>
                <div class="search">
                    <form role="form">
                        <i class="fa fa-search"></i>
                        <div class="field-toggle">
                            <input type="text" class="search-form" autocomplete="off" placeholder="Search">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </header>
    <!--/#header-->
