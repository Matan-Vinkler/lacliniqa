package org.project.lacliniqa.models;

import org.project.lacliniqa.managers.DBManager;
import org.sqlite.core.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.project.lacliniqa.globals.constants.DBConstants.*;

public class AvailableDate {
    String did;
    String datetime;

    public AvailableDate() {}

    public AvailableDate(String did, String datetime) {
        this.did = did;
        this.datetime = datetime;
    }

    public AvailableDate(AvailableDate other) {
        this.did = other.did;
        this.datetime = other.datetime;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void saveDate() throws SQLException {
        List<String> params = Arrays.asList(did, datetime);

        DBManager.getInstance().connectdb();
        DBManager.getInstance().executeQuery(MYSQL_SAVE_AVAILABLE_DATE_QUERY, params);
        DBManager.getInstance().closedb();
    }

    public static ArrayList<AvailableDate> getDates() throws SQLException {
        DBManager.getInstance().connectdb();

        ResultSet resultSet = DBManager.getInstance().executeQueryWithResult(MYSQL_GET_AVAILABLE_DATES_QUERY);

        ArrayList<AvailableDate> returnArray = new ArrayList<>();
        while(resultSet.next()) {
            AvailableDate availableDate = new AvailableDate();

            availableDate.setDid(resultSet.getString("did"));
            availableDate.setDatetime(resultSet.getString("datetime"));

            returnArray.add(availableDate);
        }

        DBManager.getInstance().closedb();

        return returnArray;
    }

    public static void delete(String datetime) throws SQLException {
        List<String> params = Arrays.asList(datetime);

        DBManager.getInstance().connectdb();
        DBManager.getInstance().executeQuery(MYSQL_DELETE_DATE_QUERY, params);
        DBManager.getInstance().closedb();
    }
}
