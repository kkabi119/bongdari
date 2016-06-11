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
			$("#check_"+theDate).attr("style", "font-size:15pt; margin-bottom:10px; display:block;");
			checkDateList.push(theDate);
			$("#"+theDate).val(1);
		});
	} else {
		$("#"+theDate).each(function(){
			$("#"+theDate).attr("class", "none");
			$("#check_"+theDate).attr("style", "font-size:15pt; margin-bottom:10px; display:none;");
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



<table class="table table-bordered table-striped table-hover" style="background-color:white; border-radius:3px;">
<tr><td colspan="7" style="text-align:center;"> <span style="color:blue; font-weight:bold; font-size:xx-large;">${startDay} ~ ${endDay}</span> </td></tr>
  <tr>	
      <th width="13%" style="color:red; vertical-align: inherit;">일</th>
      <th width="13%" style=" vertical-align: inherit;">월</th>
      <th width="13%" style=" vertical-align: inherit;">화</th>
      <th width="13%" style=" vertical-align: inherit;">수</th>
      <th width="13%" style=" vertical-align: inherit;">목</th>
      <th width="13%" style=" vertical-align: inherit;">금</th>
      <th width="13%" style="color:blue; vertical-align: inherit;">토</th>
  </tr>
 <c:forEach var="dto" items="${list}">
		  <tr style="height:64px;">
		      <td style="color:red; vertical-align: inherit;">${dto[0]}</td>
		      <td style="vertical-align: inherit;">${dto[1]}</td>
		      <td style="vertical-align: inherit;">${dto[2]}</td>
		      <td style="vertical-align: inherit;">${dto[3]}</td>
		      <td style="vertical-align: inherit;">${dto[4]}</td>
		      <td style="vertical-align: inherit;">${dto[5]}</td>
		      <td style="color:blue; vertical-align: inherit;">${dto[6]}</td>
		  </tr>
</c:forEach>
</table>
<div class="col-md-12">
	<label class="col-md-12" style="margin-bottom:10px;">선택한 날짜</label>
	<c:forEach var="dto" items="${checkDate}">
		<div id="check_${dto}" class="col-md-3" style="font-size:15pt; margin-bottom:10px; display:none;">${dto}</div>
	</c:forEach>
</div>
<div class="col-md-12" style="margin-top:10px;">
	<button type="button" class="btn btn-success" data-dismiss="modal" style="padding: 10px 15px; background-color: #3897f0; border:none;float:right;">
		 닫기
	 </button>
	
	<button type="button" onclick="applyOk();" class="btn btn-success" style="padding: 10px 15px; margin-right:20px; background-color: #3897f0; border:none;float:right;">
		 신청하기
	 </button>
</div>
</div><br/><br/>