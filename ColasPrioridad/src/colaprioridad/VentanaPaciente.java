/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Toshiba
 */
public class VentanaPaciente extends JFrame {

    private Object LDatos;
    private MiPanel Enlace;

    public VentanaPaciente(MiPanel _panel) {
        panelPaciente = new JPanel();
        lblNombre = new JLabel("Nombre:");
        lblEdad = new JLabel("Edad:");
        lblPrioridad = new JLabel("Prioridad:");
        lblDetalle = new JLabel("Estado:");
        txfNombre = new JTextField();
        txfEdad = new JTextField();
        txfDetalle = new JTextField();
        ccbPrioridad = new JColorComboBox(MiPanel.backgrounds, MiPanel.status);

        panelPaciente.setBorder(BorderFactory.createTitledBorder("Registro de Paciente"));

        panelPaciente.add(lblNombre);
        panelPaciente.add(lblEdad);
        panelPaciente.add(lblPrioridad);
        panelPaciente.add(lblDetalle);
        panelPaciente.add(txfNombre);
        panelPaciente.add(txfEdad);
        panelPaciente.add(txfDetalle);
        panelPaciente.add(ccbPrioridad);

        panelPaciente.setLayout(null);
        int height = 20;
        lblNombre.setBounds(15, 15, 50, height);
        txfNombre.setBounds(67, 15, 200, height);

        lblEdad.setBounds(28, 40, 30, height);
        txfEdad.setBounds(67, 40, 40, height);
        lblPrioridad.setBounds(115, 40, 50, height);
        ccbPrioridad.setBounds(170, 40, 90, height);

        lblDetalle.setBounds(19, 65, 40, height);
        txfDetalle.setBounds(67, 65, 200, height);

        panel = new JPanel(new BorderLayout());
        btnSave = new JButton();
        btnSave.setText("Guardar");

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jBtnSavePerformed(e);
            }
        });
        btnCancel = new JButton();
        btnCancel.setText("Cancelar");

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jBtnCancelPerformed(e);
            }
        });
        panelBotones = new JPanel();
        panelBotones.add(btnSave);
        panelBotones.add(btnCancel);

        panel.add(panelPaciente, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(panel);
        setSize(300, 170);

        Enlace = _panel;
    }

    private void jBtnSavePerformed(ActionEvent e) {
        //Validaciones
        Paciente p = null;
        if (!txfNombre.getText().isEmpty()) {
            if (ccbPrioridad.getSelectedIndex() > -1) {
                try {
                    p = new Paciente(MiPanel.NroActual + 1, txfNombre.getText(), Integer.parseInt(txfEdad.getText()), txfDetalle.getText(), ccbPrioridad.getSelectedIndex());
                    if (LDatos instanceof ColaPrioridadSecuenciaNoOrdenada) {
                        ((ColaPrioridadSecuenciaNoOrdenada) LDatos).insertar(p);
                    } else if (LDatos instanceof ColaPrioridadSecuencialOrdenada) {
                        ((ColaPrioridadSecuencialOrdenada) LDatos).insertar(p);
                    } else if (LDatos instanceof ColaPrioridadHeap) {
                        ((ColaPrioridadHeap) LDatos).insertar(p);
                    }

                    MiPanel.NroActual++;
                } catch (NumberFormatException numberFormatException) {
                    // Error en la edad

                }

                //Cerrar y devolver los datos
                Enlace.setCola(LDatos);
                Limpiar();
                this.setVisible(false);
            } else {
                // sin prioridad
            }
        } else {
            //Nombre vacio
        }
    }

    private void jBtnCancelPerformed(ActionEvent e) {
        Enlace.setCola(LDatos);
    }

    public void setDatos(Object data){
        LDatos = data;
    }
    private JPanel panel;

    private JPanel panelPaciente;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblPrioridad;
    private JLabel lblDetalle;
    private JTextField txfNombre;
    private JTextField txfEdad;
    private JTextField txfDetalle;
    private JColorComboBox ccbPrioridad;

    private JPanel panelBotones;
    private JButton btnSave;
    private JButton btnCancel;

    private void Limpiar() {
        txfNombre.setText("");
        txfEdad.setText("");
        txfDetalle.setText("");
        ccbPrioridad.setSelectedIndex(0);
    }

}
