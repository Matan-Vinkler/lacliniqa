package org.project.lacliniqa.models;

import org.project.lacliniqa.managers.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.project.lacliniqa.globals.constants.DBConstants.MYSQL_GET_APPOINTMENT_TYPES_QUERY;
import static org.project.lacliniqa.globals.constants.DBConstants.MYSQL_GET_APPOINTMENT_TYPE_QUERY;

public class AppointmentType {
    String tid;
    String typename;

    public AppointmentType() {}

    public AppointmentType(String tid, String typename) {
        this.tid = tid;
        this.typename = typename;
    }

    public AppointmentType(AppointmentType other) {
        this.tid = other.tid;
        this.typename = other.typename;
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

    public static AppointmentType getType(String tid) throws SQLException {
        List<String> params = Arrays.asList(tid);

        DBManager.getInstance().connectdb();
        ResultSet set = DBManager.getInstance().executeQueryWithResult(MYSQL_GET_APPOINTMENT_TYPE_QUERY, params);

        AppointmentType appointmentType = new AppointmentType();
        set.next();
        appointmentType.setTid(set.getString("tid"));
        appointmentType.setTypename(set.getString("typename"));

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

            ret.add(appointmentType);
        }

        DBManager.getInstance().closedb();

        return  ret;
    }
}
