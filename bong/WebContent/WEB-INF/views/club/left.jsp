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
	.tag-cloud .nav-pills li a {
    font-size: 14px;
    font-weight: 300;
    padding: 5px 15px;
    background: #FFB000;
    color: #fff;
    border-radius: 3px;
    }
    .tag-cloud .nav-pills li a:hover {
    background: #FFE258;
	}
</style>
	<script type="text/javascript">
	$(function(){
		
		var url="<%=cp%>/club/${clubSeq}/left";
		$.post(url, {}, function(data){
			
			var introduce=data.dto.introduce;
			var city=data.dto.city;
			var category=data.dto.groupSubject+" > "+data.dto.subject;
			
			$("#introduce").html(introduce);
			$("#city").html(city);
			$("#category").html(category);
		}, "JSON");
	});
	</script>
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
                                <li><a href="<%=cp%>/club/${clubSeq}/japply">가입신청하기<span class="pull-right">(9)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/free/list">자유게시판<span class="pull-right">(3)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/review/list">후기/포토게시판<span class="pull-right">(4)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/apply/list">봉사신청게시판<span class="pull-right">(2)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/calendar/main">우리동아리 달력<span class="pull-right">(8)</span></a></li>
                                <li><a href="<%=cp%>/club/${clubSeq}/admin">관리자모드<span class="pull-right">(8)</span></a></li>
                            </ul>
                        </div>
                        <div class="sidebar-item tag-cloud">
                            <h3>동아리 간단정보</h3>
                            <ul class="nav nav-pills">
                                <li><a href="#">황금다리</a></li>
                                <li><a href="#">297점</a></li>
                                <li><a id="city" href=""></a></li>
                                <li><a  id="category" href=""></a></li>
                                <li><a id="introduce" href=""></a></li>
                            </ul>
                        </div>
                        
                    </div>
                </div>
