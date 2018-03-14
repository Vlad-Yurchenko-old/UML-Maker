package sample.scenes.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.scenes.main.components.TablesField;

import java.io.IOException;

public class MainScene {

    private Stage primaryStage;

    private VBox root;
    private TablesField field;

    public MainScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        field = TablesField.getInstance();
    }

    public void run() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        fxmlLoader.setController(new MainSceneController());
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().add(field);

        primaryStage.setTitle("UML Maker");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

}
