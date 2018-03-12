package sample.scenes.addTable;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.scenes.main.components.TablesField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTableSceneController implements Initializable{

    private TablesField tablesField;

    @FXML
    private TextField textField;

    @FXML
    private Button button;

    public AddTableSceneController(TablesField tablesField) {
        this.tablesField = tablesField;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setOnMouseClicked(event -> {
            if (textField.getText().compareTo("") != 0) {
                tablesField.addNewTable(textField.getText());
            }
            ((Stage) this.button.getScene().getWindow()).close();
        });
    }
}
