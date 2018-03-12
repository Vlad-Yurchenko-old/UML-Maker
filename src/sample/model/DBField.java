package sample.model;

/**
 * Класс описывающий поле талицы в БД
 */
public class DBField {

    /**
     * Название поля
     */
    private String name = "";

    /**
     * Тип поля
     * {@value = {VARCHAR, CHAR, INT, DECIMAL, BINARY}}
     */
    private DB_TYPE type = null;

    /**
     * Описывает является ли поле первичным ключом
     */
    private boolean PK = false;

    /**
     * Описывает является ли автоинкрементируемым
     */
    private boolean AI = false;

    /**
     * Описывает является ли поле не нулевым
     */
    private boolean NN = false;

    /**
     * Описывает является ли поле уникальным
     */
    private boolean UQ = false;

    /**
     * Описывает является ли поле внешним ключом
     * <p>
     * Если не является, то поле равно {@value = null}
     */
    private FKConstraint FK = null;

    public DBField() {
    }

    public DBField(String name, DB_TYPE type) {
        this.name = name;
        this.type = type;
    }

    public DBField(String name, DB_TYPE type, boolean PK, boolean AI, boolean NN, boolean UQ) {
        this.name = name;
        this.type = type;
        this.PK = PK;
        this.AI = AI;
        this.NN = NN;
        this.UQ = UQ;
    }

    /**
     * Класс описывающий поле родительской таблицы
     * для данного поля
     */
    public static class FKConstraint {
        DBTable parentTable;
        DBField parentField;

        public FKConstraint(DBTable parentTable, DBField parentField) {
            this.parentTable = parentTable;
            this.parentField = parentField;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DB_TYPE getType() {
        return type;
    }

    public void setType(DB_TYPE type) {
        this.type = type;
    }

    public boolean isPK() {
        return PK;
    }

    public void setPK(boolean PK) {
        this.PK = PK;
    }

    public boolean isAI() {
        return AI;
    }

    public void setAI(boolean AI) {
        this.AI = AI;
    }

    public boolean isNN() {
        return NN;
    }

    public void setNN(boolean NN) {
        this.NN = NN;
    }

    public boolean isUQ() {
        return UQ;
    }

    public void setUQ(boolean UQ) {
        this.UQ = UQ;
    }

    public FKConstraint getFK() {
        return FK;
    }

    public void setFK(FKConstraint FK) {
        this.FK = FK;
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
