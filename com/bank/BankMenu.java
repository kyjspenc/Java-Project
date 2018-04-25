package com.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bank.accounts.Account;
import com.bank.accounts.CheckingAccount;
import com.bank.accounts.GoldAccount;
import com.bank.accounts.RegularAccount;
import com.bank.customer.CommercialCustomer;
import com.bank.customer.Customer;
import com.bank.customer.PersonalCustomer;
import com.bank.utils.UniqueIDFactory;

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
import javafx.scene.control.TextArea;
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
 * @Author Kyler Spencer
 * @Author Josh Bertrand
 * 
 * This is a program that is designed to simulate a bank program
 * 
 * This class is for the GUI
 * Ver 1.0;
 */
public class BankMenu extends Application implements Serializable{
	
	List<Account> accountArray = new ArrayList<Account>();
	List<Customer> customerArray = new ArrayList<Customer>();
	
	//
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
				lblContactPhone = new Label("Contact Person's Phone"),
				lblTransactionAmount = new Label("Transaction Amount"),
				lblCreated = new Label("Successfully Created!"),
				lblNotification = new Label("");
		
    	TextField txtAccountNumber = new TextField(), 
    			txtAccountBalance = new TextField(), 
    			txtCustomerName = new TextField(),
    			txtName = new TextField(), 
    			txtEmail = new TextField(),  
    			txtHomePhone = new TextField(), 
    			txtWorkPhone = new TextField(),
    			txtCreditRating = new TextField(),
    			txtContactPerson = new TextField(),
    			txtContactPhone = new TextField(),
    			txtTransactionAmount = new TextField();

    	Button btnSubmitChecking = new Button("Create Checking Account"), 
    			btnSubmitGold = new Button("Create Gold Account"), 
    			btnSubmitPersonalCust = new Button("Create Personal Customer"), 
    			btnSubmitCommercialCust = new Button("Create Commercial Customer"),
    			btnSubmitRegular = new Button("Create Regular Account"), 
    			btnDeposit = new Button("Deposit into Account"),
    			btnWithdraw = new Button("Withdraw from Account"),
    			btnExit = new Button("Exit");
    	
    	CheckBox cbCommercial = new CheckBox("Commercial");
    	
    	TextArea textOutputArea = new TextArea();
    	
    	public static void main(String[] args) {
    		launch(args);
	}
	
	 @Override
	  public void start(Stage primaryStage) {
	    
		try {
			openFileForReading();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Scene scene = new Scene(getPane(), 550,350, Color.WHITE);
	    primaryStage.setTitle("Bank");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	 }
	 
	 protected void showPopUp() {
		 Stage popUpWin = new Stage();
		 Scene popUp = new Scene(getPopUpPane(), 250, 150, Color.WHITE);
		 
		 popUpWin.setTitle("Notification");
		 popUpWin.setScene(popUp);
		 popUpWin.show();
	 }
	 
	 protected BorderPane getPopUpPane() {
		 BorderPane popUpPane = new BorderPane();
		 GridPane popUpCenter = new GridPane();
		 popUpCenter.setPadding(new Insets(11,12,13,14));
		  popUpCenter.setHgap(5);
		  popUpCenter.setVgap(5);
		  popUpCenter.setAlignment(Pos.CENTER);
		 
		  popUpCenter.addColumn(2, lblCreated, lblNotification);
		  popUpPane.setCenter(popUpCenter);
		 return popUpPane;
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
		    	exit();
		    	Platform.exit();
		    });
		        
