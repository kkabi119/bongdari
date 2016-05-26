<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>

  <section id="blog" class="padding-bottom">
             <div class="timeline-blog overflow padding-top">
             <div style="float: right; clear:both">
        		    			<button type="button" class="btn btn-lg btn-info" onclick="javascript:location.href='<%=cp%>/club/${clubSeq}/free/created';"><span class="glyphicon glyphicon glyphicon-pencil"></span> 글쓰기</button>
        					</div>
                    <div class="timeline-date text-center" style="clear:both">
                        <a href="#" class="btn btn-common uppercase">May 2016</a>
                    </div>
                    <div class="timeline-divider overflow padding-bottom">
                   <c:forEach var="dto" items="${list}">
                   		<c:if test="${dto.rnum%2==1}">
                        <div class="col-sm-6 padding-right arrow-right wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="300ms">
                            </c:if>
                            <c:if test="${dto.rnum%2==0}">
                            <div class="col-sm-6 padding-left padding-top arrow-left wow fadeInRight" data-wow-duration="1000ms" data-wow-delay="300ms">
                            </c:if>
                            <div class="single-blog timeline">
                                <div class="single-blog-wrapper">
                                    <div class="post-thumb">
                                        <img src="<%=cp%>/res/images/free/222.jpg" class="img-responsive" alt="">
                                        <div class="post-overlay">
                                           <span class="uppercase"><a href="#">14 <br><small>Feb</small></a></span>
                                       </div>
                                    </div>
                                </div>
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="blogdetails.html">${dto.subject}</a></h2>
                                    <h3 class="post-author"><a href="#">Posted by ${dto.userName}${dto.listNum}${dto.rnum}</a></h3>
                                    <p>${dto.content} [...]</p>
                                    <a href="${urlArticle}&num=${dto.clubFreeIdx}" class="read-more">View More</a>
                                    <div class="post-bottom overflow">
                                        <span class="post-date pull-left">${dto.created}</span>
                                        <span class="comments-number pull-right"><a href="#">${dto.replyDataCount} comments</a></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                 </c:forEach>
                    </div>
                </div>
                <div class="timeline-blog overflow">
                    <div class="timeline-date text-center">
                        <a href="" class="btn btn-common uppercase">Setember 2013</a>
                    </div>
                    <div class="timeline-divider overflow padding-bottom">
                        <div class="col-sm-6 padding-right arrow-right wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="300ms">
                            <div class="single-blog timeline">
                                <div class="single-blog-wrapper">
                                    <div class="post-thumb">
                                        <img src="<%=cp%>/res/images/free/222.jpg" class="img-responsive" alt="">
                                        <div class="post-overlay">
                                           <span class="uppercase"><a href="#">14 <br><small>Feb</small></a></span>
                                       </div>
                                    </div>
                                </div>
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="blogdetails.html">Advanced business cards design</a></h2>
                                    <h3 class="post-author"><a href="#">Posted by micron News</a></h3>
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber [...]</p>
                                    <a href="#" class="read-more">View More</a>
                                    <div class="post-bottom overflow">
                                        <span class="post-date pull-left">February 11, 2014</span>
                                        <span class="comments-number pull-right"><a href="#">3 comments</a></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 padding-left padding-top arrow-left wow fadeInRight" data-wow-duration="1000ms" data-wow-delay="300ms">
                            <div class="single-blog timeline">
                                <div class="single-blog-wrapper">
                                    <div class="post-thumb">
                                        <img src="<%=cp%>/res/images/free/222.jpg" class="img-responsive" alt="">
                                        <div class="post-overlay">
                                           <span class="uppercase"><a href="#">14 <br><small>Feb</small></a></span>
                                       </div>
                                    </div>
                                </div>
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="blogdetails.html">Advanced business cards design</a></h2>
                                    <h3 class="post-author"><a href="#">Posted by micron News</a></h3>
                                    <p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber [...]</p>
                                    <a href="#" class="read-more">View More</a>
                                    <div class="post-bottom overflow">
                                        <span class="post-date pull-left">February 11, 2014</span>
                                        <span class="comments-number pull-right"><a href="#">3 comments</a></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-date text-center">
                        <a href="#" class="btn btn-common">See More</a>
                    </div>
                </div>
    </section>
