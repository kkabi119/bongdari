<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<style type="text/css">
.modal-body{
}
.col-md-9{
			margin-top:-25px;
		}
/* 수정삭제는 관리자만 보이도록한다 
 */
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

.icon-wrapper:hover{
	background-color: gray;
}
.modal-body{
 background-color: #F5F5F5;
}
</style>

<script type="text/javascript">
$(function(){
	$("#listBtn_apply").click(function(){
		 
		var num = "${dto.clubApplyIdx}";
		var page = "${page}";
		var params = "num="+num+"&page="+page;
	
		$('#applyListModal .modal-body').load( "<%=cp%>/club/index/apply/applyList1?"+params, function() {
			 
				$('#applyListModal .modal-title').html('우리동아리 신청리스트');
				$('#applyListModal').modal('show');
			});		
	});
});
</script>
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
	var url="<%=cp%>/club/index/apply/listReply";
	var num="${dto.clubApplyIdx}";
		
	$.post(url, {num:num, pageNo:page}, function(data){
		$("#listReply").html(data);
		replyCount(num);
	});
}
//////////////////////////////////////////////////////////////////////////댓글 개수
function replyCount() {
	var num="${dto.clubApplyIdx}";// 해당 게시물 번호

	var url="<%=cp%>/club/index/apply/replyCount";
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

	var num="${dto.clubApplyIdx}"; // 해당 게시물 번호
	var content=$.trim($("#replyContent").val());
	if(! content ) {
		alert("내용을 입력하세요 ! ");
		$("#replyContent").focus();
		return false;
	}
	
	var params="num="+num;
	params+="&content="+content;
	params+="&answer=0";
	
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/club/index/apply/createdReply"
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


////////////////////////////////////////////////////// 댓글 삭 제 
function deleteReply(replyNum, page) {
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}
	
	if(confirm("댓글을 삭제하시겠습니까 ? ")) {	
		var url="<%=cp%>/club/index/apply/deleteReply";
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
//////////////////////////////////////// 댓글 좋 아 요 
//좋아요/싫어요 개수
function countLike(replyNum) {
		
	var url="<%=cp%>/apply/countLike";
	
	$.post(url, {replyNum:replyNum}, function(data){
		
		var likeCountId="#likeCount"+replyNum;
		
		var likeCount=data.likeCount;
		
		$(likeCountId).html("&nbsp; "+likeCount);
		
	}, "JSON");
}

/////////////////////////////////////////////////////////////////////////////////	좋아요/싫어요 추가
function insertReplyLike(replyNum) {
	
	var uid="${sessionScope.member.userId}";
	if(! uid) {
		login();
		return false;
	}

	var params="replyNum="+replyNum;
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/apply/insertReplyLike"
		,data:params
		,dataType:"json"
		,success:function(data) {
			
			var state=data.state;
			countLike(replyNum);
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
}

//////////////////////////////////////////////////////////////////// 게시물 삭제(관리자와 매니저만 가능..)
function deleteApply() {
<c:if test="${sessionScope.member.userId=='admin'}">
  
  var num = "${dto.clubApplyIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  
  var url = "<%=cp%>/club/index/apply/delete?" + params;

  if(confirm("위 자료를 삭제 하시 겠습니까 ? ")){
	  
  	location.href=url;
  }
  
</c:if>

<c:if test="${sessionScope.member.userId!='admin'}">
  alert("게시물을 삭제할 수  없습니다.");
</c:if>
}

</script>

	<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
		
   
    <div class="col-sm-10_2"  style="float:none; margin-left: auto; margin-right: auto;">

       <div class="body-title">
             <h3  style="font-size:30px;"> 봉사 신청<span style="margin-left:10px;color:#6D6D6D; font-size:15px;">  봉사를 신청할 수 있는 게시판입니다</span> </h3>
             
       </div>
                 
       </div>
       <hr style="margin-bottom:10px; margin-top:0px; border:1px solid #6D6D6D;">
       
       
      
      <div class="table-responsive" style="clear: both;">
           <div >
               <table class="table">
                    <thead >
                        <tr height="50">
                            <th style="color:#555;"colspan="7" class="bbs-subject" >
                                 ${dto.subject } &nbsp;
                                 <span style="color:#E0844F; margin-top:-10px;">
	                                 <c:if test="${dto.progress.equals('모집마감')}">
	                              	   <span class="label label-default" style="padding:8px 10px; border-radius:2px;">${dto.progress}</span>
	                                 </c:if>
	                                 <c:if test="${dto.progress.equals('모집중')}">
	                              	    <span class="label label-warning" style="padding:8px 10px; border-radius:2px;">${dto.progress}</span>
	                                 </c:if>
                                 </span>
                            </th>
                        </tr>
                   <thead>
                    <tbody>
                        <tr >
                        	<td bgcolor="#DFE6E8" style="color:black; border-top:none;text-align: left; height:45px; width:14%; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;	봉사일정
                        	</td>
                            <td style="text-align: left;height:45px; width:200px; ">${dto.startDay } ~ ${dto.endDay }</td>
                            
                            <td  bgcolor="#DFE6E8" style="color:black; border-top:none; text-align: left; width:14%;  ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사시간
                            </td>
                           <td style="text-align: left;  height:45px; width:150px; ">${dto.startTime } ~ ${dto.endTime }</td>
                         
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; border-top:none; text-align: left;  width:14%; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사요일
                           	</td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; ">${dto.volunDays }</td>
                            
                            
                        </tr>
                        <tr>
                        <td  bgcolor="#DFE6E8" style="color:black;; border-top:none;border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;모집기간
                           	</td>
                            <td  style="text-align: left;  height:45px; "> 2016-02-22 ~ 2016-02-25 </td>
                            
                             <td  bgcolor="#DFE6E8" style="color:black;border-top:none;text-align: left; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;모집인원
                           	</td>
                            <td  style="text-align: left; height:45px; "> 총 ${dto.maxNum}명 </td>
                            
                             	
                            <td bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left;  height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;신청인원
                            </td>
                            <td colspan="3"  style="text-align: left; height:45px; ">${dto.applyNum}명</td>
                        </tr>
                        
                        <tr>
                        <td  bgcolor="#DFE6E8" style="color:black;; border-top:none;border-top:none; text-align: left; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사분야
                           	</td>
                            <td style="text-align: left; height:45px; ">${dto.tsubject_parent} > ${dto.tsubject} </td>
                            
                             <td  bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사자 유형
                           	</td>
                            <td  style="text-align: left;height:45px; "> ${dto.volunteer_type } </td>
                            
                            <td bgcolor="#DFE6E8" style="color:black; border-top:none; text-align: left;height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;수요처
                            </td>
                            <td colspan="3"  style="text-align: left; height:45px; "><a href="#"> ${dto.serviceName}</a></td>
                             	
                           
                        </tr>
                        <tr>
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사장소
                            </td>
                            <td colspan="6" style="text-align: left; width:200px; height:45px; ">
                            	${dto.place } <a href="#" > [지도]</a>
                            </td>
                        </tr>
                      <tr>
                            <td colspan="7" style="border-top:2px solid #6D6D6D;  padding:50px 20px 50px 20px; line-height:20pt;">
                               ${dto.content }
                            </td>
                        </tr>
                        <tr>
                      	  <td colspan="7" style="border-top:none; ">
                       
                       			  <a class="test1" href="#" style="color:white; ">
	                       			<span style="margin-left:40%; align:center; background-color: #7ECAF1; " class="icon-wrapper" data-toggle="buttons">
	                                	 	
	                                	 	<img Id="listBtn"style=" width:35px; height:35px; background-size:cover; "src="<%=cp%>/res/images/myclub/edit.png" alt="">
	                                	 	
	                          		</span>
	                          		
                          		</a>
                          		<span style="background-color: #7ECAF1; align:center; margin-left:50px;"class="icon-wrapper"  data-toggle="buttons">
                                	<a style="color:white; "id="listBtn_apply" >
                                		 <img  style=" width:35px; height:35px; background-size:cover; "src="<%=cp%>/res/images/myclub/list (2).png" alt="">
									</a>
                          		</span>
                  		  </td>
                        </tr>   
                          <tr height="40" style="border:none;">
                	    </tr>
                           <tr height="45" style=" border-top: 2px solid #6D6D6D;  border-bottom: 2px solid #6D6D6D; ">
                     		 <td colspan="1" bgcolor="#DFE6E8" style=" color:black; text-align: left; " >
                                   &nbsp;&nbsp; <i class="fa fa-clock-o"></i> &nbsp;작성일 
                         	 </td>
                         	 <td  style="">
                         	         &nbsp;${dto.created}
                         	 </td>
                         	 <td colspan="1" bgcolor="#DFE6E8" style=" color:black; text-align: left; ">
                                      &nbsp;&nbsp;<i class="fa fa-clock-o"></i> &nbsp;수정일 
                         	 </td>
                         	 <td width="180" style="">
                         		  &nbsp; ${dto.modified}
                         	 </td>
                         	  <td colspan="" bgcolor="#DFE6E8" style=" color:black; text-align: left; ">
                                     &nbsp;&nbsp; <i class="fa fa-clock-o"></i> &nbsp;조회수 
                         	 </td>
                         	 <td colspan="" style="">
                         		  &nbsp; ${dto.hitCount }
                         	 </td>
                         </tr>
                         <c:if test="${not empty dto.saveFileName}">
                             <tr style=" border-top: 1px solid #6D6D6D;  border-bottom: 1px solid #6D6D6D;">
                                    <td class="post-bottom overflow" style="margin-top: 0px">
                                  			<a href="<%=cp%>/club/index/apply/download?num=${dto.clubApplyIdx}">
                                  				<span class="fa fa-download"></span> ${dto.originalFilename}
                                  			</a>
                                    </td>
                                 </tr>
                         </c:if>              
                                                
                      
               </table>
          </div>
           
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
                      <button type="button" class="btn btn-default" style="border-radius:0px; padding:15px 25px ; margin-bottom:0px; background-color:#3897f0; color:white; border:none;" onclick="sendReply();">
                       		등록 
                       </button>
                  </div>
                   </div>           
              </div>
          
          
          <div id="reply-content"  style="display:none; margin-top: 10px; margin-bottom: 10px;">
               
              
              <div id="listReply"></div>
          </div>
                         
          </div>
           
   	   <div class="table-responsive" style="clear: both;">
           <div >
               <table class="table">
            <c:if test="${not empty preReadDto }">
						 	<tr height="35">
						 	   <td colspan="1"bgcolor="#EEEEEE" align="center">이전글</td>
						    
							    <td colspan="6" align="left" style="border-bottom:1px solid #ddd; padding-left:10px;" colspan="3">
									<a href="<%=cp%>/club/index/apply/article?${params}&num=${preReadDto.clubApplyIdx}">
											${preReadDto.subject} 
									</a>
									<c:if test="${preReadDto.progress.equals('모집마감')}">
	                       				 	<span class="text-center" style="font-weight:bold; color:white; font-size:16px;"> 
	                       				 		<span class="label label-default" style="padding:5px;">${preReadDto.progress}</span>
	                       				 	</span>
	                       			</c:if>
	                       			<c:if test="${preReadDto.progress.equals('모집중')}">
	                       				 		<span class="text-center" style="font-weight:bold; color:white; font-size:16px;">
	                       				 			<span class="label label-warning" style="padding:5px;">${preReadDto.progress}</span>
	                       				 		</span>
	                       			</c:if>
									
								</td>
							</tr>
					 	</c:if>
					 <c:if test="${not empty nextReadDto }">    
                      <tr height="35" style="border-bottom:1px solid #ddd; ">
					    <td colspan="1"bgcolor="#EEEEEE" align="center">다음글</td>
					    <td colspan="6" align="left" style="padding-left:10px;" colspan="3">
							<a href="<%=cp%>/club/index/apply/article?${params}&num=${nextReadDto.clubApplyIdx}">
								 ${nextReadDto.subject}
							</a>							
							<c:if test="${nextReadDto.progress.equals('모집마감')}">
	                       				 	<span class="text-center" style="font-weight:bold; color:white; font-size:16px;"> 
	                       				 		<span class="label label-default" style="padding:5px;">${nextReadDto.progress}</span>
	                       				 	</span>
	                       	</c:if>
	                       	<c:if test="${nextReadDto.progress.equals('모집중')}">
	                       		<span class="text-center" style="font-weight:bold; color:white; font-size:16px;">
	                       			<span class="label label-warning" style="padding:5px;">${nextReadDto.progress}</span>
	                       		</span>
	                       	</c:if>
															
					   	 	</td>
						</tr>
						</c:if>
                   </tbody>
                   <tfoot  >
                    <tr height="10" style="border:none;">
                    </tr>
                    <tr >
                    <c:if test="${sessionScope.member.userId=='admin'}">   
					    <td style="padding-left:0px; border-top:none;"colspan="6" align="left">
   						 	<button type="button" class="btn btn-default" onclick="deleteApply();">삭제</button>
   						</td>
   					</c:if>
   						<td align="right" colspan="2" style="padding-right:0px; border-top:none;margin-bottom:30px; ">
					           <button style=""type="button" class="btn btn-default"  onclick="javascript:location.href='<%=cp%>/club/index/apply/list?${params}';">목록으로</button>

					    </td>
					    </tr>
                   </tfoot>
              </table>
          </div></div>
      </div>
 	</div>
</div>

<!-- 추가 또는 보기 모달창 div -->
	<div class="modal fade" id="applyListModal" tabindex="-1" role="dialog" aria-labelledby="scheduleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" style="width:800px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="scheduleModalLabel" style="font-family: 나눔고딕, 맑은 고딕, sans-serif; font-weight: bold;">리스트</h4>
	      </div>
	      <div class="modal-body"></div>
	    </div>
	  </div>
	</div>