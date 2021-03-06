package br.acme.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class UserInterfaceException extends Exception {
	public UserInterfaceException(String msg){
		super("Erro: "+msg);
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        alert.showAndWait();
	}
}
