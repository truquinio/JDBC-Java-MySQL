/*
Paquete servicios:

En este paquete se almacenarán aquellas clases que llevarán adelante lógica del negocio.  En 
general se crea un servicio para administrar cada una de las entidades y algunos servicios 
para manejar operaciones muy específicas como las estadísticas.
 */
package servicio;

import entidades.*;
import java.util.ArrayList;
import persistencia.ProductoDAO;


/**
 *
 * @author FT
 */
public class ServicioProducto {
    
//  ATTR:
    private ProductoDAO producto_DAO;                                           //  CREO el OBJETO desde clase PRODUCTO DAO.-

//  CONSTR:
    public ServicioProducto() {
        producto_DAO = new ProductoDAO();                                       //  Instancio el OBJETO PRODUCTO DAO.-
    }

//  CREAR PRODUCTO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void crearProducto(String nombre, Double precio, Fabricante fabricante) throws Exception {

        try {
            
            //  VALIDACIÓN si código viene NULO o VACÍO.-
            /*            if (codigo == null | codigo < 0) {
            throw new Exception("DEBE INGRESAR UN CÓDIGO");
            }*/
            
            //  VALIDACIÓN si nombre viene NULO o VACÍO.-
            if (nombre == null | nombre.trim().isEmpty()) {                     //  .TRIM recorta si hay espacios vacíos.-
                throw new Exception("NOMBRE = NULO / VACÍO");
            }

            Producto objetoProducto = new Producto();

            objetoProducto.setNombre(nombre);                                   //  Seteo el nombre.-
            
            
            //  VALIDACIÓN si precio viene NULO o VACÍO.-
            if (precio == null | precio < 0) {
                    throw new Exception("EL PRECIO ES NULO");
            }
            objetoProducto.setPrecio(precio);                                   //  Seteo el precio.-
            
            
            //  VALIDACIÓN si fabricante viene NULO o VACÍO.-
            if (fabricante == null) {
                    throw new Exception("EL CÓDIGO DEL PRODUCTO ES NULO");
            }
            objetoProducto.setFabricante(fabricante);                           //  Seteo el fabricante.-
            
            producto_DAO.guardarProducto(objetoProducto);                       //  Guardo en el método GUARDAR PRODUCTO de la clase PRODUCTO DAO.-
            
        } catch (Exception excepcion) {

            //  excepcion.printStackTrace();
            throw new Exception("ERROR DE SISTEMA");
        }
    }

//  MODIFICA USUARIO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void modificarProducto(Integer codigo, String nombre) throws Exception {

        try {
            //  VALIDACIÓN si código viene NULO o VACÍO.-
            if (codigo == null | codigo < 0) {
                throw new Exception("DEBE INGRESAR UN CÓDIGO");
            }
            //  VALIDACIÓN si nombre viene NULO o VACÍO.-
            if (nombre == null | nombre.trim().isEmpty()) {                     //  .TRIM recorta si hay espacios vacíos.-
                throw new Exception("DEBE INGRESAR UN NOMBRE");
            }

            Producto objetoProducto = producto_DAO.buscarProductoPorCodigo(codigo);

            if (objetoProducto == null) {
                throw new Exception("EL CÓDIGO NO ESTÁ ASOCIADO A NINGÚN PRODUCTO");
            }

            producto_DAO.modificarProducto(objetoProducto);

        } catch (Exception excepcion) {
            throw new Exception("ERROR AL MODIFICAR PRODUCTO");
        }
    }

//  ELIMINA USUARIO POR CÓDIGO  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void eliminarProducto(Integer codigo) throws Exception {

        try {
            if (codigo == null || codigo < 0) {
                throw new Exception("CÓDIGO = NULO / MENOR A 0");
            }

            //  Busco por CÓDIGO en la BASE de DATOS, si existe un PRODUCTO.-
            Producto objetoProducto = producto_DAO.buscarProductoPorCodigo(codigo);

            if (objetoProducto == null) {
                throw new Exception("EL CÓDIGO NO ESTÁ ASOCIADO A NINGÚN PRODUCTO");
            }

            System.out.println("Se va a eliminar el fabricante: " + objetoProducto.getNombre());
            producto_DAO.eliminarProductoPorCodigo(codigo);

        } catch (Exception excepcion) {
            throw new Exception("ERROR DE SISTEMA");
        }
    }

//  MUESTRA USUARIO ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
    public void muestraProducto() throws Exception {

        try {
            ArrayList<Producto> listaProductos = producto_DAO.obtenerProductos();

            if (listaProductos.isEmpty()) {
                throw new Exception("NO EXISTEN PRODUCTOS");

            } else {
                System.out.println("Lista de Productos:");
                System.out.printf("%-15s15s\n", "CÓDIGO", "NOMBRE");            //  Formato de impresión.-

                for (Producto fabricantesForEach : listaProductos) {
                    System.out.println(fabricantesForEach);
                }
                System.out.println();
            }
        } catch (Exception excepcion) {
            throw new Exception("ERROR DE SISTEMA");
        }
    }


}
