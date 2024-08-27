package com.example.laboratoriouno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SesionViewController {
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnModificar;

    @FXML
    private ComboBox<?> cmboxDeporte;

    @FXML
    private ComboBox<?> cmboxDuracion;

    @FXML
    private ComboBox<?> cmboxEntrenador;

    @FXML
    private TableView<?> tableSesion;

    @FXML
    private TableColumn<?, ?> tcDeporte;

    @FXML
    private TableColumn<?, ?> tcEntrenador;

    @FXML
    private TableColumn<?, ?> tcFecha;

    @FXML
    void eliminarSesionAction(ActionEvent event) {

    }

    @FXML
    void guardarSesionAction(ActionEvent event) {

    }

    @FXML
    void modificarSesionAction(ActionEvent event) {

    }
}
