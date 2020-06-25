package com.java7.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.flywaydb.core.Flyway;
import org.h2.tools.Server;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/vetRegistration.fxml"));
        Scene scene = new Scene(root, 700, 600);
        primaryStage.setTitle("Pet Clinic management system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Server server = Server.createWebServer().start();
        System.out.println("port: " + server.getPort());
        System.out.println("status: " + server.getStatus());

        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "password")
                .load();
        flyway.migrate();

        launch(args);
    }
}