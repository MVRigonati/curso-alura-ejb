package br.com.alura.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AgendamentoEmailDaoExampleTransaction {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private UserTransaction transaction;
	
	/**
	 * 	When we take control of the transaction using TransactionManagementType.BEAN
	 * we need to specify the begin and end of the transaction, otherwise it will
	 * throw an Exception.
	 *  
	 * @param agendamentoEmail - Object to merge, it must have ID.
	 * 
	 */
	public void alterar(AgendamentoEmail agendamentoEmail) {
		try {
			transaction.begin();
			this.entityManager.merge(agendamentoEmail);
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
