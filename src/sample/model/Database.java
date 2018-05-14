package sample.model;

import java.util.ArrayList;

public class Database {

    private String name;
    private String userName;
    private String userPass;
    private String url;
    private String driver;

    private ArrayList<DBTable> tables;

    {
        name = "unnamed";
        userName = "root";
        userPass = "";
        url = "jdbc:mysql://localhost/unnamed";
        driver = "com.mysql.jdbc.Driver";
    }

    public Database() {
        tables = new ArrayList<>();
    }

    public Database(String name, String userName, String userPass, String url, String driver) {
        this.name = name;
        this.userName = userName;
        this.userPass = userPass;
        this.url = url;
        this.driver = driver;
    }

    public Database(String name, String userName, String userPass, String url, String driver, ArrayList<DBTable> tables) {
        this.name = name;
        this.userName = userName;
        this.userPass = userPass;
        this.url = url;
        this.driver = driver;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void addTable(DBTable table) {
        tables.add(table);
    }

    public ArrayList<DBTable> getTables() {
        return tables;
    }

    public void setTables(ArrayList<DBTable> dbTables) {
        this.tables = dbTables;
    }

    @Override
    public String toString() {
        return "Database{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", url='" + url + '\'' +
                ", driver='" + driver + "\'\n" +
                ", tables=" + tables +
                '}';
    }
}
