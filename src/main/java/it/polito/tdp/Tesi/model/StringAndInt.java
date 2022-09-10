package it.polito.tdp.Tesi.model;

public class StringAndInt {
	
	//CLASSE WRAPPER PER RIPORTARE RISULTATI DEL DAO CON STRING AND INT
	
	private String stringa;
	private Integer intero;
	
	public StringAndInt(String stringa, Integer intero) {
		super();
		this.stringa = stringa;
		this.intero = intero;
	}
	public String getStringa() {
		return stringa;
	}
	public Integer getIntero() {
		return intero;
	}
	
	public String toString() {
		return this.stringa + "(" + this.intero + ")";
	}
	
	
}
