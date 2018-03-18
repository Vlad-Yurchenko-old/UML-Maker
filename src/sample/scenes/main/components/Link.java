package sample.scenes.main.components;

import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import sample.model.DBField;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс реализующий связь таблиц
 */
public class Link {

    private Table childTable, parentTable;
    private DBField parentPK;
    private CubicCurve cubicCurve;

    public Link(Table childTable, Table parentTable) {
        this.childTable = childTable;
        this.parentTable = parentTable;

        cubicCurve = new CubicCurve();
        cubicCurve.setFill(Color.TRANSPARENT);
        cubicCurve.setStroke(Color.BLACK);
        cubicCurve.setStrokeWidth(3);

        /**
         * Узнаем ориентацию таблиц относительно друг друга
         * */
        AtomicBoolean flag = new AtomicBoolean(childTable.getPKPos().getX() < parentTable.getPKPos().getX());

        /**
         * Установка поля первичного ключа родительской таблицы
         * */
        for (DBField dbField : childTable.getDBTable().getFKs()) {
            if (dbField.getFK().getParentTable() == parentTable.getDBTable()) {
                parentPK = dbField;
                break;
            }
        }

        /**
         * Установка координат кривой
         * */
        setControlCoor();
        setStartCoor(flag.get(), childTable.getFKsPos().get(parentPK));
        setEndCoor(flag.get(), parentTable.getPKPos());

        childTable.addEventHandler(EventType.ROOT, event -> {
            setControlCoor();
            flag.set(childTable.getPKPos().getX() < parentTable.getPKPos().getX());
            setStartCoor(flag.get(), childTable.getFKsPos().get(parentPK));
        });
        parentTable.addEventHandler(EventType.ROOT, event -> {
            setControlCoor();
            flag.set(childTable.getPKPos().getX() < parentTable.getPKPos().getX());
            setEndCoor(flag.get(), parentTable.getPKPos());
        });

        TablesField.getInstance().getChildren().add(cubicCurve);
    }

    /**
     * Установка координат контрольных точек
     */
    private void setControlCoor() {
        cubicCurve.setControlX1((childTable.getPKPos().getX() + parentTable.getPKPos().getX()) / 2);
        cubicCurve.setControlY1((childTable.getPKPos().getY() + parentTable.getPKPos().getY()) / 2);
        cubicCurve.setControlX2(cubicCurve.getControlX1());
        cubicCurve.setControlY2(cubicCurve.getControlY1());
    }

    /**
     * Установка координат начала кривой
     */
    private void setStartCoor(boolean flag, Point2D point2D) {
        if (flag) {
            cubicCurve.setStartX(point2D.getX() + Table.basicWidth);
        } else {
            cubicCurve.setStartX(point2D.getX());
        }
        cubicCurve.setStartY(point2D.getY());
    }

    /**
     * Установка координат конца кривой
     */
    private void setEndCoor(boolean flag, Point2D point) {
        if (flag) {
            cubicCurve.setEndX(point.getX());
        } else {
            cubicCurve.setEndX(point.getX() + Table.basicWidth);
        }
        cubicCurve.setEndY(point.getY());
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
