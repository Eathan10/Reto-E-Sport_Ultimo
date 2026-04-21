package Controllers.Vistas.VistaEquipo;

import Controllers.EquipoController;
import Modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class AltaEquipoView {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnSalir;

    @FXML
    private DatePicker dpFechaFundacion;

    @FXML
    private ListView<Jugador> lvJugadores;

    @FXML
    private TextField tfCodigoEquipo;

    @FXML
    private TextField tfNombreEquipo;


    @FXML
    public void initialize() {
        // puedan elegir varios jugadores a la vez
        lvJugadores.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        lvJugadores.getItems().addAll(EquipoController.obtenerTodosLosJugadores());
    }

    @FXML
    void onAceptar(ActionEvent event) {
        String NombreEquipo = tfNombreEquipo.getText();
        String CodigoEquipo = tfCodigoEquipo.getText();
        LocalDate FechaFundacion = dpFechaFundacion.getValue();

        ArrayList<Jugador> seleccionados = new ArrayList<>(lvJugadores.getSelectionModel().getSelectedItems());

        if (seleccionados.size() < 2 || seleccionados.size() > 6) {
            try{
                EquipoController.insertarEquipo(NombreEquipo, CodigoEquipo, FechaFundacion, seleccionados);
                confirmarAccion( "Equipo" + NombreEquipo + "creado correctamente");

                limpiarFormulario();
            } catch (Exception e){
                mostrarAlerta("Error al crear el equipo: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }else{
            mostrarAlerta("El equipo debe tener entre 2 y 6 jugadores. Has seleccionado: " + seleccionados.size(), Alert.AlertType.ERROR);
        }


    }


    @FXML
    void onVolver(ActionEvent event) {
        equipoView.show();
        stage.close();
    }

    private Stage stage;
    private EquipoView equipoView;
    public void init(Stage stage, EquipoView equipoView) {
        this.stage = stage;
        this.equipoView = equipoView;

    }

    private void limpiarFormulario() {
        tfNombreEquipo.clear();
        tfCodigoEquipo.clear();
        dpFechaFundacion.setValue(null);
        lvJugadores.getSelectionModel().clearSelection();
    }


    private void mostrarAlerta(String s, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    private boolean confirmarAccion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar acción");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        return alert.showAndWait().filter(r -> r == ButtonType.OK).isPresent();
    }
}
