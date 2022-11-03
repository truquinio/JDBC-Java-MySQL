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
package servicios;

import java.util.Scanner;

/**
 *
 * @author FT
 */
public class ServicioMenu {
    ServicioProducto servProducto = new ServicioProducto();
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void menuPrincipal(){
        
         System.out.println("Bienvenodo al sistema de Tienda ");
        int opcion = 20;
        
        do {
            do {
             System.out.println("Ingrese la operacion a realizar:"
                     + "\n1. Lista el nombre de todos los productos que hay en la tabla producto."
                     + "\n2. Lista los nombres y los precios de todos los productos de la tabla producto."
                     + "\n3. Listar aquellos productos que su precio esté entre 120 y 202"
                     + "\n4. Buscar y listar todos los Portátiles de la tabla producto"
                     + "\n5. Listar el nombre y el precio del producto más barato"
                     + "\n6. Ingresar un producto a la base de datos "
                     + "\n7. Ingresar un fabricante a la base de datos"
                     + "\n8. Editar un producto con datos a elección."
                     + "\n0. Salir");
             
            try {
                opcion = 20; // se reinicia con una opcion diferente a una valida
                opcion=Integer.parseInt(leer.next());
                break;
                }catch(Exception ex) {
                    System.out.println("Error, ingrese un numero ");
            }
            
            
        } while (opcion!=1 && opcion!=2 && opcion!=3 && opcion!=4 && opcion !=5 
                 && opcion !=6 && opcion !=7 && opcion !=8 && opcion!=0 );
        
       
            switch(opcion)
            {
                case 1:    
                     try {
                        servProducto.listaProductos();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:  
                    try {
                        servProducto.listaProductosMasPrecio();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                break;
                case 3: 
                    try {
                        System.out.println("Ingrese valores maximos y minimos de precios");
                        int min = leer.nextInt();
                        int max = leer.nextInt();
                        servProducto.listaProductos120y202(min,max);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                break;
                case 4:
                    try {
                        servProducto.listaProductosPortatil();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        servProducto.buscarProductosMasBarato();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    
                    break;
                case 0: break;
            }
        } while (opcion != 0);
        
    }
}