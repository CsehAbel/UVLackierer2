package auth;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
		@EJB
		private UserManager um;

		private String usr;//,Pass;
		
		private boolean loggedIn;
		
		@Inject
		private NavigationBean nav;
		
		public String doLogin(){
			if(um.getUser(usr)!=null){ //&&this.Pass.equals(pass)){
					usr=um.getUser(usr).getUser();
					loggedIn=true;
					return nav.toSorrend();
			}
			FacesMessage msg=new FacesMessage("Hibás felhasználó, bejelentkezés átugrása", "Bejelentkezési_Hiba");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return nav.toSorrend();
		}
		
		public String doAtugor(){
			FacesMessage msg=new FacesMessage("Bejelentkezés átugrása", "Bejelentkezési_kihagyva");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return nav.toSorrend();
		}
		
		public String doLogout(){
			loggedIn = false;
			FacesMessage msg=new FacesMessage("Sikeres kijelentkezés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return nav.toSorrend();
		}

		

		public String getUsr() {
			return usr;
		}

		public void setUsr(String usr) {
			this.usr = usr;
		}

		public boolean isLoggedIn() {
			return loggedIn;
		}

		public void setLoggedIn(boolean loggedIn) {
			this.loggedIn = loggedIn;
		}
}
