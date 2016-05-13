<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<style>
	li{
		margin-top:3px;
	}
	li a{
		color:#525252;
		font-weight:500;
		font-size:13px;
	}
	a:hover{
		
		color:#3897f0;
		font-weight:bold;
	}
	hr{
		border-top:1px solid #DCDCDC;
	}
	
	.panel-default>.panel-heading{
	
		background-color:#3897f0;
	}
</style>

    <!-- Header Carousel -->
    <!--  우수단원 소개란 시작 -->
	 <h1 style="margin-top:30px" class="title text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay="300ms">오월의 베스트 포토 </h1>
      <h1 style="font-weight:bold; margin-top:-6px;" class="title text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay="300ms">따뜻한 마음을 그리다</h1>
      <p style="color:gray; font-size:16px; margin-bottom:25px;" class="text-center wow fadeInDown" data-wow-duration="400ms" data-wow-delay="400ms"> 
      			이번 달을 빛낸 이달의 우수포토를 소개합니다! 댓글과 하트로 칭찬해주세용</p>
              
    <header id="myCarousel" class="carousel slide" style="width:100%;">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
            
            	<div class="fill" style="background-image:url('<%=cp%>/res/images/mainslide/fill11.jpg');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            <div class="item">	
                <div class="fill" style="background-image:url('<%=cp%>/res/images/mainslide/fill22.jpg');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            <div class="item">
                <div class="fill" style="background-image:url('<%=cp%>/res/images/mainslide/mountain.jpg');"></div>
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
	<hr style="color: #777; ">
	<!-- <div style="text-align:center; font-weight:bold; ">
		<h2>이달의 우수단원</h2>
	</div> -->
	<!--  우수단원 소개란 시작 -->
	 <h1 style="margin-top:60px" class="title text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay="300ms">오월의 우수단원 </h1>
	 <h1 style="margin-top:-6px;font-weight:bold;" class="title text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay="300ms">
	 나누는 마음을 행동으로
	 </h1>
                <p style="color:gray; font-size:16px; "class="text-center wow fadeInDown" data-wow-duration="400ms" data-wow-delay="400ms">
                	 5월을 빛낸 이달의 우수 봉다리단원을 소개합니다! 댓글과 하트로 칭찬해주세용
                </p>
              <br>     
     <section id="team">
        <div class="container">
            <div class="row">
               
                <div id="team-carousel" class="carousel slide wow fadeIn" data-ride="carousel" data-wow-duration="400ms" data-wow-delay="400ms">
                    <!-- Indicators -->
                    <ol class="carousel-indicators visible-xs">
                        <li data-target="#team-carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#team-carousel" data-slide-to="1"></li>
                    </ol>        
               <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/1.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div style="text-align:center;"class="person-info">
                                        <h2>이 제훈</h2>
                                        <p>서울북부 &amp; 으쌰아</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/2.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                   <div style="text-align:center;"class="person-info">
                                        <h2>최 양희</h2>
                                        <p>부산북부 &amp; 따뜻따뜻</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/3.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                   <div style="text-align:center;"class="person-info">
                                        <h2>안 선경</h2>
                                        <p>강원북부 &amp; 위너맘</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/1.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div style="text-align:center;"class="person-info">
                                        <h2>조 일환</h2>
                                        <p>서울남부 &amp; 호주형</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/4.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="person-info">
                                        <h2>John Doe</h2>
                                        <p>CEO &amp; Developer</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/3.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="person-info">
                                        <h2>John Doe</h2>
                                        <p>CEO &amp; Developer</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/2.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="person-info">
                                        <h2>John Doe</h2>
                                        <p>CEO &amp; Developer</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3 col-xs-6">
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="<%=cp%>/res/images/aboutus/1.jpg" class="img-responsive" alt="">
                                        </div>
                                        <div class="social-profile">
                                            <ul class="nav nav-pills">
                                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="person-info">
                                        <h2>John Doe</h2>
                                        <p>CEO &amp; Developer</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                             <!-- Controls -->
                    <a class="left team-carousel-control hidden-xs" href="#team-carousel" data-slide="prev">left</a>
                    <a class="right team-carousel-control hidden-xs" href="#team-carousel" data-slide="next">right</a>
                </div>
            </div>
        </div>
    </section>
     																					  <!--  우수단원 소개란 끝 -->    
     <hr><br>
      																					 <!--  이달의 우수 후기 시작 -->
      																					 
      	 <h1 style=" font-weight:bold;" class="title text-center wow fadeInDown" data-wow-duration="500ms" data-wow-delay="300ms">
      	 	이 달의 봉다리 News
      	 </h1>
          <p style="color:gray; font-size:16px; "class="text-center wow fadeInDown" data-wow-duration="400ms" data-wow-delay="400ms">
                5월을 빛낸 이달의 따뜻한 우수단원을 소개합니다!  <br> 봉다리분들은 댓글과 하트로 칭찬해주세용
          </p>
          <br>     																				 
       <div class="row" style="width:100%; margin:0 auto; margin-top:20px; ">
       
       <div class="col-md-5" style="width:30%; margin-left:3%;" >
                               
                <div class="panel panel-default">
                    <div class="panel-heading" style="padding:18px;font-size:18px;">
                       <i class="fa fa-fw fa-paperclip"></i> 이달의 후기	<a href="#"><span style="float:right; color:white;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a> 
                    </div>
                    <div class="panel-body" style="border-bottom:2px solid #999; ">
                        <ul type="disc" style="margin-bottom:6px; ">
                        <%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>
                         <li><a href="#" >[충주] 따뜻했던 연탄봉사의 추억~</a></li>
                       		 <li><a href="#" >[충주] 따뜻했던 연탄봉사의 추억~</a></li>
                     	  	 <li><a href="#" >[서울] 벌써 보고싶은 아이들과 함께</a></li>
                       		 <li><a href="#" >[부산] 집에 가고 싶은 사람 손~</a></li>
                       		 <li><a href="#" >[강원] 저녁 뭐 먹지?</a></li>
                       		  <li><a href="#" >[전주] 힘들었던만큼 보람찼던 그 날</a></li>
                        	
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4" style="width:30%; margin-left:3%;" >
                <div class="panel panel-default">
                    <div class="panel-heading" style="padding:18px;font-size:18px;">
                       <i class="fa fa-fw fa-paperclip"></i> 이달의 봉다리 <a href="#"><span style="float:right; color:white;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
                    </div>
                 <div class="panel-body" style="border-bottom:2px solid #999; ">
                        <ul type="disc" style="margin-bottom:6px; ">
                        <%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>
                         <li><a href="#" >[충주] 따뜻했던 연탄봉사의 추억~</a></li>
                       		 <li><a href="#" >[충주] 따뜻했던 연탄봉사의 추억~</a></li>
                     	  	 <li><a href="#" >[서울] 벌써 보고싶은 아이들과 함께</a></li>
                       		 <li><a href="#" >[부산] 집에 가고 싶은 사람 손~</a></li>
                       		 <li><a href="#" >[강원] 저녁 뭐 먹지?</a></li>
                       		  <li><a href="#" >[전주] 힘들었던만큼 보람찼던 그 날</a></li>
                        	
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4" style="width:30%; margin-left:3%;">
                <div class="panel panel-default">
                    <div class="panel-heading" style="padding:18px;font-size:18px;">
                        <i class="fa fa-fw fa-paperclip"></i> 이달의 수요처 <a href="#"><span style="float:right; color:white;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
                    </div>
                    <div class="panel-body" style="border-bottom:2px solid #999; ">
                        <ul type="disc" style="margin-bottom:6px; ">
                        <%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>         
                           <li><a href="#" >[충주] 따뜻했던 연탄봉사의 추억~</a></li>               
                       		 <li><a href="#" >[충주] 따뜻했던 연탄봉사의 추억~</a></li>
                     	  	 <li><a href="#" >[서울] 벌써 보고싶은 아이들과 함께</a></li>
                       		 <li><a href="#" >[부산] 집에 가고 싶은 사람 손~</a></li>
                       		 <li><a href="#" >[강원] 저녁 뭐 먹지?</a></li>
                       		  <li><a href="#" >[전주] 힘들었던만큼 보람찼던 그 날</a></li>
                        	<!-- <li >	<a style="margin-top:8px;" href="#" class="btn btn-default" style="text-align: right;">더보기</a></li> -->
                        </ul>
                    </div>
                </div>
            </div>
            </div>
       <!--  이달의 우수 후기 끝-->
          <br><br>
          <hr>
    <script>
$('.carousel').carousel({
    interval: 5000 //changes the speed
})
</script>