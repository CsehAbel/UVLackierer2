package recept.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import recept.entity.Receptek;

@Stateless
public class ReceptekManager {
	
	@PersistenceContext
	private EntityManager em;
	
	public Receptek saveRecept(Receptek r){
		em.persist(r);
		
		return r;
	}
	
	public void deleteRecept(String t){
		Receptek r=em.find(Receptek.class, t);
		
		em.remove(r);
	}
	
	public List<Receptek> getReceptek(){
		Query q=em.createNamedQuery("select_csillag");
		
		return q.getResultList();
	}
	
	public Receptek getRecept(String t){
		Receptek r=em.find(Receptek.class, t);
		return r;
	}

}
