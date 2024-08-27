package com.example.laboratoriouno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.Entrenador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EntrenadorViewController {

    ArrayList<Entrenador> listaEntrenadores;
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableView<?> tableEmpleados;

    @FXML
    private TableColumn<?, ?> tcEspecialidad;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtNombre;

    @FXML
    void eliminarEntrenadorAction(ActionEvent event) {

    }

    @FXML
    void guardarEntrenadorAction(ActionEvent event) {

    }

    @FXML
    void modificarEntrenadorAction(ActionEvent event) {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tcNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.tcEspecialidad.setCellValueFactory(new PropertyValueFactory("Especialidad"));
        this.listaEntrenadores =Entrenador.
    }
}
