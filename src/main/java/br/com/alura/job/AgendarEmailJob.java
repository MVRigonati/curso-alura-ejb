package br.com.alura.job;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.alura.servico.AgendamentoEmailServico;

@Singleton
public class AgendarEmailJob {

	private static final Logger LOGGER = Logger.getLogger(AgendarEmailJob.class.getName());
	
	public AgendarEmailJob() {
		System.out.println("Criando AgendarEmailJob");
	}
	
	@Inject
	private AgendamentoEmailServico servico;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void agendarEmailsSolicitados() {
		
		LOGGER.info("Chamando Job agendarEmailsSolicitados");
		this.servico.listarNaoEnviados()
		.forEach(email -> {
			this.servico.enviar(email);
			this.servico.alterarAgendamentoEmailParaAgendado(email);
		});
		
	}
	
}
