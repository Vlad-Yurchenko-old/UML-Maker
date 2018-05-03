package sample.generator.sqlImpl;

import sample.generator.SQLGenerator;
import sample.model.DBTable;
import org.stringtemplate.v4.ST;

public class MySQLGenerator extends SQLGenerator {

    private static final String createTableTemplate =
            "create table <tableName> (\n" +
                    "<vars:{v | \t" +
                    "<v.name> <v.type.MySQLAnalogue>" +
                    "<if(v.PK)> primary key<endif>" +
                    "<if(v.NN)> not null<endif>" +
                    "<if(v.AI)> auto_increment<endif>" +
                    "<if(v.UQ)> unique<endif>" +
                    "};separator=\", \n\">)\n";

    private static final String addConstraintTemplate =
            "<vars:{dbField | " +
                    "<if(dbField.FK)>" +
                    "alter table <tableName> " +
                    "add constraint fk_<dbField.name> " +
                    "foreign key (<dbField.name>) REFERENCES <dbField.FK.parentTable.name>(<dbField.FK.parentField.name>);" +
                    "<endif>" +
                    "\n}>\n";

    @Override
    protected String generateTableScript(DBTable dbTable) {
        ST generator = new ST(createTableTemplate);
        generator.add("tableName", dbTable.getName());
        generator.add("vars", dbTable.getFields());
        return generator.render();
    }

    @Override
    protected String generateConstraintsScript(DBTable dbTable) {
        ST generator = new ST(addConstraintTemplate);
        generator.add("tableName", dbTable.getName());
        generator.add("vars", dbTable.getFields());
        return generator.render();
    }


}
