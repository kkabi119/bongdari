<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<style type="text/css">
 .table-hover{
 	margin-bottom:15px;
 }
 .table-hover>tbody>tr>td{
 	padding:10px 3px;
 }
 
 .table-hover>tbody>tr>th{
 	padding:10px 3px;
 }
 

.table-hover .one {                                 

margin-bottom: 3em;

border-collapse:collapse;   }  

 

.table-hover td {                            /* removed the border from the table data rows  */

text-align: center;    

width: 10em;                   

padding: 1em;
color: #60666D;       }      

 

.table-hover th {                              /* removed the border from the table heading row  */

text-align: center;                

padding: 1em;

background-color: #3897f0;       /* added a red background color to the heading cells  */

color: white;       }                 /* added a white font color to the heading text */

 

.table-hover tr {   

height: 1em;    }

.table-hover tr:nth-child(even) {            /* added all even rows a #eee color  */

      }

 

.table-hover tr:nth-child(odd) {            /* added all odd rows a #fff color  */

 }

 
.table table-bordered table-striped table-hover{

width:300px;

}

.table-hover .btn span.glyphicon {    			
	opacity: 0;				
}
.table-hover .btn.active span.glyphicon {				
	opacity: 1;
	color:white;				
}

.btn.active{
	background:orange;
}
.btn input[type="radio"]:checked ~ label,
.btn input[type="checkbox"]:checked ~ label {
  border:none;
}
</style>

<script type="text/javascript">

var deleteCheckList=[];

function deleteDate(theDate){
	if($("#checked_"+theDate).val()==0){
		$("#checked_"+theDate).each(function(){
			$("#checked_"+theDate).attr("style", "text-decoration: line-through; font-size:15pt; margin-bottom:10px;");
			deleteCheckList.push(theDate);
			$("#checked_"+theDate).val(1);
		});
	} else {
		$("#checked_"+theDate).each(function(){
			$("#checked_"+theDate).attr("style", "font-size:15pt; margin-bottom:10px;");
			deleteCheckList.splice(theDate, 1);
			$("#checked_"+theDate).val(0);
		});
	}
}

function deleteCheckOk(){
	if(deleteCheckList.length==0){
		alert("선택된 날짜가 없습니다.");
	} else {
		var url="<%=cp%>/club/${clubSeq}/apply/deleteCheckOk";
		var params="deleteCheckList="+deleteCheckList+"&clubApplyIdx="+${clubApplyIdx};
   	 	$.ajax({
	         type:"POST",
	         url:url,
	         data:params,
	         success:function(){
	        	 alert("성공");
	        	 reLoad();
	         },
	         error:function(e){
	             alert(e.responseText);
	         }
	     });
		$('#applyListModal').modal('hide');
	}
}

function applyCheckOk(){
	var url="<%=cp%>/club/${clubSeq}/apply/applyCheckOk";
	var params="clubApplyIdx="+${clubApplyIdx};
	 	$.ajax({
         type:"POST",
         url:url,
         data:params,
         success:function(){
        	 alert("성공");
        	 reLoad();
         },
         error:function(e){
             alert(e.responseText);
         }
     });
	$('#applyListModal').modal('hide');
}
</script>


<div class="container" style="width:100%; margin-bottom:-35px;">



<table class="table table-bordered table-striped table-hover" style="background-color:white; border-radius:3px;">
<tr><td colspan="7" style="text-align:left;"> &nbsp; &nbsp;&nbsp;&nbsp;현재 총 <span style="color:orange; font-weight:bold;">${n}명</span>이 가입을 신청하였습니다</td></tr>
  <tr style="" >
  	<th  width="5%;" data-toggle="buttons" id="allSelect">
						<label class="btn btn-default" style="width:20px;; height:20px; padding:0px;">
						<input type="checkbox" autocomplete="off">
						<span class="glyphicon glyphicon-ok"></span>
					</label>		
			</th> 
      <th width="8%;" >번호</th>
      <th width="15%">이름</th>
      <th width="15%">거주지</th>
      <th width="10%;">나이</th>
      <th width="10%;"> 성별</th>
      <th width="20%"> 신청날짜 </th><!-- 
      <th width="10%;"> 상세</th> -->
  </tr>
 <c:forEach var="dto" items="${list}">
		  <tr>
		<td  width="5%;" data-toggle="buttons">
						<label class="btn btn-default" style="width:20px;; height:20px; padding:0px;">
						<input type="checkbox" autocomplete="off">
						<span class="glyphicon glyphicon-ok"></span>
					</label>		
			</td> 
		      <td>${dto.listNum}</td>
		      
		      <td> ${dto.userName }</td>
		      <td>${dto.userAddr }</td>
		      <td> ${dto.age}살 </td>
		      <td> ${dto.userGender} </td>
		      <td> ${dto.joinDate} </td><!-- 
		      <td> <button class="btn btn-info btn-xs">▼</button> -->
		  </tr>
	</c:forEach>
	
</table>
<c:if test="${enabled==0}">
<div class="col-md-12">
	<label class="col-md-12" style="margin-bottom:10px;">내가 신청한 날짜</label>
	<c:forEach var="mdto" items="${myList}">
		<c:if test="${sessionScope.member.userIdx==mdto.userIdx}">
			<div id="checked_${mdto.hopeDate}" class="col-md-3" style="font-size:15pt; margin-bottom:10px;">${mdto.hopeDate}<a onclick='deleteDate("${mdto.hopeDate}");'>X</a></div>
		</c:if>
	</c:forEach>
</div>
</c:if>
<div>
	 <c:if test="${enabled==0}">
	<button type="button" onclick="deleteCheckOk();" class="btn btn-success" style="margin-right:10px; padding: 10px 15px; background-color: #3897f0; border:none;float:right;">
		 선택취소
	 </button>
	 </c:if>
	 <button type="button" onclick="applyCheckOk();" class="btn btn-success" 
	 			style="margin-right:10px; padding: 15px 25px; background-color: #3897f0; border:none;float:right;">
		 가입승인
	 </button>
	
</div>
</div><br /><br />