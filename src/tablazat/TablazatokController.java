package tablazat;
import controller.Controller;
import controller.Rendezes;
import controller.SendFromExchange;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	private String lblC;

	private int lblD;
	
	
	private Rendezes rendezes;
	
	private List<LejCikk> lockedSorted;
	
	private ObservableList<LejCikk> view;
	
	private List<LejCikk> megjelenitendo;
	
	@Inject
	private Controller controller;


	@PostConstruct
	public void initialize() {
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
			String datum=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
			String targy=datum+" Lakkozó: a sorrendtől eltértek.";
			String uzenet="Kiszedve:\r\n"+l.getSorszam()+"\t"+l.getL()+"\r\nEredeti lista:\r\n";
			List<LejCikk> listLc=new ArrayList<>();
			listLc.addAll(view);
			listLc.removeAll(lockedSorted);
			for(LejCikk lc:megjelenitendo){
				String sor="%s\t"+lc.getL()+"\r\n";
				if(lc.isBerakva()){
					sor=String.format(sor, "X");
				} else {
					sor=String.format(sor, ""+lc.getSorszam());
				}
				uzenet+=sor;
			}
			SendFromExchange.send(sent,true,"StromUV","Abcd123456","StromUV@ziehl-abegg.hu","peter.jozsef@ziehl-abegg.hu","kecse.abel@ziehl-abegg.hu",targy,uzenet);
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
		
		if(lockedSorted.size()==megjelenitendo.size()){
			megjelenitendo.clear();
			view.clear();
			lockedSorted.clear();
		}
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