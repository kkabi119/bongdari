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
	 var mode;
	if (tabs==0) {
		url="<%=cp%>/club/${clubSeq}/manage/clubInfo"
	} else if (tabs==1) {
		url="<%=cp%>/club/${clubSeq}/manage/updateClubInfo"
		mode="update"
	} else if (tabs==2) {
		url="<%=cp%>/club/${clubSeq}/manage/joinClubList"
	} else if (tabs==3) {
		url="<%=cp%>/club/${clubSeq}/manage/deleteClub"
	}
	
	
	$.get(url,mode, {}, function(data){
		var id="#tabContent"+tabs;
		$(id).html(data);
	});
}

function searchList() {
	var f=document.searchForm;
	var searchKey=f.searchKey.value;
	var searchValue=f.searchValue.value;
	var option=$("#searchSelect").val();
	
	var url="<%=cp%>/club/${clubSeq}/admin/joinClubList";

	$.post(url, {searchKey:searchKey,searchValue:searchValue, option:option}, function(data){
		var id="#tabContent2";
		$(id).html(data);
		
		$("#searchSelect").val(option);
	});
}
  
</script>
	    <div role="tabpanel">
			  <ul class="nav nav-tabs" role="tablist">
			      <li role="presentation"  class="active"><a href="#tabContent0" aria-controls="0" role="tab" data-toggle="tab">동아리정보보기</a></li>
			      <li role="presentation"><a href="#tabContent1" aria-controls="1" role="tab" data-toggle="tab">정보 수정</a></li>
			      <li role="presentation"><a href="#tabContent2" aria-controls="2" role="tab" data-toggle="tab">동아리 가입 신청현황</a></li>
			      <li role="presentation"><a href="#tabContent3" aria-controls="3" role="tab" data-toggle="tab">동아리삭제하기</a></li>
			  </ul>
			
			  <div class="tab-content" style="padding: 5px; margin-top: 15px;">
			      <div role="tabpanel" class="tab-pane active" id="tabContent0"></div>
			      <div role="tabpanel" class="tab-pane" id="tabContent1"></div>
			      <div role="tabpanel" class="tab-pane" id="tabContent2"></div>
			      <div role="tabpanel" class="tab-pane" id="tabContent3"></div>
			  </div>
	    </div>