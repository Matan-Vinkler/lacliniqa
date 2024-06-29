package org.project.lacliniqa.models;

import org.project.lacliniqa.managers.DBManager;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.project.lacliniqa.globals.constants.DBConstants.*;

public class AppointmentType {
    String tid;
    String typename;
    int price;

    public AppointmentType() {}

    public AppointmentType(String tid, String typename, int price) {
        this.tid = tid;
        this.typename = typename;
        this.price = price;
    }

    public AppointmentType(AppointmentType other) {
        this.tid = other.tid;
        this.typename = other.typename;
        this.price = other.price;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void saveType() throws SQLException {
        List<String> params = Arrays.asList(tid, typename, Integer.toString(price));

        DBManager.getInstance().connectdb();
        DBManager.getInstance().executeQuery(MYSQL_SAVE_APPOINTMENT_TYPE_QUERY, params);
        DBManager.getInstance().closedb();
    }

    public static AppointmentType getType(String tid) throws SQLException {
        List<String> params = Arrays.asList(tid);

        DBManager.getInstance().connectdb();
        ResultSet set = DBManager.getInstance().executeQueryWithResult(MYSQL_GET_APPOINTMENT_TYPE_QUERY, params);

        AppointmentType appointmentType = new AppointmentType();
        set.next();
        appointmentType.setTid(set.getString("tid"));
        appointmentType.setTypename(set.getString("typename"));
        appointmentType.setPrice(set.getInt("price"));

        DBManager.getInstance().closedb();

        return appointmentType;
    }

    public static ArrayList<AppointmentType> getTypes() throws SQLException {
        DBManager.getInstance().connectdb();
        ResultSet set = DBManager.getInstance().executeQueryWithResult(MYSQL_GET_APPOINTMENT_TYPES_QUERY);

        ArrayList<AppointmentType> ret = new ArrayList<>();
        while(set.next()) {
            AppointmentType appointmentType = new AppointmentType();

            appointmentType.setTid(set.getString("tid"));
            appointmentType.setTypename(set.getString("typename"));
            appointmentType.setPrice(set.getInt("price"));

            ret.add(appointmentType);
        }

        DBManager.getInstance().closedb();

        return  ret;
    }
}
