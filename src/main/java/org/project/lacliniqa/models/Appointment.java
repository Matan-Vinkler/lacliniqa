package org.project.lacliniqa.models;

import org.project.lacliniqa.managers.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.project.lacliniqa.globals.constants.DBConstants.MYSQL_GET_APPOINTMENTS_FROM_UID_QUERY;
import static org.project.lacliniqa.globals.constants.DBConstants.MYSQL_SAVE_APPOINTMENT_QUERY;

public class Appointment {
    private String aid = "";
    private String userId = "";
    private String type = "";
    private String subtype = "";
    private String date = "";
    private String time = "";

    public Appointment(String aid, String userId, String type, String subtype, String date, String time) {
        this.aid = aid;
        this.userId = userId;
        this.type = type;
        this.subtype = subtype;
        this.date = date;
        this.time = time;
    }

    public Appointment() {}

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void saveAppointment() throws SQLException {
        List<String> params = Arrays.asList(aid, userId, type, subtype, date, time);

        DBManager.getInstance().connectdb();
        DBManager.getInstance().executeQuery(MYSQL_SAVE_APPOINTMENT_QUERY, params);
        DBManager.getInstance().closedb();
    }

    public static ArrayList<Appointment> getAppointments(String userId) throws SQLException {
        List<String> params = Arrays.asList(userId);

        DBManager.getInstance().connectdb();
        ResultSet resultSet = DBManager.getInstance().executeQueryWithResult(MYSQL_GET_APPOINTMENTS_FROM_UID_QUERY, params);

        ArrayList<Appointment> returnAppointments = new ArrayList<>();

        while (resultSet.next()) {
            Appointment appointment = new Appointment();

            appointment.setAid(resultSet.getString("aid"));
            appointment.setUserId(resultSet.getString("userId"));
            appointment.setType(resultSet.getString("type"));
            appointment.setSubtype(resultSet.getString("subtype"));
            appointment.setDate(resultSet.getString("date"));
            appointment.setTime(resultSet.getString("time"));

            returnAppointments.add(appointment);
        }

        DBManager.getInstance().closedb();
        return  returnAppointments;
    }
}
