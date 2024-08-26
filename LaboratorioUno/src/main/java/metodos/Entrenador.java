package metodos;

import java.util.ArrayList;

public class Entrenador {
    private String nombre;
    private String especialidad;
    public ArrayList<Sesion> listaSesiones;

    public Entrenador(String nombre, String especialidad, ArrayList<Sesion> listaSesiones) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.listaSesiones = listaSesiones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
