module org.project.lacliniqa {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires java.sql;
    requires java.prefs;
    requires jdk.jsobject;
    requires org.json;
    requires org.xerial.sqlitejdbc;

    exports org.project.lacliniqa;
    opens org.project.lacliniqa to javafx.fxml;
    exports org.project.lacliniqa.controllers;
    opens org.project.lacliniqa.controllers to javafx.fxml;
}