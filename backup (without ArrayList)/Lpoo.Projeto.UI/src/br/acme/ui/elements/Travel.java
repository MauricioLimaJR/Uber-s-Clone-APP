package br.acme.ui.elements;

import br.acme.location.Lugar;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Travel extends GridPane{

	private Solicitante user;
	private Motorista driver;
	private Lugar source;
	private Lugar destiny;
	private String value;
	private String payForm;
	
	public Travel(Solicitante user, Motorista driver){
		this.user = user;
		this.driver = driver;
		
		/*
		 * Fields IO
		 */
		
		Label source = new Label("Origem: ");
		TextField sourceField = new TextField();
		sourceField.setPromptText("lugar de origem..");
		
		Label destiy = new Label("Destino: ");
		TextField destinyField = new TextField();
		destinyField.setPromptText("para onde ir?");
		
		Label value = new Label("Valor: ");
		TextField valueField = new TextField();
		
		Label payForm = new Label("pagar com:");
		TextField payFormField = new TextField();
		
		/*
		 * Below, put elements in gridpane structure
		 */
		
		//Source place
		GridPane.setConstraints(source, 1, 1);
		GridPane.setConstraints(sourceField, 2, 1);
		//Destiny
		GridPane.setConstraints(destiy, 1, 2);
		GridPane.setConstraints(destinyField, 2, 2);
		//Value
		GridPane.setConstraints(value, 1, 3);
		GridPane.setConstraints(valueField, 2, 3);
		//Pay form
		GridPane.setConstraints(payForm, 1, 4);
		GridPane.setConstraints(payFormField, 2, 4);
		
	}
}
