/*
Paquete persistencia: 

En este paquete estará la clase DAO encarga de conectarse con la base de datos 
y de comunicarse con la base de datos para obtener sus datos. Además, estará las clases de EntidadDaoExt para cada entidad / tabla de nuestro proyecto.

USUARIO DAO: Se encarga de gestionar desde JAVA, todas las consultas que haríamos en MySQL resecto al USUARIO.-
 */
package persistencia;

import entidades.Fabricante;
import java.util.ArrayList;

/**
 *
 * @author FT
 */
public class FabricanteDAO extends DAO {

//  GUARDAR USUARIO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void guardarFabricante(Fabricante fabr) throws Exception {

        /*
        SENTENCIA SQLde INSERCIÓN:
        
        INSERT INTO fabricante (codigo, nombre)
        VALUES ('12','LG');
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            if (fabr == null) {
                throw new Exception("OBJETO FABRICANTE = NULO");
            }

            //  Crear una QUERY como String, usando la SENTENCIA SQL de INSERSIÓN.-
            String query = "INSERT INTO fabricante (codigo, nombre) VALUES ( '" + fabr.getCodigo() + "' , '" + fabr.getNombre() + "' );";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            insertarModificarEliminar(query);                                   //  Llamar al método heredado desde DAO.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL GUARDAR FABRICANTE");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  MODIFICAR USUARIO   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void modificarFabricante(Fabricante fabr) throws Exception {

        /*
        SENTENCIA SQL de MODIFICACIÓN:
        
        UPDATE fabricante 
        SET nombre 'Samsung'
        WHERE (codigo = '12');
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            if (fabr == null) {
                throw new Exception("OBJETO FABRICANTE = NULO");
            }

            //  Crear una QUERY como String, usando la SENTENCIA SQL de MODIFICACIÓN.-
            String query = "UPDATE Fabricante SET nombre = '" + fabr.getNombre() + "' WHERE (codigo = '" + fabr.getCodigo() + "';";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            insertarModificarEliminar(query);                                   //  Llamar al método heredado desde DAO.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL MODIFICAR FABRICANTE");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  BUSCAR USUARIO POR CÓDIGO   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Fabricante buscarFabricantePorCodigo(Integer codigo) throws Exception {

        /*
        SENTENCIA SQL de BÚSQUEDA:
        
        SELECT * FROM Fabricante WHERE codigo = '12';
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de BÚSQUEDA.-
            String query = "SELECT * FROM Fabricante WHERE codigo = '" + codigo + "';";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            consultarBase(query);                                               //  Llamar al método heredado desde DAO con el String creado como parámetro.-

            Fabricante objetofabricante = null;                                 //  DECLARO un OBJETO como NULO por si está lleno y fuera del WHILE para que no desaparezca al finalizar el bucle.-

            //  Recorre el OBJETO con un bucle WHILE utilizando el atributo RESULTADO de la calse DAO.-
            while (resultado.next()) {                                          //  RESULTADO.NEXT porque SQL siempre empieza de 1.-

                objetofabricante = new Fabricante();                            //  Instancio el OBJETO.-

                //  Armo la tabla con el órden que tendrán las columnas.-
                objetofabricante.setCodigo(resultado.getInt(1));                //  SETEO código desde RESULTADO en la COLUMNA 1.-
                objetofabricante.setNombre(resultado.getString(2));             //  SETEO nombre desde RESULTADO en la COLUMNA 2.-
            }
            return objetofabricante;                                            //  RETORNO el OBJETO creado.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL OBTENER FABRICANTE POR CODIGO");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  BUSCAR USUARIO POR NOMBRE   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        /*
        SENTENCIA SQL de BÚSQUEDA:
        
        SELECT * FROM Fabricante WHERE nombre = '%Asus%';
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de BÚSQUEDA.-
            String query = "SELECT * FROM Fabricante WHERE nombre = '%" + nombre + "%';";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            consultarBase(query);                                               //  Llamar al método heredado desde DAO con el String creado como parámetro.-

            Fabricante objetofabricante = null;                                 //  DECLARO un OBJETO como NULO por si está lleno y fuera del WHILE para que no desaparezca al finalizar el bucle.-

            //  Recorre el OBJETO con un bucle WHILE utilizando el atributo RESULTADO de la calse DAO.-
            while (resultado.next()) {                                          //  RESULTADO.NEXT porque SQL siempre empieza de 1.-

                objetofabricante = new Fabricante();                            //  Instancio el OBJETO.-

                objetofabricante.setCodigo(resultado.getInt(1));                //  SETEO código desde RESULTADO y le digo que sea la COLUMNA 1 porque SQL empieza desde el 1 y no desde 0.-
                objetofabricante.setNombre(resultado.getString(2));             //  SETEO nombre desde RESULTADO y le digo que sea la COLUMNA 2 porque SQL empieza desde el 1 y no desde 0.-
            }
            return objetofabricante;                                            //  RETORNO el OBJETO creado.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL OBTENER FABRICANTE POR NOMBRE");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  ELIMINAR USUARIO    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void eliminarFabricantePorCodigo(Integer codigo) throws Exception {
        /*
        SENTENCIA SQL de ELIMINAACIÓN:
        
        DELETE FROM Fabricante WHERE codigo = '12';
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de ELIMINACIÓN.-
            String query = "DELETE FROM Fabricante WHERE codigo = '" + codigo + "';";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            insertarModificarEliminar(query);                                   //  Llamar al método heredado desde DAO.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL ELIMINAR FABRICANTE");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  OBTENER LISTA DE USUARIOS   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Fabricante> obtenerFabricantes() throws Exception {
   //  BÚSQUEDA POR NOMBRE: public ArrayList<Fabricante> obtenerFabricantes(String nombreFabr) throws Exception {
   
        /*
        SENTENCIA SQL de BÚSQUEDA:
        
        SELECT * FROM fabricante;
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de BÚSQUEDA.-
            String query = "SELECT * FROM fabricante";
            //  BÚSQUEDA POR NOMBRE: String query = "SELECT * FROM fabricante WHERE nombre = '%" + nombreFabr + "';";

            consultarBase(query);                                               //  Llamar al método heredado desde DAO.-

            ArrayList<Fabricante> ListaFabricantes = new ArrayList();           //  Crear un ARRAYLIST para contener el RESULTADO del RESULTSET.-

            Fabricante objetofabricante = null;                                 //  DECLARO un OBJETO como NULO por si está lleno y fuera del WHILE para que no desaparezca al finalizar el bucle.-

            //  Recorre el OBJETO con un bucle WHILE utilizando el atributo RESULTADO de la calse DAO.-
            while (resultado.next()) {                                          //  RESULTADO.NEXT porque SQL siempre empieza de 1.-

                objetofabricante = new Fabricante();                            //  Instancio el OBJETO.-

                objetofabricante.setCodigo(resultado.getInt(1));                //  SETEO código desde RESULTADO y le digo que sea la COLUMNA 1 porque SQL empieza desde el 1 y no desde 0.-
                objetofabricante.setNombre(resultado.getString(2));             //  SETEO nombre desde RESULTADO y le digo que sea la COLUMNA 2 porque SQL empieza desde el 1 y no desde 0.-

                ListaFabricantes.add(objetofabricante);                         //  AGREGO cada OBJETO al ARRAYLIST.-
            }

            return ListaFabricantes;                                            //  RETORNO el ARRAYLIST creado.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL OBTENER LISTA DE TODOS LOS FABRICANTES");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }
}