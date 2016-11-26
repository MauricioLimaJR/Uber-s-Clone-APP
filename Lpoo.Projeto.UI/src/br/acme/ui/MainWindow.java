package br.acme.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application{

	String img = "http://4.bp.blogspot.com/-gMJxkIC8Y4E/VCS8aMvFlgI/AAAAAAAAHwA/z9RNjIET9RY/s1600/Tripda.png";
	String img2 = "img.jpeg";
	public void start(Stage mainStage) {
		try {
			//Here, we do a vertical organization
			VBox firstPlace = new VBox(50); 
			firstPlace.setAlignment(Pos.CENTER); 
			
			//Title of the program
			Label rotuloDemo = new Label("Nome do Programa"); 
			rotuloDemo.setTooltip(new Tooltip("Descrição curta do programa"));
			
			//Second component in the Vbox
			//Horizontal organization
			HBox caixaHorizontal = new HBox(); 
			caixaHorizontal.setSpacing(200);
			caixaHorizontal.setAlignment(Pos.CENTER); 
			
			//Our left HBOX space
			Image imagem = new Image(img);
			ImageView imagemView = new ImageView(imagem);
			imagemView.setTranslateX(80);
			imagemView.setTranslateY(5);
			
			//Our right Hbox space, with a two elements in other VBox
			VBox rigthSideCaixaHorizontal = new VBox(100); 
			rigthSideCaixaHorizontal.setSpacing(50); 
			rigthSideCaixaHorizontal.setAlignment(Pos.CENTER); 
			
			//First element of rigthSideCaixaHorizontal
			Button btnSignIn = new Button("Sign in");
			Button btnSignUp = new Button("Sign up");
			btnSignIn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					//((Button) e.getSource()).getScene().getWindow().hide();
					TemplateWindow janela = new TemplateWindow();
					janela.start(mainStage);
				}
			});
			
			//Put elements into layouts
			rigthSideCaixaHorizontal.getChildren().addAll(btnSignIn, btnSignUp);
			caixaHorizontal.getChildren().addAll(imagemView, rigthSideCaixaHorizontal);
			firstPlace.getChildren().addAll(rotuloDemo, caixaHorizontal);
			
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			//BorderPane root = new BorderPane();
			
			Scene scene = new Scene(firstPlace,600,400);
			scene.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
