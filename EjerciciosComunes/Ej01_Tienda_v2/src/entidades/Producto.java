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
public class Producto {

    //ATTR:
    private int codigo;
    private String nombre;
    private double precio;
    private int codigoFabricante;

    //CONSTR:
    public Producto() {
    }
    public Producto(int codigo, String nombre, double precio, int codigoFabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codigoFabricante = codigoFabricante;
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
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCodigoFabricante() {
        return codigoFabricante;
    }
    public void setCodigoFabricante(int codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    //TOSTRING:
    @Override
    public String toString() {
        return "| Producto: " + " | Código: " + codigo + " | Nombre: " + nombre + " | Precio: " + precio + " | Codigo Fabricante: " + codigoFabricante;
    }
}