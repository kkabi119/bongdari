<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<style type="text/css">

.col-md-9{

}
.col-md-7{

	width: 44%;
}
.single-blog.blog-details .post-content{
	padding-top:0px;
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

/* .reply-write {
    border: #d5d5d5 solid 1px;
    padding: 10px;
    min-height: 50px;
} */

.bbs-reply {
    border-top: #3897f0 solid 2px; 
    border-bottom: #3897f0 solid 2px; padding:15px;
    margin-bottom:70px;
}

.bbs-reply-write {   
   border-bottom: #ddd solid 2px; 
    padding: 10px;
    min-height: 50px;
}

.table>thead>tr>th{

	font-size:20px; text-align: center;
	 padding:14px; 
	 border-bottom: 2px solid #6D6D6D;

}
.icon-wrapper{
box-shadow:none;
background-color: #4FCCCD;
}
.icon-wrapper:hover{
	background-color:#4FCCCD;
}

.table>tbody>tr>td{

padding-top: 13px;
}

.form-control{
	border-radius:0px;
	height:120px;
	font-weight:lighter;
	font-size:15px;
	border:none;
	border-bottom: 1px solid #ddd;
	resize:none;
}
.form-control:hover, .form-control:focus{

	border-bottom: 1px solid #999;'
}

.btn {
	border-radius:2px;
	padding: 9px 15px;
}

.single-blog.blog-details .post-bottom{
	margin-top:10px;
}
.post-nav{
	padding:10px;
}
.post-nav li a{
	color:#6d6d6d;
}
.post-nav li a i{
	color:#3897f0;
}
</style>
<script type="text/javascript">
///////////////////////////////////////////////////////////////		댓글관련
/////////////////////		페이지 틀자마자 실행되는 함수들
$(function(){
		$("#reply-open-close").click(function(){
			
			if($("#reply-content").is(':visible')) {
				$("#reply-content").fadeOut(100);
				$("#reply-open-close").text("COMMENTS ▼");
			} else {
				$("#reply-content").fadeIn(100);
				$("#reply-open-close").text("COMMENTS ▲");
			}
		});
})

$(function(){
	listPage(1);
});

function listPage(page) {
	var url="<%=cp%>/club/${clubSeq}/review/listReply";
	var num="${dto.clubReviewIdx}";
	
	$.post(url, {num:num, pageNo:page}, function(data){
		
		$("#listReply").html(data);
			replyCount(num);
		});
}
//////////////////////////////////////////////////////////////////////////댓글 개수
function replyCount() {
	var num="${dto.clubReviewIdx}";// 해당 게시물 번호
	
	var url="<%=cp%>/club/${clubSeq}/review/replyCount";
	$.post(url, {num:num}, function(data){
	
		var count=data.count;
		$("#replyCountView").text(""+count+"개");
		
	}, "JSON");
}
/////////////////////////////////////////////////////////////////////////// 댓글 추가
function sendReply() {
	
		var uid="${sessionScope.member.userId}";
		if(! uid) {
			login();
			return false;
		}

		var num="${dto.clubReviewIdx}"; // 해당 게시물 번호
		var content= $.trim($("#replyContent").val());
		
		if(! content ) {
			alert("내용을 입력하세요 ! ");
			$("#replyContent").focus();
			return false;
		}
		
		var params="num="+num;
		params+="&content="+content;
		params+="&answer=0";
		
		//dto에 clubReviewId, content, answer을 담아서 보내고 > 컨트롤러에서 session의 useridx를 담아 mapper로 보내는고
		$.ajax({
			type:"POST"
			,url:"<%=cp%>/club/${clubSeq}/review/createdReply"
			,data:params
			,dataType:"json"
			,success:function(data) {
				$("#replyContent").val("");
			
				var state=data.state;
				if(state=="true") {
				
					listPage(1);
					$("#reply-content").fadeIn(100);
					$("#reply-open-close").text("COMMENTS ▲");
					
				} else if(state=="false") {
					alert("댓글을 등록하지 못했습니다 !");
				} else if(state=="loginFail") {
					login();
				}
			}
			
			,error:function(e) {
				alert(e.responseText);
			}
		});
}


//////////////////////////////////////////////////////댓글 삭 제 
function deleteReply(replyNum, page) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	if(confirm("댓글을 삭제하시겠습니까 ? ")) {	
		var url="<%=cp%>/club/${clubSeq}/review/deleteReply";
		$.post(url, {replyNum:replyNum, mode:"reply"}, function(data){
			
			var state=data.state;
			if(state=="loginFail") {
				login();
			} 
			else {
				listPage(page);
				replyCount();
			}
		}, "json");
	}
}

