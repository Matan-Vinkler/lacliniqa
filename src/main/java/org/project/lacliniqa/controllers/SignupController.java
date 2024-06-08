package org.project.lacliniqa.controllers;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import org.project.lacliniqa.globals.utils.PasswordHasher;
import org.project.lacliniqa.globals.utils.Validators;
import org.project.lacliniqa.models.User;
import static org.project.lacliniqa.globals.constants.MsgConstants.*;

import java.sql.*;
import java.util.UUID;

public class SignupController {
    public MFXTextField signupEmailField;
    public MFXTextField signupFnameField;
    public MFXTextField signupLnameField;
    public MFXTextField signupPhoneField;
    public MFXTextField signupIdField;
    public MFXPasswordField signupPasswordField;
    public MFXPasswordField signupRepasswordField;
    public Label signupErrorLabel;

    private boolean validateCredentials(String email, String fname, String lname, String phone, String id, String pass1, String pass2) {
        boolean valid = true;

        valid &= Validators.validateEmail(email);
        valid &= Validators.validateName(fname);
        valid &= Validators.validateName(lname);
        valid &= Validators.validatePhone(phone);
        valid &= Validators.validateId(id);
        valid &= Validators.validatePassword(pass1, pass2);

        return valid;
    }

    public void handleSignupButton(ActionEvent actionEvent) throws SQLException {
        String email = signupEmailField.getText().trim();
        String fname = signupFnameField.getText().trim();
        String lname = signupLnameField.getText().trim();
        String phone = signupPhoneField.getText().trim();
        String id = signupIdField.getText().trim();

        String password1 = signupPasswordField.getText().trim();
        String password2 = signupRepasswordField.getText().trim();

        String uid = UUID.randomUUID().toString();

        if(!validateCredentials(email, fname, lname, phone, id, password1, password2)) {
            signupErrorLabel.setText(INVALID_CREDENTIALS_MSG);
            return;
        }

        String hashed_password = PasswordHasher.hash(password1);

        try {
            User newUser = new User(uid, email, fname, lname, phone, id);
            newUser.registerUser(hashed_password);

            signupErrorLabel.setText(USER_ADDED_MSG);
        }
        catch (SQLException exception) {
            signupErrorLabel.setText(exception.getMessage());
            exception.printStackTrace();
        }
    }
}