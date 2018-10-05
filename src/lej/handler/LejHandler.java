package lej.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lej.bean.LejManager;
import lej.entity.LejCikk;

@Named
@SessionScoped
public class LejHandler implements Serializable {
	
	private String lejszam;

	public String getLejszam() {
		return lejszam;
	}

	public void setLejszam(String lejszam) {
		this.lejszam = lejszam;
	}
	
}
