package br.com.alura.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDao;
import br.com.alura.dao.AgendamentoEmailDaoExampleTransaction;
import br.com.alura.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailService {

	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmailService.class.getName());
	
	@Inject
	private AgendamentoEmailDao dao;
	
	@Inject
	private AgendamentoEmailDaoExampleTransaction daoExample;
	
	public List<AgendamentoEmail> list() {
		return this.dao.findAll();
	}
	
	public void insert(AgendamentoEmail email) {
		email.setScheduled(false);
		this.dao.insert(email);
	}
	
	public List<AgendamentoEmail> listNotScheduled() {
		return this.dao.listNotScheduled();
	}
	
	public void changeAgendamentoEmailToScheduled(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setScheduled(true);
		this.daoExample.merge(agendamentoEmail);
	}
	
	public void sendEmail(AgendamentoEmail email) {
		try {
			Thread.sleep(2000);
			LOGGER.info("Email sent to " + email.getAddress());
		} catch (Exception ex) {
			LOGGER.warning(ex.toString());
		}
	}
	
}
