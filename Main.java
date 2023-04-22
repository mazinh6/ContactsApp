import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Main extends Application {
    public static void main(String[] banana) {

        launch(banana);

        // Read from a file on startup to generate the contacts
        // Any edits made in the GUI have to be written to the file (including new
        // contacts)

        // File csv = new File("contactlist.csv");

        // Scanner sc = new Scanner(csv);

        // System.out.println(csv.isDirectory());

        Contact Mazin = new Contact("Mazin", "Habib", "MHOP", "mh@example.com",
                123456789);

    }

    @Override
    public void start(Stage window) throws Exception {

        Pane menuPane = new Pane();

        Scene menuScene = new Scene(menuPane, 600, 600);

        TableView contactTable = new TableView();

        TableColumn<Contact, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("fName"));

        TableColumn<Contact, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lName"));

        contactTable.getColumns().add(firstNameColumn);
        contactTable.getColumns().add(lastNameColumn);

        contactTable.getItems().add(new Contact("Mazin", "Habib"));

        menuPane.getChildren().add(contactTable);

        window.setScene(menuScene);

        window.show();
    }
}
