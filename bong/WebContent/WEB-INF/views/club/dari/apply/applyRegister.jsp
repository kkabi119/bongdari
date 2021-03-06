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
color: #60666D;       
}      

 

.table-hover th {                              /* removed the border from the table heading row  */

text-align: center;                

padding: 1em;

background-color: #DFE6E8;       /* added a red background color to the heading cells  */

color: black;       }                 /* added a white font color to the heading text */

 

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
var checkDateList=[];
function dateInput(theDate){
	if($("#"+theDate).val()==0){
		$("#"+theDate).each(function(){
			$("#"+theDate).attr("class", "fa fa-check fa-fw");
			$("#check_"+theDate).attr("style", "font-size:14pt; margin-bottom:5px; display:block; margin-top:20px; color:#777;");
			checkDateList.push(theDate);
			$("#"+theDate).val(1);
		});
	} else {
		$("#"+theDate).each(function(){
			$("#"+theDate).attr("class", "none");
			$("#check_"+theDate).attr("style", "margin-top:10px;font-size:14pt; margin-bottom:10px; display:none; color:#777;");
			checkDateList.splice(theDate, 1);
			$("#"+theDate).val(0);
		});	
	}
	
}

function applyOk(){
	if(checkDateList.length==0){
		alert("선택된 날짜가 없습니다.");
	} else {
		var url="<%=cp%>/club/${clubSeq}/apply/applyOk";
		var params="checkDateList="+checkDateList
					+"&clubApplyIdx="+${clubApplyIdx};
		
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
</script>

<div class="container" style="width:100%; margin-bottom:-35px;">



<table class="table table-bordered table-striped table-hover" style="background-color:white; border-radius:3px; border:none;">
<tr><td colspan="7" style="text-align:center; border:none; background-color: white; ">
		 <span style="color:#848484; font-weight:bold; font-size:x-large;">
		 	<span class="label label-warning" style="background-color: #f7be6d; padding:6px 8px; border-radius:0px;">봉사일</span>
		 	&nbsp; ${startDay} ~ ${endDay}</span> 
		</td></tr>
  <tr>	
      <th width="13%" style="color:tomato; vertical-align: inherit;">일</th>
      <th width="13%" style="color:#777; vertical-align: inherit;">월</th>
      <th width="13%" style="color:#777;  vertical-align: inherit;">화</th>
      <th width="13%" style="color:#777;  vertical-align: inherit;">수</th>
      <th width="13%" style=" color:#777; vertical-align: inherit;">목</th>
      <th width="13%" style="color:#777;  vertical-align: inherit;">금</th>
      <th width="13%" style="color:#3897f0; vertical-align: inherit;">토</th>
  </tr>
 <c:forEach var="dto" items="${list}">
		  <tr style="height:64px;">
		      <td style="color:tomato; vertical-align: inherit;">${dto[0]}</td>
		      <td style="vertical-align: inherit;">${dto[1]}</td>
		      <td style="vertical-align: inherit;">${dto[2]}</td>
		      <td style="vertical-align: inherit;">${dto[3]}</td>
		      <td style="vertical-align: inherit;">${dto[4]}</td>
		      <td style="vertical-align: inherit;">${dto[5]}</td>
		      <td style="color:#3897f0; vertical-align: inherit;">${dto[6]}</td>
		  </tr>
</c:forEach>
</table>
<div class="col-md-12" style="padding:10px;border-bottom: 2px solid #ccc;">
<span style=" border-radius:2px; font-size:20px; color:#777; font-weight:bold;">
		<img Id="listBtn"style=" width:22px; height:22px; background-size:cover; "src="<%=cp%>/res/images/myclub/calendar.png" alt="">
	           봉사 날짜 선택
<span style="margin-left:10px;color:gray; font-size:15px;">원하는 날짜를 선택해주세요</span></span>

	<div >
	<c:forEach var="dto" items="${checkDate}">
		<div id="check_${dto}" class="col-md-3" style="font-size:14px; margin-top:15px; margin-bottom:10px; display:none; color:#3897f0;  ">${dto}</div>
	</c:forEach>
	</div>
</div>
<div class="col-md-12" style="margin-top:10px;">
	<!-- <button type="button" class="btn btn-default" data-dismiss="modal" style="padding: 15px 20px; float:right; border-color:#777777; border-radius:0px;">
		 닫기
	 </button> -->
	
	<button type="button" onclick="applyOk();" class="btn btn-success" style="padding: 17px 23px; background-color: #3897f0; border:none;float:right; margin-right:-20px;border-radius:0px;">
		 신청하기
	 </button>
</div>
</div><br/><br/>