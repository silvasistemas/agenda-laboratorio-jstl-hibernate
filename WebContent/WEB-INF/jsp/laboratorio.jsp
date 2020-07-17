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
<title>Laboratorio</title>
<script type="text/javascript">

$(document).ready(function(){

	$("#salvar").click(function(e){

		e.preventDefault();
			
		var erros = 0;
			
		// verifica se ha campos vazios
		$("#laboratorioSalvar input").each(function(){
				
			// conta erros
			$(this).val() == "" ? erros++ : "";
				
		});
			
		// verifica se ha erros
		if(erros > 0 ){
					 
			alert("Existe(em) campo(os) vazio(os) neste fomulário");
					
	    }else{
			//return true;	
			$("#laboratorioSalvar").submit()
		}		
				
		});
	

    $ ("#antecedencia_min").mask("##:##"); 
    $ ("#antecedencia_max").mask("##:##"); 
    
    
	$("#vigencia_inicial").datepicker({
		dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        dateFormat: 'dd/mm/yy',
        nextText: 'Próximo',
        prevText: 'Anterior'
	});
	
	
	$("#vigencia_final").datepicker({
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
	

</script>
</head>
<c:choose>
	<c:when test="${usuarioLogado != null}">
		<body>
			<div id="menu" class="menu">
				<jsp:include page="menu.jsp" />
			</div>
			<h2>Cadastrar Laboratório</h2>
			<div class="container">
				<form action="laboratorioSalvar" method="post" id="laboratorioSalvar" name="laboratorioSalvar" class="form-inline">
					<div class="form-group">
					  <table>
					   <tr> <td>
						<label>Nome </label>
						</td>
						<td>
						<input type="text" name="laboratoriodb"	id="laboratoriodb" class="form-control" placeholder="Digite o nome do Laboratório">
						</td>
						
						</tr>
						<tr><td>
						<label>Antecedência Mínima </label>
						</td>
						<td>
						<input type="text" name="antecedencia_min"	id="antecedencia_min" class="form-control" placeholder="Antecedência miníma">
						</td>
						</tr>
						
						<tr><td>
						<label>Antecedência Máxima </label>
						</td>
						<td>
						<input type="text" name="antecedencia_max"	id="antecedencia_max" class="form-control" placeholder="Antecedência Máxima">
						</td>
						</tr>
						
						
						<tr><td>
						<label>Vigência Incial </label>
						</td>
						<td>
						<input type="text" name="vigencia_inicial"	id="vigencia_inicial" class="form-control" placeholder="Vigência Incial">
						</td>
						</tr>
						
						<tr><td>
						<label>Vigência Final </label>
						</td>
						<td>
						<input type="text" name="vigencia_final"	id="vigencia_final" class="form-control" placeholder="Vigência Final">
						</td>
						</tr>
				
					</table>
					</div><br><br>			  
					<button type="submit" value="Salvar" id="salvar" class="btn btn-success btn-responsive">Salvar</button>
				</form>
				
				<c:choose>
					<c:when test="${laboratorio == true}">
						<label class="text-success"><h3>Cadastro com Sucesso</h3></label>
					</c:when>
				</c:choose>
			</div
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