package it.polito.tdp.formula;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Adiacenza;
import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
    
	Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> boxAnno;

    @FXML
    private Button btnSelezionaStagione;

    @FXML
    private ComboBox<?> boxGara;

    @FXML
    private Button btnSimulaGara;

    @FXML
    private TextField textInputK;

    @FXML
    private TextField textInputK1;

    @FXML
    private TextArea txtResult;

    @FXML
    void doSelezionaStagione(ActionEvent event) {
    	txtResult.clear();
    	Integer anno = this.boxAnno.getValue();
        if(anno== null ) {
       	 txtResult.appendText("Seleziona una stagione");
       	 return;
        }
        this.model.creaGrafo(anno);
        txtResult.appendText(String.format("Grafo creato con %d vertici e %d archi \n", this.model.nVertici(), this.model.nArchi()));
        List<Adiacenza> massimi = this.model.getMassimi();
        if(!massimi.isEmpty()) {
        	for(Adiacenza a : massimi) {
        	txtResult.appendText(a.getR1().toString()+" / "+ a.getR2().toString()+"- Peso Massimo:"+ a.getPeso().toString() +"\n");
        	}
        	}else {
        Adiacenza massimo = this.model.getArcoMax();
        txtResult.appendText(massimo.getR1().toString()+" / "+massimo.getR2().toString()+"- Peso Massimo"+ massimo.getPeso().toString()+"\n");	
        	}
        }

    @FXML
    void doSimulaGara(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert btnSelezionaStagione != null : "fx:id=\"btnSelezionaStagione\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert boxGara != null : "fx:id=\"boxGara\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert btnSimulaGara != null : "fx:id=\"btnSimulaGara\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK != null : "fx:id=\"textInputK\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert textInputK1 != null : "fx:id=\"textInputK1\" was not injected: check your FXML file 'FormulaOne.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'FormulaOne.fxml'.";

    }
    public void setModel(Model model) {
    	this.model=model;
    	this.boxAnno.getItems().addAll(model.getAnni());
    }
}
