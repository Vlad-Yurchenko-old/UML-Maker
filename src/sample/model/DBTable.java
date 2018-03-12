package sample.model;

import java.util.ArrayList;


/**
 * Класс представляющий таблицу БД
 * */
public class DBTable {

    /**
     * Название таблицы
     * */
    private String name;

    /**
     * Поля таблицы
     * */
    private ArrayList<DBField> fields;

    public DBTable() {
        name = "";
        fields = new ArrayList<>();
    }

    public DBTable(String name) {
        this.name = name;
        fields = new ArrayList<>();
    }

    public DBTable(String name, ArrayList<DBField> fields) {
        this.name = name;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получение всех первичных ключей таблицы
     * */
    public ArrayList<DBField> getPKs() {
        ArrayList<DBField> PKs = new ArrayList<>();
        fields.forEach(dbField -> {
            if (dbField.isPK()) PKs.add(dbField);
        });
        return PKs;
    }

    /**
     * Получение всех полей, являющимися внешними ключами
     * */
    public ArrayList<DBField> getFKs() {
        ArrayList<DBField> FKs = new ArrayList<>();
        fields.forEach(dbField -> {
            if (dbField.getFK() != null) FKs.add(dbField);
        });
        return FKs;
    }

    public ArrayList<DBField> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", fields=" + fields +
                '}';
    }

}
