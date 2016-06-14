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
         <c:forEach var="dto" items="${revPhoto1}">
                 <div class="col-sm-3 col-xs-6" >
                                <div class="team-single-wrapper">
                                    <div class="team-single"  >
                                        <div class="person-thumb" >            
                                             <img src="${dto.listImageName}" style="height:200px; width:200px; background-size:cover;" > 
                                        </div>
                                    </div>
                                </div>
                                </div>
        </c:forEach> 
                         </div>
                   
                         <div class="item">
        <c:forEach var="dto2" items="${revPhoto2}"><!-- style="height: 300px; width: 300px; background-size:cover; -->
                 <div class="col-sm-3 col-xs-6" >
                                <div class="team-single-wrapper">
                                    <div class="team-single">
                                        <div class="person-thumb">
                                            <img src="${dto2.listImageName}" style="height:200px; width:200px; background-size:cover;"><!-- class="img-responsive" alt="" -->
                                        </div>
                                    </div>
                                </div>
                  </div>
     	</c:forEach>
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