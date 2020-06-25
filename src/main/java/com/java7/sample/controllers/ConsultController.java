package com.java7.sample.controllers;

import com.java7.sample.model.ConsultVetPet;
import com.java7.sample.repository.ConsultRepository;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.ConsultService;
import com.java7.sample.service.factory.ConsultFactory;
import com.java7.sample.service.factory.ConsultVetPetFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ConsultController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addConsultButton;

    @FXML
    private TextField consultIdField;

    @FXML
    private Button updateConsultButton;

    @FXML
    private Button deleteConsultButton;

    @FXML
    private Button viewConsultsButton;

    @FXML
    private Button crudVetButton;

    @FXML
    private TableView<ConsultVetPet> consultsTableView;

    @FXML
    private TableColumn<ConsultVetPet, Long> consultIdColumn;

    @FXML
    private TableColumn<ConsultVetPet, Date> dateColumn;

    @FXML
    private TableColumn<ConsultVetPet, String> descriptionColumn;

    @FXML
    private TableColumn<ConsultVetPet, String> vetFirstNameColumn;

    @FXML
    private TableColumn<ConsultVetPet, String> vetLastNameColumn;

    @FXML
    private TableColumn<ConsultVetPet, String> petRaceColumn;

    @FXML
    private TableColumn<ConsultVetPet, String> petOwnerNameColumn;

    @FXML
    private Text operationResultText;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private TextField vetIdField;

    @FXML
    private TextField petIdField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Button crudPetButton;

    @FXML
    void initialize() {
        ConsultService consultService = new ConsultService(new ModelRepository(),
                new ConsultRepository(), new PetRepository(), new VetRepository(), new ConsultFactory());

        ConsultVetPetFactory consultVetPetFactory = new ConsultVetPetFactory();

        addConsultButton.setOnAction(event -> {
            System.out.println("CONSULT INSERT IN PROGRESS");

            String vetIdText = vetIdField.getText();
            String petIdText = petIdField.getText();
            String descriptionText = descriptionArea.getText();
            LocalDate dateLocalDate = dateDatePicker.getValue();

            String result = consultService.addConsult(vetIdText, petIdText, dateLocalDate, descriptionText);
            operationResultText.setText(result);

            vetIdField.clear();
            petIdField.clear();
            descriptionArea.clear();
            dateDatePicker.getEditor().clear();
        });

        viewConsultsButton.setOnAction(event -> {
            System.out.println("SELECT CONSULTS IN PROGRESS");

            consultIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            vetFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            vetLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            petRaceColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
            petOwnerNameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));


            List<ConsultVetPet> consultList = consultService.viewConsults()
                    .stream()
                    .map(consultVetPetFactory::createConsultVetPet)
                    .collect(Collectors.toList());


            ObservableList<ConsultVetPet> consultObservableList = FXCollections.observableArrayList(consultList);
            consultsTableView.setItems(consultObservableList);
        });

        deleteConsultButton.setOnAction(event -> {
            System.out.println("DELETE CONSULT IN PROGRESS");

            String idText = consultIdField.getText();

            String result = consultService.removeConsult(idText);
            operationResultText.setText(result);

            consultIdField.clear();
        });

        updateConsultButton.setOnAction(event -> {
            System.out.println("UPDATE CONSULT IN PROGRESS");

            String idText = consultIdField.getText();
            String vetIdText = vetIdField.getText();
            String petIdText = petIdField.getText();
            LocalDate dateLocalDate = dateDatePicker.getValue();
            String descriptionAreaText = descriptionArea.getText();

            String result = consultService
                    .updateConsult(idText, vetIdText, petIdText, dateLocalDate, descriptionAreaText);
            operationResultText.setText(result);

            consultIdField.clear();
            vetIdField.clear();
            petIdField.clear();
            dateDatePicker.getEditor().clear();
            descriptionArea.clear();
        });

        crudVetButton.setOnAction(event -> {
            crudVetButton.getScene().getWindow().hide();

            Parent vetRegistrationForm = null;
            try {
                vetRegistrationForm = FXMLLoader.load(getClass().getResource("/view/vetRegistration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(vetRegistrationForm));
            stage.show();
        });

        crudPetButton.setOnAction(event -> {
            crudPetButton.getScene().getWindow().hide();

            Parent petRegistrationForm = null;
            try {
                petRegistrationForm = FXMLLoader.load(getClass().getResource("/view/petRegistration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(petRegistrationForm));
            stage.show();
        });
    }
}
