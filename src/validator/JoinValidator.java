package validator;

import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import controller.Controller;
import input.InputHandler;
import lej.bean.LejManager;
import lej.entity.LejCikk;
import lej.handler.LejHandler;
import recept.bean.ReceptekManager;

@Named
@RequestScoped
public class JoinValidator implements Validator {

	@EJB
	private LejManager lm;
	
	@Inject
	private InputHandler ih;
	
	@Inject
	private Controller controller;
	
	@EJB
	private ReceptekManager rm;
	
	private Pattern p=Pattern.compile("[0-9\\*]{16}");
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		if(arg2==null || arg2.toString().trim().length()==0||!p.matcher(arg2.toString().trim()).matches()){
			String label=(String) arg1.getAttributes().get("label");
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,(label==null || label.trim().length()==0 ? "A lejszám 14 számjegy+2csillag.":"JoinValidator 36-os sor."), null));
		} else {
			String s=arg2.toString().trim();
			int ilej=Integer.parseInt(s.substring(1, 9));
			LejCikk l=lm.getLej(ilej);
			String label=(String) arg1.getAttributes().get("label");
			if(l==null){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,(label==null || label.trim().length()==0 ? "Nincs ilyen lejszám.":"JoinValidator 43-as sor."), null));
			} else { 
				if(rm.getRecept(l.getC())==null){
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,(label==null||label.trim().length()==0 ? "Nincs recept a lejszám cikkéhez.":"JoinValidator 46. sor."),null));
				} else if(ih.getLcl().contains(l)){
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,(label==null||label.trim().length()==0 ? "Szerepel már ilyen lejelentésszám.":"JoinValidator 46. sor."),null));
				} else if(controller.getView().contains(l)){
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, (label==null || label.trim().length()==0 ? "Már a sorrendben szerepel!":"JoinValidator 60.sor"),null));
				}
			}
		}
		
		
	}

	
}
