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
                                                            <img class="media-object" src="images/blogdetails/3.png" alt="">
                                                        </a>
                                                        <div class="media-body">
                                                            <span><i class="fa fa-user"></i>Posted by <a href="#">${Adto.userName}</a></span>
                                                            <p>${Adto.content}</p>
                                                            <ul class="nav navbar-nav post-nav">
                                                                <li><a href="#"><i class="fa fa-clock-o"></i>${Adto.created }</a></li>
<c:if test="${sessionScope.member.userId==Adto.userId || sessionScope.member.userId=='admin'}">   
		     														<li><a onclick='deleteReplyAnswer("${Adto.replyNum}", "${Adto.answer}");'>삭제</a></li>
</c:if>
                                                            </ul>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
	</c:forEach>
</c:if>


<c:if test="${not empty listReplyAnswer}">
    <c:forEach var="vo" items="${listReplyAnswer}">
        <div style="clear: both; border-top: #d5d5d5 solid 1px; margin-top: 7px; padding-top: 5px;">
            <div style="float: left;">${vo.userName} | ${vo.created }</div>
            <div style="float: right; text-align: rigth;">
<c:if test="${sessionScope.member.userId==vo.userId || sessionScope.member.userId=='admin'}">   
		     <a onclick='deleteReplyAnswer("${vo.replyNum}", "${vo.answer}");'>삭제</a>
</c:if>
<c:if test="${sessionScope.member.userId!=vo.userId && sessionScope.member.userId!='admin'}">   
		   | <a href='#'>신고</a>
</c:if>
            </div>
        </div>
        <div style="clear: both; padding: 5px 0 5px 0px;  min-height: 70px;">
            ${vo.content}
        </div>
    </c:forEach>
</c:if>