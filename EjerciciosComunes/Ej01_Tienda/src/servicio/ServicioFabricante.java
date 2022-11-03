/*
Paquete servicios:

En este paquete se almacenarán aquellas clases que llevarán adelante lógica del negocio.  En 
general se crea un servicio para administrar cada una de las entidades y algunos servicios 
para manejar operaciones muy específicas como las estadísticas.
 */
package servicio;

import com.sun.istack.internal.logging.Logger;
import entidades.Fabricante;
import java.util.ArrayList;
import java.util.logging.Level;
import persistencia.FabricanteDAO;

/**
 *
 * @author FT
 */
public class ServicioFabricante {

//  ATTR:
    private FabricanteDAO fabricante_DAO;                                       //  CREO el OBJETO desde clase FABRICANTE DAO.-

//  CONSTR:
    public ServicioFabricante() {
        fabricante_DAO = new FabricanteDAO();                                   //  Instancio el OBJETO FABRICANTE DAO.-
    }

//  CREAR USUARIO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void crearFabricante(Integer codigo, String nombre) throws Exception {

        try {
            //  VALIDACIÓN si código viene NULO o VACÍO.-
            if (codigo == null | codigo < 0) {
                throw new Exception("DEBE INGRESAR UN CÓDIGO");
            }
            //  VALIDACIÓN si nombre viene NULO o VACÍO.-
            if (nombre == null | nombre.trim().isEmpty()) {                     //  .TRIM recorta si hay espacios vacíos.-
                throw new Exception("DEBE INGRESAR UN NOMBRE");
            }

            Fabricante Objetofabricante = new Fabricante();

            //Objetofabricante.setCodigo(codigo);                               //  Seteo el código.-
            Objetofabricante.setNombre(nombre);                                 //  Seteo el nombre.-

            fabricante_DAO.guardarFabricante(Objetofabricante);                 //  Guardo en el método GUARDAR FABRICANTE de la clase FABRICANTE DAO.-

        } catch (Exception excepcion) {

            //  excepcion.printStackTrace();
            throw new Exception("ERROR DE SISTEMA");
        }
    }

//  MODIFICA USUARIO +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void modificarFabricante(Integer codigo, String nombre) throws Exception {

        try {
            //  VALIDACIÓN si código viene NULO o VACÍO.-
            if (codigo == null || codigo < 0) {
                throw new Exception("DEBE INGRESAR UN CÓDIGO");
            }
            //  VALIDACIÓN si nombre viene NULO o VACÍO.-
            if (nombre == null || nombre.trim().isEmpty()) {                    //  .TRIM recorta si hay espacios vacíos.-
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

            //  Busco por CÓDIGO en la BASE de DATOS, si existe un FABRICANTE.-
            Fabricante objetoFabricante = fabricante_DAO.buscarFabricantePorCodigo(codigo);

            if (objetoFabricante == null) {
                throw new Exception("EL CÓDIGO NO ESTÁ ASOCIADO A NINGÚN FABRICANTE");
            }

            System.out.println("Se va a eliminar el fabricante: " + objetoFabricante.getNombre());
            fabricante_DAO.eliminarFabricantePorCodigo(codigo);

        } catch (Exception excepcion) {
            throw new Exception("ERROR DE SISTEMA");
        }
    }

//  MUESTRA USUARIO ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void muestraFabricante() throws Exception {

        try {
            ArrayList<Fabricante> listaFabricantes = fabricante_DAO.obtenerFabricantes();

            if (listaFabricantes.isEmpty()) {
                throw new Exception("NO EXISTEN FABRICANTES");

            } else {
                System.out.println("Lista de Fabricantes:");
                System.out.printf("%-15s15s\n", "CÓDIGO", "NOMBRE");            //  Formato de impresión.-

                for (Fabricante fabricantesForEach : listaFabricantes) {
                    System.out.println(fabricantesForEach);
                }
                System.out.println();
            }
        } catch (Exception excepcion) {
            throw new Exception("ERROR DE SISTEMA");
        }
    }

//  VERIFICA FABRICANTE    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Fabricante verificaFabricante(int codigo) {

        Fabricante objetoFabricante = null;

        try {
            objetoFabricante = fabricante_DAO.buscarFabricantePorCodigo(codigo);

        } catch (Exception excepcion) {

            java.util.logging.Logger.getLogger(ServicioFabricante.class.getName()).log(Level.SEVERE, null, excepcion);

            System.out.println("ALGO FALLÓ");
        }
        return objetoFabricante;
    }
}