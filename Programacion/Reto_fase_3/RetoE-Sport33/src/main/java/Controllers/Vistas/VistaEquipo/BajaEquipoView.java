package Controllers.Vistas.VistaEquipo;

import Controllers.EquipoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BajaEquipoView {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField tfNombreEquipo;

    @FXML
    void onAceptar(ActionEvent event) {
        String NombreEquipo = tfNombreEquipo.getText().trim();

        // no has puesto nada
        if (!NombreEquipo.isEmpty()) {
            // el equipo existe ya en la base de datos
            if (EquipoController.existeEquipo(NombreEquipo)) {
                // confirmacio para borrar el equipo
                if (confirmarAccion("¿Estás seguro de que deseas borrar el equipo: " + NombreEquipo + "?")) {
                    EquipoController.borrarEquipo(NombreEquipo);
                    mostrarAlertaInfo("Equipo borrado", "El equipo '" + NombreEquipo + "' ha sido eliminado correctamente.");
                    limpiarcajas();

                }

            }else {
                // menasaje de error de q no existe
                mostrarAlertaError("Equipo no encontrado", "No existe ningún equipo con el nombre: " + NombreEquipo);
            }
        }else{
            // para error si esta vacio el campo de texto
            mostrarAlertaError("Campo obligatorio", "El nombre del equipo no puede estar vacío.");
        }

    }

    @FXML
    void onVolver(ActionEvent event) {
        equipoView.show();
        stage.close();
    }


    // para mensaje de cuadro de confirmacion de equipo borrado
    private boolean confirmarAccion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar acción");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        return alert.showAndWait().filter(r -> r == ButtonType.OK).isPresent();
    }

    // para mensaje de cuadro de error
    private void mostrarAlertaError(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    //para mostrar mesaje de cuadro de informacion de equipo borrado
    private void mostrarAlertaInfo(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


    // para q se limpie lo escrito dentro de la caja
    @FXML
    private void limpiarcajas(){
        tfNombreEquipo.clear();
    }

    private Stage stage;
    private EquipoView equipoView;
    public void init(Stage stage, EquipoView equipoView) {
        this.stage = stage;
        this.equipoView = equipoView;
    }
}
