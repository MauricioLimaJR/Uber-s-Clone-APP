package br.acme.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class InputException extends Exception{
	
	public InputException(String msg){
		super("Erro: "+msg);
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        alert.showAndWait();
	}
	
	public InputException(String msg, String desc){
		super("Erro: "+msg);
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        alert.setContentText(desc);
        alert.showAndWait();
	}
}
