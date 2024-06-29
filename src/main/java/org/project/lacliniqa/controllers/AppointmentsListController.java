package org.project.lacliniqa.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import org.project.lacliniqa.MainApplication;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.Appointment;
import org.project.lacliniqa.models.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.project.lacliniqa.globals.constants.EventConstants.FETCH_APPOINTMENTS_LIST_EVENT_ID;

class AppointmentListCell extends ListCell<Appointment> {
    private ToolBar content;
    private AppointmentCellController controller;

    public AppointmentListCell() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/appointment-cell-view.fxml"));
            content = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Appointment item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            try {
                controller.initialize(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            setGraphic(content);
        }
    }
}

public class AppointmentsListController implements Initializable {
    public ListView<Appointment> appointmentsListView;

    public int fetchList() {
        appointmentsListView.setCellFactory(listView -> new AppointmentListCell());

        try {
            ArrayList<Appointment> appointmentsList;
            if(User.getCurrentUser().isAdmin()) {
                appointmentsList = Appointment.getAllAppointments();
            }
            else {
                appointmentsList = Appointment.getAppointments(User.getCurrentUser().getUid());
            }

            appointmentsListView.getItems().clear();
            appointmentsListView.getItems().addAll(appointmentsList);

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventsManager.getInstance().registerEvent(FETCH_APPOINTMENTS_LIST_EVENT_ID, this::fetchList);
    }
}
