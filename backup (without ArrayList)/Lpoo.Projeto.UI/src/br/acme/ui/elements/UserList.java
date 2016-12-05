package br.acme.ui.elements;

import java.util.ArrayList;
import br.acme.users.Solicitante;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class UserList extends VBox{

	public static VBox userList = new VBox();

    @SuppressWarnings("rawtypes")
	private static TableView userTable = new TableView<>();
	//@Override
  
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void initTable(){
    	try{
    		
    		TableColumn idColumn = new TableColumn<>("Id");
            TableColumn nameColumn = new TableColumn<>("Nome");
            TableColumn cpfColumn = new TableColumn<>("CPF");
            TableColumn emailColumn = new TableColumn<>("E-mail");
            TableColumn dateColumn = new TableColumn<>("Nascimento");
            TableColumn sexColumn = new TableColumn<>("Sexo");
            TableColumn numberColumn = new TableColumn<>("Contato");

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
            cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));
            numberColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCelular"));
            
            userTable.getItems().removeAll(userTable.getChildrenUnmodifiable());
            userTable.getColumns().addAll(idColumn, nameColumn, cpfColumn, emailColumn, dateColumn, sexColumn, numberColumn);
            userList.setAlignment(Pos.CENTER);
        }catch(Exception e) {
    		e.printStackTrace();
    	}	
    }
	
	@SuppressWarnings({ "unchecked" })
	public static void startTable(ArrayList<Solicitante> users){
		
	try{
		/*
		TableColumn idColumn = new TableColumn<>("Id");
        TableColumn nameColumn = new TableColumn<>("Nome");
        TableColumn cpfColumn = new TableColumn<>("CPF");
        TableColumn emailColumn = new TableColumn<>("E-mail");
        TableColumn dateColumn = new TableColumn<>("Nascimento");
        TableColumn sexColumn = new TableColumn<>("Sexo");
        TableColumn numberColumn = new TableColumn<>("Contato");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCelular"));
        
        userTable.getItems().removeAll(userTable.getChildrenUnmodifiable());
        userTable.getColumns().addAll(idColumn, nameColumn, cpfColumn, emailColumn, dateColumn, sexColumn, numberColumn);
        */
      
		userTable.getItems().removeAll(userTable.getChildrenUnmodifiable());
        userTable.setItems(FXCollections.observableArrayList(users)); 
        userList.getChildren().removeAll(userList.getChildren());
        userList.getChildren().add(userTable);
        
    }catch(Exception e) {
		e.printStackTrace();
	}	
	}
	public static void main(String[] args) {
	//	launch(args);
	}
	
	public static VBox getTablePlace(){	
		return userList;
	}
	
	@SuppressWarnings({"unchecked" })
	public static TableView<Solicitante> getTable(){
		return userTable;
	}
	
}