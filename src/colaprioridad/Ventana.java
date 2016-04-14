/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author  jose clavijo
 */
public class Ventana extends JFrame {

    public Ventana() {
        init();
    }

    private void init() {

        barra = new JMenuBar();
        menuEstructura = new JMenu();
        colaP = new JMenuItem();
        colaPNO = new JMenuItem();
        colaPH = new JMenuItem();

        menuEstructura.setText("Migrar Estructura");

        colaP.setText("Cola de Prioridad Ordenada");
        colaPNO.setText("Cola de Prioridad No Ordenada");
        colaPH.setText("Cola de Prioridad Heaps");

        colaP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colaPO_ActionPerformed(e);
            }
        });

        colaPNO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colaPNO_ActionPerformed(e);
            }
        });

        colaPH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colaPH_ActionPerformed(e);
            }
        });

        menuEstructura.add(colaP);
        menuEstructura.add(colaPNO);
        menuEstructura.add(colaPH);

        barra.add(menuEstructura);

        panel = new MiPanel();
        panel.iniciar();

        getContentPane().add(panel);
        setJMenuBar(barra);
        setTitle("Atencion al Paciente");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void colaPO_ActionPerformed(ActionEvent e) {
        System.out.println("Cambio a Cola Ordenada");
        panel.MigrarAColaOrdenada();
    }

    private void colaPNO_ActionPerformed(ActionEvent e) {
        System.out.println("Cambio a Cola No Ordenada");
        panel.MigrarAColaNoOrdenada();
    }

    private void colaPH_ActionPerformed(ActionEvent e) {
        System.out.println("Cambio a Cola Heap");
        panel.MigrarAColaHeap();
    }
    
    private JMenuBar barra;
    private JMenu menuEstructura;
    private JMenuItem colaP;
    private JMenuItem colaPNO;
    private JMenuItem colaPH;
    private MiPanel panel;

}
