package br.acme.ui.elements;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import application.MaskTextField;
import br.acme.database.MotoristaDAO;
import br.acme.exception.DialogAlert;
import br.acme.exception.InputException;
import br.acme.exception.NullStringException;
import br.acme.exception.UnableCpfExecption;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class DriverEdit extends GridPane{
	
	private ArrayList<TextField> dataOld = new ArrayList<TextField>();
	
	private  Motorista driver;
	String sexField = null;
	int bdLastPos = 0;
	int bdPos=0;
	
	//UserName
	Label name = new Label("Name:");			
	MaskTextField nameField;
	//VBox userCpf
	Label cpf = new Label("CPF:");		
	MaskTextField cpfField;
	//UserEmail
	Label email = new Label("Email:");	
	MaskTextField emailField;
	//UserSex
	Label sex = new Label("Sex:");
	
	@SuppressWarnings("rawtypes")
	ChoiceBox sexChoice;
	//VBox userBirthday
	Label birthDay = new Label("BirthDay:");		
	MaskTextField bdField;
	//VBox userNumber
	Label number = new Label("Phone:");	
	MaskTextField numberField;
	// Old userPass
	Label oldPass = new Label("Old Password:");
	PasswordField oldPassField;	
	//New userPass
	Label password = new Label("New Password:");
	PasswordField passField;	
	//UserPass Confirm
	Label passwordConfirm = new Label("Confirm:");	
	PasswordField passFieldConfirm;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DriverEdit(Motorista driverEdit){
		try{
			
			this.driver = driverEdit;
			String[] opc = {driver.getSexo(), "feminino", "masculino"};
			
			//UserName		
			nameField = new MaskTextField(driver.getNome());
			nameField.setMask("*!");
			nameField.setPromptText("Enter your name");
			nameField.setAlignment(Pos.CENTER_LEFT);
			
			
			//VBox userCpf	
			cpfField = new MaskTextField(driver.getCpf());
			cpfField.setMask("NNN.NNN.NNN-NN");
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
			
			//UserEmail	
			emailField = new MaskTextField(driver.getEmail());
			emailField.setMask("L!@L!.L!.L!");
			emailField.setPromptText("Enter your name");
			
			
			//UserSex
			sexChoice = new ChoiceBox(FXCollections.observableArrayList(opc));
			sexChoice.getSelectionModel().selectedIndexProperty().addListener(
					(ObservableValue<? extends Number> ov,
							Number oldValue, Number newValue) -> {
								sexField = opc[newValue.intValue()];
								System.out.println(opc[newValue.intValue()]);
							}
					);
			sexChoice.getSelectionModel().select(0);
			
			//VBox userBirthday
			bdField = new MaskTextField(driver.getDataNascimentoString());
			bdField.setMask("NN/NN/NNNN");
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

			//UserNumber
			numberField = new MaskTextField(driver.getNumeroCelular());
			numberField.setMask("NN*NNNNNNNNN");
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
			
			// Old userPass
			oldPassField = new PasswordField();
			oldPassField.setText(driver.getSenha());
			oldPassField.setPromptText("Enter your password");
			
			//New userPass
			passField = new PasswordField();
			passField.setPromptText("Enter your password");
			
			//UserPass Confirm
			passFieldConfirm = new PasswordField();
			passFieldConfirm.setPromptText("Enter your password again");
			
			
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
			
			//OLD USERPASS
			GridPane.setConstraints(oldPass, 1, 7 );
			GridPane.setConstraints(oldPassField, 2, 7 );
			
			//NEW USERPASS
			GridPane.setConstraints(password, 1, 8 );
			GridPane.setConstraints(passField, 2, 8 );
		
			//USERPASSCONFIRM
			GridPane.setConstraints(passwordConfirm, 1, 9 );
			GridPane.setConstraints(passFieldConfirm, 2, 9 );
			

			this.getChildren().addAll(name, nameField, cpf, cpfField, birthDay, bdField, sex, sexChoice, number, numberField,
					email, emailField, oldPass, oldPassField, password, passField, passwordConfirm, passFieldConfirm);			
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void backup(TextField[] data){
		for(TextField field : data){
			dataOld.add(field);
		}
		
	}
	
	@SuppressWarnings("unused")
	private void setUser(Motorista obj){
		driver = obj;
	}
	
	public void addStatus(Label label){
		//Delete preview label
		this.getChildren().remove(label);
		//Add the new label
		this.getChildren().addAll(label);
	}
	
	public String getPassword() throws InputException{
		/*if(passField.getText() == null && passFieldConfirm.getText() == null){
			return oldPassField.getText();
		}else if(passField.getText() != null && passFieldConfirm.getText() != null 
				&& passField.getText().equals(passFieldConfirm.getText())){
			return passField.getText();
		}else if(passField.getText() != null && passFieldConfirm.getText() != null){
			throw new InputException("Campo de senha incorreto.");
		}
		*/
		return oldPassField.getText();
	}
	
	public void getFields() throws InputException{	
		try {
			String pass = getPassword();
			Solicitante user = new Solicitante(cpfField.getText(), nameField.getText(), pass,
					sexField, bdField.getText(), emailField.getText(), numberField.getText());
			Motorista updateDriver = new Motorista(user);
			updateDriver.setId(driver.getId());
			
			if(MotoristaDAO.updateDriver(updateDriver)){
			DialogAlert.show("Sucesso", "Alterações enviadas.");
			}else DialogAlert.show("Falha", "Alterações não enviadas.");
			
		} catch ( ParseException | NullStringException | UnableCpfExecption | SQLException | InputException e) {
			e.printStackTrace();
			throw new InputException(e.getMessage());
		}
	}

}
 