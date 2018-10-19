package input;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import auth.NavigationBean;
import controller.Controller;
import lej.bean.LejManager;
import lej.entity.LejCikk;
import recept.bean.ReceptekManager;
import recept.entity.Receptek;
import tablazat.TablazatokController;

@Named
@SessionScoped
public class InputHandler implements Serializable {
	
	@EJB
	private ReceptekManager rm;
	
	@EJB
	private LejManager lm;
	
	@Inject
	private Controller controller;
	
	@Inject
	private NavigationBean nav;
	

	private ArrayList<LejCikk> lcl=new ArrayList<LejCikk>();

	public List<LejCikk> getLcl() {
		return lcl;
	}
	
	public void addLcl(String lej){
		int ilej=Integer.parseInt(lej.substring(1, 9));
		LejCikk l=lm.getLej(ilej).cloneLejCikk();
		Receptek r=rm.getRecept(l.getC()).cloneReceptek();
		l.setR(r);
		l.setDatum1(LocalDateTime.now());
		lcl.add(l);
	}
	
	public String navi(){
		if(lcl.isEmpty()&&controller.getView().isEmpty()){
			return nav.redirectToLejCikk();
		} else if(!lcl.isEmpty()) {
			controller.addView(lcl);
			lcl.clear();
			return nav.toSorrend();
		} else {
			return nav.toSorrend();
		}
	}
	
	public void torol(LejCikk l){
		lcl.remove(l);
	}
	
	
}
