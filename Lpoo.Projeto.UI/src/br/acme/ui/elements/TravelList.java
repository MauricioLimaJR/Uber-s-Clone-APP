package br.acme.ui.elements;

import java.util.ArrayList;

import br.acme.location.Viagem;
import br.acme.users.Solicitante;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TravelList extends VBox {

	private Solicitante person;
	public static VBox userList = new VBox();

    @SuppressWarnings("rawtypes")
	private static TableView personTable = new TableView<>();
	
	//@Override
    public TravelList(Solicitante person){
		this.person = person;
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void initTable(){
    	
        TableColumn id = new TableColumn<>("ID");
        TableColumn user = new TableColumn<>("ID Cliente");
        TableColumn driver = new TableColumn<>("ID Motorista");
        TableColumn from = new TableColumn<>("Origem");
        TableColumn to = new TableColumn<>("Destino");
        TableColumn value = new TableColumn<>("Valor");
        TableColumn pay = new TableColumn<>("Pagamento");
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        user.setCellValueFactory(new PropertyValueFactory<>("clienteID"));
        driver.setCellValueFactory(new PropertyValueFactory<>("motoristaID"));
        from.setCellValueFactory(new PropertyValueFactory<>("source"));
        to.setCellValueFactory(new PropertyValueFactory<>("destiny"));
        value.setCellValueFactory(new PropertyValueFactory<>("valorViagem"));
        pay.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));

        
        personTable.getColumns().addAll(id,user,driver,from,to,value,pay);
        		
    }
	
	@SuppressWarnings({ "unchecked" })
	public static VBox startTable(ArrayList<Viagem> list){
		
	try{

        
        /*
         * Load the user in DataBase
         */

       
        personTable.getItems().removeAll(personTable.getChildrenUnmodifiable());
        personTable.setItems(FXCollections.observableArrayList(list));
        userList.getChildren().removeAll(userList.getChildren());
        userList.getChildren().add(personTable);
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
		return personTable;
	}
	
	public Solicitante getPerson(){
		return this.person;
	}
	
}