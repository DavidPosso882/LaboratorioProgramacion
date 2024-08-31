package metodos;

import java.util.ArrayList;

public class Deporte extends Modelo {

    private String descripcion;
    private Dificultad dificultad;

    public ArrayList<Modelo>entrenadores;

    public Deporte(int id, String nombre, String descripcion, Dificultad dificultad) {
        super(id, nombre);
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.entrenadores=new ArrayList<>();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public ArrayList<Modelo> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(ArrayList<Modelo> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public Deporte() {
        this.entrenadores=new ArrayList<>();
    }
}



