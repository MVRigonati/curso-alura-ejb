package br.com.alura.servico;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDao;
import br.com.alura.dao.AgendamentoEmailDaoExampleTransaction;
import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {

	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmailServico.class.getName());
	
	@Inject
	private AgendamentoEmailDao dao;
	
	@Inject
	private AgendamentoEmailDaoExampleTransaction daoExample;
	
	public List<AgendamentoEmail> list() {
		return this.dao.findAll();
	}
	
	public void inserir(AgendamentoEmail email) {
		email.setAgendado(false);
		this.dao.inserir(email);
	}
	
	public List<AgendamentoEmail> listarNaoEnviados() {
		return this.dao.listarNaoAgendados();
	}
	
	public void alterarAgendamentoEmailParaAgendado(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(true);
		this.daoExample.alterar(agendamentoEmail);
	}
	
	public void enviar(AgendamentoEmail email) {
		try {
			Thread.sleep(2000);
			LOGGER.info("Email enviado para " + email.getEmail());
		} catch (Exception ex) {
			LOGGER.warning(ex.toString());
		}
	}
	
}
