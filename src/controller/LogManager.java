package controller;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LogManager {

	@PersistenceContext
	private EntityManager em;
	
	public LogMotor saveLogMotor(LogMotor lm){
		em.persist(lm);
		
		return lm;
	}
}
