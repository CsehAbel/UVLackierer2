package lej.entity;

import javax.persistence.CascadeType;
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

import recept.entity.Receptek;

@NamedQueries({
	@NamedQuery(name = "select_lej", query = "SELECT lej FROM LejCikk lej WHERE lej.l=:l")
})
@Entity
@Table(name="LC")
public class LejCikk {
	
	@Id
	private int l;
	
	private String c;
	
	@Transient
	private Receptek r;
	
	public LejCikk(){
		
	}
	
	public LejCikk cloneLejCikk(){
		LejCikk l=new LejCikk();
		l.setL(this.getL());
		l.setC(this.getC());
		l.setR(this.getR());
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
	
}
