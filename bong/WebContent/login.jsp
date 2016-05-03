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
						  
					<div class="Login">
							<div class="Login-head">
						    	<h3>LOGIN</h3>
						 	</div>

						<form>
								<div class="ticker">
									<h4>아이디</h4>
						  			<input type="text" value="봉다리" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '봉다리';}" ><span> </span>
						  			<div class="clear"> </div>
						  		</div>
						  		<div>
						  		<h4>비밀번호</h4>
								<input type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}" >
								  			<div class="clear"> </div>
								</div>
								<div class="checkbox-grid">
									<div class="inline-group green">
									<label class="radio"><input type="radio" name="radio-inline"><i> </i>Remember me</label>
									<div class="clear"> </div>
									</div>

								</div>
												 
								<div class="submit-button">
									<input type="submit" onclick="login()" value="LOGIN  >" >
								</div>
									<div class="clear"> </div>
						</form>
					</div>
											
						  
					</div>
			</div>
				<!--//End-login-form-->	
						<div class ="copy-right">
							<p>Template by <a href="#">W3layouts</a></p>
						</div>
			  </div>
	</body>
</html>