import java.io.File;
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

    Contact Mazin = new Contact("Mazin", "Habib", "MHOP", "mh@example.com",
            123456789);

    Contact Person = new Contact("Mazin", "Habib", "MHOP", "mh@example.com",
            123456789);

    public static void main(String[] banana) {

        launch(banana);

        // Read from a file on startup to generate the contacts
        // Any edits made in the GUI have to be written to the file (including new
        // contacts)

        // File csv = new File("contactlist.csv");

        // Scanner sc = new Scanner(csv);

        // System.out.println(csv.isDirectory());

    }

    @Override
    public void start(Stage window) throws Exception {

        Pane menuPane = new Pane();

        Scene menuScene = new Scene(menuPane, 600, 600);

        TableView contactTable = new TableView();

        TableColumn<Contact, String> firstNameColumn = new TableColumn<Contact, String>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("fName"));

        TableColumn<Contact, String> lastNameColumn = new TableColumn<Contact, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lName"));

        TableColumn<Contact, String> companyColumn = new TableColumn<Contact, String>("Company");
        companyColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("cmpny"));

        TableColumn<Contact, String> emailAddressColumn = new TableColumn<Contact, String>("Email Address");
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("eAddress"));

        TableColumn<Contact, String> phoneNumberColumn = new TableColumn<Contact, String>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("pNumber"));

        contactTable.getColumns().add(firstNameColumn);
        contactTable.getColumns().add(lastNameColumn);
        contactTable.getColumns().add(companyColumn);
        contactTable.getColumns().add(emailAddressColumn);
        contactTable.getColumns().add(phoneNumberColumn);

        contactTable.getItems().add(Mazin);
        contactTable.getItems().add(Person);

        // contactTable.getColumns().add(lastNameColumn);

        // ObservableList<Map<String, Object>> items = FXCollections.<Map<String,
        // Object>>observableArrayList();

        // Map<String, Object> item1 = new HashMap<>();
        // item1.put("fName", Mazin.getFName());

        // Map<String, Object> item2 = new HashMap<>();
        // item2.put("fName", Mazin.getLName());

        // items.add(item1);
        // items.add(item2);

        // contactTable.getItems().addAll(items);

        menuPane.getChildren().add(contactTable);

        window.setScene(menuScene);

        window.show();
    }
}
