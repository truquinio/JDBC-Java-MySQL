/*
Paquete entidades:

Dentro de este paquete se deben crear todas las clases necesarias que vamos a usar de la 
base de datos. Por ejemplo, una de las clases a crear dentro de este paquete es la clase 
“Producto.java” con los siguientes atributos:

•  private int codigo;
•  private String nombre;
•  private double precio;
•  private int codigoFabricante;

Agregar a cada clase el/los constructores necesarios y los métodos públicos getters y setters 
para poder acceder a los atributos privados de la clase. La llave foránea se pondrá como dato 
nada más, no como objeto.
 */
package entidades;

/**
 *
 * @author FT
 */
public class Fabricante {

//ATTR:
    private int codigo;
    private String nombre;

//CONSTR:
    public Fabricante() {
    }
    public Fabricante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

//GyS:
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
