import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Main extends Application {

    // Contact Mazin = new Contact("Mazin", "Habib", "MHOP", "mh@example.com",
    // "123456789");

    // Contact Person = new Contact("ABC", "DEF", "HIJ", "mh@example.com",
    // "987654321");

    static Contact Contacts;

    static String[] values;

    // Create a private TableView object called contactTable
    private TableView contactTable = new TableView();

    // Create a private TextField object called firstName
    private TextField firstName = new TextField();

    // Create a private TextField object called lastName
    private TextField lastName = new TextField();

    // Create a private TextField object called companyName
    private TextField companyName = new TextField();

    // Create a private TextField object called email
    private TextField email = new TextField();

    // Create a private TextField object called number
    private TextField number = new TextField();

    public static void main(String[] banana) {
        launch(banana);
    }

    @Override
    public void start(Stage window) throws Exception {

        // Creating the JavaFX Pane
        Pane menuPane = new Pane();

        // Creating the JavaFX Scene and defining the pane and dimensions of the window
        Scene menuScene = new Scene(menuPane, 800, 600);

        // Creating a TableView Object for making a table to display on screen
        // TableView contactTable = new TableView();
        contactTable.setEditable(true);

        // Creating the "First Name" column
        TableColumn<Contact, String> firstNameColumn = new TableColumn<Contact, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("fName"));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Creating the "Last Name" column
        TableColumn<Contact, String> lastNameColumn = new TableColumn<Contact, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lName"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Creating the "Company" column
        TableColumn<Contact, String> companyColumn = new TableColumn<Contact, String>("Company");
        companyColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("cmpny"));
        companyColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Creating the "Email Address" column
        TableColumn<Contact, String> emailAddressColumn = new TableColumn<Contact, String>("Email Address");
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("eAddress"));
        emailAddressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Creating the "Phone Number" column
        TableColumn<Contact, String> phoneNumberColumn = new TableColumn<Contact, String>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("pNumber"));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Adding all of the columns to the TableView Object created earlier called
        // "contactTable"
        contactTable.getColumns().add(firstNameColumn);
        contactTable.getColumns().add(lastNameColumn);
        contactTable.getColumns().add(companyColumn);
        contactTable.getColumns().add(emailAddressColumn);
        contactTable.getColumns().add(phoneNumberColumn);

        // relocate the table to coordinates (165, 85)
        contactTable.relocate(165, 85);

        // set the minimum width of the table to 470
        contactTable.setMinWidth(470);

        // set a prompt text for the TextField fristName which says "First Name"
        firstName.setPromptText("First Name");

        // add the TextField firstName to menuLayout
        menuPane.getChildren().add(firstName);

        // relocate firstName to coordinates (20, 20)
        firstName.relocate(20, 20);

        // set the maximum width of firstName to 100
        firstName.setMaxWidth(100);

        // set a prompt text for the TextField lastName which says "Last Name"
        lastName.setPromptText("Last Name");

        // add the TextField lastName to menuLayout
        menuPane.getChildren().add(lastName);

        // relocate lastName (130, 20)
        lastName.relocate(130, 20);

        // set the maximum width of lastName to 100
        lastName.setMaxWidth(100);

        // set a prompt text for the TextField companyName which says "Company"
        companyName.setPromptText("Company");

        // add the TextField companyName to menuLayout
        menuPane.getChildren().add(companyName);

        // relocate companyName to (240, 20)
        companyName.relocate(240, 20);

        // set the maximum width of companyName to 120
        companyName.setMaxWidth(120);

        // set a prompt text for the TextField email which says "Email Address"
        email.setPromptText("Email Address");

        // add the TextField email to menuLayout
        menuPane.getChildren().add(email);

        // relocate email to (370, 20)
        email.relocate(370, 20);

        // set the minimum width of email to be 200
        email.setMinWidth(200);

        // set a prompt text for the TextField number which says "Phone Number"
        number.setPromptText("Phone Number");

        // add the TextField number to menuLayout
        menuPane.getChildren().add(number);

        // relocate number to (580, 20)
        number.relocate(580, 20);

        // set the maximum width of number to 100
        number.setMaxWidth(100);

        // create a Button called addButton for adding contacts
        Button addButton = new Button("Add");

        // add the button to menuPane
        menuPane.getChildren().add(addButton);

        // relocate it to (690, 20)
        addButton.relocate(690, 20);

        // set the minimum width of it to 90
        addButton.setMinWidth(90);

        // Create an event handler for when the button is pressed
        addButton.setOnAction(event -> {

            // Create a contact object with the values in the TextFields to be the values of
            // the properties of the Contact
            Contact person = new Contact(firstName.getText(), lastName.getText(), companyName.getText(),
                    email.getText(), number.getText());

            // Call the addContact method that adds the contact to the table
            addContact(person);

            // Call the clearFields method that clears the TextFields
            clearFields();

        });

        // Create a button called delete for deleting contacts
        Button deleteButton = new Button("Delete");

        // Add it to menuPane
        menuPane.getChildren().add(deleteButton);

        // Relocate it to (690, 60)
        deleteButton.relocate(690, 60);

        // Set the minimum width to 90
        deleteButton.setMinWidth(90);

        // Create an event handler for when the button is pressed
        deleteButton.setOnAction(event -> {

            // Create an int variable called row to store the index value of the Contact
            // being deleted
            int row = contactTable.getSelectionModel().getSelectedIndex();

            // Remove the Contact with the index row
            contactTable.getItems().remove(row);

        });

        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {

                String path = "contactlist.csv";
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));

                    for (Object r : contactTable.getItems()) {
                        String firstName = ((Contact) r).fNameProperty().get();
                        String lastName = ((Contact) r).lNameProperty().get();
                        String company = ((Contact) r).cmpnyProperty().get();
                        String eAddress = ((Contact) r).eAddressProperty().get();
                        String pNumber = ((Contact) r).pNumberProperty().get();

                        System.out.println(firstName + "," + lastName + "," + company + "," + eAddress + "," + pNumber);
                        writer.write(
                                firstName + "," + lastName + "," + company + "," + eAddress + "," + pNumber);
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        // Initializing the path of the .csv file
        String path = "contactlist.csv";
        String line = "";

        // Try-Catch to avoid FileNotFoundException and IOException
        try {
            // Creating the BufferedReader object that we will use to read from the .csv
            // file
            BufferedReader br = new BufferedReader(new FileReader(path));

            // Reading each line of the file with a while loop (will repeat as long as the
            // lines in the file are not null)
            while ((line = br.readLine()) != null) {
                // Adding each string to an array of strings
                values = line.split(",");

                // Defining the contact object for one line in the csv file
                Contacts = new Contact(values[0], values[1], values[2], values[3], values[4]);

                // Adding the contact to be displayed in the GUI
                contactTable.getItems().add(Contacts);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adding the table to the pane
        menuPane.getChildren().add(contactTable);

        // Setting the active window to the menuScene created earlier
        window.setScene(menuScene);

        // Displaying the application window
        window.show();
    }

    // A private void method called addContact with the parameter of type Contact
    // called person. Its function is to add person to the contactTable
    private void addContact(Contact Person) {

        // Add person to contactTable
        contactTable.getItems().add(Person);
    }

    // A private void method called ClearFields that doesn't have any parameters.
    // Its function is to clear the TextFields
    private void clearFields() {

        // Clear firstName
        firstName.clear();

        // Clear lastName
        lastName.clear();

        // Clear companyName
        companyName.clear();

        // Clear email
        email.clear();

        // Clear number
        number.clear();

    }

}
