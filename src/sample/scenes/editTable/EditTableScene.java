package sample.scenes.editTable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.scenes.main.components.Table;

import java.io.IOException;

public class EditTableScene {

    private Table table;

    public EditTableScene(Table table) {
        this.table = table;
    }

    public void run() {
        VBox root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editTable.fxml"));
        fxmlLoader.setController(new EditTableSceneController(table));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        root.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        Stage newWindow = new Stage();
        newWindow.setTitle("Редактирование таблицы " + table.getName());
        newWindow.setScene(new Scene(root));
        newWindow.show();
    }

}
