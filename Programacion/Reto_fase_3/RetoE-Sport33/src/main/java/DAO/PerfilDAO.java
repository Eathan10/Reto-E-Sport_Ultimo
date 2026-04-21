package DAO;



import Modelo.Perfil;

import Utilidades.*;



import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;




/**
 * Clase de Acceso a Datos para la entidad perfil, que permite gestionar las cuentas de usuario tanto de administradores como usuarios) 
 * realizando operaciones de alta, baja y modificación en la base de datos.
 * @author Unax
 * @version 1.0
 * @since 2026-04-16
 */
public class PerfilDAO{


    /**
     * Registra un nuevo perfil en el sistema
     * @param perfil Objeto  con los datos de la cuenta a crear
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    public void altaPerfil (Perfil perfil) throws SQLException {



        String sql = "INSERT INTO perfiles(cod_perfil,nombre, password, tipo) " +

                "VALUES (?,?,?,?)";



        try{
            Connection conexion = BaseDatos.getConnection();

            PreparedStatement ps = conexion.prepareStatement(sql);


            ps.setInt(1, perfil.getCodPerfil() );
            ps.setString(2, perfil.getNombre());
            ps.setString(3, perfil.getPassword());
            ps.setString(4, perfil.getTipo());

            ps.executeUpdate();

        }catch (Exception e){

            e.getMessage();

        }finally {
            BaseDatos.closeConnection();
        }

    }

    /**
     * Elimina un perfil de la base de datos teniendo en cuenta su código 
     * @param codPerfil Código único del perfil que quieres eliminar
     * @return el número de filas afectadas 
     * @throws Exception si ocurre un error durante la operación
     */
    public int bajaPerfil(int codPerfil) throws Exception{

        String sql = "DELETE FROM perfiles WHERE cod_perfil = ?";

        int numBorrados = 0;

        try{
            Connection conexion = BaseDatos.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, codPerfil);

            numBorrados = ps.executeUpdate();

            if (numBorrados != 1){

                System.out.println("No se puede borrar el perfil");

            }else{

                System.out.println("El perfil ha sido eliminado");
            }



        }catch(Exception e){
            e.getMessage();

        }finally {
            BaseDatos.closeConnection();

            return numBorrados;

        }

    }


    /**
     * Actualiza la información de un perfil que ya existe tanto el nombre como la contraseña y tipo. El registro se identifica mediante el código de perfil 
     * @param perfil Objeto con los nuevos datos.
     * @return el numero de filas que se actualizaran
     * @throws Exception si ocurre un error en la actualización
     */
    public int modificarPerfil(Perfil perfil) throws Exception {

        String sql = "UPDATE perfiles" +
                " SET nombre = ? , password = ?, tipo = ?" +
                " WHERE cod_perfil = ?";

        PreparedStatement ps = null;
        try {

            Connection conexion = BaseDatos.getConnection();
            ps = conexion.prepareStatement(sql);

            ps.setString(1, perfil.getNombre());
            ps.setString(2, perfil.getPassword());
            ps.setString(3, perfil.getTipo());
            ps.setInt(4, perfil.getCodPerfil());


        } catch (Exception e) {
            e.getMessage();

        } finally {
            BaseDatos.closeConnection();
        }

        return ps.executeUpdate();
    }

}

