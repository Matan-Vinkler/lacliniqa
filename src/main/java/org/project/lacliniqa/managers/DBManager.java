package org.project.lacliniqa.managers;

import java.sql.*;

import static org.project.lacliniqa.globals.constants.DBConstants.*;

public class DBManager {
    private Connection dbConnection;

    public static DBManager instance = null;

    public void connectdb() throws SQLException {
        dbConnection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
    }

    public void executeQuery(String mysql_query) throws SQLException {
        Statement statement = dbConnection.createStatement();
        statement.execute(mysql_query);
    }

    public ResultSet executeQueryWithResult(String mysql_query) throws SQLException {
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(mysql_query);

        return resultSet;
    }

    public void closedb() throws SQLException {
        dbConnection.close();
    }

    public static DBManager getInstance() {
        if(instance == null) {
            instance = new DBManager();
        }

        return instance;
    }
}
