/*
Paquete persistencia: 

En este paquete estará la clase DAO encarga de conectarse con la base de datos 
y de comunicarse con la base de datos para obtener sus datos. Además, estará las clases de EntidadDaoExt para cada entidad / tabla de nuestro proyecto.

PRODUCTO DAO: Se encarga de gestionar desde JAVA, todas las consultas que haríamos en MySQL resecto al PRODUCTO.-
 */
package persistencia;

import entidades.Producto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


/**
 *
 * @author FT
 */
public class ProductoDAO extends DAO {
    /*
      public ProductoDAO() {
        this.productoServicio = new ServicioProducto(); // raro
    }
    */
    public ProductoDAO(){
    }
    
    //CRUD(crear, leer, actualizar, borrar)
    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto");
            }
            String sql = "INSERT INTO Producto (codigo, nombre, precio, codigoFabricante) "
                    + "VALUES ( '" + producto.getCodigo() + "' , '" + producto.getNombre() + "' ," + 
                    producto.getPrecio() + "'," + producto.getCodigoFabricante() + " );";

            System.out.println(sql);
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
      public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo = " + codigo + "";
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                
                /*mascota.setId(resultado.getInt(1));
                mascota.setApodo(resultado.getString(2));
                mascota.setRaza(resultado.getString(3));
                Integer idUsuario = resultado.getInt(4);
                Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
                mascota.setUsuario(usuario); */
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
     public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el mascota que desea modificar");
            }
            String sql = "UPDATE Producto SET "
                    + " codigo = '" + producto.getCodigo() + "' , nombre = '" + producto.getNombre() + "' , precio = " + producto.getPrecio()
                    + " codigo_fabricante = '" + producto.getCodigoFabricante() + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
     
      public void eliminarProducto(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM Mascota WHERE codigo = " + codigo + "";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
      
    //-------------------------------------------------------//
       public Collection<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT * FROM Producto ";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
       
       public HashMap<String,Double> listarProductosMasPrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM Producto ";
            consultarBase(sql);
            HashMap<String, Double> productos = new HashMap();
            while (resultado.next()) {
                productos.put(resultado.getString(1),resultado.getDouble(2));
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
       //Listar aquellos productos que su precio esté entre 120 y 202"
        public Collection<Producto> listarProductos120y202(int min, int max) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE precio BETWEEN " + min +"  AND " + max;
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
    
     //4. Buscar y listar todos los Portátiles de la tabla producto"
         public Collection<Producto> listarProductosPortatiles() throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE nombre LIKE '%portatil%';";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }
    
     //Listar el nombre y el precio del producto más barato"
     
    public HashMap<String,Double> buscarProductoMasBarato() throws Exception {
        try {
            String sql = "SELECT nombre,precio FROM producto WHERE precio=(SELECT MIN(precio) FROM producto) ";
            consultarBase(sql);
            HashMap<String, Double> productos = new HashMap();
            while (resultado.next()) {
                productos.put(resultado.getString(1),resultado.getDouble(2));
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw e;
        }
    }        
}