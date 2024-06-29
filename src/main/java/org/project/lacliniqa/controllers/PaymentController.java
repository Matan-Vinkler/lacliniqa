package org.project.lacliniqa.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import org.project.lacliniqa.globals.utils.Paypal;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.Appointment;
import org.project.lacliniqa.models.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.project.lacliniqa.globals.constants.EventConstants.FETCH_APPOINTMENTS_LIST_EVENT_ID;
import static org.project.lacliniqa.globals.constants.EventConstants.GOTO_APPOINTMENTS_EVENT_ID;
import static org.project.lacliniqa.globals.constants.MsgConstants.*;

public class PaymentController {
    public MFXTextField creditNumberField;
    public MFXTextField expireYearField;
    public MFXTextField expireMonthField;
    public MFXTextField ccvNumberField;
    public MFXButton paymentButton;
    public Label paymentStatusLabel;

    private ArrayList<Appointment> appointmentsToPay;
    int total_price = 0;

    public void handleFetchPayment(ActionEvent actionEvent) {
        try {
            appointmentsToPay = Appointment.getAppointments(User.getCurrentUser().getUid(), true);
            total_price = 0;

            for(Appointment appointmentToPay: appointmentsToPay) {
                total_price += appointmentToPay.getType().getPrice();
            }

            paymentStatusLabel.setText(TOTAL_TO_PAY_MSG + total_price);

            if(total_price > 0) {
                creditNumberField.setDisable(false);
                expireYearField.setDisable(false);
                expireMonthField.setDisable(false);
                ccvNumberField.setDisable(false);
                paymentButton.setDisable(false);
            }
        }
        catch (SQLException e) {
            paymentStatusLabel.setText(FETCH_ERROR_MSG);
            e.printStackTrace();
        }
    }

    public void handlePayment(ActionEvent actionEvent) {
        try {
            String creditNumber = creditNumberField.getText();
            int expireYear = Integer.parseInt(expireYearField.getText());
            int expireMonth = Integer.parseInt(expireMonthField.getText());
            int ccv = Integer.parseInt(ccvNumberField.getText());

            if(Paypal.processPayment(creditNumber, expireYear, expireMonth, ccv, total_price)) {
                for(Appointment paidAppointment: appointmentsToPay) {
                    paidAppointment.setPaid(true);
                    paidAppointment.updateAppointment();
                }

                appointmentsToPay.clear();
                total_price = 0;

                paymentStatusLabel.setText(PAYMENT_SUCCESS_MSG);

                creditNumberField.setDisable(true);
                expireYearField.setDisable(true);
                expireMonthField.setDisable(true);
                ccvNumberField.setDisable(true);
                paymentButton.setDisable(true);

                EventsManager.getInstance().fireEvent(FETCH_APPOINTMENTS_LIST_EVENT_ID);
                EventsManager.getInstance().fireEvent(GOTO_APPOINTMENTS_EVENT_ID);
            }
            else {
                paymentStatusLabel.setText(PAYMENT_ERROR_MSG);
            }
        }
        catch (NumberFormatException e) {
            paymentStatusLabel.setText(INVALID_CREDENTIALS_MSG);
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
