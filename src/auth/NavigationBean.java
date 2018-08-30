package auth;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class NavigationBean implements Serializable {
	
	public String toSorrend(){
		return "/Sorrend.jpg";
	}
	
	public String redirectToLejCikk(){
		System.out.println("redirect LejCikk");
		return "/korlatozott/LejCikk.jpg?faces-redirect=true";
	}
	
	public String redirectToRecept(){
		
	}

}
