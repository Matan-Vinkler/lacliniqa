package org.project.lacliniqa.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

import static org.project.lacliniqa.globals.constants.PrefConstants.PREFS_SAVED_USER_PASSWORD;
import static org.project.lacliniqa.globals.constants.PrefConstants.PREFS_SAVE_USER_UID;
import static org.project.lacliniqa.globals.constants.EventConstants.*;

public class AppController {
    public TabPane appTabPane;
    public Tab loginTab;
    public Tab signupTab;
    public Tab setappointmentTab;
    public Tab logoutTab;

    private List<Tab> userLoggedTabs;
    private List<Tab> userNotLoggedTabs;


    public int handleLoginComplete() {
        /* Display user uid */
        System.out.println(User.getCurrentUser().getUid());

        /* Disable user not logged tabs */
        for(Tab tab: userNotLoggedTabs) {
            tab.setDisable(true);
        }

        /* Enable user logged tabs */
        for(Tab tab: userLoggedTabs) {
            tab.setDisable(false);
        }

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
        /* Enable user not logged tabs */
        for(Tab tab: userNotLoggedTabs) {
            tab.setDisable(false);
        }

        /* Disable user logged tabs */
        for(Tab tab: userLoggedTabs) {
            tab.setDisable(true);
        }

        /* Navigate to login tab */
        appTabPane.getSelectionModel().select(loginTab);

        return 0;
    }

    public void checkLogin() {
        Preferences prefs = Preferences.systemNodeForPackage(AppController.class);

        String uid = prefs.get(PREFS_SAVE_USER_UID, "");
        String hased_password = prefs.get(PREFS_SAVED_USER_PASSWORD, "");

        try {
            User user = User.getUserFromUid(uid, hased_password);

            if(user != null) {
                User.getCurrentUser().setUser(user);
                handleLoginComplete();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        /* Define user logged in and not logged in tabs */
        userLoggedTabs = Arrays.asList(setappointmentTab, logoutTab);
        userNotLoggedTabs = Arrays.asList(loginTab, signupTab);

        /* Register app events */
        EventsManager.getInstance().registerEvent(LOGIN_CMPLT_EVENT_ID, this::handleLoginComplete);
        EventsManager.getInstance().registerEvent(SIGNUP_CMPLT_EVENT_ID, this::handleSignupComplete);
        EventsManager.getInstance().registerEvent(LOGOUT_CMPLT_EVENT_ID, this::handlelogoutComplete);

        checkLogin();
    }
}
