<%@ page contentType="text/html; charset=UTF-8"%>
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
                                                            <img class="media-object" src="<%=cp%>/uploads/memImg/${Adto.saveFilename}" alt="">
                                                        </a>
                                                        <div class="media-body" style="padding-bottom: 10px">
                                                            <span><i class="fa fa-user"></i>Posted by <a href="#">${Adto.userName }</a></span>
                                                            <p>${Adto.content}</p>
                                                            <ul class="nav navbar-nav post-nav" style="float: right;">
                                                                <li><a href="#"><i class="fa fa-clock-o"></i>${Adto.created }</a></li>
<c:if test="${sessionScope.member.userIdx==Adto.userIdx || sessionScope.member.userIdx==10}">   
		     														<li><a onclick='deleteReplyAnswer("${Adto.cfrIdx}", "${Adto.answer}");' style="color:#FF7218">삭제</a></li>
</c:if>
                                                            </ul>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
	</c:forEach>
</c:if>