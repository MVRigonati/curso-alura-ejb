package br.com.alura.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.alura.entity.AgendamentoEmail;
import br.com.alura.service.AgendamentoEmailService;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup",
				propertyValue = "java:/jms/queue/EmailQueue"),
		@ActivationConfigProperty(
				propertyName = "destinationType",
				propertyValue = "javax.jms.Queue")
})
public class AgendamentoEmailMDB implements MessageListener {
	
	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmailMDB.class.getName());

	@Inject
	private AgendamentoEmailService service;

	@Override
	public void onMessage(Message message) {
		try {
			
			final AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
			this.service.sendEmail(agendamentoEmail);
			
		} catch (final JMSException ex) {
			LOGGER.warning("Exception when sending email");
			LOGGER.warning(ex.toString());
			throw new RuntimeException(ex);
		}
	}

}
