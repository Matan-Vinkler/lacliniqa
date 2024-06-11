package org.project.lacliniqa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.project.lacliniqa.globals.constants.EventConstants;
import org.project.lacliniqa.managers.EventsManager;

import static org.project.lacliniqa.globals.constants.EventConstants.*;

public class AppController {
    public TabPane appTabPane;
    public Tab loginTab;
    public Tab signupTab;
    public Tab setappointmentTab;
    public Tab logoutTab;

    public int handleLoginComplete() {
        /* Disable login and register tab */
        loginTab.setDisable(true);
        signupTab.setDisable(true);

        /* Enable all other tabs */
        setappointmentTab.setDisable(false);
        logoutTab.setDisable(false);

        /* Navigate to home tab */
        appTabPane.getSelectionModel().select(setappointmentTab);

        return 0;
    }

    public int handleSignupComplete() {
        /* Navigate to login tab */
        appTabPane.getSelectionModel().select(loginTab);

        return 0;
    }

    public int handlelogoutComplete() {
        /* Enable login and register tab */
        loginTab.setDisable(false);
        signupTab.setDisable(false);

        /* Disable all other tabs */
        setappointmentTab.setDisable(true);
        logoutTab.setDisable(true);

        /* Navigate to login tab */
        appTabPane.getSelectionModel().select(loginTab);

        return 0;
    }

    @FXML
    public void initialize() {
        /* Register app events */
        EventsManager.getInstance().registerEvent(LOGIN_CMPLT_EVENT_ID, this::handleLoginComplete);
        EventsManager.getInstance().registerEvent(SIGNUP_CMPLT_EVENT_ID, this::handleSignupComplete);
        EventsManager.getInstance().registerEvent(LOGOUT_CMPLT_EVENT_ID, this::handlelogoutComplete);

        /* Navigate to login tab */
        appTabPane.getSelectionModel().select(loginTab);
    }
}
