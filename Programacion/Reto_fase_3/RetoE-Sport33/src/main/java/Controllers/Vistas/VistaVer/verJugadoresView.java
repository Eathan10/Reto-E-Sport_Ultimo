package Controllers.Vistas.VistaVer;

import Controllers.verJugadpresController;
import Modelo.Jugador;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class verJugadoresView {

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<?, ?> tbApellido;

    @FXML
    private TableColumn<Jugador, Integer> tbCodigo;

    @FXML
    private TableColumn<Jugador, String> tbEquipo;

    @FXML
    private TableColumn<Jugador, LocalDate> tbFechaNac;

    @FXML
    private TableColumn<Jugador, String> tbNick;

    @FXML
    private TableColumn<Jugador, String> tbNombre;

    @FXML
    private TableColumn<Jugador, String> tbRol;

    @FXML
    private TableColumn<Jugador, Double> tbSueldo;

    @FXML
    private TableView<Jugador> tablaJugadores;



    @FXML
    void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/perfil-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú de Usuario");
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error de Navegación", "No se pudo cargar la ventana de usuario.");
            e.printStackTrace();
        }
    }

    private verJugadpresController logicController = new verJugadpresController();

    @FXML
    public void initialize() throws SQLException {
        tbCodigo.setCellValueFactory(new PropertyValueFactory<>("codJugador"));
        tbNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tbNick.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        tbRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        tbFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
        tbSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        tbEquipo.setCellValueFactory(cellData -> {
            if (cellData.getValue().getEquipo() != null) {
                return new SimpleStringProperty(cellData.getValue().getEquipo().getCodigoEquipo());
            }
            return new SimpleStringProperty("N/A");
        });

        // para llamr al controller
        cargarDatos();
    }

    // para llamr al controller
    private void cargarDatos() throws SQLException {
        List<Jugador> lista = logicController.getListaParaLaTabla();
        if (lista != null) {
            tablaJugadores.getItems().setAll(lista);
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
