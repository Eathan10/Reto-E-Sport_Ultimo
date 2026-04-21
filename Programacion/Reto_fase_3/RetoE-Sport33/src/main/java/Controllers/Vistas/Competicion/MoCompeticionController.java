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
import java.sql.SQLException;

public class MoCompeticionController {

    @FXML
    private Button BtnActualizar;

    @FXML
    private Button BtnVolver;

    @FXML
    private Button BtnBuscar;

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
    }

    @FXML
    void onBuscar(ActionEvent event) {
        String codigoStr = tfCodigo.getText().trim();
        if (codigoStr.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "El campo de código no puede estar vacío.", "Introduzca un código para buscar la competición.");
            return;
        }
        try {
            int codigo = Integer.parseInt(codigoStr);
            Competicion competicion = competicionDAO.buscarPorCodigo(codigo);

            if (competicion != null) {
                tfNombre.setText(competicion.getNombre());
                cbEstado.setValue(competicion.getEstado());
                tfPremio.setText(String.valueOf(competicion.getPremio()));
                tfCodigo.setEditable(false);

                if (competicion.getEstado().equalsIgnoreCase("cerrado")) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Estado: Cerrado", "Esta competición está cerrada. No se pueden modificar sus datos.");
                    BtnActualizar.setDisable(true);
                } else {
                    BtnActualizar.setDisable(false);
                    tfPremio.setEditable(true);
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Competición no encontrada.", "No se encontró ninguna competición con el código proporcionado.");
            }
        }catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Código inválido.", "El código debe ser un número entero.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al buscar la competición.", "Ocurrió un error al buscar la competición: " + e.getMessage());
        }
    }

    @FXML
    void onActualizar(ActionEvent event) {
        try {
            if (tfNombre.getText().isEmpty() || tfPremio.getText().isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Rellena todos los campos.");
                return;
            }

            Competicion compe = new Competicion(
                    Integer.parseInt(tfCodigo.getText()),
                    tfNombre.getText(),
                    cbEstado.getValue(),
                    Double.parseDouble(tfPremio.getText())
            );

            competicionDAO.actualizarCompeticion(compe);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Competición actualizado correctamente.");
            onVolver(event);

        } catch (SQLException e){
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación (Oracle)", e.getMessage());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato inválido.", " El premio debe ser un número decimal.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al actualizar la competición.", "Ocurrió un error al actualizar la competición: " + e.getMessage());
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

