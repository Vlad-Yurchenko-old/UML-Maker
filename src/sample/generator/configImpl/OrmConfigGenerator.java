package sample.generator.configImpl;

import org.stringtemplate.v4.ST;
import sample.generator.ConfigGenerator;
import sample.model.DBField;
import sample.model.DBTable;
import sample.model.Database;

public class OrmConfigGenerator extends ConfigGenerator {

    private static final String openRootTagTemplate = "database name=\"<dbName>\" user=\"<user>\" pass=\"<password>\" url=\"<url>\" driver=\"<driver>\"";
    private static final String tableTagTemplate = "table name=\"<tableName>\" primary-key=\"<PK>\" entity_name=\"<entityName>\"";
    private static final String fieldTagTemplate = "field name=\"<fieldName>\" entity_field=\"<entityField>\"";


    @Override
    protected String generateOpenRootTag(Database database) {
        ST generator = new ST(openRootTagTemplate);
        generator.add("dbName", database.getName());
        generator.add("user", database.getUserName());
        generator.add("password", database.getUserPass());
        generator.add("url", database.getUrl());
        generator.add("driver", database.getDriver());
        return "<" + generator.render() + ">\n\n";
    }

    @Override
    protected String generateTables(Database database) {
        String tablesStr = "";
        for (DBTable dbTable : database.getTables()) {
            tablesStr += generateTable(dbTable);
        }
        return tablesStr;
    }

    private String generateTable(DBTable dbTable) {
        String tablesStr = "";
        ST generator = new ST(tableTagTemplate);
        tablesStr += "\t<";
        generator.add("tableName", dbTable.getName());
        for (DBField dbField : dbTable.getFields()) if (dbField.isPK()) generator.add("PK", dbField.getName());
        //TODO: заменить на имя (аналог java)
        generator.add("entityName", dbTable.getName());
        tablesStr += generator.render();
        tablesStr += ">\n";
        tablesStr += generateFields(dbTable);
        tablesStr += "\t</table>\n\n";
        return tablesStr;
    }

    private String generateFields(DBTable dbTable) {
        String fieldsStr = "";
        for (DBField dbField : dbTable.getFields()) {
            fieldsStr += generateField(dbField);
            fieldsStr += '\n';
        }
        return fieldsStr;
    }

    private String generateField(DBField dbField) {
        String fieldStr = "\t\t<";
        ST generator = new ST(fieldTagTemplate);
        generator.add("fieldName", dbField.getName());
        //TODO: заменить на имя (аналог java)
        generator.add("entityField", dbField.getName());
        fieldStr += generator.render();
        if (dbField.getFK() != null) {
            fieldStr += " foreign-key-entity=\"" + dbField.getFK().getParentTable().getName() + "\"";
        }
        fieldStr += "\\>";
        return fieldStr;
    }

    @Override
    protected String generateCloseRootTag(Database database) {
        return "</database>\n";
    }

}
