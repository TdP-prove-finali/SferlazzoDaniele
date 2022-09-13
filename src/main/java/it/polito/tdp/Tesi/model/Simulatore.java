package it.polito.tdp.Tesi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.Tesi.model.Event.EventType;

public class Simulatore {
	
	//PARAMETRI DI OUTPUT
	int mesiTot;
	List<Event> affitti;		//LISTA CHE SEGNA COME VIENE AFFITTATA MENSILMENTE L'ABITAZIONE
	
	//STATO DEL MONDO
	private Property abitazione;
	private List<TourismData> turismoPrecedente;	//CONTIENE GLI ULTIMI 5 ANNI DI TURISMO (QUINDI AVRA 20 ELEMENTI)
	private TourismData turismoAttuale;		//ULTIMO ELEMENTO DELLA LISTA TURISMOPRECEDENTE
	
	private LocalDate dataAttuale;
	
	//PARAMETRI DELLA SIMULAZIONE
	private double guadagno;
	private int flAffittoSemestrale;
	
	//CODA DEGLI EVENTI
	private Event eventoAttuale;
	
	public void init(Property abitazione, List<TourismData> turismoPrecedente) {
		//INIZIALIZZARE IL PRIMO EVENTO ED I PARAMETRI
		
		this.abitazione = abitazione;
		this.turismoPrecedente = turismoPrecedente;
		
		this.guadagno = 0;
		this.affitti = new ArrayList<Event>();
		this.mesiTot = 0;
		this.guadagno = 0;
		this.flAffittoSemestrale = 0;
		
		//DEVO INIZIALIZZARE IL PRIMO EVENTO
		this.dataAttuale = this.turismoPrecedente.get(0).getQuarter().plusMonths(3);
		this.generateTurismoAttuale();
		this.generateEvent();
		
	}
	
	

	public void run() {
		int i = 0;
		while(guadagno<abitazione.getPrice()) {		//LIMITE DELLA SIMULAZIONE
			dataAttuale = dataAttuale.plusMonths(1);		//AGGIORNO LA DATA, POICHE' PASSA UN MESE
			i++;
			if(i==3) {
				i=0;
				this.generateTurismoAttuale();
				//POICHE HO CAMBIATO STAGIONE
			}
			this.handleEvent(eventoAttuale);
		}
	}
	
	private void handleEvent(Event e) {
		
		this.affitti.add(e);
		this.mesiTot++;
		
		if(e.getType()==EventType.BUSINESS) {
			guadagno += this.abitazione.getMensilRent();
			
			if(e.getDurata()>0) {
				eventoAttuale = new Event(dataAttuale, EventType.BUSINESS, e.getDurata()-1);
				this.flAffittoSemestrale = 1;	//OVVERO IN QUESTO PERIODO HO AVUTO UN AFFITTO SEMESTRALE UNICO
				return;
				
			}
			
			if(this.flAffittoSemestrale == 1) {
				this.flAffittoSemestrale = 0;
				this.generateEvent();
				return;	//IN QUESTO MODO NON POSSO AVERE ASSICURATO UN BUSINESS DOPO UN AFFITTO SEMESTRALE DI BUSINESS
				
			}
			
			double random = Math.random();
			if(random<0.5) {
				//RIAFFITTO PER UN SINGOLO MESE
				eventoAttuale = new Event(dataAttuale, EventType.BUSINESS,0);
				return;
			}
			if(random<0.7) {
				//RIAFFITTO PER L'INTERA STAGIONE
				
				eventoAttuale = new Event(dataAttuale, EventType.BUSINESS, 2);
				
				return;
			}
			//NON HO RIAFFITTO, QUINDI DEVO GENERARE UN NUOVO EVENTO CASUALE
			this.generateEvent();
			return;
		}
		
		if(e.getType()==EventType.HOLIDAY) {
			guadagno += this.abitazione.getMensilRent();
			double random = Math.random();
			if(random<0.2) {
				//RIAFFITTO PER UN SINGOLO MESE
				eventoAttuale = new Event(dataAttuale, EventType.HOLIDAY,0);
			}
			//NON HO RIAFFITTO, QUINDI DEVO GENERARE UN NUOVO EVENTO CASUALE
			this.generateEvent();
			return;
		}
		
		if(e.getType()==EventType.VISITING) {
			guadagno += this.abitazione.getMensilRent();
			double random = Math.random();
			if(random<0.1) {
				//RIAFFITTO PER UN SINGOLO MESE
				eventoAttuale = new Event(dataAttuale, EventType.VISITING,0);
			}
			//NON HO RIAFFITTO, QUINDI DEVO GENERARE UN NUOVO EVENTO CASUALE
			this.generateEvent();
			return;
		}
		
		if(e.getType()==EventType.OTHER) {
			guadagno += this.abitazione.getMensilRent();
			double random = Math.random();
			if(random<0.05) {
				//RIAFFITTO PER UN SINGOLO MESE
				eventoAttuale = new Event(dataAttuale, EventType.OTHER,0);
			}
			//NON HO RIAFFITTO, QUINDI DEVO GENERARE UN NUOVO EVENTO CASUALE
			this.generateEvent();
			return;
		}
		
		if(e.getType()==EventType.NONE) {
			this.generateEvent();
			return;
		}
		
	}
	
