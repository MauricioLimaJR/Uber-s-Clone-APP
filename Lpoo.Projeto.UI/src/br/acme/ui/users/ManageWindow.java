package br.acme.ui.users;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.acme.database.BeDriver;
import br.acme.database.MotoristaDAO;
import br.acme.database.SolicitanteDAO;
import br.acme.exception.NullStringException;
import br.acme.exception.RepositorioException;
import br.acme.exception.UnableCpfExecption;
import br.acme.exception.UserInterfaceException;
import br.acme.storage.Repositorio;
import br.acme.ui.MainWindow;
import br.acme.ui.elements.DriverList;
import br.acme.ui.elements.TravelList;
import br.acme.ui.elements.UserList;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ManageWindow extends Application {
	
	private Gerente adm;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//For put all elements
			//BorderPane elements = new BorderPane();
			//Hold the three main elements
			VBox bodyHolder = new VBox();
			//Then, create the header, body and footer
			HBox header = new HBox();
			
			HBox body = new HBox();
			
			HBox footer = new HBox();
			
			UserList.initTable();
			
			/////////////////////////   Making the HEADER   /////////////////////////
			
			Image timg = new Image(getClass().getResource("../files/logo.png").toString());
			ImageView titleImg = new ImageView(timg);
			titleImg.getStyleClass().add("titleImg");
			
			//Label is need to show some text
			Label nomeUser = new Label(adm.getNome());
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
			
			Button doTravel = new Button("Take a run");
			doTravel.getStyleClass().add("btnMenuNav");
			//Add function while clicking 
			
			Button showTravels = new Button("Show travels");
			showTravels.getStyleClass().add("btnMenuNav");
			
			showTravels.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						loadView(TravelList.startTable(Repositorio.rpViagens.buscarPorId(adm.getId())), workSpace);
					} 
					catch (RepositorioException e) {
						e.printStackTrace();
						 Alert alert = new Alert(AlertType.WARNING);
				         alert.setTitle("Repositório vazio");
				         alert.setHeaderText(e.getMessage());
				         alert.setContentText("Nehuma viagem realizada.");
				         alert.showAndWait();
					}
				}
			});
			
			/*
			 * Buttons to accept a driver :: START
			 */
			HBox buttons = new HBox();
			buttons.setAlignment(Pos.CENTER);		
			
			Button acceptDriver = new Button("Accept Drivers");
			acceptDriver.getStyleClass().add("btnMenuNav");
			acceptDriver.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						acceptDriver(UserList.getTable());
					} catch (UserInterfaceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			Button negateDriver = new Button("Negate Drivers");
			negateDriver.getStyleClass().addAll("btnMenuNav", "btnDelete");
			negateDriver.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						negateDriver(UserList.getTable());
					} catch (UserInterfaceException e) {
						
					}
				}
			});
			
			buttons.getChildren().addAll(acceptDriver, negateDriver);
			
			Button allowDriver = new Button("Accept Drivers");
			allowDriver.getStyleClass().add("btnMenuNav");
			
			allowDriver.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					ArrayList<Solicitante> users;
					try {
						users = BeDriver.readUsers();
						UserList.startTable(users);
						clearView(workSpace);
						workSpace.getChildren().addAll(UserList.getTable(), buttons);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			
			});
			/*
			 * Buttons to accept a driver :: END
			 */
			
			/*
			 * User's Buttons :: START
			 */
			
			Button deleteUser = new Button("Delete User");
			deleteUser.getStyleClass().addAll("btnMenuNav", "btnDelete");
			deleteUser.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						deleteUser(UserList.getTable());
					} catch (UserInterfaceException | SQLException e) {
						
					}
				}
			});
			
			Button showUsers = new Button("See Users");
			showUsers.getStyleClass().add("btnMenuNav");	
			
			showUsers.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					ArrayList<Solicitante> users;
					try {
						users = SolicitanteDAO.readUsers();
						UserList.startTable(users);
						clearView(workSpace);
						workSpace.getChildren().addAll(UserList.getTable(), deleteUser);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			/*
			 * User's Buttons :: END
			 */
			
			/*
			 * Driver's Buttons :: START
			 */
			
			Button deleteDriver = new Button("Delete User");
			deleteDriver.getStyleClass().addAll("btnMenuNav", "btnDelete");
			deleteDriver.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						deleteDriver(UserList.getTable());
					} catch (UserInterfaceException | SQLException e) {
						
					}
				}
			});
			
			Button showDrivers = new Button("See Drivers");
			showDrivers.getStyleClass().add("btnMenuNav");
			
			showDrivers.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					ArrayList<Motorista> drivers;
					try {
						drivers = MotoristaDAO.readDrivers();
						DriverList.startTable(drivers);
						clearView(workSpace);
						workSpace.getChildren().addAll(DriverList.getTable(), deleteDriver);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			/*
			 * Driver's Buttons :: END
			 */
			
			Button travels = new Button("See Travels");
			travels.getStyleClass().add("btnMenuNav");
			
			travels.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					/*
					 * See all travels?
					 */
				}
				
			});
			
			Button clear = new Button("Clear");
			clear.getStyleClass().add("btnMenuNav");
			clear.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					clearView(workSpace);
				}
			});
			
			menuNav.getChildren().addAll(showUsers, allowDriver, clear);
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
			
			userSettings.setOnAction( new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
				//clearView(workSpace);
			
				}
			});
			
			userLogout.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					MainWindow mainMenu = new MainWindow();
					mainMenu.setOldEmail(adm.getEmail());
					mainMenu.start(primaryStage);
					
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
	
	public void setAmd(Gerente adm){
		this.adm = adm;
	}
	
	public void clearView(VBox content){
		content.getChildren().removeAll(content.getChildren());
	}
	
	public void loadView(Node view, VBox content){
		clearView(content);
		content.getChildren().addAll(view);
	}
	
	private void deleteUser(TableView table) throws UserInterfaceException, SQLException {
	    int selectedIndex = table.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	Solicitante user = (Solicitante) table.getItems().get(selectedIndex);
	    	table.getItems().remove(selectedIndex);
	    	SolicitanteDAO.deleteUser(user);
	    } 
	    else {
	    	throw new UserInterfaceException("Nenhuma pessoa selecionada");
	    }
	}
	
	private void deleteDriver(TableView table) throws UserInterfaceException, SQLException {
	    int selectedIndex = table.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	Motorista driver = (Motorista) table.getItems().get(selectedIndex);
	    	table.getItems().remove(selectedIndex);
	    	MotoristaDAO.deleteDriver(driver);
	    } 
	    else {
	    	throw new UserInterfaceException("Nenhuma pessoa selecionada");
	    }
	}
	
	private void acceptDriver(TableView table) throws UserInterfaceException{
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0){
			//Put user in default repository
			try {
				Solicitante user = (Solicitante) table.getItems().get(selectedIndex);
				Motorista driver = new Motorista(user);
				MotoristaDAO.insertDriver(driver);
				BeDriver.deleteUser(user);
				SolicitanteDAO.deleteUser(user);
				table.getItems().remove(selectedIndex);
				
			} catch (NullStringException | UnableCpfExecption | ParseException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			throw new UserInterfaceException("Nenhuma pessoa selecionada");
		}
	}
	
	private void negateDriver(TableView table) throws UserInterfaceException{
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0){
			//Put user in default repository
			try {
				Solicitante user = (Solicitante) table.getItems().get(selectedIndex);
				BeDriver.deleteUser(user);
				table.getItems().remove(selectedIndex);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			throw new UserInterfaceException("Nenhuma pessoa selecionada");
		}
	}
}
