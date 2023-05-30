/* Contacts App */
/* Code can be found at: https://github.com/mazinh6/ContactsApp */
/* The csv file should be cleared, or have valid content in it before running the program for the first time */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Main extends Application {

    // Creating a contact that gets used when reading the csv
    static Contact contacts;

    // For reading content in the csv
    static String[] values;

    // the number of nested phone columns (initialized to 0 at the start)
    static int numberOfNestedColumns = 0;

    // Creating a private TableView object called contactTable
    private TableView<Contact> contactTable = new TableView<>();

    // Creating the TextField objects
    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField companyName = new TextField();
    private TextField email = new TextField();
    private TextField number = new TextField();

    // Creating the parent column for the phone numbers
    TableColumn<Contact, String> parentColumn = new TableColumn<>("Phone Numbers");

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
        firstNameColumn.setCellFactory(listOfCells -> new NameTableCell());

        // Creating the "Last Name" column
        TableColumn<Contact, String> lastNameColumn = new TableColumn<Contact, String>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lName"));
        lastNameColumn.setCellFactory(listOfCells -> new NameTableCell());

        // Creating the "Company" column
        TableColumn<Contact, String> companyColumn = new TableColumn<Contact, String>("Company");
        companyColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("cmpny"));
        companyColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Creating the "Email Address" column
        TableColumn<Contact, String> emailAddressColumn = new TableColumn<Contact, String>("Email Address");
        emailAddressColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("eAddress"));
        emailAddressColumn.setCellFactory(listOfCells -> new EmailTableCell());

        // Creating the "Phone Number" column
        TableColumn<Contact, String> phoneNumberColumn = new TableColumn<Contact, String>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("pNumber"));
        phoneNumberColumn.setCellFactory(listOfCells -> new PhoneTableCell());

        // Adding all of the columns to the TableView Object created earlier called
        // "contactTable"
        contactTable.getColumns().add(firstNameColumn);
        contactTable.getColumns().add(lastNameColumn);
        contactTable.getColumns().add(companyColumn);
        contactTable.getColumns().add(emailAddressColumn);
        contactTable.getColumns().add(parentColumn);

        // Adding the phone column to the nested parent column of phone numbers and
        // incrememnting the number of nested columns
        parentColumn.getColumns().add(phoneNumberColumn);
        numberOfNestedColumns++;

        // Relocating and setting table attributes
        contactTable.relocate(165, 105);
        contactTable.setMinWidth(470);

        // firstName attributes and location
        firstName.setPromptText("First Name");
        firstName.relocate(20, 20);
        firstName.setMaxWidth(100);
        menuPane.getChildren().add(firstName);

        // lastName attributes and location
        lastName.setPromptText("Last Name");
        lastName.relocate(130, 20);
        lastName.setMaxWidth(100);
        menuPane.getChildren().add(lastName);

        // companyName attributes and location
        companyName.setPromptText("Company");
        companyName.relocate(240, 20);
        companyName.setMaxWidth(120);
        menuPane.getChildren().add(companyName);

        // emailAddress attributes and location
        email.setPromptText("Email Address");
        email.relocate(370, 20);
        email.setMinWidth(200);
        menuPane.getChildren().add(email);

        // phoneNumber attributes and location
        number.setPromptText("Phone Number");
        number.relocate(580, 20);
        number.setMaxWidth(100);
        menuPane.getChildren().add(number);

        // The button for adding contacts
        Button addButton = new Button("Add");
        addButton.relocate(690, 20);
        addButton.setMinWidth(90);
        menuPane.getChildren().add(addButton);

        // Button press Event Handler
        addButton.setOnAction(event -> {

            // Create a contact object with the values in the TextFields to be the values of
            // the properties of the Contact
            Contact person = new Contact(firstName.getText(), lastName.getText(), companyName.getText(),
                    email.getText(), number.getText());

            // Add the phone numbers to the list of that contact based on how many columns
            // there are in the table
            for (int i = 1; i < numberOfNestedColumns; i++) {
                person.addPNumber(new SimpleStringProperty("-1"));
            }

            // Call the addContact method that adds the contact to the table and validates a
            // few fields
            addContact(person);

            // Call the clearFields method that clears the TextFields
            clearFields();
        });

        // Delete button for deleting contacts
        Button deleteButton = new Button("Delete");
        deleteButton.relocate(690, 60);
        deleteButton.setMinWidth(90);
        menuPane.getChildren().add(deleteButton);

        // Delete button press Event Handler
        deleteButton.setOnAction(event -> {

            // Getting the selection
            Contact deleteRow = (Contact) contactTable.getSelectionModel().getSelectedItem();

            // Remove the Contact with the index row
            if (deleteRow != null)
                contactTable.getItems().remove(deleteRow);

        });

        // The button for adding a new phone number
        Button addPhoneNumber = new Button("Add Phone Number");
        addPhoneNumber.relocate(620, 550);
        addPhoneNumber.setMinWidth(90);
        menuPane.getChildren().add(addPhoneNumber);

        // Add Phone Number Event Handler
        addPhoneNumber.setOnAction(event -> {

            // Incrementing the nested columns count
            numberOfNestedColumns++;

            // Creating the nestedColumn
            TableColumn<Contact, String> nestedColumn = new TableColumn<>("Phone Number");

            // Iterating over the size of the rows in the table
            for (int i = 0; i < contactTable.getItems().size(); i++) {
                // For each contact, add a phone number to their list with the value of -1
                Contact c = (Contact) contactTable.getItems().get(i);
                c.addPNumber(new SimpleStringProperty("-1"));
            }

            // Setting the cell value factory to -1
            nestedColumn.setCellValueFactory(cellData -> {
                return cellData.getValue().getPNumberAt(numberOfNestedColumns - 1);
            });
            // cell factory is a phoneTableCell();
            nestedColumn.setCellFactory(list -> new PhoneTableCell());
            nestedColumn.setEditable(true); // Editable
            parentColumn.getColumns().add(nestedColumn); // Adding to the parentColumn
        });

        // Button to be used to access instructions
        Button instructions = new Button("Instructions");
        menuPane.getChildren().add(instructions);
        instructions.relocate(50, 550);

        // Button press EVENT HANDLER
        instructions.setOnAction(event -> {

            // Create an alert
            Alert instruct = new Alert(AlertType.INFORMATION);

            // Set the header text to "Instructions"
            instruct.setHeaderText("Instructions");

            // Set the content of the alert to the instructions to be displayed
            instruct.setContentText(
                    "To create a new contact, fill in the text fields and press \"Add\" \n\nTo delete a contact, select it and press \"Delete\" \n\nYou can add more phone number columns by pressing \"Add Phone Number\" \n\nTo edit a property of a contact, double click on it \n\nPhone numbers must be 10 digit numbers, and email addresses must conform to rules of email addresses");

            // Show the alert
            instruct.show();

        });

        // Event Handler for when the application is closed
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {

                // Path for the file
                String path = "contactlist.csv";

                // Try-catch to catch IOException
                try {
                    // Creating a new instance of BufferedWriter, false in the filewriter to ensure
                    // the whole contact list is overwritten
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));

                    // Enhanced for loop that repeats over the rows in the table
                    for (Object r : contactTable.getItems()) {

                        // The information from the contact
                        String firstName = ((Contact) r).fNameProperty().get();
                        String lastName = ((Contact) r).lNameProperty().get();
                        String company = ((Contact) r).cmpnyProperty().get();
                        String eAddress = ((Contact) r).eAddressProperty().get();

                        // Writing the contact information to the file
                        writer.write(
                                firstName + "," + lastName + "," + company + "," + eAddress + ",");
                        for (int i = 0; i < ((Contact) r).getPNumberList().size(); i++) {
                            if (i != ((Contact) r).getPNumberList().size() - 1) {
                                writer.write(((Contact) r).getPNumberAt(i).get() + ",");
                            } else {
                                writer.write(((Contact) r).getPNumberAt(i).get());
                            }
                        }
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
            boolean firstTime = true;

            // Reading each line of the file with a while loop (will repeat as long as the
            // lines in the file are not null)
            while ((line = br.readLine()) != null) {
                // Adding each string to an array of strings
                values = line.split(",");

                // Defining the contact object for one line in the csv file
                contacts = new Contact(values[0], values[1], values[2], values[3], values[4]);

                for (int i = 5; i < values.length; i++) {
                    contacts.addPNumber(new SimpleStringProperty(values[i]));
                }

                // Adding the contact to be displayed in the GUI
                contactTable.getItems().add(contacts);

                // If there are multiple phone numbers, then we want to create the nestedColumns
                // for them
                if (values.length > 5 && firstTime) {

                    for (int i = 1; i < contacts.getPNumberList().size(); i++) {
                        preCreatePNumberCols(i); // Creating the nested columns
                    }
                    firstTime = false;
                }
            }
            br.close();

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

    private void preCreatePNumberCols(int i) {
        // Creating and adding the nestedColumn to the parentColumn
        TableColumn<Contact, String> nestedColumn = new TableColumn<>("Phone Number");
        parentColumn.getColumns().add(nestedColumn);
        nestedColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getPNumberAt(i);
        });
        nestedColumn.setCellFactory(list -> new PhoneTableCell());
        nestedColumn.setEditable(true);
        // case for if there is only 1 number from the file
        if (i > 0)
            numberOfNestedColumns++; // Incrementing the number of nested columns
    }

    // A private void method called addContact with the parameter of type Contact
    // called person. Its function is to add person to the contactTable
    private void addContact(Contact person) {

        // Regex from https://www.javatpoint.com/java-email-validation
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(person.getEmailAddress());

        // Validation for when a contact is added, certain things should be met
        // regarding the phone number, names, and email address
        Alert alert = new Alert(AlertType.INFORMATION);
        String contentText = "Invalid Field Entered";
        alert.setContentText(contentText);
        if (person.getPhoneNumber().length() != 10) {
            alert.show();
        } else if (!matcher.matches()) {
            alert.show();
        } else if (!(person.fNameProperty().getValue().length() == 0 || person.fNameProperty().getValue() == null
                || person.lNameProperty().getValue().length() == 0 || person.lNameProperty().getValue() == null)) {
            if (person.fNameProperty().getValue().charAt(0) == ' ') {
                alert.show();
            } else if (person.lNameProperty().getValue().charAt(0) == ' ') {
                alert.show();
            }
        } else {
            boolean valid = true;
            // Add person to contactTable
            for (int i = 0; i < person.getPhoneNumber().length(); i++) {
                if (person.getPhoneNumber().charAt(i) < 48 || person.getPhoneNumber().charAt(i) > 57) {
                    alert.show();
                    valid = false;
                    break;
                }
            }
            if (valid) {
                contactTable.getItems().add(person);
            }
        }

    }

    // A private void method called ClearFields that doesn't have any parameters.
    // Its function is to clear the TextFields
    private void clearFields() {
        firstName.clear();
        lastName.clear();
        companyName.clear();
        email.clear();
        number.clear();

    }

}
