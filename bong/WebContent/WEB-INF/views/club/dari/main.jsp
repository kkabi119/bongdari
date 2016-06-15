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
/*  .row{
	margin-left: 15px
}  */

.col-md-9{
	margin-left:45px;
	width:74%;
}

li:hover{
	color:#ddd;
}
</style>

	<div class="row" class="carousel slide wow fadeIn" data-ride="carousel" data-wow-duration="400ms" data-wow-delay="400ms">
		<div class="col-md-12 col-sm-12" >
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
                  
                  <hr style="border-top:3px solid #eee;border-bottom:25px; width:98%; color: #777; ">
	
	 <h2 style="margin-top:10px; margin-left:20px; margin-bottom:20px; font-weight:500; text-align:center;" class="title wow fadeInDown" data-wow-duration="500ms" data-wow-delay="300ms">
	 		우리의 <span style="font-weight:bold;">최근활동 </span> </h2>
	<!--  <h2 style="margin-top:6px;  margin-left:20px;font-weight:bold;" class="title wow fadeInDown" data-wow-duration="500ms" data-wow-delay="300ms">
	 나누는 마음을 행동으로
	 </h2> -->
                  
               <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
         <c:forEach var="dto" items="${revPhoto1}">
                 <div class="col-sm-3 col-xs-6" >
                                <div class="team-single-wrapper">
                                    <div class="team-single"  >
                                        <div class="person-thumb" >            
                                             <img src="${dto.listImageName}" style="height:190px; width:190px; background-size:cover;" > 
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
                                            <img src="${dto2.listImageName}" style="height:190px; width:190px; background-size:cover;"><!-- class="img-responsive" alt="" -->
                                        </div>
                                    </div>
                                </div>
                  </div>
     	</c:forEach>
                    </div>
                    </div>
                    
                             <!-- Controls -->
                    <a style="left:-20px; margin-top:53px;" class="left team-carousel-control hidden-xs" href="#team-carousel" data-slide="prev">left</a>
                    <a style="right:-20px; margin-top:53px" class="right team-carousel-control hidden-xs" href="#team-carousel" data-slide="next">right</a>
                </div>
            </div>
       
    </section>
         
      <hr style="border-top:3px solid #eee; width:98%; color: #777; border-bottom:8px; ">
<div class="row" style="margin-top:20px;">
			<div class="col-md-6" style="width:45%; margin-right:85px;" >
                <div class="panel panel-default">
                    <div class="panel-heading"  style="background-color:#3897f0; border-radius:0px;">
                        <h5 style="font-size:18px;"><i class="fa fa-lightbulb-o"></i> &nbsp;&nbsp;봉사 후기 
                        		<a href="${urlListR}"><span style="float:right; color:white;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a> 
                       	</h5>
                    </div>
                    <div class="panel-body" style="border-bottom:2px solid #999; ">
                        <ul type="disc" style="padding-left:0px; ">
                         <c:forEach var="dtoR" items="${listR}">
                        	<%-- <li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li> --%>
                         
                        
                       		 <li style="line-height:30px; font-weight:600; font-size:14px; overflow:hidden; width:90%; ">
                       		 		<a style="color:#464646;" href="${urlArticleR}+&num=${dtoR.clubReviewIdx}" >${dtoR.subject}</a>
                       		 </li>
                       	 </c:forEach> 
                     	  
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-md-6" style="width:45%; " >
                <div class="panel panel-default">
                    <div class="panel-heading"  style="background-color:#3897f0; border-radius:0px;">
                        <h5 style="font-size:18px;"><i class="fa fa-lightbulb-o"></i> &nbsp;&nbsp;공지사항 <a href="${urlListN}"><span style="float:right; color:white;" class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a> </h5>
                    </div>
                    <div class="panel-body" style="border-bottom:2px solid #999; ">
                        <ul type="disc" style="padding-left:0px; ">
                         <c:forEach var="dtoN" items="${listN}">
                        	<%-- <li><a href="<%=cp%>/docu/article.sst?page=1&docuNum=${dtoDocu.docuNum}" >${dtoDocu.docuSubject}</a></li> --%>
                       		 <li style="line-height:30px; font-weight:600; font-size:14px;overflow:hidden; width:90%; ">
                       		 		<a style="color:#464646;" href="${urlArticleN}+&num=${dtoN.clubNoticeIdx}" >${dtoN.subject}</a></li>
                       	 </c:forEach> 
                     	  	 
                        	
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