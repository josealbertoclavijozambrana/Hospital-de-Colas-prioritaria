/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author sharath
 */
public class JColorComboBox extends JComboBox {

    static Color[] fondos;
    static String[] etiquetas;

    public JColorComboBox(Color[] _backgroundColors, String[] _labels) {
        super();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        fondos = _backgroundColors;
        etiquetas = _labels;

        for (String temp : _labels) {
            model.addElement(temp);
        }

        setModel(model);
        setRenderer(new ColorRenderer());
        this.setOpaque(true);
        this.setSelectedIndex(0);
    }

    @Override
    public void setSelectedItem(Object anObject) {
        super.setSelectedItem(anObject);

        int i = 0;
        while (i < etiquetas.length) {
            if (anObject.equals(etiquetas[i])) {
                break;
            }
            i++;
        }
        setBackground(fondos[i]);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        if (anObject.toString().equals("BLACK") || anObject.toString().equals("DARK_GRAY")) {
            setForeground(Color.white);
        }
    }

    public Color getSelectedColor() {

        return this.getBackground();
    }

    class ColorRenderer extends JLabel implements javax.swing.ListCellRenderer {

        public ColorRenderer() {
            this.setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object key, int index,
                boolean isSelected, boolean cellHasFocus) {

            int i = 0;
            while (i < etiquetas.length) {
                if (key.equals(etiquetas[i])) {
                    break;
                }
                i++;
            }

            Color color = fondos[i];
            String name = key.toString();

            list.setSelectionBackground(null);
            list.setSelectionForeground(null);

            if (isSelected) {
                setBorder(BorderFactory.createEtchedBorder());
            } else {
                setBorder(null);
            }
            setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            setBackground(color);
            setText(name);
            setForeground(Color.black);
            if (name.equals("BLACK") || name.equals("DARK_GRAY")) {
                setForeground(Color.white);
            }

            return this;
        }
    }
}
