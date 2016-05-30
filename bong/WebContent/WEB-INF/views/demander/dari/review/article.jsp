<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<style type="text/css">
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
	/* countRevLike(31);  */
});
 
function listPage(page) {
	var url="<%=cp%>/demander/index/review/listReply";
	var num="${dto.serviceReviewIdx}";
	$.post(url, {num:num, pageNo:page}, function(data){
		$("#listReply").html(data);
	});
}

 //좋아요/싫어요 개수
 function countRevLike(serviceReviewIdx) {
	var url="<%=cp%>/demander/index/review/countLike";
	$.post(url, {abc:serviceReviewIdx}, function(data){
		var likeCountId="#likeCount"+serviceReviewIdx;
		var likeCount=data.likeCount;
		//alert(likeCount+"zz");
		$(likeCountId).html(likeCount);
	}, "JSON");
}
//좋아요추가
function sendLike(serviceReviewIdx) {
	var uid="${sessionScope.member.userId}";

	alert("alert");
	var params="serviceReviewIdx="+serviceReviewIdx;
	
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/demander/index/review/sendLike"
		,data:params
		,dataType:"json"
		,success:function(data) {
			var state=data.state;
			countRevLike(serviceReviewIdx);
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}

//댓글 추가
function sendReply() {
	var uid="${sessionScope.member.userId}";
	

	var num="${dto.serviceReviewIdx}"; // 해당 게시물 번호
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
		,url:"<%=cp%>/demander/index/review/createdReply"
		,data:params
		,dataType:"json"
		,success:function(data) {
			$("#replyContent").val("");
			
			var state=data.state;
			if(state=="true") {
				listPage(1);
			} else if(state=="false") {
				alert("댓글을 등록하지 못했습니다. !!!");
			} 
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}

 

 //댓글 삭제
function deleteReply(replyNum, page) {
	var uid="${sessionScope.member.userId}";
	
	
	if(confirm("게시물을 삭제하시겠습니까 ? ")) {	
		var url="<%=cp%>/demander/index/review/deleteReply";
		$.post(url, {replyNum:replyNum, mode:"reply"}, function(data){
		        var state=data.state;
					listPage(page);
		}, "json");
	}
} 

//-------------------------------------
function deleteReview() {
<c:if test="${sessionScope.member.userId=='admin' || sessionScope.member.userId==dto.userId}">
  var num = "${dto.serviceReviewIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/demander/index/review/delete?" + params;

  if(confirm("위 자료를 삭제 하시 겠습니까 ? "))
  	location.href=url;
</c:if>

<c:if test="${sessionScope.member.userId!='admin' && sessionScope.member.userId!=dto.userId}">
  alert("게시물을 삭제할 수  없습니다.");
</c:if>
}

function updateReview() {
<c:if test="${sessionScope.member.userId==dto.userId}">
  var num = "${dto.serviceReviewIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/demander/index/review/update?" + params;

  location.href=url;
</c:if>

<c:if test="${sessionScope.member.userId!=dto.userId}">
 alert("게시물을 수정할 수  없습니다.");
</c:if> 
}
</script>
    <section id="blog-details" class="padding-top">
               <div class="col-md-12 col-sm-12">
                            <div class="single-blog blog-details two-column">
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="#">${dto.subject}|${dto.serviceReviewIdx}</a></h2>
                                    <h3 class="post-author"><a href="#">${dto.userName}</a></h3>
                                    <p>${dto.content}</p>
                                    <div class="post-bottom overflow">
                               
                                    
                                        <ul class="nav navbar-nav post-nav">
                                        
                                         	<li onclick="sendLike('${dto.serviceReviewIdx}')"><a href="#"><i class="fa fa-thumbs-o-up"></i>좋아요 <span id="likeCount${dto.serviceReviewIdx}">${dto.likeCount}</span></a></li> 
                                                                                        
                                            <li><a href="#"><i class="fa fa-comments"></i>댓글수  ${dto.replyCount}</a></li>
                                            <li><a href="#"><i class="fa fa-clock-o"></i>${dto.created}</a></li>
                                        </ul>
                                    </div>
                                    
                                   <%--   <c:forEach var="vo" items="${listFile}">
                                         <div class='file-list-item'><a href="${blogUrl}/download?fileNum=${vo.fileNum}">${vo.originalFilename}</a></div>
                                   </c:forEach>   --%>
                                    
                                    
                              
                              <c:if test="${listFile.size()>0}">
                                    <div class="post-bottom overflow" style="margin-top: 0px; font-size:9pt;">
                                    <c:forEach var="vo" items="${listFile}">
                                  			<a href="<%=cp%>/demander/index/review/download?num=${vo.serviceReviewIdx}&fileNum=${vo.serviceFileIdx}"><span class="fa fa-download"></span>${vo.originalFilename}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                                    </c:forEach>
                                    </div>
                               </c:if>
                               
                               <c:if test="${not empty preReadDto }">
                                    <div class="post-bottom overflow" style="margin-top: 0px">
                               
                                  			<a href="<%=cp%>/demander/index/review/article?${params}&num=${preReadDto.serviceReviewIdx}">이전글 : ${preReadDto.subject}</a>
                              
                                    </div>
                                </c:if>
                                <c:if test="${not empty nextReadDto }">    
                                    <div class="post-bottom overflow" style="margin-top: 0px">
                              
                                  			<a href="<%=cp%>/demander/index/review/article?${params}&num=${nextReadDto.serviceReviewIdx}">다음글 : ${nextReadDto.subject}</a>
                                
                                    </div>
                               </c:if>
                               <div>
                               		<div style="float:left; padding-top: 20px; padding-right: 10px">
                              				<span class="item-click" id="reply-open-close">댓글 ▼</span>&nbsp;<span id="postReplyCountView" class="item-title" style="color:#424951">(${dto.replyCount})</span>
                     				</div>
                                  	<div style="float:left; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="javascript:location.href='<%=cp%>/demander/index/review/list?${params}';"> 목록보기 <span class="fa fa-list"></span></button>
                  					</div>
                                      
                  					
                                    <div style="float:right; padding-top: 10px;padding-bottom: 10px;">
                      					<button type="button" class="btn btn-info" style="padding:10px 15px ; color:white; border:none;" onclick="updateReview();"> 수정 <span class="fa fa-pencil"></span></button>
                  					</div>
                  					<div style="float:right; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="deleteReview();"> 삭제 <span class="fa fa-times"></span></button>
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
                      <button type="button" class="btn btn-info" style="padding:10px 15px ; color:white; border:none;" onclick="sendReply();"> 댓글등록 <span class="fa fa-pencil"></span></button>
                  </div>           
              
              </div>
          
              <div id="listReply"></div>
              
          </div>                 
                                </div><!--/Response-area-->
                                </div>
                            </div>
                        </div>   
    </section>