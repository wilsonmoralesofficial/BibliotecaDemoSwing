package org.example.wsoc.views.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class button {

    public JButton button;

    public JButton createRightButtonPanel(String buttonText,ActionListener actionListener){

        button = new JButton(buttonText);
        button.addActionListener(actionListener);
        return button;
    }
}
