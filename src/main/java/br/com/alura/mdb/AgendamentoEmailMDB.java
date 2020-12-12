package br.com.alura.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

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
	private AgendamentoEmailServico servico;

	@Override
	public void onMessage(Message message) {
		try {
			
			final AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
			this.servico.enviar(agendamentoEmail);
			
		} catch (final JMSException ex) {
			LOGGER.warning("Erro ao enviar email");
			LOGGER.warning(ex.toString());
			throw new RuntimeException(ex);
		}
	}

}
