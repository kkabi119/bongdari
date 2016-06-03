<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
%>

<c:if test="${dataCount!=0}">
<script type="text/javascript">


$(function(){
	/* countRevLikeRe(29);
	countAnswer(29); */
});  

//댓글의 좋아요/싫어요 개수
function countRevLikeRe(replyNum) {
	var url="<%=cp%>/demander/index/review/countLikeReply";
	$.post(url, {replyNum:replyNum}, function(data){
		var likeCountReId="#likeCountRe"+replyNum;
		var likeCountRe=data.likeCount;
		$(likeCountReId).html(likeCountRe);
	}, "JSON");
	
}

//좋아요추가
function sendLikeRe(replyNum) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	var params="replyNum="+replyNum;
	

	$.ajax({
		type:"POST"
		,url:"<%=cp%>/demander/index/review/sendLikeReply"
		,data:params
		,dataType:"json"
		,success:function(data) {
			countRevLikeRe(replyNum);
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}

// 댓글별 답글 리스트
  function listAnswer(answer) {
	var listReplyAnswerId="#listReplyAnswer"+answer;
	var url="<%=cp%>/demander/index/review/listReplyAnswer";
	$.post(url, {answer:answer}, function(data){
		$(listReplyAnswerId).html(data);
	});
}

// 댓글별 답글 갯수
function countAnswer(answer) {
	var url="<%=cp%>/demander/index/review/replyCountAnswer";
	$.post(url, {answer:answer}, function(data){
		var count=data.count;
		var answerCountId="#answerCount"+answer;
		var answerGlyphiconId="#answerGlyphicon"+answer;
		
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
		,url:"<%=cp%>/demander/index/review/createdReply"
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
		var url="<%=cp%>/demander/index/review/deleteReply";
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
                                                    <img style="width: 137px; height: 127px; background-size:cover;" class="media-object" src="<%=cp%>/res/images/demander/demander1.jpg" alt="">
                                                </a>
                                                <div class="media-body">
                                                    <span><i class="fa fa-user"></i>Posted by <a href="#">${Rdto.userName}|${Rdto.replyNum}</a></span>
                                                    <p>${Rdto.content}</p>
                                                    <ul class="nav navbar-nav post-nav">
                                                        <li><a href="#"><i class="fa fa-clock-o"></i>${Rdto.created}</a></li>
                                                        <li><a href="#" onclick="replyAnswerLayout('${Rdto.replyNum}');"><i class="fa fa-reply"></i>답변&nbsp;<span id="answerCount${Rdto.replyNum}">${Rdto.answerCount}</span></a></li>
                                                        <li onclick="sendLikeRe('${Rdto.replyNum}')"><a href="#"><i class="fa fa-thumbs-o-up"></i>좋아요 <span id="likeCountRe${Rdto.replyNum}">${Rdto.likeCount}</span></a></li> 
                                                 <c:if test="${sessionScope.member.userId==Adto.userId || sessionScope.member.userId=='admin'}">   
		     											<li><a href="#" onclick='deleteReply("${Rdto.replyNum}", "${pageNo}");' style="color:#C03035">삭제</a></li>
												</c:if>
                                                        <%-- <li><button type="button" class="btn btn-xs btn-default" onclick="replyAnswerLayout('${Rdto.replyNum}');">답글</button></li> --%>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div id="replyAnswerLayout${Rdto.replyNum}" style="display: none;">
                                            <div style="clear: both; margin-top:10px; padding: 5px; border-top: #d5d5d5 solid 1px;">
                									<textarea id="replyContent${Rdto.replyNum}" class="form-control" rows="3" required="required"></textarea>
           									 </div>
           									 <div style="text-align: right; padding-top: 10px;">
                      								<button type="button" class="btn btn-info" style="padding:10px 15px ; color:white; border:none;" onclick="sendReplyAnswer('${Rdto.num}','${Rdto.replyNum}')"> 답글등록 <span class="fa fa-pencil"></span></button>
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