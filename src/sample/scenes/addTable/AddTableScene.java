package sample.scenes.addTable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.scenes.main.components.TablesField;

import java.io.IOException;

public class AddTableScene {

    private TablesField tablesField;

    public AddTableScene(TablesField tablesField) {
        this.tablesField = tablesField;
    }

    public void run() {
        VBox root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addTable.fxml"));
        fxmlLoader.setController(new AddTableSceneController(tablesField));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        root.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        Stage newWindow = new Stage();
        newWindow.setTitle("Создание новой таблицы");
        newWindow.setScene(new Scene(root));
        newWindow.show();
    }

}
