package Controllers.Vistas.Competicion;

import DAO.CompeticionDAO;
import Modelo.Competicion;
import Utilidades.BaseDatos;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AlCompeticionVistaController {

    @FXML
    private Button BtnCrear;

    @FXML
    private Button BtnVolver;

    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPremio;

    private CompeticionDAO competicionDAO;

    @FXML
    private void initialize() {
        competicionDAO = new CompeticionDAO(BaseDatos.getConnection());

        cbEstado.setItems(FXCollections.observableArrayList("abierto", "cerrado"));
        cbEstado.getSelectionModel().selectFirst();
    }

    @FXML
    void onCrear(ActionEvent event) {
        try {
            if (tfCodigo.getText().isEmpty() || tfNombre.getText().isEmpty() || tfPremio.getText().isEmpty()) {
                mostrarAlerta("Error", "Todos los campos deben ser completados.");
                return;
            }

            int codigo = Integer.parseInt(tfCodigo.getText());
            String nombre = tfNombre.getText();
            String estado = cbEstado.getValue();
            double premio = Double.parseDouble(tfPremio.getText());

            Competicion nuevaCompeticion = new Competicion(codigo, nombre, estado, premio);

            competicionDAO.insertarCompeticion(nuevaCompeticion);

            mostrarAlerta("Éxito", "Competición creada correctamente.");
            onVolver(event);
        }catch (NumberFormatException e ) {
            mostrarAlerta("Error", "Código y premio deben ser números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Ocurrió un error al crear la competición: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void onVolver(ActionEvent event) {
        try {
            String fxml = "/com/example/retoesport33/CompeticionGestion.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Competiciones");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
