package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entity.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> findAll() {
		return this.entityManager
				.createQuery("SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void insert(AgendamentoEmail email) {
		this.entityManager.persist(email);
	}
	
	public List<AgendamentoEmail> listNotScheduled() {
		return this.entityManager
				.createQuery("SELECT ae FROM AgendamentoEmail ae WHERE ae.scheduled = FALSE", AgendamentoEmail.class)
				.getResultList();
	}
	
	public void merge(AgendamentoEmail agendamentoEmail) {
		this.entityManager.merge(agendamentoEmail);
	}

}
