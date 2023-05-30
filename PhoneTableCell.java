import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class PhoneTableCell extends TextFieldTableCell<Contact, String> {

    // Formatted String
    private String formattedN;

    // Constructor
    public PhoneTableCell() {
        super(new DefaultStringConverter());
    }

    // Overridden method updateItem
    public void updateItem(String value, boolean empty) {
        // superclass method call and set the display text to null
        super.updateItem(value, empty);
        setText(null);

        // formats the number to appear as a formatted phone number on screen, does not
        // physically change the number in the file
        if (!empty && !value.equals("-1") && value.length() != 0) {
            formattedN = value.substring(0, 3) + "-";
            formattedN += value.substring(3, 6) + "-";
            formattedN += value.substring(6, 10);

            value = formattedN;
            setText(value);
        }

    }

    // Overridden method cancelEdit
    public void cancelEdit() {
        super.cancelEdit();
        setText(formattedN);
    }

    // Validation
    // Overridden method commitEdit
    @Override
    public void commitEdit(String newValue) {

        // If the chars are not valid numbers, then cancel the edit
        if (newValue.length() > 0) {
            for (int i = 0; i < newValue.length(); i++) {
                if (newValue.charAt(i) < 48 || newValue.charAt(i) > 57 || newValue.length() != 10) {
                    cancelEdit();
                }
            }
        }
        // Since they are valid commit the edit
        super.commitEdit(newValue);
    }
}
