package sample.generator;

import sample.model.Database;

public abstract class ConfigGenerator {

    public String generate(Database database) {
        //TODO: переделать добавление новой записи
        database.getTables().forEach(dbTable -> dbTable.getFields().removeIf(dbField -> dbField.getName().compareTo("") == 0));

        String config = "";
        config += generateOpenRootTag(database);
        config += generateTables(database);
        config += generateCloseRootTag(database);
        return config;
    }

    protected abstract String generateCloseRootTag(Database database);

    protected abstract String generateTables(Database database);

    protected abstract String generateOpenRootTag(Database database);

}
