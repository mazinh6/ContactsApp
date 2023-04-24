import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

public class Main extends Application {

    // Contact Mazin = new Contact("Mazin", "Habib", "MHOP", "mh@example.com",
    // "123456789");

    // Contact Person = new Contact("ABC", "DEF", "HIJ", "mh@example.com",
    // "987654321");

    static Contact Contacts;

    static String[] values;

    public static void main(String[] banana) {
        launch(banana);
    }

    @Override
    public void start(Stage window) throws Exception {

        // Creating the JavaFX Pane
        Pane menuPane = new Pane();

        // Creating the JavaFX Scene and defining the pane and dimensions of the window
        Scene menuScene = new Scene(menuPane, 600, 600);

        // Creating a TableView Object for making a table to display on screen
        TableView contactTable = new TableView();

        // Creating the "First Name" column
        TableColumn<Contact, String> firstNameColumn = new TableColumn<Contact, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("fName"));

        // Creating the "Last Name" column
        TableColumn<Contact, String> lastNameColumn = new TableColumn<Contact, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lName"));

        // Creating the "Company" column
        TableColumn<Contact, String> companyColumn = new TableColumn<Contact, String>("Company");
        companyColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("cmpny"));

        // Creating the "Email Address" column
        TableColumn<Contact, String> emailAddressColumn = new TableColumn<Contact, String>("Email Address");
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("eAddress"));

        // Creating the "Phone Number" column
        TableColumn<Contact, String> phoneNumberColumn = new TableColumn<Contact, String>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("pNumber"));

        // Adding all of the columns to the TableView Object created earlier called
        // "contactTable"
        contactTable.getColumns().add(firstNameColumn);
        contactTable.getColumns().add(lastNameColumn);
        contactTable.getColumns().add(companyColumn);
        contactTable.getColumns().add(emailAddressColumn);
        contactTable.getColumns().add(phoneNumberColumn);

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
}
