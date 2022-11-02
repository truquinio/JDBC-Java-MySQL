/*
Paquete servicios:

En este paquete se almacenarán aquellas clases que llevarán adelante lógica del negocio.  En 
general se crea un servicio para administrar cada una de las entidades y algunos servicios 
para manejar operaciones muy específicas como las estadísticas.

Realizar un menú en Java a través del cual se permita elegir qué consulta se desea realizar.

Las consultas a realizar sobre la BD son las siguientes:

a)  Lista el nombre de todos los productos que hay en la tabla producto. 
b)  Lista los nombres y los precios de todos los productos de la tabla producto. 
c)  Listar aquellos productos que su precio esté entre 120 y 202. 
d)  Buscar y listar todos los Portátiles de la tabla producto. 
e)  Listar el nombre y el precio del producto más barato. 
f)  Ingresar un producto a la base de datos.
g)  Ingresar un fabricante a la base de datos
h)  Editar un producto con datos a elección.
 */
package servicio;

import entidades.Fabricante;
import java.util.ArrayList;
import persistencia.FabricanteDAO;

/**
 *
 * @author FT
 */
public class ServicioFabricante {

//  ATTR:
    private FabricanteDAO fabricante_DAO;                                        //  CREO el OBJETO desde clase FABRICANTE DAO.-

//  CONSTR:
    public ServicioFabricante() {
        fabricante_DAO = new FabricanteDAO();                                    //  Instancio el OBJETO FABRICANTE DAO.-
    }

//  CREAR USUARIO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void crearFabricante(Integer codigo, String nombre) throws Exception {

        try {
            //  VALIDACIÓN si código viene NULO o VACÍO.-
            if (codigo == null | codigo < 0) {
                throw new Exception("DEBE INGRESAR UN CÓDIGO");
            }
            //  VALIDACIÓN si nombre viene NULO o VACÍO.-
            if (nombre == null | nombre.trim().isEmpty()) {                     //.TRIM recorta si hay espacios vacíos.-
                throw new Exception("DEBE INGRESAR UN NOMBRE");
            }

            Fabricante Objetofabricante = new Fabricante();

            //Objetofabricante.setCodigo(codigo);                               //  Seteo el código.-
            Objetofabricante.setNombre(nombre);                                 //  Seteo el nombre.-

            fabricante_DAO.guardarFabricante(Objetofabricante);                  //  Guardo en el método GUARDAR FABRICANTE de la clase FABRICANTE DAO.-

        } catch (Exception excepcion) {

            //  excepcion.printStackTrace();
            throw new Exception("ERROR DE SISTEMA");
        }
    }

//  MODIFICA USUARIO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void modificarFabricante(Integer codigo, String nombre) throws Exception {

        try {
            //  VALIDACIÓN si código viene NULO o VACÍO.-
            if (codigo == null | codigo < 0) {
                throw new Exception("DEBE INGRESAR UN CÓDIGO");
            }
            //  VALIDACIÓN si nombre viene NULO o VACÍO.-
            if (nombre == null | nombre.trim().isEmpty()) {                     //.TRIM recorta si hay espacios vacíos.-
                throw new Exception("DEBE INGRESAR UN NOMBRE");
            }
            
            Fabricante objetoFabricante = fabricante_DAO.buscarFabricantePorCodigo(codigo);
            
            if (objetoFabricante == null) {
                throw new Exception("EL CÓDIGO NO ESTÁ ASOCIADO A NINGÚN FABRICANTE");
            }
            
            fabricante_DAO.modificarFabricante(objetoFabricante);                

        } catch (Exception excepcion) {
            throw new Exception("ERROR AL MODIFICAR FABRICANTE");
        }
    }
    
//  ELIMINA USUARIO POR CÓDIGO  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void eliminarFabricante(Integer codigo) throws Exception {
        
        try {
            if (codigo == null || codigo < 0) {
                throw new Exception("DEBE INGRESAR UN CÓDIGO");
            }
            
            Fabricante objetoFabricante = fabricante_DAO.buscarFabricantePorCodigo(codigo);
            
            if (objetoFabricante == null) {
                throw new Exception("EL CÓDIGO NO ESTÁ ASOCIADO A NINGÚN FABRICANTE");
            }
            
            fabricante_DAO.eliminarFabricantePorCodigo(codigo);                

        } catch (Exception excepcion) {
            throw new Exception("ERROR DE SISTEMA");
        }
    }
    
//  MUESTRA USUARIO ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
    public void muestraFabricante() throws Exception {
        
        try {
            ArrayList<Fabricante> listaFabriantes = fabricante_DAO
        } catch (Exception e) {
        }
        
        
        
    }
}
