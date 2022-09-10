package it.polito.tdp.Tesi.db;

import it.polito.tdp.Tesi.model.Property;
import it.polito.tdp.Tesi.model.TourismData;

public class TestDao {

	public static void main(String[] args) {
		
		EstateDao dao = new EstateDao();
		int i = 0;
		for(TourismData td : dao.getAllTourismData()) {
			System.out.println(td);
			i++;
		}
		System.out.println("Total entries: " + i);
		
		i = 0;
		for(Property p : dao.getAllPropertiesAsc()) {
			System.out.println(p);
			i++;
		}
		System.out.println("Total entries: " + i);

	}

}
