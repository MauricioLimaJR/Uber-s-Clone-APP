package br.acme.ui;

import br.acme.exception.RepositorioException;
import br.acme.storage.Database;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.ui.users.ManageWindow;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application{

	String img = "http://4.bp.blogspot.com/-gMJxkIC8Y4E/VCS8aMvFlgI/AAAAAAAAHwA/z9RNjIET9RY/s1600/Tripda.png";
	String img2 = "img.jpeg";
	public String loginAs;

	AccountWindow newAccount = new AccountWindow();
	
	public static IRepositorio<Solicitante> userList = new RepositorioSolicitante();
	public static IRepositorio<Motorista> driverList = new RepositorioMotorista();
	
	@SuppressWarnings("unchecked")
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
			leftSideVerticalBox.setSpacing(100);
			leftSideVerticalBox.getStyleClass().add("leftSideVerticalBox");
			
			//Image image = new Image(getClass().getResource("../Lpoo.Projeto.UI/extra files/images.jpg").toExternalForm());
			ImageView imagemView = new ImageView(getClass().getResource("files/imgInit.jpg").toString());
			imagemView.setTranslateX(80);
			imagemView.setTranslateY(5);
			
			leftSideVerticalBox.getChildren().addAll(imagemView);
			
			//Our right Vbox space, with a two elements in other VBox
			VBox rigthSideVerticalBox = new VBox(); 
			rigthSideVerticalBox.setSpacing(9); 
			rigthSideVerticalBox.setAlignment(Pos.CENTER_LEFT); 
			
			//Elements of rightSideVerticalBox
			
			//////////   LoginAs Field   /////////
			HBox loginAsField = new HBox(6);
			Label loginAsCamp = new Label("Login As:");
			String[] userModel = new String[]{"select", "Gerente", "Motorista", "Solicitante"};
			@SuppressWarnings("rawtypes")
			ChoiceBox logarComo = new ChoiceBox(FXCollections.observableArrayList(userModel));
			
			logarComo.getSelectionModel().selectedIndexProperty().addListener(
					(ObservableValue<? extends Number> ov,
							Number oldValue, Number newValue) -> {
								loginAs = userModel[newValue.intValue()];
							}
					);
			logarComo.getSelectionModel().select(0);
			logarComo.setTooltip(new Tooltip("Do login as.."));
			
			loginAsField.getChildren().addAll(loginAsCamp, logarComo);
			

			//////////   userEmail Field   /////////
			
			HBox userEmail = new HBox(28);
			Label email = new Label("Email:");
			email.setAlignment(Pos.CENTER_LEFT);
			TextField emailField = new TextField();
			emailField.setPromptText("Enter your email");
			emailField.setAlignment(Pos.CENTER_LEFT);
			userEmail.getChildren().addAll(email, emailField);
			userEmail.setAlignment(Pos.CENTER_LEFT);
			
			//////////   userPass Field   /////////
			
			HBox userPass = new HBox(5);
			Label password = new Label("Password:");
			password.setAlignment(Pos.CENTER_LEFT);
			TextField passField = new TextField();
			passField.setAlignment(Pos.CENTER_LEFT);
			passField.setPromptText("Enter your password");
			userPass.getChildren().addAll(password, passField);
			userPass.setAlignment(Pos.CENTER_LEFT);
			
			Button btnSignIn = new Button("Sign in");
			btnSignIn.setAlignment(Pos.CENTER_LEFT);
			btnSignIn.getStyleClass().add("mainBtn");
			
			btnSignIn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//((Button) e.getSource()).getScene().getWindow().hide();
				try {
					if(doLogin(loginAs, emailField.getText(), passField.getText())){

						ManageWindow adm = new ManageWindow();
						adm.start(mainStage);
					}
				} catch (RepositorioException e1) {
					e1.printStackTrace();
				}
			}
			});
	
			
			Button btnSignUp = new Button("Sign up");
			btnSignUp.setAlignment(Pos.CENTER_LEFT);
			btnSignUp.getStyleClass().add("mainBtn");			
			//Clear the left side to do a new account
			btnSignUp.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent t) {
		        	leftSideVerticalBox.getChildren().remove(0);
		        	//newAccount.setAlignment(Pos.CENTER_RIGHT);
		        	leftSideVerticalBox.getChildren().add(newAccount);
		        	
		        }
		    });
				
			
			//Put elements into layouts
			rigthSideVerticalBox.getChildren().addAll(loginAsField, userEmail, userPass, btnSignIn, btnSignUp);
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
	
	public Boolean doLogin(String loginAs, String email, String pass) throws RepositorioException{
		if(loginAs.equals("select") || loginAs==null){
			throw new RepositorioException("LoginAs is wrong");
		}
		else if(loginAs.equals("Solicitante")){
			userList = Database.readDataBase("DataBase/Solicitantes.txt");
			
			for(Solicitante user : userList.buscarTodos()){
				if(user.getEmail().equals(email) && user.getSenha().equals(pass)){
					System.out.println("logando");
					return true;
				}
			}
		}else if(loginAs.equals("Motorista")){
			driverList = Database.readDataBase("DataBase/Motoristas.txt");
			
			for(Motorista driver : driverList.buscarTodos()){
				if(driver.getEmail().equals(email) && driver.getSenha().equals(pass)){
					System.out.println("logando");
					return true;
				}
			}
		}
		throw new RepositorioException("Usuário não existente!");
	}
	
}



