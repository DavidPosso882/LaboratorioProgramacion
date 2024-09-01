package metodos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SesionCrud {

    public static void agendarSesion(Sesion modelo,String ruta,List<Sesion>sesiones){
        if (sesiones == null) {
            sesiones = new ArrayList<>(); // Inicializa la lista si es nula
        }
        sesiones.add(modelo);
        saveToFile(sesiones,ruta);
    }

    public static void editarSesion(Sesion modelo,String ruta,List<Sesion>sesiones){
        //List<Modelo> usuarios=readFromFile(ruta);
        for(int i=0; i<sesiones.size();i++){
            if(sesiones.get(i).equals(modelo)){
                sesiones.set(i, modelo);
                break;
            }
        }
        saveToFile(sesiones,ruta);
    }

    public static void eliminarSesion(int id,String ruta,List<Sesion>sesiones){
        //usuarios=readFromFile(ruta);
        for(int i=0;i<sesiones.size();i++){
            if(sesiones.get(i).getId()==id){
                sesiones.remove(i);
            }
        }
        //usuarios.removeIf(usuario -> usuario.getId() == id);
        saveToFile(sesiones,ruta);
    }

    public static Estado estadoAc(String fecha){

        // Crear un formateador para el formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Convertir las cadenas a LocalDate y comparar directamente
        if (LocalDate.parse(fecha, formatter).isBefore(LocalDate.now())) {
            //System.out.println(fecha + " es anterior a " + fecha2);
            return Estado.COMPLETADA;
        } else if (LocalDate.parse(fecha, formatter).isAfter(LocalDate.now())) {
            //System.out.println(fecha + " es posterior a " + fecha2);
            return Estado.PROGRAMADA;
        } else {
            //System.out.println(fecha + " es igual a " + fecha2);
            return Estado.PROGRAMADA;
        }
    }

    //Este metodo guarda la lista en el archivo json
    public static void saveToFile(List<Sesion> usuarios,String ruta) {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();

        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(usuarios,writer);
            System.out.println("Archivo JSON guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo JSON: " + e.getMessage());
        }
    }

    //metodo para convertir el json a List
    public static List<Sesion> readFromFile(String ruta) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ruta)) {
            Type userListType = new TypeToken<ArrayList<Sesion>>() {
            }.getType();
           /* if (userListType == null) {
                System.out.print("noooo");
                ArrayList<Modelo>m=new ArrayList<>();
                return m;
            } else {
                return gson.fromJson(reader, userListType);
            }*/
            return gson.fromJson(reader, userListType);

        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo JSON: " + e.getMessage());
            return new ArrayList<>(); // Retorna lista vacía si ocurre un error
        }

    }

    public static Sesion buscarSesion(int id, String ruta){
        List<Sesion> usuarios=readFromFile(ruta);
        //T us=new T();
        for(int i=0; i<usuarios.size();i++){
            if(usuarios.get(i).getId()==id){
                //us=usuarios.get(i);
                //break;
                return usuarios.get(i);
            }
        }
        return null;
    }

    }
