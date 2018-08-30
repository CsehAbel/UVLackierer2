package controller;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import lej.entity.LejCikk;

public class Rendezes {
	private List<LejCikk> R;
	private List<LejCikk> lockedSorted;


	public List<LejCikk> getLockedSorted() {
		return lockedSorted;
	}

	public void setLockedSorted(List<LejCikk> lockedSorted) {
		this.lockedSorted = lockedSorted;
	}

	public Rendezes(){

	}

	public List<LejCikk> sort(ObservableList<LejCikk> view,List<LejCikk> lock) {
		R=new ArrayList<>();
		R.addAll(view);
		R.removeAll(lock);

		List<LejCikk> r=new ArrayList<>();
		int w;
		if(lock.size()>0){
			w=lock.get(lock.size()-1).getR().getWaermen_zeit();
		} else{
			LejCikk l=minWarmen(R);
			w=l.getR().getWaermen_zeit();
			r.add(l);
			R.remove(l);
		}
		while(R.size()>0){
			ujraSzamolX(w,R);
			LejCikk l=minX(R);
			r.add(l);
			R.remove(l);
			w=l.getR().getWaermen_zeit();
		}

		for(int i=0;i<r.size();i++){
			r.get(i).setSorszam(i+1);
		}
		return r;
	}

	private void ujraSzamolX(int elozoWarmen,List<LejCikk> rendezetlen) {
		for(LejCikk i:rendezetlen){
			i.setX(elozoWarmen);
		}
	}


	private LejCikk minWarmen(List<LejCikk> rendezetlen){

		List<LejCikk> csl=new ArrayList<>();
		int min=1000000;
		for(LejCikk i:rendezetlen){
			if(i.getR().getWaermen_zeit()<min){
				min=i.getR().getWaermen_zeit();
			}
		}
		for(LejCikk i:rendezetlen){
			if(i.getR().getWaermen_zeit()==min){
				csl.add(i);
			}
		}
		csl.sort(new Comparator<LejCikk>() {

			@Override
			public int compare(LejCikk o1, LejCikk o2) {
				// TODO Auto-generated method stub
				return o1.getDatum1().compareTo(o2.getDatum1());
			}
		});
		return csl.get(0);
	}

	private LejCikk minX(List<LejCikk> rendezetlen) {
		List<LejCikk> csl=new ArrayList<>();

		int min=1000000;
		for(LejCikk i:rendezetlen){
			if(i.getX()<min){
				min=i.getX();
			}
		}
		for(LejCikk i:rendezetlen){
			if(i.getX()==min){
				csl.add(i);
			}
		}

		csl.sort(new Comparator<LejCikk>() {

			@Override
			public int compare(LejCikk o1, LejCikk o2) {
				// TODO Auto-generated method stub
				return o1.getDatum1().compareTo(o2.getDatum1());
			}});
		return csl.get(0);
	}
}
