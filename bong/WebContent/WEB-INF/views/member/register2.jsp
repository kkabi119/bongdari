<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript" src="<%=cp%>/res/js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/util.js"></script>

<link rel="stylesheet" href="<%=cp%>/res/css/jquery-ui.min.css" type="text/css"/>


<link rel="stylesheet" href="<%=cp%>/res/css/style.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/res/css/layout/layout.css" type="text/css"/>


<link href="<%=cp %>/res/css/bootstrap.css" rel="stylesheet">

<script type="text/javascript">

//아이디 중복 검사
function userIdCheck() {
	var userId=$("#userId").val();
	if(!/^[a-z][a-z0-9_]{4,9}$/i.test(userId)) { 
		var str="아이디는 5~10자 이내이며, 첫글자는 영문자로 시작해야 합니다.";
		$("#userId").focus();
		$("#userId + .help-block").html(str);
		return false;
	}
	
	var url="<%=cp%>/member/userIdCheck";
	var params="userId="+userId;
	$.ajax({
		type:"POST"
		,url:url
		,data:params
		,dataType:"JSON"
		,success:function(data) {
			var passed=data.passed;
			if(passed=="true") {
				var str="<span style='color:blue;font-weight: bold;'>"+userId+"</span> 아이디는 사용가능 합니다.";
				$("#userId + .help-block").html(str);
			} else {
				var str="<span style='color:red;font-weight: bold;'>"+userId+"</span> 아이디는 사용할수 없습니다.";
				$("#userId + .help-block").html(str);
				$("#userId").val("");
				$("#userId").focus();
			}
		}
	});
}

function register() {
   var f = document.registerForm;;
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
   }   
    str = f.userBirth.value;
    if(!isValidDateFormat(str)) {
       $("#userBirth + .help-block").html("<span style='color:red;'>생일 형식을 확인해주세요!<span>");
        f.userBirth.focus();
        return false;
    } else {
      $("#userBirth + .help-block").html("  생년월일은 2000-01-01 형식으로 입력 합니다.");
   }
/*   
    str = f.email2.value;
    if(!/@[0-9a-zA-Z]([0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(str)) {
       $("#userEmail + .help-block").html("<span style='color:red;'> 이메일 형식을 확인해주세요!<span>");
        f.email2.focus();
        return false;
    }else {
      $("#userEmail + .help-block").html("");
   }
    */
    str = f.tel1.value;
    if(!str) {
       $("#tel3 + .help-block").html("<span style='color:red;'>전화번호를 확인해주세요!<span>");
        f.tel1.focus();
        return false;
    }else {
      $("#tel3 + .help-block").html("");
   }

    str = f.tel2.value;
    if(!/^[0-9]{4}$/g.test(str)) {
       $("#tel3 + .help-block").html("<span style='color:red;'>전화번호를 확인해주세요!<span>");
        f.tel2.focus();
        return false;
    }else {
      $("#tel3 + .help-block").html("");
   }
    
    str = f.tel3.value;
    if(!/^[0-9]{4}$/g.test(str)) {
       $("#tel3 + .help-block").html("<span style='color:red;'>전화번호를 확인해주세요!<span>");
        f.tel3.focus();
        return false;
    }else {
      $("#tel3 + .help-block").html("");
   }
    
    var failed="${failed}";
   if(failed=="true"){
      alert("회원가입에 실패했습니다!");
   }
   
    var mode="${mode}"
    if(mode=="created") {
          f.action = "<%=cp%>/member/register";
    } else if(mode=="update") {
       f.action = "<%=cp%>/member/update_ok.sst";
    }

    return true;
}
function changeEmail() {
    var f = document.memberForm;
    
    var str = f.selectEmail.value;
    if(str!="direct") {
         f.email2.value=str; 
         f.email2.readOnly = true;
         f.email1.focus(); 
    }
    else {
        f.email2.value="@";
        f.email2.readOnly = false;
        f.email1.focus();
    }
}
</script>

