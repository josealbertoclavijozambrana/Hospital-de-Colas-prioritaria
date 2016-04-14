/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

/**
 *
 * @author Diego
 */
public interface Comparador<TYPE> {
    
    public boolean esMayor(TYPE objA, TYPE objB);
    
    public boolean esMayorIgual(TYPE objA, TYPE objB);
    
    public boolean esMenor(TYPE objA, TYPE objB);
    
    public boolean esMenorIgual(TYPE objA, TYPE objB);
    
    
}
