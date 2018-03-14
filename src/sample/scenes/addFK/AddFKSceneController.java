package sample.scenes.addFK;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import sample.model.DBField;
import sample.model.DBTable;
import sample.scenes.main.components.Link;
import sample.scenes.main.components.Table;
import sample.scenes.main.components.TablesField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddFKSceneController implements Initializable {

    private DBTable dbTable;

    @FXML
    private ComboBox<DBField> field;

    @FXML
    private ComboBox<DBTable> parentTable;

    @FXML
    private ComboBox<DBField> parentField;

    @FXML
    private Button btnAddFK;

    public AddFKSceneController(DBTable dbTable) {
        this.dbTable = dbTable;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<DBTable> tables = new ArrayList<>();
        TablesField.getInstance().getTables().forEach(table -> {
            if (table.getDBTable() != dbTable) {
                tables.add(table.getDBTable());
            }
        });

        field.getItems().addAll(dbTable.getFields());

        parentTable.getItems().addAll(tables);
        parentTable.setOnAction(event -> {
            parentField.getItems().clear();
            parentField.getItems().addAll(parentTable.getValue().getFields().stream().filter(DBField::isPK).collect(Collectors.toList()));
        });

        btnAddFK.setOnMouseClicked(event -> {
            field.getValue().setFK(new DBField.FKConstraint(parentTable.getValue(), parentField.getValue()));

            //TODO: refactor
            Table child = null, parent = null;
            ArrayList<Table> allTables = TablesField.getInstance().getTables();
            for (Table table : allTables) {
                if (table.getDBTable() == dbTable) {
                    child = table;
                }
                if (table.getDBTable() == parentTable.getValue()) {
                    parent = table;
                }
            }
            TablesField.getInstance().getLinks().add(new Link(child, parent));

            ((Stage) this.btnAddFK.getScene().getWindow()).close();
        });
    }

}