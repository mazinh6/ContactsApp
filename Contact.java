public class Contact {

    private String fName;
    private String lName;
    private String cmpny;
    private String eAddress;
    private int pNumber;

    public Contact(String firstName, String lastName, String company, String emailAddress, int phoneNumber) {
        this.fName = firstName;
        this.lName = lastName;
        this.cmpny = company;
        this.eAddress = emailAddress;
        this.pNumber = phoneNumber;

        System.out.println(fName + lName + cmpny + eAddress + pNumber);
    }

    public Contact(String firstName, String lastName) {
        this.fName = firstName;
        this.lName = lastName;
    }

    // Each contact must have between 5-7 properties
}