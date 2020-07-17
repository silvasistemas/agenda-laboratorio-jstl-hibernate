package br.com.project.geral.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.primefaces.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.implementacao.crud.JdbcTemplateImpl;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Laboratorio;
import br.com.project.model.classes.Reserva;
import br.com.repository.interfaces.RepositoryReserva;
import br.com.srv.interfaces.SrvReserva;

@Controller
public class ReservaController extends ImplementacaoCrud<Reserva> implements
		InterfaceCrud<Reserva> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SrvReserva srvReserva;
	@Autowired
	private RepositoryReserva repositoryReserva;

	@Autowired
	private EntidadeController entidadeController;
	
	@Autowired
	private LaboratorioController laboratorioController;

	@Autowired
	private JdbcTemplateImpl jdbcTemplateImpl;

	public void setSrvReserva(SrvReserva srvReserva) {
		this.srvReserva = srvReserva;
	}

	public void setRepositoryReserva(RepositoryReserva repositoryReserva) {
		this.repositoryReserva = repositoryReserva;
	}

	@RequestMapping(value = "reservaSalvar", method = RequestMethod.POST)
	public String reservaSalvar(Reserva entidade, Model modelAndView)
			throws Exception {
		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1)");
		sql.append(" from reserva");  
		sql.append(" where cast(replace(horadb, ':', '') as int )");
		sql.append(" BETWEEN cast(replace('"+entidade.getHoradb()+"', ':', '') as int ) and cast(replace('"+entidade.getHoradb2()+"', ':', '') as int)");
		sql.append(" or cast(replace(horadb2, ':', '') as int )");
		sql.append(" BETWEEN cast(replace('"+entidade.getHoradb()+"', ':', '') as int ) and cast(replace('"+entidade.getHoradb2()+"', ':', '') as int)");
		sql.append(" and datareserva = '"+entidade.getDatareserva()+"'");
		sql.append(" and laboratorio = "+entidade.getLaboratorio().getId_laboratorio()+";");

		int reservas = jdbcTemplateImpl
				.queryForInt(sql.toString());
		
		
		if (reservas > 0) {
			modelAndView.addAttribute("horarioLivre", false);
		} else {
			modelAndView.addAttribute("horarioLivre", true);
			entidade = merge(entidade);

		}
		modelAndView.addAttribute("reserva", entidade);
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
		return "inicio";

	}

	@RequestMapping(value = "/minhasReservas", method = RequestMethod.GET)
	public String minhasReservas(HttpServletRequest httpSession,
			Model modelAndView) throws Exception {
		Entidade entidade = (Entidade) httpSession.getSession(true)
				.getAttribute("usuarioLogado");
		modelAndView.addAttribute(
				"minhasReservas",
				findListByProperty(Reserva.class, "entidade.ent_codigo",
						entidade.getEnt_codigo()));
		return "minhasReservas";

	}

	@RequestMapping(value = "/todasReservas", method = RequestMethod.GET)
	public String todasReservas(HttpServletRequest httpSession,
			Model modelAndView) throws Exception {
		modelAndView.addAttribute("todasReservas",
				super.findListByQueryDinamica("from Reserva"));
		return "todasReservas";

	}

	@RequestMapping(value = "reserva", method = RequestMethod.GET)
	public String reserva(Reserva entidade, Model modelAndView) {

		return "reserva";

	}

	@RequestMapping(value = "reservasRealizadas", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody
	String reservasRealizadas(@RequestParam String date, Model model)
			throws Exception {
		JSONArray jsonArray = new JSONArray();
		for (Reserva reserva : super
				.findListByQueryDinamica(" from Reserva where datareserva = '"
						+ date + "'")) {
			jsonArray.put(reserva.getJson());
		}
		return jsonArray.toString();

	}

	@RequestMapping(value = "reservaExcluir", method = RequestMethod.GET)
	public String reservaExcluir(@RequestParam Long id_reserva,
			Model modelAndView, HttpServletRequest httpSession,
			@RequestParam String tela) throws Exception {

		Reserva entidade = super.findById(Reserva.class, id_reserva);
		super.delete(entidade);

		if (tela.equalsIgnoreCase("unica")) {
			Entidade user = (Entidade) httpSession.getSession(true)
					.getAttribute("usuarioLogado");
			modelAndView.addAttribute(
					"minhasReservas",
					findListByProperty(Reserva.class, "entidade.ent_codigo",
							user.getEnt_codigo()));
			return "minhasReservas";

		} else {
			modelAndView.addAttribute("todasReservas",
					findListByQueryDinamica("from Reserva"));
			return "todasReservas";
		}

	}
	
	
	@RequestMapping(value = "reservaCancelar", method = RequestMethod.GET)
	public String reservaCancelar(@RequestParam Long id_reserva,
			Model modelAndView, HttpServletRequest httpSession,
			@RequestParam String tela) throws Exception { 

		Reserva entidade = super.findById(Reserva.class, id_reserva);
		entidade.setStatus("CANCELADA");
		super.update(entidade);

		if (tela.equalsIgnoreCase("unica")) {
			Entidade user = (Entidade) httpSession.getSession(true)
					.getAttribute("usuarioLogado");
			modelAndView.addAttribute(
					"minhasReservas",
					findListByProperty(Reserva.class, "entidade.ent_codigo",
							user.getEnt_codigo()));
			return "minhasReservas";

		} else {
			modelAndView.addAttribute("todasReservas",
					findListByQueryDinamica("from Reserva"));
			return "todasReservas";
		}

	}
	
	
	
	@RequestMapping(value = "reservaConfimar", method = RequestMethod.GET)
	public String reservaConfimar(@RequestParam Long id_reserva,
			Model modelAndView, HttpServletRequest httpSession,
			@RequestParam String tela) throws Exception {

		Reserva entidade = super.findById(Reserva.class, id_reserva);
		entidade.setStatus("RESERVADO");
		super.update(entidade);

		if (tela.equalsIgnoreCase("unica")) {
			Entidade user = (Entidade) httpSession.getSession(true)
					.getAttribute("usuarioLogado");
			modelAndView.addAttribute(
					"minhasReservas",
					findListByProperty(Reserva.class, "entidade.ent_codigo",
							user.getEnt_codigo()));
			return "minhasReservas";

		} else {
			modelAndView.addAttribute("todasReservas",
					findListByQueryDinamica("from Reserva"));
			return "todasReservas";
		}

	}
	
	
	
	@RequestMapping(value = "validarHorasAntecedencia", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String  validarHorasAntecedencia(@RequestParam String hora, 
			@RequestParam String datareserva, @RequestParam String laboratorioSelect) throws NumberFormatException, Exception{
		
		Laboratorio laboratorio = laboratorioController.carregar(Laboratorio.class, Long.parseLong(laboratorioSelect));
		
		
		
		//------------------Valida laboratorio------------------------
			SimpleDateFormat dateFormatDatareserva = new SimpleDateFormat("dd/MM/yyyy");
			
			Date dateEscolhida = dateFormatDatareserva.parse(datareserva);
			
			if (dateEscolhida.after(dateFormatDatareserva.parse(laboratorio.getVigencia_final())) || dateEscolhida.before(dateFormatDatareserva.parse(laboratorio.getVigencia_inicial()))){
				return "{\"msg\":\"Laboratório estará em manutenção na data escolhida\", \"validou\":\"false\"}";
			}
		
		//------------------Valida laboratorio------------------------
		
		
      /*  String dataEscolhidaReserva = datareserva + " " + hora;// selecionada na tela data e hora inicial
		
		String horasantecedencia_max = laboratorio.getAntecedencia_max(); //cadastro de laboratorio
		
		String horasantecedencia_min = laboratorio.getAntecedencia_min(); // cadastro de laboratorio
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		int antecedencia_min = Integer.parseInt(horasantecedencia_min.split(":")[0]);
		
		Date dataEscolhida = dateFormat.parse(dataEscolhidaReserva);
		
		Date horaDataAtual = Calendar.getInstance().getTime();
		
		int horas = (int) ((((horaDataAtual.getTime() - dataEscolhida.getTime()) / (1000 * 60 )) / 60) * -1);

		int antecedencia_max = Integer.parseInt(horasantecedencia_max.split(":")[0]);

		boolean antecedenciMinValidade = antecedencia_min > horas;// validada
		
		boolean antecedenciMaxValidade = antecedencia_max >= horas;// validada
		
		if (antecedenciMinValidade || !antecedenciMaxValidade) {// periodo invalido
			return "{\"msg\":\"selecione um periodo válido\", \"validou\":\"false\"}";
			
		}*/
		
		
		return "{\"msg\":\"Periodo válido\", \"validou\":\"true\"}";

	}
	

}
