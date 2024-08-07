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

import static org.project.lacliniqa.globals.constants.PrefConstants.PREFS_SAVE_USER_UID;
import static org.project.lacliniqa.globals.constants.EventConstants.*;

public class AppController {
    public TabPane appTabPane;
    public Tab loginTab;
    public Tab signupTab;
    public Tab setappointmentTab;
    public Tab logoutTab;
    public Tab appointmentsListTab;
    public Tab adminTab;
    public Tab paymentTab;
    public Tab usersListTab;

    private List<Tab> userLoggedTabs;
    private List<Tab> userNotLoggedTabs;
    private List<Tab> userAdminTabs;

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

        /* Enable admin tabs if user is admin */
        for(Tab tab: userAdminTabs) {
            tab.setDisable(!User.getCurrentUser().isAdmin());
        }

        /* Fetch appointments list */
        EventsManager.getInstance().fireEvent(FETCH_APPOINTMENTS_LIST_EVENT_ID);

        /* Fetch users list if admin */
        if(User.getCurrentUser().isAdmin()) {
            EventsManager.getInstance().fireEvent(FETCH_USERS_LIST_EVENT_ID);
        }

        /* Navigate to home tab */
        appTabPane.getSelectionModel().select(appointmentsListTab);

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

        /* Disable admin tabs */
        for(Tab tab: userAdminTabs) {
            tab.setDisable(true);
        }

        /* Navigate to login tab */
        appTabPane.getSelectionModel().select(loginTab);

        return 0;
    }

    public int gotoAppointments() {
        appTabPane.getSelectionModel().select(appointmentsListTab);
        return 0;
    }

    public void checkLogin() {
        Preferences prefs = Preferences.systemNodeForPackage(AppController.class);

        String uid = prefs.get(PREFS_SAVE_USER_UID, "");

        try {
            User user = User.getUserFromUid(uid);

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
        userLoggedTabs = Arrays.asList(appointmentsListTab, setappointmentTab, paymentTab, logoutTab);
        userNotLoggedTabs = Arrays.asList(loginTab, signupTab);
        userAdminTabs = Arrays.asList(adminTab, usersListTab);

        /* Register app events */
        EventsManager.getInstance().registerEvent(LOGIN_CMPLT_EVENT_ID, this::handleLoginComplete);
        EventsManager.getInstance().registerEvent(SIGNUP_CMPLT_EVENT_ID, this::handleSignupComplete);
        EventsManager.getInstance().registerEvent(LOGOUT_CMPLT_EVENT_ID, this::handlelogoutComplete);
        EventsManager.getInstance().registerEvent(GOTO_APPOINTMENTS_EVENT_ID, this::gotoAppointments);

        checkLogin();
    }
}
