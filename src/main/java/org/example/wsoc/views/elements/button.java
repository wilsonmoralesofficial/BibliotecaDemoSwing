package org.example.wsoc.views.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class button {

    private JButton button;

    public JPanel createRightButtonPanel(String buttonText,ActionListener actionListener){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinea los botones a la derecha
        button = new JButton(buttonText);
        buttonPanel.add(button);
        button.addActionListener(actionListener);
        return buttonPanel;
    }
}
