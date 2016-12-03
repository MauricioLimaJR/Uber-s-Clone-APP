package br.acme.ui;

import java.text.ParseException;
import java.util.ArrayList;

import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.storage.Database;
import br.acme.storage.IRepositorio;
import br.acme.storage.IRepositorioSolicitante;
import br.acme.storage.RepositorioSolicitante;
import br.acme.ui.elements.UserList;
import br.acme.storage.Database;
import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AccountWindow extends GridPane{
	
	private ArrayList<TextField> dataOld = new ArrayList<TextField>();
	private ArrayList<TextField> dataFields = new ArrayList<TextField>();
	
	String sexField = null;
	int bdLastPos = 0;
	int bdPos=0;
	
	private static IRepositorio<Solicitante> userList = new RepositorioSolicitante();
	
	//public void start(Stage primaryStage) throws Exception {
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			Label cpf = new Label("CPF:");
			cpf.setAlignment(Pos.CENTER_LEFT);			
			TextField cpfField = new TextField();
			cpfField.setPromptText("Enter your CPF");
			
			cpfField.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					String cpf = cpfField.getText();
					System.out.println(cpf);
					int local = cpf.length();
					if( local == 3 || local == 7){
						cpfField.appendText(".");
					}else if (local == 11) {
						cpfField.appendText("-");
					}
				}
			});
			
			cpfField.setAlignment(Pos.CENTER_LEFT);
			
			//VBox userEmail
			Label email = new Label("Email:");
			email.setAlignment(Pos.CENTER_LEFT);			
			TextField emailField = new TextField();
			emailField.setPromptText("Enter your name");
			emailField.setAlignment(Pos.CENTER_LEFT);
			
			
			//UserSex
			Label sex = new Label("Sex:");
			sex.setAlignment(Pos.CENTER_LEFT);
			String[] opc = {"feminino", "masculino"};
			ChoiceBox sexChoice = new ChoiceBox(FXCollections.observableArrayList(opc));
			sexChoice.getSelectionModel().selectedIndexProperty().addListener(
					(ObservableValue<? extends Number> ov,
							Number oldValue, Number newValue) -> {
								sexField = opc[newValue.intValue()];
								System.out.println(opc[newValue.intValue()]);
							}
					);
			sexChoice.getSelectionModel().select(0);
			sexChoice.setTooltip(new Tooltip("Your sex"));
			
			//VBox userBirthday
			Label birthDay = new Label("BirthDay:");
			birthDay.setAlignment(Pos.CENTER_LEFT);			
			TextField bdField = new TextField();
			bdField.setPromptText("dd/mm/aaaa");
			bdField.setAlignment(Pos.CENTER_LEFT);
			
			bdField.setOnKeyReleased( new EventHandler<KeyEvent>() {
				public void handle(KeyEvent event){
					String bd = bdField.getText();
					System.out.println(bd);
					bdPos = bd.length();
					if(bdPos > bdLastPos){
						if( bdPos == 2 || bdPos == 5){
							bdField.appendText("/");
						}else if(bdPos == 10){
							//throw 
							System.out.println("Errado");
						}
					}
					bdLastPos = bdPos;
				}
			});

			//VBox userNumber
			Label number = new Label("Phone:");
			number.setAlignment(Pos.CENTER_LEFT);			
			TextField numberField = new TextField();
			numberField.setPromptText("(xx) xxxxx xxxx");
			numberField.setAlignment(Pos.CENTER_LEFT);
			
			setOnKeyReleased( new EventHandler<KeyEvent>() {
				public void handle(KeyEvent event){
					String number = numberField.getText();
					System.out.println(number);
					int position = number.length();
					if( position==0){
						numberField.appendText("(");
					}else if(position==3){
						numberField.appendText(")");						
					}else if(position==4){
						numberField.appendText(" ");
					}
				}
			});
			
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
			
			btnSend.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent t) {
		        	//
		        	
						try {
							makeAnUser(cpfField.getText(), nameField.getText(), passField.getText(),
									sexField, bdField.getText(), emailField.getText(),
									numberField.getText());
						} catch (ParseException | NullStringException | UnableCpfExecption e) {
							
							e.printStackTrace();
							printError(e.getMessage());
							//Label msg = new Label(e.getMessage());
							//msg.setTextFill(Color.RED);
							//addStatus(msg);
						} catch (StringIndexOutOfBoundsException  e) {
							
							printError("Campo muito curto");
						}
						
				
		        }
		    });
			
			/*
			 * Adding the elements to a column and a raw
			 */
			
			//this.getColumnConstraints().add(new ColumnConstraints(100));
			//this.getColumnConstraints().add(new ColumnConstraints(100));
			//USERNAME
			GridPane.setConstraints(name, 1, 1 );
			GridPane.setConstraints(nameField, 2, 1 );
			
			//USERCPF
			GridPane.setConstraints(cpf, 1, 2 );
			GridPane.setConstraints(cpfField, 2, 2 );
			
			//USERBIRTHDAY
			GridPane.setConstraints(birthDay, 1, 3 );
			GridPane.setConstraints(bdField, 2, 3 );
			
			//USERSEX
			GridPane.setConstraints(sex, 1, 4 );
			GridPane.setConstraints(sexChoice, 2, 4 );
			
			//USERNUMBER
			GridPane.setConstraints(number, 1, 5 );
			GridPane.setConstraints(numberField, 2, 5 );
			
			//USEREMAIL
			GridPane.setConstraints(email, 1, 6 );
			GridPane.setConstraints(emailField, 2, 6 );
			
			//USERPASS
			GridPane.setConstraints(password, 1, 7 );
			GridPane.setConstraints(passField, 2, 7 );
		
			//USERPASSCONFIRM
			GridPane.setConstraints(passwordConfirm, 1, 8 );
			GridPane.setConstraints(passFieldConfirm, 2, 8 );
			
			//BUTTON SEND
			GridPane.setConstraints(btnSend, 2, 9);

			this.getChildren().addAll(name, nameField, cpf, cpfField, birthDay, bdField, sex, sexChoice,
					number, numberField, email, emailField, password, passField, passwordConfirm, passFieldConfirm, btnSend);			
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void backup(TextField[] data){
		for(TextField field : data){
			dataOld.add(field);
		}
		
	}
	
	public void addStatus(Label label){
		//Delete preview label
		this.getChildren().remove(label);
		//Add the new label
		this.getChildren().addAll(label);
	}
	
	public void makeAnUser(String cpf, String name, String password, String sex, String date, String email, String number) throws ParseException, NullStringException, UnableCpfExecption {
		Solicitante solicitante = null;
		//try {
			solicitante = new Solicitante(cpf, name, password, sex, date, email, number);
			sendNewAccount(solicitante);
			Label m = new Label("Enviado");
			addStatus(m);
			
	//	} catch (ParseException | NullStringException | UnableCpfExecption e) {
		//	e.printStackTrace();
		//	Label error = new Label(e.getMessage());
		//	addStatus(error);
		//}
	}
	
	public void sendNewAccount(Solicitante solicitante){
		try {
			//Save in a local repository
			userList.adicionar(solicitante);
			//After, save in an database
			Database.saveStatus(userList, "DataBase/Solicitantes.txt");
		} catch (RepositorioException e) {
			e.printStackTrace();
			printError(e.getMessage());
		}
	}
	
	public void printError(String error){
		 Alert alert = new Alert(AlertType.WARNING);
         alert.setTitle("Erro");
         alert.setHeaderText(error);
        // alert.setContentText("Nehuma viagem realizada.");
         alert.showAndWait();
	}
}
 