package Controllers.Vistas.VistaAdministradorUsuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class PerfilController {



    @FXML
    private Button btnadministrador;

    @FXML
    private Button btnusuario;

    //click en admin

    @FXML
    void onAdiminstrador(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/administrador.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Panel de Administrador");
        stage.show();
    }




    //click en usuario

    @FXML
    void onUsuario(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/usuario-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
