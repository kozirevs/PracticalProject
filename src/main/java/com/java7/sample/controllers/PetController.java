package com.java7.sample.controllers;

import com.java7.sample.model.Pet;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.service.PetService;
import com.java7.sample.service.factory.PetFactory;
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
    private TableColumn<Pet, Boolean> isVaccinatedColumn;

    @FXML
    private Text operationResultText;

    @FXML
    private CheckBox isVaccinatedCheckBox;

    @FXML
    private DatePicker dateOfBirthDatePicker;

    @FXML
    void initialize() {
        PetService petService = new PetService(new ModelRepository(), new PetRepository(), new PetFactory());

        addPetButton.setOnAction(event -> {
            System.out.println("PET INSERT IN PROGRESS");

            String raceText = raceField.getText();
            String ownerNameText = ownerNameField.getText();
            LocalDate dateOfBirthLocalDate = dateOfBirthDatePicker.getValue();
            Boolean isVaccinatedBoolean = isVaccinatedCheckBox.isSelected();

            String result = petService.addPet(raceText, dateOfBirthLocalDate, isVaccinatedBoolean, ownerNameText);
            operationResultText.setText(result);

            raceField.clear();
            ownerNameField.clear();
            isVaccinatedCheckBox.setSelected(false);
            dateOfBirthDatePicker.getEditor().clear();
        });

        viewPetsButton.setOnAction(event -> {
            System.out.println("SELECT PETS IN PROGRESS");

            petIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            raceColumn.setCellValueFactory(new PropertyValueFactory<>("race"));
            dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            ownerNameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
            isVaccinatedColumn.setCellValueFactory(new PropertyValueFactory<>("isVaccinated"));

            List<Pet> petList = petService.viewPets();
            ObservableList<Pet> petObservableList = FXCollections.observableArrayList(petList);
            petsTableView.setItems(petObservableList);
        });

        deletePetButton.setOnAction(event -> {
            System.out.println("DELETE PET IN PROGRESS");

            String idText = petIdField.getText();

            String result = petService.removePet(idText);
            operationResultText.setText(result);

            petIdField.clear();
        });

        updatePetButton.setOnAction(event -> {
            System.out.println("UPDATE PET IN PROGRESS");

            String idText = petIdField.getText();
            String raceText = raceField.getText();
            LocalDate dateOfBirthLocalDate = dateOfBirthDatePicker.getValue();
            Boolean isVaccinatedBoolean = isVaccinatedCheckBox.isSelected();
            String ownerNameText = ownerNameField.getText();

            String result = petService.updatePet(idText, raceText, dateOfBirthLocalDate, isVaccinatedBoolean, ownerNameText);
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
                consultRegistrationForm = FXMLLoader.load(getClass()
                        .getResource("/view/consultRegistration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(consultRegistrationForm));
            stage.show();
        });
    }
}
