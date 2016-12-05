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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static VBox startTable(ArrayList<Viagem> list){
		
	try{
		
        TableColumn id = new TableColumn<>("ID");
        TableColumn user = new TableColumn<>("Cliente");
        TableColumn driver = new TableColumn<>("Motorista");
        TableColumn from = new TableColumn<>("Origem");
        TableColumn to = new TableColumn<>("Destino");
        TableColumn value = new TableColumn<>("Valor");
        TableColumn pay = new TableColumn<>("Pagamento");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        user.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        driver.setCellValueFactory(new PropertyValueFactory<>("motorista"));
        from.setCellValueFactory(new PropertyValueFactory<>("origem"));
        to.setCellValueFactory(new PropertyValueFactory<>("destino"));
        value.setCellValueFactory(new PropertyValueFactory<>("valorViagem"));
        pay.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));

        
        personTable.getColumns().addAll(id,user,driver,from,to,value,pay);
        		
        /*
         * Load the user in DataBase
         */

       
        
        personTable.setItems(FXCollections.observableArrayList(list));
 
        
        if(!userList.getChildren().isEmpty()){
        	userList.getChildren().remove(0);
        }
        
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