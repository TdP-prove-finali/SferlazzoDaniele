package it.polito.tdp.Tesi.model;

import java.time.LocalDate;

public class Property {		//BASIC CLASS FOR THE TABLE PROPERTIES
	private int id;
	private String suburb;
	private String address;
	private int rooms;
	private String type;
	private int price;
	private String method;
	private String sellerG;
	private LocalDate date;
	private double distance;
	private int postCode;
	private int bedroom;
	private int bathroom;
	private Integer car;
	private int landSize;
	private Integer buildingArea;
	private Integer yearBuilt;
	private String councilArea;
	
	private int mensilRent;
	
	private double Latitude;
	private double Longitude;
	
	private String regionName;
	private int propertyCount;
	
	public Property(int id, String suburb, String address, int rooms, String type, int price, String method,
			String sellerG, LocalDate date, double distance, int postCode, int bedroom, int bathroom, Integer car,
			int landSize, Integer buildingArea, Integer yearBuilt, String councilArea, double latitude,
			double longitude, String regionName, int propertyCount) {
		super();
		this.id = id;
		this.suburb = suburb;
		this.address = address;
		this.rooms = rooms;
		this.type = type;
		this.price = price;
		this.method = method;
		this.sellerG = sellerG;
		this.date = date;
		this.distance = distance;
		this.postCode = postCode;
		this.bedroom = bedroom;
		this.bathroom = bathroom;
		this.car = car;
		this.landSize = landSize;
		this.buildingArea = buildingArea;
		this.yearBuilt = yearBuilt;
		this.councilArea = councilArea;
		Latitude = latitude;
		Longitude = longitude;
		this.regionName = regionName;
		this.propertyCount = propertyCount;
		
		//DEFINISCO LA MENSIL RENT
		
		if(price<=9000000 && price>7000000)
			mensilRent = (int) (price * 0.8/100);
		if(price <=7000000 && price>5000000)
			mensilRent = (int) (price*0.85/100);
		if(price <=5000000 && price>4000000)
			mensilRent = (int) (price*0.9/100);
		if(price<=4000000 && price>3000000)
			mensilRent = (int) (price*0.95/100);
		if(price<=3000000 && price>2000000)
			mensilRent = (int) (price*1/100);
		if(price<=2000000 && price>1000000)
			mensilRent = (int) (price*1.05/100);
		if(price<=1000000)
			mensilRent = (int) (price*1.1/100);
	}

	public int getId() {
		return id;
	}

	public String getSuburb() {
		return suburb;
	}

	public String getAddress() {
		return address;
	}

	public int getRooms() {
		return rooms;
	}

	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public String getMethod() {
		return method;
	}

	public String getSellerG() {
		return sellerG;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getDistance() {
		return distance;
	}

	public int getPostCode() {
		return postCode;
	}

	public int getBedroom() {
		return bedroom;
	}

	public int getBathroom() {
		return bathroom;
	}

	public Integer getCar() {
		return car;
	}

	public int getLandSize() {
		return landSize;
	}

	public Integer getBuildingArea() {
		return buildingArea;
	}

	public Integer getYearBuilt() {
		return yearBuilt;
	}

	public String getCouncilArea() {
		return councilArea;
	}

	public double getLatitude() {
		return Latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public String getRegionName() {
		return regionName;
	}

	public int getPropertyCount() {
		return propertyCount;
	}
	
	public int getMensilRent() {
		return mensilRent;
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
		Property other = (Property) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		return this.address + " (" + this.price + ")";
	}
	
	
	
	
	
	
	
	
}
