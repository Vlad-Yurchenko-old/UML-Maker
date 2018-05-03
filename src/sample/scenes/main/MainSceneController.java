package sample.scenes.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import sample.generator.codeImpl.JavaEntityCodeGenerator;
import sample.generator.sqlImpl.MySQLGenerator;
import sample.scenes.addTable.AddTableScene;
import sample.scenes.main.components.TablesField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    @FXML private Button toolbar_add_table_btn;
    @FXML private MenuItem export_java;
    @FXML private MenuItem export_mysql;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toolbar_add_table_btn.setOnMouseClicked(event -> {
            new AddTableScene().run();
        });

        export_java.setOnAction(event -> {
            JavaEntityCodeGenerator javaGenerator = new JavaEntityCodeGenerator();
            TablesField.getInstance().getTables().forEach(table -> System.out.println(javaGenerator.generate(table.getDBTable())));
        });

        export_mysql.setOnAction(event -> {
            MySQLGenerator mySQLGenerator = new MySQLGenerator();
            TablesField.getInstance().getTables().forEach(table -> System.out.println(mySQLGenerator.generate(table.getDBTable())));
        });
    }

}
