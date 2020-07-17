package br.com.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class Teste {
	
	@Test
	public void testData(){
		try {
		String dataEscolhidaReserva = "14/05/2017 20:00";// selecionada na tela data e hora inicial
		
		String horasantecedencia_max = "48:00"; //cadastro de laboratorio
		
		String horasantecedencia_min = "24:00"; // cadastro de laboratorio
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		int antecedencia_min = Integer.parseInt(horasantecedencia_min.split(":")[0]);
		
		Date dataEscolhida = dateFormat.parse(dataEscolhidaReserva);
		
		Date horaDataAtual = Calendar.getInstance().getTime();
		
		int horas = (int) ((((horaDataAtual.getTime() - dataEscolhida.getTime()) / (1000 * 60 )) / 60) * -1);

		int antecedencia_max = Integer.parseInt(horasantecedencia_max.split(":")[0]);

		boolean antecedenciMinValidade = antecedencia_min > horas;// validada
		
		boolean antecedenciMaxValidade = antecedencia_max >= horas;// validada
		
		if (antecedenciMinValidade || !antecedenciMaxValidade) {// periodo invalido
			System.out.println("selecione um periodo válido");
			
		}else {// periodo validado
			System.out.println("Reserva efetuada");
		}
		
		
		}catch (Exception e){
			e.printStackTrace();
		}		
		
	}
	
	@Test
	public void testDataManutencao() throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String dataInicial = "01/05/2017";
		String dataFinal = "16/05/2017";
		
		Date dateEscolhida = Calendar.getInstance().getTime();
		
		if (dateEscolhida.after(dateFormat.parse(dataFinal)) || dateEscolhida.before(dateFormat.parse(dataInicial))){
			System.out.println("Laboratório estará em manutenção na data escolhida.");
		}
		
	}

}
