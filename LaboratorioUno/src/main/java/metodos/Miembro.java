package metodos;

public class Miembro extends Modelo {
    private String correo;
    private Grupo grupo;

    public Miembro(int id, String nombre, String correo, Grupo grupo) {
        super(id, nombre);
        this.correo = correo;
        this.grupo = grupo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
