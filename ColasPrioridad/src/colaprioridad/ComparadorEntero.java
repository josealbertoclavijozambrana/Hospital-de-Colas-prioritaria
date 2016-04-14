/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

/**
 *
 * @author Usuario
 */
public class ComparadorEntero implements Comparador<Integer>{

    @Override
    public boolean esMayor(Integer objA, Integer objB) {
        return objA > objB;
    }

    @Override
    public boolean esMayorIgual(Integer objA, Integer objB) {
        return objA >= objB;
    }

    @Override
    public boolean esMenor(Integer objA, Integer objB) {
        return objA < objB;
    }

    @Override
    public boolean esMenorIgual(Integer objA, Integer objB) {
        return objA <= objB;
    }
    
}
