<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
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

							<div class="fill"
								style="background-image:url('<%=cp%>/res/images/demander/demander1.jpg');"></div>
							<div class="carousel-caption">
								<h2></h2>
							</div>
						</div>
						<div class="item">
							<div class="fill"
								style="background-image:url('<%=cp%>/res/images/demander/demander2.jpg');"></div>
							<div class="carousel-caption">
								<h2></h2>
							</div>
						</div>
						<div class="item">
							<div class="fill"
								style="background-image:url('<%=cp%>/res/images/myclub/333.jpg');"></div>
							<div class="carousel-caption">
								<h2></h2>
							</div>
						</div>

					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span class="icon-prev"></span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span class="icon-next"></span>
					</a> <br>
					<br> <br>
					<br>

				</header>
				<div class="post-content overflow">
					<h2 class="post-title bold">
						<a href="blogdetails.html">내 수요처 메인</a>
					</h2>

				</div>
			</div>
		</div>

	</div>
</div>

<!--  이달의 봉사활동 -->
<div class="row" style="margin-top: 50px;">

	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading" style="background:#F0AD4E;">
				<h5 style="color: white; font-weight: bold;">
					<i class="fa fa-fw fa-paperclip"></i> 이달의 봉사활동
				</h5>
			</div>
			<div class="panel-body">
				<ul type="disc">
					<%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>

					<li><a href="#">[수요처A] 5/30 보육 봉사활동 모집</a></li>
					<li><a href="#">[수요처A] 5/25 보육 봉사활동 모집</a></li>
					<li><a href="#">[수요처A] 5/20 보육 봉사활동 모집</a></li>
					<li><a href="#">[수요처A] 5/18 보육 봉사활동 모집</a></li>
					<li><a href="#">[수요처A] 5/10 보육 봉사활동 모집</a></li>
					<li><a href="#" class="btn btn-default"
						style="text-align: right">더보기</a></li>
				</ul>
			</div>
		</div>
		
<!-- 최근사진 -->

 <div class="sidebar-item popular">
			<h5 style="color:#F0AD4E; font-weight:normal; font-size: 17pt;">
				<i class="fa fa-fw fa-paperclip"></i> Latest Photos
			</h5>
			<div class="panel-body">
				<ul class="gallery">
					<li><a href="#"><img
							src="<%=cp%>/res/images/portfolio/popular1.jpg" alt=""></a></li>
					<li><a href="#"><img
							src="<%=cp%>/res/images/portfolio/popular2.jpg" alt=""></a></li>
					<li><a href="#"><img
							src="<%=cp%>/res/images/portfolio/popular3.jpg" alt=""></a></li>
					<li><a href="#"><img
							src="<%=cp%>/res/images/portfolio/popular4.jpg" alt=""></a></li>
					<li><a href="#"><img
							src="<%=cp%>/res/images/portfolio/popular5.jpg" alt=""></a></li>
					<li><a href="#"><img
							src="<%=cp%>/res/images/portfolio/popular1.jpg" alt=""></a></li>
				</ul>
			</div>
		</div>
	</div> 
<!-- 이달의후기 -->
	<div class="col-md-4" style="float: left;">
		<div class="panel panel-default">
			<div class="panel-heading" style="background:#F0AD4E;">
				<h5 style="color: white; font-weight: bold;">
					<i class="fa fa-fw fa-paperclip"></i> 이달의 후기
				</h5>
			</div>
			<div class="panel-body">
				<ul type="disc">
					<%--   <c:forEach var="dtoDocu" items="${listDocu}">
                        	<li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li>
                          </c:forEach> --%>

					<li><a href="#">[동아리A] 따뜻했던 연탄봉사의 추억~</a></li>
					<li><a href="#">[동아리B] 벌써 보고싶은 아이들과 함께</a></li>
					<li><a href="#">[동아리C] 집에 가고 싶은 사람 손~</a></li>
					<li><a href="#">[동아리D] 저녁 뭐 먹지?</a></li>
					<li><a href="#">[동아리E] 힘들었던만큼 보람찼던 그 날</a></li>
					<li><a href="#" class="btn btn-default"
						style="text-align: right">더보기</a></li>
				</ul>
			</div>
		</div>

	</div>





</div>
<!--  이달의 우수 후기 끝-->

<!--/#blog-->
