package br.acme.ui.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserList extends VBox {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@Override
    public UserList(List pessoas){
		
	try{

		/*
		List pessoas = Arrays.asList(
				new Solicitante("113.544.464.10", "Seu Zé", "boa", "masc", "10/06/1985", "teste@legal.com", 345678)
				//new Solicitante("113.544.464.64", "Doca", "boa", "masc", "10/06/1985", "teste@legal.com", 345678)
				//new Solicitante("113.544.464.60", "Maria", "boa", "fem", "10/06/1985", "teste@legal.com", 345678),
				//new Solicitante("113.544.464.50", "Zanza", "boa", "fem", "10/06/1985", "teste@legal.com", 345678),
				//new Solicitante("112.545.464.10", "Seu apressado", "boa", "masc", "10/06/1985", "teste@legal.com", 345678)
				);
		*/
		
        TableView userTable = new TableView<>();
        TableColumn nameColumn = new TableColumn<>("Nome");
        TableColumn cpfColumn = new TableColumn<>("CPF");
        TableColumn emailColumn = new TableColumn<>("E-mail");
        TableColumn dateColumn = new TableColumn<>("Nascimento");
        TableColumn sexColumn = new TableColumn<>("Sexo");
        TableColumn numberColumn = new TableColumn<>("Contato");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCelular"));

        
        userTable.getColumns().addAll(nameColumn, cpfColumn, emailColumn, dateColumn, sexColumn, numberColumn);
        userTable.setItems(FXCollections.observableArrayList(pessoas));
       
        /*
        primaryStage.setScene(new Scene(userTable));
        primaryStage.setWidth(250);
        primaryStage.setHeight(300);
        primaryStage.setTitle("Tabelas no JavaFX");
        primaryStage.show();
        */
        this.getChildren().add(userTable);
        
    }catch(Exception e) {
		e.printStackTrace();
	}
	
	}
	public static void main(String[] args) {
	//	launch(args);
	}
	
	
}