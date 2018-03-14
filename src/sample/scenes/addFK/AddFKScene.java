package sample.scenes.addFK;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.scenes.main.components.Table;

import java.io.IOException;

public class AddFKScene {

    private Table table;

    public AddFKScene(Table table) {
        this.table = table;
    }

    public void run() {
        VBox root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addFK.fxml"));
        fxmlLoader.setController(new AddFKSceneController(table.getDBTable()));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.setMaxSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        root.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        Stage newWindow = new Stage();
        newWindow.setResizable(false);
        newWindow.setTitle("Добавление внешнего ключа");
        newWindow.setScene(new Scene(root));
        newWindow.show();
    }


}
