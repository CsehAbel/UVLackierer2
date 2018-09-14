package tablazat;
import controller.Controller;
import controller.Rendezes;
import controller.SendFromExchange;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import lej.entity.LejCikk;

@SessionScoped
public class TablazatokController implements Serializable {
	
	private String lblC,lblD;
	
	
	private Rendezes rendezes;
	
	private List<LejCikk> lockedSorted;
	
	private ObservableList<LejCikk> view;
	
	private List<LejCikk> megjelenitendo;
	
	@Inject
	private Controller controller;


	@PostConstruct
	public void initialize() {
		
		/* EMAIL K�LD�S? LEJSZAM MEGKERESESE A TABLAZATBAN
		kTF.textProperty().addListener((obs,oldV,newV)->{
			if(newV.matches("[0-9]{8}")){
				for(Lapat a:view){
						if(Integer.parseInt(newV)==a.getLejszam()){
							a.setBerakva(true);
							lblC=a.getCikkszam();
							lblD=""+a.getDarabszam()+" db";
							if(!a.equals(view.get(lockedSorted.size()))){
								List<String> sent=new ArrayList<String>();
								SendFromYahoo.send(sent,true,false,true,"abel710@yahoo.com","vektorgrafikA1","kecse.abel@ziehl-abegg.hu",""+LocalDateTime.now()+" Lakkoz�: a sorrendt�l elt�rtek.",""+a.getLejszam(),"smtp.mail.yahoo.com");
							}
							Platform.runLater(()->{
								kTF.clear();
								kTF.setPromptText(" "+a.getLejszam());
							});

						}
				}
			}
		});*/
		
		//Listener meg�r�sa ami berakja egy lockedSet-be
				/*
									if(l!=null&&l.isBerakva()) {
										addLocked(l);
									}		
				*/		

		megjelenitendo=new ArrayList<>();
		lockedSorted=FXCollections.observableArrayList();
		view=FXCollections.observableArrayList();
		view.addListener(new ListChangeListener<LejCikk>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends LejCikk> c) {
				while(c.next()){

					if(c.wasAdded()){
						rendezes=new Rendezes();
						List<LejCikk> rendezett=FXCollections.observableArrayList();
						rendezett.addAll(rendezes.sort(view,lockedSorted));
						megjelenitendo.clear();
						megjelenitendo.addAll(lockedSorted);
						megjelenitendo.addAll(rendezett);
					}
				}
			}});
	}
	
	public void kivezet(int kiLej){
			this.addLocked(talal(kiLej));
	}
	
	public LejCikk talal(int kiLej){
		LejCikk talalat=null;
		for(LejCikk a:megjelenitendo){
			if(kiLej==a.getL()){
				talalat=a;
				break;
			}
		}
		return talalat;
	}

	public void addLocked(LejCikk l) {
		if((lockedSorted.size()!=megjelenitendo.size())&&!l.equals(megjelenitendo.get(lockedSorted.size()))){
			List<String> sent=new ArrayList<String>();
			SendFromExchange.send(sent,true,false,true,"abel710@yahoo.com","vektorgrafikA1","kecse.abel@ziehl-abegg.hu",""+LocalDateTime.now()+" Lakkozó: a sorrendtől eltértek.",""+l.getL(),"smtp.mail.yahoo.com");
		}
		l.setBerakva(true);
		l.setDatum2(LocalDateTime.now());
		lockedSorted.add(l);
		lblC=l.getC();
		lblD=l.getD();
		lockedSorted.sort(new Comparator<LejCikk>() {

			@Override
			public int compare(LejCikk o1, LejCikk o2) {
				return o1.getDatum2().compareTo(o2.getDatum2());
			}
		});

		//csak 10 db lockolt rekord maradjon a list�ban
		//hogy a lista ne nőljön a végletekig
		if(lockedSorted.size()>10){
			LejCikk lc=lockedSorted.get(0);
			lockedSorted.remove(lc);
			view.remove(lc);
		}

		rendezes=new Rendezes();
		List<LejCikk> rendezett;
		rendezett=rendezes.sort(view,lockedSorted);
		megjelenitendo.clear();
		megjelenitendo.addAll(lockedSorted);
		megjelenitendo.addAll(rendezett);
		
		controller.kivezetLog(l);
	}

	public void addView(LejCikk l) {
		view.add(l);
	}

	//ObservableList-et List-k�nt adom vissza
	public List<LejCikk> getView() {
		return view;
	}
	
	public List<LejCikk> getMegjelenitendo(){
		return this.megjelenitendo;
	}

	public List<LejCikk> getLockedSorted() {
		return lockedSorted;
	}	
	

}