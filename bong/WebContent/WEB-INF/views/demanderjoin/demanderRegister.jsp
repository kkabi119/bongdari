<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>	
<title>Register-login-form Website Template | Home :: w3layouts</title>
<link href="<%=cp%>/res/css/member.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script type="text/javascript">
function demanderRegister(){
	var f = document.registerForm;
	
	f.action = "<%=cp%>/demanderjoin/demanderRegister";
}
</script>
<!--webfonts-->
<link href='http://fonts.googleapis.com/css?family=Lobster|Pacifico:400,700,300|Roboto:400,100,100italic,300,300italic,400italic,500italic,500' ' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,100,500,600,700,300' rel='stylesheet' type='text/css'>
<!--webfonts-->
</head>
<body>	
			<!--start-login-form-->
				<div class="main">
			    	<div class="login-head">
					    <h1>Elegant Login  and Register forms</h1>
					</div>
					<div  class="wrap">
						  <div class="Regisration">
						  	<div class="Regisration-head">
						    	<h2><span></span>demanderRegister</h2>
						 	 </div>
						  	<form name="registerForm" method="POST" onsubmit="return demanderRegister()">
						  		<input type="text" name="serviceId" placeholder="아이디" >
						  		<input type="text" name="serviceName" value="기관명" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '기관명';}" >
						  		<input type="password" name="servicePwd" value="비밀번호" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '비밀번호';}" >
								<input type="password" name="servicepwdConfirm" value="비밀번호확인" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '비밀번호확인';}" >
								<input type="text" name="serviceBirth" value="설립일 YYYY-MM-DD" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '설립일 YYYY-MM-DD';}" >
								<input type="text" name="serviceEmail" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" >
						  		<input type="text" name="serviceTel" value="전화번호" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '전화번호';}" >
						  		<input type="text" name="serviceAddr" value="주소" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '주소';}" >
						  		<!-- <input type="text" name="userJob" value="직업" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '직업';}" ><br> -->
								
								<!--<input type="radio" name="userGender" id="male" checked>남자  -->
								<!--<input type="radio" name="userGender" id="female" >여자  --> 
								
								<div class="Remember-me">
																				 
								<div class="submit">
									<input type="submit" value="Sign Me Up >" >
								</div>
									<div class="clear"> </div>
								</div>
											
						  </form>
					</div>
					</div>
			</div>
				<!--//End-login-form-->	
						<div class ="copy-right">
							<p>Template by <a href="#">W3layouts</a></p>
						</div>
</body>
</html>