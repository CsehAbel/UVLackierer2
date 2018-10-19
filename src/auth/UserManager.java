package auth;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserManager {
	
	@PersistenceContext(unitName="Lakk1")
	private EntityManager em;
	
	public User getUser(String usr){
		User u=null;
		try{
		u=em.createNamedQuery("select_user",User.class).setParameter("param",usr).getSingleResult();
		} catch(Exception ex){
			return null;
		}
		return u;
	}

}
