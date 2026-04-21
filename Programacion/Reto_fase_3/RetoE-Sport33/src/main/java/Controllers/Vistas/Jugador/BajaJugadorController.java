
package Controllers.Vistas.Jugador;

import DAO.JugadorDAO;

import Utilidades.BaseDatos;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.ButtonType;

import javafx.scene.control.TextField;

import javafx.stage.Stage;



import java.io.IOException;

import java.util.Optional;



public class BajaJugadorController {



    @FXML

    private Button BtnEliminar;



    @FXML

    private Button BtnVolver;



    @FXML

    private TextField tfNicknameBusqueda;



    private JugadorDAO jugadorDAO;



    @FXML

    private void initialize() {

        jugadorDAO = new JugadorDAO(BaseDatos.getConnection());

    }



    @FXML

    void onEliminar(ActionEvent event) {

        String nickname = tfNicknameBusqueda.getText().trim();

        if (nickname.isEmpty()) {

            mostrarAlerta(Alert.AlertType.WARNING, "Campo vacío", "El nickname no puede estar vacío."); return;

        }



        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Confirmación de eliminación");

        confirmacion.setHeaderText("¿Estás seguro de que deseas eliminar al jugador con nickname: " + nickname + "?");

        confirmacion.setContentText("Esta acción no se puede deshacer.");



        Optional<ButtonType> resultado = confirmacion.showAndWait();



        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

            try {

                jugadorDAO.eliminarJugador(nickname);



                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Jugador '" + nickname + "' eliminado correctamente.");

                tfNicknameBusqueda.clear();



            } catch (Exception e) {

                mostrarAlerta(Alert.AlertType.ERROR, "Error al eliminar", e.getMessage());

            }

        }

    }



    @FXML

    void onVolver(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/JugadorGestion.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) BtnVolver.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.setTitle("Gestión de Jugadores");

            stage.show();

        } catch (IOException e) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Navegación", "No se pudo volver a la pantalla anterior.");

        }

    }



    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);

        alerta.setHeaderText(null);

        alerta.setContentText(mensaje);

        alerta.showAndWait();

    }

}