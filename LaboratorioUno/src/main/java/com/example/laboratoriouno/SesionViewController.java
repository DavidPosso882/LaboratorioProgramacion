package com.example.laboratoriouno;

import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TableView<?> tableSesion;

    @FXML
    private TableColumn<?, ?> tcDeporte;

    @FXML
    private TableColumn<?, ?> tcEntrenador;

    @FXML
    private TableColumn<?, ?> tcFecha;

    @FXML
    private TextField txtDeporte;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtEntrenador;
    @FXML
    private DatePicker txtFecha;

    String ruta="sesiones.json";
    List<Sesion>sesiones=SesionCrud.readFromFile(ruta);
    Deporte deporte;
    Entrenador entrenador;
    Sesion sesion=new Sesion();

    @FXML
    void eliminarSesionAction(ActionEvent event) {
        int id=Integer.parseInt(txtId.getText());
        SesionCrud.eliminarSesion(id,ruta,sesiones);
    }

    @FXML
    void guardarSesionAction(ActionEvent event) {
        int id=Integer.parseInt(txtId.getText());
        int min=Integer.parseInt(txtDuracion.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaTexto = txtFecha.getValue().format(formatter);

        //if(deporte==null||entrenador==null){
            System.out.print(txtFecha);
        //}
        //else{
            Sesion sesion=new Sesion(id,fechaTexto,min,SesionCrud.estadoAc(fechaTexto),deporte,entrenador);
            SesionCrud.agendarSesion(sesion,ruta,sesiones);
        //}

    }



    @FXML
    void modificarSesionAction(ActionEvent event) {
        int id=Integer.parseInt(txtId.getText());
        int min=Integer.parseInt(txtDuracion.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaTexto = txtFecha.getValue().format(formatter);
        Sesion sesion=new Sesion(id,fechaTexto,min,SesionCrud.estadoAc(fechaTexto),deporte,entrenador);
        SesionCrud.editarSesion(sesion,ruta,sesiones);
    }

    @FXML
    void addEntrenador(ActionEvent event) {
        int id=Integer.parseInt(txtEntrenador.getText());
        Type listType = new TypeToken<ArrayList<Entrenador>>(){}.getType();
        entrenador= MetodosCrud.buscarUsuario(id,"miembros.json",listType);
    }
    @FXML
    void addDeporte(ActionEvent event) {
        int id=Integer.parseInt(txtDeporte.getText());
        Type listType = new TypeToken<ArrayList<Deporte>>(){}.getType();
        deporte=MetodosCrud.buscarUsuario(id,"deportes.json",listType);

    }
    @FXML
    void buscarSesion(ActionEvent event) {
        int id=Integer.parseInt(txtId.getText());
        Type listType = new TypeToken<ArrayList<Sesion>>(){}.getType();
        Sesion s=SesionCrud.buscarSesion(id,ruta);
        if(s==null){
            System.out.print("No se encontro coincidencia");
        }
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(s.getFecha(),formatter);

            txtFecha.setValue(fecha);
            txtDuracion.setText(String.valueOf(s.getDuracionMin()));
            if(s.getDeporte()==null||s.getEntrenador()==null){
                System.out.print("entrenador o deporte son nulos");
            }
            else{
                txtEntrenador.setText(Integer.toString(s.getEntrenador().getId()));
                txtDeporte.setText(Integer.toString(s.getDeporte().getId()));
            }



        }
    }
}
