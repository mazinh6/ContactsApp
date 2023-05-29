import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

public class NameTableCell extends TextFieldTableCell<Contact, String> {

    public NameTableCell() {
        super(new DefaultStringConverter());
    }

    private String formatted;

    @Override
    public void updateItem(String value, boolean empty) {
        super.updateItem(value, empty);

        setText(null);

        // This will ensure the first letter of a name appears as uppercase on the
        // screen, it will not change it physically in the file
        if (!empty) {
            char ch1 = Character.toUpperCase(value.charAt(0));

            formatted = ch1 + value.substring(1);
            value = formatted;
            setText(value);
        }
    }

    public void cancelEdit() {
        super.cancelEdit();

        setText(formatted);
    }

    @Override
    public void commitEdit(String newValue) {

        // Iterate over the characters in the new string
        // If any of them are not any type of letter (upper/lower), cancelEdit()
        for (int i = 0; i < newValue.length(); i++) {
            if (newValue.charAt(i) < 65 || (newValue.charAt(i) > 90 && newValue.charAt(i) <= 96)
                    || newValue.charAt(i) > 122) {
                cancelEdit();
            }
        }

        super.commitEdit(newValue);

    }
}
