module com.example.laboratoriouno {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.laboratoriouno to javafx.fxml;
    opens metodos to javafx.base, com.google.gson; // Abrir 'metodos' a ambos módulos

    exports com.example.laboratoriouno;
}
