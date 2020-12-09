package br.com.alura.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDao;
import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {

	@Inject
	private AgendamentoEmailDao dao;
	
	public List<AgendamentoEmail> list() {
		return this.dao.findAll();
	}
	
	public void inserir(AgendamentoEmail email) {
		email.setAgendado(false);
		this.dao.inserir(email);
	}
	
}
