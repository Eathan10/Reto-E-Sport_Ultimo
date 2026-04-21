package Controllers.Vistas.Jugador;

import DAO.JugadorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;




import DAO.JugadorDAO;

import Modelo.Jugador;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;

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



    private BorderPane panelPrincipal;



    @FXML

    void onAlta(ActionEvent event) {

        cambiarVista("/com/example/retoesport33/AltaJugador.fxml", "Alta de Jugador");

    }



    @FXML

    void onBorrar(ActionEvent event) {

        cambiarVista("/com/example/retoesport33/BajaJugador.fxml", "Baja de Jugador");

    }



    @FXML

    void onModificar(ActionEvent event) {

        cambiarVista("/com/example/retoesport33/ModificarJugador.fxml", "Modificar de Jugador");

    }



    @FXML

    void onVolver(ActionEvent event) {

        cambiarVista("/com/example/retoesport33/panelPrincipalView.fxml", "Panel Principal");

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

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Navegación", "No se pudo cargar la vista: " + fxml);

            e.printStackTrace();

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