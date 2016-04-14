/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Usuario
 */
public class Nodo<TYPE> {

    private TYPE contenido;
    private Nodo<TYPE> anterior;
    private Nodo<TYPE> siguiente;

    public Nodo(TYPE contenido) {
        this.contenido = contenido;
    }

    public TYPE getContenido() {
        return contenido;
    }

    public void setContenido(TYPE contenido) {
        this.contenido = contenido;
    }

    public Nodo<TYPE> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<TYPE> anterior) {
        this.anterior = anterior;
    }

    public Nodo<TYPE> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<TYPE> siguiente) {
        this.siguiente = siguiente;
    }

    

}
