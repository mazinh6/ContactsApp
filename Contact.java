import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Contact {

    // Declaring the instance variables
    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty cmpny;
    private SimpleStringProperty eAddress;
    private SimpleStringProperty pNumber;
    private ArrayList<SimpleStringProperty> pNumberList;
    private String phoneNumberString;
    private String eAddressString;

    // Constructor for creating a contact
    public Contact(String firstName, String lastName, String company, String emailAddress, String phoneNumber) {
        // Initializing main properties
        this.fName = new SimpleStringProperty(firstName);
        this.lName = new SimpleStringProperty(lastName);
        this.cmpny = new SimpleStringProperty(company);
        this.eAddress = new SimpleStringProperty(emailAddress);
        this.pNumber = new SimpleStringProperty(phoneNumber);

        // Initializing the phone number list and adding the first phone number
        this.pNumberList = new ArrayList<SimpleStringProperty>();
        pNumberList.add(pNumber);

        // Strings for accessing these properties
        this.phoneNumberString = phoneNumber;
        this.eAddressString = emailAddress;

    }

    // Getter and setter methods:

    public void addPNumber(SimpleStringProperty newNumber) {
        pNumberList.add(newNumber);
    }

    public ArrayList<SimpleStringProperty> getPNumberList() {
        return pNumberList;
    }

    public SimpleStringProperty getPNumberAt(int index) {
        return pNumberList.get(index);
    }

    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    public SimpleStringProperty lNameProperty() {
        return lName;
    }

    public SimpleStringProperty cmpnyProperty() {
        return cmpny;
    }

    public SimpleStringProperty eAddressProperty() {
        return eAddress;
    }

    public SimpleStringProperty pNumberProperty() {
        return pNumber;
    }

    public String getPhoneNumber() {
        return phoneNumberString;
    }

    public String getEmailAddress() {
        return eAddressString;
    }
}