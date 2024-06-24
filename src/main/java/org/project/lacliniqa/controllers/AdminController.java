package org.project.lacliniqa.controllers;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import org.project.lacliniqa.globals.utils.DateFormatter;
import org.project.lacliniqa.globals.utils.Validators;
import org.project.lacliniqa.models.AppointmentType;
import org.project.lacliniqa.models.AvailableDate;

import java.sql.SQLException;
import java.util.UUID;
import java.util.function.UnaryOperator;

import static org.project.lacliniqa.globals.constants.MsgConstants.*;

public class AdminController {
    public MFXTextField typenameField;
    public Spinner priceSpinner;
    public Label addTypeStatusLabel;

    public MFXDatePicker appointmentDatePicker;
    public MFXTextField appointmentHourField;
    public MFXTextField appointmentMinuteField;
    public Label addDateStatusLabel;

    @FXML
    public void initialize() {
        priceSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("\\d?")) {
                return change;
            }
            return null;
        };

        appointmentHourField.delegateSetTextFormatter(new TextFormatter<String>(filter));
        appointmentMinuteField.delegateSetTextFormatter(new TextFormatter<String>(filter));
    }

    public void handleAddType(ActionEvent actionEvent) {
        String typename = typenameField.getText();
        int price = (int) priceSpinner.getValueFactory().getValue();
        String tid = UUID.randomUUID().toString();

        AppointmentType appointmentType = new AppointmentType(tid, typename, price);

        try {
            appointmentType.saveType();
            addTypeStatusLabel.setText(SAVED_MSG);
            resetAppointmentTypeFields();
        } catch (SQLException e) {
            addTypeStatusLabel.setText(SAVE_ERROR_MSG);
            e.printStackTrace();
        }
    }

    public void handleAddDatetime(ActionEvent actionEvent) {
        String dateFromPicker = appointmentDatePicker.getText();
        int hour = Integer.parseInt(appointmentHourField.getText());
        int minute = Integer.parseInt(appointmentMinuteField.getText());

        if(!Validators.validateTime(hour, minute)) {
            addDateStatusLabel.setText(INVALID_CREDENTIALS_MSG);
        }

        String datetime = DateFormatter.formatToMysql(dateFromPicker, hour, minute);
        String did = UUID.randomUUID().toString();

        AvailableDate availableDate = new AvailableDate(did, datetime);

        try {
            availableDate.saveDate();
            addDateStatusLabel.setText(SAVED_MSG);
            resetAvailableDateFields();
        } catch (SQLException e) {
            addDateStatusLabel.setText(SAVE_ERROR_MSG);
            e.printStackTrace();
        }
    }

    void resetAppointmentTypeFields() {
        typenameField.setText("");
        priceSpinner.getValueFactory().setValue(0);
    }

    void resetAvailableDateFields() {
        appointmentDatePicker.setText("");
        appointmentHourField.setText("00");
        appointmentMinuteField.setText("00");
    }
}
