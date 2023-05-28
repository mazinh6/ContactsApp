import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class EmailTableCell extends TextFieldTableCell<Contact, String> {

    public EmailTableCell() {
        super(new DefaultStringConverter());
    }

    @Override
    public void updateItem(String value, boolean empty) {
        super.updateItem(value, empty);

        setText(null);

        if (!empty) {
            setText(value);
        }
    }

    @Override
    public void commitEdit(String newValue) {

        // Regex from https://www.javatpoint.com/java-email-validation
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(newValue);

        if (!matcher.matches()) {
            cancelEdit();
        } else {
            super.commitEdit(newValue);
        }

    }
}
