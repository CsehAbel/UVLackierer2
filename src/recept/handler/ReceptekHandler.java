package recept.handler;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import recept.bean.ReceptekManager;
import recept.entity.Receptek;
import java.io.Serializable;

@Named
@SessionScoped
public class ReceptekHandler implements Serializable {

	@Inject
	private Receptek r;
	
	@EJB
	private ReceptekManager rm;
	
	public void save(){
		rm.saveRecept(r.cloneReceptek());
	}
	
	public List<Receptek> getReceptek(){
		return rm.getReceptek();
	}
	
	public void deleteRecept(String t){
		rm.deleteRecept(t);
	}
	
	public String navi(){
		if(rm.getReceptek().isEmpty()){
			return "nincsrecept";
		} else {
			return "vanrecept";
		}
	}
	
}
