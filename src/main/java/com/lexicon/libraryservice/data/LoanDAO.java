package com.lexicon.libraryservice.data;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lexicon.libraryservice.model.Loan;

public class LoanDAO implements LoanDAOInterface {

	@Inject
	EntityManager em;
	
	@Override
	public void persistLoan(Loan loan) {
		em.persist(loan);
	}
	
	@Override
	public List<Loan> getAllLoans(){
		TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l", Loan.class);
		return query.getResultList();
	}
}
