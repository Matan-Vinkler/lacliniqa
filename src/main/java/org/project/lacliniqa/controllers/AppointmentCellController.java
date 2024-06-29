package org.project.lacliniqa.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.Appointment;
import org.project.lacliniqa.models.AppointmentType;
import org.project.lacliniqa.models.User;

import java.sql.SQLException;

import static org.project.lacliniqa.globals.constants.EventConstants.FETCH_APPOINTMENTS_LIST_EVENT_ID;
import static org.project.lacliniqa.globals.constants.MsgConstants.PAID_MSG;
import static org.project.lacliniqa.globals.constants.MsgConstants.UNPAID_MSG;

public class AppointmentCellController {
    public Label appointmentName;
    public Label appointmentDatetime;
    public Label appointmentType;
    public Label appointmentSubtype;
    public Label paidLabel;

    private Appointment appointment;

    @FXML
    public void initialize(Appointment appointment) throws SQLException {
        this.appointment = new Appointment(appointment);

        appointmentDatetime.setText(appointment.getDatetime());
        appointmentType.setText(AppointmentType.getType(appointment.getTypeId()).getTypename());
        appointmentSubtype.setText(appointment.getSubtype());

        User user = User.getUserFromUid(appointment.getUserId());
        appointmentName.setText(user.getFname() + " " + user.getLname());

        if(appointment.isPaid()) {
            paidLabel.setText(PAID_MSG);
            paidLabel.setTextFill(Color.GREEN);
        }
        else {
            paidLabel.setText(UNPAID_MSG);
            paidLabel.setTextFill(Color.RED);
        }
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
