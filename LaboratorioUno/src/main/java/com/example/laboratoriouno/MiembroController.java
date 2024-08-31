package com.example.laboratoriouno;

import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metodos.Grupo;
import metodos.MetodosCrud;
import metodos.Miembro;
import metodos.Modelo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MiembroController {
    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTipo;

    @FXML
    Type listType = new TypeToken<ArrayList<Modelo>>(){}.getType();
    List<Modelo>m=MetodosCrud.readFromFile("miembros.json",listType);

    @FXML
    private TableView<Modelo> tableMiembros;

    @FXML
    private TableColumn<Modelo, String> tcEspecialidad;

    @FXML
    private TableColumn<Modelo, String> tcNombre;
    String ruta="miembros.json";

   /* @FXML
    public void initialize() {
        // Configura las columnas de la tabla
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcEspecialidad.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // Crea una lista observable de objetos Modelo
        ObservableList<Modelo> observableList = FXCollections.observableArrayList(m);

        // Asigna la lista a la TableView
        tableMiembros.setItems(observableList);
    }*/

    @FXML
    void guardarMiembroAction(ActionEvent event) {
        int id=Integer.parseInt(txtId.getText());
        int op=Integer.parseInt(txtTipo.getText());
        Miembro miembro=new Miembro(id,txtNombre.getText(),txtCorreo.getText(),Grupo.ADULTO);
        if(op==1){
            miembro.setGrupo(Grupo.JUVENIL);
        }
        MetodosCrud.crearUsuario(miembro,ruta,m);
        System.out.print(m.toString());
    }

    @FXML
    void editarMiembroAction(ActionEvent event) {
        int id=Integer.parseInt(txtId.getText());
        int op=Integer.parseInt(txtTipo.getText());
        Miembro miembro=new Miembro(id,txtNombre.getText(),txtCorreo.getText(), Grupo.ADULTO);
        if(op==1){
            miembro.setGrupo(Grupo.JUVENIL);
        }
        MetodosCrud.editarUsuario(miembro,ruta,m);
        System.out.print(m.toString());
    }

    @FXML
    void eliminarMiembroAction(ActionEvent event) {
        int id=Integer.parseInt(txtId.getText());
        MetodosCrud.eliminarUsuario(id,ruta,m);
        System.out.print(m.toString());
    }
}
