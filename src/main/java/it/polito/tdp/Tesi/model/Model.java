package it.polito.tdp.Tesi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.Tesi.db.EstateDao;

public class Model {
	
	private EstateDao dao;
	
	public List<Property> getAllPropertiesAsc(){
		this.dao = new EstateDao();
		return this.dao.getAllPropertiesAsc();
	}
	
	public List<Property> getAllPropertiesDesc(){
		this.dao = new EstateDao();
		return this.dao.getAllPropertiesDesc();
	}
	
	public List<TourismData> getAllTourismData(){
		this.dao = new EstateDao();
		return this.dao.getAllTourismData();
	}
	
	public String initialAnalysis() {
		this.dao = new EstateDao();
		String result = "Quartieri più costosi:\n";
		List<StringAndInt> lSi = this.dao.getMostExpensiveSuburb();
		for(StringAndInt si : lSi)
			result += si+"\n";
		result += "\n";
		
		lSi = this.dao.getCheapestSuburb();
		result += "Quartieri meno costosi:\n";
		for(StringAndInt si : lSi)
			result += si + "\n";
		result += "\n";
		
		lSi = this.dao.getSuburbsWMostHouses();
		result += "Quartieri con più abitazioni:\n";
		for(StringAndInt si : lSi)
			result += si + "\n";
		result += "\n";
		
		lSi = this.dao.getSellerWMostSells();
		result += "Venditori con più offerte:\n";
		for(StringAndInt si : lSi)
			result += si + "\n";
		
		return result;
		
	}
	
	public List<String> getAllSuburbs(){
		this.dao = new EstateDao();
		return this.dao.getAllSuburbs();
	}
	
	public List<String> getAllSellerG(){
		this.dao = new EstateDao();
		return this.dao.getAllSellerG();
	}
	
	public List<Property> getRecommendedList(String obiettivo, int budget, boolean car, int priceMax, String sellerG, String suburb, int rooms){
		this.dao = new EstateDao();
		
		if(obiettivo.equals("Maggior numero di abitazioni")) {	//OBIETTIVO 1
			int somma = 0;
			List<Property> properties = this.dao.getPropertiesFilteredAsc(car, priceMax, rooms, suburb, sellerG);
			
			if(properties.size()==0) {
				//LISTA VUOTA, NON E STATA TROVATA NESSUNA ABITAZIONE
				return properties;
			}
			
			List<Property> resultList = new ArrayList<Property>();	//PROBLEMA, LISTA VUOTA	/GESTIRE CASO DI LISTA VUOTA)
			while(somma<=budget && properties.size()!=0) {
				resultList.add(properties.get(0));
				somma += properties.get(0).getPrice();
				properties.remove(0);
			}
			if(somma>budget) {
				somma -= resultList.get(resultList.size()-1).getPrice();
				resultList.remove(resultList.size()-1);
			}
			String result = "Lista composta da " + resultList.size() + " abitazioni, per un totale di: " + somma + "$\n";
			for(Property p : resultList)
				result += p+"\n";
			return resultList;
			
		}
		
		if(obiettivo.equals("Minor numero di abitazioni")) {	//OBIETTIVO 2
			int somma = 0;
			List<Property> properties = this.dao.getPropertiesFilteredDesc(car, priceMax, rooms, suburb, sellerG);
			if(properties.size()==0) {
				//LISTA VUOTA, NON E STATA TROVATA NESSUNA ABITAZIONE
				return properties;
			}
			List<Property> resultList = new ArrayList<Property>();
			while(somma<=budget && properties.size()!=0) {
				resultList.add(properties.get(0));
				somma += properties.get(0).getPrice();
				properties.remove(0);
			}
			if(somma>budget) {
				somma -= resultList.get(resultList.size()-1).getPrice();
				resultList.remove(resultList.size()-1);
			}
			
			if(resultList.size()==0) {	//GESTISCO CASO LISTA VUOTA
				int fl = 0;
				for(int i = 0;i<properties.size() && fl==0;i++) {
					if(properties.get(i).getPrice()<budget) {
						fl=1;
						resultList.add(properties.get(i));
					}
				}
			}
			
			
			String result = "Lista composta da " + resultList.size() + " abitazioni, per un totale di: " + somma + "$\n";
			for(Property p : resultList)
				result += p+"\n";
			return resultList;
			
		}
		
		if(obiettivo.equals("Maggior guadagno mensile")) {		//OBIETTIVO 3
			//DA FARE RICORSIONE
			
			List<Property> properties = this.dao.getPropertiesFilteredDesc(car, priceMax, rooms, suburb, sellerG);
			
			RicorsioneMensilRent ricorsione = new RicorsioneMensilRent();
			List<Property> resultList = ricorsione.effettuaRicorsione(properties, budget);
			return resultList;
			
		}
		
		return null;	//INUTILE
	}
	
}
