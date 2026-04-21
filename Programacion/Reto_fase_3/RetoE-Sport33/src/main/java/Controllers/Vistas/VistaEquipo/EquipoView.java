package Controllers.Vistas.VistaEquipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EquipoView {

    @FXML
    private Button btnAlta;

    @FXML
    private Button btnBaja;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnVolver;

    @FXML
    void onActualizar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/ModificarEquipo-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnModificar.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Modificar Equipo");
        stage.show();


    }


    @FXML
    void onCrear(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/AltaEquipo-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnAlta.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Crear Equipo");
        stage.show();

    }

    @FXML
    void onEliminar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/BajaEquipo-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnBaja.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Eliminar Equipo");
        stage.show();




    }






    @FXML
    void onSalir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onVolver(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/menuAdministrador-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú de Usuario");
            stage.show();

    }




}
