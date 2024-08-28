package com.example.laboratoriouno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.Entrenador;
import metodos.Sesion;

import java.io.*;
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

    public static ArrayList<Entrenador> leerEntrenador() {
        ArrayList<Entrenador> listaEntrenador = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(archivoEntrenador));
            String linea;
            try {
                while ((linea = reader.readLine()) != null){
                    String[] datos = linea.split(";");
                    String nombre = datos[0];
                    String especialidad = datos[1];
                    ArrayList<Sesion> listaSesiones = new ArrayList<>();
                    listaEntrenador.add(new Entrenador(nombre, especialidad, listaSesiones));
                }
            } catch (Throwable var13) {
                try {
                    reader.close();
                } catch (Throwable var12) {
                    var13.addSuppressed(var12);
                }

                throw var13;
            }

            reader.close();
        } catch (IOException var14) {
            try {
                new BufferedWriter(new FileWriter(archivoEntrenador, true));
            } catch (IOException var11) {
                throw new RuntimeException();
            }
        }

        return listaEntrenador;
    }
}
