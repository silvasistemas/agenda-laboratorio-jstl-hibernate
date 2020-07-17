package br.com.project.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements
		InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SrvEntidade srvEntidade;

	public Date getUltimoAcessoEntidadeLogada(String login) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcessoUser(String login) {
		srvEntidade.updateUltimoAcessoUser(login);
	}

	public boolean existeUsuario(String ent_login) {
		return srvEntidade.existeUsuario(ent_login);
	}

	@RequestMapping(value = "professorSalvar", method = RequestMethod.POST)
	public String professorSalvar(Entidade entidade, Model modelAndView)
			throws Exception {
		entidade.setTipo("PROFESSOR");
		entidade = merge(entidade);
		modelAndView.addAttribute("professor", entidade);
		return "professor";

	}

	@RequestMapping(value = "professor", method = RequestMethod.GET)
	public String professo(Entidade entidade, Model modelAndView) {

		return "professor";

	}

}
