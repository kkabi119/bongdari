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
.modal-body{
}
.col-md-9{
			margin-top:-25px;
		}
/* 수정삭제는 관리자만 보이도록한다 
 */
.bbs-reply {
    border-top: #3897f0 solid 2px; 
    border-bottom: #3897f0 solid 2px; padding:15px;
    margin-bottom:70px;
}

.bbs-reply-write {   
   border-bottom: #ddd solid 2px; 
    padding: 10px;
    min-height: 50px;
}

.table{
	 margin-top: 10px;
	 border-top: 3px solid #FFBB00;
	 border-bottom: 3px solid #FFBB00;
}


.table>tbody>tr>td{
 padding-top: 13px;
 border-bottom: 1px solid #969494;
}

.table>tr{
	font-size:20px; text-align: center;
	 padding:14px; 
}

#td1{
border-right: 1px solid #969494;
}
.icon-wrapper{
box-shadow:none;
background-color: #4FCCCD;
}
.icon-wrapper:hover{
	background-color:#4FCCCD;
}


.form-control{
	border-radius:0px;
	height:120px;
	font-weight:lighter;
	font-size:15px;
	border:none;
	border-bottom: 1px solid #ddd;
	resize:none;
}
.form-control:hover, .form-control:focus{

	border-bottom: 1px solid #999;'
}

.btn {
	border-radius:2px;
}

.icon-wrapper:hover{
	background-color: gray;
}
.modal-body{
 background-color: #F5F5F5;
}
</style>


	<div class="row">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
         
    
    <!-- 프로필 -->
    <header>
		 <div style="float: right; margin-top:70px; font-weight: bold; color:#969494; font-size: 35pt;">
           Profile
           </div>
		<div class="table-responsive" style="clear: both;">
           <div >
          
               <table class="table">
                        <tr>
                       		<td id="imgtd" style=" width:47%;" rowspan="6">
                        	 	<img style="width: 350px; height: 350px; background-size: cover; margin-left: 16px;"
										class="media-object" 
										src="<%=cp%>/uploads/serviceImg/${mainProfile.serviceImg}" alt="">
							</td>
                        	<td id="td1" style="color:black; border-top:none;text-align: left; height:45px; width:14%; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
                        	 	<span style="color:#969494; font-weight: bold;">수요처 이름</span>	
                        	</td>
                        	<td bgcolor="#FFFFFF" style="color:black; border-top:none;text-align: left; height:45px; ">
                        	${mainProfile.serviceName}
                        	</td>
                        </tr>
                        <tr >
                        	<td id="td1" style="color:black; border-top:none;text-align: left; height:130px; width:14%; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
                        	 	<span style="color:#969494; font-weight: bold;">소개</span>	
                        	</td>
                        	<td bgcolor="#FFFFFF" style="color:black; border-top:none;text-align: left; height:45px;">
                   			${mainProfile.serviceIntro}
                        	</td>
                        </tr>
                          <tr >
                        	<td id="td1" style="color:black; border-top:none;text-align: left; height:45px; width:14%; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
                        	 	<span style="color:#969494; font-weight: bold;">봉사유형</span>	
                        	</td>
                        	<td bgcolor="#FFFFFF" style="color:black; border-top:none;text-align: left; height:45px;  ">
                   			${mainProfile.themeName}
                        	</td>
                        </tr>
                         <tr >
                        	<td id="td1" style="color:black; border-top:none;text-align: left; height:45px; width:14%; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
                        	 	<span style="color:#969494; font-weight: bold;">전화번호</span>	
                        	</td>
                        	<td bgcolor="#FFFFFF" style="color:black; border-top:none;text-align: left; height:45px;  ">
               			    ${mainProfile.serviceTel}
                        	</td>
                        </tr>
                         <tr >
                        	<td id="td1" style="color:black; border-top:none;text-align: left; height:45px; width:14%; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
                        	 	<span style="color:#969494; font-weight: bold;">이메일</span>	
                        	</td>
                        	<td bgcolor="#FFFFFF" style="color:black; border-top:none;text-align: left; height:45px;  ">
                 		  ${mainProfile.serviceEmail}
                        	</td>
                        </tr>
                         <tr >
                        	<td id="td1" style="color:black; border-top:none;text-align: left; height:45px; width:14%; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;
                        	 	<span style="color:#969494; font-weight: bold;">주소</span>	
                        	</td>
                        	<td bgcolor="#FFFFFF" style="color:black; border-top:none;text-align: left; height:45px;  ">
                   			 ${mainProfile.serviceAddr}<a href="#">&nbsp;&nbsp;[지도]</a>
                        	</td>
                        </tr>
               </table>
          </div>
          </div>
          </header>
          <!-- 프로필 끝-->             
    </div>
    
    
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
                                             <img src="${dto.listImageName}" style="height:215px; width:215px; background-size:cover;" > 
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
                                            <img src="${dto2.listImageName}" style="height:215px; width:215px; background-size:cover;"><!-- class="img-responsive" alt="" -->
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
<div class="col-md-5" style="margin-left:30px; margin-right:40px;" >
                <div class="panel panel-default">
                    <div class="panel-heading"  style="background:#FFCD44;">
                        <h5 style="color:white; font-weight: bold;"><i class="fa fa-fw fa-paperclip"></i> 이달의 후기</h5>
                    </div>
                    <div class="panel-body">
                        <ul type="disc">
                           <c:forEach var="revList" items="${revList}">
                        	<li><a href="${urlRevArticle}&num=${revList.serviceReviewIdx}" >${revList.subject}</a></li>
                          </c:forEach> 
                        	<li><a href="${urlRevList}" class="btn btn-default" style="text-align: right">더보기</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            
            <div class="col-md-5" style="margin-left:30px; margin-right:0px;" >
                <div class="panel panel-default">
                    <div class="panel-heading" style="background:#FFCD44;">
                        <h5 style="color:white; font-weight: bold;"><i class="fa fa-fw fa-paperclip"></i> 이달의 봉다리</h5>
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

            </div>
            
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