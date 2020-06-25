package com.java7.sample.controllers;

import com.java7.sample.model.Vet;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.VetService;
import com.java7.sample.service.factory.VetFactory;
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
        VetService vetService = new VetService(new ModelRepository(), new VetRepository(), new VetFactory());

        addVetButton.setOnAction(event -> {
            System.out.println( "VET INSERT IN PROGRESS");

            String firstNameText = firstNameField.getText();
            String lastNameText = lastNameField.getText();
            String addressText = addressField.getText();
            String specialityText = specialityField.getText();

            String result = vetService.addVet(firstNameText, lastNameText, addressText, specialityText);
            operationResultText.setText(result);

            firstNameField.clear();
            lastNameField.clear();
            addressField.clear();
            specialityField.clear();
        });

        viewVetsButton.setOnAction(event -> {
            System.out.println("SELECT VETS IN PROGRESS");

            vetIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            specialityColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));

            List<Vet> vetList = vetService.viewVets();
            ObservableList<Vet> vetObservableList = FXCollections.observableArrayList(vetList);
            vetsTableView.setItems(vetObservableList);
        });

        deleteVetButton.setOnAction(event -> {
            System.out.println("DELETE VET IN PROGRESS");

            String idText = vetIdField.getText().trim();

            String result = vetService.removeVet(idText);
            operationResultText.setText(result);

            vetIdField.clear();
        });

        updateVetButton.setOnAction(event -> {
            System.out.println("UPDATE VET IN PROGRESS");

            String idText = vetIdField.getText();
            String firstNameText = firstNameField.getText();
            String lastNameText = lastNameField.getText();
            String addressText = addressField.getText();
            String specialityText = specialityField.getText();

            String result = vetService.updateVet(idText, firstNameText, lastNameText, addressText, specialityText);
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
