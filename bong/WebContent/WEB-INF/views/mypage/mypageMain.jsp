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
	tabPageView();
});


// 탭을 선택 한 경우
var tabs=0;
$(function(){
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		tabs=$(this).attr("aria-controls");
		
		$("[id^=tabContent]").each(function(){
			$(this).html(""); // 전체를 출력하는 부분과 카테고리별 출력하는부분의 id가 같으므로 기존 정보 지움
		});
		
		tabPageView();
	});	
});

function tabPageView() {
	var url;
	if (tabs==0) {
		url="<%=cp%>/member/index/myInfo"
	} else if (tabs==1) {
		url="<%=cp%>/member/index/updateInfo"
	} else if (tabs==2) {
		url="<%=cp%>/member/index/myApply"
	} else if (tabs==3) {
		url="<%=cp%>/member/index/deleteMember"
	}
	
	
	$.get(url, {}, function(data){
		var id="#tabContent"+tabs;
		$(id).html(data);
	});
}
function searchList() {
	var f=document.searchForm;
	var searchKey=f.searchKey.value;
	var searchValue=f.searchValue.value;
	var option=$("#searchSelect").val();
	
	var url="<%=cp%>/member/index/myApply";

	$.post(url, {searchKey:searchKey,searchValue:searchValue, option:option}, function(data){
		var id="#tabContent2";
		$(id).html(data);
		
		$("#searchSelect").val(option);
	});
}
function sendOk() {
    var f = document.confirmForm;

	var str = f.userId.value;
    if(!str) {
        f.userId.focus();
        return false;
    }

    str = f.userPwd.value;
    if(!str) {
        f.userPwd.focus();
        return false;
    }

    f.action = "<%=cp%>/mypage/deleteMember";
    f.submit();
}
</script>

	<div>
	    <div role="tabpanel">
			  <ul class="nav nav-tabs" role="tablist">
			      <li role="presentation"  class="active"><a href="#tabContent0" aria-controls="0" role="tab" data-toggle="tab">내정보보기</a></li>
			      <li role="presentation"><a href="#tabContent1" aria-controls="1" role="tab" data-toggle="tab">정보 수정</a></li>
			      <li role="presentation"><a href="#tabContent2" aria-controls="2" role="tab" data-toggle="tab">나의 봉사 신청현황</a></li>
			      <li role="presentation"><a href="#tabContent3" aria-controls="3" role="tab" data-toggle="tab">회원탈퇴하기</a></li>
			  </ul>
			
			  <div class="tab-content" style="padding: 5px; margin-top: 15px;">
			      <div role="tabpanel" class="tab-pane active" id="tabContent0"></div>
			      <div role="tabpanel" class="tab-pane" id="tabContent1"></div>
			      <div role="tabpanel" class="tab-pane" id="tabContent2"></div>
			      <div role="tabpanel" class="tab-pane" id="tabContent3"></div>
			  </div>
	    </div>
    </div>