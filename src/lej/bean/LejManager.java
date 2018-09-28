package lej.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lej.entity.LejCikk;

@Stateless
public class LejManager {

	@PersistenceContext(unitName="Lakk2")
	private EntityManager em;
	
	public LejCikk getLej(int lej){
		LejCikk l=em.find(LejCikk.class, lej);
		
		return l;
	}
}
