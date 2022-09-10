package it.polito.tdp.Tesi.model;

import java.util.ArrayList;
import java.util.List;

public class RicorsioneMensilRent {
	
	List<Property> propertiesDesc;
	int budget;
	
	int maxRent;
	int ctResult;
	List<Property> resultList;
	
	//RESULTLIST MAXRENT CTTOT
	
	public int getMaxRent() {
		return maxRent;
	}
	
	public int getCtResult() {
		return ctResult;
	}
	
	public List<Property> effettuaRicorsione(List<Property> propertiesDesc, int budget) {
		this.propertiesDesc = propertiesDesc;
		this.budget = budget;
		
		List<Property> parziale = new ArrayList<Property>();
		int ctParziale = 0;
		int rentParziale = 0;
		this.maxRent = 0;
		this.ctResult = 0;
		
		this.ricorsione(parziale, ctParziale, rentParziale);
		
		return resultList;
		
	}
	
	private void ricorsione(List<Property> parziale, int ctParziale, int rentParziale) {
		
		//CASO NORMALE
		int flAggiunta = 0;
		for(Property p : propertiesDesc) {
			if(!parziale.contains(p) && ctParziale + p.getPrice()<=budget) {	//CHECK SU CONTENIMENTO E SU BUDGET RIMANENTE
				//EFFETTUO RICORSIONE
				flAggiunta = 1;
				
				parziale.add(p);
				ctParziale += p.getPrice();
				rentParziale += p.getMensilRent();
				
				//RICORSIONE
				this.ricorsione(parziale, ctParziale, rentParziale);
				
				//BACKTRACK
				parziale.remove(p);
				ctParziale -= p.getPrice();
				rentParziale -= p.getMensilRent();
				
			}
		}
		if(flAggiunta == 0 && rentParziale > this.maxRent) {	//NON HO AGGIUNTO NULLA NELLA LISTA, => CASO TERMINALE
			//SOLUZIONE VALIDA, => SALVO SOLUZIONE
			resultList = new ArrayList<Property>(parziale);
			maxRent = rentParziale;
			this.ctResult = ctParziale;
		}
		
		
	}
	
	

}
