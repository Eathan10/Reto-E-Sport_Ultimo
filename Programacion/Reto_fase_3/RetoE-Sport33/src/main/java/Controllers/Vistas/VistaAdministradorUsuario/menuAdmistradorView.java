package Controllers.Vistas.VistaAdministradorUsuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class  menuAdmistradorView {

    @FXML
    private Button btnEquipo;

    @FXML
    private Button btnJugador;

    @FXML
    private Button btnCompeticion;

    @FXML
    private Button btnPartido;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnVerJugadores;

    @FXML
    private Button btnVerEquipos;




    @FXML
    void onEquipo(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/Equipo-view.fxml", "Gestion de Equipo")  ;

    }

    @FXML
    void onJornada(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/GestionJornada-view.fxml", "Gestion de Joirnadas")  ;

    }

    @FXML
    void onPartidos(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/partido-view.fxml", "Gestion de Partidos")  ;

    }


    @FXML
    void onJugador(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/jugadorVista.fxml", "Gestión de Jugadores")  ;
    }


    @FXML
    void onCompeticion(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/GestionJornada.fxml", "Gestión de Jornadas")  ;
    }

    @FXML
    void onVerJugadores(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/verJugadores.fxml", "Lista de Jugadores")  ;
    }

    @FXML
    void onVerEquipos(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/verEquipos.fxml", "Lista de Equipos")  ;
    }


    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = (Stage) btnEquipo.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error al cargar la ventana", "No se pudo abrir la ventana de Menu del administrador.");
        }
    }


    @FXML
    void onVolver(ActionEvent event) {
        abrirVentana("/com/example/retoesport33/administrador.fxml", "Login Administrador");
    }







    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
