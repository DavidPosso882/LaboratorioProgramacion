package metodos;

import java.time.LocalDateTime;

public class Sesion {
    private LocalDateTime fecha;
    private int duracionMin;
    public Estado estado;
    public Deporte deporte;
    public Entrenador entrenador;

    public Sesion(LocalDateTime fecha, int duracionMin, Estado estado, Deporte deporte, Entrenador entrenador) {
        this.fecha = fecha;
        this.duracionMin = duracionMin;
        this.estado = estado;
        this.deporte = deporte;
        this.entrenador = entrenador;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void crearSesionEntrenamiento(LocalDateTime fecha,int duracionMin,Estado estado,Deporte deporte, Entrenador entrenador) {
        Entrenador entrenadorActual = ;
    }
}
