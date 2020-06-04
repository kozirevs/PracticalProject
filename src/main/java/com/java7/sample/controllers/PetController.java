package com.java7.sample.controllers;

import com.java7.sample.model.Pet;
import com.java7.sample.service.PetService;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addPetButton;

    @FXML
    private TextField petIdField;

    @FXML
    private TextField raceField;

    @FXML
    private Button updatePetButton;

    @FXML
    private TextField ownerNameField;

    @FXML
    private Button deletePetButton;

    @FXML
    private Button viewPetsButton;

    @FXML
    private Button crudVetButton;

    @FXML
    private Button crudConsultButton;

    @FXML
    private TableView<Pet> petsTableView;

    @FXML
    private TableColumn<Pet, Long> petIdColumn;

    @FXML
    private TableColumn<Pet, String> raceColumn;

    @FXML
    private TableColumn<Pet, Date> dateOfBirthColumn;

    @FXML
    private TableColumn<Pet, String> ownerNameColumn;

    @FXML
    private TableColumn<Pet, String> isVaccinatedColumn;

    @FXML
    private Text operationResultText;

    @FXML
    private CheckBox isVaccinatedCheckBox;

    @FXML
    private DatePicker dateOfBirthDatePicker;

    @FXML
    void initialize() {
        PetService petService = new PetService();

        addPetButton.setOnAction(event -> {
            System.out.println("pet insert in progress");

            String raceText = raceField.getText().trim();
            String ownerNameText = ownerNameField.getText().trim();

            LocalDate localDate = dateOfBirthDatePicker.getValue();
            Date dateOfBirthDate = null;
            if(localDate != null){
                Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
                dateOfBirthDate = Date.from(instant);
            }

            Boolean isVaccinatedBoolean = isVaccinatedCheckBox.isSelected();
            String isVaccinatedText = isVaccinatedBoolean ? "YES" : "NO";

            String result = petService.addPet(raceText, dateOfBirthDate, isVaccinatedText, ownerNameText);
            System.out.println(result);

            operationResultText.setText(result);

            raceField.clear();
            ownerNameField.clear();
            isVaccinatedCheckBox.setSelected(false);
            dateOfBirthDatePicker.getEditor().clear();
        });

        viewPetsButton.setOnAction(event -> {
            System.out.println("select pets in progress");

            petIdColumn.setCellValueFactory(new PropertyValueFactory<Pet, Long>("petId"));
            raceColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("race"));
            dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Pet, Date>("dateOfBirth"));
            ownerNameColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("ownerName"));
            isVaccinatedColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("isVaccinated"));

            List<Pet> petList = petService.viewPets();
            if (petList.isEmpty()) {
                operationResultText.setText("PET DATABASE IS EMPTY");
            }
            ObservableList<Pet> petObservableList = FXCollections.observableArrayList(petList);
            petsTableView.setItems(petObservableList);
        });

        deletePetButton.setOnAction(event -> {
            System.out.println("delete pet in progress");

            String petIdText = petIdField.getText().trim();
            Long petId = null;
            if (StringUtils.isNumber(petIdText)) {
                petId = Long.parseLong(petIdText);
            }

            String result = petService.removePet(petId);
            System.out.println(result);

            operationResultText.setText(result);

            petIdField.clear();
        });

        updatePetButton.setOnAction(event -> {
            System.out.println("update pet in progress");

            String petIdText = petIdField.getText().trim();
            Long petId = null;
            if (StringUtils.isNumber(petIdText)) {
                petId = Long.parseLong(petIdText);
            }
            String raceText = raceField.getText().trim();
            String ownerNameText = ownerNameField.getText().trim();

            LocalDate localDate = dateOfBirthDatePicker.getValue();
            Date dateOfBirthDate = null;
            if(localDate != null){
                Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
                dateOfBirthDate = Date.from(instant);
            }

            Boolean isVaccinatedBoolean = isVaccinatedCheckBox.isSelected();
            String isVaccinatedText = isVaccinatedBoolean ? "YES" : "NO";

            String result = petService.updatePet(petId, raceText, dateOfBirthDate, isVaccinatedText, ownerNameText);
            System.out.println(result);

            operationResultText.setText(result);

            petIdField.clear();
            raceField.clear();
            ownerNameField.clear();
            isVaccinatedCheckBox.setSelected(false);
            dateOfBirthDatePicker.getEditor().clear();
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

        crudConsultButton.setOnAction(event -> {
            crudConsultButton.getScene().getWindow().hide();

            Parent consultRegistrationForm = null;
            try {
                consultRegistrationForm = FXMLLoader.load(getClass().getResource("/view/consultRegistration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(consultRegistrationForm));
            stage.show();
        });
    }
}
