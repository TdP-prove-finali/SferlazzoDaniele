package it.polito.tdp.Tesi.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		
		System.out.println(model.getRecommendedList("Minor numero di abitazioni", 3115000, false, 100000000, null, "Footscray", 0));

	}

}
