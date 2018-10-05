package tablazat;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.Resource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.hibernate.validator.internal.util.privilegedactions.GetResources;


@Named
@SessionScoped
public class TrueFalseCSS implements Serializable {

	public String css(boolean b){
		String stilus="%s";
		if(b){
			FacesContext context = FacesContext.getCurrentInstance();
			String url = context.getApplication().evaluateExpressionGet(context, "#{resource['css:icon.png']}", String.class);
			return String.format(stilus, "background-image: url(\""+url+"\"); color: white;");
		} else {
			return "";//String.format(stilus, "yellow");
		}
	}

}
