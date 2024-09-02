package com.example.laboratoriouno;

import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SesionViewController {
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableView<Sesion> tableSesion;

    @FXML
    private TableColumn<Sesion, String> tcDeporte;

    @FXML
    private TableColumn<Sesion, String> tcEntrenador;

    @FXML
    private TableColumn<Sesion, String> tcFecha;

    @FXML
    private TextField txtDeporte;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtEntrenador;
    @FXML
    private Label lbEstado;
    @FXML
    private DatePicker txtFecha;

    private String ruta = "sesiones.json";
    private Type listTypeSesion = new TypeToken<ArrayList<Sesion>>(){}.getType();
    private List<Sesion> sesiones;
    private ObservableList<Sesion> sesionList;

    private Deporte deporte;
    private Entrenador entrenador;

    @FXML
    private void initialize() {
        // Leer los datos del archivo JSON
        sesiones = SesionCrud.readFromFile(ruta);
        sesionList = FXCollections.observableArrayList(sesiones);

        // Configurar las columnas de la tabla
        tcDeporte.setCellValueFactory(cellData -> {
            Deporte deporte = cellData.getValue().getDeporte();
            return deporte != null ? new SimpleStringProperty(deporte.getNombre()) : new SimpleStringProperty("");
        });
        tcEntrenador.setCellValueFactory(cellData -> {
            Entrenador entrenador = cellData.getValue().getEntrenador();
            return entrenador != null ? new SimpleStringProperty(entrenador.getNombre()) : new SimpleStringProperty("");
        });
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // Asignar los datos a la tabla
        tableSesion.setItems(sesionList);
    }

    @FXML
    void addEntrenador(ActionEvent event) {
        int id = Integer.parseInt(txtEntrenador.getText());
        Type listType = new TypeToken<ArrayList<Entrenador>>(){}.getType();
        entrenador = MetodosCrud.buscarUsuario(id, "entrenadores.json", listType);
    }

    @FXML
    void addDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtDeporte.getText());
        Type listType = new TypeToken<ArrayList<Deporte>>(){}.getType();
        deporte = MetodosCrud.buscarUsuario(id, "deportes.json", listType);
    }

    @FXML
    void buscarSesion(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        Sesion sesion = SesionCrud.buscarSesion(id, ruta);
        if (sesion == null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "No se encontraron resultados", ButtonType.OK);
            alerta.setTitle("Confirmación");
            alerta.showAndWait();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(sesion.getFecha(), formatter);

            txtFecha.setValue(fecha);
            txtDuracion.setText(String.valueOf(sesion.getDuracionMin()));
            lbEstado.setText(String.valueOf(sesion.getEstado()));
            if (sesion.getDeporte() == null || sesion.getEntrenador() == null) {
                System.out.print("Datos nulos");
            } else {
                txtEntrenador.setText(Integer.toString(sesion.getEntrenador().getId()));
                txtDeporte.setText(Integer.toString(sesion.getDeporte().getId()));
            }
        }
    }

    @FXML
    void eliminarSesionAction(ActionEvent event) {
        Sesion selectedSesion = tableSesion.getSelectionModel().getSelectedItem();
        if (selectedSesion != null) {
            sesionList.remove(selectedSesion);
            SesionCrud.eliminarSesion(selectedSesion.getId(), ruta, sesiones);
            tableSesion.refresh();
            mostrarAlerta("Se eliminó exitosamente");
        } else if (!txtId.getText().isEmpty()) {
            int id = Integer.parseInt(txtId.getText());
            SesionCrud.eliminarSesion(id, ruta, sesiones);
            sesionList.removeIf(s -> s.getId() == id);
            tableSesion.refresh();
            mostrarAlerta("Se eliminó exitosamente");
        } else {
            mostrarAlerta("No se ha seleccionado ninguna sesión para eliminar.");
        }
    }

    @FXML
    void guardarSesionAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        int min = Integer.parseInt(txtDuracion.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaTexto = txtFecha.getValue().format(formatter);

        // Verificar si la sesión ya existe
        Sesion sesionExistente = SesionCrud.buscarSesion(id, ruta);
        if (sesionExistente != null) {
            mostrarAlerta("Ya existe una sesión con este ID.");
            return;
        }

        // Crear los objetos Deporte y Entrenador si no existen
        if (deporte == null) {
            int deporteId = Integer.parseInt(txtDeporte.getText());
            Type listType = new TypeToken<ArrayList<Deporte>>(){}.getType();
            deporte = MetodosCrud.buscarUsuario(deporteId, "deportes.json", listType);
        }

        if (entrenador == null) {
            int entrenadorId = Integer.parseInt(txtEntrenador.getText());
            Type listType = new TypeToken<ArrayList<Entrenador>>(){}.getType();
            entrenador = MetodosCrud.buscarUsuario(entrenadorId, "entrenadores.json", listType);
        }

        Sesion sesion = new Sesion(id, fechaTexto, min, SesionCrud.estadoAc(fechaTexto), deporte, entrenador);
        SesionCrud.agendarSesion(sesion, ruta, sesiones);

        // Actualizar la lista observable
        sesionList.add(sesion);
        tableSesion.refresh();
        limpiarCampos();
        mostrarAlerta("Se agregó exitosamente");
    }

    @FXML
    void modificarSesionAction(ActionEvent event) {
        Sesion selectedSesion = tableSesion.getSelectionModel().getSelectedItem();
        if (selectedSesion != null) {
            int id = Integer.parseInt(txtId.getText());
            int min = Integer.parseInt(txtDuracion.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaTexto = txtFecha.getValue().format(formatter);

            selectedSesion.setId(id);
            selectedSesion.setDuracionMin(min);
            selectedSesion.setFecha(fechaTexto);
            selectedSesion.setEstado(SesionCrud.estadoAc(fechaTexto));
            selectedSesion.setDeporte(deporte);
            selectedSesion.setEntrenador(entrenador);

            SesionCrud.editarSesion(selectedSesion, ruta, sesiones);
            tableSesion.refresh();
            limpiarCampos();
            mostrarAlerta("Se modificó exitosamente");
        } else {
            mostrarAlerta("No se ha seleccionado ninguna sesión para modificar.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtDeporte.setText("");
        txtDuracion.setText("");
        txtEntrenador.setText("");
        txtFecha.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }
}





