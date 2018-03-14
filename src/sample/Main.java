package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.scenes.main.MainScene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        new MainScene(primaryStage).run();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
