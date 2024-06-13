package org.project.lacliniqa.controllers;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import org.project.lacliniqa.models.Appointment;
import org.project.lacliniqa.models.User;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
import java.util.function.UnaryOperator;

import static org.project.lacliniqa.globals.constants.MsgConstants.*;
import static org.project.lacliniqa.globals.constants.ValidatorConstants.*;

public class SetAppointmentController {
    public MFXTextField appointmentTypeField;
    public MFXTextField appointmentSubtypeField;
    public MFXTextField appointmentHourField;
    public MFXTextField appointmentMinuteField;
    public MFXDatePicker appointmentDatePicker;
    public Label submitStatusLabel;

    @FXML
    public void initialize() {
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

    public boolean validateCredentials(String type, String subtype, int hour, int minute) {
        if (hour < MIN_HOUR || hour > MAX_HOUR) {
            return false;
        }
        if (minute < MIN_MINUTE || minute > MAX_MINUTR) {
            return false;
        }

        return !type.isEmpty() && !subtype.isEmpty();
    }

    public void handleSubmitButton() {
        String type = appointmentTypeField.getText();
        String subtype = appointmentSubtypeField.getText();

        LocalDate selectedDate = LocalDate.parse(appointmentDatePicker.getText(), DateTimeFormatter.ofPattern(APPOINTMENT_DATEPICKER_FORMAT));
        String date = selectedDate.format(DateTimeFormatter.ofPattern(APPOINTMENT_MYSQL_FORMAT));

        int hour = Integer.parseInt(appointmentHourField.getText());
        int minute = Integer.parseInt(appointmentMinuteField.getText());
        SimpleDateFormat timeFormat = new SimpleDateFormat(APPOINTMENT_TIME_FORMAT);
        Date timeObj = new Date();
        timeObj.setHours(hour);
        timeObj.setMinutes(minute);
        timeObj.setSeconds(0);
        String time = timeFormat.format(timeObj);

        if(!validateCredentials(type, subtype, hour, minute)) {
            submitStatusLabel.setText(INVALID_CREDENTIALS_MSG);
            return;
        }

        String userId = User.getCurrentUser().getUid();
        String aid = UUID.randomUUID().toString();

        Appointment appointment = new Appointment(aid, userId, type, subtype, date, time);
        try {
            appointment.saveAppointment();
            submitStatusLabel.setText(APPOINTMENT_SAVED_MSG);
        }
        catch (SQLException e) {
            submitStatusLabel.setText(APPOINTMENT_SAVE_ERROR_MSG);
            e.printStackTrace();
        }
    }
}