</head>
<body>
<div class="container" role="main" style="margin-top:50px;">

  <div class="bodyFrame">
  <form class="form-horizontal" name="memberForm" method="post" onsubmit="return register();">
    <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label" for="userId">아이디</label>
        <div class="col-sm-7">
            <input style="width:200px;"class="form-control" id="userId" name="userId" type="text" 
                  placeholder="아이디"  value="${dto.userId}" onchange="userIdCheck();"
              ${mode=="update" ? "readonly='readonly' style='border:none;'":""}>
            <p class="help-block"> 아이디는 5~10자 이내이며, 첫글자는 영문자로 시작해야 합니다.</p>
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
                  type="text" placeholder="이름"   value="${dto.userName}" ${mode=="update" ? "readonly='readonly'
                  style='border:none;' ":""}>
        <p class="help-block">이름은 한글로 2자이상 4자 이하입니다.</p>
     </div>
    </div>
    <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="userBirth">생년월일</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="userBirth" name="userBirth" type="text" placeholder="생년월일" value="${dto.userBirth}">
            <p class="help-block">생년월일은 2000-01-01 형식으로 입력 합니다.</p>
        </div>
    </div>
  <%--  <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="userTel">전화번호</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="userTel" name="userTel" type="text" placeholder="전화번호" value="${dto.userTel}">
         
        </div>
    </div> --%>
     <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="userEmail">이메일</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="userEmail" name="userEmail" type="text" placeholder="이메일" value="${dto.userEmail}">
         
        </div>
    </div>
 <%-- <div class="form-group">
        <label class="col-sm-2 control-label" for="userEmail">이메일</label>
        <div class="col-sm-10" style="margin-top:0px;">
           <select name="selectEmail" onchange="changeEmail();" class="form-control" style="width:130px; float: left; margin-right:10px; margin:0px; padding:0px;" >
                                 <option value="">선 택</option>
                                 <option value="@naver.com" ${dto.email2=="naver.com" ? "selected='selected'" : ""}>네이버 메일</option>
                                 <option value="@hanmail.net" ${dto.email2=="hanmail.net" ? "selected='selected'" : ""}>한 메일</option>
                                 <option value="@hotmail.com" ${dto.email2=="hotmail.com" ? "selected='selected'" : ""}>핫 메일</option>
                                 <option value="@gmail.com" ${dto.email2=="gmail.com" ? "selected='selected'" : ""}>지 메일</option>
                                 <option value="direct">직접입력</option>
      </select>
       <input style="width:150px; float:left; margin-right:10px;" type="text" name="email1" size="13" 
             maxlength="30" id="email"  class="form-control" value="${dto.email1}">
         
      <input style="width:150px;  float:left; margin-right:10px; " type="text" name="email2" size="13"
             maxlength="30" id="email"  class="form-control" value="@${dto.email2}" readonly="readonly">
         <p class="help-block"> </p>
         </div>
    </div> --%>
    
    <div class="form-group" >
        <label class="col-sm-2 control-label" for="tel1">전화번호</label>
        <div class="col-sm-7">
             <div class="row" >
                  <div class="col-sm-3" style="padding-right: 5px;">
                    <select class="form-control" id="tel1" name="tel1" >
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
            <input class="form-control" id="userAddr" name="userAddr" type="text" placeholder="기본주소" value="${dto.userAddr}">
            
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
        <input type="radio" name="userGender" id="male" checked>남자
        <input type="radio" name="userGender" id="female" >여자 			
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

    
<div class="form-group">
  <div class="col-sm-offset-2 col-sm-10">
      <c:if test="${mode=='created'}">
            <button type="submit" name="sendButton" class="btn btn-info btn-sm btn-search" style="margin-right:20px; height:40px; width:130px;">
                  회원가입<span class="glyphicon glyphicon-ok"></span></button>
            <button type="button" class="btn btn-default btn-sm wbtn" onclick="javascript:location.href='<%=cp%>/member/login';" style="margin-right:20px; height:40px; width:130px;">
                  가입취소 <span class="glyphicon glyphicon-remove"></span></button>
       </c:if>
   <c:if test="${mode=='update'}">
            <button type="submit" class="btn btn-info btn-sm btn-search" style="margin-right:20px; height:40px; width:130px;" >
                  정보수정 <span class="glyphicon glyphicon-ok"></span></button>
            <button type="button" class="btn btn-default btn-sm wbtn" onclick="javascript:location.href='<%=cp%>/';" style="margin-right:20px; height:40px; width:130px;">
                  수정취소 <span class="glyphicon glyphicon-remove"></span></button>
   </c:if>            
    </div>
</div>

  <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                <p style=" text-align:center; color:tomato; font-weight:bold; font-size:13px; "class="form-control-static">${message}</p>
        </div>
    </div>  
     
  </form>
  </div>
 </div>

 <div class="container">
<!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12"  style="margin-left: 50px;">
                    <p>Copyright &copy; SIST Comm 2016</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

<script type="text/javascript" src="<%=cp%>/res/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript" src="<%=cp%>/res/js/bootstrap.min.js"></script>
</body>
</html>