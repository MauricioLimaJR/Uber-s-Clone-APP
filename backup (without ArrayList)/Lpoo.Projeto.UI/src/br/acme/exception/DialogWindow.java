package br.acme.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class DialogWindow {	
	static String pass;
	static Boolean bool=false;
	
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
	
	public static boolean ConfirmDialog(String title, String content){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		ButtonType yes = new ButtonType("Sim");
		ButtonType no = new ButtonType("Não");
		
		alert.setTitle(title);
		alert.setContentText(content);	
		alert.getButtonTypes().addAll(no,yes);
		alert.showAndWait().ifPresent( btn -> {
				if(btn == yes) bool=true;
				else bool=false;
			}
		);
		
		return bool;
	}
}
