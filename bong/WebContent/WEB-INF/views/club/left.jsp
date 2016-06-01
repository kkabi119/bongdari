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
                                <li><a href="<%=cp%>/main/club/">동아리 메인페이지<span class="pull-right">(1)</span></a></li>
                                <li class="active"><a href="<%=cp%>/club/${clubSeq}/main">내 동아리 메인<span class="pull-right">(8)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/notice/list">공지사항<span class="pull-right">(4)</span></a></li>
                                <li><a href="<%=cp%>/club/index/japply">가입신청하기<span class="pull-right">(9)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/free/list">자유게시판<span class="pull-right">(3)</span></a></li>
                                <li><a href="<%=cp%>/club/index/review">후기/포토게시판<span class="pull-right">(4)</span></a></li>
                                <li><a href="<%=cp%>/club/index/vapply">봉사신청게시판<span class="pull-right">(2)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/calendar/main">우리동아리 달력<span class="pull-right">(8)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/admin">관리자모드<span class="pull-right">(8)</span></a></li>
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
                        
                    </div>
                </div>
