<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<link rel="stylesheet" href="<%=cp%>/res/css/fileinput.css" type="text/css">

<script type="text/javascript" src="<%=cp%>/res/js/util.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/fileinput.js"></script>

<script type="text/javascript">
$(function(){
	$('.fileinput').fileinput();
});

<c:if test="${mode=='update'}">
$(function(){
	themeList();
});
</c:if>



function themeList() {
	var groupNum=$("#groupNum").val();
	if(groupNum=="") {
		$("#themeNum option").each(function() {
			$("#themeNum option:eq(0)").remove();
		});

		$("#themeNum").append("<option value=''>:: 중분류 ::</option>");
		return false;
	}
	
	var url="<%=cp%>/club/themeList";
	var params="groupNum="+groupNum;
	
	$.ajax({
		type:"post"
		,url:url
		,data:params
		,dataType:"json"
		,success:function(data){
			$("#themeNum option").each(function() {
				$("#themeNum option:eq(0)").remove();
			});

			 $("#themeNum").append("<option value=''>:: 중분류 ::</option>");
			 
			 var cn="${dto.themeNum}";
			 var s;
			 
			 for(var idx=0; idx<data.listTheme.length; idx++) {
				 s="";
				 if(cn==data.listTheme[idx].themeNum)
					 s=" selected='selected'";
				 $("#themeNum").append("<option value='"+data.listTheme[idx].themeNum+"' " + s +">"+data.listTheme[idx].subject+"</option>");
			 }
		}
	    ,error:function(e) {
	    	alert(e.responseText);
	    }
	});
}

  function sendClub() {
        var f = document.clubForm;

    	 var str = f.groupNum.value;
        if(!str) {
            alert("동아리 주제를 선택 하세요. ");
            f.groupNum.focus();
            return;
        }
        
    	str = f.themeNum.value;
        if(!str) {
            alert("동아리 주제를 선택 하세요. ");
            f.themeNum.focus();
            return;
        }

    	str = f.clubname.value;
        if(!str || str.length>50) {
            alert("50자 이내의 동아리 제목을 입력 하세요. ");
            f.clubname.focus();
            return;
        }
        
    	str = f.introduce.value;
        if(!str || str.length>150) {
            alert("150자 이내의 동아리 소개를 입력 하세요. ");
            f.introduce.focus();
            return;
        }
        
    	str = f.clubAddr.value;
        if(!str) {
            alert("지역를 선택 하세요. ");
            f.clubAddr.focus();
            return;
        }
 
    	if(f.uploads.value!="") {
    		if(! /(\.gif|\.jpg|\.png|\.jpeg)$/i.test(f.uploads.value)) {
    			alert("사진은 이미지 파일만 가능합니다. ");
    			f.uploads.focus();
    			return false;
    		}
    	} 

        var mode="${mode}";
        if(mode=="created")
            f.action = "<%=cp%>/club/created";
        else if(mode=="update")
            f.action = "<%=cp%>/club/update";
            
       f.submit();
  }

  function imageDelete(){
		if(confirm("등록된 사진을 삭제 하시겠습니까?")){
			var url="<%=cp%>/member/imageDelete";
			var filename="${dto.photoFilename}";
			$.post(url, {filename:filename}, function(data){
				var isLogin=data.isLogin;
				if(isLogin==false){
					location.href="<%=cp%>/member/login";
					return;
				}
				
				$("#imgPhoto").attr("src", "<%=cp%>/res/images/noimage.png");
				$("#btnDeletePhoto").hide();
			},"json");
		}
	}
