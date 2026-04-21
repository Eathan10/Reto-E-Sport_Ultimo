package Controllers.Vistas.VistaVer;
import Controllers.verEquiposController;
import Modelo.Equipo;
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
import java.time.LocalDate;
import java.util.List;

public class verEquiposView {

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Equipo, String> tbCodigo;

    @FXML
    private TableColumn<Equipo, LocalDate> tbFechaFundacion;

    @FXML
    private TableColumn<Equipo, String> tbJugador;

    @FXML
    private TableColumn<Equipo, String> tbNombre;

    @FXML
    private TableView<Equipo> tablaEquipos;


    @FXML
    void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/usuario-view.fxml"));
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

    private verEquiposController logicController = new verEquiposController();

    @FXML
    public void initialize() {
        tbCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tbNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbFechaFundacion.setCellValueFactory(new PropertyValueFactory<>("fechaFundacion"));

        // llama al controllador
        List<Equipo> lista = logicController.getEquiposParaMostrar();
        tablaEquipos.getItems().addAll(lista);
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}



