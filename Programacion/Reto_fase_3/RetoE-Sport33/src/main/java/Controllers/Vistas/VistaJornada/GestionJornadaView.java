package Controllers.Vistas.VistaJornada;

import Modelo.Jornada;
import Modelo.Partido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class GestionJornadaView {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField tfMNumeroJornada;

    @FXML
    void onCancelar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/menuAdministrador-view.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setTitle("Menu de Administrador");
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){
            mostrarAlerta("Error al cargar la ventana", "No se pudo abrir la ventana de competicion.");
        }
    }



    @FXML
    void onGuardar(ActionEvent event) {
        String numJornadaStr = tfMNumeroJornada.getText().trim();
        LocalDate fecha = dpFecha.getValue();

        if (!numJornadaStr.isEmpty() && fecha != null) {
            try {
                int numJornada = Integer.parseInt(numJornadaStr);
                Jornada jornadaNueva = new Jornada(numJornada, fecha);

                Controllers.JornadaController.crearJornada(jornadaNueva);

                // alerta
                Alert exito = new Alert(Alert.AlertType.INFORMATION);
                exito.setTitle("Éxito");
                exito.setHeaderText(null);
                exito.setContentText("La jornada " + numJornada + " se ha guardado correctamente.");
                exito.showAndWait();


                Stage actual = (Stage) btnGuardar.getScene().getWindow();
                actual.close();

            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El número de jornada debe ser un número.");
            }
        } else {
            mostrarAlerta("Campos vacíos", "Rellena el número y la fecha.");
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
