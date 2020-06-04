package com.java7.sample.controllers;

import com.java7.sample.model.Vet;
import com.java7.sample.service.VetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.h2.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class VetController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField vetIdField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField specialityField;

    @FXML
    private Button addVetButton;

    @FXML
    private Button updateVetButton;

    @FXML
    private Button deleteVetButton;

    @FXML
    private Button viewVetsButton;

    @FXML
    private Button crudPetButton;

    @FXML
    private Button crudConsultButton;

    @FXML
    private TableView<Vet> vetsTableView;

    @FXML
    private TableColumn<Vet, Long> vetIdColumn;

    @FXML
    private TableColumn<Vet, String> firstNameColumn;

    @FXML
    private TableColumn<Vet, String> lastNameColumn;

    @FXML
    private TableColumn<Vet, String> addressColumn;

    @FXML
    private TableColumn<Vet, String> specialityColumn;

    @FXML
    private Text operationResultText;

    @FXML
    void initialize() {
        VetService vetService = new VetService();

        addVetButton.setOnAction(event -> {
            System.out.println("vet insert in progress");

            String firstNameText = firstNameField.getText().trim();
            String lastNameText = lastNameField.getText().trim();
            String addressText = addressField.getText().trim();
            String specialityText = specialityField.getText().trim();

            String result = vetService.addVet(firstNameText, lastNameText, addressText, specialityText);
            System.out.println(result);

            operationResultText.setText(result);

            firstNameField.clear();
            lastNameField.clear();
            addressField.clear();
            specialityField.clear();
        });

        viewVetsButton.setOnAction(event -> {
            System.out.println("select vets in progress");

            vetIdColumn.setCellValueFactory(new PropertyValueFactory<Vet, Long>("vetId"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<Vet, String>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<Vet, String>("lastName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<Vet, String>("address"));
            specialityColumn.setCellValueFactory(new PropertyValueFactory<Vet, String>("speciality"));

            List<Vet> vetList = vetService.viewVets();
            if (vetList.isEmpty()) {
                operationResultText.setText("VET DATABASE IS EMPTY");
            }
            ObservableList<Vet> vetObservableList = FXCollections.observableArrayList(vetList);
            vetsTableView.setItems(vetObservableList);
        });

        deleteVetButton.setOnAction(event -> {
            System.out.println("delete vet in progress");

            String vetIdText = vetIdField.getText().trim();
            Long vetId = null;
            if (StringUtils.isNumber(vetIdText)) {
                vetId = Long.parseLong(vetIdText);
            }

            String result = vetService.removeVet(vetId);
            System.out.println(result);

            operationResultText.setText(result);

            vetIdField.clear();
        });

        updateVetButton.setOnAction(event -> {
            System.out.println("update vet in progress");

            String vetIdText = vetIdField.getText().trim();
            Long vetId = null;
            if (StringUtils.isNumber(vetIdText)) {
                vetId = Long.parseLong(vetIdText);
            }
            String firstNameText = firstNameField.getText().trim();
            String lastNameText = lastNameField.getText().trim();
            String addressText = addressField.getText().trim();
            String specialityText = specialityField.getText().trim();

            String result = vetService.updateVet(vetId, firstNameText, lastNameText, addressText, specialityText);
            System.out.println(result);

            operationResultText.setText(result);

            vetIdField.clear();
            firstNameField.clear();
            lastNameField.clear();
            addressField.clear();
            specialityField.clear();
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
