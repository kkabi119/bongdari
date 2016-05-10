<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>


                <div class="col-md-3 col-sm-5">
                    <div class="sidebar blog-sidebar">
                    	<!-- 코멘트 쓰려면 쓰기 없어도 됨 -->
                        <%-- <div class="sidebar-item  recent">
                            <h3>Comments</h3>
                            <div class="media">
                                <div class="pull-left">
                                    <a href="#"><img src="<%=cp%>/res/images/portfolio/project1.jpg" alt=""></a>
                                </div>
                                <div class="media-body">
                                    <h4><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit,</a></h4>
                                    <p>posted on  07 March 2014</p>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <a href="#"><img src="<%=cp%>/res/images/portfolio/project2.jpg" alt=""></a>
                                </div>
                                <div class="media-body">
                                    <h4><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit,</a></h4>
                                    <p>posted on  07 March 2014</p>
                                </div>
                            </div>
                            <div class="media">
                                <div class="pull-left">
                                    <a href="#"><img src="<%=cp%>/res/images/portfolio/project3.jpg" alt=""></a>
                                </div>
                                <div class="media-body">
                                    <h4><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit,</a></h4>
                                    <p>posted on  07 March 2014</p>
                                </div>
                            </div>
                        </div> --%>
                        <div class="sidebar-item categories">
                            <h3>Categories</h3>
                            <ul class="nav navbar-stacked">
                                <li><a href="<%=cp%>/main/demander">수요처 전체메인<span class="pull-right">(1)</span></a></li>
                                <li class="active"><a href="<%=cp%>/demander/index/main">수요처메인<span class="pull-right">(8)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/calendar">수요처달력<span class="pull-right">(4)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/qna">수요처QnA<span class="pull-right">(9)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/photo">포토게시판<span class="pull-right">(3)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/review">후기게시판<span class="pull-right">(4)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/schedule">일정등록페이지<span class="pull-right">(2)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/approval">요청승인페이지<span class="pull-right">(8)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/eval">평가페이지<span class="pull-right">(8)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/guest">방명록<span class="pull-right">(8)</span></a></li>
                                <li><a href="<%=cp%>/demander/index/admin">관리자페이지<span class="pull-right">(8)</span></a></li>
                            </ul>
                        </div>
                        <div class="sidebar-item tag-cloud">
                            <h3>Tag Cloud</h3>
                            <ul class="nav nav-pills">
                                <li><a href="#">Corporate</a></li>
                                <li><a href="#">Joomla</a></li>
                                <li><a href="#">Abstract</a></li>
                                <li><a href="#">Creative</a></li>
                                <li><a href="#">Business</a></li>
                                <li><a href="#">Product</a></li>
                            </ul>
                        </div>
                      <%--   <div class="sidebar-item popular">
                            <h3>Latest Photos</h3>
                            <ul class="gallery">
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular1.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular2.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular3.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular4.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular5.jpg" alt=""></a></li>
                                <li><a href="#"><img src="<%=cp%>/res/images/portfolio/popular1.jpg" alt=""></a></li>
                            </ul>
                        </div> --%>
                    </div>
                </div>
