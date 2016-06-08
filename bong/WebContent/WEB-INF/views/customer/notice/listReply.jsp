<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>
<style type="text/css">
	a{
		cursor: pointer;
	}
	
	p{
		color:black;
	}
</style>
<c:if test="${dataCount!=0}">
<script type="text/javascript">
// 댓글별 답글 리스트
  function listAnswer(answer) {
	alert(answer);
	var listReplyAnswerId="#listReplyAnswer"+answer;
	var url="<%=cp%>/notice/listReplyAnswer";
	$.post(url, {answer:answer}, function(data){
		$(listReplyAnswerId).html(data);
	});
}

// 댓글별 답글 갯수
function countAnswer(answer) {
	var url="<%=cp%>/notice/replyCountAnswer";
	
	$.post(url, {answer:answer}, function(data){
		var count="("+data.count+")";
		var answerCountId="#answerCount"+answer;		
		$(answerCountId).html(count);
		$(answerGlyphiconId).removeClass("glyphicon-triangle-bottom");
		$(answerGlyphiconId).addClass("glyphicon-triangle-top");
	}, "JSON");
}

// 댓글별 답글 폼
function replyAnswerLayout(replyNum) {
	var id="#replyAnswerLayout"+replyNum;
	var replyContent="#replyContent"+replyNum;
	var answerGlyphiconId="#answerGlyphicon"+replyNum;
	
	if($(id).is(':hidden')) {
		$("[id*=replyAnswerLayout]").hide();
		
		$("[id*=answerGlyphicon]").each(function(){
			$(this).removeClass("glyphicon-triangle-top");
			$(this).addClass("glyphicon-triangle-bottom");
		});
		
		listAnswer(replyNum);
		countAnswer(replyNum);
		
		$(id).show();
		$(answerGlyphiconId).removeClass("glyphicon-triangle-bottom");
		$(answerGlyphiconId).addClass("glyphicon-triangle-top");
	}  else {
		$(id).hide();
		$(answerGlyphiconId).removeClass("glyphicon-triangle-top");
		$(answerGlyphiconId).addClass("glyphicon-triangle-bottom");
	}
}


function sendReplyAnswer(num, replyNum) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	var rta="#replyContent"+replyNum;
	var content=$.trim($(rta).val());
	if(! content ) {
		alert("내용을 입력하세요 !!!\n");
		$(rta).focus();
		return false;
	}
	
	var params="num="+num;
	params+="&content="+content;
	params+="&answer="+replyNum;
	
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/notice/createdReply"
		,data:params
		,dataType:"json"
		,success:function(data) {
			$(rta).val("");
			
  			var state=data.state;
			if(state=="true") {
				listAnswer(replyNum);
				countAnswer(replyNum);
			} else if(state=="false") {
				alert("답글을 등록하지 못했습니다. !!!");
			} else if(state=="loginFail") {
				login();
			}
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
} 

// 댓글별 답글 삭제
function deleteReplyAnswer(replyNum, answer) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	if(confirm("게시물을 삭제하시겠습니까 ? ")) {	
		var url="<%=cp%>/notice/deleteReply";
		$.post(url, {replyNum:replyNum, mode:"answer"}, function(data){
		        var state=data.state;
				if(state=="loginFail") {
					login();
				} else {
				    listAnswer(answer);
				    countAnswer(answer);
				}
		}, "json");
	}
} 
</script>
<c:forEach var="Rdto" items="${listReply}">
				<ul class="media-list">

   						<li class="media">
                                            <div class="post-comment">
                                                <a class="pull-left" href="#">
                                                    <img class="media-object" src="images/blogdetails/2.png" alt="">
                                                </a>
                                                <div class="media-body" style="padding-bottom: 10px">
                                                    <span><i class="fa fa-user"></i>Posted by <a href="#">${Rdto.userName}</a></span>
                                                    <p>${Rdto.content}</p>
                                                    <ul class="nav navbar-nav post-nav" style="float: right;">
                                                        <li  style="color:#0099AE"><i class="fa fa-clock-o"></i>${Rdto.created}</li>
                                                        <li><a onclick="replyAnswerLayout('${Rdto.replyNum}');"><i class="fa fa-reply"></i>Answer</a></li>
                        <c:if test="${sessionScope.member.userId==Adto.userId || sessionScope.member.userId=='admin'}">   
		     											<li><a onclick='deleteReply("${Rdto.replyNum}", "${pageNo}");' style="color:#C03035">삭제</a></li>
						</c:if>
                                                    </ul>
                                                    
                                                </div>
                                            </div>
                                            <div id="replyAnswerLayout${Rdto.replyNum}" style="display: none;">
                                            <div style="clear: both; margin-top:10px; padding: 5px; border-top: #d5d5d5 solid 1px;">
                									<textarea id="replyContent${Rdto.replyNum}" class="form-control" rows="3" required="required"></textarea>
           									 </div>
           									 <div style="text-align: right; padding-top: 10px;">
                      								<button type="button" class="btn btn-info" style="padding:10px 15px ; color:white; border:none;" onclick="sendReplyAnswer('${Rdto.num}','${Rdto.replyNum}')"> Answer <span class="fa fa-pencil"></span></button>
                 							 </div>
                 							 <div id="listReplyAnswer${Rdto.replyNum}" style="padding-top: 5px;"></div>                                       
                                          </div>
                                        </li>
                                        </ul>
</c:forEach>
</c:if>              

              
<div style="clear: both; padding-top: 10px; text-align: center;">
    ${paging}
</div>
