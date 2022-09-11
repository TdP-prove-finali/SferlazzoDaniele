package it.polito.tdp.Tesi.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{

	
	
	public enum EventType{
		BUSINESS,
		HOLIDAY,
		VISITING,
		OTHER,
		NONE
	}
	
	private LocalDate tempo;
	private EventType type;
	private int durata;		//USED FOR YEARLY RENT
	
	public Event(LocalDate tempo, EventType type, int durata) {
		super();
		this.tempo = tempo;
		this.type = type;
		this.durata = durata;
	}

	public LocalDate getTempo() {
		return tempo;
	}

	public EventType getType() {
		return type;
	}
	
	public int getDurata() {
		return this.durata;
	}

	@Override
	public int compareTo(Event o) {
		return this.tempo.compareTo(o.getTempo());
	}
	
	public String toString() {
		return this.tempo + " (" + this.type + ")";
	}

}