	private void generateTurismoAttuale() {
		double business = 0;
		double holiday = 0;
		double visiting = 0;
		double other = 0;
		for(TourismData t : turismoPrecedente) {
			business += t.getBusiness();
			holiday += t.getHoliday();
			visiting += t.getVisiting();
			other += t.getOther();
		}
		
		LocalDate nuovoQuarter = turismoPrecedente.get(0).getQuarter().plusMonths(3);
		business = business/turismoPrecedente.size();
		holiday = holiday/turismoPrecedente.size();
		visiting = visiting/turismoPrecedente.size();
		other = other/turismoPrecedente.size();
		
		turismoAttuale = new TourismData(nuovoQuarter, business, holiday, visiting, other);
		turismoPrecedente.remove(turismoPrecedente.size()-1);
		turismoPrecedente.add(0,turismoAttuale);
		//RIMUOVO L'ELEMENTO PIU VECCHIO DALLA LISTA, ED AGGIUNGO IL NUOVO ELEMENTO IN PRIMA POSIZIONE
	}

	private void generateEvent() {
		double random = Math.random();
		if(random<0.034) {
			//CASA SFITTA
			eventoAttuale = new Event(dataAttuale, EventType.NONE,0);
			return;
		}
		double somma = turismoAttuale.getBusiness() + turismoAttuale.getHoliday() + turismoAttuale.getVisiting() + turismoAttuale.getOther();
		double pBusiness = turismoAttuale.getBusiness()/somma;
		double pHoliday = turismoAttuale.getHoliday()/somma;
		double pVisiting = turismoAttuale.getVisiting()/somma;
		
		random = Math.random();
		
		if(random<pBusiness) {
			//BUSINESS
			eventoAttuale = new Event(dataAttuale, EventType.BUSINESS,1);
			return;
		}
		if(random<pBusiness + pHoliday) {
			//HOLIDAY
			eventoAttuale = new Event(dataAttuale, EventType.HOLIDAY,0);
			return;
		}
		if(random<pBusiness + pHoliday + pVisiting) {
			//VISITING
			eventoAttuale = new Event(dataAttuale, EventType.VISITING,0);
			return;
		}
		
		//OTHER
		eventoAttuale = new Event(dataAttuale, EventType.OTHER,0);
		return;
		
		
	}
	
	public int getMesiTot() {
		return this.mesiTot;
	}
	
	public List<Event> getAffitti(){
		return this.affitti;
	}
	
}
