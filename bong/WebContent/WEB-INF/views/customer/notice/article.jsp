<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<style type="text/css">
li{
	color:#0099AE;
}
.item-click {
   color: #424951; display: inline-block; cursor: pointer;
}
.item-click:hover {
   color:#000000;
}
.item-title {
   color: #aaa; display: inline-block;
}

.file-list-content {
   position: relative;
   display: none;
}
.file-list{
  border:1px solid #2f3741;
  width: 250px;
  position: absolute;
  z-index:1000;
  padding:10px;
  background: #fefefe;
  
  top: 10px;
  left: 120px;
}
.file-list-item{
    width: 240px;
    text-overflow:ellipsis;
    white-space: nowrap;overflow:hidden;
    text-align: left;
}

.reply-write {
    border: #d5d5d5 solid 1px;
    padding: 10px;
    min-height: 50px;
}
</style>
<script type="text/javascript">
//댓글
$(function(){
	$("#reply-open-close").click(function(){
		  if($("#reply-content").is(':visible')) {
			  $("#reply-content").fadeOut(100);
			  $("#reply-open-close").text("댓글 ▼");
		  } else {
			  $("#reply-content").fadeIn(100);
			  $("#reply-open-close").text("댓글 ▲");
		  }
	});
})



 $(function(){
	listPage(1);
});
 
function listPage(page) {
	var url="<%=cp%>/notice/listReply";
	var num="${dto.noticeIdx}";
	$.post(url, {num:num, pageNo:page}, function(data){
		$("#listReply").html(data);
	});
}

<%-- 
function login() {
	location.href="<%=cp%>/member/login";
}
 --%>
//댓글 추가
function sendReply() {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}

	var num="${dto.noticeIdx}"; // 해당 게시물 번호
	var content=$.trim($("#replyContent").val());
	if(! content ) {
		alert("내용을 입력하세요 !!! ");
		$("#replyContent").focus();
		return false;
	}
	
	var params="num="+num;
	params+="&content="+content;
	params+="&answer=0";
	
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/notice/createdReply"
		,data:params
		,dataType:"json"
		,success:function(data) {
			$("#replyContent").val("");
			
			var state=data.state;
			if(state=="true") {
				listPage(1);
				replyCount();
			} else if(state=="false") {
				alert("댓글을 등록하지 못했습니다. !!!");
			} else if(state=="loginFail") {
				login();
			}
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}
//댓글 개수
function replyCount() {
	var num="${dto.noticeIdx}";// 해당 게시물 번호
	var url="<%=cp%>/notice/replyCount";
	$.post(url, {num:num}, function(data){
		var count=data.count;
		$("#replyCountView").text("("+count+")");
	}, "JSON");
}
<%-- //좋아요/싫어요 개수
function countLike(replyNum) {
	var url="<%=cp%>/bbs/countLike";
	$.post(url, {replyNum:replyNum}, function(data){
		var likeCountId="#likeCount"+replyNum;
		var disLikeCountId="#disLikeCount"+replyNum;
		var likeCount=data.likeCount;
		var disLikeCount=data.disLikeCount;
		
		$(likeCountId).html(likeCount);
		$(disLikeCountId).html(disLikeCount);
	}, "JSON");
}

