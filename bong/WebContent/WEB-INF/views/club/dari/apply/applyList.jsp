<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>

<style type="text/css">

 .table>tbody>tr>td{
 	padding:10px 3px;
 }
 
 .table>tbody>tr>th{
 	padding:10px 3px;
 }
 

table.one {                                 

margin-bottom: 3em;

border-collapse:collapse;   }  

 

td {                            /* removed the border from the table data rows  */

text-align: center;    

width: 10em;                   

padding: 1em;
color: #8C96A0;       }      

 

th {                              /* removed the border from the table heading row  */

text-align: center;                

padding: 1em;

background-color: #3897f0;       /* added a red background color to the heading cells  */

color: white;       }                 /* added a white font color to the heading text */

 

tr {   

height: 1em;    }

table tr:nth-child(even) {            /* added all even rows a #eee color  */

      }

 

table tr:nth-child(odd) {            /* added all odd rows a #fff color  */

 }

 
.table table-bordered table-striped table-hover{

width:300px;

}

.btn span.glyphicon {    			
	opacity: 0;				
}
.btn.active span.glyphicon {				
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

<div class="container" style="width:85%">

 	<div class="well well-sm text-center" >

<table class="table table-bordered table-striped table-hover" style="background-color:white; border-radius:3px;">

  <tr style="" >
      <th width="5%;" data-toggle="buttons">
				<label class="btn btn-default" style="width:20px;; height:20px; padding:0px;">
				<input type="checkbox" autocomplete="off">
				<span class="glyphicon glyphicon-ok"></span>
			</label>		
		</th>
      <th width="8%;" >번호</th>
      <th colspan="1" >소속</th>
      <th width="15%">이름</th>
      <th >지역</th>
      <th width="10%;">나이</th>
      <th width="10%;"> 상세</th>
  </tr>
  <tr>
  <td  width="5%;" data-toggle="buttons">
				<label class="btn btn-default" style="width:20px;; height:20px; padding:0px;">
				<input type="checkbox" autocomplete="off">
				<span class="glyphicon glyphicon-ok"></span>
			</label>		
	</td>
      <td>1</td>
      <td>우리가짱이다</td>
      <td>최양희</td>
      <td>서울 동창구</td>
      <td> 24 </td>
      <td> <button class="btn btn-info btn-xs">▼</button>
  </tr>
 <tr>
  <td  width="5%;" data-toggle="buttons">
				<label class="btn btn-default" style="width:20px;; height:20px; padding:0px;">
				<input type="checkbox" autocomplete="off">
				<span class="glyphicon glyphicon-ok"></span>
			</label>		
	</td>
      <td>1</td>
      <td>우리가짱이다</td>
      <td>최양희</td>
      <td>서울 동창구</td>
      <td> 24 </td>
      <td> <button class="btn btn-info btn-xs">▼</button>
  </tr><tr>
  <td  width="5%;" data-toggle="buttons">
				<label class="btn btn-default" style="width:20px;; height:20px; padding:0px;">
				<input type="checkbox" autocomplete="off">
				<span class="glyphicon glyphicon-ok"></span>
			</label>		
	</td>
      <td>1</td>
      <td>우리가짱이다</td>
      <td>최양희</td>
      <td>서울 동창구</td>
      <td> 24 </td>
      <td> <button class="btn btn-info btn-xs">▼</button>
  </tr>
</table>
</div></div><br /><br />