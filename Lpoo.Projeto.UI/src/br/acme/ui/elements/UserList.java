package br.acme.ui.elements;

import java.util.ArrayList;
import br.acme.users.Solicitante;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class UserList extends VBox {

	public static VBox userList = new VBox();

    @SuppressWarnings("rawtypes")
	private static TableView userTable = new TableView<>();
	//@Override
  
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static VBox startTable(ArrayList<Solicitante> users){
		
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

        
        userTable.getColumns().addAll(idColumn, nameColumn, cpfColumn, emailColumn, dateColumn, sexColumn, numberColumn);
        
      
        
        userTable.setItems(FXCollections.observableArrayList(users));
 
        
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