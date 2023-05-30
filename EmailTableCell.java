import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class EmailTableCell extends TextFieldTableCell<Contact, String> {

    // Constructor
    public EmailTableCell() {
        super(new DefaultStringConverter());
    }

    // Overridden method updateItem
    @Override
    public void updateItem(String value, boolean empty) {
        // updateItem from the super class is called
        super.updateItem(value, empty);

        // Setting the text to null
        setText(null);

        // If its not empty, then set the display text to the value
        if (!empty) {
            setText(value);
        }
    }

    // Overridden method commitEdit
    @Override
    public void commitEdit(String newValue) {

        // Regex from https://www.javatpoint.com/java-email-validation
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(newValue);

        // If it doesn't match the rules of the regex then cancelEdit();
        if (!matcher.matches()) {
            cancelEdit();
        } else {
            // If it does, commit the edit
            super.commitEdit(newValue);
        }

    }
}
