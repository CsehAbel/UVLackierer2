package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import lej.entity.LejCikk;
import tablazat.TablazatokController;

@Named
@RequestScoped
public class Controller implements Serializable{

	@Inject
	private TablazatokController tC;
	
	@EJB
	private LogManager lm;

	private String kiLej;
	
	private String vipKiLej;

	public Controller(){
	}

	public void kivezet(String kiLej){
		int i=Integer.parseInt(kiLej.substring(1,9));
		tC.kivezet(i,false);
	}
	
	public void vipKivezet(String kiLej){
		int i=Integer.parseInt(kiLej.substring(1,9));
		tC.vipKivezet(i,true);
	}
	
	
	public void addView(List<LejCikk> list){
		for(LejCikk l:list){
				tC.addView(l);
		}
	}
	
	public List<LejCikk> getMegjelenitendo(){
		return tC.getMegjelenitendo();
	}
	
	public LejCikk talal(int kiLej){
		return tC.talal(kiLej);
	}
	
	public List<LejCikk> getView(){
		return tC.getView();
	}

	public void kivezetLog(LejCikk l) {
		LogMotor log=new LogMotor(l.getL(),l.getC(), l.getDatum2());
		lm.saveLogMotor(log);
	}

	public String getKiLej() {
		return kiLej;
	}

	public void setKiLej(String kiLej) {
		this.kiLej = kiLej;
	}

	public String getVipKiLej() {
		return vipKiLej;
	}

	public void setVipKiLej(String vipKiLej) {
		this.vipKiLej = vipKiLej;
	}
	
	

}
