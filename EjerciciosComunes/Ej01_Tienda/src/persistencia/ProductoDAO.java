/*
Paquete persistencia: 

En este paquete estará la clase DAO encarga de conectarse con la base de datos 
y de comunicarse con la base de datos para obtener sus datos. Además, estará las clases de EntidadDaoExt para cada entidad / tabla de nuestro proyecto.

PRODUCTO DAO: Se encarga de gestionar desde JAVA, todas las consultas que haríamos en MySQL resecto al PRODUCTO.-
 */
package persistencia;

import entidades.Fabricante;
import entidades.Producto;
import java.util.ArrayList;

/**
 *
 * @author FT
 */
public class ProductoDAO extends DAO {

//  GUARDAR PRODUCTO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void guardarProducto(Producto prod) throws Exception {

        /*
        SENTENCIA SQLde INSERCIÓN:
        
        INSERT INTO producto (nombre, precio, codigo_fabricante)
        VALUES ('LG', '755', '5');
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            if (prod == null) {
                throw new Exception("Objeto Producto = NULO");
            }

            /*
            INSERT completa con CLAVE PRINCIPAL AUTOINCREMENTAL lo que no sería necesario escribir:
            
            String query = "INSERT INTO producto (codigo, nombre, precio, codigo_fabricante) VALUES ( '"
                    + prod.getCodigo() + "' , '" + prod.getNombre() + "' , '" + prod.getPrecio()
                    + "' , '" + prod.getCodigo_fabricante().getCodigo() + "' );";
            */
            
            //  Crear una QUERY como String, usando la SENTENCIA SQL de INSERSIÓN.-
            String query = "INSERT INTO producto (nombre, precio, codigo_fabricante) VALUES ( '"
                    + prod.getNombre() + "' , '" 
                    + prod.getPrecio() + "' , '" 
                    + prod.getFabricante().getCodigo() + "' );";                //  CÓDIGO_FABRICANTE es un objeto FABRICANTE, por eso debo poner GET.CODIGO al final, para que sea de PRODUCTO.-
                    //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            insertarModificarEliminar(query);                                   //  Llamar al método heredado desde DAO.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL GUARDAR PRODUCTO");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  MODIFICAR PRODUCTO   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void modificarProducto(Producto prod) throws Exception {

        /*
        SENTENCIA SQL de MODIFICACIÓN:
        
        UPDATE producto
        SET nombre = 'Micrófono',
        precio = '2000',
        WHERE (codigo = '51');
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            if (prod == null) {
                throw new Exception("OBJETO PRODUCTO = NULO");
            }

            //  Crear una QUERY como String, usando la SENTENCIA SQL de MODIFICACIÓN.-
            String query = "UPDATE producto SET nombre = '" + prod.getNombre() + "' , '" 
                    + prod.getPrecio() + "' , codigo_fabricante = " 
                    + prod.getFabricante().getCodigo()         //  CÓDIGO_FABRICANTE es un objeto FABRICANTE, por eso debo poner GET.CODIGO al final, para que sea de PRODUCTO.-
                    + "' WHERE (codigo = '" + prod.getCodigo() + "';";
                    //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            insertarModificarEliminar(query);                                   //  Llamar al método heredado desde DAO.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL MODIFICAR PRODUCTO");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  BUSCAR PRODUCTO POR CÓDIGO   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Producto buscarProductoPorCodigo(Integer codigo) throws Exception {

        /*
        SENTENCIA SQL de BÚSQUEDA:
        
        SELECT * FROM producto AS prod
        INNER JOIN fabricante AS fabr
        ON fabr.codigo = prod.codigo_fabricante
        WHERE codigo = '2';
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de BÚSQUEDA.-
            String query = "SELECT * FROM producto AS prod INNER JOIN fabricante AS fabr ON fabr.codigo = prod.codigo_fabricante WHERE codigo = '" + codigo + "';";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            consultarBase(query);                                               //  Llamar al método heredado desde DAO con el String creado como parámetro.-

            Producto objetoProducto = null;                                     //  DECLARO OBJETO como NULO por si está lleno y fuera del WHILE para que no desaparezca al finalizar el bucle.-
            Fabricante objetoFabricante = null;
            
            //  Recorre el OBJETO con un bucle WHILE utilizando el atributo RESULTADO de la calse DAO.-
            while (resultado.next()) {                                          //RESULTADO.NEXT porque SQL siempre empieza de 1.-

                objetoProducto = new Producto();                                //  Instancio el OBJETO PRODUCTO.-
                objetoFabricante = new Fabricante();                            //  Instancio el OBJETO FABRICANTE.-
                
                //  Armo la tabla con el órden que tendrán las columnas.-
                objetoProducto.setCodigo(resultado.getInt(1));                  //  SETEO código desde RESULTADO en la COLUMNA 1.-
                objetoProducto.setNombre(resultado.getString(2));               //  SETEO nombre desde RESULTADO en la COLUMNA 2.-
                objetoProducto.setPrecio(resultado.getDouble(3));               //  SETEO precio desde RESULTADO en la COLUMNA 3.-
                
                //  Lleno OBJETO FABRICANTE con los datos del RESULTSET.-
                objetoFabricante.setCodigo(resultado.getInt(5));                //  SETEO código de fabricante desde RESULTADO en la COLUMNA 5.-
                objetoFabricante.setNombre(resultado.getString(6));             //  SETEO nombre de fabricante desde RESULTADO en la COLUMNA 6.-
                
                //  Asigno el FABRICANTE al PRODUCTO.-
                objetoProducto.setFabricante(objetoFabricante);
                
            }
            return objetoProducto;                                              //  RETORNO el OBJETO creado.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL OBTENER PRODUCTO");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  BUSCAR PRODUCTO POR NOMBRE   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    public Producto buscarProductoPorNombre(String nombre) throws Exception {
        /*
        SENTENCIA SQL de BÚSQUEDA:
        
        SELECT * FROM producto WHERE nombre = '%Asus%';
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de BÚSQUEDA.-
            String query = "SELECT * FROM Producto WHERE nombre = '%" + nombre + "%';";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            consultarBase(query);                                               //  Llamar al método heredado desde DAO con el String creado como parámetro.-

            Producto objetoprodicante = null;                                   //  DECLARA un OBJETO como NULO por si está lleno y fuera del WHILE para que no desaparezca al finalizar el bucle.-

            //  Recorre el OBJETO con un bucle WHILE utilizando el atributo RESULTADO de la calse DAO.-
            while (resultado.next()) {                                          //  RESULTADO.NEXT porque SQL siempre empieza de 1.-

                objetoprodicante = new Producto();                              //  Instancio el OBJETO.-

                objetoprodicante.setCodigo(resultado.getInt(1));                //  SETEO código desde RESULTADO y le digo que sea la COLUMNA 1 porque SQL empieza desde el 1 y no desde 0.-
                objetoprodicante.setNombre(resultado.getString(2));             //  SETEO nombre desde RESULTADO y le digo que sea la COLUMNA 2 porque SQL empieza desde el 1 y no desde 0.-
            }

            return objetoprodicante;                                            //  RETORNO el OBJETO creado.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL OBTENER PRODUCTO");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  ELIMINAR PRODUCTO    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void eliminarProductoPorCodigo(Integer codigo) throws Exception {
        /*
        SENTENCIA SQL de ELIMINAACIÓN:
        
        DELETE FROM producto WHERE codigo = '12';
         */
        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de ELIMINACIÓN.-
            String query = "DELETE FROM Producto WHERE codigo = '" + codigo + "';";
            //  Un ; es para cerrar la consulta SQL y el otro ; es para cerrar la línea de Java.-

