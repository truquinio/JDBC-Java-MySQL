/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

/**
 *
 * @author FT
 */
public class MiExcepcion extends Exception {

    public MiExcepcion() {
    }

    public MiExcepcion(String mensaje) {
        super(mensaje);
    }
}