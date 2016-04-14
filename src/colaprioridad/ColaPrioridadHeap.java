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
 * @author jose clavijo
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
            } else {
                hijoMayor = hijoIzquierdo;
            }
        } else {
            if (comparador.esMayor(elementos[hijoDerecho], elementos[hijoIzquierdo])) {
                hijoMayor = hijoDerecho;
            } else {
                hijoMayor = hijoIzquierdo;
            }
        }
        if (comparador.esMayor(elementos[hijoMayor], elementos[hijoDerecho])) {
            TYPE aux = elementos[padre];
            elementos[padre] = elementos[hijoMayor];
            elementos[hijoMayor] = aux;
            ordenarHaciaAbajo(hijoMayor);
        }
    }

    void dibujar(Graphics g) {
        g.setFont(new Font("Courier", Font.BOLD, MiPanel.Alto - 4));
        g.drawString("Nro", 30, 15 + MiPanel.Alto - 3);
        g.setFont(new Font("Courier", Font.BOLD, MiPanel.Alto - 4));
        g.drawString("Nombre", 80, 15 + MiPanel.Alto - 3);

        g.setColor(Color.BLUE);
        g.drawLine(25, 37, 450, 37);
        g.setColor(Color.BLACK);
        g.drawLine(25, 38, 450, 38);
        
        if (cantidad != 0) {
            dibujar(g, 30, 40, 0);
        }
    }

    private void dibujar(Graphics g, int x, int y, int indNodo) {

        if (indNodo >= 0 && indNodo < cantidad) {

            Paciente p = (Paciente) elementos[indNodo];
            g.setColor(MiPanel.backgrounds[p.getPrioridad()]);
            g.drawRect(x, y, MiPanel.Ancho, MiPanel.Alto);
            g.drawRect(x + 1, y + 1, MiPanel.Ancho - 2, MiPanel.Alto - 2);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Courier", Font.ITALIC, MiPanel.Alto - 4));
            g.drawString(String.valueOf(p.getCod()), x + 5, y + MiPanel.Alto - 3);
            g.setFont(new Font("Courier", Font.PLAIN, MiPanel.Alto - 4));
            g.drawString(p.getNombre(), x + 50, y + MiPanel.Alto - 3);

            dibujar(g, x, y + MiPanel.Alto + 2, indNodo + 1);
        }
    }

    ColaPrioridadSecuencialOrdenada<Paciente> migrarAColaOrdenada() {
        ColaPrioridadSecuencialOrdenada<Paciente> C = new ColaPrioridadSecuencialOrdenada<>(new ComparadorPaciente());
        int actual = 0;
        while (actual < cantidad) {
            C.insertar((Paciente) elementos[actual]);
            actual++;
        }
        return C;
    }

    ColaPrioridadSecuenciaNoOrdenada<Paciente> migrarAColaNoOrdenada() {
        ColaPrioridadSecuenciaNoOrdenada<Paciente> C = new ColaPrioridadSecuenciaNoOrdenada<>(new ComparadorPaciente());
        int actual = 0;
        while (actual < cantidad) {
            C.insertar((Paciente) elementos[actual]);
            actual++;
        }
        return C;
    }
}
