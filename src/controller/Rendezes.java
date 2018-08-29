package controller;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Rendezes {
	private ObservableList<Lapat> R;
	private ObservableList<Lapat> lockedSorted;


	public ObservableList<Lapat> getLockedSorted() {
		return lockedSorted;
	}

	public void setLockedSorted(ObservableList<Lapat> lockedSorted) {
		this.lockedSorted = lockedSorted;
	}

	public Rendezes(){

	}

	public ObservableList<Lapat> sort(ObservableList<Lapat> view,ObservableList<Lapat> lock) {
		R=FXCollections.observableArrayList();
		R.addAll(view);
		R.removeAll(lock);

		ObservableList<Lapat> r=FXCollections.observableArrayList();
		int w;
		if(lock.size()>0){
			w=lock.get(lock.size()-1).getWarmen();
		} else{
			Lapat l=minWarmen(R);
			w=l.getWarmen();
			r.addAll(l);
			R.remove(l);
		}
		while(R.size()>0){
			ujraSzamolX(w,R);
			Lapat l=minX(R);
			r.addAll(l);
			R.removeAll(l);
			w=l.getWarmen();
		}

		for(int i=0;i<r.size();i++){
			r.get(i).setSorszam(i+1);
		}
		return r;
	}

	private void ujraSzamolX(int elozoWarmen,ObservableList<Lapat> rendezetlen) {
		for(Lapat i:rendezetlen){
			i.setX(elozoWarmen);
		}
	}


	private Lapat minWarmen(ObservableList<Lapat> rendezetlen){

		ObservableList<Lapat> csl=FXCollections.observableArrayList();
		int min=100000;
		for(Lapat i:rendezetlen){
			if(i.getWarmen()<min){
				min=i.getWarmen();
			}
		}
		for(Lapat i:rendezetlen){
			if(i.getWarmen()==min){
				csl.add(i);
			}
		}
		csl.sort(new Comparator<Lapat>() {

			@Override
			public int compare(Lapat o1, Lapat o2) {
				// TODO Auto-generated method stub
				return o1.getDatum1().compareTo(o2.getDatum1());
			}
		});
		return csl.get(0);
	}

	private Lapat minX(ObservableList<Lapat> rendezetlen) {
		ObservableList<Lapat> csl=FXCollections.observableArrayList();

		int min=10000;
		for(Lapat i:rendezetlen){
			if(i.getX()<min){
				min=i.getX();
			}
		}
		for(Lapat i:rendezetlen){
			if(i.getX()==min){
				csl.add(i);
			}
		}

		csl.sort(new Comparator<Lapat>() {

			@Override
			public int compare(Lapat o1, Lapat o2) {
				// TODO Auto-generated method stub
				return o1.getDatum1().compareTo(o2.getDatum1());
			}});
		return csl.get(0);
	}
}
