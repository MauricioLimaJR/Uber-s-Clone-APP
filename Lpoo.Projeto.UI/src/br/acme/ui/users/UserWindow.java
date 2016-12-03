package br.acme.ui.users;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class UserWindow extends Application {
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
			
			Image timg = new Image(getClass().getResource("../files/imgT.png").toString());
			ImageView titleImg = new ImageView(timg);
			
			//Label is need to show some text
			Label nomePrograma = new Label("Nome do Programa");
			nomePrograma.setTooltip(new Tooltip("Descrição curta do programa"));
			nomePrograma.setAlignment(Pos.CENTER_LEFT);
			nomePrograma.getStyleClass().addAll("labelHeader", "lbHeader");
			
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
			
			header.getChildren().addAll(titleImg, userSettings, userLogout);
			header.setSpacing(200);
			header.setAlignment(Pos.CENTER);
			header.setId("header");
			//header.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
			/////////////////////////   Making the BODY   /////////////////////////
			
			/*
			 * LEFT SIDE
			 */
			
			//Vertical Box to put all buttons of menu
			VBox menuNav = new VBox();
			
			Button doTravel = new Button("Take a run");
			doTravel.getStyleClass().add("btnMenuNav");
			//Add function while clicking 
			
			Button showTravels = new Button("Show travels");
			showTravels.getStyleClass().add("btnMenuNav");
			//Add function while clicking 
			
			
			menuNav.getChildren().addAll(doTravel, showTravels);
			menuNav.setAlignment(Pos.TOP_CENTER);
			menuNav.getStyleClass().add("menuNav");
			//menuNav.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
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

			/////////////////////////   Making the final settings   /////////////////////////
					
			bodyHolder.getChildren().addAll(header, body, footer);
			bodyHolder.setId("mainContent");
			
			Scene scene = new Scene(bodyHolder,900,540);
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
}
