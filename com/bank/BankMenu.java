package com.bank;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * @Author Marcelo Martinez
 * @Author 
 * @Author
 * 
 * This is a program that is designed to simulate a bank program
 * 
 * This class is for the GUI
 * Ver 1.0;
 */
public class BankMenu extends Application{
		Label lblPaneTitle = new Label("S");
		Label lblAccountNumber = new Label("Account Number");
    	Label lblaccountBalance = new Label("Account Balance");
    	Label lblCustomerName = new Label("Customer Name");
    	TextField txtAccountNumber = new TextField();
    	TextField txtAccountBalance = new TextField();
    	TextField txtCustomerName = new TextField();
    	Button btnSubmitChecking = new Button("Submit");
    	
    	
    	Label lblEnterName = new Label("Name");
    	Label lblEnterEmail = new Label("Email");
    	Label lblHomePhone = new Label("Home Phone");
    	Label lblWorkPhone = new Label("Work Phone");
    	TextField txtName = new TextField();
    	TextField txtEmail = new TextField();
    	TextField txtHomePhone = new TextField();
    	TextField txtWorkPhone = new TextField();
    	
    	Button btnSubmitCustomer = new Button("Submit");
    	
    	public static void main(String[] args) {
    		launch(args);
	}
	
	 @Override
	  public void start(Stage primaryStage) {
	    
	    Scene scene = new Scene(getPane(), 550,350, Color.WHITE);
	    primaryStage.setTitle("Bank");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	  }
	 
