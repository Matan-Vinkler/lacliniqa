package org.project.lacliniqa.managers;

import java.sql.*;
import java.util.List;

import static org.project.lacliniqa.globals.constants.DBConstants.*;

public class DBManager {
    private Connection dbConnection;

    public static DBManager instance = null;

    public void connectdb() throws SQLException {
        dbConnection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
    }

    public void executeQuery(String mysql_query_pattern, List<String> params) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(mysql_query_pattern);

        if(params != null) {
            for (int i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i));
            }
        }

        statement.execute();
    }

    public void executeQuery(String mysql_query_pattern) throws SQLException {
        executeQuery(mysql_query_pattern, null);
    }

    public ResultSet executeQueryWithResult(String mysql_query_pattern, List<String> params) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement(mysql_query_pattern);

        if(params != null) {
            for (int i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i));
            }
        }

        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public ResultSet executeQueryWithResult(String mysql_query_pattern) throws SQLException {
        return executeQueryWithResult(mysql_query_pattern, null);
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
