package it.polito.tdp.Tesi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.Tesi.model.Model;
import it.polito.tdp.Tesi.model.Property;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox cbCar;

    @FXML
    private ComboBox<Property> cmbAbitazione;

    @FXML
    private ComboBox<String> cmbObiettivo;

    @FXML
    private ComboBox<String> cmbSellerG;

    @FXML
    private ComboBox<String> cmbSuburb;

    @FXML
    private TextField txtBudget;

    @FXML
    private TextArea txtResult;
    
    @FXML
    private TextArea txtResultAnalizza;

    @FXML
    private TextField txtRooms;

    @FXML
    private TextField txtPrice;
    
    
    
    @FXML
    private TableView<Property> tblProperties;
    
    @FXML
    private TableColumn<Property, String> clAddress;

    @FXML
    private TableColumn<Property, Integer> clCar;

    @FXML
    private TableColumn<Property, Integer> clPostCode;

    @FXML
    private TableColumn<Property, Integer> clPrice;

    @FXML
    private TableColumn<Property, String> clRegionName;

    @FXML
    private TableColumn<Property, Integer> clRooms;

    @FXML
    private TableColumn<Property, String> clSellerG;

    @FXML
    private TableColumn<Property, String> clSuburb;
    
    

    private String analisiPreliminare;
    
    @FXML
    void handleAnalisiPreliminare(ActionEvent event) {
    	
    	long startTime = System.nanoTime();		//TIMER
    	
    	txtResult.clear();
    	txtResult.appendText(analisiPreliminare);
    	
    	long endTime = System.nanoTime();		//TIMER
    	long duration = (endTime - startTime)/1000000;		//TIMER
    	System.out.println(duration);		//TIMER
    }
    
    @FXML
    void handleAnalizza(ActionEvent event) {
    	long startTime = System.nanoTime();		//TIMER
    	txtResultAnalizza.clear();
    	if(cmbAbitazione.getValue()==null) {
    		txtResultAnalizza.appendText("Selezionare un'Abitazione dall'apposita tendina");
    		return;
    	}
    	txtResultAnalizza.appendText(this.model.simulazione(cmbAbitazione.getValue()));
    	
    	long endTime = System.nanoTime();		//TIMER
    	long duration = (endTime - startTime)/1000000;		//TIMER
    	System.out.println(duration);		//TIMER
    }

    @FXML
    void handleCalcola(ActionEvent event) {
    	long startTime = System.nanoTime();		//TIMER
    	txtResult.clear();
    	
    	
    	String obiettivo = cmbObiettivo.getValue();
    	if(obiettivo == null) {
    		txtResult.appendText("Inserire un obiettivo");
    		return;
    	}
    	Integer budget;
    	if(txtBudget.getText().equals("")) {
    		txtResult.appendText("Inserire il budget disponibile");
    		return;
    	} else budget = Integer.parseInt(txtBudget.getText());
    	
    	Integer rooms;
    	if(txtRooms.getText().equals("")) {
    		rooms = 0;
    	} else rooms = Integer.parseInt(txtRooms.getText());
    	
    	String suburb;
    	if(cmbSuburb.getValue() == "Quartiere") {
    		txtResult.appendText("Selezionare un quartiere dalla lista");
    		return;
    	} else suburb = cmbSuburb.getValue();
    	
    	Integer priceMax;
    	if(txtPrice.getText().equals("")) {
    		priceMax = 100000000;
    	} else priceMax = Integer.parseInt(txtPrice.getText());
    	
    	Boolean car = cbCar.isSelected();
    	
    	String sellerG;
    	if(cmbSellerG.getValue().equals("Venditore")) {
    		sellerG = null;
    	} else sellerG = cmbSellerG.getValue();
    	
    	
    	
    	
    	//ELABORAZIONE DELL'INPUT
    	
    	
    	if(obiettivo.equals("Maggior numero di abitazioni") || obiettivo.equals("Minor numero di abitazioni")) {	//CASO 1/2
    		List<Property> properties = this.model.getRecommendedList(obiettivo, budget, car, priceMax, sellerG, suburb, rooms);
    		if(properties.size()==0) {
    			//CASO LISTA VUOTA
    			txtResult.appendText("Non è stata trovata nessuna abitazione secondo i filtri selezionati");
    			return;
    		}
    		int somma = 0;
    		for(Property p : properties) {
    			somma+= p.getPrice();
    		}
    		String result = "Lista composta da " + properties.size() + " abitazioni, per un totale di: " + somma + "$\n";
    		txtResult.appendText(result);
    		cmbAbitazione.getItems().clear();
    		cmbAbitazione.getItems().addAll(properties);
    		
    		
    		
    		//IMPOSTO LA TABELLA
    		clSuburb.setCellValueFactory(new PropertyValueFactory<Property, String>("suburb"));
    		clAddress.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
    		clRooms.setCellValueFactory(new PropertyValueFactory<Property, Integer>("rooms"));
    		clPrice.setCellValueFactory(new PropertyValueFactory<Property, Integer>("price"));
    		clSellerG.setCellValueFactory(new PropertyValueFactory<Property, String>("sellerG"));
    		clPostCode.setCellValueFactory(new PropertyValueFactory<Property, Integer>("postCode"));
    		clCar.setCellValueFactory(new PropertyValueFactory<Property, Integer>("car"));
    		clRegionName.setCellValueFactory(new PropertyValueFactory<Property, String>("regionName"));
    		
    		tblProperties.setItems(FXCollections.observableArrayList(properties));
    		
    		long endTime = System.nanoTime();		//TIMER
        	long duration = (endTime - startTime)/1000000;		//TIMER
        	System.out.println(duration);		//TIMER
    		
    		return;
    	}
    	
    	if(obiettivo.equals("Maggior guadagno mensile")) {	//CASO 3
    		List<Property> properties = this.model.getRecommendedList(obiettivo, budget, car, priceMax, sellerG, suburb, rooms);
    		if(properties == null || properties.size()==0) {
    			//CASO LISTA VUOTA
    			txtResult.appendText("Non è stata trovata nessuna abitazione secondo i filtri selezionati");
    			return;
    		}
    		int somma = 0;
    		int rentTot = 0;
    		for(Property p : properties) {
    			somma += p.getPrice();
    			rentTot += p.getMensilRent();
    		}
    		String result = "Lista composta da " + properties.size() + " abitazioni, per un totale di: " + somma + "$\n"+
    				"Guadagno complessivo mensile: " + rentTot + "$\n";
    		txtResult.appendText(result);
    		cmbAbitazione.getItems().clear();
    		cmbAbitazione.getItems().addAll(properties);
    		
    		//IMPOSTO LA TABELLA
    		clSuburb.setCellValueFactory(new PropertyValueFactory<Property, String>("suburb"));
    		clAddress.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
    		clRooms.setCellValueFactory(new PropertyValueFactory<Property, Integer>("rooms"));
    		clPrice.setCellValueFactory(new PropertyValueFactory<Property, Integer>("price"));
    		clSellerG.setCellValueFactory(new PropertyValueFactory<Property, String>("sellerG"));
    		clPostCode.setCellValueFactory(new PropertyValueFactory<Property, Integer>("postCode"));
    		clCar.setCellValueFactory(new PropertyValueFactory<Property, Integer>("car"));
    		clRegionName.setCellValueFactory(new PropertyValueFactory<Property, String>("regionName"));
    		
    		tblProperties.setItems(FXCollections.observableArrayList(properties));
    		
    		long endTime = System.nanoTime();		//TIMER
        	long duration = (endTime - startTime)/1000000000;		//TIMER
        	System.out.println(duration);		//TIMER
    		
    		return;
    	}
    }

    @FXML
    void initialize() {
    	assert cbCar != null : "fx:id=\"cbCar\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clAddress != null : "fx:id=\"clAddress\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clCar != null : "fx:id=\"clCar\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPostCode != null : "fx:id=\"clPostCode\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPrice != null : "fx:id=\"clPrice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clRegionName != null : "fx:id=\"clRegionName\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clRooms != null : "fx:id=\"clRooms\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clSellerG != null : "fx:id=\"clSellerG\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clSuburb != null : "fx:id=\"clSuburb\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cbCar != null : "fx:id=\"cbCar\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAbitazione != null : "fx:id=\"cmbAbitazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbObiettivo != null : "fx:id=\"cmbObiettivo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSellerG != null : "fx:id=\"cmbSellerG\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSuburb != null : "fx:id=\"cmbSuburb\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblProperties != null : "fx:id=\"tblProperties\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtBudget != null : "fx:id=\"txtBudget\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRooms != null : "fx:id=\"txtRooms\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	txtResult.clear();
    	this.analisiPreliminare = this.model.initialAnalysis();
    	txtResult.appendText(this.analisiPreliminare);
    	
    	this.cmbSellerG.getItems().addAll(this.model.getAllSellerG());
    	this.cmbSuburb.getItems().addAll(this.model.getAllSuburbs());
    	
    	this.cmbObiettivo.getItems().add("Maggior numero di abitazioni");
    	this.cmbObiettivo.getItems().add("Minor numero di abitazioni");
    	this.cmbObiettivo.getItems().add("Maggior guadagno mensile");
    	
    	this.cmbSellerG.setValue("Venditore");
    	this.cmbSuburb.setValue("Quartiere");
    	
    }

}
