<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
 <link rel="shortcut icon" href="resources/img/favicon.png" type="image/x-icon"/>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="resources/css/estilo1.css"/>
<link rel="stylesheet" href="resources/css/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/bootstrap.css">

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>   
<script  type="text/javascript" src="resources/javascript/scripty.js"></script>

 
<title>Login</title>

</head>
<body>
<div class = "container-fluid">
	<div class="wrapper">
		<div id="formlogin">	
			<form id="formulario" method="post" name="formulario" action="validaLogin" class="form-signin" >
				<h3 class="form-signin-heading">Bem Vindo!</h3>
				<hr class="colorgraph"><br>
	
				<label> Login </labe>
					<input type="text" class="form-control" name="j_username" id="j_username" placeholder="E-mail" required="" autofocus="" /><br>
			
				<label> Senha </label>
					<input type="password" class="form-control" name="j_password" id="j_password" placeholder="Senha" required=""/><br>
						
				<button class="btn btn-lg btn-primary btn-block" type="submit" onclick="return validarLogin();" id="buttonLogin"> Entrar </button>

			</form>
		
		</div>
	</div>
</div>

</body>
</html>