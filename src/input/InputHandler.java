package input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lej.bean.LejManager;
import lej.entity.LejCikk;
import recept.bean.ReceptekManager;
import recept.entity.Receptek;

@Named
@SessionScoped
public class InputHandler implements Serializable {
	
	@EJB
	private ReceptekManager rm;
	
	@EJB
	private LejManager lm;

	private ArrayList<LejCikk> lcl=new ArrayList<LejCikk>();

	public List<LejCikk> getLcl() {
		return lcl;
	}
	
	public void addLcl(int lej){
		LejCikk l=lm.getLej(lej).cloneLejCikk();
		Receptek r=rm.getRecept(l.getC()).cloneReceptek();
		l.setR(r);
		lcl.add(l);
		System.out.println(""+l.getL()+" "+r.getWaermen_zeit());
	}
	
	public String navi(){
		if(lcl.isEmpty()){
			return "nincslejcikk";
		} else {
			return "vanlejcikk";
		}
	}
	
	public void torol(LejCikk l){
		lcl.remove(l);
	}
	
	
}
