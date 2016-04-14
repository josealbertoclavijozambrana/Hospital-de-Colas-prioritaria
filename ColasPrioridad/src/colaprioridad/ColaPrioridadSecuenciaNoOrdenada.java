/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Diego
 */
public class ColaPrioridadSecuenciaNoOrdenada<TYPE> extends ColaPrioridad<TYPE> {

    private Nodo<TYPE> primero;
    private Nodo<TYPE> ultimo;
    private int cantidadElementos;

    public ColaPrioridadSecuenciaNoOrdenada(Comparador<TYPE> comparador) {
        super(comparador);
        if (comparador == null) {
            throw new IllegalArgumentException("El comparador no puede ser nulo");
        }
    }

    @Override
    public void insertar(TYPE obj) {
        Nodo<TYPE> nuevo = new Nodo(obj);
        if (cantidadElementos == 0) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        cantidadElementos++;
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
        if (cantidadElementos == 0) {
            throw new Exception("La cola esta vacia");
        }

        Nodo<TYPE> nodo = getNodoMaximo();
        return nodo.getContenido();
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
        return cantidadElementos == 0;
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
        g.setFont(new Font("Courier", Font.BOLD, MiPanel.Alto - 4));
        g.drawString("Nro", 30, 15 + MiPanel.Alto - 3);
        g.setFont(new Font("Courier", Font.BOLD, MiPanel.Alto - 4));
        g.drawString("Nombre", 80, 15 + MiPanel.Alto - 3);
        
        g.drawLine(25, 38, 450, 38);

        if (cantidadElementos != 0) {
            dibujar(g, 30, 40, primero);
        }
    }

    private void dibujar(Graphics g, int x, int y, Nodo<TYPE> nodo) {
        if (nodo != null) {
            Paciente p = (Paciente) nodo.getContenido();
            g.setColor(MiPanel.backgrounds[p.getPrioridad()]);
            g.drawRect(x, y, MiPanel.Ancho, MiPanel.Alto);
            g.drawRect(x + 1, y + 1, MiPanel.Ancho - 2, MiPanel.Alto - 2);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Courier", Font.ITALIC, MiPanel.Alto - 4));
            g.drawString(String.valueOf(p.getCod()), x + 5, y + MiPanel.Alto - 3);
            g.setFont(new Font("Courier", Font.PLAIN, MiPanel.Alto - 4));
            g.drawString(p.getNombre(), x + 50, y + MiPanel.Alto - 3);

            dibujar(g, x, y + MiPanel.Alto + 2, nodo.getSiguiente());
        }
    }

}
