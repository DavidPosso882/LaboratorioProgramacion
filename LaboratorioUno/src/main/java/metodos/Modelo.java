package metodos;

//Clase padre para las clases que puedan extender de esta y usar los metodos crud generalizados
public class Modelo {
    private int id;
    private String nombre;

    public Modelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Modelo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
