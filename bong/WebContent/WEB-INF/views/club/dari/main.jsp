<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<style type="text/css">
.row{
	margin-left: 15px
}
		
</style>

	<div class="row">
		<div class="col-md-12 col-sm-12">
			<img src="<%=cp%>/uploads/club/${clubSeq}/${clubInfo.photoFilename}" style="width:100%; height:400px">
    
    </div>

    </div>
         <section id="team">
       
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
       
    </section>
         
    
<div class="row" style="margin-top:50px;">
			<div class="col-md-6" >
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5 style="font-size:18px;"><i class="fa fa-lightbulb-o"></i> 공지사항 <a href="#"><span style="float:right; color:white;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a> </h5>
                    </div>
                    <div class="panel-body">
                        <ul type="disc">
                         <c:forEach var="dtoR" items="${listR}">
                        	<%-- <li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li> --%>
                         
                        
                       		 <li><a href="${urlArticleR}+&num=${dtoR.clubReviewIdx}" >${dtoR.subject}</a></li>
                       	 </c:forEach> 
                     	  	 
                        	<li><a href="${urlListR}" class="btn btn-default" style="text-align: right">더보기</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-md-6" >
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5 style="font-size:18px;"><i class="fa fa-lightbulb-o"></i> 공지사항 <a href="#"><span style="float:right; color:white;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a> </h5>
                    </div>
                    <div class="panel-body">
                        <ul type="disc">
                         <c:forEach var="dtoN" items="${listN}">
                        	<%-- <li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li> --%>
                         
                        
                       		 <li><a href="${urlArticleN}+&num=${dtoN.clubNoticeIdx}" >${dtoN.subject}</a></li>
                       	 </c:forEach> 
                     	  	 
                        	<li><a href="${urlListN}" class="btn btn-default" style="text-align: right">더보기</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            </div>
     <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>