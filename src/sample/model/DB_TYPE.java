package sample.model;

/**
 * Типы данных в БД
 */
public enum DB_TYPE {
    VARCHAR() {
        public Class getJavaAnalogue() {
            return String.class;
        }
        public String getMySQLAnalogue() {
            return "varchar(50)";
        }
    },
    CHAR {
        public Class getJavaAnalogue() {
            return char.class;
        }
        public String getMySQLAnalogue() {
            return "char(50)";
        }
    },
    INT {
        public Class getJavaAnalogue() {
            return int.class;
        }
        public String getMySQLAnalogue() {
            return "int";
        }
    },
    DECIMAL {
        public Class getJavaAnalogue() {
            return double.class;
        }
        public String getMySQLAnalogue() {
            return "decimal";
        }
    },
    BINARY {
        public Class getJavaAnalogue() {
            return byte.class;
        }
        public String getMySQLAnalogue() {
            return "binary(50)";
        }
    };

    public abstract Class getJavaAnalogue();
    public abstract String getMySQLAnalogue();
}
