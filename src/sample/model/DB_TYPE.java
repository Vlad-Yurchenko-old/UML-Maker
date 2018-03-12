package sample.model;

/**
 * Типы данных в БД
 */
public enum DB_TYPE {
    VARCHAR() {
        public Class getJavaAnalogue() {
            return String.class;
        }
    },
    CHAR {
        public Class getJavaAnalogue() {
            return char.class;
        }
    },
    INT {
        public Class getJavaAnalogue() {
            return int.class;
        }
    },
    DECIMAL {
        public Class getJavaAnalogue() {
            return double.class;
        }
    },
    BINARY {
        public Class getJavaAnalogue() {
            return byte.class;
        }
    };

    public abstract Class getJavaAnalogue();
}
