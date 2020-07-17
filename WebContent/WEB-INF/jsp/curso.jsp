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

<script type="text/javascript">

$(document).ready(function(){

	$("#salvar").click(function(e){

		e.preventDefault();
			
		var erros = 0;
			
		// verifica se ha campos vazios
		$("#cursoSalvar input").each(function(){
				
			// conta erros
			$(this).val() == "" ? erros++ : "";
				
		});
			
		// verifica se ha erros
		if(erros > 0 ){
					 
			alert("Existe(em) campo(os) vazio(os) neste fomulário");
					
	    }else{
			//return true;	
			$("#cursoSalvar").submit()
		}		
				
		});

	});

</script>
</head>
<c:choose>
	<c:when test="${usuarioLogado != null}">
		<body>
			<div id="menu" class="menu">
				<jsp:include page="menu.jsp" />
			</div>
			<h2>Cadastrar Curso</h2>
			<div class="container">
					<form action="cursoSalvar" method="post" id="cursoSalvar" name="cursoSalvar" class="form-inline">
						<div class="form-group">
							<label>Nome </label>
								<input type="text" name="cursodb" id="cursodb" class="form-control" placeholder="Digite o nome do Curso">
						</div><br><br>
			  
						<button type="submit" value="Salvar" id="salvar" class="btn btn-success btn-responsive">Salvar</button>

					</form>
					<c:choose>
							<c:when test="${curso == true}">
								<label class="text-success"><h3>Cadastrado com Sucesso</h3></label>
							</c:when>
					</c:choose>

				</div>

	</c:when>
	<c:otherwise>
		<%
			session.invalidate();
					response.sendRedirect("index.jsp");
		%>
	</c:otherwise>
</c:choose>
</html>