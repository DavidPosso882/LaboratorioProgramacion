package metodos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sesion {
    private LocalDateTime fecha;
    private int tiempoMin;
    public Estado estado;
    public Deporte deporte;
    public Entrenador entrenador;

    public Sesion(LocalDateTime fecha, int tiempoMin, Estado estado, Deporte deporte, Entrenador entrenador) {
        this.fecha = fecha;
        this.tiempoMin = tiempoMin;
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

    public int getTiempoMin() {
        return tiempoMin;
    }

    public void setTiempoMin(int tiempoMin) {
        this.tiempoMin = tiempoMin;
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

    public static ArrayList<Sesion> asociarDeporteEntrenador(Deporte deporte, Entrenador entrenador) {
        ArrayList<Sesion> sesion = new ArrayList<>();
        return sesion;
    }
}
