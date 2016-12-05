package br.acme.ui.users;

import br.acme.exception.InputException;
import br.acme.exception.RepositorioException;
import br.acme.storage.Repositorio;
import br.acme.ui.MainWindow;
import br.acme.ui.elements.DriverEdit;
import br.acme.ui.elements.TravelList;
import br.acme.ui.elements.UserEdit;
import br.acme.ui.elements.UserList;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class DriverWindow extends Application {
	
	private Motorista driver;
	DriverEdit edit;
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			edit = new DriverEdit(driver);
			
			//For put all elements
			//BorderPane elements = new BorderPane();
			//Hold the three main elements
			VBox bodyHolder = new VBox();
			//Then, create the header, body and footer
			HBox header = new HBox();
			
			HBox body = new HBox();
			
			HBox footer = new HBox();
			
			/////////////////////////   Making the HEADER   /////////////////////////
			
			Image timg = new Image(getClass().getResource("../files/logo.png").toString());
			ImageView titleImg = new ImageView(timg);
			titleImg.getStyleClass().add("titleImg");
			
			//Label is need to show some text
			Label driverName = new Label(driver.getNome());
			//driverName.setTooltip(new Tooltip(""));
			driverName.setAlignment(Pos.CENTER_LEFT);
			driverName.getStyleClass().addAll("labelHeader", "lbHeader");
			
			//Image user button
			Button userImg = new Button("change");
			userImg.getStyleClass().addAll("btnImg", "btnHeader");
			//userImg.setAlignment(Pos.CENTER);
			//Add function while clicking 
			
			//Profile button
			Button userSettings = new Button("Profile");
			userSettings.getStyleClass().addAll("btnPf", "btnHeader");
			//userSettings.setAlignment(Pos.CENTER);
			//Add function while clicking 
			
			//Logout button
			Button userLogout = new Button("Logout");
			userLogout.getStyleClass().addAll("btnLg", "btnHeader");
			//userLogout.setAlignment(Pos.CENTER_RIGHT);
			//Add function while clicking 				
			
			header.getChildren().addAll(titleImg, driverName, userSettings, userLogout);
			//header.setSpacing(0);
			header.setAlignment(Pos.CENTER_LEFT);
			header.setId("header");
			//header.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
			/////////////////////////   Making the BODY   /////////////////////////
			
			/*
			 * RIGTH SIDE
			 */
			
			VBox workSpace = new VBox();
			
			/*
			 * Here, should be a space to holder
			 * the work windows
			 */
						
			workSpace.setAlignment(Pos.BOTTOM_CENTER);
			workSpace.getStyleClass().add("right_side_boby");
			
			
			/*
			 * LEFT SIDE
			 */
			
			//Vertical Box to put all buttons of menu
			VBox menuNav = new VBox();
			
			Button showTravels = new Button("Show travels");
			showTravels.getStyleClass().add("btnMenuNav");
			
			showTravels.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						loadView(TravelList.startTable(Repositorio.rpViagens.buscarPorId(driver.getId())), workSpace);
					} 
					catch (RepositorioException e) {
						e.printStackTrace();
						 Alert alert = new Alert(AlertType.WARNING);
				         alert.setTitle("Repositório vazio");
				         alert.setHeaderText(e.getMessage());
				         alert.setContentText("Nehuma viagem realizada.");
				         alert.showAndWait();
					}
				}
			});
			
			
			menuNav.getChildren().add(showTravels);
			menuNav.setAlignment(Pos.TOP_CENTER);
			menuNav.getStyleClass().add("menuNav");
			//menuNav.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
			/*
			 * Adding elements
			 */
			
			body.getChildren().addAll(menuNav, workSpace); 
			body.setSpacing(0);
			body.setAlignment(Pos.CENTER_LEFT);
			body.setId("body");
			//body.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
			/////////////////////////   Making the FOOTER   /////////////////////////
		
			Label by = new Label("© Maurício de Lima & Pedro Gabriel");
			
			footer.getChildren().addAll(by);
			footer.setSpacing(0);
			footer.setAlignment(Pos.BOTTOM_CENTER);
			footer.setId("footer");
			//footer.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			////////////////////////HEADER BUTTONS 
			HBox buttons = new HBox();
			Button save = new Button("Salvar");
			save.getStyleClass().add("btnMenuNav");
			save.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					try {
						edit.getFields();
					} catch (InputException e) {
						e.printStackTrace();
					}
				}
			});
			
			Button back = new Button("Cancelar");
			back.getStyleClass().addAll("btnMenuNav", "btnDelete");
			back.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					clearView(workSpace);
				}
			});
			buttons.getChildren().addAll(save,back);
			
			userSettings.setOnAction( new EventHandler<ActionEvent>() {
			
				@Override
				public void handle(ActionEvent event) {
					
					edit.setAlignment(Pos.CENTER);
					clearView(workSpace);
					workSpace.getChildren().addAll(edit, buttons);
				}
			});
			
			userLogout.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					MainWindow mainMenu = new MainWindow();
					mainMenu.setOldEmail(driver.getEmail());
					mainMenu.start(primaryStage);
					
				}
			});

			/////////////////////////   Making the final settings   /////////////////////////
					
			bodyHolder.getChildren().addAll(header, body, footer);
			bodyHolder.setId("mainContent");
			
			Scene scene = new Scene(bodyHolder,900,540);
			scene.getStylesheets().add(getClass().getResource("../files/mainwindow.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setUser(Motorista driver){
		this.driver = driver;
	}
	
	public void clearView(VBox content){
		content.getChildren().removeAll(content.getChildren());
	}
	
	public void loadView(Node view, VBox content){
		clearView(content);
		content.getChildren().addAll(view);
	}
}
