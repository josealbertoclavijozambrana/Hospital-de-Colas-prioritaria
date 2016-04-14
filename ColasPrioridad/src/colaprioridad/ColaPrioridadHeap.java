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
public class ColaPrioridadHeap<TYPE> extends ColaPrioridad<TYPE> {

    private int cantidad;
    private TYPE[] elementos;

    public ColaPrioridadHeap(Comparador<TYPE> comparador) {
        super(comparador);
        this.cantidad = 0;
        this.elementos = (TYPE[]) new Object[8];

    }

    @Override
    public void insertar(TYPE obj) {
//           if (elementos == elementos.length) {
//            redimensionar(elementos);
//        }
        if (cantidad == elementos.length) {
            redimensionar();
        }
        int primerPosicionVacio = cantidad;
        elementos[primerPosicionVacio] = obj;
        ordernarHaciaArriba(cantidad);
        cantidad++;
    }

//            if (cantidad == elementos.length) {
//            redimensionar(elementos);
//        }
//        int primerPosicionVacio = cantidad;
//        elementos[primerPosicionVacio] = obj;
//        ordernarHaciaArriba(cantidad);
//        cantidad++;
//    }
    private void ordernarHaciaArriba(int i) {
        if (i == 0) {
            return;
        }
        int padre = getPadre(i);
        if (comparador.esMenor(elementos[padre], elementos[i])) {
            TYPE obj = elementos[padre];
            elementos[padre] = elementos[i];
            elementos[i] = obj;
            ordernarHaciaArriba(padre);
        }
    }

    public static int getPadre(int i) {
        return (i - 1) / 2;
    }

    public static int getHijoIzquierdo(int i) {
        return 2 * i + 1;
    }

    public static int getHijoDerecho(int i) {
        return 2 * i + 2;
    }

    @Override
    public TYPE consultar() throws Exception {
        if (cantidad == 0) {
            throw new Exception("La cola esta vacia");
        }
        return elementos[0];
    }

    @Override
    public boolean estaVacio() {
        return cantidad == 0;
    }

    public String toString() {
        String convertirString = "";
        for (int i = 0; i < cantidad; i++) {
            TYPE e = elementos[i];
            convertirString += (e == null ? "null" : e.toString()) + " ";
        }
        return convertirString;
    }

    private void redimensionar() {
        TYPE[] arregloNuevo = (TYPE[]) new Object[(elementos.length - 1) * 2 + 1];
        for (int i = 0; i < elementos.length; i++) {
            arregloNuevo[i] = elementos[i];
        }
        elementos = arregloNuevo;
    }

    @Override
    public TYPE eliminar() throws Exception {
        if (cantidad == 0) {
            throw new Exception("La cola esta vacia");
        }
        TYPE elementoAeliminar = elementos[0];
        int ultimaPosicion = cantidad - 1;
        elementos[0] = elementos[ultimaPosicion];
        elementos[ultimaPosicion] = null;
        cantidad--;
        ordenarHaciaAbajo(0);

        return elementoAeliminar;
    }

    private void ordenarHaciaAbajo(int padre) {
        int hijoIzquierdo = getHijoIzquierdo(padre);
        int hijoDerecho = getHijoDerecho(padre);
        int hijoMayor = padre;

        if (hijoDerecho >= cantidad) {
            if (hijoIzquierdo >= cantidad) {
                return; // es hoja
            } else
                hijoMayor = hijoIzquierdo;
        }else{
            if (comparador.esMayor(elementos [hijoDerecho],elementos[hijoIzquierdo])) 
                hijoMayor =hijoDerecho;
                else
                hijoMayor = hijoIzquierdo;  
        }
        if (comparador.esMayor(elementos[hijoMayor],elementos[hijoDerecho])) {
            TYPE aux = elementos[padre];
            elementos [padre]= elementos[hijoMayor];
            elementos[hijoMayor]= aux;
            ordenarHaciaAbajo(hijoMayor);
        }
    }

    void dibujar(Graphics g) {
        
    }

}
