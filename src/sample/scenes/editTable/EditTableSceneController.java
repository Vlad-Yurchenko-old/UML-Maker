package sample.scenes.editTable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import sample.model.DBField;
import sample.model.DB_TYPE;
import sample.scenes.main.components.Table;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTableSceneController implements Initializable {

    private Table table;

    @FXML
    private Button btnSave;

    @FXML
    private TableView changeTableTableView;

    @FXML
    private TableColumn<DBField, String> fieldNameCol;

    @FXML
    private TableColumn<DBField, DB_TYPE> fieldTypeCol;

    @FXML
    private TableColumn<DBField, Boolean> fieldPKCol;

    @FXML
    private TableColumn<DBField, Boolean> fieldAICol;

    @FXML
    private TableColumn<DBField, Boolean> fieldNNCol;

    @FXML
    private TableColumn<DBField, Boolean> fieldUQCol;

    public EditTableSceneController(Table table) {
        this.table = table;
        this.table.getDBTable().getFields().add(new DBField("", null)); // строка таблицы для ввода нового поля для таблицы
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        changeTableTableView.setItems(FXCollections.observableArrayList(table.getDBTable().getFields()));

        btnSave.setOnMouseClicked(event -> {
            table.getDBTable().getFields().remove(table.getDBTable().getFields().size() - 1);
            table.refresh();
            ((Stage) this.btnSave.getScene().getWindow()).close();
        });
    }

    private void init() {
        fieldNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fieldTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        fieldPKCol.setCellValueFactory(new PropertyValueFactory<>("PK"));
        fieldAICol.setCellValueFactory(new PropertyValueFactory<>("AI"));
        fieldNNCol.setCellValueFactory(new PropertyValueFactory<>("NN"));
        fieldUQCol.setCellValueFactory(new PropertyValueFactory<>("UQ"));

        /**
         * Настройки ввода для названия поля
         * */
        fieldNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fieldNameCol.setOnEditCommit((TableColumn.CellEditEvent<DBField, String> event) -> {
            TablePosition<DBField, String> pos = event.getTablePosition();
            DBField dbField = event.getTableView().getItems().get(pos.getRow());
            if(pos.getRow() + 1 == table.getDBTable().getFields().size()) {
                DBField emptyField = new DBField("", null);
                table.getDBTable().getFields().add(emptyField);
                changeTableTableView.getItems().add(emptyField);
            }
            dbField.setName(event.getNewValue());
        });

        /**
         * Настройки ввода для типа поля
         * */
        ObservableList<DB_TYPE> genderList = FXCollections.observableArrayList(DB_TYPE.values());
        fieldTypeCol.setCellValueFactory(param -> {
            DB_TYPE gender = param.getValue().getType();
            return new SimpleObjectProperty<>(gender);
        });
        fieldTypeCol.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));
        fieldTypeCol.setOnEditCommit((TableColumn.CellEditEvent<DBField, DB_TYPE> event) -> {
            TablePosition<DBField, DB_TYPE> pos = event.getTablePosition();
            DBField person = event.getTableView().getItems().get(pos.getRow());
            if(pos.getRow() + 1 == table.getDBTable().getFields().size()) {
                DBField emptyField = new DBField("", null);
                table.getDBTable().getFields().add(emptyField);
                changeTableTableView.getItems().add(emptyField);
            }
            person.setType(event.getNewValue());
        });

        /**
         * Настройки для PK
         * */
        fieldPKCol.setCellValueFactory(param -> {
            DBField person = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isPK());
            booleanProp.addListener((observable, oldValue, newValue) -> person.setPK(newValue));
            return booleanProp;
        });
        fieldPKCol.setCellFactory(p -> {
            CheckBoxTableCell<DBField, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        /**
         * Настройки для AI
         * */
        fieldAICol.setCellValueFactory(param -> {
            DBField person = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isAI());
            booleanProp.addListener((observable, oldValue, newValue) -> person.setAI(newValue));
            return booleanProp;
        });
        fieldAICol.setCellFactory(p -> {
            CheckBoxTableCell<DBField, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        /**
         * Настройки для NN
         * */
        fieldNNCol.setCellValueFactory(param -> {
            DBField person = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isNN());
            booleanProp.addListener((observable, oldValue, newValue) -> person.setNN(newValue));
            return booleanProp;
        });
        fieldNNCol.setCellFactory(p -> {
            CheckBoxTableCell<DBField, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

        /**
         * Настройки для UQ
         * */
        fieldUQCol.setCellValueFactory(param -> {
            DBField person = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isUQ());
            booleanProp.addListener((observable, oldValue, newValue) -> person.setUQ(newValue));
            return booleanProp;
        });
        fieldUQCol.setCellFactory(p -> {
            CheckBoxTableCell<DBField, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });

    }

}
