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
	
	.btn{
		border-radius:0px;
		padding:9px 12px;
	}
	
	.post-comment{
		border-top:1px solid #ccc;
		min-height:150px;
		padding-top:12px;
		padding-left:20px;
	}
	.post-comment .pull-left img{
		margin-right:40px;
		border:1px solid #ccc;
	}
	
</style>

<c:if test="${dataCount!=0}">

<script type="text/javascript">

////////////////////////////////////////////////////////////////////////////////대댓글 폼
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

/////////////////////////////////////////////////////////////////////////////// 댓글별 답글 리스트 불러오기 
  function listAnswer(answer) {
	var listReplyAnswerId="#listReplyAnswer"+answer;
	
	var url="<%=cp%>/club/index/apply/listReplyAnswer";
	
	$.post(url, {answer:answer}, function(data){
		
		$(listReplyAnswerId).html(data);
	});
}

////////////////////////////////////////////////////////////////////////// 대댓글 갯수
function countAnswer(answer) {
	
	var url="<%=cp%>/club/index/apply/replyCountAnswer";
	$.post(url, {answer:answer}, function(data){
		var count="("+data.count+")";
		var answerCountId="#answerCount"+answer;
		var answerGlyphiconId="#answerGlyphicon"+answer;
		
		$(answerCountId).html(count);
		$(answerGlyphiconId).removeClass("glyphicon-triangle-bottom");
		$(answerGlyphiconId).addClass("glyphicon-triangle-top");
	}, "JSON");
}

///////////////////////////////////////////////////////////////////////////////////////////////// 대댓글 쓰기 
function sendReplyAnswer(num, replyNum) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	var rta="#replyContent"+replyNum;
	var content=$.trim($(rta).val());
	if(! content ) {
		alert("내용을 입력하세요!\n");
		$(rta).focus();
		return false;
	}
	
	var params="num="+num;
	params+="&content="+content;
	params+="&answer="+replyNum;
	
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/club/index/apply/createdReply"
		,data:params
		,dataType:"json"
		,success:function(data) {
			$(rta).val("");
			
  			var state=data.state;
  			
			if(state=="true") {
				listAnswer(replyNum);
				countAnswer(replyNum);
			} 
			else if(state=="false") {
				alert("답글을 등록하지 못했습니다!");
			} else if(state=="loginFail") {
				login();
			}
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
} 

//////////////////////////////////////////////////////// 대댓글 삭제
function deleteReplyAnswer(replyNum, answer) {
	
	var uid="${sessionScope.member.userId}";
	
	if(! uid) {
		login();
		return false;
	}
	
	if(confirm("댓글을 삭제하시겠습니까 ? ")) {	
		var url="<%=cp%>/club/index/apply/deleteReply";
		
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
                                            <div class="post-comment" style="">
                                                <a class="pull-left" href="#" >
                                                    <img style=" width:137px; height:127px; background-size:cover; "class="media-object" src="<%=cp%>/res/images/blogdetails/2.png" alt="">
                                                </a>
                                                <div class="media-body" style="padding-bottom: 0px">
                                                    <span><i class="fa fa-user"></i><a href="#">${Rdto.userName}</a></span>
                                                  
                                                      <c:if test="${sessionScope.member.userId==Adto.userId || sessionScope.member.userId=='admin'}">   
		     											<span style="margin-right:0px; float:right; ">
		     												<a class="btn btn-default" onclick='deleteReply("${Rdto.replyNum}", "${pageNo}");' style="margin-top:-8px;color:#C03035; border:none;">
		     													삭제
		     												</a>
		     											</span>
		     											  <span  style="color:#888; float:right;"><i class="fa fa-clock-o"  style="color:#888"></i>${Rdto.created}</span>
						</c:if>
                                                    <p style="min-height:45px;">${Rdto.content}</p>
                                                    
                                                    
                                                </div>
                                                <ul class="nav navbar-nav post-nav" style="float: right; ">
                                                         <li>
                                                         <button type="button" class="btn btn-default btn-sm" onclick="sendLike('${Rdto.replyNum}')" 
                                                         		style="font-weight: bold; width:60px; color: #6D6D6D; padding: 10px 15px; ;">
                                                         		<span> 
                                                         			<img style=" width:15px; height:15px; background-size:cover; "src="<%=cp%>/res/images/like.png" alt="">
										                			
										                		</span> <span style="text-align:center;"id="likeCount${Rdto.replyNum}">&nbsp; ${Rdto.likeCount}</span>
										                </button>
										                </li>
                                                        <li style="margin-right:5px; margin-left:-50px">
                                                        	<button class="btn btn-default" onclick="replyAnswerLayout('${Rdto.replyNum}');" style="font-weight: bold; color: #6D6D6D;">
                                                        	답글 <span style="font-weight:bold; "id="answerCount${Rdto.replyNum}">(${Rdto.answerCount})</span>
                                                        	</button>
                                                        </li>
                      
                                                    </ul>
                                            </div>
                               <!-- 																			대댓글폼																								-->
                              <div id="replyAnswerLayout${Rdto.replyNum}" 
                                    style="margin-top:10px; display: none; background-color:#EEE; padding:15px; padding-right:25px; padding-left:25px; margin-bottom:0px;
                                       		 border-top:2px solid #ccc; border-bottom:2px solid #ccc;">
                                        
                                            <div style="clear: both;  ">
                                         	   <div  style="font-size:17px; color:#6D6D6D; margin-bottom:-15px; margin-left:15px;" > COMMENTS </div>&nbsp;
                                           
                                         
                 						<div id="listReplyAnswer${Rdto.replyNum}" style="padding-top: 5px;"></div>                                       
                                           <div style="margin-top:10px; border-top:1px solid #ccc; "></div>
                                         <div style="background-color:white; margin-top:15px;">
	                                            <div style="border: 1px solid #A2A2A2; ">
	                                            
	                									<textarea id="replyContent${Rdto.replyNum}" class="form-control" rows="3" required="required"></textarea>
	           									 		 <div style="text-align: right; padding-top: 0px;">
		                      								<button type="button" class="btn btn-info" style=" padding:13px 20px ; color:white; border:none;" 
		                      											onclick="sendReplyAnswer('${Rdto.num}','${Rdto.replyNum}')">
		                      								 등록 
		                      								 </button>
		                 							 	</div>
		                 							 	
		                 							</div>
                 							 </div>
                 							  
                                         </div>
                                          </div>
                                        </li>
                                        </ul>
</c:forEach>
</c:if>              

              
<div style="clear: both; border-top:1px solid #ccc;padding-top: 10px; text-align: center;">
    ${paging}
</div>
