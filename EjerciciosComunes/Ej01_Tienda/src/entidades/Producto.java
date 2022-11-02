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
    private Integer codigo;                                                     //Integer: por si necesito validar si el código es NULL / usar comparator.-
    private String nombre;
    private Double precio;
    private Fabricante fabricante;                                              //Para la LLAVE FORÁNEA, creo un objeto como atributo.-
    // private Fabricante codigo_fabricante; Le cambié el nombre para que se entienda mejor en ProductoDao.-
    
//CONST:
    public Producto() {
        this.fabricante = new Fabricante();  //En el CONSTR vacío, instancio el objeto "Fabricante".-
    }
    public Producto(Integer codigo, String nombre, Double precio, Fabricante codigo_fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = codigo_fabricante;
    }

//GyS:
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Fabricante getFabricante() {
        return fabricante;
    }
    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}