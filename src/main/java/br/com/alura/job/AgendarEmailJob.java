package br.com.alura.job;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.service.AgendamentoEmailService;

@Singleton
public class AgendarEmailJob {

	private static final Logger LOGGER = Logger.getLogger(AgendarEmailJob.class.getName());
	
	@Inject
	private AgendamentoEmailService service;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void agendarEmailsSolicitados() {
		
		LOGGER.info("Calling job agendarEmailsSolicitados");
		
		Queue queue = extractQueue();
		
		this.service.listNotScheduled()
		.forEach(email -> {
			this.context.createProducer().send(queue, email);
			this.service.changeAgendamentoEmailToScheduled(email);
		});
		
	}

	/**
	 * This is an alternative to get the queue.
	 * 
	 * @return The same object as
	 * "<code>@Resource(mappedName = "java:/jms/queue/EmailQueue") private Queue queue;</code>"
	 * 
	 */
	private Queue extractQueue() {
		Queue queue = this.context.createQueue("EmailQueue");
		return queue;
	}
	
}
