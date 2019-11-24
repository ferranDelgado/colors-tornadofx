module com.my.color {
    requires org.flywaydb.core;
    requires org.jdbi.v3.core;
    requires org.jdbi.v3.kotlin;
    requires org.jdbi.v3.sqlite;
    requires com.zaxxer.hikari;
    requires java.desktop;
    requires java.sql;
    requires java.logging;
    requires kotlin.stdlib;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires tornadofx;

    exports com.my.color.controller;
    exports com.my.color.view;

}