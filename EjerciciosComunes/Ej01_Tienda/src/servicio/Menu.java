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
import java.util.Scanner;

/**
 *
 * @author FT
 */
public class Menu {

    Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");     //  ISO-8859-1 PERMITE CARACTERES ESPECIALES.-
    private int opcion;
    private ServicioFabricante servFabricante;
    private ServicioProducto servProducto;
    

//  CONSTR:
    public Menu() {
        opcion = 0;
        servFabricante = new ServicioFabricante();                              //  Instancio un nuevo SERVICIO FABRICANTE.-
        servProducto = new ServicioProducto();                                  //  Instancio un nuevo SERVICIO PRODUCTO.-
    }

//  CREA EL MENÚ    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void menuPrincipal() throws Exception {

        do {

            try {
                System.out.println("MENÚ PRINCIPAL:");
                System.out.println("1. Nombre de todos los productos.");
                System.out.println("2. Productos por nombre y precio.");
                System.out.println("3. Precio de producto entre $120 y $202.");
                System.out.println("4. Ver todos los portátiles.");
                System.out.println("5. Productos mas baratos.");
                System.out.println("6. Cargar nuevo producto.");
                System.out.println("7. Cargar nuevo fabricante.");
                System.out.println("8. Editar un producto.");
                System.out.println("0. SALIR.");
                
                opcion = leer.nextInt();

                switch (opcion) {

                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        nuevoProducto();
                        break;

                    case 7:
                        nuevoFabricante();                                      //  Llamo al método creado más abajo.-
                        break;

                    case 8:
                        break;

                    case 9:
                        System.out.println("Nos vemos en Disney");
                    default:
                        System.out.println("Pusiste mal el dedo.");
                }
            } catch (Exception excepcion) {

                excepcion.getMessage();
            }
        } while (opcion != 0);
    }

//  CREA UN FABRICANTE    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void nuevoFabricante() {

        System.out.println("Ingrese nombre del nuevo fabricante:");             //  Pido al usuario nombre de fabricante.-

        try {
            String nombreFabricante = leer.next().trim();                       //  Guardo en un String lo que ingrese el usuario / .TRIM recorta si hay espacios vacíos.-

            servFabricante.crearFabricante(opcion, nombreFabricante);
            
        } catch (Exception exception) {

            exception.getMessage();
        }
    }

//  CREA UN PRODUCTO    +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void nuevoProducto() {
        
        Fabricante objetoFabricante = null;                                     //  Creo un OBJETO FABRICANTE.-

        try {
            //String nombre, Double precio, Fabricante fabricante
            System.out.println("Ingrese nombre del nuevo producto:");           //  Pido al usuario nombre de prod.-
            String nombreProducto = leer.next().trim();                         //  Guardo en un String lo que ingrese el usuario / .TRIM recorta si hay espacios vacíos.-

            System.out.println("Ingrese precio del nuevo producto:");           //  Pido al usuario precio de prod.-
            Double precioProducto = leer.nextDouble();                          //  Guardo en un Double lo que ingrese el usuario.-

            int codigo = leer.nextInt();
            objetoFabricante = servFabricante.verificaFabricante(codigo);
            
            servProducto.crearProducto(nombreProducto, precioProducto, objetoFabricante);

        } catch (Exception exception) {

            exception.getMessage();
        }
    }
}