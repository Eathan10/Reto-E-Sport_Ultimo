package Controllers.Vistas.Competicion;

import DAO.CompeticionDAO;
import Modelo.Competicion;
import Utilidades.BaseDatos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CompeticionGestionController {

    @FXML
    private Button BtnAlta;

    @FXML
    private Button BtnEliminar;

    @FXML
    private Button BtnModificar;

    @FXML
    private Button BtnVolver;

    @FXML
    private TableColumn<Competicion, Integer> colCodigo;

    @FXML
    private TableColumn<Competicion, String> colEstado;

    @FXML
    private TableColumn<Competicion, String> colNombre;

    @FXML
    private TableColumn<Competicion, Double> colPremio;

    @FXML
    private TableView<Competicion> tvCompeticiones;

    private CompeticionDAO competicionDAO;

    @FXML
    private void initialize() {
        try {
            var conexion = BaseDatos.getConnection();

            if (conexion != null) {
                competicionDAO = new CompeticionDAO(conexion);

                colCodigo.setCellValueFactory(new PropertyValueFactory<>("codComp"));
                colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
                colPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));

                cargarDatos();
            } else {
                System.err.println("No hay conexión a la base de datos.");
            }

        } catch (Exception e) {
            System.err.println("Error en el inicio de Competiciones: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        if (competicionDAO == null) return;

        try {
            ObservableList<Competicion> lista = FXCollections.observableArrayList(competicionDAO.listarTodos());
            tvCompeticiones.setItems(lista);
        } catch (SQLException e) {
            System.err.println("Error al listar competiciones: " + e.getMessage());
        }
    }


    @FXML
    void onAlta(ActionEvent event) {
        cambiarVentana(event, "/com/example/retoesport33/AltaCompeticion.fxml", "Alta Competición");

    }

@FXML
    void onEliminar(ActionEvent event) {
        cambiarVentana(event , "/com/example/retoesport33/BajaCompeticion.fxml", "Baja Competición");

    }

    @FXML
    void onModificar(ActionEvent event) {
        cambiarVentana(event, "/com/example/retoesport33/ModificarCompeticion.fxml", "Modificar Competición");

    }

    @FXML
    void onVolver(ActionEvent event) {
        cambiarVentana(event, "/com/example/retoesport33/menuAdministrador-view.fxml", "Panel Principal");
    }


private void cambiarVentana(ActionEvent event, String fxml, String titulo) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.show();
    } catch (IOException e) {
        System.err.println("Error al cambiar de ventana: " + e.getMessage());
        e.printStackTrace();
    }
}
}
