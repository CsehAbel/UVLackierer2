package tablazat;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;


@Named
@SessionScoped
public class TrueFalseCSS implements Serializable {

	public String css(boolean b){
		String stilus="background-color: %s;";
		if(b){
			return String.format(stilus, "red");
		} else {
			return String.format(stilus, "yellow");
		}
	}

}
