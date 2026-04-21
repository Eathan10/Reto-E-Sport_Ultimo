package Controllers.Vistas.VistaAdministradorUsuario;

import Controllers.AministradorController;
import Utilidades.Valid;
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


public class AdministradorView {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPassword;

    private AministradorController logicController = new AministradorController();

    @FXML
    void onClickEntrar(ActionEvent event) throws IOException {

        String nombre = tfNombre.getText();
        String password = tfPassword.getText();

        if (Valid.validarDatos(nombre, "^[a-zA-Z]+$") && Valid.validarDatos(password, "^[a-zA-Z0-9]+$")) {

            boolean esValido = logicController.procesarLogin(nombre, password);

            if (esValido) {
                try {
                    navegar("/com/example/retoesport33/menuAdministrador-view.fxml", "Panel de Administrador", event);
                } catch (IOException e) {
                    mostrarAlerta("Error", "No se pudo cargar el menú.");
                }
            } else {
                mostrarAlerta("Acceso denegado", "El usuario o la contraseña no son correctos.");
            }

        } else {
            mostrarAlerta("Datos incorrectos", "El nombre solo debe contener letras y la contraseña debe ser alfanumérica.");
        }

    }



    @FXML
    void onClickVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/perfil-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Selección de Perfil");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo volver a la pantalla de selección de perfil.");
        }
    }

    private void navegar(String ruta, String titulo, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.show();
    }



    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
