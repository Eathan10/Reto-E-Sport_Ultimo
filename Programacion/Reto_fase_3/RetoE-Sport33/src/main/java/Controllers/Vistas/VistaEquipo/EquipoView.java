package Controllers.Vistas.VistaEquipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EquipoView {

    @FXML
    private Button btnAlta;

    @FXML
    private Button btnBaja;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnVolver;

    @FXML
    void onActualizar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/RetoE-Sport33/ModificarEquipo-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ventana dos");
        stage.setScene(scene);

        ModificarEquipoView controllerModificarEquipo = fxmlLoader.getController();

        controllerModificarEquipo.init(stage,this);

        stage.show();
        this.stage.close();

    }


    @FXML
    void onCrear(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/RetoE-Sport33/AltaEquipo-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ventana dos");
        stage.setScene(scene);

        AltaEquipoView controllerAltaEquipo= fxmlLoader.getController();

        controllerAltaEquipo.init(stage,this);

        stage.show();
        this.stage.close();

    }

    @FXML
    void onEliminar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/RetoE-Sport33/BajaEquipo-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Ventana dos");
        stage.setScene(scene);


        BajaEquipoView controllerBajaEquipo= fxmlLoader.getController();

        controllerBajaEquipo.init(stage,this);

        stage.show();
        this.stage.close();



    }

    private Stage stage;

    public void setStage(Stage stage) {
        // Establece la ventana actual
        this.stage = stage;
    }


    public void show() {
        // Muestra la ventana
        if (stage != null) stage.show();
    }

    @FXML
    void onSalir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/menuAdministrador-view.fxml"));
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
