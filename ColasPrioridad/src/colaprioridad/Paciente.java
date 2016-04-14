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
public class Paciente {

    private int Cod;
    private String Nombre;
    private String Detalle;
    private int Prioridad;

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    private int Edad;

    public Paciente() {
        this.Cod = -1;
        this.Nombre = "";
        this.Detalle = "";
        this.Prioridad = -1;
    }

    public Paciente(int _Cod, String _Nombre, int _Edad, String _Detalle, int _Prioridad) {
        this.Cod = _Cod;
        this.Nombre = _Nombre;
        this.Detalle = _Detalle;
        this.Prioridad = _Prioridad;
        this.Edad = _Edad;
    }

    public int getCod() {
        return Cod;
    }

    public void setCod(int _Cod) {
        this.Cod = _Cod;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public int getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(int Prioridad) {
        this.Prioridad = Prioridad;
    }

    @Override
    public String toString() {
        return "Paciente{" + "Nro=" + Cod + " | Nombre=" + Nombre + " | Detalle=" + Detalle + " | Prioridad=" + Prioridad + '}';
    }

    String toStringL() {
        return Cod + "   " + Nombre;
    }
}
