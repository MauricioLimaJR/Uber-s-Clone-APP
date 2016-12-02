package br.acme.ui;

import br.acme.exception.RepositorioException;
import br.acme.storage.Database;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.ui.users.DriverWindow;
import br.acme.ui.users.ManageWindow;
import br.acme.ui.users.UserWindow;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import br.acme.users.Usuario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application{

	String img = "http://4.bp.blogspot.com/-gMJxkIC8Y4E/VCS8aMvFlgI/AAAAAAAAHwA/z9RNjIET9RY/s1600/Tripda.png";
	String img2 = "img.jpeg";
	public String loginAs;

	AccountWindow newAccount = new AccountWindow();
	
	ManageWindow adm = new ManageWindow();
	UserWindow user = new UserWindow();
	DriverWindow driver = new DriverWindow();
	
	public static IRepositorio<Solicitante> userList = new RepositorioSolicitante();
	public static IRepositorio<Motorista> driverList = new RepositorioMotorista();
	
	public void start(Stage mainStage) {
		try {
			//Here, we do a vertical organization
			VBox firstPlace = new VBox(50); 
			firstPlace.setAlignment(Pos.CENTER);
			firstPlace.getStyleClass().add("mainBody");
			
			//Title of the program
			HBox titleText = new HBox();
			Label titleLabel = new Label("Nome do Programa"); 
			titleLabel.setTooltip(new Tooltip("Descrição curta do programa"));
			titleLabel.setAlignment(Pos.BOTTOM_CENTER);
			titleText.getChildren().add(titleLabel);
			titleText.getStyleClass().add("titleText");
			titleText.setAlignment(Pos.TOP_CENTER);
			
			//Second component in the Vbox
			//Horizontal organization
			HBox caixaHorizontal = new HBox(); 
			caixaHorizontal.setSpacing(100);
			caixaHorizontal.setAlignment(Pos.CENTER_LEFT);
			caixaHorizontal.getStyleClass().add("horizontalBox");
			
			//Our left HBOX space
			VBox leftSideVerticalBox = new VBox();
			leftSideVerticalBox.alignmentProperty();
			leftSideVerticalBox.setAlignment(Pos.CENTER_LEFT);
			leftSideVerticalBox.setSpacing(10);
			leftSideVerticalBox.getStyleClass().add("leftSideVerticalBox");
			
			//Image image = new Image(getClass().getResource("../Lpoo.Projeto.UI/extra files/images.jpg").toExternalForm());
			ImageView imagemView = new ImageView(getClass().getResource("files/imgT.png").toString());
			imagemView.setTranslateX(80);
			imagemView.setTranslateY(5);
			
			//CANCEL BUTTON
			Button cancel = new Button("Cancel");
			cancel.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t){
					leftSideVerticalBox.getChildren().remove(0, 2);
					leftSideVerticalBox.getChildren().add(imagemView);
				}
			});
			
			leftSideVerticalBox.getChildren().add(imagemView);
			
			//Our right Vbox space, with a two elements in other VBox
			VBox rigthSideVerticalBox = new VBox(); 
			rigthSideVerticalBox.setSpacing(9); 
			rigthSideVerticalBox.setAlignment(Pos.CENTER_LEFT); 
			
			//Elements of rightSideVerticalBox
			
			GridPane righSideGP = new GridPane();

			//////////   userEmail Field   /////////
			
			Label email = new Label("Email:");
			email.setAlignment(Pos.CENTER_LEFT);
			TextField emailField = new TextField();
			emailField.setPromptText("Enter your email");
			emailField.setAlignment(Pos.CENTER_LEFT);
			
			//////////   userPass Field   /////////

			//ERROR LABEL
			Label error = new Label();
			error.setAlignment(Pos.CENTER);
			
			Label password = new Label("Password:");
			password.setAlignment(Pos.CENTER_LEFT);
			TextField passField = new TextField();
			passField.setAlignment(Pos.CENTER_LEFT);
			passField.setPromptText("Enter your password");
			
			Button btnSignIn = new Button("Sign in");
			btnSignIn.setAlignment(Pos.CENTER_LEFT);
			btnSignIn.getStyleClass().add("mainBtn");
			
			btnSignIn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//((Button) e.getSource()).getScene().getWindow().hide();
				try {
					Usuario person = doLogin(emailField.getText(), passField.getText());
					
					if(person.getClass() == Solicitante.class){
						user.start(mainStage);
					}
					else if(person.getClass() == Motorista.class){
						driver.start(mainStage);
					}
					else if(person.getClass() == Gerente.class){
						adm.start(mainStage);
					}
				} catch (RepositorioException e1) {
					e1.printStackTrace();
					error.setText(e1.toString());
				}
			}
			});
			
			Button btnSignUp = new Button("Sign up");
			btnSignUp.setAlignment(Pos.CENTER_RIGHT);
			btnSignUp.getStyleClass().add("mainBtn");			
			//Clear the left side to do a new account
			btnSignUp.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent t) {
		        	leftSideVerticalBox.getChildren().remove(0);
		        	//newAccount.setAlignment(Pos.CENTER_RIGHT);
		        	leftSideVerticalBox.getChildren().addAll(newAccount, cancel);
		        	
		        }
		    });
			
			//Put elements into layouts			
			//EMAIL FIELD
			GridPane.setConstraints(email, 1, 1);
			GridPane.setConstraints(emailField, 2, 1);			
			//USERPASS
			GridPane.setConstraints(password, 1, 2);
			GridPane.setConstraints(passField, 2, 2);
			//BUTTONS
			GridPane.setConstraints(btnSignIn, 2, 3);
			GridPane.setConstraints(btnSignUp, 2, 4);
			//LABEL
			GridPane.setConstraints(error, 2, 5);
			
			righSideGP.getChildren().addAll(email, emailField, password,
					passField, btnSignIn, btnSignUp);
			
			
			rigthSideVerticalBox.getChildren().add(righSideGP);
			caixaHorizontal.getChildren().addAll(leftSideVerticalBox, rigthSideVerticalBox);
			firstPlace.getChildren().addAll(titleText, caixaHorizontal);
		
			Scene scene = new Scene(firstPlace,620,400);
			scene.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			mainStage.setTitle("Nome do Progama");
			
			Image icon = new Image(getClass().getResource("title.png").toExternalForm());
			mainStage.getIcons().add(icon);  
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
	
	public Usuario doLogin(String email, String pass) throws RepositorioException{
	
		userList = Database.readDataBase("DataBase/Solicitantes.txt");
		
		for(Solicitante user : userList.buscarTodos()){
			if(user.getEmail().equals(email) && user.getSenha().equals(pass)){
				//@PrintString
				//System.out.println("logando");
				return user;
			}
		}
	
		driverList = Database.readDataBase("DataBase/Motoristas.txt");
		
		for(Motorista driver : driverList.buscarTodos()){
			if(driver.getEmail().equals(email) && driver.getSenha().equals(pass)){
				//@PrintString
				//System.out.println("logando");
				return driver;
			}
		}
		
		
	throw new RepositorioException("Usuário não existente!");
}
	
}



