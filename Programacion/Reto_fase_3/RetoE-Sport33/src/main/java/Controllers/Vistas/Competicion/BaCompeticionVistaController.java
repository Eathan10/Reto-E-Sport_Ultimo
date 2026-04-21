package Controllers.Vistas.Competicion;

import DAO.CompeticionDAO;
import Utilidades.BaseDatos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BaCompeticionVistaController {

    @FXML
    private Button BtnEliminar;

    @FXML
    private Button BtnVolver;

    @FXML
    private TextField tfCodigo;

    private CompeticionDAO competicionDAO;

    @FXML
    private void initialize() {
        competicionDAO = new CompeticionDAO(BaseDatos.getConnection());
    }

    @FXML
    void onEliminar(ActionEvent event) {
        String codigoStr = tfCodigo.getText().trim();
        if (codigoStr.isEmpty()) {
            mostrarAlerta("Error", "El campo de código no puede estar vacío.");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr);
            competicionDAO.eliminarCompeticion(codigo);
            mostrarAlerta("Éxito", "Competición con código " + codigo + " eliminada correctamente.");
            tfCodigo.clear();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El código debe ser un número entero.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo eliminar la competición: " + e.getMessage());
        }

    }

    @FXML
    void onVolver(ActionEvent event) {
        try {
            String fxml = "/com/example/retoesport33/CompeticionGestion.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
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
