package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> findAll() {
		return this.entityManager.createQuery("SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void inserir(AgendamentoEmail email) {
		this.entityManager.persist(email);
	}
	
	public List<AgendamentoEmail> listarNaoAgendados() {
		return this.entityManager
				.createQuery("SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado = FALSE", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void alterar(AgendamentoEmail agendamentoEmail) {
		this.entityManager.merge(agendamentoEmail);
	}

}
