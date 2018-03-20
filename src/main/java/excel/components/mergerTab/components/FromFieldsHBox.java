package excel.components.mergerTab.components;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * @author Alexander Diachenko.
 */
public class FromFieldsHBox extends HBox {

    private TextField fromId;
    private TextField fromField;

    public FromFieldsHBox(final String numericRegex) {
        init(numericRegex);
    }

    private void init(String numericRegex) {
        fromId = new TextField();
        fromId.setPromptText("Enter 'from' id");
        fromId.setFocusTraversable(true);
        fromId.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(numericRegex)) {
                fromId.setText(oldValue);
            }
        });
        fromField = new TextField();
        fromField.setPromptText("Enter 'from' field");
        fromField.setFocusTraversable(true);
        fromField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(numericRegex)) {
                fromField.setText(oldValue);
            }
        });
        getChildren().addAll(fromId, fromField);
    }

    public TextField getFromId() {
        return fromId;
    }

    public TextField getFromField() {
        return fromField;
    }
}
