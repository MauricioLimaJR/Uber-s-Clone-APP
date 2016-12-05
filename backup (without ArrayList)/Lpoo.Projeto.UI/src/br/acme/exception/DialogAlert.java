package br.acme.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogAlert {

	public static void show(String msg){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        alert.showAndWait();
	}
	
	public static void show(String msg, String desc){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText(msg);
        alert.setContentText(desc);
        alert.showAndWait();
	}
}
