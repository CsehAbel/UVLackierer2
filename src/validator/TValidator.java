package validator;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import recept.bean.ReceptekManager;

@Named
@RequestScoped
public class TValidator implements Validator {
	
	@EJB
	private ReceptekManager rm;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		if(arg2 == null || arg2.toString().trim().length()==0){
			return;
		}
		if(rm.getRecept(arg2.toString().trim())!=null){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,!(arg2 == null || arg2.toString().trim().length()==0) ? "Cikkszám létezik.":"TValidator 25-ös sor",null));
		}
		
	}

}
