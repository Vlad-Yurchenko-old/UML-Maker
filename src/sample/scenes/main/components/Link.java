package sample.scenes.main.components;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import sample.model.DBField;

/**
 * Класс реализующий связь таблиц
 */
public class Link {

    private Table childTable, parentTable;
    private CubicCurve cubicCurve;
    private TablesField tablesField;

    public Link(Table childTable, Table parentTable) {
        this.childTable = childTable;
        this.parentTable = parentTable;
        tablesField = TablesField.getInstance();

        Point2D point2D;

        cubicCurve = new CubicCurve();
        cubicCurve.setFill(Color.TRANSPARENT);
        cubicCurve.setStroke(Color.BLACK);
        cubicCurve.setStrokeWidth(3);

        for (DBField dbField : childTable.getDBTable().getFKs()) {
            if (dbField.getFK().getParentTable() == parentTable.getDBTable()) {
                point2D = childTable.getFKsPos().get(dbField);
                cubicCurve.setStartX(point2D.getX());
                cubicCurve.setStartY(point2D.getY());
                break;
            }
        }

        point2D = parentTable.getPKPos();
        cubicCurve.setEndX(point2D.getX());
        cubicCurve.setEndY(point2D.getY());

        /**
         * Узнаем ориентацию таблиц относительно друг друга
         * */
        boolean flag = childTable.getPKPos().getX() < parentTable.getPKPos().getX();

        childTable.setOnMouseDragged(event -> {
            Point2D point;
            for (DBField dbField : childTable.getDBTable().getFKs()) {
                if (dbField.getFK().getParentTable() == parentTable.getDBTable()) {
                    point = childTable.getFKsPos().get(dbField);
                    if(flag) {
                        cubicCurve.setStartX(point.getX() + Table.basicWidth);
                        cubicCurve.setStartY(point.getY());
                    } else {
                        cubicCurve.setStartX(point.getX());
                        cubicCurve.setStartY(point.getY());
                    }
                    break;
                }
            }
            //polygon.getPoints().clear();
            //polygon.getPoints().addAll(x, y, x - 5, y + 5, x - 5, y - 5);
        });
        parentTable.setOnMouseDragged(event -> {
            Point2D point = parentTable.getPKPos();
            if(flag) {
                cubicCurve.setEndX(point.getX());
                cubicCurve.setEndY(point.getY());
            } else {
                cubicCurve.setEndX(point.getX() + Table.basicWidth);
                cubicCurve.setEndY(point.getY() + Table.basicWidth);
            }


            //polygon.getPoints().clear();
            //polygon.getPoints().addAll(x, y, x - 5, y + 5, x - 5, y - 5);
        });

        /*cubicCurve.setControlX1(0);
        cubicCurve.setControlY1(150);
        cubicCurve.setControlX2(200);
        cubicCurve.setControlY2(300);*/
        tablesField.getChildren().add(cubicCurve);
    }

    public Table getChildTable() {
        return childTable;
    }

    public void setChildTable(Table childTable) {
        this.childTable = childTable;
    }

    public Table getParentTable() {
        return parentTable;
    }

    public void setParentTable(Table parentTable) {
        this.parentTable = parentTable;
    }

    public CubicCurve getCubicCurve() {
        return cubicCurve;
    }

    public void setCubicCurve(CubicCurve cubicCurve) {
        this.cubicCurve = cubicCurve;
    }

    @Override
    public String toString() {
        return "Link{" +
                "childTable=" + childTable +
                ", parentTable=" + parentTable + '}';
    }
}
