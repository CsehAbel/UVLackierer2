package auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "select_user", query = "SELECT usr FROM User usr WHERE usr.felh=:param")
})
@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user")
	private String felh;
	
	@Column(name="nev")
	private String nev;
	
	@Column(name="vip")
	private int vip;

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFelh() {
		return felh;
	}

	public void setUser(String user) {
		this.felh = user;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

}