//---------------------------------------------------------		댓 글의 좋 아 요   //---------------------------------------------------------------------------
// 댓글 좋아요 카운트 
function replyCountLike(replyNum) {
		
	var url="<%=cp%>/club/${clubSeq}/review/replyCountLike";
		
	$.post(url, {replyNum:replyNum}, function(data){
		
		var replyLikeCount="#replyLikeCount"+replyNum;
		var likeCount=data.likeCount;
		
		$(replyLikeCount).html("&nbsp; "+likeCount);
		
	}, "JSON");
}

/////////////////////////////////////////////////////////////////////////////////	댓글의 좋아요/싫어요 추가
function insertReplyLike(replyNum) {
	
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	var params="replyNum="+replyNum;

	$.ajax({
		type:"POST"
		,url:"<%=cp%>/club/${clubSeq}/review/insertReplyLike"
		,data:params
		,dataType:"json"
		,success:function(data) {
			
			var state=data.state;
			replyCountLike(replyNum);
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}



////////////////////////////////////////////////////////////////////////////////////					게 시 글의  좋 아 요 처리 

///////////////////////////////////////////		좋아요/싫어요 개수
function countLike(clubReviewIdx) {
	
	var url="<%=cp%>/club/${clubSeq}/review/countLike";
	
	$.post(url, {clubReviewIdx:clubReviewIdx}, function(data){
	
		var likeCountId="#likeCount"+clubReviewIdx;
		var likeCount=data.likeCount;
		
		$(likeCountId).html(""+likeCount);

	}, "JSON");
}

///////////////////////////////////////////		좋아요/싫어요 추가
function sendLike(clubReviewIdx) {
	
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	var params="clubReviewIdx="+clubReviewIdx;
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/club/${clubSeq}/review/sendLike"
		,data:params
		,dataType:"json"
		,success:function(data) {
		
			var state=data.state;
		
			countLike(clubReviewIdx);
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}

//-------------------------------- 게시물 삭제 ----------------------------------------------------------
function deleteReview() {
<c:if test="${sessionScope.member.userId=='admin' || sessionScope.member.userIdx==dto.userIdx}">
  var num = "${dto.clubReviewIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/club/${clubSeq}/review/delete?" + params;

  if(confirm("이 글을 삭제 하시겠습니까 ? "))
  	location.href=url;
</c:if>

<c:if test="${sessionScope.member.userId!='admin' && sessionScope.member.userId!=dto.userId}">
  alert("작성자만 게시물을 삭제할 수 있습니다!");
</c:if>
}

//-------------------------------- 게시물 수정 ----------------------------------------------------------
function updateReview() {
<c:if test="${sessionScope.member.userId==dto.userId}">
  var num = "${dto.clubReviewIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/club/${clubSeq}/review/update?" + params;

  location.href=url;
</c:if>

<c:if test="${sessionScope.member.userId!=dto.userId}">
 	alert("게시물을 수정할 수  없습니다.");
</c:if> 
}
</script>



    <section id="blog-details">
               <div class="col-md-12 col-sm-12">
                            <div class="single-blog blog-details two-column">
                                <div class="post-content overflow">
                                    <h2 class="post-title bold"><a href="#" style="line-height: 37px; font-weight:bold;">${dto.subject}	</a></h2>
                                    
								    <hr style="margin-top:0px; margin-bottom:15px; width:98%; border-top:2px solid #DADADA;">
                                    <h3 class="post-author" style=" margin-bottom:15px; "><a href="#">${dto.userName} &nbsp;&nbsp;&nbsp;| No.${dto.clubReviewIdx}	</a></h3>
                                    <div style="padding:20px 10px 20px 10px;">${dto.content}</div>
                                   
                                    <!-- <hr style="margin-left:5px; margin-top:20px; margin-bottom:0px; width:98%; border-top:2px solid #DADADA;">
                                     -->
                                    <div class="post-bottom overflow" >
                                                                   
                                        <ul class="nav navbar-nav post-nav" >
                                        
                                         	<li onclick="sendLike('${dto.clubReviewIdx}')"><a style="font-size:16px; font-weight:bold;" href="#"><i class="fa fa-thumbs-o-up"></i>
                                         			<span id="likeCount${dto.clubReviewIdx}">${dto.likeCount}</span></a></li> 
                                                                                        
                                            <li><a style="font-size:16px; font-weight:bold;" href="#"><i class="fa fa-clock-o"></i>${dto.created}</a></li>
                                        </ul>
                                    </div>
                                    
                                   <%--   <c:forEach var="vo" items="${listFile}">
                                         <div class='file-list-item'><a href="${blogUrl}/download?fileNum=${vo.fileNum}">${vo.originalFilename}</a></div>
                                   </c:forEach>   --%>
                          
                            
                  					
                  					
         <div class="response-area" style="clear: both">
               <div style="clear: both;  margin-bottom:30px; border-bottom:2px solid #6D6D6D;">
                  
                   <div style="float: left; margin-bottom:20px;margin-top:20px;">
                    <span class="item-click" id="reply-open-close" style="cursor:pointer; font-size:20px; color:#6D6D6D;" >COMMENTS ▼</span>&nbsp;
                	    <span id="replyCountView" class="item-title" style='color:#f0ad4e; font-size:20px; font-weight: bold;'> </span>
                     <div style="float: right; text-align: right;"></div>
                     
          		  </div>
         <div class="reply-write"  style="margin-bottom:20px;">
                  
                  <div style="clear: both; margin-top:0px; border: 1px solid #A2A2A2;">
                      <textarea id="replyContent" class="form-control" rows="4" required="required"  ></textarea>
                 
                  <div style="text-align: right; padding-top: 0px; margin-right:0px; ">
                      <button type="button" class="btn btn-default" style="border-radius:0px; padding:15px 25px ; margin-bottom:0px;
                      			 background-color:#3897f0; color:white; border:none;" onclick="sendReply();">
                       		등록 
                       </button>
                  </div>
                   </div>           
              </div>
          
          
          <div id="reply-content"  style="display:none; margin-top: 10px; margin-bottom: 10px;">
               
              
              <div id="listReply"></div>
          </div>
                         
          </div>
                               </div><!--/Response-area-->
                    </div>
               
               <div class="table-responsive" style="clear: both; margin-top:-20px; ">
           <div >
               <table class="table">
            <c:if test="${not empty preReadDto }">
						 	<tr height="45">
						 	   <td colspan="1"bgcolor="#EEEEEE" align="center">이전글</td>
						    
							    <td colspan="6" align="left" style="border-bottom:1px solid #ddd; padding-left:10px;" colspan="3">
									
                                  			<a href="<%=cp%>/club/${clubSeq}/review/article?${params}&num=${preReadDto.clubReviewIdx}">  ${preReadDto.subject}</a>
									
									
								</td>
							</tr>
					 	</c:if>
					 <c:if test="${not empty nextReadDto }">    
                      <tr height="45" style="border-bottom:1px solid #ddd; ">
					    <td colspan="1"bgcolor="#EEEEEE" align="center">다음글</td>
					    <td colspan="6" align="left" style="padding-left:10px;" colspan="3">
															
                              <a href="<%=cp%>/club/${clubSeq}/review/article?${params}&num=${nextReadDto.clubReviewIdx}"> ${nextReadDto.subject}</a>
                                
					   	 	</td>
						</tr>
						</c:if>
                   </tbody>
                  
              </table>
                    	<div style="float:left; padding-top: 10px;padding-bottom: 10px; padding-right: 5px; ">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="javascript:location.href='<%=cp%>/club/${clubSeq}/review/list?${params}';"> 목록 <span class="fa fa-list"></span></button>
                  					</div>
                                      
                  					
                                    <div style="float:right; padding-top: 10px;padding-bottom: 10px;">
                      					<button type="button" class="btn btn-warning" style="padding:10px 15px ; background:#3897f0; color:white; border:none;" onclick="updateReview();"> 수정 <span class="fa fa-pencil"></span></button>
                  					</div>
                  					<div style="float:right; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="deleteReview();"> 삭제 <span class="fa fa-times"></span></button>
                  					</div>
                  				</div>
          </div></div>
               </div>
               
           </div>   
    </section>