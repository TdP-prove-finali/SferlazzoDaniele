package it.polito.tdp.Tesi.model;

import java.time.LocalDate;

public class TourismData {		//BASIC CLASS FOR THE TABLE TOURISM
	
	private int id;
	private LocalDate quarter;
	private String purpose;
	private double trips;
	
	public TourismData(int id, LocalDate quarter, String purpose, double trips) {
		super();
		this.id = id;
		this.quarter = quarter;
		this.purpose = purpose;
		this.trips = trips;
	}
	public int getId() {
		return id;
	}
	public LocalDate getQuarter() {
		return quarter;
	}
	public String getPurpose() {
		return purpose;
	}
	public double getTrips() {
		return trips;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TourismData other = (TourismData) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {	//TODO EVENTUALE TO STRING
		return "TourismData [id=" + id + ", quarter=" + quarter + ", purpose=" + purpose + ", trips=" + trips + "]";
	}
	
	
	
	
		
}
