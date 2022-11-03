/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import servicios.ServicioMenu;

/**
 *
 * @author FT
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServicioMenu menu = new ServicioMenu();
        menu.menuPrincipal();
    }
}
