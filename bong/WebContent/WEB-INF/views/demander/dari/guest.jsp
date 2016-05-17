<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<!-- sp3의 guest -->
<link rel="stylesheet" href="<%=cp%>/res/css/style.css" type="text/css">
<style type="text/css">
.title {
	font-weight: bold;
	font-size:13pt;
	margin-bottom:10px;
	font-family: 나눔고딕, "맑은 고딕", 돋움, sans-serif;
}

</style>


<script type="text/javascript">
<%-- $(function(){
	listPage(1);
});

function listPage(page) {
	var url="<%=cp%>/guest/list.do";
	var params="pageNo="+page;
	
	$.ajax({
		type:"post"
		,url:url
		,data:params
		,dataType:"xml"
		,success:function(data) {
			printGuest(data);
		}
	    ,error:function(e) {
	    	alert(e.responseText);
	    }
	});
}

function printGuest(data) {
	$("#listGuest").show();

	var dataCount=$(data).find("dataCount").text();
	var pageNo=$(data).find("pageNo").text();
	var paging=$(data).find("paging").text();
	
	var out="";
	if(dataCount!=0) {
		out="<table style='width: 600px; margin: 10px auto 0px; border-spacing: 0px; border-collapse: collapse;'>";
		$(data).find("record").each(function(){
			var record=$(this);
			var num=record.attr("num");
			var name=record.find("name").text();
			var content=record.find("content").text();
			var created=record.find("created").text();
			
			out+="<tr height='30' bgcolor='#EEEEEE' style='border: 1px solid #DBDBDB;'>";
			out+="  <td width='50%' style='padding-left: 5px;'>"+ name+"</td>";
			out+="  <td width='50%' align='right' style='padding-right: 5px;'>" + created;
			out+=" | <a onclick='deleteGuest(\""+num+"\", \""+pageNo+"\");'>삭제</a></td>" ;
			out+="</tr>";
			out+="<tr height='50'>";
			out+="   <td colspan='2' style='padding: 5px;' valign='top'>"+content+"</td>";
			out+="</tr>";
		});
		out+="<tr height='30'>";
		out+="  <td colspan='2' align='center'>";
		out+=paging;
		out+="  </td>";
		out+="</tr>";
		out+="</table>";
	}
	
	$("#listGuest").html(out);
}

$(function(){
	$("#btnSend").click(function(){
		var name=$("#name").val().trim();
		var content=$("#content").val().trim();
		
		var url="<%=cp%>/guest/insert.do";
		var params="name="+name;
		params+="&content="+content;
		
		$.ajax({
			type:"post"
			,url:url
			,data:params
			,dataType:"xml"
			,success:function(data) {
				// var state=$(data).find("state").text();
				listPage(1);
				
				$("#name").val("");
				$("#content").val("");
			}
		    ,error:function(e) {
		    	alert(e.responseText);
		    }
		    ,beforeSend:check
		});
		
	});
});

function check() {
	var name=$("#name").val().trim();
	var content=$("#content").val().trim();
	
	if(! name) {
		alert("이름을 입력하세요 !!!");
		$("#name").focus();
		return false;
	}
	
	if(! content) {
		alert("내용을 입력하세요 !!!");
		$("#content").focus();
		return false;
	}
	
	return true;
}

function deleteGuest(num, page) {
	if(! confirm("게시물을 삭제 하시 겠습니까 ?"))
		return false;
	
	var url="<%=cp%>/guest/delete.do";

	$.post(url, {num:num}, function(data) {
		   listPage(page);
	}, "xml");	
} --%>
</script>

</head>
<body>


		<div class="col-lg-12">
			<h1 class="page-header" style="color:#F0AD4E;">
			<i class="fa fa-comments-o" aria-hidden="true"></i>
				방명록 
			</h1>
			<ol class="breadcrumb">
				<li><a href="<%=cp%>/demander/index/main" style="color:#F0AD4E;">수요처 메인</a></li>
				<li class="active">방명록</li>
			</ol>
		</div>

	
	
<div class="row2">
<div class="col-md-7">
    <table style="width: 600px; margin: 0px auto; margin-top: 5px;" >
         <tr height="60">
         		<td width="520">
         		   <i class="fa fa-user" aria-hidden="true" style="size: 60px;">  작성자 :</i> 
         		   <input type="text" id="name" class="boxTF" size="30">
         		</td>
         		<td width="80">&nbsp;</td>
         </tr>
             
		<tr height="80">
			<td width="520" align="left">
			    <textarea rows="5" cols="85" class="boxTF" id="content" style="width:515px; height: 75px;"></textarea>
			</td>
			<td width="80" align="right" >
			<br>
			   <button type="button"  class="btn btn-warning"  id="btnSend" class="btn"
			           style="width: 70px; height: 42px;">등록</button> 
			</td>
		</tr>           
        </table>
        </div>
   </div>
        
        <div id="listGuest"></div>

	

</body>
              
    <!--/#blog-->
