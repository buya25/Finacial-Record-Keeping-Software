module FXTEST {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;
    requires Medusa;
    requires AnimateFX;
    requires mysql.connector.java;


    opens login;
    opens pace_Classes;
    opens DAO;
    opens controllers;
    opens signup;
    opens test;
    opens dash_board;

}
