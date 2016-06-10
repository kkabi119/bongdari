<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
// String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cp;
%>
<script type="text/javascript">
$(function(){
	tab1();
	tab2();
	tab3();
});

function tab1(){
	
	var url="<%=cp%>/demander/${demander_seq}/admin/tab1";
	
	var num="${dto.num}";
	
	$.post(url, {}, function(data){ //ajax 써도됨 //url, {pageNo:page}는 보낼 때 쓰는거 function(data)는 받을 때 쓰는 함수
	
		$("#deList").html(data); //이번예제는 json을 쓰는것이 아니라 html로 넣어줌 > 편한 장점이 있음
	
	});		
}	

function tab2(){
	
	var url="<%=cp%>/demander/${demander_seq}/admin/tab2";

	var num="${dto.num}";
	
	$.post(url, {}, function(data){ //ajax 써도됨 //url, {pageNo:page}는 보낼 때 쓰는거 function(data)는 받을 때 쓰는 함수
	
		$("#deBong").html(data); //이번예제는 json을 쓰는것이 아니라 html로 넣어줌 > 편한 장점이 있음
	
	});		
}	
function tab3(){
	
	var url="<%=cp%>/demander/${demander_seq}/admin/tab3";
	
	var num="${dto.num}";
	$.get(url, {}, function(data){ //ajax 써도됨 //url, {pageNo:page}는 보낼 때 쓰는거 function(data)는 받을 때 쓰는 함수
	
		$("#deUpdate").html(data); //이번예제는 json을 쓰는것이 아니라 html로 넣어줌 > 편한 장점이 있음
	
	});		
}	
</script>
<style type="text/css">

.col-md-9{
	margin-top:-30px; width:78%;

}
.col-md-7{

	width: 46%;
}
		
</style>
 <div id="tab-container">
                        <ul id="tab1" class="nav nav-tabs">
                            <li class="active"><a href="#tab1-item1" data-toggle="tab">나의 활동</a></li>
                            <li><a href="#tab1-item2" data-toggle="tab">나를 등록한 동아리</a></li>
                            <li><a href="#tab1-item3" data-toggle="tab">내 정보 수정</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="tab1-item1">
                               <div id="deList">                               
                               
                               </div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item2">
                                <div id="deBong"> </div>
                            </div>
                            <div class="tab-pane fade" id="tab1-item3">
                                <div id="deUpdate"> </div>
                            </div>
                        </div>
                </div>
            
