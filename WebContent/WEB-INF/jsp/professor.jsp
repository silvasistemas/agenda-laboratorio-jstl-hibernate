<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="professor" class="br.com.project.model.classes.Entidade" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp" />
<title>Professor</title>

<script type="text/javascript">

$(document).ready(function(){

	$("#salvar").click(function(e){

		e.preventDefault();
			
		var erros = 0;
			
		// verifica se ha campos vazios
		$("#professorSalvar input").each(function(){
				
			// conta erros
			$(this).val() == "" ? erros++ : "";
				
		});
			
		// verifica se ha erros
		if(erros > 0 ){
					 
			alert("Existe(em) campo(os) vazio(os) neste fomulário");
					
	    }else{
			//return true;	
			$("#professorSalvar").submit()
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
			<h2>Cadastrar Professor</h2>
			<div class="container">
					<form action="professorSalvar" method="post" id="professorSalvar" class="form-inline">
						<div class="form-group">
							<label for="inputdefault">Nome</label>
								<input type="text" name="nomedb" id="nomedb" class="form-control" placeholder="Digite o nome do Professor">
							<br><br>	
							
							<label for="inputdefault">E-mail</label>
								<input type="text" name="emaildb" id="nomedb" class="form-control" placeholder="Digite o E-mail do Professor">
							<br><br>
							
							<label for="inputdefault">Senha</label>
								<input type="password" name="senhadb" id="senhadb" class="form-control" placeholder="Digite a senha do Professor">
						</div>
						<br><br>			  
						<button type="submit" id="salvar" value="Salvar" class="btn btn-success btn-responsive">Salvar</button>
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