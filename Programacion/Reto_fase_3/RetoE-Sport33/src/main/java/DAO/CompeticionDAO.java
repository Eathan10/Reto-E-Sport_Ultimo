package DAO;

import Modelo.Competicion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompeticionDAO {
    private Connection conn;

    public CompeticionDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertarCompeticion(Competicion competicion) throws SQLException {
        String sql = "INSERT INTO competiciones (cod_comp, nombre, estado, premio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, competicion.getCodComp());
            ps.setString(2, competicion.getNombre());
            ps.setString(3, competicion.getEstado().toLowerCase());
            ps.setDouble(4, competicion.getPremio());
            ps.executeUpdate();
        }
    }

    public void eliminarCompeticion(int codComp) throws Exception {
        String sql = "DELETE FROM competiciones WHERE cod_comp = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codComp);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new Exception("No se encontró la competición con cod_comp: " + codComp);
            }
        }
    }

    public void actualizarCompeticion(Competicion competicion) throws Exception {
        String sql = "UPDATE competiciones SET nombre = ?, estado = ?, premio = ? WHERE cod_comp = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, competicion.getNombre());
            ps.setString(2, competicion.getEstado().toLowerCase());
            ps.setDouble(3, competicion.getPremio());
            ps.setInt(4, competicion.getCodComp());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new Exception("No se encontró la competición con cod_comp: " + competicion.getCodComp());
            }
        }
    }

    public Competicion buscarPorCodigo(int codComp) throws SQLException {
        String sql = "SELECT * FROM competiciones WHERE cod_comp = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codComp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Competicion(
                            rs.getInt("cod_comp"),
                            rs.getString("nombre"),
                            rs.getString("estado"),
                            rs.getDouble("premio")
                    );
                }
            }
        }
        return null;
    }

    public List<Competicion> listarTodos() throws SQLException {
        List<Competicion> lista = new ArrayList<>();
        String sql = "SELECT * FROM competiciones";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Competicion(
                        rs.getInt("cod_comp"),
                        rs.getString("nombre"),
                        rs.getString("estado"),
                        rs.getDouble("premio")
                ));
            }
        }
        return lista;
    }
}

