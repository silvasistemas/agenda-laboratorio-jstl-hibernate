package br.com.project.geral.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;

@Controller
public class LoginController extends ImplementacaoCrud<Entidade> implements
		InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntidadeController entidadeController;

	@RequestMapping(value = "**/inicio")
	public @ResponseBody
	String login(@RequestParam String j_username,
			@RequestParam String j_password, HttpServletRequest request)
			throws Exception {
		HashMap<String, String> maps = new HashMap<String, String>();
		Entidade entidade = super.findUninqueByProperty(Entidade.class,
				j_password, "senhadb", " and emaildb = '" + j_username + "'");
		if (entidade == null) {
			maps.put("permitido", "false");
		} else {
			maps.put("permitido", "true");
			request.getSession(true).setAttribute("usuarioLogado", entidade);
			request.getSession(true).setAttribute("tipoUser", entidade.getTipo());
		}
		return new JSONObject(maps).toString();

	}

	@RequestMapping(value = "validaLogin", method = RequestMethod.POST)
	public String validaLogin(HttpServletRequest request, Model modelAndView)
			throws Exception {
		Entidade usuarioLogado = (Entidade) request.getSession(true)
				.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			return "index";
		}
		entidadeController.updateUltimoAcessoUser(usuarioLogado.getEmaildb());
		carregarCombos(modelAndView);
		return "inicio";

	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model modelAndView)
			throws Exception {
		Entidade usuarioLogado = (Entidade) request.getSession(true)
				.getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			return "index";
		}
		carregarCombos(modelAndView);
		return "inicio";

	}

	private void carregarCombos(Model modelAndView) throws Exception {
		modelAndView
				.addAttribute(
						"professores",
						super.findListByQueryDinamica("from Entidade where tipo = 'PROFESSOR'"));
		modelAndView.addAttribute("cursos",
				super.findListByQueryDinamica("from Curso"));
		modelAndView.addAttribute("disciplinas",
				super.findListByQueryDinamica("from Disciplina"));
		modelAndView.addAttribute("laboratorios",
				super.findListByQueryDinamica("from Laboratorio"));
		modelAndView.addAttribute("cursos",
				super.findListByQueryDinamica("from Curso"));
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession(true).setAttribute("usuarioLogado", null);
		request.getSession(true).invalidate();
		return "/index";

	}
}
