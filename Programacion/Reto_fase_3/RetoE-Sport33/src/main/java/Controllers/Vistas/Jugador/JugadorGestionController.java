package Controllers.Vistas.Jugador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class JugadorGestionController {

    @FXML
    private Button BtnAlta;

    @FXML
    private Button BtnBorrar;

    @FXML
    private Button BtnModificar;

    @FXML
    private Button Btnvolver;

    @FXML
    void onAlta(ActionEvent event) {
        cambiarVista("/com/example/retoesport33/AltaJugador.fxml", "Alta de Jugador");
    }

    @FXML
    void onModificar(ActionEvent event) {
        cambiarVista("/com/example/retoesport33/ModificarJugador.fxml", "Modificar Jugador");
    }

    @FXML
    void onBorrar(ActionEvent event) {
        cambiarVista("/com/example/retoesport33/BajaJugador.fxml", "Baja de Jugador");
    }

    @FXML
    void onVolver(ActionEvent event) {
        cambiarVista("/com/example/retoesport33/menuAdministrador-view.fxml", "Panel Principal");
    }

    private void cambiarVista(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            Stage stage = (Stage) BtnAlta.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

        } catch (IOException e) {
            System.err.println("ERROR: No se pudo cargar el archivo FXML en la ruta: " + fxml);
            e.printStackTrace();
        }
    }
}