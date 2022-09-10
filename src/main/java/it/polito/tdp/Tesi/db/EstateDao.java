package it.polito.tdp.Tesi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.Tesi.model.Property;
import it.polito.tdp.Tesi.model.StringAndInt;
import it.polito.tdp.Tesi.model.TourismData;

public class EstateDao {
	
	//FUNZIONE DI BASE PER OTTENERE TUTTE LE PROPERTIES NEL DATABASE
	public List<Property> getAllPropertiesAsc(){
		final String sql = "SELECT * FROM properties ORDER BY price asc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<Property> result = new ArrayList<Property>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			while(res.next()) {
				Property p = new Property(res.getInt("#"), res.getString("Suburb"), res.getString("Address"), res.getInt("Rooms"),
						res.getString("Type"), res.getInt("Price"), res.getString("Method"), res.getString("SellerG"), res.getDate("Date").toLocalDate(),
						res.getDouble("Distance"), res.getInt("Postcode"), res.getInt("Bedroom"), res.getInt("Bathroom"), res.getInt("Car"),
						res.getInt("Landsize"), res.getInt("BuildingArea"), res.getInt("YearBuilt"), res.getString("CouncilArea"),
						res.getDouble("Latitude"), res.getDouble("Longitude"), res.getString("RegionName"), res.getInt("PropertyCount"));
				result.add(p);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Property> getAllPropertiesDesc(){
		final String sql = "SELECT * FROM properties ORDER BY price desc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<Property> result = new ArrayList<Property>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			while(res.next()) {
				Property p = new Property(res.getInt("#"), res.getString("Suburb"), res.getString("Address"), res.getInt("Rooms"),
						res.getString("Type"), res.getInt("Price"), res.getString("Method"), res.getString("SellerG"), res.getDate("Date").toLocalDate(),
						res.getDouble("Distance"), res.getInt("Postcode"), res.getInt("Bedroom"), res.getInt("Bathroom"), res.getInt("Car"),
						res.getInt("Landsize"), res.getInt("BuildingArea"), res.getInt("YearBuilt"), res.getString("CouncilArea"),
						res.getDouble("Latitude"), res.getDouble("Longitude"), res.getString("RegionName"), res.getInt("PropertyCount"));
				result.add(p);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Property> getPropertiesFilteredAsc(boolean car, int priceMax, int rooms, String suburb, String sellerG){
		String sql = "SELECT * FROM properties WHERE ";
		if(car) {
			sql += "car>0 AND ";
		}
		sql += "price<=? AND rooms>=? AND suburb = ? ";
		if(sellerG != null) {
			sql += "AND sellerG = ? ";
		}
		sql += "ORDER BY price asc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<Property> result = new ArrayList<Property>();
			
			st.setInt(1, priceMax);
			st.setInt(2, rooms);
			st.setString(3, suburb);
			
			if(sellerG != null) {
				st.setString(4, sellerG);
			}
			
			System.out.println(sql);	//DEBUGGING
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			while(res.next()) {
				Property p = new Property(res.getInt("#"), res.getString("Suburb"), res.getString("Address"), res.getInt("Rooms"),
						res.getString("Type"), res.getInt("Price"), res.getString("Method"), res.getString("SellerG"), res.getDate("Date").toLocalDate(),
						res.getDouble("Distance"), res.getInt("Postcode"), res.getInt("Bedroom"), res.getInt("Bathroom"), res.getInt("Car"),
						res.getInt("Landsize"), res.getInt("BuildingArea"), res.getInt("YearBuilt"), res.getString("CouncilArea"),
						res.getDouble("Latitude"), res.getDouble("Longitude"), res.getString("RegionName"), res.getInt("PropertyCount"));
				result.add(p);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Property> getPropertiesFilteredDesc(boolean car, int priceMax, int rooms, String suburb, String sellerG){
		String sql = "SELECT * FROM properties WHERE ";
		if(car) {
			sql += "car>0 AND ";
		}
		sql += "price<=? AND rooms>=? AND suburb = ? ";
		if(sellerG != null) {
			sql += "AND sellerG = ? ";
		}
		sql += "ORDER BY price desc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<Property> result = new ArrayList<Property>();
			
			st.setInt(1, priceMax);
			st.setInt(2, rooms);
			st.setString(3, suburb);
			
			if(sellerG != null) {
				st.setString(4, sellerG);
			}
			
			System.out.println(sql);	//DEBUGGING
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			while(res.next()) {
				Property p = new Property(res.getInt("#"), res.getString("Suburb"), res.getString("Address"), res.getInt("Rooms"),
						res.getString("Type"), res.getInt("Price"), res.getString("Method"), res.getString("SellerG"), res.getDate("Date").toLocalDate(),
						res.getDouble("Distance"), res.getInt("Postcode"), res.getInt("Bedroom"), res.getInt("Bathroom"), res.getInt("Car"),
						res.getInt("Landsize"), res.getInt("BuildingArea"), res.getInt("YearBuilt"), res.getString("CouncilArea"),
						res.getDouble("Latitude"), res.getDouble("Longitude"), res.getString("RegionName"), res.getInt("PropertyCount"));
				result.add(p);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FUNZIONE DI BASE PER OTTENERE TUTTE I TOURISMDATA NEL DATABASE
	public List<TourismData> getAllTourismData(){
		final String sql = "SELECT * FROM tourism";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<TourismData> result = new ArrayList<TourismData>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			while(res.next()) {
				TourismData t = new TourismData(res.getInt("#"), res.getDate("Quarter").toLocalDate(),
						res.getString("Purpose"), res.getDouble("Trips"));
				result.add(t);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FUNZIONI PER OTTENERE LE VALUTAZIONE DI PARTENZA DEL PROGRAMMA
	public List<StringAndInt> getSuburbsWMostHouses(){
		final String sql = "SELECT p.Suburb, COUNT(*) AS n "
				+ "FROM properties p "
				+ "GROUP BY p.Suburb "
				+ "ORDER BY COUNT(*) desc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<StringAndInt> result = new ArrayList<StringAndInt>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			for(int i = 0;i<10;i++) {
				res.next();
				StringAndInt si = new StringAndInt(res.getString("Suburb"), res.getInt("n"));
				result.add(si);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<StringAndInt> getMostExpensiveSuburb(){
		final String sql = "SELECT p.Suburb, AVG(p.Price) AS price "
				+ "FROM properties p "
				+ "GROUP BY p.Suburb "
				+ "ORDER BY AVG(p.Price) desc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<StringAndInt> result = new ArrayList<StringAndInt>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			for(int i = 0;i<10;i++) {
				res.next();
				StringAndInt si = new StringAndInt(res.getString("Suburb"), res.getInt("price"));
				result.add(si);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<StringAndInt> getCheapestSuburb(){
		final String sql = "SELECT p.Suburb, AVG(p.Price) AS price "
				+ "FROM properties p "
				+ "GROUP BY p.Suburb "
				+ "ORDER BY AVG(p.Price) asc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<StringAndInt> result = new ArrayList<StringAndInt>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			for(int i = 0;i<10;i++) {
				res.next();
				StringAndInt si = new StringAndInt(res.getString("Suburb"), res.getInt("price"));
				result.add(si);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<StringAndInt> getSellerWMostSells(){
		final String sql = "SELECT p.SellerG, COUNT(*) AS sells "
				+ "FROM properties p "
				+ "GROUP BY p.SellerG "
				+ "ORDER BY COUNT(*) desc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<StringAndInt> result = new ArrayList<StringAndInt>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			for(int i = 0;i<10;i++) {
				res.next();
				StringAndInt si = new StringAndInt(res.getString("SellerG"), res.getInt("sells"));
				result.add(si);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllSuburbs(){
		final String sql = "SELECT DISTINCT Suburb FROM properties ORDER BY suburb asc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<String> result = new ArrayList<String>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			while(res.next()) {
				String s = res.getString("Suburb");
				result.add(s);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllSellerG(){
		final String sql = "SELECT DISTINCT SellerG FROM properties ORDER BY SellerG asc";
		try {
			//STABILISCO LA CONNESSIONE
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			List<String> result = new ArrayList<String>();
			
			//ESEGUO LA QUERY
			ResultSet res = st.executeQuery();
			
			//SCANSIONO IL RISULTATO, E LO SALVO NELLA LISTA RESULT
			while(res.next()) {
				String s = res.getString("SellerG");
				result.add(s);
			}
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
