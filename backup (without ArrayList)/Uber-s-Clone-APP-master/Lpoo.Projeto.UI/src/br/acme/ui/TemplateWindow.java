package br.acme.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TemplateWindow extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//For put all elements
			//BorderPane elements = new BorderPane();
			//Hold the three main elements
			VBox bodyHolder = new VBox();
			//Then, create the header, body and footer
			HBox header = new HBox();
			
			HBox body = new HBox();
			
			HBox footer = new HBox();
			
			/////////////////////////   Making the HEADER   /////////////////////////
			
			Label nomePrograma = new Label("Nome do Programa");
			nomePrograma.setTooltip(new Tooltip("Descrição curta do programa"));
			nomePrograma.setAlignment(Pos.CENTER_LEFT);
			nomePrograma.getStyleClass().add("label_header");
			
			Button userSettings = new Button("User Profile");
			userSettings.getStyleClass().add("headerBtn");
			userSettings.setAlignment(Pos.CENTER_RIGHT);
			//Add function while clicking 
			Button userLogout = new Button("Logout");
			userLogout.getStyleClass().add("headerBtn");
			userLogout.setAlignment(Pos.CENTER_RIGHT);
			//Add function while clicking 				
			
			header.getChildren().addAll(nomePrograma, userSettings, userLogout);; 
			header.setSpacing(200);
			header.setAlignment(Pos.CENTER);
			header.setId("header");
			
			/////////////////////////   Making the BODY   /////////////////////////
			
			VBox menuNav = new VBox();
			//Left Side
			Button adicionar = new Button("Adicionar");
			adicionar.getStyleClass().add("menuNavBtn");
			//Add function while clicking 
			Button aceitarCadastro = new Button("Aceitar Cadastro");
			aceitarCadastro.getStyleClass().add("menuNavBtn");
			//Add function while clicking 
			Button listarSolicitantes = new Button("Listar Solicitantes");
			listarSolicitantes.getStyleClass().add("menuNavBtn");
			//Add function while clicking 
			Button listarMotoristas = new Button("Listar Motoristas");
			listarMotoristas.getStyleClass().add("menuNavBtn");
			//Add function while clicking 
			Button relatorio = new Button("Relatório de Viagens");
			relatorio.getStyleClass().add("menuNavBtn");
		
			//Add function while clicking 
			
			menuNav.getChildren().addAll(adicionar, aceitarCadastro, listarMotoristas, listarSolicitantes, relatorio);
			menuNav.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			menuNav.setAlignment(Pos.CENTER_LEFT);
			
			//Right Side
			VBox rightSideContent = new VBox();
			
			//  Here, should create a window that
			//  show a table with the objects
			//  (Solicitantes / Motoristas) 
			//  and your details
			
			rightSideContent.setAlignment(Pos.BOTTOM_CENTER);
			rightSideContent.getStyleClass().add("right_side_boby");
			
			HBox acoes = new HBox();
			
			Button remover = new Button("Remover");
			remover.getStyleClass().add("removerBtn");
			remover.setAlignment(Pos.CENTER_LEFT);
			//Add function while clicking 
			
			acoes.getChildren().addAll(remover);
			acoes.getStyleClass().add("acoes");
			acoes.setAlignment(Pos.BOTTOM_LEFT);
			acoes.getStyleClass().add("acoes");
			rightSideContent.getChildren().addAll(acoes);
			
			body.getChildren().addAll(menuNav, rightSideContent); 
			body.setSpacing(0);
			body.setAlignment(Pos.CENTER_LEFT);
			body.setId("body");
			
			/////////////////////////   Making the FOOTER   /////////////////////////
		
			Label criador = new Label("Desenvolvido por Maurício de Lima e Pedro Gabriel");
			criador.setAlignment(Pos.CENTER);
			
			footer.getChildren().addAll(criador);
			footer.setSpacing(100);
			footer.setAlignment(Pos.CENTER);
			footer.setId("footer");

			/////////////////////////   Making the final settings   /////////////////////////
					
			bodyHolder.getChildren().addAll(header, body, footer);
			bodyHolder.setId("mainContent");
			
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(bodyHolder,900,640);
			scene.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//////////////  METHODS //////////////
	
	
}

