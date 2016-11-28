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

public class DriverList extends VBox {

	public static VBox userList = new VBox();

    private static TableView userTable = new TableView<>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@Override
    public DriverList(List pessoas){
	}
	public static VBox startTable(List pessoas){
		
	try{

		/*
		List pessoas = Arrays.asList(
				new Solicitante("113.544.464.10", "Seu Z�", "boa", "masc", "10/06/1985", "teste@legal.com", 345678)
				//new Solicitante("113.544.464.64", "Doca", "boa", "masc", "10/06/1985", "teste@legal.com", 345678)
				//new Solicitante("113.544.464.60", "Maria", "boa", "fem", "10/06/1985", "teste@legal.com", 345678),
				//new Solicitante("113.544.464.50", "Zanza", "boa", "fem", "10/06/1985", "teste@legal.com", 345678),
				//new Solicitante("112.545.464.10", "Seu apressado", "boa", "masc", "10/06/1985", "teste@legal.com", 345678)
				);
		*/
		
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
 
        
        if(!userList.getChildren().isEmpty()){
        	userList.getChildren().remove(0);
        }
        
        userList.getChildren().add(userTable);
        return userList;
        
    }catch(Exception e) {
		e.printStackTrace();
	}
	return userList;
	
	}
	public static void main(String[] args) {
	//	launch(args);
	}
	
	@SuppressWarnings({"rawtypes" })
	public static TableView getTable(){
		return userTable;
	}
	
}