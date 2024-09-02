package com.example.laboratoriouno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import metodos.Administrador;
import metodos.MetodosCrud;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public  void abrirVEntrenador() {
        try {
            // Cargar el archivo FXML de la ventana secundaria
            FXMLLoader loader = new FXMLLoader(getClass().getResource("entrenador-view.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana)
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Crud entrenadores");
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.show(); // Mostrar la nueva ventana
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public  void abrirVSesion() {
        try {
            // Cargar el archivo FXML de la ventana secundaria
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sesion-view.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana)
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Sesiones");
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.show(); // Mostrar la nueva ventana
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void abrirVMiembro() {
        try {
            // Cargar el archivo FXML de la ventana secundaria
            FXMLLoader loader = new FXMLLoader(getClass().getResource("miembro-view.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana)
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Crud Miembros");
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.show(); // Mostrar la nueva ventana
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void abrirVDeporte() {
        try {
            // Cargar el archivo FXML de la ventana secundaria
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Deporte-view.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana)
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Crud deportes");
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.show(); // Mostrar la nueva ventana
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void guardarAdmin() {
        Administrador admin=new Administrador(Integer.parseInt(txtId.getText()),txtNombre.getText());
        MetodosCrud.crearUsuario(admin,"administradores.json",null);
        mostrarAlerta("Administrador se registro correctamente");
    }
    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }

}



