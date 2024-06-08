package org.project.lacliniqa.controllers;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import org.project.lacliniqa.globals.utils.PasswordHasher;
import org.project.lacliniqa.models.User;

import java.sql.*;

public class LoginController {
    public MFXTextField loginEmailField;
    public MFXPasswordField loginPasswordField;
    public Label loginErrorLabel;

    public void handleLoginButton(ActionEvent actionEvent) {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();

        String hashed_password = PasswordHasher.hash(password);

        try {
            User user = User.getUser(email, hashed_password);
            if(user != null) {
                loginErrorLabel.setText("Welcome " + user.getFname() + " " + user.getLname());
            }
        }
        catch (SQLException exception) {
            loginErrorLabel.setText(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
