package Controllers.Vistas.Jugador;

import DAO.EquipoDAO;

import DAO.JugadorDAO;

import Modelo.Equipo;

import Modelo.Jugador;

import Utilidades.BaseDatos;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javafx.util.StringConverter;



import java.io.IOException;

import java.sql.SQLException;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;

import java.util.List;

import java.util.Locale;



public class AltaJugadorController {



    @FXML

    private Button bGuardar;



    @FXML

    private Button bVolver;



    @FXML

    private ComboBox<Equipo> cbEquipo;



    @FXML

    private ComboBox<String> cbRol;



    @FXML

    private TextField tfApellido;



    @FXML

    private TextField tfNacimiento;



    @FXML

    private TextField tfNacionalidad;



    @FXML

    private TextField tfNickname;



    @FXML

    private TextField tfNombre;



    @FXML

    private TextField tfSueldo;



    private JugadorDAO jugadorDAO;



    @FXML

    private void initialize() {

        jugadorDAO = new JugadorDAO(BaseDatos.getConnection());

        cbRol.setItems(FXCollections.observableArrayList("duelista", "iniciador", "centinela", "controlador"));

        cargarEquipos();



        cbEquipo.setConverter(new StringConverter<Equipo>() {

            @Override

            public String toString(Equipo equipo) {

                return (equipo != null) ? equipo.getNombreEquipo() : "";

            }



            @Override

            public Equipo fromString(String string) {

                return null;

            }

        });

    }



    private void cargarEquipos() {

        try {

            ObservableList<Equipo> equipos = FXCollections.observableArrayList(jugadorDAO.obtenerEquiposDisp());

            cbEquipo.setItems(equipos);

        } catch (SQLException e) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Carga", "No se pudieron cargar los equipos: " + e.getMessage());

        }

    }



    @FXML

    void onGuardar(ActionEvent event) {

        try {

            if (tfNombre.getText().isEmpty() || tfApellido.getText().isEmpty() || tfNickname.getText().isEmpty() ||

                    tfNacionalidad.getText().isEmpty() || tfNacimiento.getText().isEmpty() || tfSueldo.getText().isEmpty() ||

                    cbEquipo.getValue() == null || cbRol.getValue() == null) {

                mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "Todos los campos son obligatorios.");

                return;

            }



            Jugador jugadorNuevo = new Jugador();

            jugadorNuevo.setNombre(tfNombre.getText());

            jugadorNuevo.setApellido(tfApellido.getText());

            jugadorNuevo.setNickname(tfNickname.getText());

            jugadorNuevo.setNacionalidad(tfNacionalidad.getText());

            jugadorNuevo.setFechaNac(LocalDate.parse(tfNacimiento.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)));

            jugadorNuevo.setSueldo(Double.parseDouble(tfSueldo.getText()));

            jugadorNuevo.setRol(cbRol.getValue());

            jugadorNuevo.setEquipo(cbEquipo.getValue());



            jugadorDAO.insertarJugador(jugadorNuevo);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Jugador guardado correctamente.");

            limpiarTodo();

        } catch (DateTimeParseException e) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Fecha", "El formato de fecha debe ser: AAAA-MM-DD");

        } catch (NumberFormatException e) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", "El sueldo debe ser un número válido.");

        } catch (SQLException e) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());

        }

    }



    @FXML

    void onVolver(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/JugadorGestion.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) bVolver.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.setTitle("Gestión de Jugadores");

            stage.show();

        } catch(IOException e) {

            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo volver a la pantalla anterior.");

        }

    }



    private void limpiarTodo() {

        tfNombre.clear();

        tfApellido.clear();

        tfNickname.clear();

        tfNacionalidad.clear();

        tfNacimiento.clear();

        tfSueldo.clear();

        cbEquipo.getSelectionModel().clearSelection();

        cbRol.getSelectionModel().clearSelection();

    }



    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {

        Alert alert = new Alert(tipo);

        alert.setTitle(titulo);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();

    }

}