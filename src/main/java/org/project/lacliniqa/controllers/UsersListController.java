package org.project.lacliniqa.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import org.project.lacliniqa.MainApplication;
import org.project.lacliniqa.managers.EventsManager;
import org.project.lacliniqa.models.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.project.lacliniqa.globals.constants.EventConstants.FETCH_USERS_LIST_EVENT_ID;

class UserListCell extends ListCell<User> {
    private VBox content;
    private UserCellController controller;

    public UserListCell() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("views/user-cell-view.fxml"));
            content = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(User item, boolean empty) {
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

public class UsersListController implements Initializable {
    public ListView<User> usersListView;

    public int fetchList() {
        usersListView.setCellFactory(listView -> new UserListCell());
        usersListView.getItems().clear();

        try {
            if(User.getCurrentUser().isAdmin()) {
                ArrayList<User> usersList = User.fetchAllUsers();
                usersListView.getItems().addAll(usersList);

                return 0;
            }

            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventsManager.getInstance().registerEvent(FETCH_USERS_LIST_EVENT_ID, this::fetchList);
    }
}
