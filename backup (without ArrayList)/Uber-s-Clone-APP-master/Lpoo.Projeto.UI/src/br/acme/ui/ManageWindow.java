package br.acme.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ManageWindow extends TemplateWindow {

	@Override
	public void start(Stage primaryStage) {
		try {
			Stage teste = new Stage();
			TemplateWindow janela = new TemplateWindow();
			janela.start(teste);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
