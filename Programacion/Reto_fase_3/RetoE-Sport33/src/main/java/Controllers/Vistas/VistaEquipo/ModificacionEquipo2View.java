package Controllers.Vistas.VistaEquipo;

import Controllers.EquipoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ModificacionEquipo2View {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnContinuar;

    @FXML
    private DatePicker dpFechaFundacion;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbFecha;

    @FXML
    private Label lbNombre;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombreEquipo;


    @FXML
    void onContinuar(ActionEvent event) {
        String nuevoNombre = tfNombreEquipo.getText().trim();
        String nuevoCodigo = tfCodigo.getText().trim();
        LocalDate nuevaFecha = dpFechaFundacion.getValue();

        // 1. Validamos que al menos haya escrito algo o que la fecha no sea nula
        if (!nuevoNombre.isEmpty() && !nuevoCodigo.isEmpty() && nuevaFecha != null) {

            try {
                // llamamos al controllador con los datos nuevos metidos
                EquipoController.actualizarEquipo(nombreOriginal, nuevoNombre, nuevoCodigo, nuevaFecha);

                // mostrar mensaje de que se ha actualizado correctamente el equipo
                mostrarAlertaInfo("Actualizado", "El equipo se ha modificado correctamente.");

                // para volver a la ventana anterior, llamamos al onVolver
                onVolver(event);

            } catch (Exception e) {
                mostrarAlertaError("Error", "No se pudo actualizar en la base de datos.");
            }

        } else {
            // Error si deja campos vacíos
            mostrarAlertaError("Campos incompletos", "Por favor, rellena todos los campos para continuar.");
        }

    }


    @FXML
    void onVolver(ActionEvent event) {
        controller.show();
        stage.close();

    }


    private String nombreOriginal; // Variable para saber qué equipo estamos editando
    private ModificarEquipoView controller;
    private Stage stage;

    public void init(String nombreOriginal, Stage stage, ModificarEquipoView modificarEquipoView) {
        this.nombreOriginal = nombreOriginal; // Guardamos el nombre que vino de la ventana 1
        lbNombre.setText(nombreOriginal);
        this.controller = modificarEquipoView;
        this.stage = stage;
    }

    private void mostrarAlertaInfo(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void mostrarAlertaError(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


}
