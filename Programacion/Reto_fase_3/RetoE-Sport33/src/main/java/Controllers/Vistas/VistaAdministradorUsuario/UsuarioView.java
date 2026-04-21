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

public class UsuarioView{

    @FXML
    private Button btnVerEquipos;

    @FXML
    private Button btnVerJugadores;

    @FXML
    private Button btnVolver;

    @FXML
    void onVerEquipos(ActionEvent event) {
        cambiarVentana("/com/example/retoesport33/verEquipos.fxml", "Lista de Equipos");
    }


    @FXML
    void onVerJugadores(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/verJugadores.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVerJugadores.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Jugadores");
            stage.show();

        } catch (IOException e) {

            mostrarAlerta("Error al cargar la ventana", "No se pudo abrir la ventana de Menu del administrador.");
        }
    }

    @FXML
    void onVolver(ActionEvent event) {
        cambiarVentana("/com/example/retoesport33/perfil-view.fxml", "Selección de Perfil");
    }




    private void cambiarVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = (Stage) btnVerEquipos.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error al cargar la ventana", "No se pudo abrir la ventana de Menu del administrador.");
        }

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
