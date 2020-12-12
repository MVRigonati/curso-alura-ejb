package br.com.alura.job;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.servico.AgendamentoEmailServico;

@Singleton
public class AgendarEmailJob {

	private static final Logger LOGGER = Logger.getLogger(AgendarEmailJob.class.getName());
	
	@Inject
	private AgendamentoEmailServico servico;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void agendarEmailsSolicitados() {
		
		LOGGER.info("Chamando Job agendarEmailsSolicitados");
		this.servico.listarNaoEnviados()
		.forEach(email -> {
			this.context.createProducer().send(queue, email);
			this.servico.alterarAgendamentoEmailParaAgendado(email);
		});
		
	}
	
}
