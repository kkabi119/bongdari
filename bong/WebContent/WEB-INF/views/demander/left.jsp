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
	.col-md-3 {
		width:22%;
	}
</style>

                <div class="col-md-3 col-sm-5" style="width:width:22%;">
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
                                <li><a href="<%=cp%>/main/demander">수요처 전체메인<span class="pull-right"></span></a></li>
                                <li class="active"><a href="<%=cp%>/demander/${demander_seq}/main">수요처메인<span class="pull-right"></span></a></li>
                                <li><a href="<%=cp%>/demander/${demander_seq}/notice/list">공지사항<span class="pull-right"></span></a></li>
                                <li><a href="<%=cp%>/demander/${demander_seq}/schedule">수요처 일정(달력/신청)<span class="pull-right"></span></a></li>
                                <li><a href="<%=cp%>/demander/${demander_seq}/qna/list">수요처QnA<span class="pull-right"></span></a></li>
                               <%--  <li><a href="<%=cp%>/demander/${demander_seq}/photo">포토게시판<span class="pull-right"></span></a></li> --%>
                                <li><a href="<%=cp%>/demander/${demander_seq}/review/list">후기게시판<span class="pull-right"></span></a></li>
							<%--<li><a href="<%=cp%>/demander/${demander_seq}/schedule">일정등록페이지<span class="pull-right"></span></a></li> --%>
                              
                                <li><a href="<%=cp%>/demander/${demander_seq}/bookmark">관심등록 동아리<span class="pull-right"></span></a></li>
                                <li><a href="<%=cp%>/demander/${demander_seq}/guest">방명록<span class="pull-right"></span></a></li>
                                <li><a href="<%=cp%>/demander/${demander_seq}/admin/admin">관리자페이지<span class="pull-right"></span></a></li>
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
