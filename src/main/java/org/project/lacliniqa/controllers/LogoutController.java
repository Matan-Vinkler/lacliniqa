package org.project.lacliniqa.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.User;

import static org.project.lacliniqa.globals.constants.EventConstants.LOGOUT_CMPLT_EVENT_ID;

public class LogoutController {
    public MFXButton logoutButton;

    public void handleLogoutButton(ActionEvent actionEvent) {
        User.getCurrentUser().unsetUser();

        EventsManager.getInstance().fireEvent(LOGOUT_CMPLT_EVENT_ID);
    }
}
