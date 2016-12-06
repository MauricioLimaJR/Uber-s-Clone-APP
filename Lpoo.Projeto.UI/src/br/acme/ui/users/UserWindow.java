package br.acme.ui.users;

import java.sql.SQLException;
import java.util.ArrayList;


import br.acme.database.BeDriver;
import br.acme.database.MotoristaDAO;
import br.acme.database.SolicitanteDAO;
import br.acme.database.TravelDAO;
import br.acme.exception.DialogWindow;
import br.acme.exception.InputException;
import br.acme.exception.UserInterfaceException;
import br.acme.ui.MainWindow;
import br.acme.ui.elements.DriverList;
import br.acme.ui.elements.Travel;
import br.acme.ui.elements.TravelList;
import br.acme.ui.elements.UserEdit;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class UserWindow extends Application {
	
	private Solicitante user;
	UserEdit edit;
	Travel travel;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			edit = new UserEdit(user);
			DriverList.initTable();
			TravelList.initTable();
			
			//For put all elements
			//BorderPane elements = new BorderPane();
			//Hold the three main elements
			VBox bodyHolder = new VBox();
			//Then, create the header, body and footer
			HBox header = new HBox();
			
			HBox body = new HBox();
			
			HBox footer = new HBox();
			
			/////////////////////////   Making the HEADER   /////////////////////////
			
			Image timg = new Image(getClass().getResource("../files/logo.png").toString());
			ImageView titleImg = new ImageView(timg);
			titleImg.getStyleClass().add("titleImg");
			
			//Label is need to show some text
			Label nomeUser = new Label(user.getNome());
			//nomeUser.setTooltip(new Tooltip("Descrição curta do programa"));
			nomeUser.setAlignment(Pos.CENTER_LEFT);
			nomeUser.getStyleClass().addAll("labelHeader", "lbHeader");
			
			//Image user button
			Button userImg = new Button("change");
			userImg.getStyleClass().addAll("btnImg", "btnHeader");
			//userImg.setAlignment(Pos.CENTER);
			//Add function while clicking 
			
			//Profile button
			Button userSettings = new Button("Profile");
			userSettings.getStyleClass().addAll("btnPf", "btnHeader");
			//userSettings.setAlignment(Pos.CENTER);
			//Add function while clicking 
			
			//Logout button
			Button userLogout = new Button("Logout");
			userLogout.getStyleClass().addAll("btnLg", "btnHeader");
			//userLogout.setAlignment(Pos.CENTER_RIGHT);
			//Add function while clicking 				
			
			header.getChildren().addAll(titleImg, nomeUser, userSettings, userLogout);
			header.setSpacing(100);
			header.setAlignment(Pos.CENTER_LEFT);
			header.setId("header");
			//header.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
			/////////////////////////   Making the BODY   /////////////////////////
			
			/*
			 * RIGTH SIDE
			 */
			
			VBox workSpace = new VBox();
			
			/*
			 * Here, should be a space to holder
			 * the work windows
			 */
						
			workSpace.setAlignment(Pos.BOTTOM_CENTER);
			workSpace.getStyleClass().add("right_side_boby");
			
			
			/*
			 * LEFT SIDE
			 */
			
			//Vertical Box to put all buttons of menu
			VBox menuNav = new VBox();
			
			/*
			 * BUttons to do a Travel :: START
			 */
			HBox travelButtons = new HBox();
			
			Button cancelTravel = new Button("Cancelar viagem.");
			cancelTravel.getStyleClass().add("btnMenuNav");
			cancelTravel.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					Boolean confirm = DialogWindow.ConfirmDialog("Confirmação", "Quer cancelar a viagem?");
					if(confirm){
						clearView(workSpace);
					}
					
				}
			});
			
			Button startTravel = new Button("Pedir viagem");
			startTravel.getStyleClass().add("btnMenuNav");
			startTravel.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					try {
						travel.startTravel();
						DialogWindow.show("Sucesso", "Viagem realizada.");
						clearView(workSpace);
					} catch (SQLException e) {
						e.printStackTrace();
						DialogWindow.show(e.getMessage());
					}
					
				}
			});
			
			
			Button choiceDriver = new Button("Chamar motorista");
			choiceDriver.getStyleClass().add("btnMenuNav");
			choiceDriver.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					try {
						Motorista driver = getDriver(DriverList.getTable());
						travel.setDriver(driver);
						travelButtons.getChildren().remove(cancelTravel);
						travelButtons.getChildren().addAll(startTravel, cancelTravel);
						
					} catch (UserInterfaceException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			Button doTravel = new Button("Take a run");
			doTravel.getStyleClass().add("btnMenuNav");

			doTravel.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					ArrayList<Motorista> drivers;
					try {
						drivers = MotoristaDAO.readDrivers();
						DriverList.startTable(drivers);
						
						travel = new Travel(user);
						clearView(workSpace);
						travelButtons.getChildren().removeAll(travelButtons.getChildrenUnmodifiable());
						travelButtons.getChildren().addAll(choiceDriver, cancelTravel);
						workSpace.getChildren().addAll(DriverList.getTable(), travel, travelButtons);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			Button showTravels = new Button("Show travels");
			showTravels.getStyleClass().add("btnMenuNav");
			
			showTravels.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
				
					try{
						clearView(workSpace);
						loadView(TravelList.startTable(TravelDAO.readTravel(user.getId())), workSpace);
					} 
					catch ( SQLException e) {
						DialogWindow.show("Erro", "Nenhuma viagem realizada.");
						e.printStackTrace();
					}
				}
			});
			
			Button beDriver = new Button("Be Driver");
			beDriver.getStyleClass().add("btnMenuNav");
			
			beDriver.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						Solicitante verify = MotoristaDAO.readDriver(user.getEmail(), user.getSenha());
						if(verify != null){
							DialogWindow.show("Pedido já enviado.");
							clearView(workSpace);
						}else{
							Boolean choose = DialogWindow.ConfirmDialog("Confirmação", "Quer se tornar motorista?");
							if(choose) BeDriver.insertUser(user);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			});
			
			menuNav.getChildren().addAll(doTravel, showTravels, beDriver);
			menuNav.setAlignment(Pos.TOP_CENTER);
			menuNav.getStyleClass().add("menuNav");
			//menuNav.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
			/*
			 * Adding elements
			 */
			
			body.getChildren().addAll(menuNav, workSpace); 
			body.setSpacing(0);
			body.setAlignment(Pos.CENTER_LEFT);
			body.setId("body");
			//body.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());
			
			
			/////////////////////////   Making the FOOTER   /////////////////////////
		
			Label by = new Label("© Maurício de Lima & Pedro Gabriel");
			
			footer.getChildren().addAll(by);
			footer.setSpacing(0);
			footer.setAlignment(Pos.BOTTOM_CENTER);
			footer.setId("footer");
			//footer.getStylesheets().add(getClass().getResource("mainwindow.css").toExternalForm());

			
			//////////////////////// HEADER BUTTONS 
			HBox buttons = new HBox();
			Button save = new Button("Salvar");
			save.getStyleClass().add("btnMenuNav");
			save.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					try {
						edit.getFields();
					} catch (InputException e) {
						e.printStackTrace();
					}
				}
			});
			
			Button back = new Button("Cancelar");
			back.getStyleClass().addAll("btnMenuNav", "btnDelete");
			back.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					clearView(workSpace);
				}
			});
			
			Button delete = new Button("Deletar Conta");
			delete.getStyleClass().addAll("btnMenuNav", "btnDelete");
			delete.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String pass = DialogWindow.InputDialogDelete();
					if(user.getSenha().equals(pass)){
						try {
							SolicitanteDAO.deleteUser(user);
							MainWindow mainMenu = new MainWindow();
							mainMenu.start(primaryStage);							
						} catch (SQLException e) {
							e.printStackTrace();
							DialogWindow.show(e.getMessage());
						}
					}else DialogWindow.show("Senha incompatível.");
					
				}
			});
			
			buttons.getChildren().addAll(save,back,delete);
			
			userSettings.setOnAction( new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					edit.setAlignment(Pos.CENTER);
					clearView(workSpace);
					workSpace.getChildren().addAll(edit, buttons);
				}
			});
			
			userLogout.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					Boolean choice = DialogWindow.ConfirmDialog("Confimação", "Você quer realmente sair?");
					if(choice){
						MainWindow mainMenu = new MainWindow();
						mainMenu.setOldEmail(user.getEmail());
						mainMenu.start(primaryStage);
					}
				}
			});
			
			/////////////////////////   Making the final settings   /////////////////////////
					
			bodyHolder.getChildren().addAll(header, body, footer);
			bodyHolder.setId("mainContent");
			
			Scene scene = new Scene(bodyHolder,900,540);
			scene.getStylesheets().add(getClass().getResource("../files/mainwindow.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setUser(Solicitante user){
		this.user = user;
	}
	
	public void clearView(VBox content){
		content.getChildren().removeAll(content.getChildren());
	}
	
	public void loadView(Node view, VBox content){
		clearView(content);
		content.getChildren().addAll(view);
	}
	
	@SuppressWarnings("rawtypes")
	private Motorista getDriver(TableView table) throws UserInterfaceException, SQLException {
	    int selectedIndex = table.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	Boolean choice = DialogWindow.ConfirmDialog("Confimação", "Escolher este motorista?");
			if(choice){
		    	Motorista driver = (Motorista) table.getItems().get(selectedIndex);
		    	return driver;
			}
		}
	    
	    throw new UserInterfaceException("Nenhuma pessoa selecionada");
	}
}