//좋아요/싫어요 추가
function sendLike(replyNum, replyLike) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}

	var msg="게시물이 마음에 들지 않으십니까 ?";
	if(replyLike==1)
		msg="게시물에 공감하십니까 ?";
	if(! confirm(msg))
		return false;
	
	var params="replyNum="+replyNum;
	params+="&replyLike="+replyLike;

	$.ajax({
		type:"POST"
		,url:"<%=cp%>/bbs/replyLike"
		,data:params
		,dataType:"json"
		,success:function(data) {
			
			var state=data.state;
			if(state=="true") {
				countLike(replyNum);
			} else if(state=="false") {
				alert("좋아요/싫어요는 한번만 가능합니다. !!!");
			} else if(state=="loginFail") {
				login();
			}
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}
--%>
//댓글 삭제
function deleteReply(replyNum, page) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	if(confirm("게시물을 삭제하시겠습니까 ? ")) {	
		var url="<%=cp%>/notice/deleteReply";
		$.post(url, {replyNum:replyNum, mode:"reply"}, function(data){
		        var state=data.state;
				if(state=="loginFail") {
					login();
				} else {
					listPage(page);
					replyCount();
				}
		}, "json");
	}
}

//-------------------------------------
function deleteNotice() {
<c:if test="${sessionScope.member.userId=='admin' || sessionScope.member.userId==dto.userId}">
  var num = "${dto.noticeIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/notice/delete?" + params;

  if(confirm("위 자료를 삭제 하시 겠습니까 ? "))
  	location.href=url;
</c:if>    
<c:if test="${sessionScope.member.userId!='admin' && sessionScope.member.userId!=dto.userId}">
  alert("게시물을 삭제할 수  없습니다.");
</c:if>
}

function updateNotice() {
<c:if test="${sessionScope.member.userName==dto.userName || sessionScope.member.userName=='관리자'}">
  var num = "${dto.noticeIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/notice/update?" + params;
  location.href=url;
 
</c:if>

<c:if test="${sessionScope.member.userId!=dto.userId || sessionScope.member.userName!='관리자'}}">
 alert("게시물을 수정할 수  없습니다.");
</c:if> 
}
</script>
    <section id="blog-details" class="padding-top">
               <div class="col-md-12 col-sm-12">
                            <div class="single-blog blog-details two-column">
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="#">${dto.subject}</a></h2>
                                    <h3 class="post-author"><a href="#">${dto.userName}</a></h3>
                                    <p>${dto.content}</p>
                                    <div class="post-bottom overflow">
                                        <ul class="nav navbar-nav post-nav">
                                            <li><i class="fa fa-clock-o"></i> ${dto.created}</li>
                                        </ul>
                                    </div>
                               <c:if test="${not empty dto.saveFilename}">
                                    <div class="post-bottom overflow" style="margin-top: 0px">
                                  			<a href="<%=cp%>/notice/download?num=${dto.noticeIdx}"><span class="fa fa-download"></span> ${dto.originalFilename}</a>
                                    </div>
                               </c:if>
                               <c:if test="${not empty preReadDto }">
                                    <div class="post-bottom overflow" style="margin-top: 0px">
                               
                                  			<a href="<%=cp%>/notice/article?${params}&num=${preReadDto.noticeIdx}">이전글 : ${preReadDto.subject}</a>
                              
                                    </div>
                                </c:if>
                                <c:if test="${not empty nextReadDto }">    
                                    <div class="post-bottom overflow" style="margin-top: 0px">
                              
                                  			<a href="<%=cp%>/notice/article?${params}&num=${nextReadDto.noticeIdx}">다음글 : ${nextReadDto.subject}</a>
                                
                                    </div>
                               </c:if>
                               <div>
                               		<div style="float:left; padding-top: 20px; padding-right: 10px">
                              				<span class="item-click" id="reply-open-close">댓글 ▼</span>&nbsp;<span id="replyCountView" class="item-title" style="color:#424951">(${replyCount})</span>
                     				</div>
                                  	<div style="float:left; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="javascript:location.href='<%=cp%>/notice/list?${params}';"> 목록보기 <span class="fa fa-list"></span></button>
                  					</div>
                                     
                                    <div style="float:right; padding-top: 10px;padding-bottom: 10px;">
                      					<button type="button" class="btn btn-info" style="padding:10px 15px ; color:white; border:none;" onclick="updateNotice();"> 수정 <span class="fa fa-pencil"></span></button>
                  					</div>
                  					<div style="float:right; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="deleteNotice();"> 삭제 <span class="fa fa-times"></span></button>
                  					</div>
                  				</div>
                  					
                  					
                                    <div class="response-area" style="clear: both">
                <!-- 댓글 폼 및 리스트-->                    
               <div id="reply-content"  style="display:none; margin-top: 10px; margin-bottom: 10px;">
               
              <div class="reply-write" >
                  
                  <div style="clear: both; ">
                        <div style="float: left; "><span style="font-size:23px;">COMMENTS</span><span></span></div>
                        <div style="float: right; text-align: right;"></div>
                  </div>
                  
                  <div style="clear: both; padding-top: 30px; ">
                      <textarea id="replyContent" class="form-control" rows="3" required="required"></textarea>
                  </div>
                  
                  <div style="text-align: right; padding-top: 10px;">
                      <button type="button" class="btn btn-info" style="padding:10px 15px ; color:white; border:none;" onclick="sendReply();"> REPLY <span class="fa fa-pencil"></span></button>
                  </div>           
              
              </div>
          
              <div id="listReply"></div>
              
          </div>                 
                                </div><!--/Response-area-->
                                </div>
                            </div>
                        </div>   
    </section>