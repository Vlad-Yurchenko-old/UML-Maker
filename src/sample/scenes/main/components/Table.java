package sample.scenes.main.components;

import com.sun.javafx.geom.Vec2f;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import sample.model.DBTable;
import sample.scenes.editTable.EditTableScene;

import java.util.ArrayList;

public class Table extends Group {

    private ArrayList<Label> rowsLabels;
    private DBTable table;

    private final int basicWidth = 150;
    private final int basicHeight = 50;

    /**
     * Координаты при перемещении
     * */
    private Vec2f start;
    private ArrayList<Vec2f> startNode;

    public Table(String name) {
        rowsLabels = new ArrayList<>();
        table = new DBTable(name);

        rowsLabels.add(createLabel(name, 0, 0));
        rowsLabels.get(0).setStyle("-fx-background-color: black; -fx-text-fill: white");

        getChildren().add(new Label()); //TODO: костыль
        getChildren().addAll(rowsLabels);
    }

    private Label createLabel(String text, int x, int y) {
        Label label = LabelBuilder.create()    // создание билдера для Label
                .text(text)             // текстовое значение
                .prefWidth(basicWidth)                // возможная ширина
                .prefHeight(basicHeight)                // возможная высота
                .alignment(Pos.CENTER)         // выравнивание содержимого по центру
                .layoutX(x) // задание  коорд. Х
                .layoutY(y)  // задание  коорд. Y
                .style("-fx-background-color: #e8e8e8;-fx-border-color: black;\n" +
                        "    -fx-border-width: 2;")  // зарисуем фон в оранжевый
                .build(); // создадим из билдера сам объект класса Label

        label.setOnMousePressed(event -> {
            start = new Vec2f((float) event.getSceneX(), (float) event.getSceneY());
            startNode = new ArrayList<>();
            for (Node field : rowsLabels) {
                startNode.add(new Vec2f((float) field.getLayoutX(), (float) field.getLayoutY()));
            }
        });

        label.setOnMouseDragged(event -> {
            int vecX = (int) (start.x - event.getSceneX());
            int vecY = (int) (start.y - event.getSceneY());
            for (int i = 0; i < rowsLabels.size(); i++) {
                rowsLabels.get(i).setLayoutX(startNode.get(i).x - vecX);
                rowsLabels.get(i).setLayoutY(startNode.get(i).y - vecY);
            }
        });

        label.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                //TODO: неприятное место (костыль или норма?)
                new EditTableScene(this).run();
            }
        });

        return label;
    }

    public String getName() {
        return table.getName();
    }

    public DBTable getDBTable() {
        return table;
    }

    public void refresh() {
        for(int i = rowsLabels.size() - 1; i > 0; i--) {
            getChildren().remove(rowsLabels.get(i));
            rowsLabels.remove(i);
        }
        table.getFields().forEach(dbField -> addLabel(dbField.getName(), dbField.getType().name()));
    }

    private void addLabel(String name, String type) {
        Label label = createLabel(name + " (" + type + ")",
                (int) rowsLabels.get(0).getLayoutX(),
                (int) rowsLabels.get(rowsLabels.size() - 1).getLayoutY() + 50);
        rowsLabels.add(label);
        getChildren().add(label);
    }

}
