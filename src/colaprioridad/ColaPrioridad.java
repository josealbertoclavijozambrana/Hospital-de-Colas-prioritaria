/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.Graphics;
import java.util.Observable;

/**
 *
 * @author jose clavijo
 */
public abstract class ColaPrioridad<TYPE> extends Observable{
    
    protected Comparador<TYPE> comparador;

    public ColaPrioridad(Comparador<TYPE> comparador) {
        this.comparador = comparador;
        
    } 
    
    
    public abstract void insertar(TYPE obj);
    
    public abstract TYPE eliminar() throws Exception;
    
    public abstract TYPE consultar() throws Exception;
    
    public abstract boolean estaVacio();
    
}
