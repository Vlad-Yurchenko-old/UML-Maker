package sample.scenes.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.scenes.addTable.AddTableScene;
import sample.scenes.main.components.TablesField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    @FXML
    private Button toolbar_add_table_btn;

    private TablesField tablesField;

    public MainSceneController(TablesField tablesField) {
        this.tablesField = tablesField;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toolbar_add_table_btn.setOnMouseClicked(event -> {
            new AddTableScene(tablesField).run();
        });
    }
}
