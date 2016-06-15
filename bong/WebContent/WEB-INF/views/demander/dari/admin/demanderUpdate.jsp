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
function demanderUpdate() {
   var f = document.demanderUpdateForm;
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
    if(!/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str)) { 
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
    
    str = f.userName.value;
    str = $.trim(str);

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
    str = f.serviceBirth.value;
    if(!str) {
       $("#serviceBirth + .help-block").html("<span style='color:red;'>생일 형식을 확인해주세요!<span>");
        f.serviceBirth.focus();
        return false;
    } else {
      $("#serviceBirth + .help-block").html("  생년월일은 2000-01-01 형식으로 입력 합니다.");
   }
   
    str = f.email2.value;
    if(!/[0-9a-zA-Z]([0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(str)) {
       $("#serviceEmail + .help-block").html("<span style='color:red;'> 이메일 형식을 확인해주세요!<span>");
        f.email2.focus();
        return false;
    }else {
      $("#serviceEmail + .help-block").html("");
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
        $("#serviceTel + .help-block").html("<span style='color:red;'>번호만 입력해주세요<span>");
         f.tel1.focus();
         return false;
     } else {
       $("#serviceTel + .help-block").html("  전화번호는 번호만 입력해주세요");
    }
    var failed="${failed}";
   if(failed=="true"){
      alert("회원가입에 실패했습니다!");
   } 
   
    var mode="${mode}"
    if(mode=="created") {
          f.action = "<%=cp%>/member/register";
    } else if(mode=="update") {
       f.action = "<%=cp%>/demander/${demander_seq}/admin/tab3";
       
    }

    return true;
}
function changeEmail() {
    var f = document.demanderUpdateForm;
    
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
		var url="<%=cp%>/member/${demander_seq}/imageDelete";
		var filename="${dto.serviceImg}";
		$.post(url, {filename:filename}, function(data){
			var isLogin=data.isLogin;
			if(isLogin==false){
				location.href="<%=cp%>/member/login";
				return;
			}
			
			$("#imgPhoto").attr("src", "<%=cp%>/res/images/noimage.png");
			$("#btnDeletePhoto").hide();
			
			var f = document.demanderUpdateForm;
			f.serviceImgname.value="";
			f.serviceImg.value="";
			
		},"json");
	}
}
function searchDemanderAddr() {
    $("#modalZip").val("");
	$("#modalDemanderAddr").modal("show");	
}


function modalSearchDemanderAddr() {
	var zipp = $("#modalDemanderZip").val();
	var url = "<%=cp%>/demanderjoin/zip";
	$.post(url, {zip:zipp},function(data){
		$("#resultZip").html(data);
	});

}

function selectDemanderZip(zip){
	$("#modalDemanderAddr").modal("hide");
	$("#addr1").val(zip);
}
</script>



  <div class="bodyFrame">
  <form class="form-horizontal" name="demanderUpdateForm" method="post" onsubmit="return demanderUpdate();" enctype="multipart/form-data">
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
        <label class="col-sm-2 control-label" for="serviceName">이름</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="userName" name="serviceName" 
                  type="text" placeholder="이름"   value="${dto.serviceName}" readonly="readonly">
        <p class="help-block">이름은 한글로 2자이상 4자 이하입니다.</p>
     </div>
    </div>
  <div class="form-group">
        <label class="col-sm-2 control-label" for="serviceImgname">사진</label>
        <div class="col-sm-7">
            <div class="fileinput fileinput-new" data-provides="fileinput" style="float: left;">
                <div class="fileinput-preview thumbnail" style="width: 130px; height: 150px;"></div>
                <div>
                     <span class="btn btn-default wbtn btn-file"><span class="fileinput-new">이미지 선택</span><span class="fileinput-exists">변경</span><input type="file" name="uploads" accept="image/png, image/jpeg, image/gif"></span>
                     <a href="#" class="btn btn-default wbtn fileinput-exists" data-dismiss="fileinput">삭제</a>
                </div>
            </div>

            <div style="float: left; margin-left: 10px;">
               <c:if test="${not empty dto.serviceImgname}">
                    <div style="width: 130px; height: 150px;  margin-bottom:10px; border: 1px solid #ddd; padding: 3px;"><img id="imgPhoto" src="<%=cp%>/uploads/serviceImg/${dto.serviceImg}" style="width: 100%; height: 100%;"></div>
                    <div style="padding-left: 15px;">
                         <span>등록 이미지</span>
                         <a id="btnDeletePhoto" href="javascript:imageDelete();" class="close" style="float: none">&times;</a>
                    </div>
                </c:if>
               <c:if test="${empty dto.serviceImgname}">
                   <div style="width: 130px; height: 150px;  margin-bottom:10px; border: 1px solid #ddd; padding: 3px;"><img src="<%=cp%>/res/images/noimage.png" style="width: 100%; height: 100%;"></div>
                   <div style="padding-left: 15px;">
                         <span>등록 이미지</span>
                   </div>
                </c:if>
            </div>

        </div>
    </div>
    <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="serviceBirth">설립일</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="serviceBirth" name="serviceBirth" type="text" placeholder="생년월일" value="${dto.serviceBirth}">
            <p class="help-block">설립일은 2000-01-01 형식으로 입력 합니다.</p>
        </div>
    </div>

<div class="form-group">
        <label class="col-sm-2 control-label" for="servicerEmail">이메일</label>
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
        <label class="col-sm-2 control-label" for="tel1">기관번호</label>
        <div class="col-sm-7">
             <div class="row" >
                  <div class="col-sm-3" style="padding-right: 5px;">
                    <select class="form-control" style="width:130px; float: left; margin:0px; padding:0px;"id="tel1" name="tel1" >
                        <option value="">선 택</option>
                        <option value="02" ${dto.tel1=="02" ? "selected='selected'" : ""}>02</option>
                        <option value="031" ${dto.tel1=="031" ? "selected='selected'" : ""}>031</option>
                        <option value="032" ${dto.tel1=="032" ? "selected='selected'" : ""}>032</option>
                        <option value="033" ${dto.tel1=="033" ? "selected='selected'" : ""}>033</option>
                        <option value="041" ${dto.tel1=="041" ? "selected='selected'" : ""}>041</option>
                        <option value="051" ${dto.tel1=="051" ? "selected='selected'" : ""}>051</option>
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
        <label class="col-sm-2 control-label" for="serviceAddr">주소</label>
        <div class="col-sm-7">
             <input class="form-control" id="addr1" name="addr1" type="text" placeholder="기본주소" value="${dto.addr1}" readonly="readonly">
            <input class="form-control" id="addr2" name="addr2" type="text" placeholder="상세주소" value="${dto.addr2}">
         <button type="button" onclick="searchDemanderAddr()" class="btn btn-info btn-sm btn-search" style="margin-right:20px; height:40px; width:130px;">
                  주소검색<span class="glyphicon glyphicon-ok"></span></button>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="serviceIntro">수요처 소개</label>
        <div class="col-sm-7">
        <textarea class="form-control" rows="5" cols="80" id="serviceIntro" name="serviceIntro" placeholder="수요처소개">${dto.serviceIntro}</textarea>
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
		
		<input type="hidden" name="serviceImgname" value="${dto.serviceImgname}">	
		<input type="hidden" name="serviceImg" value="${dto.serviceImg}">	
		
	
			
  <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                <p style=" text-align:center; color:tomato; font-weight:bold; font-size:13px; "class="form-control-static">${message}</p>
        </div>
    </div>  
     
  </form>
  </div>
   <div id="modalDemanderAddr" class="modal fade" tabindex="-1" role="dialog"
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
						<label class="control-label" for="modalDemanderZip">도로명을 입력해주세요</label> <input
							class="form-control" id="modalDemanderZip" name="zip" type="text"
							placeholder="도로명" onkeydown="enter(search)">
					</div>
					<div id="resultZip"></div>
					<div class="form-group">
						<button class="btn btn-lg btn-primary btn-block" type="button"
							name="search" onclick="modalSearchDemanderAddr();">
							검색<span class="glyphicon glyphicon-ok"></span>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
    <!-- /.container -->

