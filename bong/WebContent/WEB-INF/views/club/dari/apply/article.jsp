<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<style type="text/css">

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
	 border-bottom: 2px solid gray;

}

.icon-wrapper:hover{
	background-color:#4FCCCD;
}

.table>tbody>tr>td{

padding-top: 13px;
}

</style>


	<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
		
   
    <div class="col-sm-10_2"  style="float:none; margin-left: auto; margin-right: auto;">

       <div class="body-title">
             <h3  style="font-size:30px;"> 봉사 신청<span style="margin-left:10px;color:gray; font-size:15px;">  봉사를 신청할 수 있는 게시판입니다</span> </h3>
             
       </div>
                 
       </div>
       <hr style="margin-bottom:10px; margin-top:0px; border:1px solid gray;">
       
       
       <div class="table-responsive" style="clear: both;">
           <div >
               <table class="table">
                    <thead >
                        <tr height="50">
                            <th style="color:#555;"colspan="7" class="bbs-subject" >
                                 ${dto.subject } <span style="color:#E0844F;">
                                 <c:if test="${dto.progress.equals('모집마감')}">
                              	   <span class="label label-default">${dto.progress}</span>
                                 </c:if>
                                 <c:if test="${dto.progress.equals('모집중')}">
                              	    <span class="label label-warning">${dto.progress}</span>
                                 </c:if>
                                 </span>
                            </th>
                        </tr>
                   <thead>
                    <tbody>
                        <tr >
                        	<td bgcolor="#DFE6E8" style="color:black;;border-top:none;text-align: left; width:14%; height:45px; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;   	봉사일정
                        	</td>
                            <td style="text-align: left; width:25%; height:45px; ">${dto.startDay } ~ ${dto.endDay }</td>
                            
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left; width:14%; height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사시간
                            </td>
                           <td style="text-align: left; width:20%; height:45px; ">${dto.startTime } ~ ${dto.endTime }</td>
                         
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사요일
                           	</td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; ">${dto.volunDays }</td>
                            
                            
                        </tr>
                        <tr>
                        <td  bgcolor="#DFE6E8" style="color:black;; border-top:none;border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;모집기간
                           	</td>
                            <td  style="text-align: left;  height:45px; "> 2016-02-22 ~ 2016-02-25 </td>
                            
                             <td  bgcolor="#DFE6E8" style="color:black;border-top:none;text-align: left; width:12%; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;모집인원
                           	</td>
                            <td  style="text-align: left; width:200px; height:45px; "> 총 ${dto.maxNum}명 </td>
                            
                             	
                            <td bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; width:12%; height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;신청인원
                            </td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; ">${dto.applyNum}명</td>
                        </tr>
                        
                        <tr>
                        <td  bgcolor="#DFE6E8" style="color:black;; border-top:none;border-top:none; text-align: left; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사분야
                           	</td>
                            <td style="text-align: left; height:45px; ">문화체육 > 행사보조</td>
                            
                             <td  bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; width:12%; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사자 유형
                           	</td>
                            <td  style="text-align: left; width:200px; height:45px; "> ${dto.volunteer_type } </td>
                            
                            <td bgcolor="#DFE6E8" style="color:black; border-top:none; text-align: left; width:12%; height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;수요처
                            </td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; "><a href="#"> ${dto.serviceName}</a></td>
                             	
                           
                        </tr>
                        <tr>
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; width:12%; height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사장소
                            </td>
                            <td colspan="6" style="text-align: left; width:200px; height:45px; ">
                            	${dto.place } <a href="#" > [지도]</a>
                            </td>
                        </tr>
                      <tr>
                            <td colspan="7" style="border-top:2px solid gray;  padding:50px 20px 50px 20px; line-height:20pt;">
                               ${dto.content }
                            </td>
                        </tr>
                        <tr>
                      	  <td colspan="7" style="border-top:none; padding-bottom:30px;">
                       			 <a href="#" style="color:white; ">
	                       			 <span style="margin-left:41%;align:center; background-color: #3897f0; border: 1px solid g" class="icon-wrapper">
	                                	<i class="fa fa-2x fa-heart-o" ></i>
	                          		</span>
                          		</a>
                          		
                          		<a href="#" style="color:white; ">
                          			<span style="background-color: #3897f0; align:center; margin-left:50px;"class="icon-wrapper">
                                	<i class="fa fa-2x fa-comments-o"></i>
                          		</span></a>
                          		<br>
                          		<div style="">
	                          		<span style="font-size:18px; margin-left:43.5%; margin-top:-2px; text-align: center; color:#00AFF0; ">
	                          				<a style="font-weight:bold;" href="#"> 신청 </a>
	                          		</span>
	                          		<span style="font-size:18px;text-align: center; color:#00AFF0; ">
	                          				<a style="font-weight:bold; margin-left:83px; "  href="#">  리스트 </a>
	                          		</span>
                          		</div><br>
                          		</td>
                        </tr>        
                                                 
                        <tr height="35">
					    <td colspan="1"bgcolor="#EEEEEE" align="center">이전글</td>
					    <td colspan="6" align="left" style="padding-left:10px;" colspan="3">
								<a href="<%=cp%>/club/index/apply/article?${params}&num=${preReadDto.clubApplyIdx}">
										이전글 : ${preReadDto.subject} 
										<c:if test="${dto.progress.equals('모집마감')}">
                       				 		<span class="text-center" style="font-weight:bold; color:white; font-size:16px;"> 
                       				 			<span class="label label-default" style="padding:5px;">${dto.progress}</span>
                       				 		</span>
                       				 	</c:if>
                       				 	<c:if test="${dto.progress.equals('모집중')}">
                       				 		<span class="text-center" style="font-weight:bold; color:white; font-size:16px;">
                       				 			<span class="label label-warning" style="padding:5px;">${dto.progress}</span>
                       				 		</span>
                       				 	</c:if>
								</a>
                              
							
						</td>
					</tr>
                        <tr height="35">
					    <td colspan="1"bgcolor="#EEEEEE" align="center">다음글</td>
					    <td colspan="6" align="left" style="padding-left:10px;" colspan="3">
							<a href="<%=cp%>/club/index/apply/article?${params}&num=${nextReadDto.clubApplyIdx}">
								다음글 : ${nextReadDto.subject}
							
								<c:if test="${dto.progress.equals('모집마감')}">
                       				 		<span class="text-center" style="font-weight:bold; color:white; font-size:16px;"> 
                       				 			<span class="label label-default" style="padding:5px;">${dto.progress}</span>
                       				 		</span>
                       				 	</c:if>
                       				 	<c:if test="${dto.progress.equals('모집중')}">
                       				 		<span class="text-center" style="font-weight:bold; color:white; font-size:16px;">
                       				 			<span class="label label-warning" style="padding:5px;">${dto.progress}</span>
                       				 		</span>
                       				 	</c:if>
							</a>							
					    </td>
					</tr>
                   </tbody>
                   <tfoot>
                    <tr>
					    <td colspan="6" align="left">
   						 	<button type="button" class="btn btn-default" onclick="deleteApply();">삭제</button>
   						</td>
   						
   						<td align="right" colspan="2">
					           <button type="button" class="btn btn-default"  onclick="javascript:location.href='<%=cp%>/club/index/apply/list?${params}';">목록으로</button>

					    </td>
					    </tr>
                   </tfoot>
               </table>
          </div>
          
          <div class="bbs-reply">
              <div class="bbs-reply-write" >
                  <div style="clear: both; ">
                        <div style="float: left; "><span style="font-size:23px;">COMMENTS</span><span>  3개 </span></div>
                        <div style="float: right; text-align: right;"></div>
                  </div>
                  
                  <div style="clear: both; padding-top: 30px; ">
                      <textarea id="shareR_content" class="form-control" rows="3" required="required"></textarea>
                  </div>
                  <div style="text-align: right; padding-top: 10px;">
                      <button type="button" class="btn btn-default" style="padding:10px 15px ;background-color:#3897f0; color:white; border:none;" onclick="sendReply();"> 댓글등록 <span class="glyphicon glyphicon-ok"></span></button>
                  </div>           
              </div>
          
              <div id="listReply"></div>
          </div>
          
      </div>
 	</div>
</div>

