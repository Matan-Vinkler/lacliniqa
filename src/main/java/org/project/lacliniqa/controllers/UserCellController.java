package org.project.lacliniqa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.project.lacliniqa.models.Appointment;
import org.project.lacliniqa.models.User;

import java.sql.SQLException;

import static org.project.lacliniqa.globals.constants.MsgConstants.*;

public class UserCellController {
    public Label userEmail;
    public Label userFullName;
    public Label userId;
    public Label userPhone;
    public Label paidAllLabel;
    public Label isAdminLabel;

    @FXML
    public void initialize(User user) throws SQLException {
        userEmail.setText(user.getEmail());
        userFullName.setText(user.getFname() + " " + user.getLname());
        userId.setText(user.getId());
        userPhone.setText(user.getPhone());

        if(user.isAdmin()) {
            isAdminLabel.setText(IS_ADMIN_MSG);
            isAdminLabel.setTextFill(Color.GREEN);
        }
        else {
            isAdminLabel.setText(ISNT_ADMIN_MSG);
            isAdminLabel.setTextFill(Color.RED);
        }

        int n_appointments = 0, n_paid_appointments = 0;
        for(Appointment appointment: Appointment.getAppointments(user.getUid())) {
            n_appointments++;

            if(appointment.isPaid()) {
                n_paid_appointments++;
            }
        }

        if(n_paid_appointments < n_appointments) {
            paidAllLabel.setTextFill(Color.RED);
        }
        else {
            paidAllLabel.setTextFill(Color.GREEN);
        }

        paidAllLabel.setText(String.format(PAID_COUNT_MSG, n_paid_appointments, n_appointments));
    }
}
