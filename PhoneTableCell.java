import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class PhoneTableCell extends TextFieldTableCell<Contact, String> {

    public PhoneTableCell() {
        super(new DefaultStringConverter());
    }

    private String formattedN;

    public void updateItem(String value, boolean empty) {
        super.updateItem(value, empty);
        setText(null);

        // formats the number to appear as a formatted phone number on screen, does not
        // physically change the number in the file
        if (!empty && !value.equals("-1")) {
            formattedN = value.substring(0, 3) + "-";
            formattedN += value.substring(3, 6) + "-";
            formattedN += value.substring(6, 10);

            value = formattedN;
            setText(value);
        }

    }

    public void cancelEdit() {
        super.cancelEdit();

        setText(formattedN);
    }

    // Validation
    @Override
    public void commitEdit(String newValue) {

        // If the chars are not valid numbers, then cancel the edit
        for (int i = 0; i < newValue.length(); i++) {
            if (newValue.charAt(i) < 48 || newValue.charAt(i) > 57 || newValue.length() != 10) {
                cancelEdit();
            }
        }

        super.commitEdit(newValue);
    }
}
