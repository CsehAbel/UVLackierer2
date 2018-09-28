package lej.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import recept.entity.Receptek;

@NamedQueries({
	@NamedQuery(name = "select_lej", query = "SELECT lej FROM LejCikk lej WHERE lej.l=:l")
})
@Entity
@Table(name="PUB.PP_Auftrag")
public class LejCikk {
	
	@Id
	@Column(name="RueckMeldeNr")
	private int l;

	@Column(name="Artikel")
	private String c;
	
	@Column(name="ProduktionsMenge")
	private int d;
	
	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	@Transient
	private boolean Berakva;
	
	@Transient
	private int X;
	
	@Transient
	private LocalDateTime Datum1;
	
	@Transient
	private LocalDateTime Datum2;
	
	@Transient
	private Receptek r;
	
	@Transient
	private int Sorszam;
	
	public LejCikk(){
		this.Berakva=false;
	}
	
	public LejCikk cloneLejCikk(){
		LejCikk l=new LejCikk();
		l.setL(this.getL());
		l.setC(this.getC());
		l.setD(this.getD());
		l.setR(this.getR());
		l.setDatum1(this.getDatum1());
		//l.setDatum2(this.getDatum2());
		//l.setSorszam(this.getSorszam());
		//l.setBerakva(this.isBerakva());
		return l;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LejCikk){
			if(((LejCikk)obj).getL()==this.getL()){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return this.getL();
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}
	

	public Receptek getR() {
		return r;
	}

	public void setR(Receptek r) {
		this.r = r;
	}

	public boolean isBerakva() {
		return Berakva;
	}

	public void setBerakva(boolean berakva) {
		Berakva =berakva;
	}
	
	

	public int getX() {
		return X;
	}

	public void setX(int elozoWarmen) {
		X = elozoWarmen+r.getTauchen_1_zeit()+r.getAbtropfen_zeit()+150-r.getWaermen_zeit();
	}

	public LocalDateTime getDatum1() {
		return Datum1;
	}

	public void setDatum1(LocalDateTime datum1) {
		Datum1 = datum1;
	}

	public LocalDateTime getDatum2() {
		return Datum2;
	}

	public void setDatum2(LocalDateTime datum2) {
		Datum2 = datum2;
	}

	public int getSorszam() {
		return Sorszam;
	}

	public void setSorszam(int sorszam) {
		Sorszam = sorszam;
	}
	
	
}
