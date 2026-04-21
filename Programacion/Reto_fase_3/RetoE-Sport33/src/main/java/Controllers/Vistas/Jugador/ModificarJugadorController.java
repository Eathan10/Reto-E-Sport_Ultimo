package Controllers.Vistas.Jugador;

import DAO.JugadorDAO;
import Modelo.Equipo;
import Modelo.Jugador;
import Utilidades.BaseDatos;
import javafx.collections.FXCollections;
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

public class  ModificarJugadorController {

    @FXML
    private Button BtnModificar;

    @FXML
    private Button BtnVolver;

    @FXML
    private Button btnBuscar;

    @FXML
    private ComboBox<Equipo> cbEquipo;

    @FXML
    private ComboBox<String> cbRol;

    @FXML
    private TextField tfApellido;

    @FXML
    private TextField tfBusqueda;

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
        try {
            jugadorDAO = new JugadorDAO(BaseDatos.getConnection());
            cargarLosEquipos();
        } catch (Exception e) {
            System.out.println("Error de conexión en Modificar: " + e.getMessage());
        }
        cbRol.setItems(FXCollections.observableArrayList("duelista", "iniciador", "centinela", "controlador"));
        confirmarComboEquipos();
    }

    @FXML
    void onBuscar(ActionEvent event) {
        String busquedaNickName = tfBusqueda.getText().trim();
        if (busquedaNickName.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Busqueda", "Introduzca un nickname para buscar");
            return;
        }

        try {
            Jugador jugador = jugadorDAO.buscarJugadorPorNickname(busquedaNickName);

            if (jugador != null) {
                tfNombre.setText(jugador.getNombre());
                tfApellido.setText(jugador.getApellido());
                tfNickname.setText(jugador.getNickname());
                tfNacionalidad.setText(jugador.getNacionalidad());
                tfNacimiento.setText(jugador.getFechaNac().toString());
                tfSueldo.setText(String.valueOf(jugador.getSueldo()));
                cbRol.setValue(jugador.getRol());

                for (Equipo equipo : cbEquipo.getItems()) {
                    if (equipo.getCodigoEquipo().equals(jugador.getEquipo().getCodigoEquipo())) {
                        cbEquipo.setValue(equipo);
                        break;
                    }
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "No encontrado", "No se encontró ningún jugador con ese nickname");
            }
        }catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al buscar el jugador: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onModificar(ActionEvent event) {
        try {
            if (tfNombre.getText().isEmpty() || tfNickname.getText().isEmpty() || cbEquipo.getValue() == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Validación", "Rellena los campos obligatorios.");
                return;
            }

            Jugador jugador = new Jugador();
            jugador.setNombre(tfNombre.getText());
            jugador.setApellido(tfApellido.getText());
            jugador.setNickname(tfNickname.getText());
            jugador.setNacionalidad(tfNacionalidad.getText());
            jugador.setFechaNac(LocalDate.parse(tfNacimiento.getText()));
            jugador.setSueldo(Double.parseDouble(tfSueldo.getText()));
            jugador.setRol(cbRol.getValue());
            jugador.setEquipo(cbEquipo.getValue());

            jugadorDAO.actualizarJugador(jugador);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Jugador modificado correctamente");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al modificar el jugador: " + e.getMessage());
        }
    }

    @FXML
    void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/JugadorGestion.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Jugadores");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo volver atrás.");
        }
    }

    private void cargarLosEquipos() {
        try {
            cbEquipo.setItems(FXCollections.observableArrayList(jugadorDAO.obtenerEquiposDisp()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void confirmarComboEquipos() {
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
