/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.Graphics;

/**
 *
 * @author Diego
 */
public class ColaPrioridadSecuencialOrdenada<TYPE> extends ColaPrioridad<TYPE> {

    private Nodo<TYPE> primero;
    private Nodo<TYPE> ultimo;
    private int cantidadElementos;

    public ColaPrioridadSecuencialOrdenada(Comparador<TYPE> comparador) {
        super(comparador);
        if (comparador == null) {
            throw new IllegalArgumentException("El comparador no puede ser nulo");
        }
    }

    @Override
    public void insertar(TYPE obj) {
        Nodo<TYPE> newNodo = new Nodo<>(obj);
        if (estaVacio()) {
            primero = newNodo;
            ultimo = newNodo;
            return;
        }
        if (comparador.esMenor(obj, primero.getContenido())) {
            newNodo.setSiguiente(primero);
            primero.setAnterior(newNodo);
            primero = newNodo;
            return;
        }

        if (comparador.esMayor(obj, ultimo.getContenido())) {
            newNodo.setAnterior(ultimo);
            ultimo.setSiguiente(newNodo);
            ultimo = newNodo;
            return;
        }

        Nodo<TYPE> actual = primero;
        while (comparador.esMayor(obj, actual.getContenido())) {
            actual = actual.getSiguiente();
        }

        newNodo.setSiguiente(actual);
        newNodo.setAnterior(actual.getAnterior());

        actual.getAnterior().setSiguiente(newNodo);
        actual.setAnterior(newNodo);
    }

    @Override
    public TYPE eliminar() throws Exception {
        if (cantidadElementos == 0) {
            throw new Exception("La cola esta vacia");
        }

        Nodo<TYPE> nodoAEliminar = getNodoMaximo();
        TYPE contenido = nodoAEliminar.getContenido();

        //Proceso de eliminacion
        if (primero == ultimo) {
            primero = null;
            ultimo = null;
            cantidadElementos = 0;
            return contenido;
        }

        if (nodoAEliminar == primero) {
            primero = nodoAEliminar.getSiguiente();
            primero.setAnterior(null);
            nodoAEliminar.setSiguiente(null);
            cantidadElementos--;
            return contenido;
        }

        if (nodoAEliminar == ultimo) {
            ultimo = nodoAEliminar.getAnterior();
            ultimo.setSiguiente(null);
            nodoAEliminar.setAnterior(null);
            cantidadElementos--;
            return contenido;
        }

        nodoAEliminar.getAnterior()
                .setSiguiente(nodoAEliminar.getSiguiente());
        nodoAEliminar.getSiguiente()
                .setAnterior(nodoAEliminar.getAnterior());

        nodoAEliminar.setSiguiente(null);
        nodoAEliminar.setAnterior(null);
        cantidadElementos--;

        return contenido;
    }

    @Override
    public TYPE consultar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Nodo<TYPE> getNodoMaximo() {
        if (cantidadElementos == 0) {
            return null;
        }

        if (cantidadElementos == 1) {
            return primero;
        }

        Nodo<TYPE> mayor = primero;
        Nodo<TYPE> nodoAux = primero.getSiguiente();
        while (nodoAux != null) {
            if (this.comparador.esMayor(nodoAux.getContenido(),
                    mayor.getContenido())) {
                mayor = nodoAux;
            }

            nodoAux = nodoAux.getSiguiente();
        }
        return mayor;

    }

    @Override
    public boolean estaVacio() {
        return primero == null;
    }

    public String toString() {
        Nodo<TYPE> aux = primero;
        String resultado = "";
        while (aux != null) {
            resultado += aux.getContenido().toString() + " - ";
            aux = aux.getSiguiente();
        }
        return resultado;
    }

    void dibujar(Graphics g) {
        
    }

}
