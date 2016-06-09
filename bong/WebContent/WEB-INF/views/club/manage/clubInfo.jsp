<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<style type="text/css">

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
<link rel="stylesheet" href="<%=cp%>/res/css/fileinput.css" type="text/css">

<script type="text/javascript" src="<%=cp%>/res/js/util.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/fileinput.js"></script>

	<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">

       <div class="table-responsive" style="clear: both; width: 700px; align:center; margin: auto;">
           <div >
               <table class="table">
                    <tbody>
                       <tr style=" width: 500px;">
                        	<td rowspan="3" bgcolor="#DFE6E8" style="color:black;;border-top:none;text-align: left; width:5%; height:45px; ">
                               <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;   클럽메인사진
                        	</td>
                        	<td rowspan="3" style="text-align: left; width:5%; height:45px; ">
                               <img src="<%=cp%>/uploads/club/${clubInfo.photoFilename}" style="width: 150px; height:150px;">
                            </td>
                            <td bgcolor="#DFE6E8" style="color:black;;border-top:none;text-align: left; width:5%; height:45px; ">
                        	 	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;   	개설자아이디
                        	</td>
                            <td style="text-align: left; width:5%; height:45px; ">${clubInfo.userId}</td>
                            </tr>
                        <tr >
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left; width:5%; height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;동아리이름
                            </td>
                            <td style="text-align: left; width:5%; height:45px; ">${clubInfo.clubname}</td>
                         
                        	
                        <tr>
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left; width:5%; height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;동아리개설일
                            </td>
                            <td style="text-align: left; width:5%; height:45px; ">${clubInfo.clubBirth}</td>

                        </tr>
                         <tr>
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left; width:5%; height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;도시
                            </td>
                            <td style="text-align: left; width:5%; height:45px; ">${clubInfo.city }</td>
                         
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;가입회원수
                           	</td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; ">32 명</td>
                        </tr>
                         <tr>
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left; width:5%; height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;대분류
                            </td>
                            <td style="text-align: left; width:5%; height:45px; ">${clubInfo.groupSubject }</td>
                         
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;소분류
                           	</td>
                            <td colspan="3"  style="text-align: left; width:200px; height:45px; ">${clubInfo.subject }</td>
                        </tr>
                        <tr>
                         <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left; width:5%; height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;동아리 소개
                            </td>
                            <td colspan="3" style="text-align: left; width:5%; height:45px; ">${clubInfo.introduce }</td>
                        </tr>
               </table>
          </div>
      </div>
 	</div>
</div>
	<div class="row" style="margin-left:15px;">
		<div class="col-md-12 col-sm-12">
       <div class="table-responsive" style="clear: both; width: 700px; align:center; margin: auto;">
           <div >
               <table class="table">
                    <tbody>
                         <tr>
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left;  height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;총 봉사시간
                            </td>
                            <td style="text-align: left;  height:45px; ">20시간</td>
                         
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; border-top:none; text-align: left;  height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;참석률
                           	</td>
                            <td style="text-align: left;  height:45px; ">90% </td>
                        </tr>
                         <tr>
                            <td  bgcolor="#DFE6E8" style="color:black;; border-top:none; text-align: left;  height:45px; ">
                            	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;획득 점수
                            </td>
                            <td style="text-align: left;  height:45px; ">500점</td>
                         
                        	<td  bgcolor="#DFE6E8" style="color:black;;border-top:none; border-top:none; text-align: left;   height:45px; ">
                         		  	<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>&nbsp;봉다리 레벨
                           	</td>
                            <td style="text-align: left;  height:45px; ">황금다리</td>
                        </tr>

               </table>
          </div>
      </div>
      </div>
      </div>			