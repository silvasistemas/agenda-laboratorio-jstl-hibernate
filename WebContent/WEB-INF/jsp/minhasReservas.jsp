<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="professor"
	class="br.com.project.model.classes.Entidade" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp" />
<title>Curso</title>
</head>
<c:choose>
	<c:when test="${usuarioLogado != null}">
		<body>
			<div id="menu" class="menu">
				<jsp:include page="menu.jsp" />
			</div>

			<div class="container-fluid">
				<h1>Minhas Reservas</h1>

				<table class="table table-responsive">
					<tr>
						<td>
							<form action="todasReservas" method="get">
								<input type="submit" value="Todas Reservas" class="btn btn-info btn-responsive">
							</form>
						</td>
						<td>
							<form action="minhasReservas" method="get">
								<input type="submit" value="Atualizar" class="btn btn-info btn-responsive">
							</form>
						</td>
						
					</tr>
				</table>

				<form action="reservaExcluir" method="get" id="reservaExcluir">
					<div class="container-fluid">
						<table class="table table-responsive table-hover">
							<thead class="table-active"	>
								<tr>
									<th>Laboratório</th>
									<th>Turma</th>
									<th>Data</th>
									<th>Hora</th>
									<th>Status</th>
									<th>Cancelar</th>
								</tr>
							</thead>
							<tbody>
								<tr align="left">
									<c:forEach items="${minhasReservas}" var="reserva">
										<tr align="left">
											<td>${reserva.laboratorio.laboratoriodb}</td>
											<td>${reserva.curso.cursodb}</td>
											<td>${reserva.datareserva}</td>
											<td>${reserva.horadb}</td>
											<td>${reserva.status}</td>
											<td><a href="reservaExcluir?tela=unica&id_reserva=${reserva.id_reserva}">Cancelar</a>
											</td>
										</tr>
								
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</body>
	</c:when>
	<c:otherwise>
		<%
			session.invalidate();
					response.sendRedirect("index.jsp");
		%>
	</c:otherwise>
</c:choose>
</html>