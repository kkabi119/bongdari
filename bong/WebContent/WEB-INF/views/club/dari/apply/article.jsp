<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<style type="text/css">
.bbs-reply {
    border-top: #1B8BFF solid 2px; 
    border-bottom: gray solid 2px; padding:15px;
    margin-bottom:70px;
}

.bbs-reply-write {   
   border-bottom: #ddd solid 2px; 
    padding: 10px;
    min-height: 50px;
}
.bbs-subject{
	font-size:20px; text-align: center;
	/* padding:0px; 
	 */border-bottom: 2px solid gray;
	margin-bottom: 10px;
}
.ddd{
	border-top:0px;
}
</style>


<div class="col-md-9 col-sm-7">
	<div class="row">
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
                                 노원 동네주민 축제의 안전요원을 모집합니다 <span style="color:#E0844F;">[ 모집중 ]</span>
                            </th>
                        </tr>
                   <thead>
                    <tbody>
                        <tr >
                        	<td bgcolor="#DFE6E8" style="color:black;;border-top:none;text-align: left; width:12%; height:45px; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;   	봉사일정
                        	</td>
                            <td style="text-align: left; width:22%; height:45px; ">2016-03-22 ~ 2016-03-25</td>
                            
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left; width:12%; height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사시간
                            </td>
                            <td style="text-align: left; width:200px; height:45px; ">3시~5시</td>
                         
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사요일
                           	</td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; "> 금,토,일 </td>
                            
                            
                        </tr>
                        <tr>
                        <td  bgcolor="#DFE6E8" style="color:black;; border-top:none;border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;모집기간
                           	</td>
                            <td  style="text-align: left;  height:45px; "> 2016-02-22 ~ 2016-02-25 </td>
                            
                             <td  bgcolor="#DFE6E8" style="color:black;border-top:none;text-align: left; width:12%; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;모집인원
                           	</td>
                            <td  style="text-align: left; width:200px; height:45px; "> 5명 / 일 </td>
                            
                             	
                            <td bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; width:12%; height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;신청인원
                            </td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; ">3봉 15명</td>
                        </tr>
                        
                        <tr>
                        <td  bgcolor="#DFE6E8" style="color:black;; border-top:none;border-top:none; text-align: left; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사분야
                           	</td>
                            <td style="text-align: left; height:45px; ">문화체육 > 행사보조</td>
                            
                             <td  bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; width:12%; height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사자 유형
                           	</td>
                            <td  style="text-align: left; width:200px; height:45px; "> 성인 </td>
                            
                            <td bgcolor="#DFE6E8" style="color:black; border-top:none; text-align: left; width:12%; height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;수요처
                            </td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; "><a href="#"> 희망복지관</a></td>
                             	
                           
                        </tr>
                        <tr>
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; text-align: left; width:12%; height:45px; ">
                            		<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉사장소
                            </td>
                            <td colspan="6" style="text-align: left; width:200px; height:45px; ">서울특별시 노원구 삼양동 종합복지센터 ( 장암역 1번출구 )</td>
                        </tr>
                      <tr>
                            <td colspan="7" style="border-top:2px solid gray;  padding:50px 20px 50px 20px; line-height:20pt;">
                                삼양동종합복지센터에서는 따뜻한 봄을 맞이하여 '봄맞이 어르신 봄나들이' 프로그램을 진행 할 예정입니다. <br>
								인솔을 함께할 자원봉사자를 모집합니다. <br>
								어르신을 공경하고, 서비스 마인드를 가지신 분들의 신청을 기다립니다^^ <br><br>
								
								일시: 2016년 5월 12일 목요일 08:30 ~ 16:30 (8시간) <br>
								장소: 롯데마트 삼양점 앞 집결 / 일산 아쿠아플라넷, 고양 꽃박람회장 이동 / 센터 도착 후 해산. <br>
								모집인원: 2명 <br>
								신청자격: 남녀무관 20세 이상, 사회복지학과 재학중인 학생 우대 <br>
								내용: 어르신 봄나들이 인솔 및 진행 보조, 중식 제공 <br>
								담당자: 어르신복지팀 하나래 팀장 <br>
								문의: 02) 945-1305~6 <br><br>
								
								많은 지원 부탁드립니다^^
								<br>
								
                            </td>
                        </tr>
                        <tr>
                      	  <td colspan="7" style="border-top:none; padding-bottom:30px;">
                       			 <span style="margin-left:400px; align:center" class="icon-wrapper">
                                	<i class="fa fa-2x fa-heart-o"></i>
                          		</span>
                          		
                          		<span style="margin-left:50px;  align:center; "class="icon-wrapper">
                                	<i class="fa fa-2x fa-comments-o"></i>
                          		</span>
                          		<br>
                          		<div style="">
	                          		<span style="font-size:18px; margin-left:420px; margin-top:-2px; text-align: center; color:#00AFF0; "><a style="font-weight:bold;" href="#"> 신청 </a></span>
	                          		<span style="font-size:18px;  margin-left:85px; text-align: center; color:#00AFF0; "><a style="font-weight:bold;" href="#"> 리스트 </a></span>
                          		</div><br>
                          		</td>
                        </tr>        
                                                 
                        <tr height="35">
					    <td colspan="1"bgcolor="#EEEEEE" align="center">이전글</td>
					    <td colspan="6" align="left" style="padding-left:10px;" colspan="3">
							
								<a href=#>희망복지관- 아이들과 키즈랜드 가기 [2016-02-03일]  <span style="color:orange;">[ 모집중 ]</span></a>
							
						</td>
					</tr>
                        <tr height="35">
					    <td colspan="1"bgcolor="#EEEEEE" align="center">다음글</td>
					    <td colspan="6" align="left" style="padding-left:10px;" colspan="3">
							
								<a href=#>나눔복지관- 독거노인과 함께하는 주말 [2016-02-03일] <span style="color:orange;">[ 모집중 ]</span></a>
							
					    </td>
					</tr>
                   </tbody>
                   <tfoot>
                    <tr>
					    <td colspan="6" align="left">
					    
                 			   <button type="button" class="btn btn-default">수정</button>   					
   						&nbsp;<button type="button" class="btn btn-default">삭제</button>

   						</td>
   						<td align="right" colspan="2">
					           <button type="button" class="btn btn-default">목록으로</button>

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
                      <button type="button" class="btn btn-default" style="background-color:#1B8BFF; color:white; border:none;" onclick="sendReply();"> 댓글등록 <span class="glyphicon glyphicon-ok"></span></button>
                  </div>           
              </div>
          
              <div id="listReply"></div>
          </div>
          
      </div>
 	</div>
</div>
    </div>
    </div>

