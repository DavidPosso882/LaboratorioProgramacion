package metodos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MetodosCrud {
    //String ruta="miembros.json";

//Clase general para los metodos crud de las clases que hereden de modelo
    public MetodosCrud() {
    }

    //metodo para crear un usuario
    public static void crearUsuario(Modelo modelo,String ruta,List<Modelo>usuarios){
        if (usuarios == null) {
            usuarios = new ArrayList<>(); // Inicializa la lista si es nula
        }
        usuarios.add(modelo);
        saveToFile(usuarios,ruta);
    }

    //editar usuario por id
    public static void editarUsuario(Modelo modelo,String ruta,List<Modelo>usuarios){
        //List<Modelo> usuarios=readFromFile(ruta);
        for(int i=0; i<usuarios.size();i++){
            if(usuarios.get(i).getId()==modelo.getId()){
                usuarios.set(i, modelo);
                break;
            }
        }
        saveToFile(usuarios,ruta);
    }

    public static void eliminarUsuario(int id,String ruta,List<Modelo>usuarios){
        //usuarios=readFromFile(ruta);
        for(int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getId()==id){
                usuarios.remove(i);
            }
        }
        //usuarios.removeIf(usuario -> usuario.getId() == id);
        saveToFile(usuarios,ruta);
    }

    //buscar objeto por id retorna un objeto
    public static <T extends Modelo> T buscarUsuario(int id, String ruta, Type type){
        List<T> usuarios=readFromFile(ruta,type);
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

    //Este metodo guarda la lista en el archivo json
    public static void saveToFile(List<Modelo> usuarios,String ruta) {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();

        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(usuarios,writer);
            System.out.println("Archivo JSON guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo JSON: " + e.getMessage());
        }
    }
    //metodo para convertir el json a List
    public static <T> List<T> readFromFile(String ruta,Type userListType ) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ruta)) {
            //Type userListType = new TypeToken<ArrayList<Modelo>>() {
           //z }.getType();
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

    public static Dificultad dificultadC(int entrada){
        if(entrada==1){
            return Dificultad.BAJO;
        }
        else if(entrada==2){
            return Dificultad.MEDIO;
        }
        else{
            return Dificultad.ALTO;
        }
    }
}



