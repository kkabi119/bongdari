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
<style type="text/css">
.guest {
    font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", 돋움, sans-serif;
}
.guest-write {
    border: #d5d5d5 solid 1px;
    padding: 10px;
    min-height: 50px;
}

.table td {
    font-weight: normal;
    border-top: none;
}
.table .guest-header{
    border: #d5d5d5 solid 1px;
    background: #eeeeee; color: #787878;
} 
</style>

<script type="text/javascript">
$(function(){
	listPage(1);
});

function listPage(page) {

	var url="<%=cp%>/demander/${demander_seq}/guest/list";
	$.post(url, {pageNo:page}, function(data){
		printGuest(data);
	}, "JSON");
}

function sendGuest() {
	var uid="${sessionScope.member.userId}";
	
	var content=$.trim($("#content").val());
	
	var params="content="+content;
	
	$.ajax({
		type:"POST"
		,url:"<%=cp%>/demander/${demander_seq}/guest/created"
		,data:params
		,dataType:"JSON"
		,success:function(data) {
			printGuest(data);
			$("#content").val("");
		}
		,beforeSend:check
		,error:function(e) {
			alert(e.responseText);
		}
	});
}

function check() {
	if(! $.trim($("#content").val()) ) {
		$("#content").focus();
		return false;
	}
	
	return true;
}

function deleteGuest(num, page) {
	var uid="${sessionScope.member.userIdx}";
	if(! uid) {
		alert("로그인이 필요 합니다.");
		return;
	}
	if(confirm("게시물을 삭제하시겠습니까 ? ")) {
		var url="<%=cp%>/demander/${demander_seq}/guest/delete";
		$.post(url, {num:num, pageNo:page}, function(data){
			printGuest(data);

		}, "JSON");
	}
}

function printGuest(data) {
	var uid="${sessionScope.member.userIdx}";
	var total_page=data.total_page;
	var dataCount=data.dataCount;
	var pageNo=data.pageNo;
	var paging=data.paging;
	
	var out="<div style='clear: both; padding-top: 20px;'>";
	out+="  <div style='float: left;'>";
	out+="    <span style='color: #3EA9CD; font-weight: bold;'>방명록 "+dataCount+"개</span>";
	out+="    <span>[목록, "+pageNo+"/"+total_page+" 페이지]</span>";
	out+="  </div>";
	out+="  <div style='float: right; text-align: right;'></div>";
	if(dataCount!=0) {
		out+="  <div class='table-responsive' style='clear: both; padding-top: 5px;'>";
		out+="    <table class='table'>";
		for(var idx=0; idx<data.list.length; idx++) {
			var num=data.list[idx].sguestIdx;
			var userName=data.list[idx].userName;
			var userIdx=data.list[idx].userIdx;
			var content=data.list[idx].content;
			var created=data.list[idx].created;
			
			out+="    <tr class='guest-header'>";
			out+="      <td style='width: 50%;'>"+ userName+"</td>";
			out+="      <td style='width: 50%; text-align: right;'>" + created;
			if(uid==userIdx || uid=="admin") {
				out+=" | <a onclick='deleteGuest(\""+num+"\", \""+pageNo+"\");'>삭제</a></td>" ;
			} else {
				out+=" | <a href='#'>신고</a></td>" ;
			}
			out+="    </tr>";
			out+="    <tr style='height: 50px;'>";
			out+="      <td colspan='2'>"+content+"</td>";
			out+="    </tr>";
		}
		out+="    <tr style='height: 30px;'>";
		out+="      <td colspan='2' style='text-align: center;'>";
		out+=paging;
		out+="      </td>";
		out+="    </tr>";
		out+="  </table>";
	}
	
	$("#listGuest").html(out);
}
</script>


</head>
<body>
		<div class="col-lg-12">
			<h1 class="page-header" style="color:#F0AD4E;">
			<i class="fa fa-comments-o" aria-hidden="true"></i>
				방명록 
			</h1>
			<ol class="breadcrumb">
				<li><a href="<%=cp%>/demander/${demander_seq}/main" style="color:#F0AD4E;">수요처 메인</a></li>
				<li class="active">방명록</li>
			</ol>
		</div>

	
	
<div class="row2">
<div class="col-md-7">
    <table style="width: 600px; margin: 0px auto; margin-top: 5px;" >
         <tr height="60">
         		<td width="520">
         		   <i class="fa fa-user" aria-hidden="true" style="size: 60px;">  작성자 :</i> 
         		  ${sessionScope.member.userName}
         		</td>
         		<td width="80">&nbsp;</td>
         </tr>
             
		<tr height="80">
			<td width="520" align="left">
			    <textarea rows="5" cols="85" class="boxTF" id="content" style="width:515px; height: 75px;"></textarea>
			</td>
			<td width="80" align="right" >
			<br>
			   <button type="button" onclick="sendGuest();" class="btn btn-warning"  id="btnSend" class="btn"
			           style="width: 70px; height: 42px;">등록</button> 
			</td>
		</tr>           
        </table>
        </div>
   </div>
        
        <div id="listGuest"></div>

</body>
              
    <!--/#blog-->
