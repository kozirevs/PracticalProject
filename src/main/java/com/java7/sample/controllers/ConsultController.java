package com.java7.sample.controllers;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.repository.ConsultRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.ConsultService;
import com.java7.sample.service.factory.ConsultFactory;
import com.java7.sample.service.validator.ConsultServiceValidator;
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
import org.h2.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
    private TableView<Consult> consultsTableView;

    @FXML
    private TableColumn<Consult, Long> consultIdColumn;

    @FXML
    private TableColumn<Consult, Date> dateColumn;

    @FXML
    private TableColumn<Consult, String> descriptionColumn;


    @FXML
    private TableView<Vet> vetTableView;

    @FXML
    private TableColumn<Vet, String> vetFirstNameColumn;

    @FXML
    private TableColumn<Vet, String> vetLastNameColumn;

    @FXML
    private TableView<Pet> petTableView;

    @FXML
    private TableColumn<Pet, String> petRaceColumn;

    @FXML
    private TableColumn<Pet, String> petOwnerNameColumn;

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
        ConsultService consultService = new ConsultService(new ConsultRepository()
                , new PetRepository(),
                new VetRepository(),
                new ConsultFactory(),
                new ConsultServiceValidator());

        addConsultButton.setOnAction(event -> {
            System.out.println("consult insert in progress");

            String vetIdText = vetIdField.getText().trim();
            Long vetId = null;
            if (StringUtils.isNumber(vetIdText)) {
                vetId = Long.parseLong(vetIdText);
            }
            String petIdText = petIdField.getText().trim();
            Long petId = null;
            if (StringUtils.isNumber(petIdText)) {
                petId = Long.parseLong(petIdText);
            }

            String descriptionText = descriptionArea.getText().trim();

            LocalDate localDate = dateDatePicker.getValue();
            Date date = null;
            if(localDate != null){
                Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
                date = Date.from(instant);
            }

            String result = consultService.addConsult(vetId, petId, date, descriptionText);
            System.out.println(result);

            operationResultText.setText(result);

            vetIdField.clear();
            petIdField.clear();
            descriptionArea.clear();
            dateDatePicker.getEditor().clear();
        });

        viewConsultsButton.setOnAction(event -> {
            System.out.println("select consults in progress ");

            consultIdColumn.setCellValueFactory(new PropertyValueFactory<>("consultId"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Consult, String>("description"));
            vetFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Vet, String>("firstName"));
            vetLastNameColumn.setCellValueFactory(new PropertyValueFactory<Vet, String>("lastName"));
            petRaceColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("race"));
            petOwnerNameColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("ownerName"));

            List<Consult> consultList = consultService.viewConsults();
            if (consultList.isEmpty()) {
                operationResultText.setText("CONSULT DATABASE IS EMPTY");
            }
            List<Vet> vetList = consultService.viewVetsByConsults();
            if (vetList.isEmpty()) {
                operationResultText.setText("CONSULT DATABASE IS EMPTY");
            }
            List<Pet> petList = consultService.viewPetsByConsults();
            if (petList.isEmpty()) {
                operationResultText.setText("CONSULT DATABASE IS EMPTY");
            }
            ObservableList<Consult> consultObservableList = FXCollections.observableArrayList(consultList);
            ObservableList<Vet> vetObservableList = FXCollections.observableArrayList(vetList);
            ObservableList<Pet> petObservableList = FXCollections.observableArrayList(petList);

            consultsTableView.setItems(consultObservableList);
            vetTableView.setItems(vetObservableList);
            petTableView.setItems(petObservableList);
        });

        deleteConsultButton.setOnAction(event -> {
            System.out.println("delete consult in progress");

            String consultIdText = consultIdField.getText().trim();
            Long consultId = null;
            if (StringUtils.isNumber(consultIdText)) {
                consultId = Long.parseLong(consultIdText);
            }

            String result = consultService.removeConsult(consultId);
            System.out.println(result);

            operationResultText.setText(result);

            consultIdField.clear();
        });

        updateConsultButton.setOnAction(event -> {
            System.out.println("update consult in progress");

            String consultIdText = consultIdField.getText().trim();
            Long consultId = null;
            if (StringUtils.isNumber(consultIdText)) {
                consultId = Long.parseLong(consultIdText);
            }

            String vetIdText = vetIdField.getText().trim();
            Long vetId = null;
            if (StringUtils.isNumber(vetIdText)) {
                vetId = Long.parseLong(vetIdText);
            }

            String petIdText = petIdField.getText().trim();
            Long petId = null;
            if (StringUtils.isNumber(petIdText)) {
                petId = Long.parseLong(petIdText);
            }

            LocalDate localDate = dateDatePicker.getValue();
            Date date = null;
            if(localDate != null){
                Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
                date = Date.from(instant);
            }

            String descriptionAreaText = descriptionArea.getText().trim();

            String result = consultService.updateConsult(consultId, vetId, petId, date, descriptionAreaText);
            System.out.println(result);

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
