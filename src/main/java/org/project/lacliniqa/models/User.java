package org.project.lacliniqa.models;

import org.project.lacliniqa.managers.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.project.lacliniqa.globals.constants.DBConstants.*;

public class User {
    private String uid;
    private String email;
    private String fname;
    private String lname;
    private String phone;
    private String id;
    private boolean is_admin;

    private static User currentUser = null;

    public User() {}

    public User(String uid, String email, String fname, String lname, String phone, String id, boolean is_admin) {
        this.uid = uid;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.id = id;
        this.is_admin = is_admin;
    }

    public User(User other)
    {
        this.uid = other.uid;
        this.email = other.email;
        this.fname = other.fname;
        this.lname = other.lname;
        this.phone = other.phone;
        this.id = other.id;
        this.is_admin = other.is_admin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdmin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isAdmin() {
        return is_admin;
    }

    public void signupUser(String password) throws SQLException {
        List<String> params = Arrays.asList(uid, email, fname, lname, phone, id, password);

        DBManager.getInstance().connectdb();
        DBManager.getInstance().executeQuery(MYSQL_SIGNUP_USER_QUERY, params);
        DBManager.getInstance().closedb();
    }

    public static User loginUser(String email, String password) throws SQLException {
        List<String> params = Arrays.asList(email, password);

        DBManager.getInstance().connectdb();
        ResultSet set = DBManager.getInstance().executeQueryWithResult(MYSQL_LOGIN_USER_QUERY, params);

        User user = null;

        while(set.next()) {
            user = new User();

            user.setUid(set.getString("uid"));
            user.setEmail(set.getString("email"));
            user.setFname(set.getString("fname"));
            user.setLname(set.getString("lname"));
            user.setPhone(set.getString("phone"));
            user.setId(set.getString("id"));
            user.setAdmin(set.getBoolean("is_admin"));
        }

        DBManager.getInstance().closedb();
        return user;
    }

    public static User getUserFromUid(String uid) throws SQLException {
        List<String> params = Arrays.asList(uid);

        DBManager.getInstance().connectdb();
        ResultSet set = DBManager.getInstance().executeQueryWithResult(MYSQL_GET_USER_QUERY, params);

        User user = null;

        while(set.next()) {
            user = new User();

            user.setUid(set.getString("uid"));
            user.setEmail(set.getString("email"));
            user.setFname(set.getString("fname"));
            user.setLname(set.getString("lname"));
            user.setPhone(set.getString("phone"));
            user.setId(set.getString("id"));
            user.setAdmin(set.getBoolean("is_admin"));
        }

        DBManager.getInstance().closedb();
        return user;
    }

    public void setUser(User other) {
        uid = other.uid;
        email = other.email;
        fname = other.fname;
        lname = other.lname;
        phone = other.phone;
        id = other.id;
        is_admin = other.is_admin;
    }

    public void unsetUser() {
        uid = "";
        email = "";
        fname = "";
        lname = "";
        phone = "";
        id = "";
        is_admin = false;
    }

    public static User getCurrentUser() {
        if(currentUser == null) {
            currentUser = new User();
        }

        return currentUser;
    }
}
