package sample.generator;

import sample.model.DBTable;
import sample.model.Database;

public abstract class Generator {

    public abstract String generate(DBTable dbTable);

}
