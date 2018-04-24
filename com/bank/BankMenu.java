package com.bank;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
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
	//create all labels to be used: 
		Label lblPaneTitle = new Label(), 
				lblAccountNumber = new Label("Account Number"), 
				lblaccountBalance = new Label("Account Balance"),
				lblCustomerName = new Label("Customer Name"), lblEnterName = new Label("Name"), 
				lblEnterEmail = new Label("Email"),
				lblHomePhone = new Label("Home Phone"), 
				lblWorkPhone = new Label("Work Phone"),
				lblCreditRating = new Label("Credit Rating"),
				lblContactPerson = new Label("Contact Person Name"),
				lblContactPhone = new Label("Contact Person's Phone");
		
    	TextField txtAccountNumber = new TextField(), 
    			txtAccountBalance = new TextField(), 
    			txtCustomerName = new TextField(),
    			txtName = new TextField(), 
    			txtEmail = new TextField(),  
    			txtHomePhone = new TextField(), 
    			txtWorkPhone = new TextField(),
    			txtCreditRating = new TextField(),
    			txtContactPerson = new TextField(),
    			txtContactPhone = new TextField();

    	Button btnSubmitChecking = new Button("Create Checking Account"), 
    			btnSubmitGold = new Button("Create Gold Account"), 
    			btnSubmitPersonalCust = new Button("Create Customer"), 
    			btnSubmitCommercialCust = new Button("Create Customer"),
    			btnSubmitRegular = new Button("Create Regular Account");
    	CheckBox cbCommercial = new CheckBox("Commercial");
    	

    	
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
		    
		    exitMenuItem.setOnAction(actionEvent -> {
		    	Platform.exit();
		    });
		        
		    /********************************
		     * These are all the on action events
		     * Place any events that will occur one button clicks
		     * beyond this point
		     *********************************/
		    cbCommercial.setOnAction(actionEvent -> {
		    	if(cbCommercial.isSelected()) {
		    		mainPane.setCenter(null);
				    centerPane.getChildren().clear();
			    		
			    	lblPaneTitle.setText("Enter Customer Information: ");
			    	centerPane.add(lblPaneTitle, 0, 0);
			    	centerPane.add(lblEnterName, 0, 1);
			    	centerPane.add(lblEnterEmail, 0, 2);
			    	centerPane.add(lblCreditRating, 0, 3);
			    	centerPane.add(lblContactPerson, 0, 4);
			    	centerPane.add(lblContactPhone, 0, 5);
			    	
			    	centerPane.add(cbCommercial, 1, 0);
			    	
			    	centerPane.add(txtName, 1, 1);
			    	centerPane.add(txtEmail, 1, 2);
			    	centerPane.add(txtCreditRating, 1, 3);
			    	centerPane.add(txtContactPerson, 1, 4);
			    	centerPane.add(txtContactPhone, 1, 5);
			    	
			    	centerPane.add(btnSubmitPersonalCust, 1, 6);
			    	
			    	mainPane.setCenter(centerPane);
		    	}
		    	else {
			    	mainPane.setCenter(null);
			    	centerPane.getChildren().clear();
		    		
		    		lblPaneTitle.setText("Enter Customer Information: ");
			    	centerPane.add(lblPaneTitle, 0, 0);
			    	centerPane.add(lblEnterName, 0, 1);
			    	centerPane.add(lblEnterEmail, 0, 2);
			    	centerPane.add(lblHomePhone, 0, 3);
			    	centerPane.add(lblWorkPhone, 0, 4);
			    	
			    	centerPane.add(cbCommercial, 1, 0);
			    	
			    	centerPane.add(txtName, 1, 1);
			    	centerPane.add(txtEmail, 1, 2);
			    	centerPane.add(txtHomePhone, 1, 3);
			    	centerPane.add(txtWorkPhone, 1, 4);
			    	
			    	centerPane.add(btnSubmitPersonalCust, 1, 5);
			    	
			    	mainPane.setCenter(centerPane);
		    	}
		    });
		    
		    customerMenu.setOnAction(actionEvent -> {   	
		    	mainPane.setCenter(null);
		    	centerPane.getChildren().clear();
	    		
	    		lblPaneTitle.setText("Enter Customer Information: ");
		    	centerPane.add(lblPaneTitle, 0, 0);
		    	centerPane.add(lblEnterName, 0, 1);
		    	centerPane.add(lblEnterEmail, 0, 2);
		    	centerPane.add(lblHomePhone, 0, 3);
		    	centerPane.add(lblWorkPhone, 0, 4);
		    	
		    	centerPane.add(cbCommercial, 1, 0);
		    	
		    	centerPane.add(txtName, 1, 1);
		    	centerPane.add(txtEmail, 1, 2);
		    	centerPane.add(txtHomePhone, 1, 3);
		    	centerPane.add(txtWorkPhone, 1, 4);
		    	
		    	centerPane.add(btnSubmitPersonalCust, 1, 5);
		    	
		    	mainPane.setCenter(centerPane);
		  
		    });
		    
		    checkingMenu.setOnAction(actionEvent -> {
		    	mainPane.setCenter(null);
		    	centerPane.getChildren().clear();
		    	
		    	lblPaneTitle.setText("Enter Checking Account Info: ");
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
		   
		    goldMenu.setOnAction(actionEvent -> {
		    	mainPane.setCenter(null);
		    	centerPane.getChildren().clear();
		    	
		    	lblPaneTitle.setText("Enter Gold Account Information: ");
		    	centerPane.add(lblPaneTitle, 0, 0);
		    	centerPane.add(lblAccountNumber, 0, 1);
		    	centerPane.add(lblaccountBalance, 0, 2);
		    	centerPane.add(lblCustomerName, 0, 3);
		    	
		    	centerPane.add(txtAccountNumber, 1, 1);
		    	centerPane.add(txtAccountBalance, 1, 2);
		    	centerPane.add(txtCustomerName, 1, 3);
		    	
		    	centerPane.add(btnSubmitGold, 1, 4);
		    	
		    	mainPane.setCenter(centerPane);
		    });
		    
		    regularMenu.setOnAction(actionEvent -> {
		    	mainPane.setCenter(null);
		    	centerPane.getChildren().clear();
		    	
		    	lblPaneTitle.setText("Enter Regular Account Information: ");
		    	centerPane.add(lblPaneTitle, 0, 0);
		    	centerPane.add(lblAccountNumber, 0, 1);
		    	centerPane.add(lblaccountBalance, 0, 2);
		    	centerPane.add(lblCustomerName, 0, 3);
		    	
		    	centerPane.add(txtAccountNumber, 1, 1);
		    	centerPane.add(txtAccountBalance, 1, 2);
		    	centerPane.add(txtCustomerName, 1, 3);
		    	
		    	centerPane.add(btnSubmitRegular, 1, 4);
		    	
		    	mainPane.setCenter(centerPane);
		    });
		    
		    /*
		     * This section will have to handle what happens once the submit button is pressed
		     * This will have to handle all the text conversions to manageable data. 
		     * There are buttons for each pane presented in order to easily manage which 
		     * button was used. 
		     */
		    btnSubmitPersonalCust.setOnAction(actionEvent -> {
		    	
		    });
		    
		    btnSubmitChecking.setOnAction(actionEvent -> {
		    	
		    });
		    
		    btnSubmitGold.setOnAction(actionEvent -> {
		    	
		    });
		    
		    
		    menuBar.getMenus().addAll(fileMenu, createMenu, transactionsMenu, maintenanceMenu, displayMenu);
		    
		    return mainPane;
	 }

}
