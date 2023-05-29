import javafx.beans.property.SimpleStringProperty;

public class Contact {

    private SimpleStringProperty fName;
    private SimpleStringProperty lName;
    private SimpleStringProperty cmpny;
    private SimpleStringProperty eAddress;
    private SimpleStringProperty pNumber;
    private String phoneNumberString;
    private String eAddressString;

    // must extend a cell factory to provide for input validation for all columns
    // when editing (commitEdit)
    // must extend a cell factory to provide custom rendering for one column
    // (updateItem)
    // one column needs to be a dynamic size property that can have any number of
    // values (i.e. many phone numbers but can be any column) [using a custom cell
    // factory here makes the most sense and can be paired with prev 2 requirements]

    public Contact(String firstName, String lastName, String company, String emailAddress, String phoneNumber) {
        this.fName = new SimpleStringProperty(firstName);
        this.lName = new SimpleStringProperty(lastName);
        this.cmpny = new SimpleStringProperty(company);
        this.eAddress = new SimpleStringProperty(emailAddress);
        this.pNumber = new SimpleStringProperty(phoneNumber);

        this.phoneNumberString = phoneNumber;
        this.eAddressString = emailAddress;

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

    public SimpleStringProperty pNumberProperty() {
        return pNumber;
    }

    public String getPhoneNumber() {
        return phoneNumberString;
    }

    public String getEmailAddress() {
        return eAddressString;
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