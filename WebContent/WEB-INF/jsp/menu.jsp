<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/css/estilo1.css"/>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<script type="text/javascript">
	$(document).ready(function() {
		$('.nav li').hover(function() { //appearing on hover
			$('ul', this).fadeIn();
		}, function() { //disappearing on hover
			$('ul', this).fadeOut();
		});
	});

	$(document).ready(function() {
		$("input:text:first:visible").focus();
	});
</script>
<title>Agenda</title>
</head>
<body>
<nav class=" navbar navbar-inverse">
  <div class="container-fluid"> 
		<ul class="nav navbar-nav">
			<li>
				<a href="${pageContext.request.contextPath}/home">Início</a></li>
				<c:choose>
					<c:when test="${tipoUser == 'ADMIN'}">
						<li class="dropdown-toggle">
							<a href="#">Cadastro <span class="caret"></span></a>
							<ul  class="dropdown-menu">
								<li><a href="professor">Professor</a></li>
								<li><a href="curso">Curso</a></li>
								<li><a href="disciplina">Diciplina</a></li>
								<li><a href="laboratorio">Laboratorio</a></li>
							</ul></li>
					</c:when>
				</c:choose>
		
		<c:choose>
			<c:when test="${tipoUser == 'ADMIN'}">
				<li class="dropdown-toggle">
					<a href="#">Reservas <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="minhasReservas">Solicitações</a></li>
						<li><a href="todasReservas">Pendentes</a></li>
					</ul></li>
			</c:when>
		</c:choose>		
		<c:choose>
			<c:when test="${tipoUser == 'PROFESSOR'}">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Reservas <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="minhasReservas">Pré-Reservas</a></li>
						<li><a href="todasReservas">Reservas Confirmadas</a></li>
					</ul></li>
			</c:when>
		</c:choose>

		<li><a href="logout">Sair</a>
	</ul>
</div>
    </ul>
  </div>
</nav>
</body>
</html>