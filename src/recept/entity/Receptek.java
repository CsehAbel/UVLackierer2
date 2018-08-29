package recept.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lej.entity.LejCikk;


@NamedQueries({
	@NamedQuery(name="select_csillag", query="SELECT r FROM Receptek r")
	})
@Named
@RequestScoped
@Entity
@Table(name="receptek")
public class Receptek implements Serializable {

	//\d{8}[0-9A-Z] vagy \d{8}-[A-Z]\d{2}
	@Id
	@Pattern(regexp="[0-9]{8}[0-9A-Z]", message="pl. 60012172B, 70028109-E00")
	private String teilenummer;
	
	//\d{1,4}
	private int waermen_zeit;
	
	//\d{1,4}
	private int tauchen_1_zeit;
	
	//\d{1,4}
	private int abtropfen_zeit;
	
	public Receptek(){
	}

	
	public String getTeilenummer() {
		return teilenummer;
	}

	public int getWaermen_zeit() {
		return waermen_zeit;
	}


	public int getTauchen_1_zeit() {
		return tauchen_1_zeit;
	}


	public int getAbtropfen_zeit() {
		return abtropfen_zeit;
	}


	//whitespace-t trimmelem róla
	public void setTeilenummer(String teilenummer) {
		this.teilenummer = teilenummer.trim();
	}


	public void setWaermen_zeit(int waermen_zeit) {
		this.waermen_zeit = waermen_zeit;
	}


	public void setTauchen_1_zeit(int tauchen_1_zeit) {
		this.tauchen_1_zeit = tauchen_1_zeit;
	}


	public void setAbtropfen_zeit(int abtropfen_zeit) {
		this.abtropfen_zeit = abtropfen_zeit;
	}

	public Receptek cloneReceptek(){
		Receptek r=new Receptek();
		r.setAbtropfen_zeit(this.getAbtropfen_zeit());
		r.setTauchen_1_zeit(this.getTauchen_1_zeit());
		r.setTeilenummer(this.getTeilenummer());
		r.setWaermen_zeit(this.getWaermen_zeit());
		return r;
	}
}
