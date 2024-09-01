package com.example.laboratoriouno;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorViewController {

    ArrayList<Sesion> listaSesiones;
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableView<Entrenador> tableEmpleados;

    @FXML
    private TableColumn<Entrenador, String> tcEspecialidad;

    @FXML
    private TableColumn<Entrenador, String> tcNombre;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtId;
    private String ruta = "entrenadores.json";
    private Type listType = new TypeToken<ArrayList<Entrenador>>(){}.getType();
    private List<Entrenador> entrenadores = MetodosCrud.readFromFile(ruta, listType);
    private ObservableList<Entrenador> entrenadorList = FXCollections.observableArrayList(entrenadores);

    @FXML
    private void initialize() {
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));

        tableEmpleados.setItems(entrenadorList);
    }
    

    @FXML
    void eliminarEntrenadorAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        MetodosCrud.eliminarUsuario(id, ruta, (List<Modelo>) (List<?>) entrenadores);
        entrenadorList.removeIf(entrenador -> entrenador.getId() == id);
        tableEmpleados.refresh();
        limpiarCampos();
        mostrarAlerta("Entrenador eliminado exitosamente");
    }

    @FXML
    void guardarEntrenadorAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        Entrenador entrenador = new Entrenador(id, txtNombre.getText(), txtEspecialidad.getText(), listaSesiones);
        MetodosCrud.crearUsuario(entrenador, ruta, (List<Modelo>) (List<?>) entrenadores);
        entrenadorList.add(entrenador);
        tableEmpleados.refresh();
        limpiarCampos();
        mostrarAlerta("Entrenador guardado exitosamente");
    }

    @FXML
    void modificarEntrenadorAction(ActionEvent event) {
        Entrenador selectedEntrenador = tableEmpleados.getSelectionModel().getSelectedItem();
        if (selectedEntrenador != null) {
            int id = Integer.parseInt(txtId.getText());
            selectedEntrenador.setId(id);
            selectedEntrenador.setNombre(txtNombre.getText());
            selectedEntrenador.setEspecialidad(txtEspecialidad.getText());

            MetodosCrud.editarUsuario(selectedEntrenador, ruta, (List<Modelo>) (List<?>) entrenadores);
            entrenadorList.set(entrenadorList.indexOf(selectedEntrenador), selectedEntrenador);
            tableEmpleados.refresh();
            limpiarCampos();
            mostrarAlerta("Entrenador modificado exitosamente");
        } else {
            mostrarAlerta("No se ha seleccionado ningún entrenador para modificar.");
        }
    }

    @FXML
    void buscarEntrenador(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        Entrenador entrenador = MetodosCrud.buscarUsuario(id, ruta, listType);
        if (entrenador == null) {
            mostrarAlerta("No se encontraron resultados");
        } else {
            txtNombre.setText(entrenador.getNombre());
            txtEspecialidad.setText(entrenador.getEspecialidad());
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEspecialidad.setText("");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
        alerta.setTitle("Información");
        alerta.show();
    }

}
