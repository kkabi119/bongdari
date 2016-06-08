<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
   String cp=request.getContextPath();
%>
<style type="text/css">
.fixed {
    position: fixed;
    top:0; left:0;
    width: 100%;
    background-color: white; 
    z-index: 100;
    border-top: 0;
    }

</style>
<script type="text/javascript">
$(document).ready(function($) {
	  
    var navbar = $('#header'),
    		distance = navbar.offset().top,
        $window = $(window);

    $window.scroll(function() {
        if ($window.scrollTop() >= distance) {
            navbar.removeClass('fixed').addClass('fixed');
          	$("#mainContent").css("padding-top", "145px");
        } else {
            navbar.removeClass('navbar-fixed-top');
            $("#mainContent").css("padding-top", "0px");
        }
    });
});
</script>
	<div class="container">
            <div class="row">
                <div class="col-sm-12 overflow">
                   <div class="social-icons pull-right">
                        <ul class="nav nav-pills">
                            <c:if test="${sessionScope.member.isService==0}">
                           <span style="color:blue;">${sessionScope.member.userName}</span>회원님 <i></i>
                           <li><a href="<%=cp%>/member/logout"><i class="fa fa-sign-out"> Logout</i></a></li>
                            </c:if>
                            <c:if test="${sessionScope.member.isService==1}">
                           <span style="color:blue;">${sessionScope.member.userName}</span>수요처님 <i></i>
                           <li><a href="<%=cp%>/member/logout"><i class="fa fa-sign-out"> Logout</i></a></li>
                            </c:if>
                            <c:if test="${empty sessionScope.member && empty sessionScope.demanderjoin}">
                            <li><a href="<%=cp%>/member/login"><i class="fa fa-sign-in"> Login</i></a></li>
                            </c:if>
                            <c:if test="${sessionScope.member.isService==0}">
                            <li><a href="<%=cp%>/member/index/myPage"><i class="glyphicon glyphicon-user">MyPage</i></a></li>
                            </c:if>
                            <c:if test="${sessionScope.member.isService==1}">
                            <li><a href="<%=cp%>/demander/${sessionScope.member.demander_seq}/main"><i class="glyphicon glyphicon-user">수요처페이지</i></a></li>
                            </c:if>
                            
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

                    <a class="navbar-brand" href="<%=cp%>/">
                       <h1><img src="<%=cp%>/res/images/logo.png" alt="logo"></h1>
                    </a>
                    
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="<%=cp%>/">Home</a></li>
                        <li class="dropdown"><a href="<%=cp%>/main/club">동아리 <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                    <c:if test="${sessionScope.member.isService==0}">    
                            <c:if test="${sessionScope.member.clubIdx==0}">
                                <li><a href="<%=cp%>/club/me">동아리 개설하기</a></li>
                            </c:if>
                            <c:if test="${sessionScope.member.clubIdx>0}">
                                <li><a href="<%=cp%>/club/${sessionScope.member.clubIdx}/main">내가 개설한 동아리</a></li>
                            </c:if>
                                <li><a href="<%=cp%>/club/index/main">클럽1</a></li>
                                <li><a href="<%=cp%>/main/club2">클럽2</a></li>
                    </c:if>
                                <li><a href="<%=cp%>/main/searchClub">동아리 검색하기</a></li>
                                
                            </ul>
                        </li>                    
                        <li class="dropdown"><a href="">수요처 <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="<%=cp%>/main/demander">수요처 전체 메인</a></li>
                                <li><a href="<%=cp%>/main/searchDemander">수요처 검색</a></li>
                            </ul>
                        </li>
                        
                        <li class="dropdown"><a href="">고객센터 <i class="fa fa-angle-down"></i></a>
                            <ul role="menu" class="sub-menu">
                                <li><a href="<%=cp%>/notice/list">공지사항</a></li>
                                <li><a href="<%=cp%>/qna/list">질문하기</a></li>                 
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