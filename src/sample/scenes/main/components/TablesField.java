package sample.scenes.main.components;

import javafx.scene.Group;
import javafx.scene.shape.Line;

import java.util.ArrayList;

/**
* Рабочая область содержащая множество таблиц
 * (размер определяется относительно размера окна)
* */
public class TablesField extends Group {

    private ArrayList<Table> tables;

    public TablesField() {
        tables = new ArrayList<>();
    }

    public void addNewTable(String name) {
        Table table = new Table(name);
        tables.add(table);
        getChildren().add(table);
    }

}
