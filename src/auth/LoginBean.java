package auth;

import java.io.Serializable;

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
	
		private static final String[] felhasznalok={"admin:admin","muszakvezeto:almafa123"};

		private String User,Pass;
		
		private boolean loggedIn;
		
		@Inject
		private NavigationBean nav;
		
		public String doLogin(){
			for(String felh:felhasznalok){
				String user=felh.split(":")[0];
				String pass=felh.split(":")[1];
				
				if(this.User.equals(user)&&this.Pass.equals(pass)){
					loggedIn=true;
					return nav.toSorrend();
				}
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

		public String getUser() {
			return User;
		}

		public void setUser(String user) {
			User = user;
		}

		public String getPass() {
			return Pass;
		}

		public void setPass(String pass) {
			Pass = pass;
		}

		public boolean isLoggedIn() {
			return loggedIn;
		}

		public void setLoggedIn(boolean loggedIn) {
			this.loggedIn = loggedIn;
		}
}
