package br.acme.ui.elements;

import java.sql.SQLException;
import java.util.Random;

import br.acme.database.TravelDAO;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

@SuppressWarnings("unused")
public class Travel extends GridPane{

	private Solicitante user;
	private Motorista driver;
	Double travelValue;
	
	Label driverName = new Label("Motorista:");
	static TextField driverField;
	
	Label source = new Label("Origem: ");
	TextField sourceField = new TextField();
	
	Label destiy = new Label("Destino: ");
	TextField destinyField = new TextField();
	
	static Button makeValue = new Button("Calcular preço");
	
	Label value = new Label("Valor: ");
	static TextField valueField = new TextField();
	
	Label payForm = new Label("pagar com:");
	TextField payFormField = new TextField();
	
	@SuppressWarnings("static-access")
	public Travel(Solicitante user){
		this.user = user;
		
		/*
		 * Fields IO
		 */
		
		driverField = new TextField();
		
		this.sourceField.setPromptText("lugar de origem..");
		
		this.destinyField.setPromptText("para onde ir?");
		
		this.makeValue.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				/*
				Double value = 0.0;
				while(value < 10 || value > 100){
					value = new Random().nextDouble()*10;
				}
				
				valueField.setText("value");
				travelValue = value;
				*/
				travelValue = 50.00;
				valueField.setText(travelValue.toString());
				valueField.setDisable(true);
			}
		});
		
		/*
		 * Below, put elements in gridpane structure
		 */
		
		//Driver place
		GridPane.setConstraints(driverName, 1, 1);
		GridPane.setConstraints(driverField, 2, 1);
		//Source place
		GridPane.setConstraints(source, 1, 2);
		GridPane.setConstraints(sourceField, 2, 2);
		//Destiny
		GridPane.setConstraints(destiy, 1, 3);
		GridPane.setConstraints(destinyField, 2, 3);
		//Value
		GridPane.setConstraints(value, 1, 4);
		GridPane.setConstraints(valueField, 2, 4);
		GridPane.setConstraints(makeValue, 3, 4);
		//Pay form
		GridPane.setConstraints(payForm, 1, 5);
		GridPane.setConstraints(payFormField, 2, 5);
		
		this.getChildren().addAll(driverName, driverField, source, sourceField, destiy, destinyField, 
				value, valueField, makeValue, payForm, payFormField);
		
	}

	public Motorista getDriver() {
		return driver;
	}

	public void setDriver(Motorista driverNew) {
		driver = driverNew;
		driverField.setText(driver.getNome());
		driverField.setDisable(true);
	}
	
	public void startTravel() throws SQLException{
		Lugar source = new Lugar("Viagem", this.sourceField.getText());
		Lugar destiny = new Lugar("Viagem", this.destinyField.getText());
		Viagem travel = new Viagem(this.user, this.driver, source, destiny, this.travelValue, this.payFormField.getText());
		TravelDAO.insertTravel(travel);
	}
}
