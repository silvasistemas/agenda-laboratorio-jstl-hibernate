var arrayIdsElementsPage = new Array;
var idundefined = 'idundefined';

function reloadPage() {
	$(function() {
		location.reload();
	});

}

/**
 * Carrega um array global com os ids de todos os componentes da pagina Para ter
 * faciliades em obtencao de valores dos componentes bem como trabalhar com ajax
 */
function carregarIdElementosPagina() {
	arrayIdsElementsPage = new Array;
	for (form = 0; form <= document.forms.length; form++) {
		var formAtual = document.forms[form];
		if (formAtual != undefined) {
			for (i = 0; i < document.forms[form].elements.length; i++) {
				if (document.forms[form].elements[i].id != '') {
					arrayIdsElementsPage[i] = document.forms[form].elements[i].id;
				}
			}
		}
	}
}

/**
 * Retorno o valor do id do componente dentro do documento html passando como
 * parametro a descriзгo do id declarada em jsf
 * 
 * @param id
 */
function getValorElementPorId(id) {
	carregarIdElementosPagina();
	for (i = 0; i < arrayIdsElementsPage.length; i++) {
		var valor = "" + arrayIdsElementsPage[i];
		if (valor.indexOf(id) > -1) {
			return valor;
		}
	}
	return idundefined;
}

function sair(contextPath) {
	document.location = contextPath + '/j_spring_security_logout';
}

function validarSenhaLogin() {
	j_username = document.getElementById('j_username').value;
	j_password = document.getElementById('j_password').value;

	if (j_username == null || j_username.trim() == '') {
		alert("Informe o Login.");
		$("#j_username").focus();
		return false;
	}

	if (j_password == null || j_password.trim() == '') {
		alert("Informe a Senha.");
		$("#j_password").focus();
		return false;
	}

	return true;

}

function permitNumber(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9 && unicode != 37 && unicode != 39
			&& unicode != 46) {
		if (unicode < 48 || unicode > 57) {
			return false;
		}
	}
}

function naoPermiteEntradaDeDados(e) {
	return false;
}

function permitAlphaNumerico(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9) {
		return true;
	}
}

function permitDecimal(e) {
	var unicode = e.charCode ? e.charCode : e.keyCode;
	if (unicode != 8 && unicode != 9 && unicode != 46) {
		if (unicode < 48 || unicode > 57) {
			return false;
		}
	}
}

function addMascaraDecimalMonetaria(id) {
	var id = getValorElementPorId(id);
	if (id != idundefined) {
		jQuery(function($) {
			$("#" + id).maskMoney({
				precision : 4,
				decimal : ",",
				thousands : "."
			});
		});
	}

}

function validarSenhaLogin() {
	j_username = document.getElementById('j_username').value;
	j_password = document.getElementById('j_password').value;

	if (j_username == null || j_username.trim() == '') {
		alert("Informe o Login.");
		$("#j_username").focus();
		return false;
	}

	if (j_password == null || j_password.trim() == '') {
		alert("Informe a Senha.");
		$("#j_password").focus();
		return false;
	}

	return true;

}

function permitirApenasNumero(id) {
	var id = getValorElementPorIdJQuery(id);
	$(id).keypress(permitNumber);
}

function gerenciaTeclaEnterTransferFocus(tranfereFocusNoEnter) {
	$(document).ready(function() {
		$('input').keypress(function(e) {
			var code = null;
			code = (e.keyCode ? e.keyCode : e.which);
			if (code == 13) {
				e.keyCode = 0;
				var id = getValorElementPorIdJQuery(tranfereFocusNoEnter);
				$(id).focus();
				return false;
			} else {
				return true;
			}

		});

		$('input[type=text]').keydown(function(e) {
			// get the next index of text input element
			var next_idx = $('input[type=text]').index(this) + 1;

			// get number of text input element in a html document
			var tot_idx = $('body').find('input[type=text]').length;

			// enter button in ASCII code
			if (e.keyCode == 13) { // button
				if (tot_idx == next_idx) {
					// go to the first text element if focused in the last text
					// input element
					// $('input[type=text]:eq(0)').focus();
				} else {
					// go to the next text input element
					$('input[type=text]:eq(' + next_idx + ')').focus();
				}
			}
		});
	});

}

function addFocoNoPrimeiroCampo() {
	$(document).ready(function() {
		$("input:text:first:visible").focus();
	});
}

function bloqueiaComandoCtrlCeCtrlV(event) {
	var tecla = String.fromCharCode(event.keyCode).toLowerCase();

	if (event.ctrlKey && (tecla == "c" || tecla == "v")) {
		window.event ? event.returnValue = false : event.preventDefault();
		return false;
	}
}

function validarLogin() {

	if (validarSenhaLogin() === false) {
		return false;
	}

	j_username = document.getElementById('j_username').value;
	j_password = document.getElementById('j_password').value;

	$.ajax({
		type : "GET",
		url : "inicio",
		data : {
			j_password : j_password,
			j_username : j_username
		}
	}).always(function(resposta) {
		var json = JSON.parse(resposta);
		if (json.permitido == 'true') {
			document.getElementById("formulario").submit();
		} else {
			alert("Usuário e senha inválidos");
		}
	});
	return false;
}

function reservasRealizadas(date) {
	$.ajax({
		dataType : "json",
		type : "GET",
		url : "reservasRealizadas",
		data : {
			date : date
		}
	}).always(function(data) {
		var table = "<table class=\"reservasDia\">";

		$.each(data, function(key, reserva) {
			table += "<tr>";
			table += "<th>" + reserva.data + " - " + reserva.curso + "</th>";
			table += "</tr>";
			table += "<tr>";
			table += "<td>Hora: " + reserva.horadb + "</td>";
			table += "</tr>";
			table += "<tr>";
			table += "<td>Laboratorio: " + reserva.laboratorio + "</td>";
			table += "</tr>";
			table += "<tr>";
			table += "<td>Disciplina: " + reserva.disciplina + "</td>";
			table += "</tr>";
			table += "<tr>";
			table += "<td>Reservado: " + reserva.reservado + "</td>";
			table += "</tr>";
		});

		table += "</table>";
		document.getElementById("conteudo3").innerHTML = table;
	});
}

function excluir(elemento, id) {
	document.getElementById("'" + elemento + "'").value = id;
}
