<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<style type="text/css">
.parrent .media-list{
	margin-left:0px;
}
.parrent .post-comment{
	margin-left:20px;
	min-height:130px;
}
</style>
<c:if test="${not empty listReplyAnswer}">
	<c:forEach var="Adto" items="${listReplyAnswer}">
<div class="parrent">
                                                <ul class="media-list">
                                                    <li class="post-comment reply">
                                                    
                                                        <a class="pull-left" href="#">
                                                            <img style=" width:117px; height:107px; background-size:cover; "class="media-object" src="<%=cp%>/res/images/blogdetails/1.png" alt="">
                                                        </a>
                                                        <div class="media-body" style="padding-bottom: 10px">
                                                            <span><i class="fa fa-user"></i><a href="#">${Adto.userName}</a></span>
                                                             <c:if test="${sessionScope.member.userIdx==Adto.userIdx || sessionScope.member.userId=='admin'}">   
		     											<span style="margin-left:10px; float:right; ">
		     												<a class="btn" onclick='deleteReplyAnswer("${Adto.replyNum}", "${Adto.answer}");' style="background-color:none; margin-top:-5px;color:#C03035; border:none;">
		     													삭제
		     												</a>
		     											</span>
		     											  <span  style="color:#888; float:right;"><i class="fa fa-clock-o"  style="color:#888"></i>${Adto.created}</span>
						</c:if>
                                                            <p>${Adto.content}</p>
                      
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
	</c:forEach>
</c:if>


<%-- <%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<c:if test="${not empty listReplyAnswer}">
	<c:forEach var="Adto" items="${listReplyAnswer}">
<div class="parrent">
                                                <ul class="media-list">
                                                    <li class="post-comment reply">
                                                    
                                                        <a class="pull-left" href="#">
                                                            <img class="media-object" src="images/blogdetails/3.png" alt="">
                                                        </a>
                                                        <div class="media-body">
                                                            <span><i class="fa fa-user"></i>Posted by <a href="#">${Adto.userName}</a></span>
                                                            <p>${Adto.content}</p>
                                                            <ul class="nav navbar-nav post-nav">
                                                                <li><a href="#"><i class="fa fa-clock-o"></i>${Adto.created }</a></li>
<c:if test="${sessionScope.member.userId==Adto.userId || sessionScope.member.userId=='admin'}">   
		     														<li><a href="#" onclick='deleteReplyAnswer("${Adto.replyNum}", "${Adto.answer}");'>삭제</a></li>
</c:if>
                                                            </ul>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
	</c:forEach>
</c:if>

 --%>