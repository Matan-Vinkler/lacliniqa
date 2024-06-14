package org.project.lacliniqa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.project.lacliniqa.models.Appointment;
import org.project.lacliniqa.models.User;

import java.sql.SQLException;

public class AppointmentCellController {
    public Label appointmentName;
    public Label appointmentDate;
    public Label appointmentTime;
    public Label appointmentType;
    public Label appointmentSubtype;

    @FXML
    public void initialize(Appointment appointment) throws SQLException {
        appointmentDate.setText(appointment.getDate());
        appointmentTime.setText(appointment.getTime());
        appointmentType.setText(appointment.getType());
        appointmentSubtype.setText(appointment.getSubtype());

        User user = User.getUserFromUid(appointment.getUserId());
        appointmentName.setText(user.getFname() + " " + user.getLname());
    }
}
