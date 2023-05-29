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

    // Contact Mazin = new Contact("Mazin", "Habib", "MHOP", "mh@example.com",
    // "123456789");

    // Contact Person = new Contact("ABC", "DEF", "HIJ", "mh@example.com",
    // "987654321");

    static Contact contacts;

    static String[] values;

    static int numberOfNestedColumns = 0;

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

    private TextField newNumber = new TextField();

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

        menuPane.getChildren().add(newNumber);
        newNumber.relocate(550, 550);
        newNumber.setMaxWidth(100);

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

            // TESTING: seems to work right now?????? no way
            for (int i = 0; i < numberOfNestedColumns; i++) {
                person.addPNumber(new SimpleStringProperty("-1"));
            }

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
            // int row = contactTable.getSelectionModel().getSelectedIndex();

            Contact deleteRow = (Contact) contactTable.getSelectionModel().getSelectedItem();

            // Remove the Contact with the index row
            if (deleteRow != null)
                contactTable.getItems().remove(deleteRow);

        });

        Button addPhoneNumber = new Button("Add Phone Number");
        menuPane.getChildren().add(addPhoneNumber);
        addPhoneNumber.relocate(620, 550);
        addPhoneNumber.setMinWidth(90);

        addPhoneNumber.setOnAction(event -> {

            numberOfNestedColumns++;

            TableColumn<Contact, String> nestedColumn = new TableColumn<>("Phone Number");
            phoneNumberColumn.getColumns().add(nestedColumn);

            for (int i = 0; i < contactTable.getItems().size(); i++) {
                Contact c = (Contact) contactTable.getItems().get(i);

                c.addPNumber(new SimpleStringProperty("-1"));

            }

            nestedColumn.setCellValueFactory(cellData -> {
                return cellData.getValue().getPNumberAt(numberOfNestedColumns - 1);
            });
            nestedColumn.setCellFactory(list -> new PhoneTableCell());
            nestedColumn.setEditable(true);

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

                        //
                        String firstName = ((Contact) r).fNameProperty().get();
                        String lastName = ((Contact) r).lNameProperty().get();
                        String company = ((Contact) r).cmpnyProperty().get();
                        String eAddress = ((Contact) r).eAddressProperty().get();
                        String pNumber = ((Contact) r).pNumberProperty().get();

                        System.out.println(firstName + "," + lastName + "," + company + "," + eAddress + "," + pNumber);
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

                if (values.length > 5 && firstTime) {

                    for (int i = 0; i < contacts.getPNumberList().size(); i++) {
                        preCreatePNumberCols(i);
                    }
                    firstTime = false;
                }
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

    private void preCreatePNumberCols(int i) {
        System.out.println("Print" + (i + 1));
        TableColumn<Contact, String> nestedColumn = new TableColumn<>("Phone Number");
        ((TableColumn<Contact, String>) contactTable.getColumns().get(4)).getColumns().add(nestedColumn);
        nestedColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().getPNumberAt(i);
        });
        nestedColumn.setCellFactory(list -> new PhoneTableCell());
        nestedColumn.setEditable(true);
        numberOfNestedColumns++;
    }

    // A private void method called addContact with the parameter of type Contact
    // called person. Its function is to add person to the contactTable
    private void addContact(Contact Person) {

        // Regex from https://www.javatpoint.com/java-email-validation
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Person.getEmailAddress());

        // Validation for when a contact is added, the phone number should be a valid
        // phone number, and same with the email address
        Alert alert = new Alert(AlertType.INFORMATION);
        String contentText = "Invalid Field Entered";
        alert.setContentText(contentText);
        if (Person.getPhoneNumber().length() != 10) {
            alert.show();
        } else if (!matcher.matches()) {
            alert.show();
        } else {
            // Add person to contactTable
            contactTable.getItems().add(Person);
        }

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
