package controller;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LM")
public class LogMotor {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="L")
	private int lej;
	
	@Column(name="C")
	private String cikk;
	
	@Column(name="D")
	private LocalDateTime date;
	
	public LogMotor(){
		
	}

	public LogMotor(int lej, String cikk, LocalDateTime date) {
		super();
		this.lej = lej;
		this.cikk = cikk;
		this.date = date;
	}

	public int getLej() {
		return lej;
	}

	public void setLej(int lej) {
		this.lej = lej;
	}

	public String getCikk() {
		return cikk;
	}

	public void setCikk(String cikk) {
		this.cikk = cikk;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
