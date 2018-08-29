package tablazat;
import controller.Controller;
import controller.Rendezes;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

import lej.entity.LejCikk;

@SessionScoped
public class TablazatokController implements Serializable {
	
	@FXML
	private Button btn;
	@FXML
	private TextField kTF;
	
	private String lblC,lblD;
	
	
	private Rendezes rendezes;
	
	private ObservableSet<LejCikk> lockedSet;
	private ObservableList<LejCikk> lockedSorted;
	private ObservableList<LejCikk> view;
	private ObservableList<LejCikk> megjelenitendo;


	@PostConstruct
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/* EMAIL KÜLDÉS? LEJSZAM MEGKERESESE A TABLAZATBAN
		kTF.textProperty().addListener((obs,oldV,newV)->{
			if(newV.matches("[0-9]{8}")){
				for(Lapat a:view){
						if(Integer.parseInt(newV)==a.getLejszam()){
							a.setBerakva(true);
							lblC=a.getCikkszam();
							lblD=""+a.getDarabszam()+" db";
							if(!a.equals(view.get(lockedSorted.size()))){
								List<String> sent=new ArrayList<String>();
								SendFromYahoo.send(sent,true,false,true,"abel710@yahoo.com","vektorgrafikA1","kecse.abel@ziehl-abegg.hu",""+LocalDateTime.now()+" Lakkozó: a sorrendtõl eltértek.",""+a.getLejszam(),"smtp.mail.yahoo.com");
							}
							Platform.runLater(()->{
								kTF.clear();
								kTF.setPromptText(" "+a.getLejszam());
							});

						}
				}
			}
		});*/
		
		//Listener megírása ami berakja egy lockedSet-be
				/*
									if(l!=null&&l.isBerakva()) {
										addLocked(l);
									}		
				*/		

		megjelenitendo=FXCollections.observableArrayList();
		
		lockedSorted=FXCollections.observableArrayList();
		view=FXCollections.observableArrayList();
		view.addListener(new ListChangeListener<Lapat>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Lapat> c) {
				while(c.next()){

					if(c.wasAdded()){
						rendezes=new Rendezes();
						ObservableList<Lapat> rendezett=FXCollections.observableArrayList();
						rendezett.addAll(rendezes.sort(view,lockedSorted));
						megjelenitendo.clear();
						megjelenitendo.addAll(lockedSorted);
						megjelenitendo.addAll(rendezett);
					}
				}
			}});

		lockedSet=FXCollections.observableSet();

		lockedSet.addListener(new SetChangeListener<Lapat>(){

			@Override
			public void onChanged(javafx.collections.SetChangeListener.Change<? extends Lapat> change) {
				if(change.wasAdded()){

					change.getElementAdded().setDatum2(LocalDateTime.now());
					//<--feltölt adatbázisba
					lockedSorted=FXCollections.observableArrayList(lockedSet);

					lockedSorted.sort(new Comparator<Lapat>() {

						@Override
						public int compare(Lapat o1, Lapat o2) {
							return o1.getDatum2().compareTo(o2.getDatum2());
						}
					});

					//csak 10 db lockolt rekord maradjon a listában
					//hogy a lista ne nõjjön a végletekig
					if(lockedSorted.size()>10){
						Lapat l=lockedSorted.get(0);
						lockedSorted.remove(l);
						lockedSet.remove(l);
						view.remove(l);
					}

					rendezes=new Rendezes();
					ObservableList<Lapat> rendezett;
					rendezett=rendezes.sort(view,lockedSorted);
					megjelenitendo.clear();
					megjelenitendo.addAll(lockedSorted);
					megjelenitendo.addAll(rendezett);
				}

			}});
	}

	public void addLocked(Lapat l) {
		lockedSet.add(l);
	}

	public void addView(LejCikk l) {
		view.add(l);
	}

	//ObservableList-et List-ként adom vissza
	public List<LejCikk> getView() {
		return view;
	}
	
	public List<LejCikk> getMegjelenitendo(){
		return this.megjelenitendo;
	}

}