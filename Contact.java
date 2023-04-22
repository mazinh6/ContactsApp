import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Contact {

    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty cmpny;
    private SimpleStringProperty eAddress;
    private SimpleIntegerProperty pNumber;

    public Contact(String firstName, String lastName, String company, String emailAddress, int phoneNumber) {
        this.fName = new SimpleStringProperty(firstName);
        this.lName = new SimpleStringProperty(lastName);
        this.cmpny = new SimpleStringProperty(company);
        this.eAddress = new SimpleStringProperty(emailAddress);
        this.pNumber = new SimpleIntegerProperty(phoneNumber);

    }

    public Contact(String firstName, String lastName) {
        this.fName = new SimpleStringProperty(firstName);
        this.lName = new SimpleStringProperty(lastName);
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

    public SimpleIntegerProperty pNumberProperty() {
        return pNumber;
    }

    // public String getCompany() {
    // return cmpny;
    // }

    // public String getEmailAddress() {
    // return eAddress;
    // }

    // public int getPhoneNumber() {
    // return pNumber;
    // }

    // Each contact must have between 5-7 properties
}