package com.example.laboratoriouno;

import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import metodos.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DeporteController {
    @FXML
    private Button btnEliminarDeporte;

    @FXML
    private Button btnGuardarDeporte;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombreDeporte;
    @FXML
    private TextField txtDescriptionDeporte;
    @FXML
    private TextField txtDificultad;
    @FXML
    private TextField txtIdEntrenador;


    @FXML
    private TableView<Modelo> tableMiembros;

    @FXML
    private TableColumn<Modelo, String> tcEspecialidad;

    @FXML
    private TableColumn<Modelo, String> tcNombre;
    String ruta="deportes.json";
    @FXML
    Type listType = new TypeToken<ArrayList<Deporte>>(){}.getType();
    List<Modelo> m= MetodosCrud.readFromFile(ruta,listType);
    Deporte deporte=new Deporte();
    //odelo mo;

    @FXML
    void agregarEntrenador(ActionEvent event) {
        int id = Integer.parseInt(txtIdEntrenador.getText());
        Type listType = new TypeToken<ArrayList<Modelo>>(){}.getType();
         Modelo mo= MetodosCrud.buscarUsuario(id,"miembros.json",listType);
         deporte.entrenadores.add(mo);
    }

    @FXML
    void crearDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        int op = Integer.parseInt(txtDificultad.getText());
        //Deporte deporte=new Deporte(id,txtNombreDeporte.getText(),txtDescriptionDeporte.getText(),MetodosCrud.dificultadC(op));
        //deporte.entrenadores.add(mo);
        deporte.setId(id);
        deporte.setNombre(txtNombreDeporte.getText());
        deporte.setDificultad(MetodosCrud.dificultadC(op));
        deporte.setDescripcion(txtDescriptionDeporte.getText());
        MetodosCrud.crearUsuario(deporte,ruta,m);
        System.out.print(m.toString());
        deporte=new Deporte();
        txtDificultad.setText("");
        txtDescriptionDeporte.setText("");
        txtId.setText("");
        txtNombreDeporte.setText("");
        txtIdEntrenador.setText("");
        //mo=new Modelo();
    }

    @FXML
    void editarDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        int op = Integer.parseInt(txtDificultad.getText());
        //Deporte deporte=new Deporte(id,txtNombreDeporte.getText(),txtDescriptionDeporte.getText(),MetodosCrud.dificultadC(op));
        //deporte.entrenadores.add(mo);
        deporte.setId(id);
        deporte.setNombre(txtNombreDeporte.getText());
        deporte.setDificultad(MetodosCrud.dificultadC(op));
        deporte.setDescripcion(txtDescriptionDeporte.getText());
        MetodosCrud.editarUsuario(deporte,ruta,m);
        deporte=new Deporte();
    }

    @FXML
    void eliminarDeporte(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        MetodosCrud.eliminarUsuario(id,ruta,m);
    }
}
