package org.project.lacliniqa.models;

import org.project.lacliniqa.managers.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.project.lacliniqa.globals.constants.DBConstants.MYSQL_GET_AVAILABLE_DATES_QUERY;

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
}