</script>
<div class="container" role="main" style="margin-top:50px;">
  <div class="bodyFrame">
  <div style="width:600px; padding-top:35px; clear: both; margin: 0px auto;">
        <div class="body-clubname">
              <h3> 동아리 ${mode=="created"?"생성":"수정" }</h3>
        </div>
     </div>
  <form class="form-horizontal" name="clubForm" method="post" enctype="multipart/form-data">
      <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label" for="userId">주제</label>
        <div class="col-sm-7">
            <select name="groupNum" id="groupNum" class="form-control" onchange="themeList();" style="float:left; width:20%;margin-right: 10px">
			<option value="">:: 대분류 ::</option>
             	<c:forEach var="vo" items="${listGroup}">
			          <option value="${vo.themeNum}" ${vo.themeNum==dto.groupNum ? "selected='selected'" : ""}>${vo.subject}</option>
			    </c:forEach>
			</select>
			<select name="themeNum" id="themeNum" class="form-control" style="float:left; width:20%;">
			     <option value="">:: 중분류 ::</option>
			 </select>
			  <p style="clear:both" class="help-block"> 주요활동분야 혹은 관심분야를 지정해주세요</p>
        </div>
    </div>
    <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label">동아리이름</label>
        <div class="col-sm-7">
            <input style="width:200px;"class="form-control" id="clubname" name="clubname" type="text" 
                  placeholder="동아리이름"  value="${dto.clubname}" ${mode=="update" ? "readonly='readonly' style='border:none;'":""}>
            <p class="help-block"> 의미있는 동아리의 이름을 멋있게 지어주세요</p>
        </div>
    </div>
 
    <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label" for="userPwd">동아리소개</label>
        <div class="col-sm-7">
            <textarea name="introduce" cols="75" rows="5" class="form-control" style="height: 50px;">${dto.introduce}</textarea>
            <p class="help-block">간단히 동아리를 소개해주세요</p>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label">동아리주인</label>
        <div class="col-sm-7">
            <input class="form-control" id="userName" style="width:200px; border: none; background-color: white" 
            name="userName" type="text" value="${sessionScope.member.userName}" readonly="readonly">
        </div>
    </div>
 
    <div class="form-group">
        <label class="col-sm-2 control-label">지역</label>
        <div class="col-sm-7">
            <select name="clubAddr" class="form-control" style="width: 222px;">
			                <option value="">:: 선택 ::</option>
			                <option value="서울" ${dto.clubAddr=="서울" ? "selected='selected'" : ""}>서울</option>
			                <option value="부산" ${dto.clubAddr=="부산" ? "selected='selected'" : ""}>부산</option>
			                <option value="대구" ${dto.clubAddr=="대구" ? "selected='selected'" : ""}>대구</option>
			                <option value="인천" ${dto.clubAddr=="인천" ? "selected='selected'" : ""}>인천</option>
			                <option value="광주" ${dto.clubAddr=="광주" ? "selected='selected'" : ""}>광주</option>
			                <option value="대전" ${dto.clubAddr=="대전" ? "selected='selected'" : ""}>대전</option>
			                <option value="울산" ${dto.clubAddr=="울산" ? "selected='selected'" : ""}>울산</option>
			                <option value="세종" ${dto.clubAddr=="세종" ? "selected='selected'" : ""}>세종</option>
			                <option value="강원" ${dto.clubAddr=="강원" ? "selected='selected'" : ""}>강원</option>
			                <option value="경기" ${dto.clubAddr=="경기" ? "selected='selected'" : ""}>경기</option>
			                <option value="충남" ${dto.clubAddr=="충남" ? "selected='selected'" : ""}>충남</option>
			                <option value="충북" ${dto.clubAddr=="충북" ? "selected='selected'" : ""}>충북</option>
			                <option value="전남" ${dto.clubAddr=="전남" ? "selected='selected'" : ""}>전남</option>
			                <option value="전북" ${dto.clubAddr=="전북" ? "selected='selected'" : ""}>전북</option>
			                <option value="경남" ${dto.clubAddr=="경남" ? "selected='selected'" : ""}>경남</option>
			                <option value="경북" ${dto.clubAddr=="경북" ? "selected='selected'" : ""}>경북</option>
			                <option value="제주" ${dto.clubAddr=="제주" ? "selected='selected'" : ""}>제주</option>
			            </select>
        <p class="help-block">주활동 지역을 선택해주세요</p>
     </div>
    </div>
  <div class="form-group">
        <label class="col-sm-2 control-label" for="photoFilename">사진</label>
        <div class="col-sm-7">
            <div class="fileinput fileinput-new" data-provides="fileinput" style="float: left;">
                <div class="fileinput-preview thumbnail" style="width: 130px; height: 150px;"></div>
                <div>
                     <span class="btn btn-default wbtn btn-file"><span class="fileinput-new">이미지 선택</span><span class="fileinput-exists">변경</span><input type="file" name="uploads" id="photoFilename" accept="image/png, image/jpeg, image/gif"></span>
                     <a href="#" class="btn btn-default wbtn fileinput-exists" data-dismiss="fileinput">삭제</a>
                </div>
            </div>
 <c:if test="${mode=='update'}">
            <div style="float: left; margin-left: 10px;">
               <c:if test="${not empty dto.photoFilename}">
                    <div style="width: 130px; height: 150px;  margin-bottom:10px; border: 1px solid #ddd; padding: 3px;"><img id="imgPhoto" src="<%=cp%>/uploads/club/${dto.photoFilename}" style="width: 100%; height: 100%;"></div>
                    <div style="padding-left: 15px;">
                         <span>등록 이미지</span>
                         <a id="btnDeletePhoto" href="javascript:imageDelete();" class="close" style="float: none">&times;</a>
                    </div>
                </c:if>
               <c:if test="${empty dto.photoFilename}">
                   <div style="width: 130px; height: 150px;  margin-bottom:10px; border: 1px solid #ddd; padding: 3px;"><img src="<%=cp%>/res/images/noimage.png" style="width: 100%; height: 100%;"></div>
                   <div style="padding-left: 15px;">
                         <span>등록 이미지</span>
                   </div>
                </c:if>
            </div>
</c:if> 
        </div>
    </div>
  <div class="form-group">
  <div class="col-sm-offset-2 col-sm-10">

            <button type="button" name="sendButton" class="btn btn-info btn-sm btn-search" onclick="sendClub();" style="margin-right:20px; height:40px; width:130px;">
                   ${mode=="created"?"등록완료":"수정완료"} <span class="glyphicon glyphicon-ok"></span></button>
            <button type="button" class="btn btn-default btn-sm wbtn" onclick="javascript:history.back();" style="margin-right:20px; height:40px; width:130px;">
                 ${mode=="created"?"등록취소":"수정취소"} <span class="glyphicon glyphicon-remove"></span></button>   
    </div>
</div>
</form>
</div> 
</div>