package validator;

import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import controller.Controller;
import lej.entity.LejCikk;

@Named
@RequestScoped
public class KiValidator implements Validator {
	
	private Pattern p=Pattern.compile("\\*[0-9]{14}\\*");
	
	@Inject
	private Controller c;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String label=(String) arg1.getAttributes().get("label");
		if(arg2==null||arg2.toString().trim().length()==0||!p.matcher(arg2.toString()).matches()){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					(label==null || label.trim().length()==0 ? "*14számjegy* formátum a helyes":"validator.IntValidator 21-es sor"),
					null));//)
		} else{
			//ha nincs benne a listában nem vezethetem ki
			//ha már ki van vezetve nem vezethetem ki
			LejCikk l=c.talal(Integer.parseInt(arg2.toString().substring(1, 9)));
			if(l==null){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,(label==null||label.trim().length()==0 ? "Nincs ilyen lejszámú a sorrendben.":"KiValidator 32."),null));
			} else if(l.isBerakva()==true){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,(label==null||label.trim().length()==0 ? "Már ki van vezetve":"KiValidator 34."),null));
			}
		}
		
	}

}
