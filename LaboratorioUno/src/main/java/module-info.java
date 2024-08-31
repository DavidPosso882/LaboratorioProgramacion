module com.example.laboratoriouno {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.laboratoriouno to javafx.fxml;
    exports com.example.laboratoriouno;


    // Exporta el paquete 'metodos' al módulo 'com.google.gson'
    opens metodos to com.google.gson;
}