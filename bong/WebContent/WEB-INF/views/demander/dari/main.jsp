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
                                <div class="post-thumb">
                                    <a href="blogdetails.html"><img src="<%=cp%>/res/images/blog/7.jpg" class="img-responsive" alt=""></a>
                                    <div class="post-overlay">
                                        <span class="uppercase"><a href="#">14 <br><small>Feb</small></a></span>
                                    </div>
                                </div>
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="blogdetails.html">내 수요처 메인!</a></h2>
                                   
                                </div>
                            </div>
                        </div>
                      
                 </div>
            </div>
            
              <!--  이달의 우수 후기 -->


	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5 style="color: #687ead; font-weight: bold;">
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
	</div>

	<div class="col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5 style="color: #687ead; font-weight: bold;">
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
		
		<div class="sidebar-item popular" >
                <h5 style="color: #687ead; font-weight: bold;">
					<i class="fa fa-fw fa-paperclip"></i> Latest Photos
				</h5>
				<div class="panel-body">
                            <ul class="gallery">
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular1.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular2.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular3.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular4.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular5.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular1.jpg" alt=""></a></li>
                            </ul>
                   </div>
          </div>
	</div>






<!--  이달의 우수 후기 끝-->
      
    <!--/#blog-->
