package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {

    // Inicializamos a null explícitamente
    private static Connection conn = null;

    public static void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@172.20.225.144:1521:orcl";
            String user = "eqdaw01";
            String password = "eqdaw01";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con éxito.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de Oracle " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al abrir conexión SQL: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}