	 protected BorderPane getPane() {
		 BorderPane mainPane = new BorderPane();
		  MenuBar menuBar = new MenuBar();
		  menuBar.setPrefWidth(300);
		  mainPane.setTop(menuBar);
		  
		  GridPane centerPane = new GridPane();
		  centerPane.setPadding(new Insets(11,12,13,14));
		  centerPane.setHgap(5);
		  centerPane.setVgap(5);
		  centerPane.setAlignment(Pos.CENTER);
		  
		  
		// File Menu - Exit
		    Menu fileMenu = new Menu("File");
		    MenuItem exitMenuItem = new MenuItem("Exit");
		    exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));

		    fileMenu.getItems().addAll(exitMenuItem);
		    
		    
		// Create Menu - Create Customer, Create Checking Account, Create Gold Account, Create Regular Account
		    
		    Menu createMenu = new Menu("Create");
		    MenuItem customerMenu = new MenuItem("Create Customer");
		    createMenu.getItems().add(customerMenu);
		    
		    MenuItem checkingMenu = new MenuItem("Create Checking Account");
		    createMenu.getItems().add(checkingMenu);
		    
		    MenuItem goldMenu = new MenuItem("Create Gold Account");
		    createMenu.getItems().add(goldMenu);
		    
		    MenuItem regularMenu = new MenuItem("Create Regular Account");
		    createMenu.getItems().add(regularMenu);
		    
		// Transactions Menu - Deposit, Withdraw
		    Menu transactionsMenu = new Menu("Transactions");
		    MenuItem depositMenu = new MenuItem("Deposit");
		    transactionsMenu.getItems().add(depositMenu);
		    
		    MenuItem withdrawalMenu = new MenuItem("Withdrawal");
		    transactionsMenu.getItems().add(withdrawalMenu);
		    
		// Maintenance Menu - EoM Update, Remove 
		    Menu maintenanceMenu = new Menu("Maintenance");
		    MenuItem eomMenu = new MenuItem("Apply EoM Updates");
		    maintenanceMenu.getItems().add(eomMenu);
		    
		    MenuItem removeMenuItem = new MenuItem("Remove Account");
		    maintenanceMenu.getItems().add(removeMenuItem);
		    
		// Display Menu - Display Account Info, Display All Accounts, Display Bank Stats 
		    Menu displayMenu = new Menu("Display");
		    MenuItem accountInfoMenuItem = new MenuItem("Display Account Info");
		    displayMenu.getItems().add(accountInfoMenuItem);
		    
		    MenuItem allAccountsMenuItem = new MenuItem("Display All Accounts");
		    displayMenu.getItems().add(allAccountsMenuItem);
		    
		    MenuItem bankStatsMenuItem = new MenuItem("Display Bank Stats");
		    displayMenu.getItems().add(bankStatsMenuItem);
		        
		    /********************************
		     * These are all the on action events
		     * Place any events that will occur one button clicks
		     * beyond this point
		     *********************************/
		    customerMenu.setOnAction(actionEvent -> {
		    	mainPane.setCenter(null);
		    	centerPane.getChildren().clear();
		    	
		    	lblPaneTitle.setText("Enter Customer Information: ");
		    	centerPane.add(lblPaneTitle, 0, 0);
		    	centerPane.add(lblEnterName, 0, 1);
		    	centerPane.add(lblEnterEmail, 0, 2);
		    	centerPane.add(lblHomePhone, 0, 3);
		    	centerPane.add(lblWorkPhone, 0, 4);
		    	
		    	centerPane.add(txtName, 1, 1);
		    	centerPane.add(txtEmail, 1, 2);
		    	centerPane.add(txtHomePhone, 1, 3);
		    	centerPane.add(txtWorkPhone, 1, 4);
		    	
		    	centerPane.add(btnSubmitCustomer, 1, 5);
		    	
		    	mainPane.setCenter(centerPane);
		    	
		    });
		    
		    checkingMenu.setOnAction(actionEvent -> {
		    	mainPane.setCenter(null);
		    	centerPane.getChildren().clear();
		    	
		    	lblPaneTitle.setText("Enter Checking Account Info: ");
		    	centerPane.add(lblPaneTitle, 0, 0);
		    	Label lblAccountNumber = new Label("Account Number");
		    	Label lblaccountBalance = new Label("Account Balance");
		    	Label lblCustomerName = new Label("Customer Name");

		    	TextField txtAccountNumber = new TextField();
		    	TextField txtAccountBalance = new TextField();
		    	TextField txtCustomerName = new TextField();
		    	
		    	Button btnSubmitChecking = new Button("Submit");
		    	centerPane.add(lblAccountNumber, 0, 1);
		    	centerPane.add(lblaccountBalance, 0, 2);
		    	centerPane.add(lblCustomerName, 0, 3);
		    	
		    	centerPane.add(txtAccountNumber, 1, 1);
		    	centerPane.add(txtAccountBalance, 1, 2);
		    	centerPane.add(txtCustomerName, 1, 3);
		    	
		    	centerPane.add(btnSubmitChecking, 1, 4);
		    	
		    	mainPane.setCenter(centerPane);
		    });
		   
		    goldMenu.setOnAction(actionEvent -> {
		    	mainPane.setCenter(null);
		    	centerPane.getChildren().clear();
		    	
		    	lblPaneTitle.setText("Enter Gold Account Info: ");
		    	centerPane.add(lblPaneTitle, 0, 0);
		    	centerPane.add(lblAccountNumber, 0, 1);
		    	centerPane.add(lblaccountBalance, 0, 2);
		    	centerPane.add(lblCustomerName, 0, 3);
		    	
		    	centerPane.add(txtAccountNumber, 1, 1);
		    	centerPane.add(txtAccountBalance, 1, 2);
		    	centerPane.add(txtCustomerName, 1, 3);
		    	
		    	centerPane.add(btnSubmitChecking, 1, 4);
		    	
		    	mainPane.setCenter(centerPane);
		    });
		    
		    btnSubmitChecking.setOnAction(actionEvent -> {
		    	
		    });
		    
		    exitMenuItem.setOnAction(actionEvent -> {
		    	Platform.exit();
		    });
		    
		    menuBar.getMenus().addAll(fileMenu, createMenu, transactionsMenu, maintenanceMenu, displayMenu);
		    
		    return mainPane;
	 }

}
