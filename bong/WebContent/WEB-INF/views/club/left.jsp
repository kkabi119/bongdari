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
    font-weight: 500;
    padding: 5px 15px;
    background: none;
    color: #777;
     border:1px solid #ccc;
    border-radius: 1px;
    }
    .tag-cloud .nav-pills li a:hover {
    background: #FFE258;
	}
	
	.sidebar h3{
		border-bottom:3px solid #ccc;
		margin-bottom: 10px ;
	}
	
	.btn .btn-default:hover{
		background-color:#777;
	}
</style>
	<script type="text/javascript">
	$(function(){
		
		var url="<%=cp%>/club/${clubSeq}/left";
		$.post(url, {}, function(data){
			
			var introduce=data.dto.introduce;
			var city=data.dto.city;
			var category=data.dto.groupSubject+" > "+data.dto.subject;
			var clubname=data.dto.clubname;
			
			$("#introduce").html(introduce);
			$("#city").html(city);
			$("#category").html(category);
			$("#clubname").html(clubname);
		}, "JSON");
	});
	
	$(function(){
		var idx="${subMenu}";
		if(!idx) idx=1;
		var subMenu=".navbar-stacked-item"+idx;
		$(subMenu).addClass("active");
	});
	
	function joinClub(){
				
		
	  	location.href=url;
		  	
	}
	
	$(function(){
		$("#joinClub").click(function(){			 
/* 			var num = "${dto.clubApplyIdx}";
			var enabled = "${enabled}";
			
			var params = "num="+num+"&enabled="+enabled;
 */			
			var url = "<%=cp%>/club/${clubSeq}/joinClub";

			$('#joinClubModal .modal-body').load(url,function(){
				
					$('#joinClubModal .modal-title').html('가입신청');
					
					$('#joinClubModal').modal('show'); 
				});		
		})
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
                        <div class="sidebar-item tag-cloud" style="margin-bottom:20px;">
                        <!-- 개설일, 회원수, 관리자이름  -->
                            <h3 >Info </h3>
                            <ul class="nav nav-pills" style="margin-top:10px;">
                                <li><a href="level">황금다리</a></li>
                                <li><a href="point">297점</a></li>
                                <li><a id="city" href=""></a></li>
                                <li><a  id="category" href=""></a></li>
                                <li><a id="introduce" href=""></a></li>
                            </ul>
                             <hr style="margin-bottom:10px; margin-top:15px; border:2px solid #ccc;">
                             <c:if test="${authority==null}">
                             <button type="button" class="btn btn-default" 
                             			style="width:100%;border-radius:0px; padding:15px 25px ; margin-bottom:0px; background-color:#3897f0; color:white; border:none;"
                      			 id="joinClub">가입하기</button>
                      		 </c:if>
                        </div>
                        <div class="sidebar-item categories">
                            <ul class="nav navbar-stacked" >
                            <li><h3 class="nav navbar-stacked lefthead">Categories</h3></li>
                                <li  class="navbar-stacked-item3"><a href="<%=cp%>/club/${clubSeq}/notice/list">공지사항</a></li>
                                <li  class="navbar-stacked-item5"><a href="<%=cp%>/club/${clubSeq}/free/list">자유게시판</a></li>
                                <li  class="navbar-stacked-item6"><a href="<%=cp%>/club/${clubSeq}/review/list">후기/포토게시판</a></li>
                                <li  class="navbar-stacked-item7"><a href="<%=cp%>/club/${clubSeq}/apply/list">봉사신청게시판</a></li>
                                <li  class="navbar-stacked-item8"><a href="<%=cp%>/club/${clubSeq}/calendar/main">우리동아리 달력</a></li>
                                <li  class="navbar-stacked-item9"><a href="<%=cp%>/club/${clubSeq}/manage">관리자모드</a></li>
                            </ul>
                             <hr style="margin-bottom:10px; margin-top:15px; border:2px solid #ccc;">
                        </div>
                    </div>
                </div>
                
                
                
<!-- 가입신청 버튼 클릭 후 뜨는 모달창 -->
	<div class="modal fade" id="joinClubModal" tabindex="-1" role="dialog" aria-labelledby="scheduleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" style="width:600px; top:120px;">
	    <div class="modal-content" style="border-radius:3px;">
	      <div class="modal-header" style="background:#3897f0; color:white;">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        	<span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="scheduleModalLabel" style="font-family: 나눔고딕, 맑은 고딕, sans-serif; font-weight: bold;">가입신청</h4>
	      </div>
	      <div class="modal-body"> </div>
	    </div>
	  </div>
	</div>
