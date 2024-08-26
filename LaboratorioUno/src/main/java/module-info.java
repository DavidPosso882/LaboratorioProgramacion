module com.example.laboratoriouno {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laboratoriouno to javafx.fxml;
    exports com.example.laboratoriouno;
}