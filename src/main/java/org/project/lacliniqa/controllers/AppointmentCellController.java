package org.project.lacliniqa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.Appointment;
import org.project.lacliniqa.models.AppointmentType;
import org.project.lacliniqa.models.User;

import java.sql.SQLException;

import static org.project.lacliniqa.globals.constants.EventConstants.FETCH_APPOINTMENTS_LIST_EVENT_ID;

public class AppointmentCellController {
    public Label appointmentName;
    public Label appointmentDatetime;
    public Label appointmentType;
    public Label appointmentSubtype;

    private Appointment appointment;

    @FXML
    public void initialize(Appointment appointment) throws SQLException {
        this.appointment = new Appointment(appointment);

        appointmentDatetime.setText(appointment.getDatetime());
        appointmentType.setText(AppointmentType.getType(appointment.getTypeId()).getTypename());
        appointmentSubtype.setText(appointment.getSubtype());

        User user = User.getUserFromUid(appointment.getUserId());
        appointmentName.setText(user.getFname() + " " + user.getLname());
    }

    public void handleCancel(ActionEvent actionEvent) {
        String appointmentId = appointment.getAid();

        try {
            Appointment.deleteAppointment(appointmentId);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        EventsManager.getInstance().fireEvent(FETCH_APPOINTMENTS_LIST_EVENT_ID);
    }
}
