package sample.generator.codeImpl;

import sample.generator.CodeGenerator;
import sample.model.DBField;
import sample.model.DBTable;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;

public class JavaEntityCodeGenerator extends CodeGenerator {

    private static final String fieldTemplate = "<vars:{v | \tprivate <if(v.FK)><v.FK.parentTable.name><else><v.type.JavaAnalogue.SimpleName><endif> <v.name>;\n}>";
    private static final String setterTemplate = "\tpublic void set<bigName>(<type> <smallName>) {\n\t\t this.<smallName> = <smallName>;\n\t}\n\n";
    private static final String getterTemplate = "\tpublic <type> get<bigName>() {\n\t\t return <smallName>;\n\t}\n\n";
    private static final String constructorTemplate = "\tpublic <className> (<vars:{v | <if(v.FK)><v.FK.parentTable.name><else><v.type.JavaAnalogue.SimpleName><endif> <v.name>};separator=\", \">) {\n <vars:{v | \t\tthis.<v.name> = <v.name>;\n}>\t}\n\n";
    private static final String toStringTemplate = "\t@Override \n\tpublic String toString() {\n\t\treturn \"<className>{<vars:{v | \" <v.name> = \" + <v.name>};separator=\" + \',\' + \n\t\t\t\t\">\" + \"}\";\n\t}\n";

    private ST generator;

    @Override
    protected String generateHeader(DBTable dbTable) {
        return "public class " + dbTable.getName() + " {\n\n";
    }

    @Override
    protected String generateFields(DBTable dbTable) {
        generator = new ST(fieldTemplate);
        generator.add("vars", dbTable.getFields().toArray());
        return generator.render() + "\n";
    }

    @Override
    protected String generateBaseConstructor(DBTable dbTable) {
        generator = new ST(constructorTemplate);
        generator.add("className", dbTable.getName());
        generator.add("vars", new ArrayList<>().toArray());
        return generator.render();
    }

    @Override
    protected String generateAllFieldsConstructor(DBTable dbTable) {
        generator = new ST(constructorTemplate);
        generator.add("className", dbTable.getName());
        generator.add("vars", dbTable.getFields().toArray());
        return generator.render();
    }

    @Override
    protected String generateSetters(DBTable dbTable) {
        return generateGETSETByTemplate(setterTemplate, dbTable);
    }

    @Override
    protected String generateGetters(DBTable dbTable) {
        return generateGETSETByTemplate(getterTemplate, dbTable);
    }

    @Override
    protected String generateToString(DBTable dbTable) {
        generator = new ST(toStringTemplate);
        generator.add("className", dbTable.getName());
        generator.add("vars", dbTable.getFields().toArray());
        return generator.render();
    }

    @Override
    protected String generateFooter(DBTable dbTable) {
        return "\n}";
    }

    private String generateGETSETByTemplate(String template, DBTable dbTable) {
        StringBuilder result = new StringBuilder();
        for (DBField dbField : dbTable.getFields()) {
            generator = new ST(template);
            if(dbField.getFK() == null) {
                generator.add("type", dbField.getType().getJavaAnalogue().getSimpleName());
            } else {
                generator.add("type", dbField.getFK().getParentTable().getName());
            }
            generator.add("smallName", dbField.getName());
            generator.add("bigName", firstUpperCase(dbField.getName()));
            result.append(generator.render());
        }
        return result.toString();
    }

    private String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
