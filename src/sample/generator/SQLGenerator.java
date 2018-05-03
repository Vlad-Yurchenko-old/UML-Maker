package sample.generator;

import sample.model.DBTable;

public abstract class SQLGenerator extends Generator{

    @Override
    public String generate(DBTable dbTable) {
        //TODO: переделать добавление новой записи
        dbTable.getFields().removeIf(dbField -> dbField.getName().compareTo("") == 0);

        String entityCode = "";
        entityCode += generateTableScript(dbTable);
        entityCode += generateConstraintsScript(dbTable);
        return entityCode;
    }

    protected abstract String generateTableScript(DBTable dbTable);

    protected abstract String generateConstraintsScript(DBTable dbTable);

}
