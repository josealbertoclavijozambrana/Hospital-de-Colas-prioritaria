/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        ColaPrioridad<Integer> cola = new ColaPrioridadHeap<>(new ComparadorEntero());
        
        System.out.println("Esta vacia? = " + cola.estaVacio());
        
        cola.insertar(24);
        cola.insertar(18);
        cola.insertar(16);
        cola.insertar(31);
        cola.insertar(40);
        cola.insertar(22);
        cola.insertar(45);
        cola.insertar(82);
        cola.insertar(46);
        
        System.out.println("Esta vacia? =" + cola.estaVacio());
        
        System.out.println(cola);
        
        try {      
            System.out.println("Consultar: " + cola.consultar());
        } catch (Exception e) {
            
        }
        System.out.println(cola);
        try {
            System.out.println("consultar " + cola.consultar());
            System.out.println("Eliminar " +cola.eliminar());
            System.out.println(cola);
            System.out.println("consultar "+ cola.consultar());
            System.out.println("eliminar "+ cola.eliminar());
            System.out.println(cola);
            System.out.println("consultar " +cola.consultar());
            System.out.println(cola.eliminar());
            System.out.println(cola);
            System.out.println(cola.consultar());
            System.out.println(cola.eliminar());
            System.out.println(cola);
            System.out.println(cola.consultar());
            System.out.println(cola.eliminar());
            System.out.println(cola);
            System.out.println(cola.eliminar());
            System.out.println("Esta vacia? = " + cola.estaVacio());
            
            System.out.println(cola);
            
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
    }
}
