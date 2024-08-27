package metodos;

import java.io.*;
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
    public void enlistarSesion(Sesion sesion){
        listaSesiones.add(sesion);
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
