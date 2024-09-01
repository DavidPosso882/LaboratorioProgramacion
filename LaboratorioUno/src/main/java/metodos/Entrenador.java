package metodos;

import java.io.*;
import java.util.ArrayList;

public class Entrenador extends Modelo {

    private String especialidad;
    public ArrayList<Sesion> listaSesiones;

    public Entrenador(int id, String nombre, String especialidad, ArrayList<Sesion> listaSesiones) {
        super(id, nombre);
        this.especialidad = especialidad;
        this.listaSesiones = listaSesiones;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public ArrayList<Sesion> getListaSesiones() {
        return listaSesiones;
    }

    public void setListaSesiones(ArrayList<Sesion> listaSesiones) {
        this.listaSesiones = listaSesiones;
    }
}
