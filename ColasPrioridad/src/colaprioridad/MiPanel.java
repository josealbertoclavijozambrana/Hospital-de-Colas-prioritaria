/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author
 */
 public class MiPanel extends JPanel implements Observer {

    private Object Datos;

    public static final String[] status = {"Bueno", "Indeterminado", "Regular", "Grave", "Critico"};
    public static final Color[] backgrounds = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};

    public static final int Ancho = 400;
    public static final int Alto = 20;
    public static int NroActual = 0;

    public MiPanel() {
        super(new BorderLayout());

        panelDibujo = new DrawingPane();
        panelDibujo.setBackground(new Color(247, 247, 247));
        panelDibujo.setBorder(BorderFactory.createTitledBorder("Pacientes"));

        JScrollPane scroller = new JScrollPane(panelDibujo);
        scroller.setPreferredSize(new Dimension(200, 200));

        btnAdd = new JButton();
        btnAdd.setText("Agregar Paciente");

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jBtnAddPerformed(e);
            }
        });
        btnDel = new JButton();
        btnDel.setText("Atender Paciente");
        btnDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jBtnDelPerformed(e);
            }
        });

        panelBotones = new JPanel();
        panelBotones.add(btnAdd);
        panelBotones.add(btnDel);

        add(scroller, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

    }

    @Override
    public void update(Observable o, Object arg) {
        Datos = o;
    }

    private void jBtnAddPerformed(ActionEvent e) {
        if (VPaciente == null) {
            VPaciente = new VentanaPaciente(this);
        }
        VPaciente.setDatos(Datos);
        VPaciente.setVisible(true);
    }

    private void jBtnDelPerformed(ActionEvent e) {
        if (Datos instanceof ColaPrioridadSecuenciaNoOrdenada) {
            try {
                Paciente P = (Paciente)((ColaPrioridadSecuenciaNoOrdenada) Datos).consultar();
                String Mensaje = "Datos del Paciente:\n"
                        + "Nro: " + P.getCod() +"\n"
                        + "Nombre: " + P.getNombre() +"\n"
                        + "Edad: " + P.getEdad() +"\n"
                        + "Prioridad: " + MiPanel.status[P.getPrioridad()] +"\n"
                        + "Detalle: " + P.getDetalle() +"\n";
                ((ColaPrioridadSecuenciaNoOrdenada) Datos).eliminar();
                JOptionPane.showMessageDialog(this, Mensaje);
            } catch (Exception ex) {
                //Vacia
                
            }
        } else if (Datos instanceof ColaPrioridadSecuencialOrdenada) {
            try {
                Paciente P = (Paciente)((ColaPrioridadSecuencialOrdenada) Datos).consultar();
                String Mensaje = "Datos del Paciente:\n"
                        + "Nro: " + P.getCod() +"\n"
                        + "Nombre: " + P.getNombre() +"\n"
                        + "Edad: " + P.getEdad() +"\n"
                        + "Prioridad: " + MiPanel.status[P.getPrioridad()] +"\n"
                        + "Detalle: " + P.getDetalle() +"\n";
                ((ColaPrioridadSecuencialOrdenada) Datos).eliminar();
                JOptionPane.showMessageDialog(this, Mensaje);
            } catch (Exception ex) {
                //Vacia
                
            }
        } else if (Datos instanceof ColaPrioridadHeap) {
            try {
                Paciente P = (Paciente)((ColaPrioridadHeap) Datos).consultar();
                String Mensaje = "Datos del Paciente:\n"
                        + "Nro: " + P.getCod() +"\n"
                        + "Nombre: " + P.getNombre() +"\n"
                        + "Edad: " + P.getEdad() +"\n"
                        + "Prioridad: " + MiPanel.status[P.getPrioridad()] +"\n"
                        + "Detalle: " + P.getDetalle() +"\n";
                ((ColaPrioridadHeap) Datos).eliminar();
                JOptionPane.showMessageDialog(this, Mensaje);
            } catch (Exception ex) {
                //Vacia
                Logger.getLogger(MiPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setCola(Object _data) {
        Datos = _data;
        this.panelDibujo.repaint();
    }

    public class DrawingPane extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (Datos != null) {
                // Dibujar
                if (Datos instanceof ColaPrioridadSecuenciaNoOrdenada) {
                    ((ColaPrioridadSecuenciaNoOrdenada) Datos).dibujar(g);
                } else if (Datos instanceof ColaPrioridadSecuencialOrdenada) {
                    ((ColaPrioridadSecuencialOrdenada) Datos).dibujar(g);
                } else if (Datos instanceof ColaPrioridadHeap) {
                    ((ColaPrioridadHeap) Datos).dibujar(g);
                }
            }
        }
    }

    private JPanel panelDibujo;
    private JPanel panelBotones;
    private JButton btnAdd;
    private JButton btnDel;
    private JPanel panelB;
    private Dimension area;
    private VentanaPaciente VPaciente;
}
