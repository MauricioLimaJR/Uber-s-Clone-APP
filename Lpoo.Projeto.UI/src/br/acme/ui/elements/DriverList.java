package br.acme.ui.elements;

import java.util.ArrayList;

import br.acme.users.Motorista;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class DriverList extends VBox{

	public static VBox driverList = new VBox();

    @SuppressWarnings("rawtypes")
	private static TableView driverTable = new TableView<>();
	//@Override
  
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void initTable(){
    	try{
    		
    		TableColumn idColumn = new TableColumn<>("Id");
    		TableColumn statusColumn = new TableColumn<>("Status");
            TableColumn nameColumn = new TableColumn<>("Nome");
            TableColumn cpfColumn = new TableColumn<>("CPF");
            TableColumn emailColumn = new TableColumn<>("E-mail");
            TableColumn dateColumn = new TableColumn<>("Nascimento");
            TableColumn sexColumn = new TableColumn<>("Sexo");
            TableColumn numberColumn = new TableColumn<>("Contato");

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
            cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
            sexColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));
            numberColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCelular"));
            
            driverTable.getItems().removeAll(driverTable.getChildrenUnmodifiable());
            driverTable.getColumns().addAll(idColumn, statusColumn, nameColumn, cpfColumn, emailColumn, dateColumn, sexColumn, numberColumn);
            driverList.setAlignment(Pos.CENTER);
        }catch(Exception e) {
    		e.printStackTrace();
    	}	
    }
	
	@SuppressWarnings({ "unchecked" })
	public static void startTable(ArrayList<Motorista> drivers){
		
	try{
      
		driverTable.getItems().removeAll(driverTable.getChildrenUnmodifiable());
        driverTable.setItems(FXCollections.observableArrayList(drivers)); 
        driverList.getChildren().removeAll(driverList.getChildren());
        driverList.getChildren().add(driverTable);
        
    }catch(Exception e) {
		e.printStackTrace();
	}	
	}
	public static void main(String[] args) {
	//	launch(args);
	}
	
	public static VBox getTablePlace(){	
		return driverList;
	}
	
	@SuppressWarnings({"unchecked" })
	public static TableView<Motorista> getTable(){
		return driverTable;
	}
	
}