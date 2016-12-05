package br.acme.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class DialogWindow {	
	static String pass;
	
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
	
	public static String InputDialogDelete(){
		 TextInputDialog dialogoNome = new TextInputDialog();
         dialogoNome.setTitle("Deletar Conta");
         dialogoNome.setHeaderText("Você realmente quer deletar sua conta?");
         dialogoNome.setContentText("Senha:");
         dialogoNome.showAndWait().ifPresent(v -> pass = v);
         return pass;
	}
}
