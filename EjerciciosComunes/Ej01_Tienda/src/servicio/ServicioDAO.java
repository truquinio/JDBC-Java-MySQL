/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import persistencia.DAO;

/**
 *
 * @author FT
 */
public class ServicioDAO extends DAO{
    
    protected void desconectarBase() throws Exception {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected void insertarModificarEliminar(String query) throws Exception {
        try {
            conectarBase();
            sentencia = (Statement) conexion.createStatement();
            sentencia.executeUpdate(query);
        } catch (SQLException ex) {
            
            //  En caso de que el QUERY tenga un error, hacemos un ROLLBACK.-
            conexion.rollback();
            /*  Descomentar la linea anterior si desean tener en cuenta el rollback.
                Correr el siguiente script en Workbench
            
                SET autocommit=1;
                COMMIT;
            
                **Sin rollback igual anda */

            throw ex;
        } finally {
            desconectarBase();
        }
    }

    protected void consultarBase(String sql) throws Exception {
        try {
            conectarBase();
            sentencia = (Statement) conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
