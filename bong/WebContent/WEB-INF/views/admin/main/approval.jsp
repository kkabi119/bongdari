<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>


<style type="text/css">
 .row4 {
/* 	margin-top: 20px;
	margin-bottom: 60px;
	margin-left: 20px; */
	height: 400px;
	width: 600px;
} 
</style>
<script type="text/javascript">
	function detail(serviceIdx){
		$.get("<%=cp%>/admin/approvalDetail",{serviceIdx:serviceIdx}, function(data) {
		    $('#scheduleModal .modal-title').html('수요처 정보');
		    $('#scheduleModal .modal-body').html(data);
			$('#scheduleModal').modal('show');
		});	
	}
	
	function approvalOk(serviceIdx){
		$.post("<%=cp%>/admin/approvalDetailOk", {serviceIdx:serviceIdx}, function(data){
			if(data=="OK"){
				alert("수요처가 승인되었습니다.");
				$('#scheduleModal').modal('hide');
				window.location.reload();
			}
			else
				alert("실패");
		});
	}
	
</script>
<!-- 관리자 수요처 승인 페이지 -->
<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow" style="padding:0px;">
 					<div class="bodyFrame2">
          				<h3  style="font-size:30px;">수요처 목록<span style="margin-left:10px;color:gray; font-size:15px;">다음과 같은 수요처가 있습니다.</span> </h3>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left; color:#3897f0;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 전체 ${dataCount}개 <span style="color:#777;">(${page}/${total_page} 페이지)</span> </div>
            			
        				</div> 
        		<!-- <hr style="margin-bottom:10px; margin-top:0px; border:1px solid gray;"> -->
        		
        				<div class="table-responsive" style="clear: both; "> <!-- 테이블 반응형 -->
            				<table class="table table-hover" style="overflow:hidden;" >
                				<thead style="min-width:100%; font-size:15px; background-color:#DFE6E8; color:#555; ">
                    				<tr >
                        				<th class="text-center" style="width:7%; font-weight:500;">번호</th>
                        				<th class="text-center" style="width:17%; font-weight:500;">수요처 명</th>
                        				<th class="text-center" style="width:17%; font-weight:500;">봉사 분야</th>
                        				<th class="text-center" style="width:8%; font-weight:500;">지역</th>
                        				<th class="text-center" style="width:17%; font-weight:500;">전화번호</th>
                        				<th class="text-center" style="width:17%; font-weight:500;">E-mail</th>
                        				<th class="text-center" style="width:17%; font-weight:500;">개설날짜</th>
                        				
                    				</tr>
                				</thead>
                				
                				<tbody>
                					<c:forEach var="dto" items="${list}">
									<tr>
                        				<td class="text-center">${dto.rNum}</td>
                        				<td class="text-center"><a href="#" onclick="detail('${dto.serviceIdx}')">${dto.serviceName}</a></td>
                       				 	<td class="text-center" >${dto.lSubject}>${dto.sSubject}</td>
                       				 	<td class="text-center">${dto.serviceAddr}</td>
                       				 	<td class="text-center" style="">${dto.serviceTel}</a></td>
                        				<td class="text-center" style="">${dto.serviceEmail}</td>
                        				<td class="text-center" style="">${dto.serviceBirth}</td>
                        				
            						</tr>
            						</c:forEach>
            						
                				</tbody>
            				</table>
        				</div>

        				<div class="paging" style="text-align: center; min-height: 30px; line-height: 30px;">
<c:if test="${dataCount==0 }">
                  등록된 게시물이 없습니다.
</c:if>
<c:if test="${dataCount!=0 }">
                ${paging}
</c:if>
        				</div>        
        
        				<div style="clear: both;">
        					<div style="float: left; width: 20%; min-width: 85px;">
        		    			<button type="button" class="btn btn-default" onclick="javascript:location.href='<%=cp%>/bbs/list';">새로고침</button>
        					</div>
        					<div style="float: left; width: 60%; text-align: center;">
        		     			<form name="searchForm" method="post" class="form-inline">
						  			<select class="form-control input-sm" name="searchKey" style="height:32px">
						      			<option value="subject">제목</option>
						      			<option value="userName">작성자</option>
						      			<option value="content">내용</option>
						      			<option value="created">등록일</option>
						  			</select>
						  			<input type="text" class="form-control input-sm input-search" name="searchValue" placeholder="검색" style="width:50%; height:32px;">
						  			<button type="button" class="btn btn-success" onclick="searchList();" style="background-color: #3897f0; border:none;"><span class="glyphicon glyphicon-search" ></span> 검색</button>
        		     			</form>
        					</div>
        					
        				</div>
        			</div>
        		</div>
        	</div>
        </div>
	</div>
	

<div class="modal fade" id="scheduleModal" tabindex="-1" role="dialog" aria-labelledby="scheduleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" style="width:600px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="scheduleModalLabel" style="font-family: 나눔고딕, 맑은 고딕, sans-serif; font-weight: bold;">일정</h4>
	      </div>
	      <div class="modal-body"></div>
	    </div>
	  </div>
	</div>