package sample.generator;

import sample.model.DBTable;

public abstract class SQLGenerator extends Generator{

    @Override
    public String generate(DBTable dbTable) {
        //TODO: переделать добавление новой записи
        dbTable.getFields().removeIf(dbField -> dbField.getName().compareTo("") == 0);

        String script = "";
        script += generateTableScript(dbTable);
        script += generateConstraintsScript(dbTable);
        return script;
    }

    protected abstract String generateTableScript(DBTable dbTable);

    protected abstract String generateConstraintsScript(DBTable dbTable);

}
