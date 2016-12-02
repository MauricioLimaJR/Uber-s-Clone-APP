package br.acme.ui.users;

import java.util.Arrays;
import java.util.List;

import javax.swing.text.TabableView;

import br.acme.storage.Database;
import br.acme.ui.AccountWindow;
import br.acme.ui.elements.UserList;
import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ManageWindow extends Application {
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
			//Right Side WorkSpace
			VBox rightSideContent = new VBox();
			
			//  Here, should create a window that
			//  show a table with the objects
			//  (Solicitantes / Motoristas) 
			//  and your details
			
			rightSideContent.setAlignment(Pos.BOTTOM_LEFT);
			rightSideContent.getStyleClass().add("right_side_body");
			
			// Right - Bottom Side
			HBox acoes = new HBox();
					
			Button remover = new Button("Remover");
			remover.getStyleClass().add("removerBtn");
			remover.setAlignment(Pos.CENTER_LEFT);
			remover.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent t) {
		        	deleteUser(UserList.getTable());
		        }
		    });
			
			Button add = new Button("Adicionar");
			add.getStyleClass().add("adicionarBtn");
			add.setAlignment(Pos.CENTER_LEFT);
			add.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent t){
					addUser(UserList.getTable());
				}
			});
						
			acoes.getChildren().addAll(add, remover);
			acoes.getStyleClass().add("acoes");
			acoes.setAlignment(Pos.BOTTOM_LEFT);
			acoes.getStyleClass().add("acoes");
			rightSideContent.getChildren().addAll(acoes);
			
		//	getSelectionModel().selectedItemProperty().addListener(
		//            (observable, oldValue, newValue) -> showPersonDetails(newValue));
		//}
			
			/// Left Side
			
			VBox menuNav = new VBox();
			//Left Side
			Button adicionar = new Button("Adicionar");
			adicionar.getStyleClass().add("menuNavBtn");
			adicionar.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent t) {
		        	AccountWindow account = new AccountWindow();
		        	account.setAlignment(Pos.TOP_LEFT);
		        	loadView(account, rightSideContent, acoes);
		        	
		        }
		    });
			
			///////
			List pessoas = Arrays.asList(
					new Solicitante("113.544.464.10", "Seu Zé", "boa", "masc", "10/06/1985", "teste@legal.com", "345678"),
					new Solicitante("338.804.515-16", "Doca", "boa", "masc", "10/06/1985", "teste@legal.com", "345678")
					);
			
			//Solicitante[] pessoas = (Solicitante[]) SolicitationDB.LerBaseSolicitantes("1").buscarTodos();
			///
			
			Button aceitarCadastro = new Button("Aceitar Cadastro");
			aceitarCadastro.getStyleClass().add("menuNavBtn");
			aceitarCadastro.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent t) {
		        	/*UserList list = new UserList(pessoas);
		        	list.setAlignment(Pos.TOP_LEFT);
		        	loadView(list, rightSideContent, acoes);*/
		        	loadView(UserList.startTable(pessoas), rightSideContent, acoes);
		        			        	
		        }
		    });
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
			primaryStage.setResizable(false);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//////////////  METHODS //////////////
	
	public void loadView(Node view, VBox content, HBox base){
		clearView(content);
		content.getChildren().addAll(view,base);
	}
	
	public void clearView(VBox content){
		content.getChildren().removeAll(content.getChildren());
	}
	
	private void deleteUser(TableView table) {
	    int selectedIndex = table.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	table.getItems().remove(selectedIndex);
	    } 
	    else {
	        // Nothing selected
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Nenhuma seleção");
	        alert.setHeaderText("Nenhuma Pessoa Selecionada");
	        alert.setContentText("Por favor, selecione uma pessoa na tabela.");
	        alert.showAndWait();
	    }
	}
	
	private void addUser(TableView table){
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0){
			//Put user in default repository
			table.getItems().remove(selectedIndex);
		}
		else{
			 // Nothing selected
			 Alert alert = new Alert(AlertType.WARNING);
	         alert.setTitle("Nenhuma seleção");
	         alert.setHeaderText("Nenhuma Pessoa Selecionada");
	         alert.setContentText("Por favor, selecione uma pessoa na tabela.");
	         alert.showAndWait();
		}
	}
	
}

