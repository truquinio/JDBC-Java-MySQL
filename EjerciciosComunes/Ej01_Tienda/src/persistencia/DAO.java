/*
Paquete persistencia: 

En este paquete estará la clase DAO encarga de conectarse con la base de datos 
y de comunicarse con la base de datos para obtener sus datos. Además, estará las clases de EntidadDaoExt
para cada entidad / tabla de nuestro proyecto.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author FT
 */
public abstract class DAO {

    
    protected Connection conexion;                                              //  Administra nuestra conexión.-
    protected Statement sentencia;                                              //  Introducción de consulta.-
    protected ResultSet resultado;                                              //  Manipula los resultados (ResulSet funciona como una lista, pero inicia desde 1).-

    private final String DRIVER = "com.mysql.jdbc.Driver";                      //  Colocar esta cadena en el DRIVER/CONTROLADOR: "com.mysql.jdbc.Driver".-
    private final String NOMBRE_DATABASE = "tienda";                            //  Nombre de la BASE de DATOS.-

    //  Intenta establecer una conexión con la base de datos database-name utilizando el driver JDBC que proporciona MySQL.-
    private final String USER = "root";                                         //  Para poder acceder al RDBMS MySQL es necesario introducir un username y un password válidos.-
    private final String PASSWORD = "root";

    private final String URL = "jdbc:mysql://localhost:3306/" + NOMBRE_DATABASE;//  Ubicación de la BASE de DATOS.-

    
//  MÉTODOS: ///////////////////////////////////////////////////////////////////////////////////////////////
    
//  CONECTARSE A BBDD +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    protected void conectarBase() throws Exception {
        try {
            
            Class.forName(DRIVER);                                              //  Carga el DRIVER/CONTROLADOR.-
            //  OTRA FORMA: Class.forName("com.mysql.jdbc.Driver");

            String urlBaseDeDatos = URL + NOMBRE_DATABASE;                      //  Crea la URL de la BASE de DATOS.-
            //  OTRA FORMA: String urlBaseDeDatos = "jdbc:mysql://localhost:3306/" + NOMBRE_DATABASE;

            conexion = DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD); //  Establece la CONEXIÓN.-
            //  OTRA FORMA: conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda?useSSL=false", "root", "root");

        } catch (SQLException excepcion) {
            throw excepcion;
        }
    }

//  CONSULTAR BBDD ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    protected void consultarBase(String query) throws Exception {
        try {
            
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            sentencia = conexion.createStatement();                             //  Crea la SENTENCIA.-

            resultado = sentencia.executeQuery(query);                          //  Dicha SENTENCA nos dará como RESULTADO la EJECUCIÓN del QUERY.-

        } catch (Exception excepcion) {
            throw excepcion;
        }
    }

//  DESCONECTARSE A BBDD  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    protected void desconectarBase() throws Exception {
        
        try {                                                                   //  Cómo va en cascada, debe DESCONECTAR uno x uno.-
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException excepcion) {
            throw excepcion;
        }
    }

//  EDITAR/ MODIFICAR / ELIMINAR BBDD   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    protected void insertarModificarEliminar(String query) throws Exception {
        try {
            
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            sentencia = conexion.createStatement();                             //  Crea la SENTENCIA.-

            sentencia.executeUpdate(query);                                     //  Dicha SENTENCA nos dará como RESULTADO la ACTUALIZACIÓN del QUERY.-

        } catch (SQLException excepcion) {

            conexion.rollback();                                                //  En caso de que el QUERY tenga un error, debe hacerse un ROLLBACK.-
            /*  Descomentar la linea anterior si desean tener en cuenta el rollback.
                Correr el siguiente script en Workbench
            
                SET autocommit=1;
                COMMIT;
            
                **Sin rollback igual anda */

            throw excepcion;

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }
}