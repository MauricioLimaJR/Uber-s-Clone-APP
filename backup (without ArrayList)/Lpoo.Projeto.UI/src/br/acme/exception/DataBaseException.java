package br.acme.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class DataBaseException extends Exception{

	public DataBaseException(String msg){
		super("Erro: "+msg);
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        alert.showAndWait();
	}
	
	public DataBaseException(String msg, String desc){
		super("Erro: "+msg);
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        alert.setContentText(desc);
        alert.showAndWait();
	}
}
