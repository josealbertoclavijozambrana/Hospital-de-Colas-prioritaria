/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

/**
 *
 * @author Toshiba
 */
public class ComparadorPaciente implements Comparador<Paciente> {

    @Override
    public boolean esMayor(Paciente objA, Paciente objB) {
        return objA.getPrioridad() > objB.getPrioridad();
    }

    @Override
    public boolean esMayorIgual(Paciente objA, Paciente objB) {
        return objA.getPrioridad() >= objB.getPrioridad();
    }

    @Override
    public boolean esMenor(Paciente objA, Paciente objB) {
        return objA.getPrioridad() < objB.getPrioridad();
    }

    @Override
    public boolean esMenorIgual(Paciente objA, Paciente objB) {
        return objA.getPrioridad() <= objB.getPrioridad();
    }
}
