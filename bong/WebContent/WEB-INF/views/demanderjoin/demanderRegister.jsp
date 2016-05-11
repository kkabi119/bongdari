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

function demanderRegister() {
   var f = document.registerForm;;
   var str;

   str=f.serviceId.value;
   if(!/^[a-z][a-z0-9_]{4,9}$/i.test(str)) { 
      $("#serviceId + .help-block").html("<span style='color:red;'>아이디를 확인해주세요! <span>");
      f.serviceId.focus();
      return false;
   }else {
      $("#serviceId + .help-block").html("아이디는 5~10자 이내이며, 첫글자는 영문자로 시작해야 합니다.");
   }
   
   str = f.servicePwd.value;
   if(!/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str)) { 
      $("#servicePwd + .help-block").html("<span style='color:red;'>비밀번호 형식을 확인해주세요! <span>");
      f.servicePwd.focus();
      return false;
   }else {
      $("#servicePwd + .help-block").html("패스워드는 5~10자이며 하나 이상의 숫자나 특수문자가 포함되어야 합니다.");
   }
   
   if(f.servicePwdCheck.value != str) {
      $("#servicePwdCheck + .help-block").html("<span style='color:red;'>비밀번호가 일치하지 않습니다! <span>");
      f.servicePwdCheck.focus();
      return false;
   } else {
      $("#servicePwdCheck + .help-block").html("비밀번호를 한번 더 입력해주세요");
   }
   
    str = f.serviceName.value;
   str = $.trim(str);

    if(!/^[\uac00-\ud7a3]{2,4}$/g.test(str)) {
       $("#serviceName + .help-block").html("<span style='color:red;'>이름을 확인해주세요! <span>");
        f.serviceName.focus();
        return false;
    } 
    else {
      $("#serviceName + .help-block").html("이름은 한글로 2자이상 4자 이하입니다.");
   }   
    str = f.serviceBirth.value;
    if(!isValidDateFormat(str)) {
       $("#serviceBirth + .help-block").html("<span style='color:red;'>생일 형식을 확인해주세요!<span>");
        f.serviceBirth.focus();
        return false;
    } else {
      $("#serviceBirth + .help-block").html("  생년월일은 2000-01-01 형식으로 입력 합니다.");
   }
/*   
    str = f.email2.value;
    if(!/@[0-9a-zA-Z]([0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(str)) {
       $("#serviceEmail + .help-block").html("<span style='color:red;'> 이메일 형식을 확인해주세요!<span>");
        f.email2.focus();
        return false;
    }else {
      $("#serviceEmail + .help-block").html("");
   }
    
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
     */
    var failed="${failed}";
   if(failed=="true"){
      alert("회원가입에 실패했습니다!");
   }
   
    var mode="${mode}"
    if(mode=="created") {
          f.action = "<%=cp%>/demanderjoin/demanderRegister";
    } else if(mode=="update") {
       f.action = "<%=cp%>/member/update_ok.sst";
    }

    return true;
}
</script>
<script>

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
  <form class="form-horizontal" name="demanderjoinForm" method="post" onsubmit="return demanderRegister();">
    <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label" for="serviceId">아이디</label>
        <div class="col-sm-7">
            <input style="width:200px;"class="form-control" id="serviceId" name="serviceId" type="text" 
                  placeholder="아이디"  value="${dto.serviceId}"
              ${mode=="update" ? "readonly='readonly' style='border:none;'":""}>
            <p class="help-block"> 아이디는 5~10자 이내이며, 첫글자는 영문자로 시작해야 합니다.</p>
        </div>
    </div>
 
    <div class="form-group" style="margin-bottom:0px;">
        <label class="col-sm-2 control-label" for="servicePwd">패스워드</label>
        <div class="col-sm-7">
            <input style="width:200px;  " class="form-control" id="servicePwd" name="servicePwd" type="password" placeholder="비밀번호">
            <p class="help-block">패스워드는 5~10자이며 하나 이상의 숫자나 특수문자가 포함되어야 합니다.</p>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-2 control-label" for="servicePwdCheck">패스워드 확인</label>
        <div class="col-sm-7">
            <input style="width:200px; "class="form-control" id="servicePwdCheck" name="servicePwdCheck" type="password" placeholder="비밀번호 확인">
            <p class="help-block">패스워드를 한번 더 입력해주세요.</p>
        </div>
    </div>
 
    <div class="form-group">
        <label class="col-sm-2 control-label" for="serviceName">기관명</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="serviceName" name="serviceName" 
                  type="text" placeholder="기관명"   value="${dto.serviceName}" ${mode=="update" ? "readonly='readonly'
                  style='border:none;' ":""}>
        <p class="help-block">이름은 한글로 2자이상 4자 이하입니다.</p>
     </div>
    </div>
    <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="serviceBirth">설립일</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="serviceBirth" name="serviceBirth" type="text" placeholder="설립일" value="${dto.serviceBirth}">
            <p class="help-block">설립일은 2000-01-01 형식으로 입력 합니다.</p>
        </div>
    </div>
   <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="serviceTel">기관번호</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="serviceTel" name="serviceTel" type="text" placeholder="기관번호" value="${dto.serviceTel}">
         
        </div>
    </div>
     <div style=""class="form-group">
        <label class="col-sm-2 control-label" for="serviceEmail">이메일</label>
        <div class="col-sm-7">
            <input style="width:200px; " class="form-control" id="serviceEmail" name="serviceEmail" type="text" placeholder="이메일" value="${dto.serviceEmail}">
         
        </div>
    </div>
   <%--  <div class="form-group">
        <label class="col-sm-2 control-label" for="serviceEmail">이메일</label>
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
    </div>
    
    <div class="form-group" >
        <label class="col-sm-2 control-label" for="tel1">전화번호</label>
        <div class="col-sm-7">
             <div class="row" >
                  <div class="col-sm-3" style="padding-right: 5px;">
                    <select class="form-control" style="width:130px; float: left; margin:0px; padding:0px;"id="tel1" name="tel1" >
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
    </div>   --%>  
    <div class="form-group">
        <label class="col-sm-2 control-label" for="serviceAddr">주소</label>
        <div class="col-sm-7">
            <input class="form-control" id="serviceAddr" name="serviceAddr" type="text" placeholder="기본주소" value="${dto.serviceAddr}">
            
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
            <button type="submit" class="btn btn-default btn-sm wbtn" style="margin-right:20px; height:40px; width:130px;">
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