            insertarModificarEliminar(query);                                   //  Llamar al método heredado desde DAO.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL ELIMINAR PRODUCTO");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }

//  OBTENER LISTA DE PRODUCTOS   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Producto> obtenerProductos() throws Exception {
    //  BÚSQUEDA POR NOMBRE: public ArrayList<Producto> obtenerProductos(String nombreProd) throws Exception {
        
        /*
         /*
        SENTENCIA SQL de BÚSQUEDA:
        
        SELECT * FROM producto AS prod
        INNER JOIN fabricante AS fabr
        ON fabr.codigo = prod.codigo_fabricante;
         */

        try {
            conectarBase();                                                     //  Debe CONECTARSE a la BASE de DATOS.-

            //  Crear una QUERY como String, usando la SENTENCIA SQL de BÚSQUEDA.-
            String query = "SELECT * FROM producto AS prod INNER JOIN fabricante AS fabr ON fabr.codigo = prod.codigo_fabricante;";

            consultarBase(query);                                               //  Llamar al método heredado desde DAO.-

            ArrayList<Producto> ListaProductos = new ArrayList();               //  Crear un ARRAYLIST para contener el RESULTADO del RESULTSET.-

            Producto objetoProducto = null;                                     // DECLARA un OBJETO como NULO por si está lleno y fuera del WHILE para que no desaparezca al finalizar el bucle.-
            Fabricante objetoFabricante = null;
            
            //  Recorre el OBJETO con un bucle WHILE utilizando el atributo RESULTADO de la calse DAO.-
            while (resultado.next()) {                                          //RESULTADO.NEXT porque SQL siempre empieza de 1.-

                objetoProducto = new Producto();                                //  Instancio el OBJETO PRODUCTO.-
                objetoFabricante = new Fabricante();                            //  Instancio el OBJETO FABRICANTE.-
                
                //  Armo la tabla con el órden que tendrán las columnas.-
                objetoProducto.setCodigo(resultado.getInt(1));                  //  SETEO código desde RESULTADO en la COLUMNA 1.-
                objetoProducto.setNombre(resultado.getString(2));               //  SETEO nombre desde RESULTADO en la COLUMNA 2.-
                objetoProducto.setPrecio(resultado.getDouble(3));               //  SETEO precio desde RESULTADO en la COLUMNA 3.-
                
                //  Lleno OBJETO FABRICANTE con los datos del RESULTSET.-
                objetoFabricante.setCodigo(resultado.getInt(5));                //  SETEO código de fabricante desde RESULTADO en la COLUMNA 5.-
                objetoFabricante.setNombre(resultado.getString(6));             //  SETEO nombre de fabricante desde RESULTADO en la COLUMNA 6.-
                
                //  Asigno el FABRICANTE al PRODUCTO.-
                objetoProducto.setFabricante(objetoFabricante);
            }

            return ListaProductos;                                              //  RETORNO el ARRAYLIST creado.-

        } catch (Exception excepcion) {

            System.out.println(excepcion.getMessage());                         //  Muestra cual es el error.-

            throw new Exception("ERROR AL OBTENER TODOS LOS PRODUCTOS");

        } finally {                                                             //  Finalmente se DESCONECTA de la BASE de DATOS.-
            desconectarBase();
        }
    }
}