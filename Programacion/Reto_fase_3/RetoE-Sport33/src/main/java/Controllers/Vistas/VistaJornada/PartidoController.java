package Controllers.Vistas.VistaJornada;

import Controllers.EquipoController;
import DAO.EquipoDAO;
import Modelo.Equipo;
import Modelo.Jornada;
import Modelo.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PartidoController {

    @FXML
    private Label lbfecha;

    @FXML
    private Label lbhora;

    @FXML
    private Label lblocal;

    @FXML
    private Label lbprediccion;

    @FXML
    private Label lbvisitante;

    @FXML
    private Button btnVolver;

    @FXML
    void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/retoesport33/menuAdmistradorView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menu Amdinistrador");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar el menú principal.");
        }

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private EquipoDAO equipoDAO = new EquipoDAO();

    @FXML
    public void initialize() {
        Jornada ultima = Controllers.JornadaController.obtenerUltimaJornada();
        if (ultima != null) {
            lbfecha.setText(ultima.getFecha_inicio().toString());
            lbhora.setText("Jornada " + ultima.getNumJornada());
        }
        generarEnfrentamientosAutomaticos();
    }

    private void generarEnfrentamientosAutomaticos() {


        List<Equipo> todosLosEquipos = EquipoController.listarTodosLosEquipos();

        if (todosLosEquipos.size() >= 2) {
            Collections.shuffle(todosLosEquipos);

            Equipo local = todosLosEquipos.get(0);
            Equipo visitante = todosLosEquipos.get(1);

            lblocal.setText(local.getNombreEquipo());
            lbvisitante.setText(visitante.getNombreEquipo());

            String ganadorIA = predecirGanadorIA(local, visitante);
            lbprediccion.setText(ganadorIA);

        } else {
            mostrarAlerta("Error", "No hay suficientes equipos para generar un partido.");
        }

    }

    // prediccion IA
    private String API_KEY = "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";
    private String API_URL = "https://api.groq.com/openai/v1/chat/completions";

    private String predecirGanadorIA(Equipo local, Equipo visitante) {
        try {
            String textoPrompt = "Dime solo el nombre del ganador de este partido: "
                    + local.getNombreEquipo() + " vs " + visitante.getNombreEquipo();

            String jsonCuerpo = "{"
                    + "\"model\": \"llama-3.3-70b-versatile\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + textoPrompt + "\"}]"
                    + "}";

            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonCuerpo))
                    .build();

            java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String resultado = response.body();
                int inicio = resultado.indexOf("\"content\":\"") + 11;
                int fin = resultado.indexOf("\"", inicio);
                return resultado.substring(inicio, fin);
            }

        } catch (Exception e) {
            System.out.println("Error con la IA: " + e.getMessage());
        }
        return "Empate técnico";
    }


}


