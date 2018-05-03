package sample.generator;

import sample.model.DBTable;

public abstract class CodeGenerator extends Generator {

    @Override
    public String generate(DBTable dbTable) {
        //TODO: переделать добавление новой записи
        dbTable.getFields().removeIf(dbField -> dbField.getName().compareTo("") == 0);

        String entityCode = "";
        entityCode += generateHeader(dbTable);
        entityCode += generateFields(dbTable);
        entityCode += generateBaseConstructor(dbTable);
        entityCode += generateAllFieldsConstructor(dbTable);
        entityCode += generateGetters(dbTable);
        entityCode += generateSetters(dbTable);
        entityCode += generateToString(dbTable);
        entityCode += generateFooter(dbTable);
        return entityCode;
    }

    protected abstract String generateHeader(DBTable dbTable);

    protected abstract String generateFields(DBTable dbTable);

    protected abstract String generateBaseConstructor(DBTable dbTable);

    protected abstract String generateAllFieldsConstructor(DBTable dbTable);

    protected abstract String generateGetters(DBTable dbTable);

    protected abstract String generateSetters(DBTable dbTable);

    protected abstract String generateToString(DBTable dbTable);

    protected abstract String generateFooter(DBTable dbTable);

}
