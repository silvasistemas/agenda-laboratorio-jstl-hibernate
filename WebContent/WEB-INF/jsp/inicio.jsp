<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="reserva" class="br.com.project.model.classes.Reserva" />
<!DOCTYPE html>
<html lang="pt-br">
<head>
<jsp:include page="header.jsp" />
<title>Agenda</title>
<script type="text/javascript">

	$(function() {
		$("#datareserva").datepicker({
			dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
	        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
	        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
	        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
	        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
	        dateFormat: 'dd/mm/yy',
	        nextText: 'Próximo',
	        prevText: 'Anterior'
		});
	});

	$(function() {
		$("#datareservaseleciona").datepicker({
			dateFormat : 'dd-mm-yy',
			onSelect : function(date) {
				reservasRealizadas(date);
			}
		});
	});

	$(document).ready(function() {
		$("#salvar").click(function(e) {
			e.preventDefault();
			var erros = 0;

			// verifica se ha campos vazios
			$("#reservaSalvar input").each(function() {

				// conta erros
				$(this).val() == "" ? erros++ : "";

			});
			
			

			// verifica se ha erros
			if (erros > 0) {
				
				alert("Existe(em) campo(os) vazio(os) neste fomulário");

			} else {
				// validar os periodos
				$.ajax({
					dataType : "json",
					type : "GET",
					url : "validarHorasAntecedencia",
					data : 
					{
						hora : $('#horadb').val(),
						datareserva:  $('#datareserva').val(),
						laboratorioSelect: $('#laboratorioSelect').val()
					}
				}).always(function(data) {
					if (data.validou === 'false'){
						 alert(data.msg); 
					 }else {
					   $("#reservaSalvar").submit();
					 }
					 
				});	
		   }
		});
		
		
		 $("#horadb").mask("##:##"); 
		 $("#horadb2").mask("##:##"); 

	});
	
	
</script>
</head>
<c:choose>
	<c:when test="${usuarioLogado != null}">
		<body>
			<div id="menu" class="menu">
				<jsp:include page="menu.jsp" />
			</div>
			
			<h2>Reservar Laboratório</h2>
			
			<div class="container-fluid">
			
			
			
			<table class=" table table-responsive">
				<c:choose>
					<c:when test="${tipoUser == 'ADMIN' || tipoUser == 'PROFESSOR'}">
						<tr>
							<td>
								<input type="submit" id="salvar" value="Reservar" class="btn btn-success ">
							</td>
							<td>
								<input type="button" value="Limpar" onclick="reset();" class="btn btn-danger btn-responsive">
							</td>
							<td>
								<form action="todasReservas" method="get">
									<input type="submit" value="Todas Reservas" class="btn btn-info btn-responsive">
								</form>
							</td>
						</tr>
					</c:when>
				</c:choose>
			</table>
			
			<c:choose>
				<c:when test="${tipoUser == 'ADMIN' || tipoUser == 'PROFESSOR'}">
					<form action="reservaSalvar" method="post" id="reservaSalvar" name="reservaSalvar">							
						<table class="table table-responsive">
							<c:choose>
								<c:when test="${horarioLivre == false}">
										<label class="text-danger"><h3>Horário já se encontra reservado nesta data</h3></label>

								</c:when>
								<c:when test="${horarioLivre == true}">
										<label class="text-success"><h3>Laboratório reservado com Sucesso</h3></label>

								</c:when>
							</c:choose>
							
							<tr align="left">
							<td>Professor</td>
								<td>
									<select name="entidade.ent_codigo" id="entidade.ent_codigo" class="form-control mobileSelect" >
										<c:forEach items="${professores}" var="professor">
											<option value="${professor.ent_codigo}"	id="${professor.ent_codigo}">${professor.nomedb}</option>
										</c:forEach>
									</select><br>
								</td>
							</tr>
							
							<tr align="left">
								<td align="left">Curso</td>
									<td>
										<select name="curso.id_curso" id="curso.id_curso" class="form-control mobileSelect" >
											<c:forEach items="${cursos}" var="curso">
												<option value="${curso.id_curso}" id="${curso.id_curso}">${curso.cursodb}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								
								<tr align="left">
									<td>Disciplina</td>
										<td>
											<select name="disciplina.id_disciplina"	id="disciplina.id_disciplina" class="form-control mobileSelect" >
												<c:forEach items="${disciplinas}" var="disciplina">
													<option value="${disciplina.id_disciplina}"
													id="${disciplina.id_disciplina}">${disciplina.disciplinadb}</option>
												</c:forEach>
											</select>
										</td>
								</tr>

								<tr align="left">
									<td>Laboratório</td>
										<td>
											<select name="laboratorio.id_laboratorio" id="laboratorioSelect" class="form-control mobileSelect" >
												<c:forEach items="${laboratorios}" var="laboratorio">
													<option value="${laboratorio.id_laboratorio}" id="">${laboratorio.laboratoriodb}</option>
												</c:forEach>
											</select>
										</td>
										
								<tr align="left">
									<td>Horário</td>									
										<td>
										    <input type="text" id="horadb" name="horadb"> até 
										    <input type="text" id="horadb2" name="horadb2">
										</td>
										
								<tr align="left">
									<td>Data</td>
										<td>
											<input type="text" id="datareserva" name="datareserva">
										</td>								
								</tr>
																
								<c:choose>
									<c:when test="${horarioLivre == false}">
								</c:when>
								</c:choose>								
						</form>
					</c:when>
				</c:choose>

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