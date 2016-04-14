/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author jose clavijo
 */
public class Main {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        Ventana frame = new Ventana();
        frame.setVisible(true);
    }

}
