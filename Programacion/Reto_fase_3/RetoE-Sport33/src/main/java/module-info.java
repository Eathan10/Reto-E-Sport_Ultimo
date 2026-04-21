module com.example.retoesport33 {

    requires java.desktop;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;

    opens com.example.retoesport33 to javafx.fxml;
    exports com.example.retoesport33;

    exports Controllers;
    opens Controllers to javafx.fxml;

    exports Controllers.Vistas.VistaEquipo;
    opens Controllers.Vistas.VistaEquipo to javafx.fxml;

    exports Controllers.Vistas.VistaAdministradorUsuario;
    opens Controllers.Vistas.VistaAdministradorUsuario to javafx.fxml;

    exports Controllers.Vistas;
    opens Controllers.Vistas to javafx.fxml;

    exports Controllers.Vistas.VistaVer;
    opens Controllers.Vistas.VistaVer to javafx.fxml;

    exports Controllers.Vistas.Jugador;
    opens Controllers.Vistas.Jugador to javafx.fxml;

}