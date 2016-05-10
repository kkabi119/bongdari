<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>


<div class="col-md-9 col-sm-7">
	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
                                
			<!-- Header Carousel -->
    		<header id="myCarousel" class="carousel slide">
        		<!-- Indicators -->
        			<ol class="carousel-indicators">
            			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            			<li data-target="#myCarousel" data-slide-to="1"></li>
            			<li data-target="#myCarousel" data-slide-to="2"></li>
        			</ol>

        	<!-- Wrapper for slides -->
        	<div class="carousel-inner">
           		<div class="item active">
            
            	<div class="fill" style="background-image:url('<%=cp%>/res/images/myclub/111.jpg');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            <div class="item">	
                <div class="fill" style="background-image:url('<%=cp%>/res/images/myclub/222.jpg');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            <div class="item">
                <div class="fill" style="background-image:url('<%=cp%>/res/images/myclub/333.jpg');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
          
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
        
        <br><br>
		<br><br>
     
    </header>
	<hr>
                                          
    </div>
    </div>

    </div>
    <!--  이달의 우수 후기 -->
<div class="row" style="margin-top:50px;">
<div class="col-md-4" >
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5 style="color:#687ead; font-weight: bold;"><i class="fa fa-fw fa-paperclip"></i> 이달의 후기</h5>
                    </div>
                    <div class="panel-body">
                        <ul type="disc">
                        <%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>
                        
                       		 <li><a href="#" >[충주] 따뜻했던 연탄봉억~</a></li>
                     	  	 <li><a href="#" >[서울] 벌써 보고싶은 아께</a></li>
                       		 <li><a href="#" >[부산] 집에 가고 싶은 ~</a></li>
                       		 <li><a href="#" >[강원] 저녁 뭐 먹지?</a></li>
                       		 <li><a href="#" >[전주] 힘들었던만큼날</a></li>
                        	<li><a href="#" class="btn btn-default" style="text-align: right">더보기</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4" >
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5 style="color:#687ead; font-weight: bold;"><i class="fa fa-fw fa-paperclip"></i> 이달의 봉다리</h5>
                    </div>
                    <div class="panel-body">
                        <ul type="disc">
                        <%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>
                        
                       		 <li><a href="#" >[충주] 따뜻했던 연~</a></li>
                     	  	 <li><a href="#" >[서울] 벌써 보고 함께</a></li>
                       		 <li><a href="#" >[부산] 집에 가고 람 손~</a></li>
                       		 <li><a href="#" >[강원] 저녁 뭐 먹지?</a></li>
                       		  <li><a href="#" >[전주] 힘들었던만큼  날</a></li>
                        	<li>	<a href="#" class="btn btn-default" style="text-align: right">더보기</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4" >
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5 style="color:#687ead; font-weight: bold;"><i class="fa fa-fw fa-paperclip"></i> 이달의 수요처</h5>
                    </div>
                    <div class="panel-body">
                        <ul type="disc">
                        <%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>
                        
                       		 <li><a href="#" >[충주] 따뜻했던 연탄봉사의~</a></li>
                     	  	 <li><a href="#" >[서울] 벌써 보고싶은 아께</a></li>
                       		 <li><a href="#" >[부산] 집에 가고 싶은손~</a></li>
                       		 <li><a href="#" >[강원] 저녁 뭐 지?</a></li>
                       		  <li><a href="#" >[전주] 힘들었던만큼 날</a></li>
                        	<li>	<a href="#" class="btn btn-default" style="text-align: right">더보기</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            </div>
       <!--  이달의 우수 후기 끝-->
                    <div class="blog-pagination">
                        <ul class="pagination">
                          <li><a href="#">left</a></li>
                          <li><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li class="active"><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">5</a></li>
                          <li><a href="#">6</a></li>
                          <li><a href="#">7</a></li>
                          <li><a href="#">8</a></li>
                          <li><a href="#">9</a></li>
                          <li><a href="#">right</a></li>
                        </ul>
                    </div>
                 </div>
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>
