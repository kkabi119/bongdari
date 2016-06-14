<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<script type="text/javascript">
function nextPage(){
	var total_page="${total_page}";
	var page="${page}";
	if(total_page==page){
		return;
	}
	var url="<%=cp%>/club/${clubSeq}/free/list2";
	var pageNo=page;
	
	$.post(url, {pageNo:pageNo}, function(data){
		$("#listFree${page}").append(data);
	});
}
</script>

  
             <div class="timeline-blog overflow padding-top" style="padding-top: 0px">
                    <div class="timeline-divider overflow padding-bottom">
                   <c:forEach var="dto" items="${list}">
                   		<c:if test="${dto.rnum%2==1}">
                        <div class="col-sm-6 padding-right arrow-right wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="300ms">
                            </c:if>
                            <c:if test="${dto.rnum%2==0}">
                            <div class="col-sm-6 padding-left padding-top arrow-left wow fadeInRight" data-wow-duration="1000ms" data-wow-delay="300ms">
                            </c:if>
                            <div class="single-blog timeline">
                               <div class="single-blog-wrapper" style="height:200px">
                                <c:if test="${not empty dto.listImageName}">
                                    <div class="post-thumb">
                                        ${dto.listImageName}
                                    </div>      
                                </c:if>
                                <c:if test="${empty dto.listImageName}">
                                    <div class="post-thumb">
                                        <img src="<%=cp%>/res/images/clubfree/no-photo.png">
                                    </div>      
                                </c:if>
                                </div>
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="${urlArticle}&num=${dto.clubFreeIdx}">${dto.subject}</a></h2>
                                    <h3 class="post-author"><a href="#">Posted by ${dto.userName}</a></h3>
                                    <p>${dto.content}[...]</p>
                                    <a href="${urlArticle}&num=${dto.clubFreeIdx}" class="read-more">View More</a>
                                    <div class="post-bottom overflow">
                                        <span class="post-date pull-left">${dto.created}</span>
                                        <span class="comments-number pull-right" style="padding-left: 10px">${dto.hitCount} views </span>
                                        <span class="comments-number pull-right"><a href="#">${dto.replyDataCount} comments</a></span>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                 </c:forEach>
                    </div>
                </div>
                <div id="listFree${page}">
                 
                </div>

    
