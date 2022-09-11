package it.polito.tdp.Tesi.model;

import java.time.LocalDate;

public class TourismData {		//BASIC CLASS FOR THE TABLE TOURISM
	
	private LocalDate quarter;
	private double business;
	private double holiday;
	private double visiting;
	private double other;
	
	public TourismData(LocalDate quarter, double business, double holiday, double visiting, double other) {
		super();
		this.quarter = quarter;
		this.business = business;
		this.holiday = holiday;
		this.visiting = visiting;
		this.other = other;
	}
	
	public LocalDate getQuarter() {
		return quarter;
	}
	
	public double getBusiness() {
		return business;
	}
	
	public double getHoliday() {
		return holiday;
	}
	
	public double getVisiting() {
		return visiting;
	}
	
	public double getOther() {
		return other;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quarter == null) ? 0 : quarter.hashCode());
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
		if (quarter == null) {
			if (other.quarter != null)
				return false;
		} else if (!quarter.equals(other.quarter))
			return false;
		return true;
	}
	@Override
	public String toString() {	//TODO CAMBIARE EVENTUALMENTE IL TOSTRING
		return "TourismData [date=" + quarter + ", business=" + business + ", holiday=" + holiday + ", visiting="
				+ visiting + ", other=" + other + "]";
	}
	
	
	
	
	
	
		
}
