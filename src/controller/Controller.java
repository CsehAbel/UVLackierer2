package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

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


@SessionScoped
public class Controller implements Serializable{

	@Inject
	private TablazatokController tC;


	public Controller(){
	}

	public void addView(List<LejCikk> ol){
		for(LejCikk l:ol){
				tC.addView(l);
		}
	}

	public List<LejCikk> getView(){
		return tC.getView();
	}
	
	public List<LejCikk> getMegjelenitendo(){
		return tC.getMegjelenitendo();
	}

}
