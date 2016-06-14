<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en-IN">
<head>
<meta charset="utf-8">
<title></title>
<link href='http://fonts.googleapis.com/css?family=Ropa+Sans' rel='stylesheet'>
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel='stylesheet'>
<style>
body{font-family: 'Ropa Sans', sans-serif; color:#666; font-size:14px; color:#333}
li,ul,body,input{margin:0; padding:0; list-style:none}
#login-form{width:350px; background:#FFF; margin:0 auto; margin-top:70px; background:#f8f8f8; overflow:hidden; border-radius:7px}
.form-header{display:table; clear:both}
.form-header label{display:block; cursor:pointer; z-index:999}
.form-header li{margin:0; line-height:60px; width:350px; text-align:center; background:#eee; font-size:18px; float:left; transition:all 600ms ease}
/*section*/
.section-out{width:700px; float:left; transition:all 600ms ease}
.section-out:after{content:''; clear:both; display:table}
.section-out section{width:350px; float:left}

.login{padding:20px}
.ul-list{clear:both; display:table; width:100%}
.ul-list:after{content:''; clear:both; display:table}
.ul-list li{ margin:0 auto; margin-bottom:12px}
.input{background:#fff; transition:all 800ms; width:247px; border-radius:3px 0 0 3px; font-family: 'Ropa Sans', sans-serif; border:solid 1px #ccc; border-right:none; outline:none; color:#999; height:40px; line-height:40px; display:inline-block; padding-left:10px; font-size:16px}
.input,.login span.icon{vertical-align:top}
.login span.icon{width:50px; transition:all 800ms; text-align:center; color:#999; height:40px; border-radius:0 3px 3px 0; background:#e8e8e8; height:40px; line-height:40px; display:inline-block; border:solid 1px #ccc; border-left:none; font-size:16px}
.input:focus:invalid{border-color:red}
.input:focus:invalid+.icon{border-color:red}
.input:focus:valid{border-color:green}
.input:focus:valid+.icon{border-color:green}
#check,#check1{top:1px; position:relative}
.btn{border:none; outline:none; background:#0099CC; border-bottom:solid 4px #006699; font-family: 'Ropa Sans', sans-serif; margin:0 auto;  height:40px; width:30%; padding:5 10px; border-radius:3px; font-size:16px; color:#FFF}

.social-login{padding:15px 20px; background:#f1f1f1; border-top:solid 2px #e8e8e8; text-align:right}
.social-login a{display:inline-block; height:35px; text-align:center; line-height:35px; width:35px; margin:0 3px; text-decoration:none; color:#FFFFFF}
.form a i.fa{line-height:35px}
.fb{background:#305891} .tw{background:#2ca8d2} .gp{background:#ce4d39} .in{background:#006699}
.remember{width:50%; display:inline-block; clear:both; font-size:14px}
.remember:nth-child(2){text-align:right}
.remember a{text-decoration:none; color:#666}

.hide{display:none}

/*swich form*/
#demander:checked~.section-out{margin-left:-350px}
#login:checked~.section-out{margin-left:0px}
#login:checked~div .form-header li:nth-child(1),#demander:checked~div .form-header li:nth-child(2){background:#e8e8e8}
</style>
<script type="text/javascript">
function enter(where){
	if(event.keyCode == 13){
		where.focus();
	}
}
function onclick_confirm(){
	alert("로그인");
}
function memberLogin() {
	var f = document.memberLoginForm;
	
	var str = f.userId.value;
	if(!str){
		f.userId.focus();
		return false;
	}
	str = f.userPwd.value;
	if(!str){
		f.userPwd.focus();
		return false;
	}
	
	f.action = "<%=cp%>/member/login_check";
	f.submit();
}

function join(){
	var url="<%=cp%>/member/register";
	location.href=url;
}
function demanderLogin() {
	var f = document.demanderLoginForm;
	
	var str = f.serviceId.value;
	if(!str){
		f.serviceId.focus();
		return false;
	}
	str = f.servicePwd.value;
	if(!str){
		f.servicePwd.focus();
		return false;
	}
	
	f.action = "<%=cp%>/member/login";
	f.submit();
}
function demanderJoin(){
	var url="<%=cp%>/demanderjoin/demanderRegister";
	location.href=url;
}
</script>
<style>
.padding-top{
	padding-top:0px;
	min-height:450px;
}
</style>
</head>
<body>
<div id="login-form">

<input type="radio" checked id="login" name="switch" class="hide">

<div>
<ul class="form-header">
<li><label for="login"><i class="fa fa-lock"></i> 회원 로그인</label></li>
</ul>
</div>

<div class="section-out">
<section class="login-section">
<div class="login">
<form  name="memberLoginForm" method="post">
<ul class="ul-list">
<li><input type="text" required class="input" placeholder="아이디" name="userId" id="userId" onkeydown="enter(userPwd)"/><span class="icon"><i class="fa fa-user"></i></span></li>
<li><input type="password" required class="input" placeholder="비밀번호" name="userPwd" id="userPwd"  onkeydown="enter(login)"/><span class="icon"><i class="fa fa-lock"></i></span></li>
<li><input type="button" onclick="memberLogin()" name="login" value="로그인" class="btn">&nbsp;<input type="button" onclick="join()" value="일반가입" class="btn">&nbsp;<input type="button" onclick="demanderJoin()" value="수요처가입" class="btn"></li>

</ul>
</form>
</div>

<!-- <li><span class="remember"><input type="checkbox" id="check"> <label for="check">Remember Me</label></span><span class="remember"><a href="">Forget Password</a></span></li> -->
</section>
</div>

</div>

</body>
</html>