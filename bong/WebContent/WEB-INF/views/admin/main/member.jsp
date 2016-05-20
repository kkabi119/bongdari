<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
			<div class="single-blog two-column">
				<div class="post-content overflow" style="padding:0px;">
 					<div class="bodyFrame2">
          				<h3  style="font-size:30px;"> 회원목록<span style="margin-left:10px;color:gray; font-size:15px;">회원목록이야</span> </h3>
    					<div style="clear: both; height: 30px; line-height: 30px;">
            				<div style="float: left; color:#3897f0;"> <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 전체 ${dataCount}개 <span style="color:#777;">(${page}/${total_page}페이지)</span> </div>
            			
        				</div> 
        		<!-- <hr style="margin-bottom:10px; margin-top:0px; border:1px solid gray;"> -->
        		
        				<div class="table-responsive" style="clear: both; "> <!-- 테이블 반응형 -->
            				<table class="table table-hover" style="overflow:hidden;" >
                				<thead style="min-width:100%; font-size:15px; background-color:#DFE6E8; color:#555; ">
                    				<tr >
                        				<th class="text-center" style="width: 60px; font-weight:500;">번호</th>
                        				<th class="text-center" style="width: 105px; font-weight:500;">회원 ID</th>
                        				<th class="text-center" style="width:120px; font-weight:500;">회원이름</th>
                        				<th class="text-center" style="width:180px;font-weight:500;">전화번호</th>
                        				<th class="text-center" style="width:60px; ;font-weight:500;">성별</th>
                        				<th class="text-center" style="width:60px; ;font-weight:500;">레벨</th>
                        				<th class="text-center" style="width:100px; ;font-weight:500;">NoShow</th>
                        				<th class="text-center" style="width:120px; ;font-weight:500;">가입 날짜</th>
                        				<th class="text-center" style="width:130px; ;font-weight:500;">최종 로그인</th>
                        				<th class="text-center" style="width:120px; ;font-weight:500;">상세정보</th>
                        				
                    				</tr>
                				</thead>
                				
                				<tbody>
                				<c:forEach var="dto" items="${list}">
									<tr>
                        				<td class="text-center">${dto.rNum}</td>
                        				<td class="text-center">${dto.userId}</td>
                       				 	<td class="text-center" >${dto.userName}</td>
                       				 	<td class="text-center" style="">${dto.userTel}</td>
                        				<td class="text-center" style="">${dto.userGender}</td>
                        				<td class="text-center" style="">${dto.userLevel}</td>
                        				<td class="text-center" style="">${dto.userNoShow}번</td>
                        				<td class="text-center" style="">${dto.created_date}</td>
                        				<td class="text-center" style="">${dto.last_Login}</td>
                        				<td class="text-center" style="">상세정보</td>                        				
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