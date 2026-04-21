package Controllers.Vistas.VistaEquipo;

import Controllers.EquipoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ModificarEquipoView {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lb;

    @FXML
    private Label lbNo;

    @FXML
    private TextField tfNombreEquipo;


    @FXML
    void onAceptar(ActionEvent event) throws IOException {
        String NombreEquipo = tfNombreEquipo.getText().trim();

        // comprobamos q la caja de texto no este vacia
        if (!NombreEquipo.isEmpty()) {
            // el equipo existe ya en la base de datos
            if (EquipoController.existeEquipo(NombreEquipo)) {

                // si existe pasaremos a la sigueinte ventana
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/RetoE-Sport33/ModificarEquipo2-view.fxml"));
                Parent root = fxmlLoader.load();

                Scene scene = new Scene(root);
                Stage nextStage = new Stage();
                nextStage.setTitle("Editando Equipo: " + NombreEquipo);
                nextStage.setScene(scene);

                ModificacionEquipo2View controller2 = fxmlLoader.getController();
                controller2.init(NombreEquipo, nextStage, this);// para pasarle el nombre del equipo ala siguiente ventana
                nextStage.show();
                this.stage.close();// para cerrar esta ventana
            }else {
                // menasaje de error de q no existe
                mostrarAlertaError("Equipo no encontrado", "No existe ningún equipo con el nombre: " + NombreEquipo);
            }
        }else{
            // para error si esta vacio el campo de texto
            mostrarAlertaError("Campo obligatorio", "El nombre del equipo no puede estar vacío.");
        }
    }



    private Stage stage;

    public void setStage(Stage stage) {
        // Establece la ventana actual
        this.stage = stage;
    }


    public void show() {
        // Muestra la ventana
        stage.show();
    }





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

    private void mostrarAlertaInfo(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait(); // Se detiene aquí hasta que el usuario pulsa "Aceptar"
    }


    // para q se limpie lo escrito dentro de la caja
    @FXML
    private void limpiarcajas(){
        tfNombreEquipo.clear();
    }


    @FXML
    void onVolver(ActionEvent event) {
        equipoView.show();
        stage.close();
    }

    private EquipoView equipoView;

    public void init(Stage stage, EquipoView equipoView) {
        this.stage = stage;
        this.equipoView = equipoView;
    }
}
