package org.project.lacliniqa.managers;

import org.project.lacliniqa.MainApplication;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.List;

import static org.project.lacliniqa.globals.constants.BackupDBConstants.*;
import static org.project.lacliniqa.globals.constants.DBConstants.*;

public class DBManager {
    private Connection mainConnection;
    private Connection backupConnection;

    public static DBManager instance = null;

    public void connectdb() throws SQLException {
        mainConnection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        backupConnection = DriverManager.getConnection(SQLITE_URL);
    }

    public void executeQuery(String mysql_query_pattern, List<String> params) throws SQLException {
        PreparedStatement statement = mainConnection.prepareStatement(mysql_query_pattern);
        PreparedStatement backupStatement = backupConnection.prepareStatement(mysql_query_pattern);

        if(params != null) {
            for (int i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i));
                backupStatement.setString(i + 1, params.get(i));
            }
        }

        statement.execute();
        backupStatement.execute();
    }

    public void executeQuery(String mysql_query_pattern) throws SQLException {
        executeQuery(mysql_query_pattern, null);
    }

    public ResultSet executeQueryWithResult(String mysql_query_pattern, List<String> params) throws SQLException {
        PreparedStatement statement = mainConnection.prepareStatement(mysql_query_pattern);

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
        mainConnection.close();
        backupConnection.close();
    }

    public static DBManager getInstance() {
        if(instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    public void backupUsers() throws SQLException {
        Statement statement = mainConnection.createStatement();
        statement.execute(MYSQL_DROP_USERS);
        statement.execute(MYSQL_CREATE_USERS);

        statement = backupConnection.createStatement();
        ResultSet set = statement.executeQuery(SQLITE_FECTH_USERS);

        String sql_query = MYSQL_INSERT_USERS;
        int i = 0;

        while(set.next()) {
            if(i > 0) {
                sql_query += ",";
            }
            ++i;

            String uid = set.getString("uid");
            String email = set.getString("email");
            String fname = set.getString("fname");
            String lname = set.getString("lname");
            String phone = set.getString("phone");
            String id = set.getString("id");
            String password = set.getString("password");
            boolean is_admin = set.getBoolean("is_admin");

            int is_admin_int = is_admin ? 1 : 0;

            sql_query += String.format(MYSQL_INSERT_USERS_VALUES_FORMAT, uid, email, fname, lname, phone, id, password, is_admin_int);
        }

        sql_query += ";";

        if(i > 0) {
            statement = mainConnection.createStatement();
            statement.execute(sql_query);
        }
    }

    public void backupDates() throws SQLException {
        Statement statement = mainConnection.createStatement();
        statement.execute(MYSQL_DROP_DATES);
        statement.execute(MYSQL_CREATE_DATES);

        statement = backupConnection.createStatement();
        ResultSet set = statement.executeQuery(SQLITE_FECTH_DATES);

        String sql_query = MYSQL_INSERT_DATES;
        int i = 0;

        while(set.next()) {
            if(i > 0) {
                sql_query += ",";
            }
            ++i;

            String did = set.getString("did");
            String datetime = set.getString("datetime");

            sql_query += String.format(MYSQL_INSERT_DATES_VALUES_FORMAT, did, datetime);
        }

        sql_query += ";";

        if(i > 0) {
            statement = mainConnection.createStatement();
            statement.execute(sql_query);
        }
    }

    public void backupTypes() throws SQLException {
        Statement statement = mainConnection.createStatement();
        statement.execute(MYSQL_DROP_TYPES);
        statement.execute(MYSQL_CREATE_TYPES);

        statement = backupConnection.createStatement();
        ResultSet set = statement.executeQuery(SQLITE_FECTH_TYPES);

        String sql_query = MYSQL_INSERT_TYPES;
        int i = 0;

        while(set.next()) {
            if(i > 0) {
                sql_query += ",";
            }
            ++i;

            String tid = set.getString("tid");
            String typename = set.getString("typename");
            int price = set.getInt("price");

            sql_query += String.format(MYSQL_INSERT_TYPES_VALUES_FORMAT, tid, typename, price);
        }

        sql_query += ";";

        if(i > 0) {
            statement = mainConnection.createStatement();
            statement.execute(sql_query);
        }
    }

    public void backupAppointments() throws SQLException {
        Statement statement = mainConnection.createStatement();
        statement.execute(MYSQL_DROP_APPTS);
        statement.execute(MYSQL_CREATE_APPTS);

        statement = backupConnection.createStatement();
        ResultSet set = statement.executeQuery(SQLITE_FECTH_APPTS);

        String sql_query = MYSQL_INSERT_APPTS;
        int i = 0;

        while(set.next()) {
            if(i > 0) {
                sql_query += ",";
            }
            ++i;

            String aid = set.getString("aid");
            String userId = set.getString("userId");
            String typeId = set.getString("typeId");
            String subtype = set.getString("subtype");
            String datetime = set.getString("datetime");
            int paid = set.getBoolean("paid") ? 1 : 0;

            sql_query += String.format(MYSQL_INSERT_APPTS_VALUES_FORMAT, aid, userId, typeId, subtype, datetime, paid);
        }

        sql_query += ";";

        if(i > 0) {
            statement = mainConnection.createStatement();
            statement.execute(sql_query);
        }
    }
}
