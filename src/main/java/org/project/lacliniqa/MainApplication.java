package org.project.lacliniqa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static org.project.lacliniqa.globals.constants.MsgConstants.APP_TITLE;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(APP_TITLE);
        stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("icons/app-icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}