		    /********************************
		     * These are all the on action events
		     * Place any events that will occur one button clicks
		     * beyond this point
		     *********************************/
		    cbCommercial.setOnAction(actionEvent -> {
		    	if(cbCommercial.isSelected()) {
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
			    	
			    	centerPane.add(btnSubmitCommercialCust, 1, 6);
			    	
			    	mainPane.setCenter(centerPane);
		    	}
		    	else {
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
		    //if(!customerArray.isEmpty()) {
			    checkingMenu.setOnAction(actionEvent -> {
			    	centerPane.getChildren().clear();
			    	
			    	lblPaneTitle.setText("Enter Checking Account Info: ");
			    	centerPane.add(lblPaneTitle, 0, 0);
	
			    	centerPane.add(lblaccountBalance, 0, 1);
			    	centerPane.add(lblCustomerName, 0, 2);
			    	
			    	centerPane.add(txtAccountBalance, 1, 1);
			    	centerPane.add(txtCustomerName, 1, 2);
			    	
		    		centerPane.add(btnSubmitChecking, 1, 3);

			    	btnSubmitChecking.setVisible(false);
			    	if (!(txtAccountBalance.getText().equals("") && txtCustomerName.getText().equals(""))) {
			    		btnSubmitChecking.setVisible(true);
			    	}
			    	mainPane.setCenter(centerPane);
			    });
			    
			   
			    goldMenu.setOnAction(actionEvent -> {
			    	centerPane.getChildren().clear();
			    	
			    	lblPaneTitle.setText("Enter Gold Account Information: ");
			    	centerPane.add(lblPaneTitle, 0, 0);

			    	centerPane.add(lblaccountBalance, 0, 1);
			    	centerPane.add(lblCustomerName, 0, 2);
			    	
			    	centerPane.add(txtAccountBalance, 1, 1);
			    	centerPane.add(txtCustomerName, 1, 2);
			    	
			    	centerPane.add(btnSubmitGold, 1, 3);
			    	
			    	mainPane.setCenter(centerPane);
			    });
			    
			    regularMenu.setOnAction(actionEvent -> {
			    	centerPane.getChildren().clear();
			    	
			    	lblPaneTitle.setText("Enter Regular Account Information: ");
			    	centerPane.add(lblPaneTitle, 0, 0);
			    	centerPane.add(lblaccountBalance, 0, 1);
			    	centerPane.add(lblCustomerName, 0, 2);
			    	
			    	centerPane.add(txtAccountBalance, 1, 1);
			    	centerPane.add(txtCustomerName, 1, 2);
			    	
			    	centerPane.add(btnSubmitRegular, 1, 3);
			    	
			    	mainPane.setCenter(centerPane);
			    });
			    
			    depositMenu.setOnAction(actionEvent -> {
			    	centerPane.getChildren().clear();
			    	//element, column, row
			    	lblPaneTitle.setText("Deposit");
			    	
			    	centerPane.add(lblPaneTitle, 0, 0);
			    	centerPane.add(lblAccountNumber, 0,1);
			    	centerPane.add(txtAccountNumber, 1, 1);
			    	centerPane.add(txtTransactionAmount, 1, 2);
			    	centerPane.add(lblTransactionAmount, 0, 2);
			    	centerPane.add(btnDeposit, 1, 4);
			    	
			    	mainPane.setCenter(centerPane);
			    });
			    
			    withdrawalMenu.setOnAction(actionEvent ->{
			    	centerPane.getChildren().clear();
			    	//element, column, row
			    	lblPaneTitle.setText("Withdraw");
			    	
			    	
			    	centerPane.add(lblPaneTitle, 0, 0);
			    	centerPane.add(lblAccountNumber, 0,1);
			    	centerPane.add(txtAccountNumber, 1, 1);
			    	centerPane.add(txtTransactionAmount, 1, 2);
			    	centerPane.add(lblTransactionAmount, 0, 2);
			    	centerPane.add(btnWithdraw, 1, 4);
			    	
			    	mainPane.setCenter(centerPane);
			    });
			    
			    allAccountsMenuItem.setOnAction(actionEvent -> {
			    	centerPane.getChildren().clear();
			    	
			    	lblPaneTitle.setText("Display All Accounts");
			    	//element, column, row
			    	centerPane.add(lblPaneTitle, 0, 0);
			    	textOutputArea.setEditable(false);
			    	centerPane.add(textOutputArea, 0, 1);
			    	centerPane.add(btnExit, 1, 2);
			    	
			    	for(Account a : accountArray) {
			    		
			    		textOutputArea.appendText(a.getCustomer().getName() + "\t\t\t" + a.getAccountNumber());
			    		
			    	}
			    	
			    	mainPane.setCenter(centerPane);
			    });
			    
			    bankStatsMenuItem.setOnAction(actionEvent -> {
			    	centerPane.getChildren().clear();
			    	
			    	lblPaneTitle.setText("Display Bank Statistics");
			    	centerPane.add(lblPaneTitle, 0, 0);
			    	centerPane.add(textOutputArea, 0, 1);
			    	centerPane.add(btnExit, 1, 2);
			    });
		    //}
		    /*
		     * This section will have to handle what happens once the submit button is pressed
		     * This will have to handle all the text conversions to manageable data. 
		     * There are buttons for each pane presented in order to easily manage which 
		     * button was used. 
		     */
		    btnSubmitPersonalCust.setOnAction(actionEvent -> {
		    	
		    	String customerName = txtName.getText();
		    	String customerEmail = txtEmail.getText();
		    	String customerHomePhone = txtHomePhone.getText();
		    	String customerWorkPhone = txtWorkPhone.getText();
		    	
		    	Customer newPersonal = new PersonalCustomer(UniqueIDFactory.generateUniqueID(), customerName, customerEmail, customerHomePhone, customerWorkPhone);
				customerArray.add(newPersonal);
								
				txtName.clear();
				txtEmail.clear();
				txtHomePhone.clear();
				txtWorkPhone.clear();
				
				centerPane.getChildren().clear();
				mainPane.setCenter(centerPane);
				
				lblNotification.setText("Your ID is: " + Long.toString(newPersonal.getCustomerId()));
				showPopUp();
		    });
		    
		    btnSubmitCommercialCust.setOnAction(actionEvent ->{
		    	
		    	String customerFullName = txtName.toString();
		    	String customerEmail = txtEmail.toString();
		    	int customerCreditRating = Integer.parseInt(txtCreditRating.toString());
		    	String contactPerson = txtContactPerson.toString();
		    	String contactPersonPhone = txtContactPhone.toString();
		    	
		    	Customer newCommercial = new CommercialCustomer(UniqueIDFactory.generateUniqueID(), customerFullName, customerEmail, customerCreditRating, contactPerson, contactPersonPhone);
				customerArray.add(newCommercial);
				
				txtName.clear();
				txtEmail.clear();
				txtCreditRating.clear();
				txtContactPerson.clear();
				txtContactPhone.clear();
				
				centerPane.getChildren().clear();
				mainPane.setCenter(centerPane);
				
				lblNotification.setText("Your ID is: " + Long.toString(newCommercial.getCustomerId()));
				showPopUp();
		    });
		    
		    btnSubmitChecking.setOnAction(actionEvent -> {
		    	Date newDate = new Date();
		    	double startingBalance = Double.parseDouble(txtAccountBalance.getText());
		    	
		    	String customerInput = txtCustomerName.getText();
		    	boolean customerSelected = false;
		    	Customer customer = null;
		    	
		    	for(Customer c : customerArray) {
		    		
		    		System.out.println(c);
					if(c.getName().equals(customerInput)) {
						customer = c;
						customerSelected = true;
					}
				}
		    	
		    	if(customerSelected) {
			    	Account chkAcct = new CheckingAccount(UniqueIDFactory.generateUniqueID(),startingBalance, customer, newDate);
					accountArray.add(chkAcct);
					lblNotification.setText("Your accountNumber is: " + chkAcct.getAccountNumber());
					showPopUp();
		    	}else {
		    		lblCreated.setText("Not Created!");
		    		lblNotification.setText("Customer is not available!");
		    		showPopUp();
		    	}
				txtCustomerName.clear();
				txtAccountBalance.clear();
				
				centerPane.getChildren().clear();
				mainPane.setCenter(centerPane);
		    });
		    
		    btnSubmitGold.setOnAction(actionEvent -> {
		    	Date newDate = new Date();
		    	
		    	double startingBalance = Double.parseDouble(txtAccountBalance.getText());
		    	
		    	String customerInput = txtCustomerName.toString();
		    	Customer customer = null;
		    	
		    	for(Customer c : customerArray) {
					if(c.getName().equals(customerInput)) {
						customer = c;
						//customerSelected = true;
					}
				}
		    	
		    	Account goldAcct = new GoldAccount(UniqueIDFactory.generateUniqueID(),startingBalance, customer, newDate);
				accountArray.add(goldAcct);
				System.out.println("Gold account has been successfully created. Your accountNumber is: " + goldAcct.getAccountNumber() + "\n");
		   
				txtCustomerName.clear();
				txtAccountBalance.clear();
				
				centerPane.getChildren().clear();
				mainPane.setCenter(centerPane);
		    });
		    
		    btnSubmitRegular.setOnAction(actionEvent ->{
		    	Date newDate = new Date();
		    	
		    	double startingBalance = Double.parseDouble(txtAccountBalance.getText());
		    	
		    	String customerInput = txtCustomerName.toString();
		    	Customer customer = null;
		    	
		    	for(Customer c : customerArray) {
					if(c.getName().equals(customerInput)) {
						customer = c;
						//customerSelected = true;
					}
				}
		    	
		    	Account regularAcct = new RegularAccount(UniqueIDFactory.generateUniqueID(), startingBalance, customer, newDate);
				accountArray.add(regularAcct);
				System.out.println("Regular Account has been created. Your account number is : " + regularAcct.getAccountNumber() + "\n");
		    
				txtCustomerName.clear();
				txtAccountBalance.clear();
				
				centerPane.getChildren().clear();
				mainPane.setCenter(centerPane);
		    });
		    
		    btnDeposit.setOnAction(actionEvent -> {
		    	
		    });
		    
//		    btnWithraw.setOnAction(actionEvent -> {
		    	
//		    });
		    
		    btnExit.setOnAction(actionEvent -> {
		    	
		    	textOutputArea.clear();
		    	
		    	centerPane.getChildren().clear();
				mainPane.setCenter(centerPane);
		    
		    });
		    
		    
		    menuBar.getMenus().addAll(fileMenu, createMenu, transactionsMenu, maintenanceMenu, displayMenu);
		    
		    return mainPane;
}
	 
	 public void openFileForReading() throws ClassNotFoundException {
			
			File f = new File("bankdata.ser");
			if(f.exists()) {

				try {
					ObjectInputStream input = new ObjectInputStream(new FileInputStream(f));

					accountArray = (ArrayList<Account>) input.readObject();
		
				} catch(IOException ioException) {
					System.err.println("Error opening file.");
					//ioException.printStackTrace();
					System.out.println();
				}
			}
		}
	 public void exit() {
			
			File f = new File("bankdata.ser");
			try {
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(f));
				output.writeObject(accountArray);
				System.out.println("Saved");
			} catch (IOException ioException) {
				System.err.println("Error opening file.");
				//ioException.printStackTrace();
				System.out.println();
			}
			
		}

}