package org.project.lacliniqa.models;

import org.project.lacliniqa.managers.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.project.lacliniqa.globals.constants.DBConstants.*;

public class Appointment {
    private String aid = "";
    private String userId = "";
    private String typeId = "";
    private String subtype = "";
    private String datetime = "";

    public Appointment(String aid, String userId, String typeId, String subtype, String datetime) {
        this.aid = aid;
        this.userId = userId;
        this.typeId = typeId;
        this.subtype = subtype;
        this.datetime = datetime;
    }

    public Appointment() {}

    public Appointment(Appointment other)
    {
        this.aid = other.aid;
        this.userId = other.userId;
        this.typeId = other.typeId;
        this.subtype = other.subtype;
        this.datetime = other.datetime;
    }

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void saveAppointment() throws SQLException {
        List<String> params = Arrays.asList(aid, userId, typeId, subtype, datetime);

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
            appointment.setTypeId(resultSet.getString("typeId"));
            appointment.setSubtype(resultSet.getString("subtype"));
            appointment.setDatetime(resultSet.getString("datetime"));

            returnAppointments.add(appointment);
        }

        DBManager.getInstance().closedb();
        return  returnAppointments;
    }

    public static void deleteAppointment(String aid) throws SQLException
    {
        List<String> params = Arrays.asList(aid);

        DBManager.getInstance().connectdb();
        DBManager.getInstance().executeQuery(MYSQL_DELETE_APPOINTMENT_QUERY, params);
        DBManager.getInstance().closedb();
    }
}
