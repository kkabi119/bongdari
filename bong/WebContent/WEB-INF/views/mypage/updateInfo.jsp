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
function enter(where){
	if(event.keyCode == 13){
		where.focus();
	}
}
function updateInfo() {
   var f = document.memberUpdateForm;
   var str;

   str=f.userId.value;
   if(!/^[a-z][a-z0-9_]{4,9}$/i.test(str)) { 
      $("#userId + .help-block").html("<span style='color:red;'>아이디를 확인해주세요! <span>");
      f.userId.focus();
      return false;
   }else {
      $("#userId + .help-block").html("아이디는 5~10자 이내이며, 첫글자는 영문자로 시작해야 합니다.");
   }
   
   str = f.userPwd.value;
  /*  if(!/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str)) { 
      $("#userPwd + .help-block").html("<span style='color:red;'>비밀번호 형식을 확인해주세요! <span>");
      f.userPwd.focus();
      return false;
   }else {
      $("#userPwd + .help-block").html("패스워드는 5~10자이며 하나 이상의 숫자나 특수문자가 포함되어야 합니다.");
   }
   
   if(f.userPwdCheck.value != str) {
      $("#userPwdCheck + .help-block").html("<span style='color:red;'>비밀번호가 일치하지 않습니다! <span>");
      f.userPwdCheck.focus();
      return false;
   } else {
      $("#userPwdCheck + .help-block").html("비밀번호를 한번 더 입력해주세요");
   }
    */
    str = f.userName.value;
 /*    str = $.trim(str);

    if(!/^[\uac00-\ud7a3]{2,4}$/g.test(str)) {
       $("#userName + .help-block").html("<span style='color:red;'>이름을 확인해주세요! <span>");
        f.userName.focus();
        return false;
    } 
    else {
      $("#userName + .help-block").html("이름은 한글로 2자이상 4자 이하입니다.");
   }    */

   
   str=f.uploads.value;
   
    if(str){
    	if(! isImageFile(f.uploads.value)){
    		alert("이미지파일만 가능합니다.")
    		f.uploads.focus();
    		return false;
    	}
    }
   
    str = f.userBirth.value;
    if(!str) {
       $("#userBirth + .help-block").html("<span style='color:red;'>생일 형식을 확인해주세요!<span>");
        f.userBirth.focus();
        return false;
    } else {
      $("#userBirth + .help-block").html("  생년월일은 2000-01-01 형식으로 입력 합니다.");
   }
    
    str = f.email2.value;
    if(!/[0-9a-zA-Z]([0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(str)) {
       $("#userEmail + .help-block").html("<span style='color:red;'> 이메일 형식을 확인해주세요!<span>");
        f.email2.focus();
        return false;
    }else {
      $("#userEmail + .help-block").html("");
   }

    str = f.tel1.value;
    if(!str) {
        f.tel1.focus();
        return false;
    }

    str = f.tel2.value;
    if(!/^(\d+)$/.test(str)) {
        f.tel2.focus();
        return false;
    }

    str = f.tel3.value;
    if(!/^(\d+)$/.test(str)) {
        f.tel3.focus();
        return false;
    } 
    if(!str) {
        $("#userTel + .help-block").html("<span style='color:red;'>번호만 입력해주세요<span>");
         f.tel1.focus();
         return false;
     } else {
       $("#userTel + .help-block").html("  전화번호는 번호만 입력해주세요");
    }
    str = f.userGender.value;  
    
    var failed="${failed}";
   if(failed=="true"){
      alert("회원가입에 실패했습니다!");
   } 
   
    var mode="${mode}"
    	
    if(mode=="created") {
       f.action = "<%=cp%>/member/register";
    } else if(mode=="update") {
       f.action = "<%=cp%>/member/index/updateInfo";
    }

    return true;
}
function changeEmail() {
    var f = document.memberUpdateForm;
    
    var str = f.selectEmail.value;
    if(str!="direct") {
         f.email2.value=str; 
         f.email2.readOnly = true;
         f.email1.focus(); 
    }
    else {
        f.email2.value="";
        f.email2.readOnly = false;
        f.email1.focus();
    }
}

function imageDelete(){
	if(confirm("등록된 사진을 삭제 하시겠습니까?")){
		var url="<%=cp%>/member/index/imageDelete";
		var filename="${dto.memImg}";
		$.post(url, {filename:filename}, function(data){
			var isLogin=data.isLogin;
			if(isLogin==false){
				location.href="<%=cp%>/member/login";
				return;
			}
			
			$("#imgPhoto").attr("src", "<%=cp%>/res/images/noimage.png");
			$("#btnDeletePhoto").hide();
			
			var f = document.memberUpdateForm;
			f.memImgname.value="";
			f.memImg.value="";
			
		},"json");
	}
}
function searchAddr() {
    $("#modalZip").val("");
	$("#modalAddr").modal("show");	
}


function modalSearchAddr() {
	var zipp = $("#modalZip").val();
	var url = "<%=cp%>/member/zip";
	$.post(url, {zip:zipp},function(data){
		$("#resultZip").html(data);
	});

}

function selectZip(zip){
	$("#modalAddr").modal("hide");
	$("#addr1").val(zip);
}
</script>

<div class="container" role="main" style="margin-top:50px;">

  <div class="bodyFrame">
  <form class="form-horizontal" name="memberUpdateForm" method="post" onsubmit="return updateInfo();" enctype="multipart/form-data">
    <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label" for="userId">아이디</label>
        <div class="col-sm-7">
            <input style="width:200px;"class="form-control" id="userId" name="userId" type="text" 
                  placeholder="아이디" onchange="userIdCheck();" value="${dto.userId}" readonly="readonly">

        </div>
    </div>
 
    <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label" for="userPwd">패스워드</label>
        <div class="col-sm-7">
            <input style="width:200px;  " class="form-control" id="userPwd" name="userPwd" type="password" placeholder="비밀번호">
            <p class="help-block">패스워드는 5~10자이며 하나 이상의 숫자나 특수문자가 포함되어야 합니다.</p>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label" for="userPwdCheck">패스워드 확인</label>
        <div class="col-sm-7">
            <input style="width:200px; "class="form-control" id="userPwdCheck" name="userPwdCheck" type="password" placeholder="비밀번호 확인">
            <p class="help-block">패스워드를 한번 더 입력해주세요.</p>
        </div>
    </div>
 
    <div class="form-group">
        <label class="col-sm-2 control-label" for="userName">이름</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="userName" name="userName" 
                  type="text" placeholder="이름"   value="${dto.userName}" readonly="readonly">
        <p class="help-block">이름은 한글로 2자이상 4자 이하입니다.</p>
     </div>
    </div>
  <div class="form-group">
        <label class="col-sm-2 control-label" for="memImgname">사진</label>
        <div class="col-sm-7">
            <div class="fileinput fileinput-new" data-provides="fileinput" style="float: left;">
                <div class="fileinput-preview thumbnail" style="width: 130px; height: 150px;"></div>
                <div>
                     <span class="btn btn-default wbtn btn-file"><span class="fileinput-new">이미지 선택</span><span class="fileinput-exists">변경</span><input type="file" name="uploads" accept="image/png, image/jpeg, image/gif"></span>
                     <a href="#" class="btn btn-default wbtn fileinput-exists" data-dismiss="fileinput">삭제</a>
                </div>
            </div>

            <div style="float: left; margin-left: 10px;">
               <c:if test="${not empty dto.memImgname}">
                    <div style="width: 130px; height: 150px;  margin-bottom:10px; border: 1px solid #ddd; padding: 3px;"><img id="imgPhoto" src="<%=cp%>/uploads/memImg/${dto.memImg}" style="width: 100%; height: 100%;"></div>
                    <div style="padding-left: 15px;">
                         <span>등록 이미지</span>
                         <a id="btnDeletePhoto" href="javascript:imageDelete();" class="close" style="float: none">&times;</a>
                    </div>
                </c:if>
               <c:if test="${empty dto.memImgname}">
                   <div style="width: 130px; height: 150px;  margin-bottom:10px; border: 1px solid #ddd; padding: 3px;"><img src="<%=cp%>/res/images/noimage.png" style="width: 100%; height: 100%;"></div>
                   <div style="padding-left: 15px;">
                         <span>등록 이미지</span>
                   </div>
                </c:if>
            </div>

        </div>
    </div>
    <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="userBirth">생년월일</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="userBirth" name="userBirth" type="text" placeholder="생년월일" value="${dto.userBirth}">
            <p class="help-block">생년월일은 2000-01-01 형식으로 입력 합니다.</p>
        </div>
    </div>

<div class="form-group">
        <label class="col-sm-2 control-label" for="userEmail">이메일</label>
        <div class="col-sm-10" style="margin-top:0px;">
        <table>
        <tr>
        <td>
           <select name="selectEmail" onchange="changeEmail();" class="form-control" style="width:130px; float: left; margin-right:10px; margin:0px; padding:0px;" >
                                 <option value="">선 택</option>
                                 <option value="naver.com" ${dto.email2=="naver.com" ? "selected='selected'" : ""}>네이버 메일</option>
                                 <option value="hanmail.net" ${dto.email2=="hanmail.net" ? "selected='selected'" : ""}>한 메일</option>
                                 <option value="hotmail.com" ${dto.email2=="hotmail.com" ? "selected='selected'" : ""}>핫 메일</option>
                                 <option value="gmail.com" ${dto.email2=="gmail.com" ? "selected='selected'" : ""}>지 메일</option>
                                 <option value="direct">직접입력</option>
      </select>
      </td>
      <td>
       <input style="width:150px; float:left; margin-right:10px;" type="text" name="email1" size="13" 
             maxlength="30" id="email1"  class="form-control" value="${dto.email1}">@&nbsp;&nbsp;
       </td>
       <td>      
       <input style="width:150px;  float:left; margin-right:10px; " type="text" name="email2" size="13"
             maxlength="30" id="email2"  class="form-control" value="${dto.email2}" readonly="readonly">
         </td>
         </tr>
         </table>
         </div>
    </div> 
    
    <div class="form-group" >
        <label class="col-sm-2 control-label" for="tel1">전화번호</label>
        <div class="col-sm-7">
             <div class="row" >
                  <div class="col-sm-2" style="padding-left: 13px;">
                    <select class="form-control" id="tel1" name="tel1" style="width:100px;">
                        <option value="">선 택</option>
                        <option value="010" ${dto.tel1=="010" ? "selected='selected'" : ""}>010</option>
                        <option value="011" ${dto.tel1=="011" ? "selected='selected'" : ""}>011</option>
                        <option value="016" ${dto.tel1=="016" ? "selected='selected'" : ""}>016</option>
                        <option value="017" ${dto.tel1=="017" ? "selected='selected'" : ""}>017</option>
                        <option value="018" ${dto.tel1=="018" ? "selected='selected'" : ""}>018</option>
                        <option value="019" ${dto.tel1=="019" ? "selected='selected'" : ""}>019</option>
                    </select>
                  </div>

                  <div class="col-sm-1" style="width: 1%; padding-left: 5px; padding-right: 10px;">
                         <p class="form-control-static">-</p>
                  </div>
                 <div class="col-sm-2" style="padding-left: 5px; padding-right: 5px;">
                     <input class="form-control" id="tel2" name="tel2" type="text" value="${dto.tel2}" maxlength="4">
                  </div>
                  <div class="col-sm-1" style="width: 1%; padding-left: 5px; padding-right: 10px;">
                         <p class="form-control-static">-</p>
                  </div>
                  <div class="col-sm-6" style="padding-left: 5px; padding-right: 5px;">
                    <input style="width:100px;float:left; margin-right:10px; " class="form-control" id="tel3" 
                          name="tel3" type="text" value="${dto.tel3}" maxlength="4">
                         <span style="float:left; width:170px;" class="help-block" > </span>
                 
                  </div>
             </div>
        </div>
    </div>   
    <div class="form-group">
        <label class="col-sm-2 control-label" for="userAddr">주소</label>
        <div class="col-sm-7">
            <input class="form-control" id="addr1" name="addr1" type="text" placeholder="기본주소" value="${dto.addr1}" readonly="readonly">
            <input class="form-control" id="addr2" name="addr2" type="text" placeholder="상세주소" value="${dto.addr2}">
           <button type="button" onclick="searchAddr()" class="btn btn-info btn-sm btn-search" style="margin-right:20px; height:40px; width:130px;">
                  주소검색<span class="glyphicon glyphicon-ok"></span></button>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="userJob">직업</label>
        <div class="col-sm-2">
            <input class="form-control" id="userJob" name="userJob" type="text" placeholder="직업" value="${dto.userJob}">
        </div>
    </div>
    <div class="form-group">
       <label class="col-sm-2 control-label" for="userGender">성별</label>
       <div class="col-sm-2">
        <input type="radio" name="userGender" value="남자" id="male" checked>남자
        <input type="radio" name="userGender" value="여자" id="female" >여자 			
       </div>		
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="agree">약관 동의</label>
        <div class="col-sm-7 checkbox">
            <label>
                <input id="agree" name="agree" type="checkbox" checked="checked"
                         onchange="form.sendButton.disabled = !checked"> <a href="#">이용약관</a>에 동의합니다.
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-info btn-sm btn-search" style="margin-right:20px; height:40px; width:130px;" >
                  정보수정 <span class="glyphicon glyphicon-ok"></span></button>
    <button type="reset" class="btn btn-info btn-sm btn-search" style="margin-right:20px; height:40px; width:130px;" >
                  다시입력 <span class="glyphicon glyphicon-ok"></span></button>
            <button type="button" class="btn btn-default btn-sm wbtn" onclick="javascript:location.href='<%=cp%>/';" style="margin-right:20px; height:40px; width:130px;">
                  수정취소 <span class="glyphicon glyphicon-remove"></span></button>
		
		<input type="hidden" name="memImgname" value="${dto.memImgname}">	
		<input type="hidden" name="memImg" value="${dto.memImg}">	
		
	
			
  <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                <p style=" text-align:center; color:tomato; font-weight:bold; font-size:13px; "class="form-control-static">${message}</p>
        </div>
    </div>  
     
  </form>
  </div>
 </div>

<div id="modalAddr" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h2 id="myModalLabel" class="modal-title text-center fc-orange"
					style="font-family: sans-serif, 나눔고딕, 맑은 고딕; font-weight: bold;">주소
					검색</h2>
			</div>
			<div class="modal-body">
				<form name="modalSearchForm" method="post">
					<div class="form-group">
						<label class="control-label" for="modalZip">도로명을 입력해주세요</label> <input
							class="form-control" id="modalZip" name="zip" type="text"
							placeholder="도로명" onkeydown="enter(search)">
					</div>
					<div id="resultZip"></div>
					<div class="form-group">
						<button class="btn btn-lg btn-primary btn-block" type="button"
							name="search" onclick="modalSearchAddr();">
							검색<span class="glyphicon glyphicon-ok"></span>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
    <!-- /.container -->

