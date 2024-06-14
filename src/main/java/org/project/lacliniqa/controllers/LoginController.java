package org.project.lacliniqa.controllers;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.Label;
import org.project.lacliniqa.globals.utils.PasswordHasher;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.User;
import java.sql.*;
import java.util.prefs.Preferences;

import static org.project.lacliniqa.globals.constants.PrefConstants.PREFS_SAVE_USER_UID;
import static org.project.lacliniqa.globals.constants.EventConstants.LOGIN_CMPLT_EVENT_ID;

public class LoginController {
    public MFXTextField loginEmailField;
    public MFXPasswordField loginPasswordField;
    public Label loginStatusLabel;

    public void handleLoginButton() {
        String email = loginEmailField.getText().trim();
        String password = loginPasswordField.getText().trim();

        String hashed_password = PasswordHasher.hash(password);

        try {
            User user = User.loginUser(email, hashed_password);
            if(user != null) {
                User.getCurrentUser().setUser(user);

                Preferences prefs = Preferences.systemNodeForPackage(AppController.class);
                prefs.put(PREFS_SAVE_USER_UID, user.getUid());

                resetInputFields();

                EventsManager.getInstance().fireEvent(LOGIN_CMPLT_EVENT_ID);
            }
        }
        catch (SQLException exception) {
            loginStatusLabel.setText(exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void resetInputFields() {
        loginEmailField.setText("");
        loginPasswordField.setText("");
    }
}
