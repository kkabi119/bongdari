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
  z-${demander_seq}:1000;
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

function deleteQna() {
<c:if test="${sessionScope.member.userId=='admin' || sessionScope.member.userId==dto.userId ||demander_seq==sessionScope.member.demander_seq}">
  var num = "${dto.sqnaIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/demander/${demander_seq}/qna/delete?" + params;

  if(confirm("위 자료를 삭제 하시 겠습니까 ? "))
  	location.href=url;
</c:if>

<c:if test="${sessionScope.member.userId!='admin' && sessionScope.member.userId!=dto.userId &&demander_seq!=sessionScope.member.demander_seq}">
  alert("게시물을 삭제할 수  없습니다.");
</c:if>
}

function updateQna() {
<c:if test="${sessionScope.member.userId==dto.userId}">
  var num = "${dto.sqnaIdx}";
  var page = "${page}";
  var params = "num="+num+"&page="+page;
  var url = "<%=cp%>/demander/${demander_seq}/qna/update?" + params;

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
                                    <h2 class="post-title bold"><a href="#">${dto.subject}&nbsp;&nbsp;<small>no.${dto.sqnaIdx}</small></a></h2>
                                    <h3 class="post-author"><a href="#">${dto.userName}</a></h3>
                                    <p>${dto.content}</p>
                                    <div class="post-bottom overflow">
                                        <ul class="nav navbar-nav post-nav">                       
                                            <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>조회수  ${dto.hitCount}</a></li>
                                            <li><a href="#"><i class="fa fa-clock-o"></i>${dto.created}</a></li>
                                        </ul>
                                    </div>
                                    
                                  
                                
                                   <div>
                                  	<div style="float:left; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="javascript:location.href='<%=cp%>/demander/${demander_seq}/qna/list?${params}';"> 목록보기 <span class="fa fa-list"></span></button>
                  					</div>
     
                                    <div style="float:right; padding-top: 10px;padding-bottom: 10px;">
                      					<button type="button" class="btn btn-warning" style="padding:10px 15px ; color:white; border:none;" onclick="updateQna();"> 수정 <span class="fa fa-pencil"></span></button>
                  					</div>
                  					<div style="float:right; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
                      					<button type="button" class="btn btn-default" style="padding:10px 15px ;" onclick="deleteQna();"> 삭제 <span class="fa fa-times"></span></button>
                  					</div>
                  				
                  					
                  				<c:if test="${sessionScope.member.userId=='admin'|| demander_seq==sessionScope.member.demander_seq}">
                  					<c:if test="${amode!='reply'}">
	        		   					<div style="float:right; padding-top: 10px;padding-bottom: 10px; padding-right: 5px">
	        		   					 <button type="button" style="color:#F0AD4E; padding:10px 15px ;" class="btn btn-default" onclick="javascript:location.href='<%=cp%>/demander/${demander_seq}/qna/reply?num=${dto.sqnaIdx}&page=${page}';"><span class='glyphicon glyphicon-ok'>&nbsp;답변 </span></button>
										</div>
									</c:if>
								</c:if> 
								
								
                  				</div>
                                  
                                   </div>
                                   </div>
                                   </div>
    </section>