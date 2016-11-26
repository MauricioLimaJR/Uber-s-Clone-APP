package br.acme.ui;

import java.text.ParseException;

import br.acme.exception.NullStringException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountWindow extends VBox{
	
	//public void start(Stage primaryStage) throws Exception {
	public AccountWindow(){
		try{
			//VBox userName
			HBox userName = new HBox(28);
			Label name = new Label("Name:");
			name.setAlignment(Pos.CENTER_LEFT);			
			TextField nameField = new TextField();
			nameField.setPromptText("Enter your name");
			nameField.setAlignment(Pos.CENTER_LEFT);
			userName.getChildren().addAll(name, nameField);
			userName.setAlignment(Pos.CENTER_LEFT);
			
			//VBox userCpf
			HBox userCpf = new HBox(28);
			Label cpf = new Label("CPF:");
			cpf.setAlignment(Pos.CENTER_LEFT);			
			TextField cpfField = new TextField();
			cpfField.setPromptText("Enter your CPF");
			cpfField.setAlignment(Pos.CENTER_LEFT);
			userCpf.getChildren().addAll(cpf, cpfField);
			userCpf.setAlignment(Pos.CENTER_LEFT);
			
			//VBox userEmail
			HBox userEmail = new HBox(28);
			Label email = new Label("Email:");
			email.setAlignment(Pos.CENTER_LEFT);			
			TextField emailField = new TextField();
			emailField.setPromptText("Enter your name");
			emailField.setAlignment(Pos.CENTER_LEFT);
			userEmail.getChildren().addAll(email, emailField);
			userEmail.setAlignment(Pos.CENTER_LEFT);
			
			//VBox userSex
			HBox userSex = new HBox(28);
			Label sex = new Label("Sex:");
			sex.setAlignment(Pos.CENTER_LEFT);			
			TextField sexField = new TextField();
			sexField.setPromptText("Enter your sex");
			sexField.setAlignment(Pos.CENTER_LEFT);
			userSex.getChildren().addAll(sex, sexField);
			userSex.setAlignment(Pos.CENTER_LEFT);
			
			//VBox userBirthday
			HBox userBD = new HBox(28);
			Label birthDay = new Label("BirthDay:");
			birthDay.setAlignment(Pos.CENTER_LEFT);			
			TextField bdField = new TextField();
			bdField.setPromptText("dd/mm/aaaa");
			bdField.setAlignment(Pos.CENTER_LEFT);
			userBD.getChildren().addAll(birthDay, bdField);
			userBD.setAlignment(Pos.CENTER_LEFT);

			//VBox userNumber
			HBox userNumber = new HBox(28);
			Label number = new Label("Phone:");
			number.setAlignment(Pos.CENTER_LEFT);			
			TextField numberField = new TextField();
			numberField.setPromptText("just numbers");
			numberField.setAlignment(Pos.CENTER_LEFT);
			userNumber.getChildren().addAll(number, numberField);
			userNumber.setAlignment(Pos.CENTER_LEFT);
			
			//VBox userPass
			HBox userPass = new HBox(28);
			Label password = new Label("Password:");
			password.setAlignment(Pos.CENTER_LEFT);	
			PasswordField passField = new PasswordField();
			passField.setPromptText("Enter your password");
			passField.setAlignment(Pos.CENTER_LEFT);
			userPass.getChildren().addAll(password, passField);
			userPass.setAlignment(Pos.CENTER_LEFT);
			
			//VBox userPass Confirm
			HBox userPassConfirm = new HBox(28);
			Label passwordConfirm = new Label("Confirm:");
			passwordConfirm.setAlignment(Pos.CENTER_LEFT);	
			PasswordField passFieldConfirm = new PasswordField();
			passFieldConfirm.setPromptText("Enter your password again");
			passFieldConfirm.setAlignment(Pos.CENTER_LEFT);
			userPassConfirm.getChildren().addAll(passwordConfirm, passFieldConfirm);
			userPassConfirm.setAlignment(Pos.CENTER_LEFT);
			
			Button btnSend = new Button("Send");
			btnSend.setAlignment(Pos.BOTTOM_RIGHT);
			btnSend.getStyleClass().add("accountBtn");			
			//
			btnSend.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent t) {
		        	//
		        	
		        }
		    });
			
			
			this.getChildren().addAll(userName, userCpf, userPass, userPassConfirm, userSex, userBD, userNumber, userEmail, btnSend);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void addStatus(Label label){
		this.getChildren().addAll(label);
	}
	
	public void makeAnUser(String cpf, String name, String password, String sex, String date, String email, int number) throws ParseException, NullStringException, UnableCpfExecption{
		Solicitante solicitante = new Solicitante(cpf, name, password, sex, date, email, number);
		//Add in repository
		Label m = new Label("Enviado");
		addStatus(m);
	}
	
	public void sendNewAccount(Solicitante solicitatne){
		
	}
}
